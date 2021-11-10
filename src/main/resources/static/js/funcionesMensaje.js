// Se envía solo un parámetro que es un diccionario, lee el servicio y carga los datos al diccionario json
//DEBO CAMBIAR LA URL Y LA ESTRUCTURA DEL DICCIONARIO JSON


function autoInicioRelacionCliente(){
    
    $.ajax({
        url:"http://129.151.125.142:8080/api/Client/all",
        type:"GET",
        datatype:"JSON",
        success:function(respuesta){
          
            let $select = $("#select-client");
            $.each(respuesta, function (id, name) {
                $select.append('<option value='+name.idClient+'>'+name.name+'</option>');
            
            }); 
        }
    
    })
}
function autoInicioCostume(){

    $.ajax({
        url:"http://129.151.125.142:8080/api/Costume/all",
        type:"GET",
        datatype:"JSON",
        success:function(respuesta){
        
            let $select = $("#select-costume");
            $.each(respuesta, function (id, name) {
                $select.append('<option value='+name.id+'>'+name.name+'</option>');
         
            }); 
        }
    
    })
}

function limpiar(){

    document.getElementById("idMessageText").value = "";
    document.getElementById("select-costume").value = "";
    document.getElementById("select-client").value = "";
}

function pintarRespuesta(respuesta){

    $("#idDivConsulta").empty();
    $("#idDivConsulta").append("<caption>Tabla Mensaje</caption>");
    $("#idDivConsulta").append("<tr><th>Mensaje</th><th>Cliente</th><th>Disfraz</th></tr>");
   
    for(i=0;i<respuesta.length;i++){

        $("#idDivConsulta").append("<tr>");
        $("#idDivConsulta").append("<td>" + respuesta[i].messageText + "</td>");
        $("#idDivConsulta").append("<td>" + respuesta[i].client.name + "</td>"); 
        $("#idDivConsulta").append("<td>" + respuesta[i].costume.name + "</td>");        
        $("#idDivConsulta").append('<td><button onclick="borrar('+respuesta[i].idMessage+')">Borrar</button> </td>');
        $("#idDivConsulta").append('<td><button onclick="actualizar('+respuesta[i].idMessage+')">Actualizar</button> </td>');
       
        $("#idDivConsulta").append("</tr>");
    }   
    console.log(respuesta)

}

function obtenerItems(){
    $.ajax({
        url:"http://129.151.125.142:8080/api/Message/all",
        type:"GET",
        datatype:"JSON",
        success:function(respuesta){
            console.log(respuesta);
            pintarRespuesta(respuesta);
        }
    });
}

function insertar() {

    var name= $("#idMessageText").val();
    var disfraz =$("#select-costume").val();
    var cliente= $("#select-client").val();

  
    if(name.length==0 || disfraz.length==0 || cliente.length==0){
        alert('Error, debe completar todos los campos');
        $("#idMessageText").focus();
        return false;
    }
    else{
    let elemento;
    elemento = { 
       
        messageText:$("#idMessageText").val(),
        client: {idClient:cliente},
        costume:{id:disfraz}
    };
   
    $.ajax (
        {
            type:'POST',
            contentType: "application/json; charset=utf-8",
            dataType: 'JSON',
            data: JSON.stringify(elemento),
            url:"http://129.151.125.142:8080/api/Message/save",

            success      :  function(response){
                               
                               console.log(response);
                               alert("El mensage se guardo correctamente");
                               obtenerItems();
                               limpiar();
                            },
            error       :   function(jqXHR,textStatus,errorThrown){
                            console.log( xhr);
                            alert("No se guardo correctamente");
                               
                            }


        }
    );
    }


}

function borrar(idElemento) {
    let elemento;
    elemento = { 
        id:idElemento
    };
    let dataToSend   = JSON.stringify(elemento);
    console.log(dataToSend);
    $.ajax (
        {
            url:"http://129.151.125.142:8080/api/Message/"+idElemento,
            type:"DELETE",
            data:dataToSend,
            contentType:"application/JSON",
            datatype:"JSON",
            success      :  function(response){
                                console.log(response);
                                alert("Se mensaje se ha borrado Correctamente!")
                                obtenerItems();
                                limpiar();

                            },
            error       :   function(jqXHR,textStatus,errorThrown){
                                console.log(xhr);

                            }
        }
    );
}

function actualizar(idElemento) {
  
    var name= $("#idMessageText").val();
    var disfraz =$("#select-costume").val();
    var cliente= $("#select-client").val();
  
    if(name.length==0 || disfraz.length==0 || cliente.length==0){
        alert('Error, debe completar todos los campos');
        $("#idMessageText").focus();
        return false;
    }
    else{
    let elemento;
    elemento = { 
       idMessage: idElemento,
        messageText:$("#idMessageText").val(),
        client: {idClient:cliente},
        costume:{id:disfraz}
    };
         
        $.ajax (
            {
                url:"http://129.151.125.142:8080/api/Message/update",
                type:"PUT",
                data:JSON.stringify(elemento),
                contentType:"application/JSON",
                datatype:"JSON",
                success      :  function(response){
                                
                                console.log(response);
                                alert("El registro del mensaje fue modificado satisfactoriamente");
                                obtenerItems();
                                limpiar();
                                },
                error       :   function(jqXHR,textStatus,errorThrown){
                                console.log( xhr);

                                }


            }
        );

    }
}
                                                 
