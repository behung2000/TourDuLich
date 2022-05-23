package GUI;

import BLL.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchTT extends JFrame{
    private JPanel JPanel1;
    private JComboBox comboBox1;
    private JTextField SearchJTextField;
    private JButton backButton;
    private JButton reloadButton;
    private JButton searchButton;
    private JTable table1;


    public SearchTT() {
        setContentPane(JPanel1);
        setSize(900,700);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);

        DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel();
        comboBoxModel.addElement("Khách hàng");
        comboBoxModel.addElement("Nhân viên");
        comboBoxModel.addElement("Đoàn");
        comboBoxModel.addElement("Tour");
        comboBoxModel.addElement("Địa điểm");
        comboBoxModel.addElement("Loại tour");
        comboBoxModel.addElement("Loại chi phí");

        comboBox1.setModel(comboBoxModel);
        GetStringForCombobox("");
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GetStringForCombobox("");
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
                GetStringForCombobox("");
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchName = SearchJTextField.getText();
                if(searchName.length()>0){
                    GetStringForCombobox(searchName);
                }
                else{
                    JFrameMess jFrameMess = new JFrameMess();
                    jFrameMess.mess("Tên search", "Chưa nhập tên để search");
                }
            }
        });


    }

    public void setTTTable(String bool, String name){
        if(bool.equalsIgnoreCase("Khách hàng")){
            BLLKhachHang bllKhachHang = new BLLKhachHang();
            table1.setModel(bllKhachHang.getListKhachHangTableModel(bllKhachHang.getListKhachHangWithName(name)));
        }
        else{
            if(bool.equalsIgnoreCase("Nhân viên")){
                BLLNhanVien bllNhanVien = new BLLNhanVien();
                table1.setModel(bllNhanVien.getListNhanVienTableModel(bllNhanVien.getListNhanVienWithName(name)));
            }
            else {
                if(bool.equalsIgnoreCase("Đoàn")){
                    BLLDoan bllDoan = new BLLDoan();
                    table1.setModel(bllDoan.getListDoanTableModel(bllDoan.getSearchName(name)));
                }
                else{
                    if(bool.equalsIgnoreCase("Tour")){
                        BLLTour bllTour = new BLLTour();
                        table1.setModel(bllTour.getListTourVSGiaTableModel(bllTour.getSearchName(name)));
                    }
                    else{
                        if(bool.equalsIgnoreCase("Địa điểm")){
                            BLLDiaDiem bllDiaDiem = new BLLDiaDiem();
                            table1.setModel(bllDiaDiem.getListTableModelDiaDiem(bllDiaDiem.getListDDWithname(name)));
                        }
                        else {
                            BLLLoai bllLoai = null;
                            if (bool.equalsIgnoreCase("Loại tour")) {
                                bllLoai = new BLLLoai();
                                table1.setModel(bllLoai.getTableModelTourLoai(bllLoai.getListLoaiWithName(name)));
                            }
                            else {
                                BLLLoaiChiPhi bllLoaiChiPhi = new BLLLoaiChiPhi();
                                table1.setModel(bllLoaiChiPhi.getTableModelLoaiCP(bllLoaiChiPhi.getListLoaiCPWithName(name)));
                            }
                        }
                    }
                }
            }
        }
    }

    public void GetStringForCombobox(String name){
        setTTTable(comboBox1.getSelectedItem().toString(), name);
    }

}
