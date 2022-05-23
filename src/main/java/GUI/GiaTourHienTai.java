package GUI;

import BLL.BLLTour;

import javax.swing.*;

public class GiaTourHienTai extends JFrame{
    private JPanel JPanel1;
    private JTable table1;

    public GiaTourHienTai(){
        setTitle("JFrame GiaTourHienTai");
        setContentPane(JPanel1);
        setSize(1000,400);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);

        BLLTour bllTour = new BLLTour();
        table1.setModel(bllTour.getListTourVSGiaTableModel(bllTour.getALLTour()));
    }
}
