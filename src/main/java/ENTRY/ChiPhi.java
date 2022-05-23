package ENTRY;

public class ChiPhi {
    int chiphi_id;
    int doan_id;
    double chiphi_total;
    String chiphi_chitiet;

    public ChiPhi() {
    }

    public ChiPhi(int chiphi_id, int doan_id, double chiphi_total, String chiphi_chitiet) {
        this.chiphi_id = chiphi_id;
        this.doan_id = doan_id;
        this.chiphi_total = chiphi_total;
        this.chiphi_chitiet = chiphi_chitiet;
    }

    public int getChiphi_id() {
        return chiphi_id;
    }

    public void setChiphi_id(int chiphi_id) {
        this.chiphi_id = chiphi_id;
    }

    public int getDoan_id() {
        return doan_id;
    }

    public void setDoan_id(int doan_id) {
        this.doan_id = doan_id;
    }

    public double getChiphi_total() {
        return chiphi_total;
    }

    public void setChiphi_total(double chiphi_total) {
        this.chiphi_total = chiphi_total;
    }

    public String getChiphi_chitiet() {
        return chiphi_chitiet;
    }

    public void setChiphi_chitiet(String chiphi_chitiet) {
        this.chiphi_chitiet = chiphi_chitiet;
    }
}
