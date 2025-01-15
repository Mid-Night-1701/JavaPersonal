import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Sucvat {
    public static void main(String[] args) {
        ArrayList<SinhVien> list = new ArrayList<>();
        try (Scanner scn = new Scanner(System.in)) {
            System.out.print("Enter number of students: ");
            int n = scn.nextInt();
            scn.nextLine();

            for (int i = 0; i < n; i++) {
                SinhVien sv = new SinhVien();
                System.out.println("Student " + (i + 1) + ":");
                System.out.print("ID: ");
                sv.maSV = scn.nextLine();
                System.out.print("Name: ");
                sv.tenSV = scn.nextLine();
                System.out.print("Score: ");
                sv.diemTichLuy = scn.nextFloat();
                scn.nextLine();
                list.add(sv);
            }
        }

        for (SinhVien x : list) {
            System.out.println(x.maSV + " - " + x.tenSV + " - " + x.diemTichLuy);
        }

        Collections.sort(list, (SinhVien o1, SinhVien o2) -> {
            if (o1.diemTichLuy == o2.diemTichLuy) {
                int result = o1.getTen().compareTo(o2.getTen());
                if(result==0)
                    return 0;
                else if(result<0)
                    return -1;
                else
                    return 1;
            } else if (o1.diemTichLuy > o2.diemTichLuy) {
                return -1;
            } else {
                return 1;
            }
        });

        System.out.println("Comparated List: ");
        for (SinhVien x : list) {
            System.out.println(x.maSV + " - " + x.tenSV + " - " + x.diemTichLuy);
        }
    }
}