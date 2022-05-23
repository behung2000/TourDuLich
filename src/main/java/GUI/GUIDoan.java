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
                        jFrameMess.mess("ERROR","Hệ thống lỗi -- " + ex.getMessage());
                    }
                }
                else{
                    JFrameMess jFrameMess = new JFrameMess();
                    jFrameMess.mess("Ghi chú: ","Chưa chọn đoàn để sữa");
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
                        jFrameMess.mess("ERROR","Hệ thống lỗi -- " + ex.getMessage());
                    }
                }
                else{
                    JFrameMess jFrameMess = new JFrameMess();
                    jFrameMess.mess("Ghi chú: ","Chưa chọn đoàn để xóa");
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
                        jFrameMess.mess("Ghi chú: ", "Xảy ra lỗi hệ thống !!!");
                    }
                }
                else {
                    JFrameMess jFrameMess = new JFrameMess();
                    jFrameMess.mess("Ghi chú: ", "Chưa chọn đoàn để thêm khách hàng");
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
                        jFrameMess.mess("Ghi chú: ", "Xảy ra lỗi hệ thống !!!");
                    }
                }
                else {
                    JFrameMess jFrameMess = new JFrameMess();
                    jFrameMess.mess("Ghi chú: ", "Chưa chọn đoàn để thêm nhân viên");
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
                    jFrameMess.mess("Ghi chú: ", "Chưa chọn đoàn để thêm chi phí");
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
                                jFrameMess.mess("Khách hàng: ", "delete 1 khách hàng trong đoàn thành công !!!");
                            }
                            else {
                                jFrameMess.mess("Khách hàng: ", "delete 1 khách hàng trong đoàn không thành công !!!");
                            }
                        }
                        catch (NumberFormatException ex){
                            JFrameMess jFrameMess = new JFrameMess();
                            jFrameMess.mess("Ghi chú: ", "Xảy ra lỗi hệ thống !!!");
                        }
                    }
                    else{
                        JFrameMess jFrameMess = new JFrameMess();
                        jFrameMess.mess("Ghi chú: ", "Chưa chọn khách hàng để xóa ra khỏi đoàn");
                    }
                }
                else {
                    JFrameMess jFrameMess = new JFrameMess();
                    jFrameMess.mess("Ghi chú: ", "Chưa chọn đoàn để xóa khách hàng ra khỏi đoàn");
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
                                jFrameMess.mess("Nhân viên: ", "delete 1 nhân viên trong đoàn thành công !!!");
                            }
                            else {
                                jFrameMess.mess("Nhân viên: ", "delete 1 nhân viên trong đoàn không thành công !!!");
                            }
                        }
                        catch (NumberFormatException ex){
                            JFrameMess jFrameMess = new JFrameMess();
                            jFrameMess.mess("Ghi chú: ", "Xảy ra lỗi hệ thống !!!");
                        }
                    }
                    else{
                        JFrameMess jFrameMess = new JFrameMess();
                        jFrameMess.mess("Ghi chú: ", "Chưa chọn nhân viên để xóa ra khỏi đoàn");
                    }
                }
                else {
                    JFrameMess jFrameMess = new JFrameMess();
                    jFrameMess.mess("Ghi chú: ", "Chưa chọn đoàn để xóa nhân viên ra khỏi đoàn");
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
                            jFrameMess.mess("Loại chi phí: ", "delete 1 loại chi phí trong đoàn thành công !!!");
                        }
                        else {
                            jFrameMess.mess("Loại chi phí: ", "delete 1 loại chi phí trong đoàn không thành công !!!");
                        }
                    }
                    else{
                        JFrameMess jFrameMess = new JFrameMess();
                        jFrameMess.mess("Ghi chú: ", "Chưa chọn loại chi phí để xóa ra khỏi đoàn");
                    }
                }
                else {
                    JFrameMess jFrameMess = new JFrameMess();
                    jFrameMess.mess("Ghi chú: ", "Chưa chọn đoàn để xóa loại chi phí ra khỏi đoàn");
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
                        jFrameMess.mess("Hệ thống", "lỗi");
                    }
                }
            }
        });
    }

    //Phần Đoàn
    public void DSDoan(){
        setTitle("JFrame khachhang1doan");
        //DSChinhJLabel.setText("Danh sách đoàn");
        //SearchJLabel.setText("Tìm kiếm theo tên đoàn");
        //DSXuatJLabel.setText("Danh sách khách hàng");
        TenJLabel.setText("Tên đoàn");
        table2.setModel(new DefaultTableModel());
        table3.setModel(new DefaultTableModel());
        table4.setModel(new DefaultTableModel());
        //Set
        bllDoan = new BLLDoan();
        table1.setModel(bllDoan.getListDoanTableModel(bllDoan.getALLDoan()));
    }
}
