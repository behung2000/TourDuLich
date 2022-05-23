package DAL;

import ENTRY.Doan;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DALDoan extends DatabaseManager{
    String name = "tour_doan";

    public DALDoan(){
        ConnectionDB();
    }

    //Get List
    public ArrayList<Doan> getList(String where, String order){
        ArrayList<Doan> list = new ArrayList<Doan>();
        Doan doan;
        ResultSet resultSet = readTable(name, where, order);
        if(resultSet != null){
            try {
                while (resultSet.next()){
                    doan = new Doan();
                    doan.setDoan_id(resultSet.getInt("doan_id"));
                    doan.setTour_id(resultSet.getInt("tour_id"));
                    doan.setDoan_name(resultSet.getString("doan_name"));
                    doan.setDoan_ngaydi(resultSet.getString("doan_ngaydi"));
                    doan.setDoan_ngayve(resultSet.getString("doan_ngayve"));
                    doan.setGia_id(resultSet.getInt("gia_id"));
                    doan.setDoan_chitietchuongtrinh(resultSet.getString("doan_chitietchuongtrinh"));
                    list.add(doan);
                }
            }
            catch (SQLException e){
                System.out.println("ERROR -> DALDoan -> getList -> "+e.getMessage());
            }
        }
        return list;
    }

    //Add 1 Doan
    public boolean AddDoan(Doan doan){
        String column = "doan_id, tour_id, doan_name, doan_ngaydi, doan_ngayve, gia_id, doan_chitietchuongtrinh";
        String values = "'"+doan.getDoan_id()+"','"+doan.getTour_id()+"','"+doan.getDoan_name()+"','"+doan.getDoan_ngaydi()+"','"+doan.getDoan_ngayve()+"','"+doan.getGia_id()+"','"+doan.getDoan_chitietchuongtrinh()+"'";
        boolean bool = insertrow(name, column, values);
        return bool;
    }

    //Update 1 Doan
    public boolean updateDoan(Doan doan){
        String set = "tour_id='"+doan.getTour_id()+"', "
                    + "doan_name='"+doan.getDoan_name()+"', "
                    + "doan_ngaydi='"+doan.getDoan_ngaydi()+"', "
                    + "doan_ngayve='"+doan.getDoan_ngayve()+"', "
                    + "gia_id='"+doan.getGia_id()+"', "
                    + "doan_chitietchuongtrinh='"+doan.getDoan_chitietchuongtrinh()+"'";
        String where = "doan_id='"+doan.getDoan_id()+"'";
        boolean bool = updaterow(name,set,where);
        return bool;
    }

    //Delete 1 Doan
    public boolean deleteDoan(int doan_id){
        String where = "doan_id='"+doan_id+"'";
        boolean bool = deleterow(name,where);
        return bool;
    }

    //Test
    /*
    public static void main(String[] args){
        //SELECT * FROM tour_doan WHERE doan_id like '%%' ORDER BY doan_id ASC;
        /*
        ConnectionDB();
        ArrayList<Doan> list = new ArrayList<Doan>();
        list=getList("doan_id like '%%'", "doan_id ASC");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getDoan_id());
        }


    }
    */

}
