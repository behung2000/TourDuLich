package ENTRY;

public class NhanVien {
    int nv_id;
    String nv_ten;
    String nv_sdt;
    String nv_ngaysinh;
    String nv_email;
    String nv_nhiemvu;

    public NhanVien(){}

    public NhanVien(int nv_id, String nv_ten, String nv_sdt, String nv_ngaysinh, String nv_email, String nv_nhiemvu) {
        this.nv_id = nv_id;
        this.nv_ten = nv_ten;
        this.nv_sdt = nv_sdt;
        this.nv_ngaysinh = nv_ngaysinh;
        this.nv_email = nv_email;
        this.nv_nhiemvu = nv_nhiemvu;
    }

    public int getNv_id() {
        return nv_id;
    }

    public void setNv_id(int nv_id) {
        this.nv_id = nv_id;
    }

    public String getNv_ten() {
        return nv_ten;
    }

    public void setNv_ten(String nv_ten) {
        this.nv_ten = nv_ten;
    }

    public String getNv_sdt() {
        return nv_sdt;
    }

    public void setNv_sdt(String nv_sdt) {
        this.nv_sdt = nv_sdt;
    }

    public String getNv_ngaysinh() {
        return nv_ngaysinh;
    }

    public void setNv_ngaysinh(String nv_ngaysinh) {
        this.nv_ngaysinh = nv_ngaysinh;
    }

    public String getNv_email() {
        return nv_email;
    }

    public void setNv_email(String nv_email) {
        this.nv_email = nv_email;
    }

    public String getNv_nhiemvu() {
        return nv_nhiemvu;
    }

    public void setNv_nhiemvu(String nv_nhiemvu) {
        this.nv_nhiemvu = nv_nhiemvu;
    }
}
