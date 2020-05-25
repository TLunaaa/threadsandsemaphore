package viejo.luna.threadsandsem.model;

public class Ticket {

    private static int count = 0;
    private int id;
    private String state;

    public Ticket(){
        this.id = ++count;
        this.state="LIBRE";
    }

    public Ticket(int id){
        this.state="RESERVADO";
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

}
