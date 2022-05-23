package GUI;

import BLL.BLLChiTiet;
import BLL.BLLDiaDiem;
import ENTRY.DiaDiem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddDiaDiem extends JFrame {
    private JPanel JPanel1;
    private JComboBox comboBox1;
    private JButton button1;
    private JLabel ThanhPhoJLabel;
    private JLabel TenJLabel;
    private JLabel MoTaJLabel;

    public AddDiaDiem() {
        setContentPane(JPanel1);
        setSize(700,700);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        BLLDiaDiem bllDiaDiem = new BLLDiaDiem();
        comboBox1.setModel(bllDiaDiem.getIDDiaDiemCombobox());
        getDDIDForCombobox();
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getDDIDForCombobox();
            }
        });
    }

    public void getTT1DiaDiem(int dd_id){
        BLLDiaDiem bllDiaDiem = new BLLDiaDiem();
        DiaDiem diadiem = bllDiaDiem.get1DiaDiem(dd_id);
        if(diadiem != null){
            ThanhPhoJLabel.setText(diadiem.getDd_thanhpho());
            TenJLabel.setText(diadiem.getDd_ten());
            MoTaJLabel.setText(diadiem.getDd_mota());
        }
        else {
            JFrameMess jFrameMess = new JFrameMess();
            jFrameMess.mess("Địa điểm","Không tìm thấy thông tin !!!");
        }
    }

    public void getDDIDForCombobox(){
        String dd_id = comboBox1.getSelectedItem().toString();
        try{
            int id = Integer.parseInt(dd_id);
            getTT1DiaDiem(id);
        }
        catch (NumberFormatException e){
            JFrameMess jFrameMess = new JFrameMess();
            jFrameMess.mess("Error","dd_id combobox xảy ra lỗi !!!");
        }
    }

    public void AddDiaDiemToTour(int tour_id){
        button1.setText("ADD");
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sdd_id = comboBox1.getSelectedItem().toString();
                try{
                    int dd_id = Integer.parseInt(sdd_id);
                    BLLChiTiet bllChiTiet = new BLLChiTiet();
                    boolean bool = bllChiTiet.Add1ChiTiet(tour_id, dd_id);
                    JFrameMess jFrameMess = new JFrameMess();
                    if(bool == true){
                        jFrameMess.mess("Địa điểm","thêm địa điểm vào tour thành công!!!");
                    }
                    else {
                        jFrameMess.mess("Địa điểm","thêm địa điểm vào tour không thành công!!!");
                    }
                }
                catch(NumberFormatException ex){
                    JFrameMess jFrameMess = new JFrameMess();
                    jFrameMess.mess("Error","dd_id combobox xảy ra lỗi !!!");
                }
                setVisible(false);
            }
        });
    }
}
