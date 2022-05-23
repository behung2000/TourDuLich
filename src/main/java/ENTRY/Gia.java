package ENTRY;

public class Gia {
    int gia_id;
    double gia_sotien;
    int tour_id;
    String gia_tungay;
    String ghichu;

    public Gia() {
    }

    public Gia(int gia_id, double gia_sotien, int tour_id, String gia_tungay, String ghichu) {
        this.gia_id = gia_id;
        this.gia_sotien = gia_sotien;
        this.tour_id = tour_id;
        this.gia_tungay = gia_tungay;
        this.ghichu = ghichu;
    }

    public int getGia_id() {
        return gia_id;
    }

    public void setGia_id(int gia_id) {
        this.gia_id = gia_id;
    }

    public double getGia_sotien() {
        return gia_sotien;
    }

    public void setGia_sotien(double gia_sotien) {
        this.gia_sotien = gia_sotien;
    }

    public int getTour_id() {
        return tour_id;
    }

    public void setTour_id(int tour_id) {
        this.tour_id = tour_id;
    }

    public String getGia_tungay() {
        return gia_tungay;
    }

    public void setGia_tungay(String gia_tungay) {
        this.gia_tungay = gia_tungay;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }
}
