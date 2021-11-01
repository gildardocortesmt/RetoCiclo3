package co.usaciclo3.ciclo3.repository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import co.usaciclo3.ciclo3.Reports.CountClient;
import co.usaciclo3.ciclo3.model.Client;
import co.usaciclo3.ciclo3.model.Reservation;
import co.usaciclo3.ciclo3.repository.crud.ReservationCrudRepository;

@Repository
public class ReservationRepository {
    @Autowired
    private ReservationCrudRepository reservationCrudRepository;
    
    public List<Reservation> getAll(){
        return (List<Reservation>) reservationCrudRepository.findAll();
    }

    public Optional<Reservation> getReservation(int id){
        return reservationCrudRepository.findById(id);
    }

    public Reservation save(Reservation r){
        return reservationCrudRepository.save(r);
    }
    public void delete(Reservation reservation){
        reservationCrudRepository.delete(reservation);
    }
    
    public List<Reservation> getReservationByStatus(String status){
        return reservationCrudRepository.findAllByStatus(status);
    }

    public List<Reservation> getReservationPeriod(Date dateOne, Date dateTwo){
        return reservationCrudRepository.findAllByStartDateAfterAndStartDateBefore(dateOne,dateTwo);
    }

    public List<CountClient> getTopClient(){
        List<CountClient> clientList = new ArrayList<>();
        List<Object[]> report = reservationCrudRepository.countTotalReservationByClient();

        for(int i=0;i<report.size();i++){
            clientList.add(new CountClient((Long) report.get(i)[1],(Client)report.get(i)[0]));
        }
        return clientList;
    }
}
