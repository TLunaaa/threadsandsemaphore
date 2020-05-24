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


        for(int i=1;i<51;i++){
            conector.insert("Libre");
        }
        conector.selectAll();
        conector.update(1,"Reservado");
        System.out.println(conector.selectTicketLibre());
        System.out.println(conector.selectTicketReservado());

    }

}
