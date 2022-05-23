package GUI;

import BLL.BLLKhachHang;
import BLL.BLLNguoiDi;
import BLL.BLLNhanVien;
import ENTRY.KhachHang;
import ENTRY.NguoiDi;
import ENTRY.NhanVien;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddKHorNVToDoan extends JFrame{
    private JPanel JPanel1;
    private JLabel TieuDeJLabel;
    private JButton Add;
    private JLabel TieuDeCMNDorNVJLabel;
    private JComboBox combobox1;
    private JLabel tenJLabel;
    private JLabel SDTJLabel;
    private JLabel NSJLabel;
    private JLabel EmailJLabel;
    private JLabel CMNDorNVJLabel;

    BLLKhachHang bllKhachHang;
    BLLNhanVien bllNhanVien;

    public AddKHorNVToDoan(){
        setContentPane(JPanel1);
        setSize(700,700);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public void AddKHToDoan(int Doan_id){
        TieuDeJLabel.setText("Khách hàng");
        bllKhachHang = new BLLKhachHang();
        TieuDeCMNDorNVJLabel.setText("CMND");
        combobox1.setModel(bllKhachHang.getComboBoxIDKH());
        getKHIDForCombobox();
        combobox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getKHIDForCombobox();
            }
        });

        Add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String skh_id = combobox1.getSelectedItem().toString();
                try {
                    int kh_id = Integer.parseInt(skh_id);
                    BLLNguoiDi bllNguoiDi = new BLLNguoiDi();
                    boolean bool = bllNguoiDi.updateDSKhachAdd1KH(Doan_id, kh_id);
                    JFrameMess jFrameMess = new JFrameMess();
                    if(bool == true){
                        jFrameMess.mess("Khách hàng","Thêm khách hàng vào đoàn thành công !!!");
                    }
                    else{
                        jFrameMess.mess("Khách hàng","Thêm khách hàng vào đoàn không thành công !!!");
                    }
                }
                catch (NumberFormatException ex){
                    JFrameMess jFrameMess = new JFrameMess();
                    jFrameMess.mess("Khách hàng","lỗi hệ thống !!!");
                }
            }
        });
    }

    public void getTT1KhachHang(int kh_id){
        bllKhachHang = new BLLKhachHang();
        KhachHang khachHang = bllKhachHang.get1KhachHang(kh_id);
        if(khachHang != null){
            tenJLabel.setText(khachHang.getKh_ten());
            SDTJLabel.setText(khachHang.getKh_sdt());
            NSJLabel.setText(khachHang.getKh_ngaysinh());
            EmailJLabel.setText(khachHang.getKh_email());
            CMNDorNVJLabel.setText(khachHang.getKh_cmnd());
        }
        else {
            JFrameMess jFrameMess = new JFrameMess();
            jFrameMess.mess("Khách hàng","Không tìm thấy thông tin !!!");
        }
    }

    public void getKHIDForCombobox(){
        String kh_id = combobox1.getSelectedItem().toString();
        try{
            int id = Integer.parseInt(kh_id);
            getTT1KhachHang(id);
        }
        catch (NumberFormatException e){
            JFrameMess jFrameMess = new JFrameMess();
            jFrameMess.mess("Error","KH_id combobox xảy ra lỗi !!!");
        }
    }

    public void AddNVToDoan(int doan_id){
        TieuDeJLabel.setText("Nhân viên");
        bllNhanVien = new BLLNhanVien();
        TieuDeCMNDorNVJLabel.setText("Nhiệm vụ");
        combobox1.setModel(bllNhanVien.getComboboxIDNV());
        getNVIDForCombobox();
        combobox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getNVIDForCombobox();
            }
        });

        Add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String snv_id = combobox1.getSelectedItem().toString();
                try {
                    int nv_id = Integer.parseInt(snv_id);
                    BLLNguoiDi bllNguoiDi = new BLLNguoiDi();
                    boolean bool = bllNguoiDi.updateDSNhanVienAdd1NV(doan_id, nv_id);
                    JFrameMess jFrameMess = new JFrameMess();
                    if(bool == true){
                        jFrameMess.mess("Nhân viên","Thêm nhân viên vào đoàn thành công !!!");
                    }
                    else{
                        jFrameMess.mess("Nhân viên","Thêm nhân viên vào đoàn không thành công !!!");
                    }
                }
                catch (NumberFormatException ex){
                    JFrameMess jFrameMess = new JFrameMess();
                    jFrameMess.mess("Nhân viên","lỗi hệ thống !!!");
                }
            }
        });
    }

    public void getTT1NhanVien(int nv_id){
        bllNhanVien = new BLLNhanVien();
        NhanVien nhanvien = bllNhanVien.get1NhanVien(nv_id);
        if(nhanvien != null){
            tenJLabel.setText(nhanvien.getNv_ten());
            SDTJLabel.setText(nhanvien.getNv_sdt());
            NSJLabel.setText(nhanvien.getNv_sdt());
            EmailJLabel.setText(nhanvien.getNv_email());
            CMNDorNVJLabel.setText(nhanvien.getNv_nhiemvu());
        }
        else {
            JFrameMess jFrameMess = new JFrameMess();
            jFrameMess.mess("Nhân viên","Không tìm thấy thông tin !!!");
        }
    }

    public void getNVIDForCombobox(){
        String nv_id = combobox1.getSelectedItem().toString();
        try{
            int id = Integer.parseInt(nv_id);
            getTT1NhanVien(id);
        }
        catch (NumberFormatException e){
            JFrameMess jFrameMess = new JFrameMess();
            jFrameMess.mess("Error","nv_id combobox xảy ra lỗi !!!");
        }
    }



}
