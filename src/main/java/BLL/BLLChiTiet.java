package BLL;

import DAL.DALChiTiet;
import ENTRY.ChiTiet;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Vector;

public class BLLChiTiet {
    DALChiTiet dalChiTiet;
    CheckInput checkInput;
    BLLDiaDiem bllDiaDiem;
    public DefaultTableModel tableModel=null;
    public Vector header=null;

    public BLLChiTiet(){ dalChiTiet = new DALChiTiet();}

    //Get ALL list
    public ArrayList<ChiTiet> getALLListChiTiet(){
        String where = "tour_id like '%%'";
        String order = "ct_id ASC";
        return dalChiTiet.getList(where, order);
    }

    //get list Chi tiet with tour_id
    public ArrayList<ChiTiet> getSearchTour_id(int tour_id){
        String where = "tour_id="+tour_id;
        String order = "ct_thutu ASC";
        return dalChiTiet.getList(where, order);
    }

    //Create ct_id
    public int CreateCT_id(){
        ArrayList<ChiTiet> list = null;
        list = getALLListChiTiet();
        if(list!=null && list.size()>0){
            return list.get(list.size()-1).getCt_id()+1;
        }
        return 1;
    }

    //Create ct_thutu
    public int CreateCT_ThuTu(int tour_id){
        ArrayList<ChiTiet> list = null;
        list = getSearchTour_id(tour_id);
        if(list!=null && list.size()>0){
            return list.get(list.size()-1).getCt_thutu()+1;
        }
        return 1;
    }

    //add 1 chitiet
    public boolean Add1ChiTiet(int tour_id, int  dd_id){
        int ct_id = CreateCT_id();
        int ct_thutu = CreateCT_ThuTu(tour_id);
        ChiTiet chiTiet = new ChiTiet();
        chiTiet.setCt_id(ct_id);
        chiTiet.setTour_id(tour_id);
        chiTiet.setDd_id(dd_id);
        chiTiet.setCt_thutu(ct_thutu);
        boolean bool = dalChiTiet.insert1ChiTiet(chiTiet);
        return bool;
    }

    //Delete 1 chitiet with tour_id, dd_id, ct_thutu
    public boolean DeleteChiTietWithtourid_ddid_ctthutu(int tour_id, int dd_id, int ct_thutu){
        boolean bool = dalChiTiet.deleteChiTietWithtourid_ddid_ctthutu(tour_id, dd_id, ct_thutu);
        return bool;
    }

    //Delete n chitiet with tour_id
    public boolean DeleteNChiTietWithTour_id(int tour_id){
        return dalChiTiet.deleteNChiTietWithTour_id(tour_id);
    }

    //Return list dia diem search tour_id with tablemodel
    public DefaultTableModel getDSDiaDiemTableModel(String tour_id) {
        checkInput = new CheckInput();
        boolean b = checkInput.CheckInputisNumber(tour_id);
        if(b==true){
            int i = Integer.parseInt(tour_id);
            ArrayList<ChiTiet> list = null;
            list = getSearchTour_id(i);
            if(list!=null){
                bllDiaDiem = new BLLDiaDiem();
                tableModel = bllDiaDiem.getListDSDiaDiemSearchChiTietTableModel(bllDiaDiem.getListDiaDiemForChiTiet(list));
            }
            else {
                System.out.println("ERROR -> BLLNguoiDi -> getDSKhachTableModel -> nguoidi = null !!!!");
            }
        }
        return  tableModel;
    }
}
