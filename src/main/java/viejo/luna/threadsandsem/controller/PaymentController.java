package viejo.luna.threadsandsem.controller;

import org.springframework.web.bind.annotation.*;
import viejo.luna.threadsandsem.model.Ticket;
import viejo.luna.threadsandsem.model.TicketHandler;

@RestController
public class PaymentController {

    @GetMapping("/compra")
    public int greeting() {
        return new TicketHandler().start();
    }





}