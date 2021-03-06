package GUI;

import BLL.BLLKhachHang;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GUIKhach extends JFrame {

    private JPanel JPanel1;
    private JTextField TenJTextField;
    private JTextField SDTJTextField;
    private JTextField NgaySinhJTextField;
    private JTextField EmailJTextField;
    private JTextField CMNDJTextField;
    private JButton reloadButton;
    private JButton backButton;
    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JTable table1;

    public GUIKhach(){
        setContentPane(JPanel1);
        setSize(800,500);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        setTT();
        //table
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(table1.getSelectedRow()>=0){
                    TenJTextField.setText(table1.getModel().getValueAt(table1.getSelectedRow(), 1).toString());
                    SDTJTextField.setText(table1.getModel().getValueAt(table1.getSelectedRow(), 2).toString());
                    NgaySinhJTextField.setText(table1.getModel().getValueAt(table1.getSelectedRow(), 3).toString());
                    EmailJTextField.setText(table1.getModel().getValueAt(table1.getSelectedRow(), 4).toString());
                    CMNDJTextField.setText(table1.getModel().getValueAt(table1.getSelectedRow(), 5).toString());
                }
            }
        });

        //Button
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
                BLLKhachHang bllKhachHang = new BLLKhachHang();
                boolean bool = bllKhachHang.add1Khach(TenJTextField.getText(), SDTJTextField.getText(), NgaySinhJTextField.getText(), EmailJTextField.getText(), CMNDJTextField.getText());
                JFrameMess jFrameMess = new JFrameMess();
                if(bool==true){
                    jFrameMess.mess("Kh??ch: ", "Th??m th??nh c??ng !!!");
                    setTT();
                }
                else {
                    jFrameMess.mess("Kh??ch: ", "Th??m kh??ng th??nh c??ng !!!");
                }
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(table1.getSelectedRow()>=0){
                    BLLKhachHang bllKhachHang = new BLLKhachHang();
                    boolean bool = bllKhachHang.update1Khach(table1.getModel().getValueAt(table1.getSelectedRow(), 0).hashCode(), TenJTextField.getText(), SDTJTextField.getText(), NgaySinhJTextField.getText(), EmailJTextField.getText(), CMNDJTextField.getText());
                    JFrameMess jFrameMess = new JFrameMess();
                    if(bool==true){
                        jFrameMess.mess("Kh??ch: ", "S???a th??nh c??ng !!!");
                        setTT();
                    }
                    else {
                        jFrameMess.mess("Kh??ch: ", "S???a kh??ng th??nh c??ng !!!");
                    }
                }
                else {
                    JFrameMess jFrameMess = new JFrameMess();
                    jFrameMess.mess("Kh??ch: ", "Ch??a ch???n ?????i t?????ng ????? s???a !!!");
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(table1.getSelectedRow()>=0){
                    BLLKhachHang bllKhachHang = new BLLKhachHang();
                    boolean bool = bllKhachHang.delete1Khach(table1.getModel().getValueAt(table1.getSelectedRow(), 0).hashCode());
                    JFrameMess jFrameMess = new JFrameMess();
                    if(bool==true){
                        jFrameMess.mess("Kh??ch: ", "X??a th??nh c??ng !!!");
                        setTT();
                    }
                    else {
                        jFrameMess.mess("Kh??ch: ", "X??a kh??ng th??nh c??ng !!!");
                    }
                }
                else {
                    JFrameMess jFrameMess = new JFrameMess();
                    jFrameMess.mess("Kh??ch: ", "Ch??a ch???n ?????i t?????ng ????? x??a !!!");
                }
            }
        });
    }

    public void setTT(){
        BLLKhachHang bllKhachHang = new BLLKhachHang();
        table1.setModel(bllKhachHang.getListKhachHangTableModel(bllKhachHang.getALLKhachHang()));
    }
}
