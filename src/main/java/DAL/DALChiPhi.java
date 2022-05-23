package DAL;

import ENTRY.ChiPhi;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DALChiPhi extends DatabaseManager{
    String name = "tour_chiphi";
    public DALChiPhi(){ ConnectionDB();}

    //Get ALL List
    public ArrayList<ChiPhi> getList(String where, String order){
        ArrayList<ChiPhi> list = new ArrayList<ChiPhi>();
        ChiPhi chiphi;
        ResultSet resultSet = readTable(name, where, order);
        if(resultSet != null){
            try{
                while(resultSet.next()){
                    chiphi = new ChiPhi();
                    chiphi.setChiphi_id(resultSet.getInt("chiphi_id"));
                    chiphi.setDoan_id(resultSet.getInt("doan_id"));
                    chiphi.setChiphi_total(resultSet.getDouble("chiphi_total"));
                    chiphi.setChiphi_chitiet(resultSet.getString("chiphi_chitiet"));
                    list.add(chiphi);
                }
            }
            catch (SQLException e){
                System.out.println("ERROR -> DALTourChiPhi -> getList -> "+e.getMessage());
            }
        }
        return list;
    }

    //Add Tour_ChiPhi
    public void addTour_ChiPhi(ChiPhi chiphi){
        String columnn = "chiphi_id, doan_id, chiphi_total, chiphi_chitiet";
        String values = "'"+chiphi.getChiphi_id()+"', '"+chiphi.getDoan_id()+"', '"+chiphi.getChiphi_total()+"', '"+chiphi.getChiphi_chitiet()+"'";
        boolean bool = insertrow(name, columnn, values);
        if(!bool){
            System.out.println("ERROR -> DALTourChiPhi -> addTour_ChiPhi ");
        }
    }

    //UPdate Tour_chiphi
    public void updateTour_ChiPhi(ChiPhi chiphi){
        String set = "chiphi_total='"+chiphi.getChiphi_total()+"', "
                + "chiphi_chitiet='"+chiphi.getChiphi_chitiet()+"'";
        String where = "doan_id='"+chiphi.getDoan_id()+"'";
        boolean bool = updaterow(name, set, where);
        if(!bool){
            System.out.println("ERROR -> DALTourChiPhi -> updateTour_ChiPhi ");
        }
    }

    //Delete Tour_chiphi
    public void deleteChiPhi(int doan_id){
        String where = "doan_id='"+doan_id+"'";
        boolean bool = deleterow(name, where);
        if(!bool){
            System.out.println("ERROR -> DALTourChiPhi -> deleteTour_chiphi ");
        }
    }
}
