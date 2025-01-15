import java.util.Scanner;

public class Prime {
    static int[] A;
    static int n;

    public static void isPrime(int k) {
        for (int t = 2; k * t <= n; t++) {
            A[k * t] = -1;
        }
    }

    public static void main(String[] args) throws Exception {
        try (Scanner scn = new Scanner(System.in)) {
            System.out.print("Enter n: ");
            n = scn.nextInt();
        }
        if (n < 2) {
            System.err.println("\n NOTFOUND! \n11");
        } else {
            A = new int[n + 1];
            A[0] = 0;
            A[1] = 0;
            for (int j = 2; j <= n; j++) {
                if (A[j] != -1) {
                    System.out.print(j + " ");
                    isPrime(j);
                }
            }
        }
    }
}
