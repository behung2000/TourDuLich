package GUI;

import BLL.BLLNhanVien;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GUINhanVien extends JFrame{
    private JTextField TenJTextField;
    private JTextField SDTJTextField;
    private JTextField NgaySinhJTextField;
    private JTextField EmailJTextField;
    private JTextField NhiemVuJTextField;
    private JPanel JPanel1;
    private JButton reloadButton;
    private JButton backButton;
    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JTable table1;

    public GUINhanVien(){
        setContentPane(JPanel1);
        setSize(800,500);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        setTT();
        //Buuton
        reloadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setTT();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BLLNhanVien bllNhanVien = new BLLNhanVien();
                boolean bool = bllNhanVien.add1NV(TenJTextField.getText(), SDTJTextField.getText(), NgaySinhJTextField.getText(), EmailJTextField.getText(), NhiemVuJTextField.getText());
                JFrameMess jFrameMess = new JFrameMess();
                if(bool==true){
                    jFrameMess.mess("Nhân viên", "Thêm thành công !!!");
                    setTT();
                }
                else {
                    jFrameMess.mess("Nhân viên", "Thêm không thành công !!!");
                }
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(table1.getSelectedRow()>=0){
                    BLLNhanVien bllNhanVien = new BLLNhanVien();
                    boolean bool = bllNhanVien.update1NV(table1.getModel().getValueAt(table1.getSelectedRow(), 0).hashCode(), TenJTextField.getText(), SDTJTextField.getText(), NgaySinhJTextField.getText(), EmailJTextField.getText(), NhiemVuJTextField.getText());
                    JFrameMess jFrameMess = new JFrameMess();
                    if(bool==true){
                        jFrameMess.mess("Nhân viên", "Sửa thành công !!!");
                        setTT();
                    }
                    else {
                        jFrameMess.mess("Nhân viên", "Sửa không thành công !!!");
                    }
                }
                else {
                    JFrameMess jFrameMess = new JFrameMess();
                    jFrameMess.mess("Nhân viên", "Chưa chọn đối tượng để sửa !!!");
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(table1.getSelectedRow()>=0){
                    BLLNhanVien bllNhanVien = new BLLNhanVien();
                    boolean bool = bllNhanVien.delete1NV(table1.getModel().getValueAt(table1.getSelectedRow(), 0).hashCode());
                    JFrameMess jFrameMess = new JFrameMess();
                    if(bool==true){
                        jFrameMess.mess("Nhân viên", "xóa thành công !!!");
                        setTT();
                    }
                    else {
                        jFrameMess.mess("Nhân viên", "xóa không thành công !!!");
                    }
                }
                else {
                    JFrameMess jFrameMess = new JFrameMess();
                    jFrameMess.mess("Nhân viên", "Chưa chọn đối tượng để xóa !!!");
                }
            }
        });

        //Table
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(table1.getSelectedRow()>=0){
                    TenJTextField.setText(table1.getModel().getValueAt(table1.getSelectedRow(), 1).toString());
                    SDTJTextField.setText(table1.getModel().getValueAt(table1.getSelectedRow(), 2).toString());
                    NgaySinhJTextField.setText(table1.getModel().getValueAt(table1.getSelectedRow(), 3).toString());
                    EmailJTextField.setText(table1.getModel().getValueAt(table1.getSelectedRow(), 4).toString());
                    NhiemVuJTextField.setText(table1.getModel().getValueAt(table1.getSelectedRow(), 5).toString());
                }
            }
        });
    }

    public void setTT(){
        BLLNhanVien bllNhanVien = new BLLNhanVien();
        table1.setModel(bllNhanVien.getListNhanVienTableModel(bllNhanVien.getALLNhanVien()));
    }
}
