package GUI;

import BLL.BLLDoan;
import BLL.BLLGia;
import BLL.BLLTour;
import ENTRY.Doan;
import ENTRY.Gia;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.util.StringTokenizer;

public class TTChiTietDoan extends JFrame{
    private JLabel TieuDeJLabel;
    private JPanel JPanel1;
    private JLabel MaJLabel;
    private JLabel TenJabel;
    private JLabel TenLoaiJLabel;
    private JLabel NgayDiJLabel;
    private JLabel NgayVeJLabel;
    private JLabel GiaTourJLabel;
    private JTextField TenTextField;
    private JLabel Ma1JLabel;
    private JComboBox comboBox1;
    private JLabel GiaTour1JLabel;
    private JTextField NgayDiTextField;
    private JTextField NgayVeTextField;
    private JButton button1;
    private JTextField MoTatextField;

    private BLLDoan bllDoan;
    private BLLTour bllTour;

    public TTChiTietDoan(){
        setContentPane(JPanel1);
        setSize(700,700);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        //Cho lựa chọn vào combobox
        bllTour = new BLLTour();
        comboBox1.setModel(bllTour.getComboboxTour());
        getGia();
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getGia();
            }
        });
    }

    //Lấy gia theo tour
    public void getGia(){
        Gia gia = getGia(gettour_id());
        GiaTour1JLabel.setText(gia.getGia_sotien()+"");
    }

    //Lấy tour_id từ combobox
    public int gettour_id(){
        String s = comboBox1.getSelectedItem().toString();
        StringTokenizer stringTokenizer = new StringTokenizer(s,"-");
        String intS = stringTokenizer.nextToken();
        int i = 0;
        try{
            i = Integer.parseInt(intS);
        }
        catch (NumberFormatException e){
            System.out.println("GUI -> TTChiTietDoan -> gettour_id -> "+e.getMessage());
        }
        return i;
    }

    //Lấy giá từ tour_id
    public Gia getGia(int tour_id){
        BLLGia bllGia = new BLLGia();
        Gia gia = null;
        gia = bllGia.getGiaNOW(tour_id);
        return gia;
    }

    //ADD GUI
    public void ADDDoan(){
        bllDoan = new BLLDoan();
        //Khởi tạo id đoàn
        int doan_id = bllDoan.CreateDoan_id();
        Ma1JLabel.setText(""+doan_id);
        button1.setText("ADD");
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Doan doan = new Doan();
                doan.setDoan_id(doan_id);
                doan.setDoan_name(TenTextField.getText());
                doan.setTour_id(gettour_id());
                doan.setDoan_ngaydi(NgayDiTextField.getText());
                doan.setDoan_ngayve(NgayVeTextField.getText());
                Gia gia = getGia(gettour_id());
                doan.setGia_id(gia.getGia_id());
                doan.setDoan_chitietchuongtrinh(MoTatextField.getText().toString());

                //Check input
                boolean bool = bllDoan.CheckTTaddDoan(doan);
                JFrameMess jFrameMess = new JFrameMess();
                if(bool==true) {
                    jFrameMess.mess("Add Doan","thành công");
                }
                else {
                    jFrameMess.mess("Add Doan","không thành công");
                }
                setVisible(false);
            }
        });
    }

    //UPDATE GUI

    public void UPDATEDoan(int id){
        bllDoan = new BLLDoan();
        Doan doan = bllDoan.get1Doan(id);
        if(doan != null) {
            Ma1JLabel.setText(doan.getDoan_id() + "");
            TenTextField.setText(doan.getDoan_name());
            //Set tour id
            int n = comboBox1.getItemCount();
            for (int i = 0; i < n; i++) {
                String tmp = comboBox1.getItemAt(i).toString();
                //System.out.println(tmp);
                String tour_id = ""+doan.getTour_id();
                StringTokenizer stringTokenizer = new StringTokenizer(tmp,"-");
                if(tour_id.equalsIgnoreCase(stringTokenizer.nextToken())){
                    //System.out.println("true");
                    comboBox1.setSelectedIndex(i);
                    break;
                }
            }
            NgayDiTextField.setText(doan.getDoan_ngaydi());
            NgayVeTextField.setText(doan.getDoan_ngayve());
            //Gia
            getGia();
            MoTatextField.setText(doan.getDoan_chitietchuongtrinh());
            button1.setText("Update");

            button1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Doan doanupdate = new Doan();
                    doanupdate.setDoan_id(doan.getDoan_id());
                    doanupdate.setDoan_name(TenTextField.getText());
                    doanupdate.setTour_id(gettour_id());
                    doanupdate.setDoan_ngaydi(NgayDiTextField.getText());
                    doanupdate.setDoan_ngayve(NgayVeTextField.getText());
                    Gia gia = getGia(gettour_id());
                    doanupdate.setGia_id(gia.getGia_id());
                    doanupdate.setDoan_chitietchuongtrinh(MoTatextField.getText().toString());

                    //Check input
                    boolean bool = bllDoan.CheckTTupdateDoan(doanupdate);
                    JFrameMess jFrameMess = new JFrameMess();
                    if(bool==true) {
                        jFrameMess.mess("UPDATE Doan","thành công");
                    }
                    else {
                        jFrameMess.mess("UPDATE Doan","không thành công");
                    }
                    setVisible(false);
                }
            });
        }
    }


}
