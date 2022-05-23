package DAL;


import ENTRY.Gia;
import ENTRY.Tour;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DALGia extends DatabaseManager{
    String name = "tour_gia";
    String select = "g.gia_id, g.tour_id, t.tour_ten, g.gia_sotien, g.gia_tungay, g.ghichu";
    String nameextends = "tour_gia as g, tours as t";
    public DALGia(){ ConnectionDB();}

    //Get list
    public ArrayList<Gia> getList(String where, String order) {
        ArrayList<Gia> list = new ArrayList<Gia>();
        Gia gia;
        ResultSet resultSet = readTable(name, where, order);
        if(resultSet != null){
            try{
                while(resultSet.next()){
                    gia = new Gia();
                    gia.setGia_id(resultSet.getInt("gia_id"));
                    gia.setGia_sotien(resultSet.getInt("gia_sotien"));
                    gia.setTour_id(resultSet.getInt("tour_id"));
                    gia.setGia_tungay(resultSet.getString("gia_tungay"));
                    gia.setGhichu(resultSet.getString("ghichu"));
                    list.add(gia);
                }
            }
            catch (SQLException e){
                System.out.println("ERROR -> DALTour -> getList -> "+e.getMessage());
            }
        }
        return list;
    }

    //Get List extends
    public ArrayList<Tour> getListExtends(String where1, String order){
        String where = "g.tour_id = t.tour_id AND "+where1;
        ArrayList<Tour> list = new ArrayList<Tour>();
        Tour tour;
        ResultSet resultSet = readTableExtended(select, nameextends, where, order);
        if(resultSet != null){
            try{
                while(resultSet.next()){
                    tour = new Tour();
                    tour.setGia_id(resultSet.getInt("gia_id"));
                    tour.setTour_id(resultSet.getInt("tour_id"));
                    tour.setTour_ten(resultSet.getString("tour_ten"));
                    tour.setGia_sotien(resultSet.getDouble("gia_sotien"));
                    tour.setGia_tungay(resultSet.getString("gia_tungay"));
                    tour.setGhichu(resultSet.getString("ghichu"));
                    list.add(tour);
                }
            }
            catch (SQLException e){
                System.out.println("ERROR -> DALTour -> getListExtends -> "+e.getMessage());
            }
        }
        return list;
    }

    //Add 1 gia
    public boolean add1Gia(Gia gia){
        String column = "gia_id, gia_sotien, tour_id, gia_tungay, ghichu";
        String values = "'"+gia.getGia_id()+"','"+gia.getGia_sotien()+"','"+gia.getTour_id()+"','"+gia.getGia_tungay()+"','"+gia.getGhichu()+"'";
        return insertrow(name, column, values);
    }

    //Update 1 gia
    public boolean update1Gia(Gia gia){
        String set = "gia_sotien='"+gia.getGia_sotien()+"', "
                + "tour_id='"+gia.getTour_id()+"', "
                + "gia_tungay='"+gia.getGia_tungay()+"', "
                + "ghichu='"+gia.getGhichu()+"'";
        String where = "gia_id='"+gia.getGia_id()+"'";
        return updaterow(name, set, where);
    }

    //Delete with gia_id
    public boolean delete1GiaWithGia_id(int gia_id){
        String where = "gia_id='"+gia_id+"'";
        return deleterow(name, where);
    }

    //Delete n chitiet with tour_id
    public boolean deleteNGiaWithTour_id(int tour_id) {
        String where = "tour_id='"+tour_id+"'";
        boolean bool = deleterow(name, where);
        return bool;
    }

}
