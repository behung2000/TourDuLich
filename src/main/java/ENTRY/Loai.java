package ENTRY;

public class Loai {
    int loai_id;
    String loai_ten;
    String loai_mota;

    public Loai() {}

    public Loai(int loai_id, String loai_ten, String loai_mota) {
        this.loai_id = loai_id;
        this.loai_ten = loai_ten;
        this.loai_mota = loai_mota;
    }

    public int getLoai_id() {
        return loai_id;
    }

    public void setLoai_id(int loai_id) {
        this.loai_id = loai_id;
    }

    public String getLoai_ten() {
        return loai_ten;
    }

    public void setLoai_ten(String loai_ten) {
        this.loai_ten = loai_ten;
    }

    public String getLoai_mota() {
        return loai_mota;
    }

    public void setLoai_mota(String loai_mota) {
        this.loai_mota = loai_mota;
    }
}
