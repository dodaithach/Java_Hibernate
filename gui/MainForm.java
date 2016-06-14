package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListModel;

import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import dao.BenhNhanDAO;
import error.DialogBaoLoi;
import error.ErrorLog;
import pojo.BenhNhan;

import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainForm {

	private JFrame frmQuanLiPhong;
	private JTextField editHoTen;
	private JTextField editGioiTinh;
	private JTextField editNamSinh;
	private JTextField editDiaChi;
	private JList listBenhNhan;
	private JButton btnThem;
	private JButton btnSua;
	private JButton btnKham;
	private String currentDay = "";
	
	//
	// My model
	//
	private DefaultListModel<String> listModel = new DefaultListModel<>();
	private ArrayList<BenhNhan> dsKham = new ArrayList<>();
	private List<BenhNhan> benhNhanCu = null;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {					
					MainForm window = new MainForm();
					window.frmQuanLiPhong.setVisible(true);
				} catch (Exception ex) {
					ErrorLog.log(ex.getMessage());
				}
			}
		});
	}
	
	private void loadData() {
		this.benhNhanCu = BenhNhanDAO.layDSBenhNhan();
	}

	private void themBenhNhan() {
		// 1. Kiem tra so luong benh nhan tron danh sach kham
		if (dsKham.size() == 40) {
			DialogBaoLoi dialog = new DialogBaoLoi("So benh nhan khong vuot qua 40");
			dialog.setVisible(true);
			
			return;
		}
		
		// 2. Lay thong tin benh nhan
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
		
		BenhNhan bn = new BenhNhan(hoten, gioitinh, namsinh, diachi);
		
		// 3. Kiem tra xem co phai benh nhan cu hay khong
		int idx = -1;
		for (int i = 0; i < this.benhNhanCu.size(); i++) {
			BenhNhan bnCu = this.benhNhanCu.get(i);
			
			if (bn.equals(bnCu)) {
				idx = i;
				break;
			}
		}
		
		// 4. Cap nhat danh sach kham
		// 4.1. Benh nhan cu
		if (idx != -1) {
			bn = this.benhNhanCu.get(idx);
			
			// Kiem tra xem benh nhan co trong danh sach kham hay chua
			if (this.dsKham.contains(bn)) {
				DialogBaoLoi dialog = new DialogBaoLoi("Benh nhan da co trong danh sach kham");
				dialog.setVisible(true);
				
				return;
			}
			else {
				this.dsKham.add(bn);
				
				this.updateView();
			}
		}
		// 4.2. Benh nhan moi
		else {
			// 4.2.1. Them benh nhan vao csdl
			boolean res = BenhNhanDAO.themBenhNhan(bn);
			
			if (!res) {
				DialogBaoLoi dialog = new DialogBaoLoi("Them benh nhan moi that bai");
				dialog.setVisible(true);
			}
			else {
				DialogBaoLoi dialog = new DialogBaoLoi("Da them benh nhan moi");
				dialog.setVisible(true);
			}
			
			// 4.2.2. Load lai danh sach benh nhan cu
			this.loadData();
			
			// 4.2.3. Them benh nhan vao danh sach kham
			this.dsKham.add(bn);
			
			this.updateView();
		}
	}
	
	private void suaThongTin() {
		// Lay benh nhan dang duoc chon
		int idx = listBenhNhan.getSelectedIndex();
		if (idx == -1) {
			DialogBaoLoi dialog = new DialogBaoLoi("Chua co benh nhan nao duoc chon");
			dialog.setVisible(true);
			
			return;
		}
		
		BenhNhan bn = this.dsKham.get(idx);
		
		DialogSuaThongTin dialog = new DialogSuaThongTin(bn);
		dialog.setVisible(true);
		
		this.updateView();
	}
	
	private void kham() {
		// Lay benh nhan dang duoc chon
		int idx = listBenhNhan.getSelectedIndex();
		if (idx == -1) {
			DialogBaoLoi dialog = new DialogBaoLoi("Chua co benh nhan nao duoc chon");
			dialog.setVisible(true);
			
			return;
		}
		
		BenhNhan bn = dsKham.get(idx);
				
		DialogKham dialog = new DialogKham(bn);
		dialog.setVisible(true);
		
		dsKham.remove(idx);
		this.updateView();
	}
	
	private void updateView() {
		// 1. Refresh dsBenhNhan
		
		// 2. Update list
		this.listModel.clear();
		for (int i = 0; i < this.dsKham.size(); i++) {
			BenhNhan bn = dsKham.get(i);
			String data = "" + bn.getHoTen();
			
			listModel.addElement(data);
		}
	}
	
	/**
	 * Create the application.
	 */
	public MainForm() {		
		ErrorLog.flush();
		initialize();
		
		this.listBenhNhan.setModel(this.listModel);
		this.loadData();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("deprecation")
	private void initialize() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception ex) {
			ErrorLog.log(ex.getMessage());
		}
		
		Calendar cal = Calendar.getInstance();
		this.currentDay = "" + cal.get(Calendar.DATE) + "/" + (cal.get(Calendar.MONTH) + 1)
							+ "/" + cal.get(Calendar.YEAR);
		
		frmQuanLiPhong = new JFrame();
		frmQuanLiPhong.setTitle("Quan Li Phong Mach");
		frmQuanLiPhong.setResizable(false);
		frmQuanLiPhong.setBounds(100, 100, 550, 400);
		frmQuanLiPhong.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmQuanLiPhong.getContentPane().setLayout(null);
		
		JLabel lblNgay = new JLabel("Ngay: " + this.currentDay);
		lblNgay.setHorizontalAlignment(SwingConstants.CENTER);
		lblNgay.setForeground(Color.RED);
		lblNgay.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNgay.setBounds(222, 11, 100, 14);
		frmQuanLiPhong.getContentPane().add(lblNgay);
		
		JLabel lblHoTen = new JLabel("Ho Ten");
		lblHoTen.setBounds(50, 47, 46, 14);
		frmQuanLiPhong.getContentPane().add(lblHoTen);
		
		JLabel lblGioiTinh = new JLabel("Gioi tinh");
		lblGioiTinh.setBounds(181, 47, 46, 14);
		frmQuanLiPhong.getContentPane().add(lblGioiTinh);
		
		JLabel lblNamSinh = new JLabel("Nam sinh");
		lblNamSinh.setBounds(312, 47, 46, 14);
		frmQuanLiPhong.getContentPane().add(lblNamSinh);
		
		JLabel lblDiaChi = new JLabel("Dia chi");
		lblDiaChi.setBounds(443, 47, 46, 14);
		frmQuanLiPhong.getContentPane().add(lblDiaChi);
		
		editHoTen = new JTextField();
		editHoTen.setBounds(16, 72, 115, 20);
		frmQuanLiPhong.getContentPane().add(editHoTen);
		editHoTen.setColumns(10);
		
		editGioiTinh = new JTextField();
		editGioiTinh.setColumns(10);
		editGioiTinh.setBounds(147, 72, 115, 20);
		frmQuanLiPhong.getContentPane().add(editGioiTinh);
		
		editNamSinh = new JTextField();
		editNamSinh.setColumns(10);
		editNamSinh.setBounds(278, 72, 115, 20);
		frmQuanLiPhong.getContentPane().add(editNamSinh);
		
		editDiaChi = new JTextField();
		editDiaChi.setColumns(10);
		editDiaChi.setBounds(409, 72, 115, 20);
		frmQuanLiPhong.getContentPane().add(editDiaChi);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(16, 113, 377, 247);
		frmQuanLiPhong.getContentPane().add(scrollPane);
		
		listBenhNhan = new JList();
		listBenhNhan.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(listBenhNhan);
		
		btnThem = new JButton("Them");
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				themBenhNhan();
			}
		});
		btnThem.setBounds(422, 113, 89, 23);
		frmQuanLiPhong.getContentPane().add(btnThem);
		
		btnSua = new JButton("Sua");
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				suaThongTin();
			}
		});
		btnSua.setBounds(422, 147, 89, 23);
		frmQuanLiPhong.getContentPane().add(btnSua);
		
		btnKham = new JButton("Kham");
		btnKham.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				kham();
			}
		});
		btnKham.setBounds(422, 181, 89, 23);
		frmQuanLiPhong.getContentPane().add(btnKham);
	}
}
