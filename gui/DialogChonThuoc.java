package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.DonThuocDAO;
import error.DialogBaoLoi;
import error.ErrorLog;
import pojo.DonThuoc;
import pojo.PhieuKhamBenh;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class DialogChonThuoc extends JDialog {
	private JTextField editSoLuong;
	private JComboBox comboChonThuoc;
	private JButton btnOk;
	private JButton btnCancel;
	private JComboBox comboDonVi;
	private JComboBox comboCachDung;
	private JLabel lblCachDung;
	
	private DefaultComboBoxModel<String> comboChonThuocModel = new DefaultComboBoxModel<>();
	private DefaultComboBoxModel<String> comboDonViModel = new DefaultComboBoxModel<>();
	private DefaultComboBoxModel<String> comboCachDungModel = new DefaultComboBoxModel<>();
	
	/**
	 * Create the dialog.
	 */
	public DialogChonThuoc(PhieuKhamBenh phieukhambenh) {
		setTitle("Chon thuoc");
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 550, 130);
		getContentPane().setLayout(null);
		{
			JLabel lblThuoc = new JLabel("Thuoc");
			lblThuoc.setHorizontalAlignment(SwingConstants.LEFT);
			lblThuoc.setBounds(10, 11, 46, 14);
			getContentPane().add(lblThuoc);
		}
		
		comboChonThuoc = new JComboBox();
		comboChonThuoc.setBounds(10, 32, 130, 20);
		getContentPane().add(comboChonThuoc);
		
		JLabel lblSoLuong = new JLabel("So luong");
		lblSoLuong.setBounds(434, 11, 46, 14);
		getContentPane().add(lblSoLuong);
		
		editSoLuong = new JTextField();
		editSoLuong.setBounds(434, 32, 100, 20);
		getContentPane().add(editSoLuong);
		editSoLuong.setColumns(10);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(445, 67, 89, 23);
		getContentPane().add(btnCancel);
		
		btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int idloai = comboChonThuoc.getSelectedIndex();
				int iddonvi = comboDonVi.getSelectedIndex();
				int idcachdung = comboCachDung.getSelectedIndex();
				String sl = editSoLuong.getText();
				int soluong = 0;
				
				try {
					soluong = Integer.parseInt(sl);
				}
				catch (Exception ex) {
					ErrorLog.log(ex.getMessage());
				}
				
				if (idloai == -1 || iddonvi == -1 || idcachdung == -1
					|| sl.compareTo("") == 0 || soluong <= 0) {
					DialogBaoLoi dialog = new DialogBaoLoi("Thong tin khong hop le");
					dialog.setVisible(true);
				}
				else {
					String loai = comboChonThuocModel.getElementAt(idloai);
					String donvi = comboDonViModel.getElementAt(iddonvi);
					String cachdung = comboCachDungModel.getElementAt(idcachdung);
					
					DonThuoc dt = new DonThuoc(soluong, phieukhambenh, donvi, cachdung, loai);
					boolean res = DonThuocDAO.themDonThuoc(dt);
					if (!res) {
						DialogBaoLoi dialog = new DialogBaoLoi("Tao don thuoc that bai");
						dialog.setVisible(true);
					}
					else {
						phieukhambenh.getDonThuoc().add(dt);
					}
				}
				
				dispose();
			}
		});
		btnOk.setBounds(346, 67, 89, 23);
		getContentPane().add(btnOk);
		
		JLabel lblDonVi = new JLabel("Don vi");
		lblDonVi.setBounds(150, 11, 46, 14);
		getContentPane().add(lblDonVi);
		
		comboDonVi = new JComboBox();
		comboDonVi.setBounds(150, 32, 130, 20);
		getContentPane().add(comboDonVi);
		
		lblCachDung = new JLabel("Cach dung");
		lblCachDung.setBounds(294, 11, 60, 14);
		getContentPane().add(lblCachDung);
		
		comboCachDung = new JComboBox();
		comboCachDung.setBounds(294, 32, 130, 20);
		getContentPane().add(comboCachDung);
		
		this.initCombo();
	}
	
	private void initCombo() {
		comboChonThuoc.setModel(comboChonThuocModel);
		comboChonThuocModel.addElement("Thuoc loai 1");
		comboChonThuocModel.addElement("Thuoc loai 2");
		comboChonThuocModel.addElement("Thuoc loai 3");
		comboChonThuocModel.addElement("Thuoc loai 4");
		comboChonThuocModel.addElement("Thuoc loai 5");
		comboChonThuocModel.addElement("Thuoc loai 6");
		comboChonThuocModel.addElement("Thuoc loai 7");
		comboChonThuocModel.addElement("Thuoc loai 8");
		comboChonThuocModel.addElement("Thuoc loai 9");
		comboChonThuocModel.addElement("Thuoc loai 10");
		comboChonThuocModel.addElement("Thuoc loai 11");
		comboChonThuocModel.addElement("Thuoc loai 12");
		comboChonThuocModel.addElement("Thuoc loai 13");
		comboChonThuocModel.addElement("Thuoc loai 14");
		comboChonThuocModel.addElement("Thuoc loai 15");
		comboChonThuocModel.addElement("Thuoc loai 16");
		comboChonThuocModel.addElement("Thuoc loai 17");
		comboChonThuocModel.addElement("Thuoc loai 18");
		comboChonThuocModel.addElement("Thuoc loai 19");
		comboChonThuocModel.addElement("Thuoc loai 20");
		comboChonThuocModel.addElement("Thuoc loai 21");
		comboChonThuocModel.addElement("Thuoc loai 22");
		comboChonThuocModel.addElement("Thuoc loai 23");
		comboChonThuocModel.addElement("Thuoc loai 24");
		comboChonThuocModel.addElement("Thuoc loai 25");
		comboChonThuocModel.addElement("Thuoc loai 26");
		comboChonThuocModel.addElement("Thuoc loai 27");
		comboChonThuocModel.addElement("Thuoc loai 28");
		comboChonThuocModel.addElement("Thuoc loai 29");
		comboChonThuocModel.addElement("Thuoc loai 30");
		
		comboDonVi.setModel(comboDonViModel);
		comboDonViModel.addElement("Don vi 1");
		comboDonViModel.addElement("Don vi 2");
		
		comboCachDung.setModel(comboCachDungModel);
		comboCachDungModel.addElement("Cach dung 1");
		comboCachDungModel.addElement("Cach dung 2");
		comboCachDungModel.addElement("Cach dung 3");
		comboCachDungModel.addElement("Cach dung 4");
	}
}
