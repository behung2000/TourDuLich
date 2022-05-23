package ENTRY;

public class NguoiDi {
    int nguoidi_id;
    int doan_id;
    String nguoidi_dsnhanvien;
    String nguoidi_dskhachhang;

    public NguoiDi(){

    }

    public NguoiDi(int nguoidi_id, int doan_id, String nguoidi_dsnhanvien, String nguoidi_dskhachhang) {
        this.nguoidi_id = nguoidi_id;
        this.doan_id = doan_id;
        this.nguoidi_dsnhanvien = nguoidi_dsnhanvien;
        this.nguoidi_dskhachhang = nguoidi_dskhachhang;
    }

    public int getNguoidi_id() {
        return nguoidi_id;
    }

    public void setNguoidi_id(int nguoidi_id) {
        this.nguoidi_id = nguoidi_id;
    }

    public int getDoan_id() {
        return doan_id;
    }

    public void setDoan_id(int doan_id) {
        this.doan_id = doan_id;
    }

    public String getNguoidi_dsnhanvien() {
        return nguoidi_dsnhanvien;
    }

    public void setNguoidi_dsnhanvien(String nguoidi_dsnhanvien) {
        this.nguoidi_dsnhanvien = nguoidi_dsnhanvien;
    }

    public String getNguoidi_dskhachhang() {
        return nguoidi_dskhachhang;
    }

    public void setNguoidi_dskhachhang(String nguoidi_dskhachhang) {
        this.nguoidi_dskhachhang = nguoidi_dskhachhang;
    }
}
