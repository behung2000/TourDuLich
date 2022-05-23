package BLL;

import DAL.DALNguoiDi;
import ENTRY.NguoiDi;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Vector;

public class BLLNguoiDi {
    DALNguoiDi dalNguoiDi;
    CheckInput checkInput;
    BLLKhachHang bllKhachHang;
    BLLNhanVien bllNhanVien;
    public DefaultTableModel modeltable=null;
    public Vector header=null;

    public BLLNguoiDi(){
        dalNguoiDi = new DALNguoiDi();
    }

    //get list NguoiDi
    public ArrayList<NguoiDi> getALLList(){
        String where = "doan_id like '%%'";
        String order = "nguoidi_id ASC";
        return dalNguoiDi.getList(where, order);
    }

    //get 1 NguoiDi with doan_id
    public NguoiDi get1NguoiDi(int doan_id){
        String where = "doan_id="+doan_id;
        String order = "nguoidi_id ASC";
        ArrayList<NguoiDi> list = new ArrayList<NguoiDi>();
        list = dalNguoiDi.getList(where,order);
        NguoiDi nguoidi = new NguoiDi();
        if(list.size()==1){
            nguoidi = list.get(0);
        }
        else
        {
            System.out.println("ERROR -> BLLNguoiDi -> get1NguoiDi !!!!");
        }
        return nguoidi;
    }

    //get N nguoidi with nv_id
    public ArrayList<NguoiDi> getALLListwithnv_id(int nv_id){
        String where = "nguoidi_dsnhanvien like '%-"+nv_id+"-%'";
        String order = "nguoidi_id ASC";
        return dalNguoiDi.getList(where, order);
    }

    //get N nguoidi with kh_id
    public ArrayList<NguoiDi> getALLListwithkh_id(int kh_id){
        String where = "nguoidi_dskhach like '%-"+kh_id+"-%'";
        String order = "nguoidi_id ASC";
        return dalNguoiDi.getList(where, order);
    }

    //Return danh sach khach nguoi di search doan_id with tablemodel
    public DefaultTableModel getDSKhachTableModel(String doan_id){
        checkInput = new CheckInput();
        boolean bool = checkInput.CheckInputisNumber(doan_id);

        if(bool == true) {
            int i = Integer.parseInt(doan_id);
            NguoiDi nguoidi = null;
            nguoidi = get1NguoiDi(i);
            if(nguoidi != null){
                bllKhachHang = new BLLKhachHang();
                modeltable = bllKhachHang.getListKhachHangTableModel(bllKhachHang.getListWithString(nguoidi.getNguoidi_dskhachhang()));
            }
            else {
                System.out.println("ERROR -> BLLNguoiDi -> getDSKhachTableModel -> nguoidi = null !!!!");
            }
        }

        return modeltable;
    }

    //Return danh sach khach nguoi di search doan_id with tablemodel
    public DefaultTableModel getDSNhanVienTableModel(String doan_id){
        checkInput = new CheckInput();
        boolean bool = checkInput.CheckInputisNumber(doan_id);

        if(bool == true) {
            int i = Integer.parseInt(doan_id);
            NguoiDi nguoidi = null;
            nguoidi = get1NguoiDi(i);
            if(nguoidi != null){
                bllNhanVien = new BLLNhanVien();
                modeltable = bllNhanVien.getListNhanVienTableModel(bllNhanVien.getListWithString(nguoidi.getNguoidi_dsnhanvien()));
            }
            else {
                System.out.println("ERROR -> BLLNguoiDi -> getDSNhanVienTableModel -> nguoidi = null !!!!");
            }
        }
        return modeltable;
    }

    //Create nguoidi_id
    public int CreateNguoidi_id(){
        ArrayList<NguoiDi> list = null;
        list = getALLList();
        if(list != null && list.size() > 0){
            int i = list.size() - 1;
            return list.get(i).getNguoidi_id()+1;
        }
        return 1;
    }

    //Add NguoiDi
    public void addNguoiDi(int doan_id){
        NguoiDi nguoidi = new NguoiDi();
        nguoidi.setNguoidi_id(CreateNguoidi_id());
        nguoidi.setDoan_id(doan_id);
        nguoidi.setNguoidi_dskhachhang("");
        nguoidi.setNguoidi_dsnhanvien("");
        dalNguoiDi.addNguoiDi(nguoidi);
    }

    //Delete NguoiDi
    public void deleteNguoiDi(int doan_id){
        dalNguoiDi.deleteNguoiDi(doan_id);
    }


    //UPdate
    public void UPdateNguoiDi(NguoiDi nguoidi){
        dalNguoiDi.updateNguoiDi(nguoidi);
    }
        //Add 1
    public boolean updateDSKhachAdd1KH(int doan_id, int kh_id){
        NguoiDi nguoidi = get1NguoiDi(doan_id);
        String skh_id = "-"+kh_id+"-";
        checkInput = new CheckInput();
        boolean bool = checkInput.CheckDS(nguoidi.getNguoidi_dskhachhang(), skh_id);
        if(bool == true){
            nguoidi.setNguoidi_dskhachhang(nguoidi.getNguoidi_dskhachhang()+skh_id);
            UPdateNguoiDi(nguoidi);
            return true;
        }
        else return false;
    }
    public boolean updateDSNhanVienAdd1NV(int doan_id, int nv_id){
        NguoiDi nguoidi = get1NguoiDi(doan_id);
        String snv_id = "-"+nv_id+"-";
        checkInput = new CheckInput();
        boolean bool = checkInput.CheckDS(nguoidi.getNguoidi_dsnhanvien(), snv_id);
        if(bool == true){
            nguoidi.setNguoidi_dsnhanvien(nguoidi.getNguoidi_dsnhanvien()+snv_id);
            UPdateNguoiDi(nguoidi);
            return true;
        }
        else return false;
    }
        //Delete 1
    public boolean updateDSKhachDelete1KH(int doan_id, int kh_id){
        NguoiDi nguoidi = get1NguoiDi(doan_id);
        String skh_id = "-"+kh_id+"-";
        checkInput = new CheckInput();
        boolean bool = checkInput.CheckDS(nguoidi.getNguoidi_dskhachhang(), skh_id);
        if(bool == false){
            String updatedskhach = nguoidi.getNguoidi_dskhachhang().replace(skh_id, "");
            nguoidi.setNguoidi_dskhachhang(updatedskhach);
            UPdateNguoiDi(nguoidi);
            return true;
        }
        else return false;
    }
    public boolean updateDSNhanVienDelete1NV(int doan_id, int nv_id){
        NguoiDi nguoidi = get1NguoiDi(doan_id);
        String snv_id = "-"+nv_id+"-";
        checkInput = new CheckInput();
        boolean bool = checkInput.CheckDS(nguoidi.getNguoidi_dsnhanvien(), snv_id);
        if(bool == false){
            String updatedsnv = nguoidi.getNguoidi_dsnhanvien().replace(snv_id, "");
            nguoidi.setNguoidi_dsnhanvien(updatedsnv);
            UPdateNguoiDi(nguoidi);
            return true;
        }
        else return false;
    }

        //Delete 1 NV for N nguoidi
    public boolean updateNNguoiDiDSNhanVienDelete1NV(int nv_id){
        ArrayList<NguoiDi> list = null;
        list = getALLListwithnv_id(nv_id);
        if(list!=null && list.size()>=0){
            if(list.size()==0) return true;
            for(NguoiDi nguoidi : list){
                if(!updateDSNhanVienDelete1NV(nguoidi.getDoan_id(), nv_id)) return false;
            }
            return true;
        }
        return false;
    }

        //Delete 1 KH for N nguoidi
    public boolean updateNNguoiDiDSKhachDelete1KH(int kh_id){
        ArrayList<NguoiDi> list = null;
        list = getALLListwithkh_id(kh_id);
        if(list!=null && list.size()>=0){
            if(list.size()==0) return true;
            for(NguoiDi nguoidi : list){
                if(!updateDSKhachDelete1KH(nguoidi.getDoan_id(), kh_id)) return false;
            }
            return true;
        }
        return false;
    }


}
