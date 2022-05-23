package BLL;

import DAL.DALDiaDiem;
import ENTRY.ChiTiet;
import ENTRY.DiaDiem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Vector;

public class BLLDiaDiem {
    DALDiaDiem dalDiaDiem;
    CheckInput checkInput;
    public DefaultComboBoxModel comboBoxModel;
    public DefaultTableModel tableModel;
    public Vector header;

    public BLLDiaDiem(){ dalDiaDiem = new DALDiaDiem();}

    //Get ALL list dia diem
    public ArrayList<DiaDiem> getALLList(){
        String where = "dd_id like '%%'";
        String order = "dd_id ASC";
        return dalDiaDiem.getList(where, order);
    }

    //Get list dia diem with name
    public ArrayList<DiaDiem> getListDDWithname(String name){
        String where = "dd_ten like '%"+name+"%'";
        String order = "dd_id ASC";
        return dalDiaDiem.getList(where, order);
    }

    //Get 1 dia diem with dd_id
    public DiaDiem get1DiaDiem(int dd_id){
        String where = "dd_id="+dd_id;
        String order = "dd_id ASC";
        ArrayList<DiaDiem> list = new ArrayList<DiaDiem>();
        list = dalDiaDiem.getList(where, order);
        DiaDiem diaDiem = null;
        if(list.size()==1){
            diaDiem = list.get(0);
        }
        else {
            System.out.println("ERROR -> BLLDiaDiem -> get1DiaDiem !!!!");
        }
        return diaDiem;
    }

    //Get list dia diem with chitiet search tour_id
    public ArrayList<DiaDiem> getListDiaDiemForChiTiet(ArrayList<ChiTiet> listct){
        ArrayList<DiaDiem> list = new ArrayList<DiaDiem>();
        for(ChiTiet chiTiet : listct){
            DiaDiem diaDiem = null;
            diaDiem = get1DiaDiem(chiTiet.getDd_id());
            if(diaDiem != null){
                diaDiem.setCt_thutu(chiTiet.getCt_thutu());
                diaDiem.setCt_id(chiTiet.getCt_id());
                diaDiem.setTour_id(chiTiet.getTour_id());
                list.add(diaDiem);
            }
            else {
                System.out.println("ERROR -> BLLDiaDiem -> getListDiaDiemForChiTiet -> diadiem = null !!!!");
            }
        }
        return list;
    }

    //Create ID dia diem
    public int CreateIDDiaDiem(){
        ArrayList<DiaDiem> list = null;
        list = getALLList();
        if(list!=null && list.size()>0){
            return list.get(list.size()-1).getDd_id()+1;
        }
        return 1;
    }

    //Add 1 dia diem
    public boolean add1DiaDiem(String tp, String ten, String mota){
        checkInput = new CheckInput();
        boolean bool1 = checkInput.CheckName(tp);
        boolean bool2 = checkInput.CheckName(ten);
        if(bool1==true && bool2==true){
            DiaDiem diadiem = new DiaDiem();
            diadiem.setDd_id(CreateIDDiaDiem());
            diadiem.setDd_thanhpho(tp);
            diadiem.setDd_ten(ten);
            diadiem.setDd_mota(mota);
            return dalDiaDiem.Add1DiaDiem(diadiem);
        }
        return false;
    }

    //Update 1 dia diem
    public boolean update1DiaDiem(int dd_id, String tp, String ten, String mota){
        checkInput = new CheckInput();
        boolean bool1 = checkInput.CheckName(tp);
        boolean bool2 = checkInput.CheckName(ten);
        if(bool1==true && bool2==true){
            DiaDiem diadiem = new DiaDiem();
            diadiem.setDd_id(dd_id);
            diadiem.setDd_thanhpho(tp);
            diadiem.setDd_ten(ten);
            diadiem.setDd_mota(mota);
            return dalDiaDiem.UpdateDiaDiem(diadiem);
        }
        return false;
    }

    //Delete 1 DiaDiem with dd_id
    public boolean delete1DiaDiemWithddid(int dd_id){
        return dalDiaDiem.DeleteDiaDiem(dd_id);
    }


    //Create combobox
    public DefaultComboBoxModel getIDDiaDiemCombobox(){
        ArrayList<DiaDiem> list = null;
        list = getALLList();
        comboBoxModel = new DefaultComboBoxModel();
        if(list!=null && list.size()>0){
            for(DiaDiem diadiem : list ){
                String si = ""+diadiem.getDd_id();
                comboBoxModel.addElement(si);
            }
        }
        return comboBoxModel;
    }

    //Return list DiDiem with tableModel
    public DefaultTableModel getListTableModelDiaDiem(ArrayList<DiaDiem> list){
        header = new Vector();
        header.add("Mã id");
        header.add("Thành phố");
        header.add("Tên");
        header.add("Mô tả");

        tableModel = new DefaultTableModel(header, 0);

        if(list != null && list.size() > 0) {
            for (DiaDiem diaDiem : list) {
                Vector row = new Vector();
                row.add(diaDiem.getDd_id());
                row.add(diaDiem.getDd_thanhpho());
                row.add(diaDiem.getDd_ten());
                row.add(diaDiem.getDd_mota());
                tableModel.addRow(row);
            }
        }
        return tableModel;
    }

    //Return list DiaDiem search chitiet with TableModel
    public DefaultTableModel getListDSDiaDiemSearchChiTietTableModel(ArrayList<DiaDiem> list) {
        header = new Vector();
        header.add("Thứ tự đi");
        header.add("Mã địa điểm");
        header.add("Thành phố");
        header.add("Tên");
        header.add("Mô tả");

        tableModel = new DefaultTableModel(header, 0);

        for(DiaDiem diaDiem : list){
            Vector row = new Vector();
            row.add(diaDiem.getCt_thutu());
            row.add(diaDiem.getDd_id());
            row.add(diaDiem.getDd_thanhpho());
            row.add(diaDiem.getDd_ten());
            row.add(diaDiem.getDd_mota());

            tableModel.addRow(row);
        }

        return tableModel;
    }

}
