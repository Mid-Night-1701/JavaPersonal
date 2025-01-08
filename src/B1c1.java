import java.util.Scanner;

public class B1c1 {
    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        System.out.print("Second: ");
        int S = scn.nextInt();
        
        int hr = S / 3600;
        int mt = (S % 3600) / 60;
        int sc = S % 60;
        
        System.out.printf("%02d:%02d:%02d\n", hr, mt, sc);
        scn.close();
    }
}
