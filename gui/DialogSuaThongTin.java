package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.BenhNhanDAO;
import error.DialogBaoLoi;
import error.ErrorLog;
import pojo.BenhNhan;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DialogSuaThongTin extends JDialog {
	private JTextField editHoTen;
	private JTextField editGioiTinh;
	private JTextField editNamSinh;
	private JTextField editDiaChi;

	/**
	 * Create the dialog.
	 */
	public DialogSuaThongTin(BenhNhan bn) {
		setTitle("Sua Thong Tin");
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 400, 195);
		getContentPane().setLayout(null);
		
		JLabel label = new JLabel("Ho Ten");
		label.setBounds(10, 14, 46, 14);
		getContentPane().add(label);
		
		editHoTen = new JTextField();
		editHoTen.setColumns(10);
		editHoTen.setBounds(66, 11, 318, 20);
		getContentPane().add(editHoTen);
		
		editGioiTinh = new JTextField();
		editGioiTinh.setColumns(10);
		editGioiTinh.setBounds(66, 42, 318, 20);
		getContentPane().add(editGioiTinh);
		
		JLabel label_1 = new JLabel("Gioi tinh");
		label_1.setBounds(10, 45, 46, 14);
		getContentPane().add(label_1);
		
		editNamSinh = new JTextField();
		editNamSinh.setColumns(10);
		editNamSinh.setBounds(66, 73, 318, 20);
		getContentPane().add(editNamSinh);
		
		JLabel label_2 = new JLabel("Nam sinh");
		label_2.setBounds(10, 76, 46, 14);
		getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("Dia chi");
		label_3.setBounds(10, 107, 46, 14);
		getContentPane().add(label_3);
		
		editDiaChi = new JTextField();
		editDiaChi.setColumns(10);
		editDiaChi.setBounds(66, 104, 318, 20);
		getContentPane().add(editDiaChi);
		
		// Khoi tao gia tri
		editHoTen.setText(bn.getHoTen());
		editGioiTinh.setText(bn.getGioiTinh());
		editNamSinh.setText("" + bn.getNamSinh());
		editDiaChi.setText(bn.getDiaChi());
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(295, 135, 89, 23);
		getContentPane().add(btnCancel);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {								
				String hoten = editHoTen.getText();
				String gioitinh = editGioiTinh.getText();
				int namsinh = 1995;
				try {
					namsinh = Integer.parseInt(editNamSinh.getText());
				}
				catch (Exception ex) {
					ErrorLog.log(ex.getMessage());
				}
				
				String diachi = editDiaChi.getText();
				
				if (hoten.compareTo("") == 0 || gioitinh.compareTo("") == 0
						|| namsinh <= 1910 || diachi.compareTo("") == 0) {
					DialogBaoLoi dialog = new DialogBaoLoi("Thong tin khong hop le");
					dialog.setVisible(true);
					return;
				}
				
				bn.setHoTen(hoten);
				bn.setGioiTinh(gioitinh);
				bn.setNamSinh(namsinh);
				bn.setDiaChi(diachi);
				
				boolean res = BenhNhanDAO.capNhatBenhNhan(bn);
				
				if (!res) {
					DialogBaoLoi dialog = new DialogBaoLoi("Cap nhat thong tin that bai");
					dialog.setVisible(true);
				}
				else {
					DialogBaoLoi dialog = new DialogBaoLoi("Cap nhat thong tin thanh cong");
					dialog.setVisible(true);
				}
				
				dispose();
			}
		});
		btnOk.setBounds(196, 135, 89, 23);
		getContentPane().add(btnOk);
	}
}
