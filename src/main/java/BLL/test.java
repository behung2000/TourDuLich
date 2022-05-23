package BLL;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

public class test {
    public static void main(String[] args) {
        /*
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String nowdate = dateFormat.format(date);
        System.out.println(nowdate);
        String date1 = "2021-12-04";
        //System.out.println("so sanh: "+(date1>nowdate));
         */

        /*
        Date datenow = new Date();
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = null;
        try {
            date1 = sdf.parse("2021-11-03");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(date1.after(datenow));
         */

        //Xử lý chuỗi
        String t= "-1--11--9--8-";
        System.out.println(t);
        StringTokenizer stringTokenizer = new StringTokenizer(t,"-");
        System.out.println(stringTokenizer.countTokens());
        while(stringTokenizer.hasMoreTokens()){
            System.out.println(stringTokenizer.nextToken());
        }
        System.out.println();
        String t1 = t.replace("-12-","");
        System.out.println(t1);
        stringTokenizer = new StringTokenizer(t1,"-");
        System.out.println(stringTokenizer.countTokens());
        while(stringTokenizer.hasMoreTokens()){
            System.out.println(stringTokenizer.nextToken());
        }
    }
}
