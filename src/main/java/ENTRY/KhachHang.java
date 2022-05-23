package ENTRY;

public class KhachHang {
    int kh_id;
    String kh_ten;
    String kh_sdt;
    String kh_ngaysinh;
    String kh_email;
    String kh_cmnd;

    public KhachHang(){

    }

    public KhachHang(int kh_id, String kh_ten, String kh_sdt, String kh_ngaysinh, String kh_email, String kh_cmnd) {
        this.kh_id = kh_id;
        this.kh_ten = kh_ten;
        this.kh_sdt = kh_sdt;
        this.kh_ngaysinh = kh_ngaysinh;
        this.kh_email = kh_email;
        this.kh_cmnd = kh_cmnd;
    }

    public int getKh_id() {
        return kh_id;
    }

    public void setKh_id(int kh_id) {
        this.kh_id = kh_id;
    }

    public String getKh_ten() {
        return kh_ten;
    }

    public void setKh_ten(String kh_ten) {
        this.kh_ten = kh_ten;
    }

    public String getKh_sdt() {
        return kh_sdt;
    }

    public void setKh_sdt(String kh_sdt) {
        this.kh_sdt = kh_sdt;
    }

    public String getKh_ngaysinh() {
        return kh_ngaysinh;
    }

    public void setKh_ngaysinh(String kh_ngaysinh) {
        this.kh_ngaysinh = kh_ngaysinh;
    }

    public String getKh_email() {
        return kh_email;
    }

    public void setKh_email(String kh_email) {
        this.kh_email = kh_email;
    }

    public String getKh_cmnd() {
        return kh_cmnd;
    }

    public void setKh_cmnd(String kh_cmnd) {
        this.kh_cmnd = kh_cmnd;
    }
}
