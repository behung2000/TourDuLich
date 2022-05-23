package DAL;

import ENTRY.Loai;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DALLoai extends DatabaseManager{
    String name = "tour_loai";

    public DALLoai() { ConnectionDB();}

    //Get List
    public ArrayList<Loai> getList(String where, String order){
        ArrayList<Loai> list = new ArrayList<Loai>();
        Loai loai;
        ResultSet resultSet = readTable(name ,where, order);
        if(resultSet != null){
            try{
                while(resultSet.next()){
                    loai = new Loai();
                    loai.setLoai_id(resultSet.getInt("loai_id"));
                    loai.setLoai_ten(resultSet.getString("loai_ten"));
                    loai.setLoai_mota(resultSet.getString("loai_mota"));
                    list.add(loai);
                }
            }
            catch (SQLException e){
                System.out.println("ERROR -> DALLoai -> getList -> "+e.getMessage());
            }
        }
        return list;
    }

    //Add loai
    public boolean Add1Loai(Loai loai){
        String column ="loai_id, loai_ten, loai_mota";
        String values ="'"+loai.getLoai_id()+"','"+loai.getLoai_ten()+"','"+loai.getLoai_mota()+"'";
        return insertrow(name, column, values);
    }

    //Update loai
    public boolean Update1Loai(Loai loai){
        String set = "loai_ten='"+loai.getLoai_ten()+"', "
                + "loai_mota='"+loai.getLoai_mota()+"'";
        String where = "loai_id='"+loai.getLoai_id()+"'";
        return  updaterow(name, set, where);
    }

    //Delete loai
    public boolean Delete1Loai(int loai_id){
        String where = "loai_id='"+loai_id+"'";
        return  deleterow(name, where);
    }
}
