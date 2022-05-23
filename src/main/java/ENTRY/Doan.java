package ENTRY;

public class Doan {
    int doan_id;
    int tour_id;
    String doan_name;
    String doan_ngaydi;
    String doan_ngayve;
    int gia_id;
    String doan_chitietchuongtrinh;

    public Doan(){

    }

    public Doan(int doan_id, int tour_id, String doan_name, String doan_ngaydi, String doan_ngayve, int gia_id, String doan_chitietchuongtrinh) {
        this.doan_id = doan_id;
        this.tour_id = tour_id;
        this.doan_name = doan_name;
        this.doan_ngaydi = doan_ngaydi;
        this.doan_ngayve = doan_ngayve;
        this.gia_id = gia_id;
        this.doan_chitietchuongtrinh = doan_chitietchuongtrinh;
    }

    public int getDoan_id() {
        return doan_id;
    }

    public void setDoan_id(int doan_id) {
        this.doan_id = doan_id;
    }

    public int getTour_id() {
        return tour_id;
    }

    public void setTour_id(int tour_id) {
        this.tour_id = tour_id;
    }

    public String getDoan_name() {
        return doan_name;
    }

    public void setDoan_name(String doan_name) {
        this.doan_name = doan_name;
    }

    public String getDoan_ngaydi() {
        return doan_ngaydi;
    }

    public void setDoan_ngaydi(String doan_ngaydi) {
        this.doan_ngaydi = doan_ngaydi;
    }

    public String getDoan_ngayve() {
        return doan_ngayve;
    }

    public void setDoan_ngayve(String doan_ngayve) {
        this.doan_ngayve = doan_ngayve;
    }

    public int getGia_id() {
        return gia_id;
    }

    public void setGia_id(int gia_id) {
        this.gia_id = gia_id;
    }

    public String getDoan_chitietchuongtrinh() {
        return doan_chitietchuongtrinh;
    }

    public void setDoan_chitietchuongtrinh(String doan_chitietchuongtrinh) {
        this.doan_chitietchuongtrinh = doan_chitietchuongtrinh;
    }
}
