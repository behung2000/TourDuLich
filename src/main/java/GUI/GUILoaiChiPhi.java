package GUI;

import BLL.BLLLoaiChiPhi;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GUILoaiChiPhi extends JFrame{
    private JPanel JPanel1;
    private JTextField TenJTextField;
    private JTextField MoTaJTextField;
    private JButton reloadButton;
    private JButton backButton;
    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JTable table1;

    public GUILoaiChiPhi(){
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
                if(table1.getSelectedRow()>=0) {
                    TenJTextField.setText(table1.getModel().getValueAt(table1.getSelectedRow(),1).toString());
                    MoTaJTextField.setText(table1.getModel().getValueAt(table1.getSelectedRow(),2).toString());
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
                BLLLoaiChiPhi loaiChiPhi = new BLLLoaiChiPhi();
                boolean bool = loaiChiPhi.add1LoaiCP(TenJTextField.getText(), MoTaJTextField.getText());
                JFrameMess jFrameMess = new JFrameMess();
                if(bool){
                    jFrameMess.mess("Lo???i ch?? ph??: ","th??m th??nh c??ng !!!");
                    setTT();
                }
                else{
                    jFrameMess.mess("Lo???i ch?? ph??: ","th??m kh??ng th??nh c??ng !!!");
                }
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(table1.getSelectedRow()>=0){
                    BLLLoaiChiPhi loaiChiPhi = new BLLLoaiChiPhi();
                    boolean bool = loaiChiPhi.update1LoaiCP(table1.getModel().getValueAt(table1.getSelectedRow(), 0).hashCode(), TenJTextField.getText(), MoTaJTextField.getText());
                    JFrameMess jFrameMess = new JFrameMess();
                    if(bool){
                        jFrameMess.mess("Lo???i ch?? ph??: ","S???a th??nh c??ng !!!");
                        setTT();
                    }
                    else{
                        jFrameMess.mess("Lo???i ch?? ph??: ","S???a kh??ng th??nh c??ng !!!");
                    }
                }
                else{
                    JFrameMess jFrameMess = new JFrameMess();
                    jFrameMess.mess("Lo???i ch?? ph??: ","ch??a ch???n ?????i t?????ng s???a !!!");
                }

            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public void setTT(){
        BLLLoaiChiPhi bllLoaiChiPhi = new BLLLoaiChiPhi();
        table1.setModel(bllLoaiChiPhi.getTableModelLoaiCP(bllLoaiChiPhi.getALLLoaiCP()));
    }

}
