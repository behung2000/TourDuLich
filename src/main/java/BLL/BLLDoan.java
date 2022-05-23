package BLL;

import DAL.DALDoan;
import ENTRY.Doan;
import ENTRY.Tour;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Vector;

public class BLLDoan {
    DALDoan dalDoan;
    BLLNguoiDi bllNguoiDi;
    BLLChiPhi bllTourChiPhi;
    CheckInput checkInput;
    public DefaultTableModel modeltable=null;
    public Vector header=null;

    public BLLDoan(){
        dalDoan = new DALDoan();
    }

    //Get all list doan
    public ArrayList<Doan> getALLDoan(){
        String where = "doan_id like '%%'";
        String order = "doan_id ASC";
        return dalDoan.getList(where,order);
    }

    //Get list doan with tour_id
    public ArrayList<Doan> getALLDoanWithTour_id(int tour_id){
        String where = "tour_id='"+tour_id+"'";
        String order = "doan_id ASC";
        return dalDoan.getList(where,order);
    }

    //Get 1 Doan
    public Doan get1Doan(int i){
        String where = "doan_id = '"+i+"'";
        String order = "doan_id ASC";
        ArrayList<Doan> list = dalDoan.getList(where, order);
        if(list.size()>0){
            return list.get(0);
        }
        return null;
    }

    //Get List doan with search name
    public ArrayList<Doan> getSearchName(String name){
        String where = "doan_name like '%"+name+"%'";
        String order = "doan_id ASC";
        return dalDoan.getList(where,order);
    }

    //Return list doan with TableModel
    public DefaultTableModel getListDoanTableModel(ArrayList<Doan> listDoan){
        header = new Vector();
        header.add("Mã đoàn");
        header.add("Tên đoàn");
        header.add("Tên tour");
        header.add("Ngày đi");
        header.add("Ngày về");
        header.add("Chi tiết chương trình");

        modeltable = new DefaultTableModel (header,0);

        ArrayList<Doan> list = listDoan;
        for(Doan doan : list){
            Vector row = new Vector();
            row.add(doan.getDoan_id());
            row.add(doan.getDoan_name());
            BLLTour bllTour = new BLLTour();
            Tour tour = bllTour.get1Tour(doan.getTour_id());
            if(tour!=null) row.add(tour.getTour_ten());
            else row.add("lỗi -- error");
            row.add(doan.getDoan_ngaydi());
            row.add(doan.getDoan_ngayve());
            row.add(doan.getDoan_chitietchuongtrinh());
            modeltable.addRow(row);
        }
        return modeltable;
    }

    //Create doan_id
    public int CreateDoan_id(){
        ArrayList<Doan> list = null;
        list = getALLDoan();
        if(list != null && list.size() > 0){
            int i = list.size() - 1;
            return list.get(i).getDoan_id()+1;
        }
        return 1;
    }

    //ADD Doan
    public boolean CheckTTaddDoan(Doan doan){
        checkInput = new CheckInput();
        boolean bool1 = checkInput.CheckDateNgayDiNgayVeInputADD(doan.getDoan_ngaydi(), doan.getDoan_ngayve());
        boolean bool2 = checkInput.CheckName(doan.getDoan_name());
        if(bool1==false || bool2==false) return false;
        boolean bool = dalDoan.AddDoan(doan);
        if(bool == true){
            bllNguoiDi = new BLLNguoiDi();
            bllNguoiDi.addNguoiDi(doan.getDoan_id());
            bllTourChiPhi = new BLLChiPhi();
            bllTourChiPhi.addTourChiphi(doan.getDoan_id());
            return true;
        }
        return false;
    }

    //Delete Doan
    public boolean deleteDoan(int doan_id){
        bllNguoiDi = new BLLNguoiDi();
        bllNguoiDi.deleteNguoiDi(doan_id);
        bllTourChiPhi = new BLLChiPhi();
        bllTourChiPhi.deleteChiphi(doan_id);
        return dalDoan.deleteDoan(doan_id);
    }

    //Delete Doan with tour_id
    public boolean deleteNDoanWithTour_id(int tour_id) {
        ArrayList<Doan> list = null;
        list = getALLDoanWithTour_id(tour_id);
        if(list!=null && list.size()>=0){
            if(list.size()==0) return true;
            for(Doan doan : list){
                if(!deleteDoan(doan.getDoan_id())) return false;
            }
            return true;
        }
        return false;
    }

    //UPDATE Doan
    public boolean CheckTTupdateDoan(Doan doan){
        checkInput = new CheckInput();
        boolean bool1 = checkInput.CheckDateNgayDiNgayVeInputADD(doan.getDoan_ngaydi(), doan.getDoan_ngayve());
        boolean bool2 = checkInput.CheckName(doan.getDoan_name());
        if(bool1==false || bool2==false) return false;
        return dalDoan.updateDoan(doan);
    }

}
