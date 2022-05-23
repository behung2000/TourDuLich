package BLL;

import DAL.DALNhanVien;
import ENTRY.NhanVien;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Vector;

public class BLLNhanVien {
    DALNhanVien dalNhanVien;
    CheckInput checkInput;
    public DefaultComboBoxModel comboBoxModel=null;
    public DefaultTableModel modeltable=null;
    public Vector header=null;

    public BLLNhanVien(){
        dalNhanVien = new DALNhanVien();
    }

    //get all list Nhan vien
    public ArrayList<NhanVien> getALLNhanVien(){
        String where = "nv_id like '%%'";
        String order = "nv_id ASC";
        return dalNhanVien.getList(where, order);
    }

    //get list nhan vien with name
    public ArrayList<NhanVien> getListNhanVienWithName(String name){
        String where = "nv_ten like '%"+name+"%'";
        String order = "nv_id ASC";
        return dalNhanVien.getList(where, order);
    }

    //get 1 nhan vien with id
    public NhanVien get1NhanVien(int nv_id){
        String where = "nv_id="+nv_id;
        String order = "nv_id ASC";
        ArrayList<NhanVien> list = new ArrayList<NhanVien>();
        list = dalNhanVien.getList(where, order);
        NhanVien nhanVien = null;
        if(list.size()==1){
            nhanVien = list.get(0);
        }
        else {
            System.out.println("ERROR -> BLLNhanVien -> get1NhanVien !!!!");
        }
        return nhanVien;
    }

    //get list nhan vien with string
    public ArrayList<NhanVien> getListWithString(String dsnhanvien){
        ArrayList<NhanVien> list = new ArrayList<NhanVien>();
        if(dsnhanvien!=null && dsnhanvien.length()>0) {
            StringTokenizer stringTokenizer = new StringTokenizer(dsnhanvien, "-");
            if (stringTokenizer.countTokens() > 0) {
                while (stringTokenizer.hasMoreTokens()) {
                    String s = stringTokenizer.nextToken();
                    checkInput = new CheckInput();
                    boolean b = checkInput.CheckInputisNumber(s);
                    if (b == true) {
                        int i = Integer.parseInt(s);
                        NhanVien nhanVien = null;
                        nhanVien = get1NhanVien(i);
                        if (nhanVien != null) list.add(nhanVien);
                    } else {
                        System.out.println("ERROR -> BLLNhanVien -> getListWithString -> " + s);
                    }
                }
            }
        }
        return list;
    }

    //Create new id nhân viên
    public int CreateIDNV(){
        ArrayList<NhanVien> list = null;
        list = getALLNhanVien();
        if(list!=null && list.size()>0){
            return list.get(list.size()-1).getNv_id()+1;
        }
        return 1;
    }

    //Add 1 nhân viên
    public boolean add1NV(String ten, String sdt, String ngaysinh, String email, String nhiemvu){
        checkInput = new CheckInput();
        boolean bool1 = checkInput.CheckName(ten);
        boolean bool2 = checkInput.CheckSDT(sdt);
        boolean bool3 = checkInput.CheckFormatDate(ngaysinh);
        boolean bool4 = checkInput.CheckEmail(email);
        boolean bool5 = checkInput.CheckName(nhiemvu);
        if(bool1 && bool2 && bool3 && bool4 && bool5){
            NhanVien addNV = new NhanVien();
            addNV.setNv_id(CreateIDNV());
            addNV.setNv_ten(ten);
            addNV.setNv_sdt(sdt);
            addNV.setNv_ngaysinh(ngaysinh);
            addNV.setNv_email(email);
            addNV.setNv_nhiemvu(nhiemvu);
            return dalNhanVien.Add1NV(addNV);
        }
        return false;
    }

    //Update 1 Nhân viên
    public boolean update1NV(int nv_id, String ten, String sdt, String ngaysinh, String email, String nhiemvu){
        checkInput = new CheckInput();
        boolean bool1 = checkInput.CheckName(ten);
        boolean bool2 = checkInput.CheckSDT(sdt);
        boolean bool3 = checkInput.CheckFormatDate(ngaysinh);
        boolean bool4 = checkInput.CheckEmail(email);
        boolean bool5 = checkInput.CheckName(nhiemvu);
        if(bool1 && bool2 && bool3 && bool4 && bool5){
            NhanVien addNV = new NhanVien();
            addNV.setNv_id(nv_id);
            addNV.setNv_ten(ten);
            addNV.setNv_sdt(sdt);
            addNV.setNv_ngaysinh(ngaysinh);
            addNV.setNv_email(email);
            addNV.setNv_nhiemvu(nhiemvu);
            return dalNhanVien.Update1NV(addNV);
        }
        return false;
    }

    //Delete 1 Nhân viên
    public boolean delete1NV(int nv_id){
        BLLNguoiDi bllNguoiDi = new BLLNguoiDi();
        boolean bool1 = bllNguoiDi.updateNNguoiDiDSNhanVienDelete1NV(nv_id);
        if(bool1==true) return dalNhanVien.Delete1NV(nv_id);
        return false;
    }

    //Creae Combobox nhân viên
    public DefaultComboBoxModel getComboboxIDNV(){
        ArrayList<NhanVien> list = null;
        list = getALLNhanVien();
        comboBoxModel = new DefaultComboBoxModel();
        if(list != null && list.size() > 0){
            for(NhanVien nhanvien : list){
                comboBoxModel.addElement(nhanvien.getNv_id());
            }
        }
        return comboBoxModel;
    }

    //Return list Nhan Vien with tablemodel
    public DefaultTableModel getListNhanVienTableModel(ArrayList<NhanVien> listkh){
        header = new Vector();
        header.add("Mã Nhân viên");
        header.add("Tên nhân viên");
        header.add("SDT");
        header.add("Ngày sinh (YYYY-MM-DD)");
        header.add("email");
        header.add("Nhiệm vụ");

        modeltable = new DefaultTableModel (header,0);

        ArrayList<NhanVien> list = listkh;
        for(NhanVien nhanVien : list){
            Vector row = new Vector();
            row.add(nhanVien.getNv_id());
            row.add(nhanVien.getNv_ten());
            row.add(nhanVien.getNv_sdt());
            row.add(nhanVien.getNv_ngaysinh());
            row.add(nhanVien.getNv_email());
            row.add(nhanVien.getNv_nhiemvu());
            modeltable.addRow(row);
        }
        return modeltable;
    }

}
