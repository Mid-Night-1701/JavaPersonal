import java.util.Scanner;

public class BatPhan {

    public static void main(String[] args) {
        int n;
        try (Scanner scn = new Scanner(System.in)) {
            System.out.print("Enter n: ");
            n = scn.nextInt();
        }

        String octal = "";

        if (n == 0) {
            octal = "0";
        } else {
            while (n > 0) {
                int k = n % 8;
                octal = k + octal;
                n = n / 8;
            }
        }

        System.out.println("Octal n: " + octal);
    }
}
