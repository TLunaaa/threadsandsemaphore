package viejo.luna.threadsandsem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import viejo.luna.threadsandsem.model.Conector;
import viejo.luna.threadsandsem.model.Ticket;

@SpringBootApplication
public class ThreadsandsemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThreadsandsemApplication.class, args);
        Conector conector = new Conector();
        conector.conectar();
        conector.nuevaDB(); //solo la primera vez xd
        conector.crearNuevaTabla(); //solo la primera vez xd

        Ticket ticket1 = new Ticket();
        Ticket ticket2 = new Ticket();
        Ticket ticket3 = new Ticket();
        Ticket ticket4 = new Ticket();
        Ticket ticket5 = new Ticket();
        Ticket ticket6 = new Ticket();
        Ticket ticket7 = new Ticket();
        Ticket ticket8 = new Ticket();
        Ticket ticket9 = new Ticket();
        Ticket ticket10 = new Ticket();
        conector.insert(ticket1);
        conector.insert(ticket2);
        conector.insert(ticket3);
        conector.insert(ticket4);
        conector.insert(ticket5);
        conector.insert(ticket6);
        conector.insert(ticket7);
        conector.insert(ticket8);
        conector.insert(ticket9);
        conector.insert(ticket10);
        conector.selectAll();
    }

}
