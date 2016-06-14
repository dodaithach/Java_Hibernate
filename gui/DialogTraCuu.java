package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.BenhNhanDAO;
import dao.PhieuKhamBenhDAO;
import error.DialogBaoLoi;
import error.ErrorLog;
import pojo.BenhNhan;
import pojo.PhieuKhamBenh;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.awt.event.ActionEvent;

public class DialogTraCuu extends JDialog {
	private JTextField editHoTen;
	private JTextField editNgayKham;
	private JTextField editBenh;
	private JTextField editTrieuChung;
	private JList listKetQua;
	private JButton btnTraCuu;
	private JButton btnCancel;
	
	// List model
	private DefaultListModel<String> listModel = new DefaultListModel<>();
	
	/**
	 * Create the dialog.
	 */
	public DialogTraCuu(BenhNhan bn) {
		setTitle("Tra cuu");
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 538, 386);
		getContentPane().setLayout(null);
		{
			JLabel lblHoTen = new JLabel("Ho ten");
			lblHoTen.setHorizontalAlignment(SwingConstants.CENTER);
			lblHoTen.setBounds(47, 11, 46, 14);
			getContentPane().add(lblHoTen);
		}
		{
			JLabel lblNgayKham = new JLabel("Ngay kham");
			lblNgayKham.setHorizontalAlignment(SwingConstants.CENTER);
			lblNgayKham.setBounds(167, 11, 67, 14);
			getContentPane().add(lblNgayKham);
		}
		{
			JLabel lblBenh = new JLabel("Benh");
			lblBenh.setHorizontalAlignment(SwingConstants.CENTER);
			lblBenh.setBounds(307, 11, 46, 14);
			getContentPane().add(lblBenh);
		}
		{
			JLabel lblTrieuChung = new JLabel("Trieu chung");
			lblTrieuChung.setHorizontalAlignment(SwingConstants.CENTER);
			lblTrieuChung.setBounds(427, 11, 67, 14);
			getContentPane().add(lblTrieuChung);
		}
		{
			editHoTen = new JTextField();
			editHoTen.setBounds(10, 36, 120, 20);
			getContentPane().add(editHoTen);
			editHoTen.setColumns(10);
		}
		{
			editNgayKham = new JTextField();
			editNgayKham.setColumns(10);
			editNgayKham.setBounds(140, 36, 120, 20);
			getContentPane().add(editNgayKham);
		}
		{
			editBenh = new JTextField();
			editBenh.setColumns(10);
			editBenh.setBounds(270, 36, 120, 20);
			getContentPane().add(editBenh);
		}
		{
			editTrieuChung = new JTextField();
			editTrieuChung.setColumns(10);
			editTrieuChung.setBounds(400, 36, 120, 20);
			getContentPane().add(editTrieuChung);
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 67, 510, 249);
			getContentPane().add(scrollPane);
			{
				listKetQua = new JList();
				listKetQua.setModel(listModel);
				scrollPane.setViewportView(listKetQua);
			}
		}
		{
			btnCancel = new JButton("Cancel");
			btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btnCancel.setBounds(431, 327, 89, 23);
			getContentPane().add(btnCancel);
		}
		{
			btnTraCuu = new JButton("Tra cuu");
			btnTraCuu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String hoten = editHoTen.getText();
					
					String date = editNgayKham.getText();
					SimpleDateFormat formatter = new SimpleDateFormat("dd/mm/yyyy");
					Date ngaykham = Calendar.getInstance().getTime();
					try {
						Date tmp = formatter.parse(date);
						ngaykham = tmp;
					}
					catch (Exception ex) {
						ErrorLog.log(ex.getMessage());
					}
					finally {
						String benh = editBenh.getText();
						String trieuchung = editTrieuChung.getText();
						
						if (hoten.compareTo("") == 0
							&& date.compareTo("") == 0
							&& benh.compareTo("") == 0
							&& trieuchung.compareTo("") == 0) {
							DialogBaoLoi dialog = new DialogBaoLoi("Thong tin tra cuu khong hop le");
							dialog.setVisible(true);
							
							return;
						}
						
						List<PhieuKhamBenh> listpkb = new ArrayList<>();
						
						if (hoten.compareTo("") != 0) {
							List<BenhNhan> listbn = BenhNhanDAO.layDSBenhNhan(hoten);
							
							for (BenhNhan bn : listbn) {
								Set<PhieuKhamBenh> setpkb = bn.getPKB();
								
								for (PhieuKhamBenh p : setpkb) {
									if (dateToString(p.getNgayKham()).contains(date)
										&& p.getTenBenh().contains(benh)
										&& p.getTrieuChung().contains(trieuchung)) {
										listpkb.add(p);
									}
								}
							}
						}
						else if (date.compareTo("") != 0) {
							List<PhieuKhamBenh> pkb = PhieuKhamBenhDAO.layDSPKB(ngaykham);
							for (PhieuKhamBenh p : pkb) {
								if (p.getTenBenh().contains(benh)
									&& p.getTrieuChung().contains(trieuchung)) {
										listpkb.add(p);
									}
							}
						}
						else if (benh.compareTo("") != 0) {
							List<PhieuKhamBenh> pkb = PhieuKhamBenhDAO.layDSPKBTheoBenh(benh);
							for (PhieuKhamBenh p : pkb) {
								if (p.getTrieuChung().contains(trieuchung)) {
										listpkb.add(p);
									}
							}
						}
						else {
							listpkb = PhieuKhamBenhDAO.layDSPKBTheoTrieuChung(trieuchung);
						}
						
						updateList(listpkb);
					}
				}
			});
			btnTraCuu.setBounds(332, 327, 89, 23);
			getContentPane().add(btnTraCuu);
		}
		
		//
		// Khoi tao
		//
		editHoTen.setText(bn.getHoTen());
		
		List<PhieuKhamBenh> list = new ArrayList<>();
		Set<PhieuKhamBenh> setpkb = bn.getPKB();
		
		for (PhieuKhamBenh p : setpkb) {
			if (p.getTenBenh().compareTo("default") != 0
				&& p.getTrieuChung().compareTo("default") != 0)
				list.add(p);
		}
		
		// Init list
		updateList(list);
	}
	
	private String dateToString(Date date) {
		String ngay = "" + date.getDate() + "/" + (date.getMonth() + 1) + "/" + (date.getYear() + 1900);
		
		return ngay;
	}
	
	private String makeString(PhieuKhamBenh p) {
		String ngay = dateToString(p.getNgayKham());
		
		return "Ho ten: " + p.getBenhNhan().getHoTen() + " | "
				+ "Ngay kham: " + ngay + " | "
				+ "Trieu chung: " + p.getTrieuChung() + " | "
				+ "Ten benh: " + p.getTenBenh();
	}
	
	private void updateList(List<PhieuKhamBenh> listpkb) {
		listModel.clear();
		
		for (int i = 0; i < listpkb.size(); i++) {
			PhieuKhamBenh p = listpkb.get(i);
			listModel.addElement(makeString(p));
		}
	}
}
