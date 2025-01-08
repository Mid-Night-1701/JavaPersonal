import java.util.Arrays;
import java.util.Scanner;

public class B1c2 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        System.out.print("Scan a, b, c, d: ");
        int[] A = new int[4];
        for (int i = 0; i < 4; i++) {
            A[i] = scn.nextInt();
        }
        Arrays.sort(A);                
        System.out.println("Max1: " + A[3]);
        System.out.println("Max2: " + A[2]);
        scn.close();
    }
}
