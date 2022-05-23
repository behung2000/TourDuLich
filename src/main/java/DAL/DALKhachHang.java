package DAL;

import ENTRY.KhachHang;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DALKhachHang extends DatabaseManager{
    String name = "tour_khachhang";
    public DALKhachHang(){
        ConnectionDB();
    }

    //Get List
    public ArrayList<KhachHang> getList(String where, String order){
        ArrayList<KhachHang> list = new ArrayList<KhachHang>();
        KhachHang khachhang;
        ResultSet resultSet = readTable(name, where, order);
        if(resultSet != null){
            try{
                while(resultSet.next()){
                    khachhang = new KhachHang();
                    khachhang.setKh_id(resultSet.getInt("kh_id"));
                    khachhang.setKh_ten(resultSet.getString("kh_ten"));
                    khachhang.setKh_sdt(resultSet.getString("kh_sdt"));
                    khachhang.setKh_ngaysinh(resultSet.getString("kh_ngaysinh"));
                    khachhang.setKh_email(resultSet.getString("kh_email"));
                    khachhang.setKh_cmnd(resultSet.getString("kh_cmnd"));
                    list.add(khachhang);
                }
            }
            catch (SQLException e){
                System.out.println("ERROR -> DALKhachHang -> getList -> "+e.getMessage());
            }
        }
        return list;
    }

    //Add Khách hàng
    public boolean Add1KH(KhachHang khachhang){
        String column = "kh_id, kh_ten, kh_sdt, kh_ngaysinh, kh_email, kh_cmnd";
        String values = "'"+khachhang.getKh_id()+"','"
                + khachhang.getKh_ten()+"','"
                + khachhang.getKh_sdt()+"','"
                + khachhang.getKh_ngaysinh()+"','"
                + khachhang.getKh_email()+"','"
                + khachhang.getKh_cmnd()+"'";
        return insertrow(name, column, values);
    }

    //Update khách hàng
    public boolean Update1KH(KhachHang khachhang){
        String set = "kh_ten='"+khachhang.getKh_ten()+"', "
                +"kh_sdt='"+khachhang.getKh_sdt()+"', "
                +"kh_ngaysinh='"+khachhang.getKh_ngaysinh()+"', "
                +"kh_email='"+khachhang.getKh_email()+"', "
                +"kh_cmnd='"+khachhang.getKh_cmnd()+"'";
        String where = "kh_id='"+khachhang.getKh_id()+"'";
        return updaterow(name, set, where);
    }

    //Delete nhân viên
    public boolean Delete1KH(int kh_id){
        String where = "kh_id='"+kh_id+"'";
        return deleterow(name, where);
    }

}
