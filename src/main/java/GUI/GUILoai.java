package GUI;

import BLL.BLLLoai;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GUILoai extends JFrame{
    private JPanel JPanel1;
    private JTextField TenJTextField;
    private JTextField MoTaJTextField;
    private JButton reloadButton;
    private JButton backButton;
    private JTable table1;
    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;

    public GUILoai(){
        setContentPane(JPanel1);
        setSize(800,500);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        setTT();

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
                BLLLoai bllLoai = new BLLLoai();
                boolean bool = bllLoai.add1Loai(TenJTextField.getText(), MoTaJTextField.getText());
                JFrameMess jFrameMess = new JFrameMess();
                if(bool == true){
                    jFrameMess.mess("Loại tour","thêm thành công !!!");
                    setTT();
                }
                else{
                    jFrameMess.mess("Loại tour","thêm không thành công !!!");
                }
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(table1.getSelectedRow()>=0){
                    BLLLoai bllLoai = new BLLLoai();
                    boolean bool = bllLoai.update1Loai(table1.getModel().getValueAt(table1.getSelectedRow(), 0).hashCode(),TenJTextField.getText(), MoTaJTextField.getText());
                    JFrameMess jFrameMess = new JFrameMess();
                    if(bool == true){
                        jFrameMess.mess("Loại tour","sửa thành công !!!");
                        setTT();
                    }
                    else{
                        jFrameMess.mess("Loại tour","sửa không thành công !!!");
                    }
                }
                else{
                    JFrameMess jFrameMess = new JFrameMess();
                    jFrameMess.mess("Loại tour","chưa chọn đối tượng để sửa !!!");
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(table1.getSelectedRow()>=0){
                    BLLLoai bllLoai = new BLLLoai();
                    boolean bool = bllLoai.delete1LoaiTour(table1.getModel().getValueAt(table1.getSelectedRow(), 0).hashCode());
                    JFrameMess jFrameMess = new JFrameMess();
                    if(bool == true){
                        jFrameMess.mess("Loại tour","xóa thành công !!!");
                        setTT();
                    }
                    else{
                        jFrameMess.mess("Loại tour","xóa không thành công !!!");
                    }
                }
                else{
                    JFrameMess jFrameMess = new JFrameMess();
                    jFrameMess.mess("Loại tour","chưa chọn đối tượng để xóa !!!");
                }
            }
        });

        //Table
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(table1.getSelectedRow()>=0){
                    TenJTextField.setText(table1.getModel().getValueAt(table1.getSelectedRow(),1).toString());
                    MoTaJTextField.setText(table1.getModel().getValueAt(table1.getSelectedRow(),2).toString());
                }
            }
        });
    }

    public void setTT(){
        BLLLoai bllLoai = new BLLLoai();
        table1.setModel(bllLoai.getTableModelTourLoai(bllLoai.getALLLoai()));
    }
}
