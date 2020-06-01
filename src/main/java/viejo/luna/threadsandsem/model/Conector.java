package viejo.luna.threadsandsem.model;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Conector {
    private static Conector obj ;

    private Conector(){
        File tmpDir = new File("./tpdb.db");
        if(!tmpDir.exists()){
            this.nuevaDB(); //solo la primera vez xd
            this.crearNuevaTabla(); //solo la primera vez xd
            for(int i=1;i<51;i++){
                this.insert(i,"Libre");
            }
        }
        //System.out.println(this.selectTicketLibre());
    }

    public static Conector getInstance()
    {
        if (obj==null)
            obj = new Conector();
        return obj;
    }

    private Connection conectar() {
        Connection connection = null;
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:tpdb.db");
        }
        catch(SQLException | ClassNotFoundException e){
            System.out.println(e.getClass().getName()+ ": " + e.getMessage());
            System.out.println("Error en la conexion");
        }
        return connection;
    }

    public void nuevaDB(){
        try (Connection conn = this.conectar()) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
                conn.close();
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void crearNuevaTabla() {
        // SQLite connection string
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS tickets (\n"
                + "	id int PRIMARY KEY,\n"
                + "	estado text NOT NULL\n"
                + ");";

        try (Connection conn = this.conectar();
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insert(int id,String estado) {
        String sql = "INSERT INTO tickets(id,estado) VALUES(?,?)";
        try (Connection conn = this.conectar();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(2, estado);
            pstmt.setInt(1,id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
        }
    }
    public void selectAll(){
        String sql = "SELECT id, estado FROM tickets";

        try (Connection conn = this.conectar();
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

        try (Connection conn = this.conectar();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            // loop through the result set
            while ((rs.next())&&(band)) {
                if(rs.getString("estado").equals("Libre")) {
                    res = rs.getInt("id");
                    band = false;
                }
            }
        } catch (SQLException e) {
            System.out.println("sadasd " + e.getMessage());
        }
        return res;
    }

    public int selectTicketReservado(int idTicket){ //devuelve el id del ticket o 0 si no hay
        String sql = "SELECT id, estado FROM tickets";
        int res = 0;
        Boolean band = true;

        try (Connection conn = this.conectar();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            // loop through the result set
            while ((rs.next())&&(band)) {
                if(rs.getString("estado").equals("Reservado")) {
                    res = rs.getInt("id");
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

        try (Connection conn = this.conectar();
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
