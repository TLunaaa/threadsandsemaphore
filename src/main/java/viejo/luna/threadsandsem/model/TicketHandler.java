package viejo.luna.threadsandsem.model;
import org.json.JSONObject;

import java.io.*;
import java.net.Socket;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TicketHandler extends Thread {
    private Conector connector;
    private Ticket ticket;
    final Socket insocket;

    public TicketHandler(Conector conector, Socket s){
        this.connector = conector;
        this.insocket = s;
    }

    @Override
    public void run(){
        try{
            PrintWriter out = new PrintWriter(insocket.getOutputStream());
            checkURL(out);
            out.close();
            insocket.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void checkURL(PrintWriter out) throws IOException {
        InputStream is = insocket.getInputStream();
        BufferedReader in = new BufferedReader(new InputStreamReader(is));
        String request_method = in.readLine();
        JSONObject json_encode = new JSONObject();
        try{
            if(request_method.contains("/reservar")){
                json_encode.put("id",reservarTicket());
                out.println("HTTP/1.0 200 OK");
                out.println("Content-Type: application/json; charset=utf-8");
                out.println("");
                out.println(json_encode);
            }else{
                if(request_method.contains("/comprar")){
                    String[] query = request_method.split("[ ]");
                    Map<String, Object> parameters = new HashMap<String, Object>();
                    parseQuery(query[1].split("[?]")[1],parameters);
                    int idTicket = Integer.valueOf((String)parameters.get("id"));
                    System.out.println(idTicket);
                    comprarTicket(idTicket);
                    out.println("HTTP/1.0 200 OK");
                    out.println("Content-Type: text/plain; charset=utf-8");
                    out.println("");
                    out.println("Comprado");
                }
            }
        }catch (Error e){
            out.println("HTTP/1.0 401 Unauthorized");
            out.println("Content-Type: application/json; charset=utf-8");
            out.println("");
        }

    }

    private int reservarTicket(){
        int id = connector.selectTicketLibre();
        if(id != 0){
            connector.update(id,"Reservado");
            return id;
        }else{
            throw new Error("NoMoreTickets");
        }
    }

    private void comprarTicket(int idTicket){
        if(connector.selectTicketReservado(idTicket) != 0){
            connector.update(idTicket,"Comprado");
        }else{
            throw new Error("NoMoreTickets");
        }
    }


    public static void parseQuery(String query, Map<String, Object> parameters) throws UnsupportedEncodingException {
        if (query != null) {
            String[] pairs = query.split("[&]");
            for (String pair : pairs) {
                String[] param = pair.split("[=]");
                String key = null;
                String value = null;
                if (param.length > 0) {
                    key = URLDecoder.decode(param[0], System.getProperty("file.encoding"));
                }
                if (param.length > 1) {
                    value = URLDecoder.decode(param[1], System.getProperty("file.encoding"));
                }
                if (parameters.containsKey(key)) {
                    Object obj = parameters.get(key);
                    if (obj instanceof List<?>) {
                        List values = (List) obj;
                        values.add(value);

                    } else if (obj instanceof String) {
                        List values = new ArrayList();
                        values.add((String) obj);
                        values.add(value);
                        parameters.put(key, values);
                    }
                } else {
                    parameters.put(key, value);
                }
            }
        }
    }

}
