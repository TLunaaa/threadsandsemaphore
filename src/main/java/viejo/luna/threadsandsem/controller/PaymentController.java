package viejo.luna.threadsandsem.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class PaymentController {
    private final String sharedKey = "SHARED_KEY";
    private static final String SUCCESS_STATUS = "success";
    private static final String ERROR_STATUS = "error";
    private static final int CODE_SUCCESS = 100;
    private static final int AUTH_FAILURE = 102;

    @GetMapping("/compra")
    public String greeting() {
        return "Hello World";
    }



}