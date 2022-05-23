package ENTRY;

public class DiaDiem extends ChiTiet{
    int dd_id;
    String dd_thanhpho;
    String dd_ten;
    String dd_mota;

    public DiaDiem() {
    }

    public DiaDiem(int dd_id, String dd_thanhpho, String dd_ten, String dd_mota) {
        this.dd_id = dd_id;
        this.dd_thanhpho = dd_thanhpho;
        this.dd_ten = dd_ten;
        this.dd_mota = dd_mota;
    }

    public int getDd_id() {
        return dd_id;
    }

    public void setDd_id(int dd_id) {
        this.dd_id = dd_id;
    }

    public String getDd_thanhpho() {
        return dd_thanhpho;
    }

    public void setDd_thanhpho(String dd_thanhpho) {
        this.dd_thanhpho = dd_thanhpho;
    }

    public String getDd_ten() {
        return dd_ten;
    }

    public void setDd_ten(String dd_ten) {
        this.dd_ten = dd_ten;
    }

    public String getDd_mota() {
        return dd_mota;
    }

    public void setDd_mota(String dd_mota) {
        this.dd_mota = dd_mota;
    }
}
