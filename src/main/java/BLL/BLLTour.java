package BLL;

import DAL.DALTour;
import ENTRY.Gia;
import ENTRY.Tour;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Vector;

public class BLLTour {
    DALTour dalTour;
    BLLGia bllGia;
    CheckInput checkInput;
    public DefaultComboBoxModel comboBoxModel=null;
    public DefaultTableModel modeltable=null;
    public Vector header=null;

    public BLLTour(){ dalTour = new DALTour(); }

    //Get ALL list tour
    public ArrayList<Tour> getALLTour(){
        String where = "tour_id like '%%'";
        String order = "tour_id ASC";
        return dalTour.getList(where, order);
    }

    //Get 1 Tour
    public Tour get1Tour(int tour_id){
        String where = "tour_id = '"+tour_id+"'";
        String order = "tour_id ASC";
        ArrayList<Tour> list = dalTour.getList(where, order);
        if(list.size() > 0){
            return list.get(0);
        }
        return null;
    }

    //Get list Tour with search name tour
    public ArrayList<Tour> getSearchName(String name){
        String where = "tour_ten like '%"+name+"%'";
        String order = "tour_id ASC";
        return dalTour.getList(where, order);
    }

    //Create new tour_id
    public int CreateNewTourID(){
        String where = "tour_id like '%%'";
        String order = "tour_id ASC";
        ArrayList<Tour> list = dalTour.getList(where, order);
        if(list != null && list.size() > 0){
            return list.get(list.size()-1).getTour_id()+1;
        }
        return 1;
    }

    //Create comboboxmodel
    public DefaultComboBoxModel getComboboxTour(){
        ArrayList<Tour> list = null;
        list = getALLTour();
        comboBoxModel = new DefaultComboBoxModel();
        if(list != null && list.size() > 0){
            for(Tour tour : list){
                String s = tour.getTour_id()+"-"+tour.getTour_ten();
                comboBoxModel.addElement(s);
            }
        }
        return comboBoxModel;
    }

    //Add 1 Tour
    public boolean Add1Tour(Tour tour){
        checkInput = new CheckInput();
        boolean bool1 = checkInput.CheckName(tour.getTour_ten());
        if(bool1 == true){
            return dalTour.insert1Tour(tour);
        }
        return false;
    }

    //update 1 tour
    public boolean UpdateTour(Tour tour){
        checkInput = new CheckInput();
        boolean bool1 = checkInput.CheckName(tour.getTour_ten());
        if(bool1 == true){
            return dalTour.update1Tour(tour);
        }
        return false;
    }

    //Delete N tour with loai_id
    public boolean DeleteNTourWithLoaiID(int loai_id){
        return dalTour.deleteNTour(loai_id);
    }

    //Delete 1 tour
    public boolean Delete1Tour(int tour_id){
        BLLChiTiet bllChiTiet = new BLLChiTiet();
        boolean bool1 = bllChiTiet.DeleteNChiTietWithTour_id(tour_id);
        BLLGia bllGia = new BLLGia();
        boolean bool2 = bllGia.DeleteNGiaWithTour_id(tour_id);
        BLLDoan bllDoan = new BLLDoan();
        boolean bool3 = bllDoan.deleteNDoanWithTour_id(tour_id);
        if(bool1 && bool2 && bool3) return dalTour.delete1Tour(tour_id);
        return false;
    }

    //Return list tour with tablemodel
    public  DefaultTableModel getListTourTableModel(ArrayList<Tour> listTour){
        header = new Vector();
        header.add("Mã tour");
        header.add("Tên tour");

        modeltable = new DefaultTableModel(header,0);

        ArrayList<Tour> list = listTour;

        for(Tour tour : list){
            Vector row = new Vector();
            row.add(tour.getTour_id());
            row.add(tour.getTour_ten());
            modeltable.addRow(row);
        }

        return modeltable;
    }

    //Return list tour có giá with tablemodel
    public DefaultTableModel getListTourVSGiaTableModel(ArrayList<Tour> list){
        header = new Vector();
        header.add("Mã tour");
        header.add("Tên tour");
        header.add("Mô tả");
        header.add("Giá (VND)");
        header.add("Từ ngày");

        modeltable = new DefaultTableModel(header, 0);

        for(Tour tour : list){
            bllGia = new BLLGia();
            Gia gia = null;
            gia = bllGia.getGiaNOW(tour.getTour_id());

            Vector row = new Vector();
            row.add(tour.getTour_id());
            row.add(tour.getTour_ten());
            row.add(tour.getTour_mota());
            if(gia != null) {
                row.add(gia.getGia_sotien());
                row.add(gia.getGia_tungay());
            }
            else{
                row.add("Chưa có");
                row.add("Chưa có");
            }

            modeltable.addRow(row);
        }

        return modeltable;
    }

}
