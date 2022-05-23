package BLL;

import DAL.DALLoaiChiPhi;
import ENTRY.LoaiChiPhi;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Vector;

public class BLLLoaiChiPhi {
    DALLoaiChiPhi dalLoaiChiPhi;
    CheckInput checkinput;
    public DefaultComboBoxModel comboBoxModel=null;
    public DefaultTableModel modeltable=null;
    public Vector header=null;

    public BLLLoaiChiPhi() {dalLoaiChiPhi = new DALLoaiChiPhi();}

    //Get List
    public ArrayList<LoaiChiPhi> getALLLoaiCP(){
        String where = "cp_id like '%%'";
        String order = "cp_id ASC";
        return dalLoaiChiPhi.getList(where, order);
    }

    //Get list loai chi phi with name
    public ArrayList<LoaiChiPhi> getListLoaiCPWithName(String name){
        String where = "cp_ten like '%"+name+"%'";
        String order = "cp_id ASC";
        return dalLoaiChiPhi.getList(where, order);
    }

    //Get 1 Loại chi phi with cp_id
    public LoaiChiPhi get1LoaiCPWithcpid(int cp_id){
        String where = "cp_id ='"+cp_id+"'";
        String order = "cp_id ASC";
        ArrayList<LoaiChiPhi> list = null;
        list = dalLoaiChiPhi.getList(where, order);
        if(list!=null && list.size()>0){
            return list.get(0);
        }
        return null;
    }

    //Get List chi tiết chi phí with String
    public ArrayList<LoaiChiPhi> getListWithString(String dsChiPhi){
        ArrayList<LoaiChiPhi> list = new ArrayList<LoaiChiPhi>();
        if(dsChiPhi!=null && dsChiPhi.length()>0) {
            StringTokenizer stringTokenizer = new StringTokenizer(dsChiPhi, "-");
            if (stringTokenizer.countTokens() > 0) {
                while (stringTokenizer.hasMoreTokens()) {
                    String s = stringTokenizer.nextToken();
                    checkinput = new CheckInput();
                    boolean b = checkinput.CheckInputisNumber(s);
                    if (b == true) {
                        int i = Integer.parseInt(s);
                        LoaiChiPhi loaiChiPhi = null;
                        loaiChiPhi = get1LoaiCPWithcpid(i);
                        if (loaiChiPhi != null) list.add(loaiChiPhi);
                    } else {
                        System.out.println("ERROR -> BLLLoaiChiPhi -> getListWithString -> " + s);
                    }
                }
            }
        }
        return list;
    }

    //Create new id loại chi phí
    public int CreateIDLoaiCP(){
        ArrayList<LoaiChiPhi> list = null;
        list = getALLLoaiCP();
        if(list!=null && list.size()>0){
            return list.get(list.size()-1).getCp_id()+1;
        }
        return 1;
    }

    //Add loại chi phí
    public boolean add1LoaiCP(String ten, String mota){
        checkinput = new CheckInput();
        boolean bool1 = checkinput.CheckName(ten);
        if(bool1){
            LoaiChiPhi loaiChiPhi = new LoaiChiPhi();
            loaiChiPhi.setCp_id(CreateIDLoaiCP());
            loaiChiPhi.setCp_ten(ten);
            loaiChiPhi.setCp_mota(mota);
            return dalLoaiChiPhi.Add1Loai(loaiChiPhi);
        }
        return false;
    }

    //Update Loại chi phí
    public boolean update1LoaiCP(int cp_id, String ten, String mota){
        checkinput = new CheckInput();
        boolean bool1 = checkinput.CheckName(ten);
        if(bool1){
            LoaiChiPhi loaiChiPhi = new LoaiChiPhi();
            loaiChiPhi.setCp_id(cp_id);
            loaiChiPhi.setCp_ten(ten);
            loaiChiPhi.setCp_mota(mota);
            return dalLoaiChiPhi.Update1Loai(loaiChiPhi);
        }
        return false;
    }

    //Delete 1 loại chi phí



    //Craete combobox loại chi phí id
    public DefaultComboBoxModel getComboBoxModelCPID(){
        ArrayList<LoaiChiPhi> list = null;
        list = getALLLoaiCP();
        comboBoxModel = new DefaultComboBoxModel();
        if(list != null && list.size() > 0){
            for(LoaiChiPhi loaiChiPhi : list){
                comboBoxModel.addElement(loaiChiPhi.getCp_id());
            }
        }
        return comboBoxModel;
    }

    //Return tablemodel list
    public DefaultTableModel getTableModelLoaiCP(ArrayList<LoaiChiPhi> list){
        header = new Vector();
        header.add("Mã id");
        header.add("Tên");
        header.add("Mô tả");

        modeltable = new DefaultTableModel(header, 0);

        if(list!=null && list.size() > 0){
            for(LoaiChiPhi loaiChiPhi : list){
                Vector row = new Vector();
                row.add(loaiChiPhi.getCp_id());
                row.add(loaiChiPhi.getCp_ten());
                row.add(loaiChiPhi.getCp_mota());
                modeltable.addRow(row);
            }
        }
        return modeltable;
    }
}
