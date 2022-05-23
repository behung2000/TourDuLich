package BLL;

import DAL.DALGia;
import ENTRY.Gia;
import ENTRY.Tour;

import javax.swing.table.DefaultTableModel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

public class BLLGia {
    DALGia dalGia;
    CheckInput checkinput;
    public DefaultTableModel modeltable=null;
    public Vector header=null;

    public BLLGia(){ dalGia = new DALGia(); }

    //Get List ALL


    //Get List ALL extends
    public ArrayList<Tour> getALLListExtends(){
        String where = "gia_id like '%%'";
        String order = "gia_id ASC";
        return dalGia.getListExtends(where, order);
    }

    //Create gia_id
    public int CreateGiaID(){
        ArrayList<Tour> list = null;
        list = getALLListExtends();
        if(list != null && list.size()>0){
            return list.get(list.size()-1).getGia_id()+1;
        }
        return 1;
    }

    //ADD 1 Gia
    public boolean Add1Gia(int tour_id, String giasotien, String tungay, String ghichu){
        checkinput = new CheckInput();
        boolean bool1 = checkinput.CheckInputisDouble(giasotien);
        boolean bool2 = checkinput.CheckFormatDate(tungay);
        if(bool1 == true && bool2 == true){
            try {
                int gia_id = CreateGiaID();
                Double gia_sotien = Double.parseDouble(giasotien);
                Gia gia = new Gia();
                gia.setGia_id(gia_id);
                gia.setGia_sotien(gia_sotien);
                gia.setTour_id(tour_id);
                gia.setGia_tungay(tungay);
                gia.setGhichu(ghichu);
                return dalGia.add1Gia(gia);
            }
            catch (NumberFormatException e)
            {
                return false;
            }
        }
        return false;
    }

    //Update 1 Gia
    public boolean Update1Gia(int gia_id, int tour_id, String giasotien, String tungay, String ghichu){
        checkinput = new CheckInput();
        boolean bool1 = checkinput.CheckInputisDouble(giasotien);
        boolean bool2 = checkinput.CheckFormatDate(tungay);
        if(bool1 == true && bool2 == true){
            try {
                Double gia_sotien = Double.parseDouble(giasotien);
                Gia gia = new Gia();
                gia.setGia_id(gia_id);
                gia.setGia_sotien(gia_sotien);
                gia.setTour_id(tour_id);
                gia.setGia_tungay(tungay);
                gia.setGhichu(ghichu);
                return dalGia.update1Gia(gia);
            }
            catch (NumberFormatException e)
            {
                return false;
            }
        }
        return false;
    }

    //Delete 1 gia with gia_id
    public boolean Delete1GiaWithGia_id(int gia_id){
        return dalGia.delete1GiaWithGia_id(gia_id);
    }

    //Delete N gia with tour_id
    public boolean DeleteNGiaWithTour_id(int tour_id){
        return dalGia.deleteNGiaWithTour_id(tour_id);
    }

    //Return table model list extends
    public DefaultTableModel getTableModelALLListExtends(){
        header = new Vector();
        header.add("Giá id");
        header.add("Tour id");
        header.add("Tên tour");
        header.add("Giá (số tiền)");
        header.add("Giá từ ngày (YYYY-MM-DD)");
        header.add("Ghi chú");
        modeltable = new DefaultTableModel(header, 0);

        ArrayList<Tour> list = null;
        list = getALLListExtends();
        if(list!=null && list.size()>0){
            for(Tour tour : list){
                Vector row = new Vector();
                row.add(tour.getGia_id());
                row.add(tour.getTour_id());
                row.add(tour.getTour_ten());
                row.add(tour.getGia_sotien());
                row.add(tour.getGia_tungay());
                row.add(tour.getGhichu());
                modeltable.addRow(row);
            }
        }
        return modeltable;
    }

    //Get giá mới nhất đang áp dụng bằng id
    public Gia getGiaNOW(int tour_id){
        Gia gia = null;

        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String nowdate = dateFormat.format(date);

        String where = "tour_id="+tour_id+" AND gia_tungay<='"+nowdate+"'";
        String order = "gia_tungay DESC";

        ArrayList<Gia> list = null;
        list = dalGia.getList(where, order);

        if(list!=null){
            if(list.size()>0) {
                gia = list.get(0);
            }
            else{
                return null;
            }
        }
        return gia;
    }

}
