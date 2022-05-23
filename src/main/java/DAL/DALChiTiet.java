package DAL;

import ENTRY.ChiTiet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DALChiTiet extends DatabaseManager{
    String name = "tour_chitiet";
    public DALChiTiet(){
        ConnectionDB();
    }

    //GetList
    public ArrayList<ChiTiet> getList(String where, String order){
        ArrayList<ChiTiet> list = new ArrayList<ChiTiet>();
        ChiTiet chiTiet;

        ResultSet resultSet = readTable(name, where, order);

        if(resultSet != null){
            try{
                while(resultSet.next()){
                    chiTiet = new ChiTiet();
                    chiTiet.setCt_id(resultSet.getInt("ct_id"));
                    chiTiet.setTour_id(resultSet.getInt("tour_id"));
                    chiTiet.setDd_id(resultSet.getInt("dd_id"));
                    chiTiet.setCt_thutu(resultSet.getInt("ct_thutu"));
                    list.add(chiTiet);
                }
            }
            catch (SQLException e){
                System.out.println("ERROR -> DALChiTiet -> getList -> "+e.getMessage());
            }
        }

        return list;
    }

    //Insert 1 chitiet
    public boolean insert1ChiTiet(ChiTiet chitiet){
        String column = "ct_id, tour_id, dd_id, ct_thutu";
        String values = "'"+chitiet.getCt_id()+"','"+chitiet.getTour_id()+"','"+chitiet.getDd_id()+"','"+chitiet.getCt_thutu()+"'";
        boolean bool = insertrow(name, column, values);
        return bool;
    }

    //Delete 1 chitiet with tour_id, dd_id, ct_thutu
    public boolean deleteChiTietWithtourid_ddid_ctthutu(int tour_id, int dd_id, int ct_thutu) {
        String where = "tour_id='"+tour_id+"' and dd_id='"+dd_id+"' and ct_thutu='"+ct_thutu+"'";
        boolean bool = deleterow(name, where);
        return bool;
    }

    //Delete n chitiet with tour_id
    public boolean deleteNChiTietWithTour_id(int tour_id) {
        String where = "tour_id='"+tour_id+"'";
        boolean bool = deleterow(name, where);
        return bool;
    }
}
