package DAL;

import ENTRY.NguoiDi;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DALNguoiDi extends DatabaseManager{
    String name = "tour_nguoidi";
    public  DALNguoiDi(){
        ConnectionDB();
    }

    //Get List
    public ArrayList<NguoiDi> getList(String where, String order){
        ArrayList<NguoiDi> list = new ArrayList<NguoiDi>();
        NguoiDi nguoidi;
        ResultSet resultset = readTable(name, where, order);
        if(resultset != null){
            try{
                while(resultset.next()){
                    nguoidi = new NguoiDi();
                    nguoidi.setNguoidi_id(resultset.getInt("nguoidi_id"));
                    nguoidi.setDoan_id(resultset.getInt("doan_id"));
                    nguoidi.setNguoidi_dsnhanvien(resultset.getString("nguoidi_dsnhanvien"));
                    nguoidi.setNguoidi_dskhachhang(resultset.getString("nguoidi_dskhach"));
                    list.add(nguoidi);
                }
            }
            catch (SQLException e){
                System.out.println("ERROR -> DALNguoiDi -> getList -> "+e.getMessage());
            }
        }
        return list;
    }

    //Add NguoiDi
    public void addNguoiDi(NguoiDi nguoiDi){
        String column = "nguoidi_id, doan_id, nguoidi_dsnhanvien, nguoidi_dskhach";
        String values = "'"+nguoiDi.getNguoidi_id()+"', '"+nguoiDi.getDoan_id()+"', '"+nguoiDi.getNguoidi_dsnhanvien()+"', '"+nguoiDi.getNguoidi_dskhachhang()+"'";
        boolean bool = insertrow(name, column, values);
        if(!bool){
            System.out.println("ERROR -> DALNguoiDi -> addNguoiDi ");
        }
    }

    //Update NguoiDi
    public void updateNguoiDi(NguoiDi nguoiDi){
        String set = "nguoidi_dsnhanvien='"+nguoiDi.getNguoidi_dsnhanvien()+"', "
                + "nguoidi_dskhach='"+nguoiDi.getNguoidi_dskhachhang()+"'";
        String where = "doan_id='"+nguoiDi.getDoan_id()+"'";
        boolean bool = updaterow(name, set, where);
        if(!bool){
            System.out.println("ERROR -> DALNguoiDi -> UpdateNguoiDi ");
        }
    }

    //Delete NguoiDi
    public void deleteNguoiDi(int doan_id){
        String where = "doan_id='"+doan_id+"'";
        boolean bool = deleterow(name, where);
        if(!bool){
            System.out.println("ERROR -> DALNguoiDi -> DeleteNguoiDi ");
        }
    }
}
