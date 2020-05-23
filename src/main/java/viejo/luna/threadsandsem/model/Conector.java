package viejo.luna.threadsandsem.model;

import org.springframework.boot.autoconfigure.amqp.RabbitProperties;

public class Conector {
    public Conector(){

    }
    public void conectar() {
        RabbitProperties.Cache.Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:data.sqlite");
            if (connection != null) {
                System.out.println("Conexion exitosa");
            }
        }
        catch(Exception e){
            System.err.println(e.getClass().getName()+ ": " + e.getMessage());
            System.out.println("Error en la conexion");
        }
    }
    public void nuevaDB(){
        //String url = "jdbc:sqlite:C:\\Users\\Nacho\\Desktop\\Engineering\\Software Libre\\TP hilos\\Repositorio\\tpdb.db"; //ACA VA LA URL DONDE ESTA LA BASE DE DATOS, DEPENDE DE LA PC
        String url = "jdbc:sqlite:C:.\\tpdb.db";
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void crearNuevaTabla() {
        // SQLite connection string
        //String url = "jdbc:sqlite:C:\\Users\\Nacho\\Desktop\\Engineering\\Software Libre\\TP hilos\\Repositorio\\tpdb.db"; //ACA VA LA URL DONDE ESTA LA BASE DE DATOS, DEPENDE DE LA PC
        String url = "jdbc:sqlite:C:.\\tpdb.db";
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS tickets (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	Ticket ticket NOT NULL\n"
                + ");";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    private Connection connect() {
        // SQLite connection string
        //String url = "jdbc:sqlite:C:\\Users\\Nacho\\Desktop\\Engineering\\Software Libre\\TP hilos\\Repositorio\\tpdb.db"; //ACA VA LA URL DONDE ESTA LA BASE DE DATOS, DEPENDE DE LA PC
        String url = "jdbc:sqlite:C:.\\tpdb.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    public void insert(Ticket ticket) {
        String sql = "INSERT INTO tickets(ticket) VALUES(?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setObject(1, ticket);
                pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void selectAll(){
        String sql = "SELECT id, ticket FROM tickets";

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("id") +  "\t" +
                        rs.getString("ticket"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
