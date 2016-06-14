package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.BenhNhanDAO;
import dao.DonThuocDAO;
import dao.PhieuKhamBenhDAO;
import error.DialogBaoLoi;
import pojo.BenhNhan;
import pojo.DonThuoc;
import pojo.PhieuKhamBenh;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class DialogKham extends JDialog {
	private JTextField editTrieuChung;
	private JList listThuoc;
	private JButton btnThemThuoc;
	private JButton btnTraCuu;
	private JButton btnOk;
	private JButton btnCancel;
	private JComboBox comboBenh;
	
	private String currentDay = "";
	
	private DefaultListModel<String> listmodel = new DefaultListModel<>();
	private DefaultComboBoxModel<String> combomodel = new DefaultComboBoxModel<>();
	
	private PhieuKhamBenh phieukhambenh;
	
	/**
	 * Create the dialog.
	 */
	public DialogKham(BenhNhan bn) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				PhieuKhamBenhDAO.xoaPhieuKhamBenh(phieukhambenh);
			}
		});
		Calendar cal = Calendar.getInstance();
		this.currentDay = "" + cal.get(Calendar.DATE) + "/" + (cal.get(Calendar.MONTH) + 1)
							+ "/" + cal.get(Calendar.YEAR);		
		
		phieukhambenh = new PhieuKhamBenh(Calendar.getInstance().getTime(), "default", "default");
		phieukhambenh.setBenhNhan(bn);
		PhieuKhamBenhDAO.themPhieuKhamBenh(phieukhambenh);
		
		setModal(true);
		setTitle("Phieu Kham Benh");
		setResizable(false);
		setBounds(100, 100, 550, 400);
		getContentPane().setLayout(null);
		{
			JLabel labelHoTen = new JLabel("Ho Ten: " + bn.getHoTen());
			labelHoTen.setForeground(Color.RED);
			labelHoTen.setFont(new Font("Tahoma", Font.PLAIN, 12));
			labelHoTen.setHorizontalAlignment(SwingConstants.LEFT);
			labelHoTen.setBounds(10, 11, 180, 14);
			getContentPane().add(labelHoTen);
		}
		{
			JLabel labelNgay = new JLabel("Ngay: " + this.currentDay);
			labelNgay.setForeground(Color.RED);
			labelNgay.setFont(new Font("Tahoma", Font.PLAIN, 12));
			labelNgay.setHorizontalAlignment(SwingConstants.RIGHT);
			labelNgay.setBounds(354, 11, 180, 14);
			getContentPane().add(labelNgay);
		}
		{
			JLabel lblTrieuChung = new JLabel("Trieu chung");
			lblTrieuChung.setHorizontalAlignment(SwingConstants.CENTER);
			lblTrieuChung.setBounds(89, 36, 90, 14);
			getContentPane().add(lblTrieuChung);
		}
		{
			JLabel lblDuDoanBenh = new JLabel("Du doan benh");
			lblDuDoanBenh.setHorizontalAlignment(SwingConstants.CENTER);
			lblDuDoanBenh.setBounds(364, 36, 90, 14);
			getContentPane().add(lblDuDoanBenh);
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 95, 524, 229);
			getContentPane().add(scrollPane);
			{
				listThuoc = new JList();
				listThuoc.setModel(listmodel);
				scrollPane.setViewportView(listThuoc);
			}
		}
		{
			btnOk = new JButton("OK");
			btnOk.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String trieuchung = editTrieuChung.getText();
					int idx = comboBenh.getSelectedIndex();
					
					if (trieuchung.compareTo("") == 0 || idx == -1) {
						DialogBaoLoi dialog = new DialogBaoLoi("Thong tin khong hop le");
						dialog.setVisible(true);
						
						return;
					}
					
					String benh = combomodel.getElementAt(idx);
					phieukhambenh.setTenBenh(benh);
					phieukhambenh.setTrieuChung(trieuchung);
					
					boolean res = PhieuKhamBenhDAO.capNhatPhieuKhamBenh(phieukhambenh);
					if (!res) {
						DialogBaoLoi dialog = new DialogBaoLoi("Cap nhat phieu kham benh that bai");
						dialog.setVisible(true);
						
						return;
					}
					
					bn.getPKB().add(phieukhambenh);
					
					dispose();
				}
			});
			btnOk.setBounds(346, 337, 89, 23);
			getContentPane().add(btnOk);
		}
		{
			btnCancel = new JButton("Cancel");
			btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					PhieuKhamBenhDAO.xoaPhieuKhamBenh(phieukhambenh);
					dispose();
				}
			});
			btnCancel.setBounds(445, 337, 89, 23);
			getContentPane().add(btnCancel);
		}
		{
			editTrieuChung = new JTextField();
			editTrieuChung.setBounds(10, 61, 250, 23);
			getContentPane().add(editTrieuChung);
			editTrieuChung.setColumns(10);
		}
		{
			btnTraCuu = new JButton("Tra cuu lich su");
			btnTraCuu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					DialogTraCuu dialog = new DialogTraCuu(bn);
					dialog.setVisible(true);
				}
			});
			btnTraCuu.setBounds(225, 335, 111, 23);
			getContentPane().add(btnTraCuu);
		}
		{
			btnThemThuoc = new JButton("Them thuoc");
			btnThemThuoc.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					DialogChonThuoc dialog = new DialogChonThuoc(phieukhambenh);
					dialog.setVisible(true);
					
					updateList();
				}
			});
			btnThemThuoc.setBounds(126, 335, 89, 23);
			getContentPane().add(btnThemThuoc);
		}
		{
			comboBenh = new JComboBox();
			comboBenh.setBounds(284, 61, 250, 23);
			getContentPane().add(comboBenh);
		}
		
		this.initCombo();
	}
	
	private void updateList() {
		listmodel.clear();
		
		Set<DonThuoc> ds = phieukhambenh.getDonThuoc();
		for (DonThuoc dt : ds) {
			String data = "Loai thuoc: " + dt.getLoaiThuoc() 
							+ " | Don vi: " + dt.getDonVi()
							+ " | So luong: " + dt.getSoLuong()
							+ " | Cach dung: " + dt.getCachDung();
			
			listmodel.addElement(data);
		}
	}
	
	private void initCombo() {
		comboBenh.setModel(combomodel);
		
		combomodel.addElement("Benh loai 1");
		combomodel.addElement("Benh loai 2");
		combomodel.addElement("Benh loai 3");
		combomodel.addElement("Benh loai 4");
		combomodel.addElement("Benh loai 5");
	}

}
