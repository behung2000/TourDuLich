package BLL;

import DAL.DALChiPhi;
import ENTRY.ChiPhi;
import ENTRY.LoaiChiPhi;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Vector;

public class BLLChiPhi {
    DALChiPhi dalTourChiPhi;
    CheckInput checkInput;
    BLLLoaiChiPhi bllLoaiChiPhi;
    public DefaultTableModel modeltable=null;
    public Vector header=null;

    public BLLChiPhi(){
        dalTourChiPhi = new DALChiPhi();
    }

    //get ALL List
    public ArrayList<ChiPhi> getALLList(){
        String where = "doan_id like '%%'";
        String order = "chiphi_id ASC";
        return  dalTourChiPhi.getList(where, order);
    }

    //Get 1 Chi phí with id_doan
    public ChiPhi get1ChiphiWithIDDoan(int id_doan){
        String where = "doan_id='"+id_doan+"'";
        String order = "chiphi_id ASC";
        ArrayList<ChiPhi> list = null;
        list = dalTourChiPhi.getList(where, order);
        if(list!=null && list.size()>0){
            return list.get(0);
        }
        return null;
    }

    //Create chiphi_id
    public int CreateChiphi_id(){
        ArrayList<ChiPhi> list = null;
        list = getALLList();
        if(list!=null && list.size()>0){
            int i = list.size() - 1;
            System.out.println(list.get(i).getChiphi_id());
            return list.get(i).getChiphi_id()+1;
        }
        return 1;
    }

    //Add tour_chiphi
    public void addTourChiphi(int doan_id){
        ChiPhi chiphi = new ChiPhi();
        chiphi.setChiphi_id(CreateChiphi_id());
        chiphi.setDoan_id(doan_id);
        chiphi.setChiphi_total(0);
        chiphi.setChiphi_chitiet("");
        dalTourChiPhi.addTour_ChiPhi(chiphi);
    }

    //Delete tour_chiphi
    public void deleteChiphi(int doan_id){
        dalTourChiPhi.deleteChiPhi(doan_id);
    }

    //Update tour_chiphi
    public void updateLoaiCP(ChiPhi chiphi){
        dalTourChiPhi.updateTour_ChiPhi(chiphi);
    }

    //Update chi tiết chi phi add 1 loai chi phi
    public boolean updateDSCTCPadd1LoaiCP(int doan_id, int cp_id){
        ChiPhi chiphi = get1ChiphiWithIDDoan(doan_id);
        String scp_id = "-"+cp_id+"-";
        checkInput = new CheckInput();
        boolean bool = checkInput.CheckDS(chiphi.getChiphi_chitiet(), scp_id);
        if(bool == true){
            String updatedsctcp = chiphi.getChiphi_chitiet()+scp_id;
            chiphi.setChiphi_chitiet(updatedsctcp);
            Double updatetotal =  chiphi.getChiphi_total()+100000;
            chiphi.setChiphi_total(updatetotal);
            updateLoaiCP(chiphi);
            return true;
        }
        else return false;
    }

    //Update chi tiết chi phi delete 1 loai chi phi
    public boolean updateDSCTCPdelete1LoaiCP(int doan_id, int cp_id){
        ChiPhi chiphi = get1ChiphiWithIDDoan(doan_id);
        String scp_id = "-"+cp_id+"-";
        checkInput = new CheckInput();
        boolean bool = checkInput.CheckDS(chiphi.getChiphi_chitiet(), scp_id);
        if(bool == false){
            String updatedsctcp = chiphi.getChiphi_chitiet().replace(scp_id, "");
            chiphi.setChiphi_chitiet(updatedsctcp);
            Double updatetotal =  chiphi.getChiphi_total()-100000;
            chiphi.setChiphi_total(updatetotal);
            updateLoaiCP(chiphi);
            return true;
        }
        else return false;
    }

    //return danh sach chi tiết chi phí search doan_id with tablemodel
    public DefaultTableModel getDSChiPhiTableModel(int doan_id){
        ChiPhi chiphi = null;
        chiphi = get1ChiphiWithIDDoan(doan_id);
        if(chiphi != null){
            bllLoaiChiPhi = new BLLLoaiChiPhi();
            modeltable = bllLoaiChiPhi.getTableModelLoaiCP(bllLoaiChiPhi.getListWithString(chiphi.getChiphi_chitiet()));
        }
        else {
            System.out.println("ERROR -> BLLChiPhi -> getDSChiPhiTableModel -> chiphi = null !!!!");
        }
        return modeltable;
    }

    //Test
    /*
    public static void main(String[] args){
        BLLTourChiPhi bllTourChiPhi = new BLLTourChiPhi();
        ArrayList<ChiPhi> list = null;
        list = bllTourChiPhi.getALLList();
        System.out.println(list.get(list.size()-1).getChiphi_id());
    }
     */


}
