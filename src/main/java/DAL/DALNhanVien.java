package DAL;



import ENTRY.NhanVien;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DALNhanVien extends DatabaseManager{
    String name = "tour_nhanvien";
    public DALNhanVien(){ ConnectionDB();}

    //Get List
    public ArrayList<NhanVien> getList(String where, String order){
        ArrayList<NhanVien> list = new ArrayList<NhanVien>();
        NhanVien nhanvien;
        ResultSet resultSet = readTable(name, where, order);
        if(resultSet != null){
            try{
                while(resultSet.next()){
                    nhanvien = new NhanVien();
                    nhanvien.setNv_id(resultSet.getInt("nv_id"));
                    nhanvien.setNv_ten(resultSet.getString("nv_ten"));
                    nhanvien.setNv_sdt(resultSet.getString("nv_sdt"));
                    nhanvien.setNv_ngaysinh(resultSet.getString("nv_ngaysinh"));
                    nhanvien.setNv_email(resultSet.getString("nv_email"));
                    nhanvien.setNv_nhiemvu(resultSet.getString("nv_nhiemvu"));
                    list.add(nhanvien);
                }
            }
            catch (SQLException e){
                System.out.println("ERROR -> DALNhanVien -> getList -> "+e.getMessage());
            }
        }
        return list;
    }

    //Add Nhân viên
    public boolean Add1NV(NhanVien nhanvien){
        String column = "nv_id, nv_ten, nv_sdt, nv_ngaysinh, nv_email, nv_nhiemvu";
        String values = "'"+nhanvien.getNv_id()+"','"
                + nhanvien.getNv_ten()+"','"
                + nhanvien.getNv_sdt()+"','"
                + nhanvien.getNv_ngaysinh()+"','"
                + nhanvien.getNv_email()+"','"
                + nhanvien.getNv_nhiemvu()+"'";
        return insertrow(name, column, values);
    }

    //Update nhân viên
    public boolean Update1NV(NhanVien nhanvien){
        String set = "nv_ten='"+nhanvien.getNv_ten()+"', "
                +"nv_sdt='"+nhanvien.getNv_sdt()+"', "
                +"nv_ngaysinh='"+nhanvien.getNv_ngaysinh()+"', "
                +"nv_email='"+nhanvien.getNv_email()+"', "
                +"nv_nhiemvu='"+nhanvien.getNv_nhiemvu()+"'";
        String where = "nv_id='"+nhanvien.getNv_id()+"'";
        return updaterow(name, set, where);
    }

    //Delete nhân viên
    public boolean Delete1NV(int nv_id){
        String where = "nv_id='"+nv_id+"'";
        return deleterow(name, where);
    }
}
