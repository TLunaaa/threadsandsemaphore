package viejo.luna.threadsandsem.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import viejo.luna.threadsandsem.model.TicketHandler;

@RestController
public class PaymentController {

    @GetMapping(value= "/compra", produces = MediaType.APPLICATION_JSON_VALUE)
    public Integer greeting() {
        TicketHandler tickethandler = new TicketHandler();
        tickethandler.start();
        while(tickethandler.isEnded() == false){};
        if(tickethandler.getTicket() != null){
            return tickethandler.getTicket().getId();
        }
        return null;
    }





}