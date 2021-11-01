package co.usaciclo3.ciclo3.service;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.usaciclo3.ciclo3.Reports.CountClient;
import co.usaciclo3.ciclo3.Reports.ReservationStatus;
import co.usaciclo3.ciclo3.model.Reservation;
import co.usaciclo3.ciclo3.repository.ReservationRepository;

@Service
public class ReservationService {
     
    @Autowired
    private ReservationRepository reservationRepository;

    public List<Reservation> getAll(){
        return reservationRepository.getAll();
    }
    
    public Optional<Reservation> getReservation(int id){
        return reservationRepository.getReservation(id);
    }

    public Reservation save(Reservation r){
        if(r.getIdReservation()==null){
            return reservationRepository.save(r);
        }else{
            //Valida si el Id existe, si es vacío no existe y lo guarda
            Optional<Reservation> raux=reservationRepository.getReservation(r.getIdReservation());
            if (raux.isEmpty()){
                return reservationRepository.save(r);
            }else{
                return r;
            }
        }
    }
    
    public Reservation update(Reservation reservation) {
        if (reservation.getIdReservation() != null) {
            Optional<Reservation> e = reservationRepository.getReservation(reservation.getIdReservation());
            if (!e.isEmpty()) {
                if (reservation.getStartDate() != null) {
                    e.get().setStartDate(reservation.getStartDate());
                }
                if (reservation.getDevolutionDate() != null) {
                    e.get().setDevolutionDate(reservation.getDevolutionDate());
                }
                if (reservation.getStatus() != null) {
                    e.get().setStatus(reservation.getStatus());
                }
                reservationRepository.save(e.get());
                return e.get();

            } else {
                return reservation;
            }
        } else {
            return reservation;
        }
    }
    
    public boolean deleteReservation(int id){
        Boolean aBoolean=getReservation(id).map(reservation -> {
            reservationRepository.delete(reservation);
            return true;
        }).orElse(false);
        return aBoolean;
    }

    public ReservationStatus getReservationStatusReport(){
        List<Reservation> completed = reservationRepository.getReservationByStatus("completed");
        List<Reservation> cancelled = reservationRepository.getReservationByStatus("cancelled");
        return new ReservationStatus(completed.size(), cancelled.size());

        
    }

    public List<Reservation> getReservationPeriodo(String dateOne, String dateTwo){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date startDate = dateFormat.parse(dateOne);
            Date endDate = dateFormat.parse(dateTwo);
            if(startDate.before(endDate)){
                return reservationRepository.getReservationPeriod(startDate, endDate);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return new ArrayList<>();
    }
    public List<CountClient> getTopClients(){
        return reservationRepository.getTopClient();
    }
}

