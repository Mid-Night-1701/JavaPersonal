import java.util.Scanner;

public class B1th2 {
    public static void main(String[] args) {
        long n;
        long a, b, f;
        try (Scanner scn = new Scanner(System.in)) {
            System.out.print("Enter N: ");
            n = scn.nextInt();
            a = 1;
            b = 1;
            f = 0;
        }

        if (n <= 0) {
            System.out.println("n must be greater than 0");
        } else if(n==1 || n==2) {
            System.out.println("1");
        } else {
            for (int i = 2; i < n; i++) {
                f = a + b;
                 a = b;
                b = f;
            }
            System.out.println(f);
        }
    }
}
