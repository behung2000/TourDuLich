package BLL;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.StringTokenizer;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CheckInput {
    public CheckInput(){

    }

    public boolean CheckInputisNumber(String input) {
        try{
            int i = Integer.parseInt(input);
            return true;
        }
        catch (NumberFormatException e){
            return false;
        }
    }

    public boolean CheckInputisDouble(String input) {
        try{
            Double i = Double.parseDouble(input);
            return true;
        }
        catch (NumberFormatException e){
            return false;
        }
    }

    public boolean CheckFormatDate(String date){
        StringTokenizer stringTokenizer = new StringTokenizer(date,"-");
        int i = stringTokenizer.countTokens();
        if(i==3){
            String year = stringTokenizer.nextToken();
            String month = stringTokenizer.nextToken();
            String day = stringTokenizer.nextToken();
            if(CheckInputisNumber(year) && CheckInputisNumber(month) && CheckInputisNumber(day)){

                int yyyy = Integer.parseInt(year);
                int mm = Integer.parseInt(month);
                int dd = Integer.parseInt(day);
                if(dd<1 || dd>31) return false;
                if(mm<1 || mm>12) return false;
                if((mm%2==0) && (mm!=2) && (mm<7) && (dd>30)) return false;
                if((mm%2!=0) && (mm>7) && (dd>30)) return false;
                if((yyyy%4==0) && (mm==2) && (dd>29)) return false;
                if((yyyy%4!=0) && (mm==2) && (dd>28)) return false;
                return true;
            }
        }
        return false;
    }

    public boolean CheckDateNgayDiNgayVeInputADD(String NgayDi, String NgayVe){
        if(CheckFormatDate(NgayDi) && CheckFormatDate(NgayVe)) {
            DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date1 = null;
            Date date2 = null;
            try {
                date1 = sdf.parse(NgayDi);
                date2 = sdf.parse(NgayVe);
                if (date2.after(date1)) return true;
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean CheckName(String name){
        if(name.equalsIgnoreCase("") || name.equalsIgnoreCase("null")) return false;
        return true;
    }

    public boolean CheckSDT(String sdt){
        if(sdt.length()>=10 && sdt.length()<=11){
            return CheckInputisNumber(sdt);
        }
        return false;
    }

    public boolean CheckEmail(String email){
        String bool = email.replace("@","");
        if((bool.length()+1)==email.length()) return true;
        return false;
    }

    public boolean CheckCMND(String cmnd){
        if(cmnd.length()==9 || cmnd.length()==12){
            //System.out.println(CheckInputisDouble(cmnd));
            return CheckInputisDouble(cmnd);
        }
        return false;
    }

    //Check UPdate
    public boolean CheckDS(String ds, String id){
        String sbool = ds.replace(id, "");
        if(ds.length()==sbool.length()) return true;
        else return false;
    }

    /*
    public static void main(String[] args){
        CheckInput checkInput = new CheckInput();
        System.out.println("1"+checkInput.CheckCMND("079200008ads"));
    }
     */
}
