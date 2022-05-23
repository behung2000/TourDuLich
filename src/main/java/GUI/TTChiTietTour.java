package GUI;

import BLL.BLLLoai;
import BLL.BLLTour;
import ENTRY.Loai;
import ENTRY.Tour;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TTChiTietTour extends JFrame{
    private JPanel JPanel1;
    private JButton button1;
    private JComboBox comboBox1;
    private JTextField TenTourJTextField;
    private JTextField MoTaTourJTextField;
    private JLabel MaTourJLabel;
    private JLabel TenLoaiJLabel;
    private JLabel MoTaLoaiJLabel;

    public TTChiTietTour(){
        setContentPane(JPanel1);
        setSize(700,700);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        //Cho lựa chọn vào combobox
        BLLLoai bllloai = new BLLLoai();
        comboBox1.setModel(bllloai.getComboboxLoai());
        getTTLoai();
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getTTLoai();
            }
        });
    }

    public void getTTLoai(){
        String sloai_id = comboBox1.getSelectedItem().toString();
        try{
            int loai_id = Integer.parseInt(sloai_id);
            BLLLoai bllLoai = new BLLLoai();
            Loai loai = bllLoai.get1Loai(loai_id);
            TenLoaiJLabel.setText(loai.getLoai_ten());
            MoTaLoaiJLabel.setText(loai.getLoai_mota());
        }
        catch (NumberFormatException e){
            JFrameMess jFrameMess = new JFrameMess();
            jFrameMess.mess("Hệ thống: ","Lỗi !!!");
        }
    }

    public void AddTour(){
        BLLTour bllTour = new BLLTour();
        int tour_id = bllTour.CreateNewTourID();
        MaTourJLabel.setText(tour_id+"");
        button1.setText("ADD");
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Tour tour = new Tour();
                    tour.setTour_id(tour_id);
                    tour.setTour_ten(TenTourJTextField.getText());
                    tour.setTour_mota(MoTaTourJTextField.getText());
                    String sloai_id = comboBox1.getSelectedItem().toString();
                    int loai_id = Integer.parseInt(sloai_id);
                    tour.setLoai_id(loai_id);
                    boolean bool = bllTour.Add1Tour(tour);
                    JFrameMess jFrameMess = new JFrameMess();
                    if(bool == true){
                        jFrameMess.mess("Tour: ","thêm tour thành công !!!");
                    }
                    else {
                        jFrameMess.mess("Hệ thống: ","thêm tour không thành công !!!");
                    }
                }
                catch (NumberFormatException ex){
                    JFrameMess jFrameMess = new JFrameMess();
                    jFrameMess.mess("Hệ thống: ","Lỗi !!!");
                }
                setVisible(false);
            }
        });
    }

    public void updateTour(int tour_id){
        BLLTour bllTour = new BLLTour();
        Tour tour = bllTour.get1Tour(tour_id);
        MaTourJLabel.setText(tour.getTour_id()+"");
        TenTourJTextField.setText(tour.getTour_ten());
        MoTaTourJTextField.setText(tour.getTour_mota());
        comboBox1.setSelectedItem(tour.getLoai_id());
        button1.setText("UPDATE");
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Tour updatetour = new Tour();
                    updatetour.setTour_id(tour_id);
                    updatetour.setTour_ten(TenTourJTextField.getText());
                    updatetour.setTour_mota(MoTaTourJTextField.getText());
                    String sloai_id = comboBox1.getSelectedItem().toString();
                    int loai_id = Integer.parseInt(sloai_id);
                    updatetour.setLoai_id(loai_id);
                    boolean bool = bllTour.UpdateTour(updatetour);
                    JFrameMess jFrameMess = new JFrameMess();
                    if(bool == true){
                        jFrameMess.mess("Tour: ","cập nhật tour thành công !!!");
                    }
                    else {
                        jFrameMess.mess("Hệ thống: ","cập nhật tour không thành công !!!");
                    }
                }
                catch (NumberFormatException ex){
                    JFrameMess jFrameMess = new JFrameMess();
                    jFrameMess.mess("Hệ thống: ","Lỗi !!!");
                }
                setVisible(false);
            }
        });
    }

}
