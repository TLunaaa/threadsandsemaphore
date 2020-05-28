package viejo.luna.threadsandsem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import viejo.luna.threadsandsem.model.Conector;
import viejo.luna.threadsandsem.model.TicketHandler;

@RestController
public class PaymentController {
    private final Conector conector;

    @Autowired
    public PaymentController(Conector conector){
        this.conector = conector;
    }

    @GetMapping(value= "/reserva", produces = MediaType.APPLICATION_JSON_VALUE)
    public Integer booking() {
        TicketHandler tickethandler = new TicketHandler(conector);
        tickethandler.start();
        while(!tickethandler.isEnded()){
            System.out.println("Not finished");
        };
        if(tickethandler.getTicket() != null){
            return tickethandler.getTicket().getId();
        }
        return null;
    }
    //TODO
    /*@GetMapping(value= "/pago", produces = MediaType.APPLICATION_JSON_VALUE)
    public Integer payment() {
        TicketHandler tickethandler = new TicketHandler();
        tickethandler.start();
        while(tickethandler.isEnded() == false){};
        if(tickethandler.getTicket() != null){
            return tickethandler.getTicket().getId();
        }
        return null;
    }
    */



}