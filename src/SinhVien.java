public class SinhVien {
    String maSV;
    String tenSV;
    float diemTichLuy;

    public String getTen() {
        if (tenSV != null && tenSV.contains(" ")) {
            String[] parts = tenSV.split(" ");
            return parts[parts.length - 1];
        }
        return tenSV;
    }
}
