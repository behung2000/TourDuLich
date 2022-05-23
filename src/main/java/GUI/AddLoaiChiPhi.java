package GUI;

import BLL.BLLChiPhi;
import BLL.BLLLoaiChiPhi;
import ENTRY.LoaiChiPhi;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddLoaiChiPhi extends JFrame{
    private JPanel JPanel1;
    private JComboBox comboBox1;
    private JButton button;
    private JLabel TenJLabel;
    private JLabel MoTaJLabel;

    public AddLoaiChiPhi(){}

    public AddLoaiChiPhi(int doan_id){
        setContentPane(JPanel1);
        setSize(700,700);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        BLLLoaiChiPhi bllLoaiChiPhi = new BLLLoaiChiPhi();
        comboBox1.setModel(bllLoaiChiPhi.getComboBoxModelCPID());
        getCPIDForCombobox();
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getCPIDForCombobox();
            }
        });
        button.setText("Add");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String scp_id = comboBox1.getSelectedItem().toString();
                try {
                    int cp_id = Integer.parseInt(scp_id);
                    BLLChiPhi bllChiPhi = new BLLChiPhi();
                    boolean bool = bllChiPhi.updateDSCTCPadd1LoaiCP(doan_id, cp_id);
                    JFrameMess jFrameMess = new JFrameMess();
                    if(bool == true){
                        jFrameMess.mess("Loại chi phí: ","Thêm loại chi phí vào đoàn thành công !!!");
                    }
                    else{
                        jFrameMess.mess("Loại chi phí: ","Thêm loại chi phí vào đoàn không thành công !!!");
                    }
                }
                catch (NumberFormatException ex){
                    JFrameMess jFrameMess = new JFrameMess();
                    jFrameMess.mess("Loại chi phí: ","lỗi hệ thống !!!");
                }
            }
        });

    }

    public void getTT1LoaiCP(int cp_id){
        BLLLoaiChiPhi bllloaiChiPhi = new BLLLoaiChiPhi();
        LoaiChiPhi loaiChiPhi = bllloaiChiPhi.get1LoaiCPWithcpid(cp_id);
        if(loaiChiPhi != null){
            TenJLabel.setText(loaiChiPhi.getCp_ten());
            MoTaJLabel.setText(loaiChiPhi.getCp_mota());
        }
        else {
            JFrameMess jFrameMess = new JFrameMess();
            jFrameMess.mess("Loại chi phí: ","Không tìm thấy thông tin !!!");
        }
    }

    public void getCPIDForCombobox(){
        String scp_id = comboBox1.getSelectedItem().toString();
        try{
            int cp_id = Integer.parseInt(scp_id);
            getTT1LoaiCP(cp_id);
        }
        catch (NumberFormatException e){
            JFrameMess jFrameMess = new JFrameMess();
            jFrameMess.mess("Error","cp_id combobox xảy ra lỗi !!!");
        }
    }

}
