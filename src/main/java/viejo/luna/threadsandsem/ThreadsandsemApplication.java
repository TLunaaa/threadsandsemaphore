package viejo.luna.threadsandsem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import viejo.luna.threadsandsem.model.Ticket;

@SpringBootApplication
public class ThreadsandsemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThreadsandsemApplication.class, args);
        Ticket ticket1 = new Ticket();
        Ticket ticket2 = new Ticket();
        Ticket ticket3 = new Ticket();
        Ticket ticket4 = new Ticket();
        Ticket ticket5 = new Ticket();
    }

}
