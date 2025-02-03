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
        server.setExecutor(null);
        System.out.println("Server started on port 8000");
    }

    static class RootHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("GET".equals(exchange.getRequestMethod())) {
                String response = new String(Files.readAllBytes(Paths.get("../web/index.html")));
                exchange.sendResponseHeaders(200, response.length());
                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(response.getBytes());
                }
            } else {
                exchange.sendResponseHeaders(405, -1);
            }
        }
    }

    static class CalculatorHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("POST".equals(exchange.getRequestMethod())) {
                String requestBody;
                try (Scanner scanner = new Scanner(exchange.getRequestBody()).useDelimiter("\\A")) {
                    requestBody = scanner.hasNext() ? scanner.next() : "";
                }
                String[] parts = requestBody.split("&");
                double result = 0;
                boolean validRequest = true;
                try {
                    double num1 = Double.parseDouble(parts[0].split("=")[1]);
                    String operator = parts[1].split("=")[1];
                    double num2 = Double.parseDouble(parts[2].split("=")[1]);
                    result = switch (operator) {
                        case "add" -> num1 + num2;
                        case "subtract" -> num1 - num2;
                        case "multiply" -> num1 * num2;
                        case "divide" -> {
                            if (num2 != 0) {
                                yield num1 / num2;
                            } else {
                                validRequest = false;
                                yield 0;
                            }
                        }
                        default -> {
                            validRequest = false;
                            yield 0;
                        }
                    };
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    validRequest = false;
                }
                String response = validRequest ? String.valueOf(result) : "Invalid request";
                exchange.sendResponseHeaders(validRequest ? 200 : 400, response.length());
                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(response.getBytes());
                }
            } else {
                exchange.sendResponseHeaders(405, -1);
            }
        }
    }
}
