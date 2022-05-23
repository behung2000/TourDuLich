package GUI;

import BLL.BLLChiTiet;
import BLL.BLLTour;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GUITours extends JFrame{
    private JPanel JPanel1;
    private JTable table1;
    private JTable table2;
    private JButton backButton;
    private JTextField SearchTextField;
    private JButton searchButton;
    private JLabel TenJLabel;
    private JButton DeleteButton;
    private JButton UpdateButton;
    private JButton AddButton;
    private JButton Delete1Button;
    private JButton Add1Button;
    private JButton reloadButton;


    private BLLTour bllTour;
    private BLLChiTiet bllChiTiet;

    public GUITours(){
        setTitle("JFrame DiaDiem1Tour");
        setContentPane(JPanel1);
        setSize(1500,700);
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
                DSDiaDiem();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = SearchTextField.getText();
                bllTour = new BLLTour();
                table1.setModel(bllTour.getListTourTableModel(bllTour.getSearchName(name)));
            }
        });

        AddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TTChiTietTour ttChiTietTour = new TTChiTietTour();
                ttChiTietTour.AddTour();
            }
        });

        UpdateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(table1.getSelectedRow()>=0){
                    String stour_id = table1.getModel().getValueAt(table1.getSelectedRow(),0).toString();
                    try{
                        int tour_id = Integer.parseInt(stour_id);
                        //System.out.println(tour_id);
                        TTChiTietTour ttChiTietTour = new TTChiTietTour();
                        ttChiTietTour.updateTour(tour_id);
                    }
                    catch (NumberFormatException ex){
                        JFrameMess jFrameMess = new JFrameMess();
                        jFrameMess.mess("Hệ thống: ","Lỗi !!!");
                    }
                }
                else {
                    JFrameMess jFrameMess = new JFrameMess();
                    jFrameMess.mess("Tour: ","Chưa chọn tour để sửa !!!");
                }
            }
        });

        DeleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(table1.getSelectedRow()>=0){
                    BLLTour bllTour = new BLLTour();
                    boolean bool = bllTour.Delete1Tour(table1.getModel().getValueAt(table1.getSelectedRow(),0).hashCode());
                    JFrameMess jFrameMess = new JFrameMess();
                    if(bool == true){
                        jFrameMess.mess("Tour: ","Xóa thành công !!!");
                    }
                    else {
                        jFrameMess.mess("Tour: ","Xóa không thành công !!!");
                    }
                }
                else {
                    JFrameMess jFrameMess = new JFrameMess();
                    jFrameMess.mess("Tour: ","Chưa chọn tour để xóa !!!");
                }
            }
        });

        Add1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(table1.getSelectedRow()>=0) {
                    int id = table1.getModel().getValueAt(table1.getSelectedRow(),0).hashCode();
                    AddDiaDiem addDiaDiem = new AddDiaDiem();
                    addDiaDiem.AddDiaDiemToTour(id);
                }
                else
                {
                    JFrameMess jFrameMess = new JFrameMess();
                    jFrameMess.mess("Tour: ","Chưa chọn tour !!!");
                }
            }
        });

        Delete1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(table1.getSelectedRow()>=0){
                    if(table2.getSelectedRow()>=0){
                        int tour_id = table1.getModel().getValueAt(table1.getSelectedRow(),0).hashCode();
                        int ct_thutu = table2.getModel().getValueAt(table2.getSelectedRow(),0).hashCode();
                        int dd_id = table2.getModel().getValueAt(table2.getSelectedRow(), 1).hashCode();
                        //ystem.out.println("tour_id:"+tour_id+" --- ct_thutu:"+ct_thutu+" --- dd_id:"+dd_id);
                        BLLChiTiet bllChiTiet = new BLLChiTiet();
                        boolean bool = bllChiTiet.DeleteChiTietWithtourid_ddid_ctthutu(tour_id, dd_id, ct_thutu);
                        JFrameMess jFrameMess = new JFrameMess();
                        if(bool == true){
                            jFrameMess.mess("Địa điểm: ","Xóa địa điểm khỏi tour thành công !!!");
                        }
                        else{
                            jFrameMess.mess("Địa điểm: ","Xóa địa điểm khỏi tour không thành công !!!");
                        }
                    }
                    else
                    {
                        JFrameMess jFrameMess = new JFrameMess();
                        jFrameMess.mess("Địa điểm: ","Chưa chọn địa điểm !!!");
                    }
                }
                else
                {
                    JFrameMess jFrameMess = new JFrameMess();
                    jFrameMess.mess("Tour: ","Chưa chọn tour !!!");
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
                    String tentour = table1.getModel().getValueAt(selection,1).toString();
                    TenJLabel.setText(tentour);
                    bllChiTiet = new BLLChiTiet();
                    table2.setModel(bllChiTiet.getDSDiaDiemTableModel(input));
                }
            }
        });




    }

    //Phần Tour
    public void DSDiaDiem(){
        //Set
        bllTour = new BLLTour();
        table2.setModel(new DefaultTableModel());
        table1.setModel(bllTour.getListTourTableModel(bllTour.getALLTour()));
    }

}
