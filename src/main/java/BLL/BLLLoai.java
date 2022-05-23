package BLL;

import DAL.DALLoai;
import ENTRY.Loai;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Vector;

public class BLLLoai {
    DALLoai dalLoai;
    CheckInput checkinput;
    public DefaultComboBoxModel comboBoxModel=null;
    public DefaultTableModel modeltable=null;
    public Vector header=null;

    public BLLLoai() {dalLoai = new DALLoai();}

    //Get ALL List Loai
    public ArrayList<Loai> getALLLoai(){
        String where = "loai_id like '%%'";
        String order = "loai_id ASC";
        return dalLoai.getList(where, order);
    }

    //Get list loai with name
    public ArrayList<Loai> getListLoaiWithName(String name){
        String where = "loai_ten like '%"+name+"%'";
        String order = "loai_id ASC";
        return dalLoai.getList(where, order);
    }

    //Get 1 Loai
    public Loai get1Loai(int id_loai){
        String where = "loai_id = '"+id_loai+"'";
        String order = "loai_id ASC";
        ArrayList<Loai> list = dalLoai.getList(where, order);
        if(list.size() > 0){
            return list.get(0);
        }
        return null;
    }

    //Create id tour loại
    public int CreateIDTourLoai(){
        ArrayList<Loai> list = null;
        list = getALLLoai();
        if(list!=null && list.size()>0){
            return list.get(list.size()-1).getLoai_id()+1;
        }
        return 1;
    }

    //Add 1 tour loai
    public boolean add1Loai(String ten, String mota){
        checkinput = new CheckInput();
        boolean bool1 = checkinput.CheckName(ten);
        if(bool1 == true){
            Loai loai = new Loai();
            loai.setLoai_id(CreateIDTourLoai());
            loai.setLoai_ten(ten);
            loai.setLoai_mota(mota);
            return dalLoai.Add1Loai(loai);
        }
        return false;
    }

    //Update 1 tour loai
    public boolean update1Loai(int loai_id, String ten, String mota){
        checkinput = new CheckInput();
        boolean bool1 = checkinput.CheckName(ten);
        if(bool1 == true){
            Loai loai = new Loai();
            loai.setLoai_id(loai_id);
            loai.setLoai_ten(ten);
            loai.setLoai_mota(mota);
            return dalLoai.Update1Loai(loai);
        }
        return false;
    }

    //Delete 1 tour loai
    public boolean delete1LoaiTour(int loai_id){
        BLLTour bllTour = new BLLTour();
        boolean bool1 = bllTour.DeleteNTourWithLoaiID(loai_id);
        if(bool1 == true){
            return dalLoai.Delete1Loai(loai_id);
        }
        return false;
    }

    //Create comboboxmodel
    public DefaultComboBoxModel getComboboxLoai(){
        ArrayList<Loai> list = null;
        list = getALLLoai();
        if(list != null && list.size() > 0){
            comboBoxModel = new DefaultComboBoxModel();
            for(Loai loai : list){
                comboBoxModel.addElement(loai.getLoai_id());
            }
        }
        return comboBoxModel;
    }

    //return table model tour loại
    public DefaultTableModel getTableModelTourLoai(ArrayList<Loai> list){
        header = new Vector();
        header.add("Mã id");
        header.add("Tên");
        header.add("Mô tả");

        modeltable = new DefaultTableModel(header, 0);

        if(list!=null && list.size() > 0){
            for(Loai loai : list){
                Vector row = new Vector();
                row.add(loai.getLoai_id());
                row.add(loai.getLoai_ten());
                row.add(loai.getLoai_mota());
                modeltable.addRow(row);
            }
        }
        return modeltable;
    }
}
