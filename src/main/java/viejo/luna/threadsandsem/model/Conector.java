package viejo.luna.threadsandsem.model;
//import com.sun.org.apache.xpath.internal.operations.Bool;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Conector {
    public Conector(){

    }
    public void conectar() {
        Connection connection = null;
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
        String url = "jdbc:sqlite:C:\\Users\\Nacho\\Desktop\\Engineering\\Software Libre\\TP hilos\\Repositorio\\tpdb.db"; //ACA VA LA URL DONDE ESTA LA BASE DE DATOS, DEPENDE DE LA PC
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
        String url = "jdbc:sqlite:C:\\Users\\Nacho\\Desktop\\Engineering\\Software Libre\\TP hilos\\Repositorio\\tpdb.db"; //ACA VA LA URL DONDE ESTA LA BASE DE DATOS, DEPENDE DE LA PC

        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS tickets (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	estado text NOT NULL\n"
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
        String url = "jdbc:sqlite:C:\\Users\\Nacho\\Desktop\\Engineering\\Software Libre\\TP hilos\\Repositorio\\tpdb.db"; //ACA VA LA URL DONDE ESTA LA BASE DE DATOS, DEPENDE DE LA PC
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    public void insert(String estado) {
        String sql = "INSERT INTO tickets(estado) VALUES(?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, estado);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void selectAll(){
        String sql = "SELECT id, estado FROM tickets";

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("id") +  "\t" +
                        rs.getString("estado"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public int selectTicketLibre(){ //devuelve el id del ticket o 0 si no hay
        String sql = "SELECT id, estado FROM tickets";
        int res = 0;
        Boolean band = true;

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            // loop through the result set
            while ((rs.next())&&(band)) {
                if(rs.getString("estado").equals("Libre")) {
                    res = rs.getInt("id");
                    update(res,"Reservado");
                    band = false;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return res;
    }

    public int selectTicketReservado(){ //devuelve el id del ticket o 0 si no hay
        String sql = "SELECT id, estado FROM tickets";
        int res = 0;
        Boolean band = true;

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            // loop through the result set
            while ((rs.next())&&(band)) {
                if(rs.getString("estado").equals("Reservado")) {
                    res = rs.getInt("id");
                    update(res,"Reservado");
                    band = false;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return res;
    }

    public void update(int id, String estado) {
        String sql = "UPDATE tickets SET estado = ? "
                + "WHERE id = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setString(1, estado);
            pstmt.setInt(2, id);
            // update
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
