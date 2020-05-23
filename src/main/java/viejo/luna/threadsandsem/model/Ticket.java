package viejo.luna.threadsandsem.model;

public class Ticket {

    private int id;
    private String state;

    public Ticket(){
        this.state="Libre";
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
