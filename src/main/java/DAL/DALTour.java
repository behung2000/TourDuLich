package DAL;

import ENTRY.Tour;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DALTour extends DatabaseManager{
    String name = "tours";
    public DALTour() {
        ConnectionDB();
    }


    //Get list
    public ArrayList<Tour> getList(String where, String order) {
        ArrayList<Tour> list = new ArrayList<Tour>();
        Tour tour;
        ResultSet resultSet = readTable(name, where, order);
        if(resultSet != null){
            try{
                while(resultSet.next()){
                    tour = new Tour();
                    tour.setTour_id(resultSet.getInt("tour_id"));
                    tour.setTour_ten(resultSet.getString("tour_ten"));
                    tour.setTour_mota(resultSet.getString("tour_mota"));
                    tour.setLoai_id(resultSet.getInt("loai_id"));
                    list.add(tour);
                }
            }
            catch (SQLException e){
                System.out.println("ERROR -> DALTour -> getList -> "+e.getMessage());
            }
        }
        return list;
    }

    //Insert 1 tour
    public boolean insert1Tour(Tour tour){
        String column = "tour_id, tour_ten, tour_mota, loai_id";
        String values = "'"+tour.getTour_id()+"','"+tour.getTour_ten()+"','"+tour.getTour_mota()+"','"+tour.getLoai_id()+"'";
        boolean bool = insertrow(name, column, values);
        return bool;
    }

    //Update 1 tour
    public boolean update1Tour(Tour tour){
        String set = "tour_ten='"+tour.getTour_ten()+"', "
                + "tour_mota='"+tour.getTour_mota()+"', "
                + "loai_id='"+tour.getLoai_id()+"'";
        String where = "tour_id='"+tour.getTour_id()+"'";
        boolean bool = updaterow(name, set, where);
        return bool;
    }

    //Delete n tour with loai_id
    public boolean deleteNTour(int loai_id){
        String where = "loai_id='"+loai_id+"'";
        return  deleterow(name, where);
    }

    //Delete 1 tour with tour_id
    public boolean delete1Tour(int tour_id){
        String where = "tour_id='"+tour_id+"'";
        return  deleterow(name, where);
    }
}
