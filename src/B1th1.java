import java.util.Scanner;

public class B1th1 {
    public static void main(String[] args) {
        try (Scanner scn = new Scanner(System.in)) {
            System.out.print("Enter n: ");
            int number = scn.nextInt();
            int product = 1;

            while (number != 0) {
                int digit = number % 10;
                product *= digit;
                number /= 10;
            }

            System.out.println("Tích:  " + product);
        }
    }
}