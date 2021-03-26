
import com.sun.net.httpserver.HttpServer;
import service.CalcService;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class WebcalcStarter {

    public static int PORT = 8005;

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(PORT), 0);
        server.createContext("/", new CalcService());
        server.setExecutor(Executors.newFixedThreadPool(10));
        System.out.println("Server listening at: http://127.0.0.1:" + PORT);
        server.start();
    }
}