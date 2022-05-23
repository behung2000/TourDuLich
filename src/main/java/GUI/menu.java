package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class menu extends JFrame{
    private JPanel JPanel1;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton button7;
    private JButton button8;
    private JButton button9;
    private JButton button10;
    private JButton button11;

    public menu(){
        setTitle("JFrame menu");
        setContentPane(JPanel1);
        setSize(800,500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        //Button
        button1.setText("Quản lý đoàn (thêm sửa xóa đoàn + ds khách và nhân viên 1 đoàn)");
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUIDoan doan = new GUIDoan();
                doan.DSDoan();
            }
        });

        button2.setText("Quản lý Tour (thêm sửa xóa tour + ds địa điểm 1 tour)");
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUITours tour = new GUITours();
                tour.DSDiaDiem();
            }
        });

        button3.setText("Quản lý Tour giá");
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUIGia guiGia = new GUIGia();

            }
        });

        button4.setText("Giá tour hiện tại");
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GiaTourHienTai giaTourHienTai = new GiaTourHienTai();
            }
        });

        button5.setText("Quản lý tour loại");
        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUILoai guiLoai = new GUILoai();
            }
        });

        button6.setText("Quản lý địa điểm");
        button6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUIDiaDiem guiDiaDiem = new GUIDiaDiem();

            }
        });

        button7.setText("Quản lý Nhân viên");
        button7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUINhanVien guiNhanVien = new GUINhanVien();
            }
        });

        button8.setText("Quản lý khách hàng");
        button8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUIKhach guiKhach = new GUIKhach();

            }
        });

        button9.setText("Quản lý tour loại chi phí");
        button9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUILoaiChiPhi guiLoaiChiPhi = new GUILoaiChiPhi();
            }
        });

        button10.setText("Tìm kiếm");
        button10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SearchTT searchTT = new SearchTT();
            }
        });

        button11.setText("Thống kê");
        button11.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrameMess jFrameMess = new JFrameMess();
                jFrameMess.mess("Chú ý", "Phần này chưa làm");
            }
        });

    }

    public static void main(String[] args){
        menu men = new menu();
    }

}
