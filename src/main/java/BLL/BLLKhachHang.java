package BLL;

import DAL.DALKhachHang;
import ENTRY.KhachHang;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Vector;

public class BLLKhachHang {
    DALKhachHang dalKhachHang;
    CheckInput checkInput;
    public DefaultComboBoxModel comboBoxModel=null;
    public DefaultTableModel modeltable=null;
    public Vector header=null;

    public BLLKhachHang(){
        dalKhachHang = new DALKhachHang();
    }

    //get all list khach hang
    public ArrayList<KhachHang> getALLKhachHang(){
        String where = "kh_id like '%%'";
        String order = "kh_id ASC";
        return dalKhachHang.getList(where, order);
    }

    //get list khach hang with name
    public ArrayList<KhachHang> getListKhachHangWithName(String name){
        String where = "kh_ten like '%"+name+"%'";
        String order = "kh_id ASC";
        return dalKhachHang.getList(where, order);
    }

    //get 1 Khach hang with id
    public KhachHang get1KhachHang(int kh_id){
        String where = "kh_id="+kh_id;
        String order = "kh_id ASC";
        ArrayList<KhachHang> list = new ArrayList<KhachHang>();
        list = dalKhachHang.getList(where, order);
        KhachHang khachHang = null;
        if(list.size()==1){
            khachHang = list.get(0);
        }
        else {
            System.out.println("ERROR -> BLLKhachHang -> get1khachhang !!!!");
        }
        return khachHang;
    }

    //get list khach hang with string
    public ArrayList<KhachHang> getListWithString(String dskhachHang){
        ArrayList<KhachHang> list = new ArrayList<KhachHang>();
        if(dskhachHang!=null && dskhachHang.length()>0) {
            StringTokenizer stringTokenizer = new StringTokenizer(dskhachHang, "-");
            if (stringTokenizer.countTokens() > 0) {
                while (stringTokenizer.hasMoreTokens()) {
                    String s = stringTokenizer.nextToken();
                    checkInput = new CheckInput();
                    boolean b = checkInput.CheckInputisNumber(s);
                    if (b == true) {
                        int i = Integer.parseInt(s);
                        KhachHang khachHang = null;
                        khachHang = get1KhachHang(i);
                        if (khachHang != null) list.add(khachHang);
                    } else {
                        System.out.println("ERROR -> BLLKhachHang -> getListWithString -> " + s);
                    }
                }
            }
        }
        return list;
    }

    //Create new id khách
    public int CreateIDKhach(){
        ArrayList<KhachHang> list = null;
        list = getALLKhachHang();
        if(list!=null && list.size()>0){
            return list.get(list.size()-1).getKh_id()+1;
        }
        return 1;
    }

    //Add 1 khach
    public boolean add1Khach(String ten, String sdt, String ngaysinh, String email, String cmnd){
        checkInput = new CheckInput();
        boolean bool1 = checkInput.CheckName(ten);
        boolean bool2 = checkInput.CheckSDT(sdt);
        boolean bool3 = checkInput.CheckFormatDate(ngaysinh);
        boolean bool4 = checkInput.CheckEmail(email);
        boolean bool5 = checkInput.CheckCMND(cmnd);
        if(bool1 && bool2 && bool3 && bool4 && bool5){
            KhachHang khachhang = new KhachHang();
            khachhang.setKh_id(CreateIDKhach());
            khachhang.setKh_ten(ten);
            khachhang.setKh_sdt(sdt);
            khachhang.setKh_ngaysinh(ngaysinh);
            khachhang.setKh_email(email);
            khachhang.setKh_cmnd(cmnd);
            return dalKhachHang.Add1KH(khachhang);
        }
        return false;
    }

    //Update 1 khach
    public boolean update1Khach(int kh_id, String ten, String sdt, String ngaysinh, String email, String cmnd){
        checkInput = new CheckInput();
        boolean bool1 = checkInput.CheckName(ten);
        boolean bool2 = checkInput.CheckSDT(sdt);
        boolean bool3 = checkInput.CheckFormatDate(ngaysinh);
        boolean bool4 = checkInput.CheckEmail(email);
        boolean bool5 = checkInput.CheckCMND(cmnd);
        if(bool1 && bool2 && bool3 && bool4 && bool5){
            KhachHang khachhang = new KhachHang();
            khachhang.setKh_id(kh_id);
            khachhang.setKh_ten(ten);
            khachhang.setKh_sdt(sdt);
            khachhang.setKh_ngaysinh(ngaysinh);
            khachhang.setKh_email(email);
            khachhang.setKh_cmnd(cmnd);
            return dalKhachHang.Update1KH(khachhang);
        }
        return false;
    }

    //Delete 1 khach
    public boolean delete1Khach(int kh_id){
        BLLNguoiDi bllNguoiDi = new BLLNguoiDi();
        boolean bool1 = bllNguoiDi.updateNNguoiDiDSKhachDelete1KH(kh_id);
        if(bool1==true) return dalKhachHang.Delete1KH(kh_id);
        return false;
    }

    //Create combobox model
    public DefaultComboBoxModel getComboBoxIDKH(){
        ArrayList<KhachHang> list = null;
        list = getALLKhachHang();
        comboBoxModel = new DefaultComboBoxModel();
        if(list != null && list.size() > 0){
            for(KhachHang khachhang : list){
                comboBoxModel.addElement(khachhang.getKh_id());
            }
        }
        return comboBoxModel;
    }

    //Return list khach hang with tablemodel
    public DefaultTableModel getListKhachHangTableModel(ArrayList<KhachHang> listkh){
        header = new Vector();
        header.add("Mã khách hàng");
        header.add("Tên khách hàng");
        header.add("SDT");
        header.add("Ngày sinh (YYYY-MM-DD)");
        header.add("email");
        header.add("cmnd");

        modeltable = new DefaultTableModel (header,0);

        ArrayList<KhachHang> list = listkh;
        for(KhachHang khachhang : list){
            Vector row = new Vector();
            row.add(khachhang.getKh_id());
            row.add(khachhang.getKh_ten());
            row.add(khachhang.getKh_sdt());
            row.add(khachhang.getKh_ngaysinh());
            row.add(khachhang.getKh_email());
            row.add(khachhang.getKh_cmnd());
            modeltable.addRow(row);
        }

        return modeltable;
    }
}
