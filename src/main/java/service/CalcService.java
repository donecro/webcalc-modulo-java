package service;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class CalcService implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) {
        try {
            String param = exchange.getRequestURI().getQuery();
            int x = Integer.parseInt(param.substring(param.indexOf("x=") + 2, param.indexOf("&")));
            int y = Integer.parseInt(param.substring(param.indexOf("y=") + 2));
            int answer = Math.floorMod(x, y);
            String str = x + "mod" + y + "=" + answer;
            String result = "{\"error\":false,\"string\":\"" + str + "\",\"answer\":" + answer + "}";
            byte[] bytes = result.getBytes(StandardCharsets.UTF_8);
            exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            exchange.getResponseHeaders().add("Content-Type", "application/json;charset=utf-8");
            exchange.sendResponseHeaders(200, bytes.length);
            OutputStream out = exchange.getResponseBody();
            out.write(bytes);
            out.flush();
            out.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

