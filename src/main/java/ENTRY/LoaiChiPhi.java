package ENTRY;

public class LoaiChiPhi {
    int cp_id;
    String cp_ten;
    String cp_mota;

    public LoaiChiPhi() {
    }

    public LoaiChiPhi(int cp_id, String cp_ten, String cp_mota) {
        this.cp_id = cp_id;
        this.cp_ten = cp_ten;
        this.cp_mota = cp_mota;
    }

    public int getCp_id() {
        return cp_id;
    }

    public void setCp_id(int cp_id) {
        this.cp_id = cp_id;
    }

    public String getCp_ten() {
        return cp_ten;
    }

    public void setCp_ten(String cp_ten) {
        this.cp_ten = cp_ten;
    }

    public String getCp_mota() {
        return cp_mota;
    }

    public void setCp_mota(String cp_mota) {
        this.cp_mota = cp_mota;
    }
}
