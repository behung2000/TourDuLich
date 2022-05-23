package GUI;

import BLL.BLLDiaDiem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GUIDiaDiem extends JFrame{
    private JPanel JPanel1;
    private JButton reloadButton;
    private JButton backButton;
    private JButton deleteButton;
    private JButton updateButton;
    private JButton addButton;
    private JTable table1;
    private JTextField TenJTextField;
    private JTextField TPJTextField;
    private JTextField MoTaJTextField;

    public GUIDiaDiem(){
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
                BLLDiaDiem bllDiaDiem = new BLLDiaDiem();
                boolean bool = bllDiaDiem.add1DiaDiem(TPJTextField.getText(), TenJTextField.getText(), MoTaJTextField.getText());
                JFrameMess jFrameMess = new JFrameMess();
                if(bool == true){
                    jFrameMess.mess("Địa điểm: ","Thêm thành công !!!");
                    setTT();
                }
                else {
                    jFrameMess.mess("Địa điểm: ","Thêm không thành công !!!");
                }
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(table1.getSelectedRow()>=0){
                    BLLDiaDiem bllDiaDiem = new BLLDiaDiem();
                    boolean bool = bllDiaDiem.update1DiaDiem(table1.getModel().getValueAt(table1.getSelectedRow(), 0).hashCode(), TPJTextField.getText(), TenJTextField.getText(), MoTaJTextField.getText());
                    JFrameMess jFrameMess = new JFrameMess();
                    if(bool == true){
                        jFrameMess.mess("Địa điểm: ","Sửa thành công !!!");
                        setTT();
                    }
                    else {
                        jFrameMess.mess("Địa điểm: ","Sửa không thành công !!!");
                    }
                }
                else{
                    JFrameMess jFrameMess = new JFrameMess();
                    jFrameMess.mess("Địa điểm: ","chưa chọn đối tượng để sửa !!!");
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(table1.getSelectedRow()>=0){
                    BLLDiaDiem bllDiaDiem = new BLLDiaDiem();
                    boolean bool = bllDiaDiem.delete1DiaDiemWithddid(table1.getModel().getValueAt(table1.getSelectedRow(), 0).hashCode());
                    JFrameMess jFrameMess = new JFrameMess();
                    if(bool == true){
                        jFrameMess.mess("Địa điểm: ","Xóa thành công !!!");
                        setTT();
                    }
                    else {
                        jFrameMess.mess("Địa điểm: ","Xóa không thành công !!!");
                    }
                }
                else{
                    JFrameMess jFrameMess = new JFrameMess();
                    jFrameMess.mess("Địa điểm: ","chưa chọn đối tượng để sửa !!!");
                }
            }
        });

        //Table
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(table1.getSelectedRow()>=0){
                    TPJTextField.setText(table1.getModel().getValueAt(table1.getSelectedRow(), 1).toString());
                    TenJTextField.setText(table1.getModel().getValueAt(table1.getSelectedRow(), 2).toString());
                    MoTaJTextField.setText(table1.getModel().getValueAt(table1.getSelectedRow(), 3).toString());
                }
            }
        });
    }

    public void setTT(){
        BLLDiaDiem bllDiaDiem = new BLLDiaDiem();
        table1.setModel(bllDiaDiem.getListTableModelDiaDiem(bllDiaDiem.getALLList()));
    }

}
