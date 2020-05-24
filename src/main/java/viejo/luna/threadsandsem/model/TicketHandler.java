package viejo.luna.threadsandsem.model;

public class TicketHandler extends Thread {
    private final Conector BD = new Conector();
    private Ticket ticket;
    private boolean ended = false;

    public TicketHandler(){
    }

    public void run(){
        //Integer id_ticket = consulta a la base de datos y devuelve el id
        int id_ticket = 0;
        setTicket(id_ticket);
        this.ended = true;
    }

    public Ticket getTicket(){
        return this.ticket;
    }

    private void setTicket(int id){
        this.ticket = new Ticket(id);
    }

    public boolean isEnded() {
        return ended;
    }
}
