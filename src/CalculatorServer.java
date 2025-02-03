import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class CalculatorServer {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/", new RootHandler());
        server.createContext("/calculate", new CalculatorHandler());
        server.setExecutor(null); // default executor
        server.start();
        System.out.println("Server started on port 8000");
    }

    static class RootHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("GET".equals(exchange.getRequestMethod())) {
                String response = new String(Files.readAllBytes(Paths.get("web/index.html")));
                exchange.sendResponseHeaders(200, response.length());
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
        }
    }

    static class CalculatorHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("POST".equals(exchange.getRequestMethod())) {
                Scanner scanner = new Scanner(exchange.getRequestBody()).useDelimiter("\\A");
                String requestBody = scanner.hasNext() ? scanner.next() : "";
                String[] parts = requestBody.split("&");
                double result = 0;
                try {
                    double num1 = Double.parseDouble(parts[0].split("=")[1]);
                    String operator = parts[1].split("=")[1];
                    double num2 = Double.parseDouble(parts[2].split("=")[1]);
                    switch (operator) {
                        case "add":
                            result = num1 + num2;
                            break;
                        case "subtract":
                            result = num1 - num2;
                            break;
                        case "multiply":
                            result = num1 * num2;
                            break;
                        case "divide":
                            result = num1 / num2;
                            break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                String response = String.valueOf(result);
                exchange.sendResponseHeaders(200, response.length());
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
        }
    }
}
