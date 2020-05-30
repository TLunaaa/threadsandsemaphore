package viejo.luna.threadsandsem.model;

public class TicketHandler {
    private Conector connector;
    private Ticket ticket;
    private boolean ended = false;

    public TicketHandler(Conector conector){
        this.connector = conector;
    }

    public void run(){
        int id_ticket = connector.selectTicketLibre();
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
