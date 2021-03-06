package GUI;

import BLL.BLLChiPhi;
import BLL.BLLDoan;
import BLL.BLLNguoiDi;
import ENTRY.ChiPhi;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GUIDoan extends JFrame{
    private JPanel JPanel1;
    private JLabel DSChinhJLabel;
    private JButton backButton;
    private JLabel SearchJLabel;
    private JTextField SearchTextField;
    private JButton searchButton;
    private JTable table1;
    private JTable table2;
    private JButton DeleteButton;
    private JButton UpdateButton;
    private JButton AddButton;
    private JButton AddKHButton;
    private JButton DeleteKHButton;
    private JLabel TenJLabel;
    private JLabel DSXuatJLabel;
    private javax.swing.JPanel JPanel;
    private JTable table3;
    private JButton reloadButton;
    private JButton AddNVButton;
    private JButton DeleteNVButton;
    private JTable table4;
    private JButton DeleteCPButton;
    private JButton AddCPButton;
    private JLabel SoTienJLabel;

    private BLLDoan bllDoan;
    private BLLNguoiDi bllNguoiDi;

    public GUIDoan(){
        setContentPane(JPanel1);
        setSize(1500,900);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);

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
                DSDoan();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = SearchTextField.getText();
                bllDoan = new BLLDoan();
                table1.setModel(bllDoan.getListDoanTableModel(bllDoan.getSearchName(name)));
            }
        });

        AddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TTChiTietDoan ttChiTietDoan = new TTChiTietDoan();
                ttChiTietDoan.ADDDoan();
            }
        });

        UpdateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = table1.getSelectedRow();
                if(i!=-1){
                    String t = table1.getModel().getValueAt(i, 0).toString();
                    try{
                        int j = Integer.parseInt(t);
                        TTChiTietDoan ttChiTietDoan = new TTChiTietDoan();
                        ttChiTietDoan.UPDATEDoan(j);
                    }
                    catch (NumberFormatException ex){
                        JFrameMess jFrameMess = new JFrameMess();
                        jFrameMess.mess("ERROR","H??? th???ng l???i -- " + ex.getMessage());
                    }
                }
                else{
                    JFrameMess jFrameMess = new JFrameMess();
                    jFrameMess.mess("Ghi ch??: ","Ch??a ch???n ??o??n ????? s???a");
                }
            }
        });

        DeleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = table1.getSelectedRow();
                if(i!=-1){
                    String t = table1.getModel().getValueAt(i, 0).toString();
                    try{
                        int j = Integer.parseInt(t);
                        bllDoan = new BLLDoan();
                        bllDoan.deleteDoan(j);
                    }
                    catch (NumberFormatException ex){
                        JFrameMess jFrameMess = new JFrameMess();
                        jFrameMess.mess("ERROR","H??? th???ng l???i -- " + ex.getMessage());
                    }
                }
                else{
                    JFrameMess jFrameMess = new JFrameMess();
                    jFrameMess.mess("Ghi ch??: ","Ch??a ch???n ??o??n ????? x??a");
                }
            }
        });

        AddKHButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(table1.getSelectedRow()>=0) {
                    String i = table1.getModel().getValueAt(table1.getSelectedRow(), 0).toString();
                    try {
                        int id_doan = Integer.parseInt(i);
                        AddKHorNVToDoan addKH = new AddKHorNVToDoan();
                        addKH.AddKHToDoan(id_doan);
                    } catch (NumberFormatException ex) {
                        JFrameMess jFrameMess = new JFrameMess();
                        jFrameMess.mess("Ghi ch??: ", "X???y ra l???i h??? th???ng !!!");
                    }
                }
                else {
                    JFrameMess jFrameMess = new JFrameMess();
                    jFrameMess.mess("Ghi ch??: ", "Ch??a ch???n ??o??n ????? th??m kh??ch h??ng");
                }
            }
        });

        AddNVButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(table1.getSelectedRow()>=0) {
                    String i = table1.getModel().getValueAt(table1.getSelectedRow(), 0).toString();
                    try {
                        int id_doan = Integer.parseInt(i);
                        AddKHorNVToDoan addNV = new AddKHorNVToDoan();
                        addNV.AddNVToDoan(id_doan);
                    } catch (NumberFormatException ex) {
                        JFrameMess jFrameMess = new JFrameMess();
                        jFrameMess.mess("Ghi ch??: ", "X???y ra l???i h??? th???ng !!!");
                    }
                }
                else {
                    JFrameMess jFrameMess = new JFrameMess();
                    jFrameMess.mess("Ghi ch??: ", "Ch??a ch???n ??o??n ????? th??m nh??n vi??n");
                }
            }
        });

        AddCPButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(table1.getSelectedRow()>=0) {
                    int doan_id = table1.getModel().getValueAt(table1.getSelectedRow(),0).hashCode();
                    AddLoaiChiPhi addLoaiChiPhi = new AddLoaiChiPhi(doan_id);
                }
                else {
                    JFrameMess jFrameMess = new JFrameMess();
                    jFrameMess.mess("Ghi ch??: ", "Ch??a ch???n ??o??n ????? th??m chi ph??");
                }
            }
        });

        DeleteKHButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(table1.getSelectedRow()>=0) {
                    String si = table1.getModel().getValueAt(table1.getSelectedRow(), 0).toString();
                    if(table2.getSelectedRow()>=0){
                        String sj = table2.getModel().getValueAt(table2.getSelectedRow(), 0).toString();
                        try{
                            int i = Integer.parseInt(si);
                            int j = Integer.parseInt(sj);
                            bllNguoiDi = new BLLNguoiDi();
                            boolean bool = bllNguoiDi.updateDSKhachDelete1KH(i, j);
                            JFrameMess jFrameMess = new JFrameMess();
                            if(bool == true){
                                jFrameMess.mess("Kh??ch h??ng: ", "delete 1 kh??ch h??ng trong ??o??n th??nh c??ng !!!");
                            }
                            else {
                                jFrameMess.mess("Kh??ch h??ng: ", "delete 1 kh??ch h??ng trong ??o??n kh??ng th??nh c??ng !!!");
                            }
                        }
                        catch (NumberFormatException ex){
                            JFrameMess jFrameMess = new JFrameMess();
                            jFrameMess.mess("Ghi ch??: ", "X???y ra l???i h??? th???ng !!!");
                        }
                    }
                    else{
                        JFrameMess jFrameMess = new JFrameMess();
                        jFrameMess.mess("Ghi ch??: ", "Ch??a ch???n kh??ch h??ng ????? x??a ra kh???i ??o??n");
                    }
                }
                else {
                    JFrameMess jFrameMess = new JFrameMess();
                    jFrameMess.mess("Ghi ch??: ", "Ch??a ch???n ??o??n ????? x??a kh??ch h??ng ra kh???i ??o??n");
                }
            }
        });

        DeleteNVButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(table1.getSelectedRow()>=0) {
                    String si = table1.getModel().getValueAt(table1.getSelectedRow(), 0).toString();
                    if(table3.getSelectedRow()>=0){
                        String sj = table3.getModel().getValueAt(table3.getSelectedRow(), 0).toString();
                        try{
                            int i = Integer.parseInt(si);
                            int j = Integer.parseInt(sj);
                            bllNguoiDi = new BLLNguoiDi();
                            boolean bool = bllNguoiDi.updateDSNhanVienDelete1NV(i, j);
                            JFrameMess jFrameMess = new JFrameMess();
                            if(bool == true){
                                jFrameMess.mess("Nh??n vi??n: ", "delete 1 nh??n vi??n trong ??o??n th??nh c??ng !!!");
                            }
                            else {
                                jFrameMess.mess("Nh??n vi??n: ", "delete 1 nh??n vi??n trong ??o??n kh??ng th??nh c??ng !!!");
                            }
                        }
                        catch (NumberFormatException ex){
                            JFrameMess jFrameMess = new JFrameMess();
                            jFrameMess.mess("Ghi ch??: ", "X???y ra l???i h??? th???ng !!!");
                        }
                    }
                    else{
                        JFrameMess jFrameMess = new JFrameMess();
                        jFrameMess.mess("Ghi ch??: ", "Ch??a ch???n nh??n vi??n ????? x??a ra kh???i ??o??n");
                    }
                }
                else {
                    JFrameMess jFrameMess = new JFrameMess();
                    jFrameMess.mess("Ghi ch??: ", "Ch??a ch???n ??o??n ????? x??a nh??n vi??n ra kh???i ??o??n");
                }
            }
        });

        DeleteCPButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(table1.getSelectedRow()>=0) {
                    String si = table1.getModel().getValueAt(table1.getSelectedRow(), 0).toString();
                    if(table4.getSelectedRow()>=0){
                        int doan_id = table1.getModel().getValueAt(table1.getSelectedRow(),0).hashCode();
                        int cp_id = table4.getModel().getValueAt(table4.getSelectedRow(),0).hashCode();
                        BLLChiPhi bllChiPhi = new BLLChiPhi();
                        boolean bool = bllChiPhi.updateDSCTCPdelete1LoaiCP(doan_id, cp_id);
                        JFrameMess jFrameMess = new JFrameMess();
                        if(bool == true){
                            jFrameMess.mess("Lo???i chi ph??: ", "delete 1 lo???i chi ph?? trong ??o??n th??nh c??ng !!!");
                        }
                        else {
                            jFrameMess.mess("Lo???i chi ph??: ", "delete 1 lo???i chi ph?? trong ??o??n kh??ng th??nh c??ng !!!");
                        }
                    }
                    else{
                        JFrameMess jFrameMess = new JFrameMess();
                        jFrameMess.mess("Ghi ch??: ", "Ch??a ch???n lo???i chi ph?? ????? x??a ra kh???i ??o??n");
                    }
                }
                else {
                    JFrameMess jFrameMess = new JFrameMess();
                    jFrameMess.mess("Ghi ch??: ", "Ch??a ch???n ??o??n ????? x??a lo???i chi ph?? ra kh???i ??o??n");
                }
            }
        });

        //Table
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int selection = table1.getSelectedRow();
                if(selection != -1){
                    String input = table1.getModel().getValueAt(selection,0).toString();
                    String tendoan = table1.getModel().getValueAt(selection,1).toString();
                    TenJLabel.setText(input+"-"+tendoan);
                    bllNguoiDi = new BLLNguoiDi();
                    table2.setModel(bllNguoiDi.getDSKhachTableModel(input));
                    table3.setModel(bllNguoiDi.getDSNhanVienTableModel(input));
                    int doan_id = table1.getModel().getValueAt(selection,0).hashCode();
                    BLLChiPhi bllChiPhi = new BLLChiPhi();
                    ChiPhi chiphi = bllChiPhi.get1ChiphiWithIDDoan(doan_id);
                    if(chiphi != null){
                        SoTienJLabel.setText(chiphi.getChiphi_total()+"");
                        table4.setModel(bllChiPhi.getDSChiPhiTableModel(doan_id));
                    }
                    else{
                        JFrameMess jFrameMess = new JFrameMess();
                        jFrameMess.mess("H??? th???ng", "l???i");
                    }
                }
            }
        });
    }

    //Ph???n ??o??n
    public void DSDoan(){
        setTitle("JFrame khachhang1doan");
        //DSChinhJLabel.setText("Danh s??ch ??o??n");
        //SearchJLabel.setText("T??m ki???m theo t??n ??o??n");
        //DSXuatJLabel.setText("Danh s??ch kh??ch h??ng");
        TenJLabel.setText("T??n ??o??n");
        table2.setModel(new DefaultTableModel());
        table3.setModel(new DefaultTableModel());
        table4.setModel(new DefaultTableModel());
        //Set
        bllDoan = new BLLDoan();
        table1.setModel(bllDoan.getListDoanTableModel(bllDoan.getALLDoan()));
    }
}
