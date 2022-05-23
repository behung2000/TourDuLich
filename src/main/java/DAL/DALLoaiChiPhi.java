package DAL;

import ENTRY.LoaiChiPhi;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DALLoaiChiPhi extends DatabaseManager{
    String name="tour_loaichiphi";

    public DALLoaiChiPhi(){ ConnectionDB();}

    //Get List
    public ArrayList<LoaiChiPhi> getList(String where, String order){
        ArrayList<LoaiChiPhi> list = new ArrayList<LoaiChiPhi>();
        LoaiChiPhi loaiChiPhi;
        ResultSet resultSet = readTable(name, where, order);
        if(resultSet != null){
            try{
                while(resultSet.next()){
                    loaiChiPhi = new LoaiChiPhi();
                    loaiChiPhi.setCp_id(resultSet.getInt("cp_id"));
                    loaiChiPhi.setCp_ten(resultSet.getString("cp_ten"));
                    loaiChiPhi.setCp_mota(resultSet.getString("cp_mota"));
                    list.add(loaiChiPhi);
                }
            }
            catch (SQLException e){
                System.out.println("ERROR -> DALLoai -> getList -> "+e.getMessage());
            }
        }
        return list;
    }

    //Add 1 Loại chi phí
    public boolean Add1Loai(LoaiChiPhi loaiChiPhi){
        String column ="cp_id, cp_ten, cp_mota";
        String values ="'"+loaiChiPhi.getCp_id()+"','"+loaiChiPhi.getCp_ten()+"','"+loaiChiPhi.getCp_mota()+"'";
        return insertrow(name, column, values);
    }

    //Update loại chi phí
    public boolean Update1Loai(LoaiChiPhi loaiChiPhi){
        String set = "cp_ten='"+loaiChiPhi.getCp_ten()+"', "
                + "cp_mota='"+loaiChiPhi.getCp_mota()+"'";
        String where = "cp_id='"+loaiChiPhi.getCp_id()+"'";
        return  updaterow(name, set, where);
    }

    //Delete loại chi phí
    public boolean Delete1Loai(int cp_id){
        String where = "cp_id='"+cp_id+"'";
        return  deleterow(name, where);
    }

}
