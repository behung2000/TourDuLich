package DAL;

import ENTRY.DiaDiem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DALDiaDiem extends DatabaseManager{
    String name = "tour_diadiem";
    public DALDiaDiem(){
        ConnectionDB();
    }

    //GetList
    public ArrayList<DiaDiem> getList(String where, String order){
        ArrayList<DiaDiem> list = new ArrayList<DiaDiem>();
        DiaDiem diaDiem = new DiaDiem();

        ResultSet resultSet = readTable(name, where, order);

        if(resultSet != null){
            try{
                while(resultSet.next()){
                    diaDiem = new DiaDiem();
                    diaDiem.setDd_id(resultSet.getInt("dd_id"));
                    diaDiem.setDd_thanhpho(resultSet.getString("dd_thanhpho"));
                    diaDiem.setDd_ten(resultSet.getString("dd_ten"));
                    diaDiem.setDd_mota(resultSet.getString("dd_mota"));
                    list.add(diaDiem);
                }
            }
            catch (SQLException e){
                System.out.println("ERROR -> DALDiaDiem -> getList -> "+e.getMessage());
            }
        }

        return list;
    }

    //Add 1 DiaDiem
    public boolean Add1DiaDiem(DiaDiem diadiem){
        String column = "dd_id, dd_thanhpho, dd_ten, dd_mota";
        String values = "'"+diadiem.getDd_id()+"','"+diadiem.getDd_thanhpho()+"','"+diadiem.getDd_ten()+"','"+diadiem.getDd_mota()+"'";
        return insertrow(name, column, values);
    }

    //Update 1 DiaDiem
    public boolean UpdateDiaDiem(DiaDiem diadiem){
        String set = "dd_thanhpho='"+diadiem.getDd_thanhpho()+"', "
                + "dd_ten='"+diadiem.getDd_ten()+"', "
                + "dd_mota='"+diadiem.getDd_mota()+"'";
        String where = "dd_id='"+diadiem.getDd_id()+"'";
        return updaterow(name, set, where);
    }

    //Delete 1 DiaDiem with dd_id
    public boolean DeleteDiaDiem(int dd_id){
        String where = "dd_id='"+dd_id+"'";
        return deleterow(name, where);
    }
}
