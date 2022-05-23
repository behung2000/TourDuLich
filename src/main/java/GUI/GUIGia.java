package GUI;

import javax.swing.*;

import BLL.BLLGia;
import BLL.BLLTour;
import ENTRY.Tour;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.StringTokenizer;

public class GUIGia extends JFrame{
    private JPanel JPanel1;
    private JComboBox comboBox1;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton backButton;
    private JButton addButton;
    private JButton reloadButton;
    private JTable table1;
    private JLabel TenTourJLabel;
    private JTextField GiaJTextField;
    private JTextField GiaTuNgayJTextField;
    private JTextField GhiChuJTextField;

    public GUIGia(){
        setContentPane(JPanel1);
        setSize(800,500);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        BLLTour bllTour = new BLLTour();
        setTT();
        comboBox1.setModel(bllTour.getComboboxTour());
        getTourIDForCombobox();
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getTourIDForCombobox();
            }
        });

        //Button
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        reloadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setTT();
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sel = comboBox1.getSelectedItem().toString();
                StringTokenizer stringTokenizer = new StringTokenizer(sel,"-");
                String stour_id = stringTokenizer.nextToken();
                try{
                    int tour_id = Integer.parseInt(stour_id);
                    String sotien = GiaJTextField.getText();
                    String TuNgay = GiaTuNgayJTextField.getText();
                    String GhiChu = GhiChuJTextField.getText();
                    BLLGia bllGia = new BLLGia();
                    boolean bool = bllGia.Add1Gia(tour_id, sotien, TuNgay, GhiChu);
                    JFrameMess jFrameMess = new JFrameMess();
                    if(bool == true){
                        jFrameMess.mess("Giá: ","Thêm thành công !!!");
                    }
                    else{
                        jFrameMess.mess("Giá: ","Thêm không thành công !!!");
                    }
                }
                catch (NumberFormatException ex){
                    JFrameMess jFrameMess = new JFrameMess();
                    jFrameMess.mess("Hệ thống: ","Lỗi !!!");
                }
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(table1.getSelectedRow()>=0) {
                    int gia_id = table1.getModel().getValueAt(table1.getSelectedRow(),0).hashCode();
                    String sel = comboBox1.getSelectedItem().toString();
                    StringTokenizer stringTokenizer = new StringTokenizer(sel, "-");
                    String stour_id = stringTokenizer.nextToken();
                    try {
                        int tour_id = Integer.parseInt(stour_id);
                        String sotien = GiaJTextField.getText();
                        String TuNgay = GiaTuNgayJTextField.getText();
                        String GhiChu = GhiChuJTextField.getText();
                        BLLGia bllGia = new BLLGia();
                        boolean bool = bllGia.Update1Gia(gia_id, tour_id, sotien, TuNgay, GhiChu);
                        JFrameMess jFrameMess = new JFrameMess();
                        if (bool == true) {
                            jFrameMess.mess("Giá: ", "sửa thành công !!!");
                        } else {
                            jFrameMess.mess("Giá: ", "sửa không thành công !!!");
                        }
                    } catch (NumberFormatException ex) {
                        JFrameMess jFrameMess = new JFrameMess();
                        jFrameMess.mess("Hệ thống: ", "Lỗi !!!");
                    }
                }
                else
                {
                    JFrameMess jFrameMess = new JFrameMess();
                    jFrameMess.mess("Giá: ", "Chưa chọn đối tượng để sửa !!!");
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(table1.getSelectedRow()>=0){
                    int gia_id = table1.getModel().getValueAt(table1.getSelectedRow(),0).hashCode();
                    BLLGia bllGia = new BLLGia();
                    boolean bool = bllGia.Delete1GiaWithGia_id(gia_id);
                    JFrameMess jFrameMess = new JFrameMess();
                    if (bool == true) {
                        jFrameMess.mess("Giá: ", "xóa thành công !!!");
                    } else {
                        jFrameMess.mess("Giá: ", "xóa không thành công !!!");
                    }
                }
                else
                {
                    JFrameMess jFrameMess = new JFrameMess();
                    jFrameMess.mess("Giá: ", "Chưa chọn đối tượng để xóa !!!");
                }
                setTT();
            }
        });

        //Table
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(table1.getSelectedRow()>=0){
                    int tour_id = table1.getModel().getValueAt(table1.getSelectedRow(), 1).hashCode();
                    String tour_ten = table1.getModel().getValueAt(table1.getSelectedRow(), 2).toString();
                    String id = tour_id+"-"+tour_ten;
                    System.out.println(id);
                    GiaJTextField.setText(table1.getModel().getValueAt(table1.getSelectedRow(),3).toString());
                    GiaTuNgayJTextField.setText(table1.getModel().getValueAt(table1.getSelectedRow(),4).toString());
                    GhiChuJTextField.setText(table1.getModel().getValueAt(table1.getSelectedRow(), 5).toString());
                }
            }
        });
    }

    public void getTTTour(int tour_id){
        BLLTour blltour = new BLLTour();
        Tour tour = blltour.get1Tour(tour_id);
        if(tour != null){
            TenTourJLabel.setText(tour.getTour_ten());
        }
        else{
            JFrameMess jFrameMess = new JFrameMess();
            jFrameMess.mess("Hệ thống: ","Lỗi không tìm thấy thông tin tour từ tour_id");
        }
    }

    public void getTourIDForCombobox(){
        String sel = comboBox1.getSelectedItem().toString();
        StringTokenizer stringTokenizer = new StringTokenizer(sel,"-");
        String stour_id = stringTokenizer.nextToken();
        try{
            int tour_id = Integer.parseInt(stour_id);
            getTTTour(tour_id);
        }
        catch (NumberFormatException e){
            JFrameMess jFrameMess = new JFrameMess();
            jFrameMess.mess("Hệ thống: ","Lỗi !!!");
        }
    }

    public void setTT(){
        BLLGia bllGia = new BLLGia();
        table1.setModel(bllGia.getTableModelALLListExtends());
    }

}
