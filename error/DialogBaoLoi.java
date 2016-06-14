package error;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DialogBaoLoi extends JDialog {
	/**
	 * Create the dialog.
	 */
	public DialogBaoLoi(String err) {
		setTitle("Loi");
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 401, 100);
		getContentPane().setLayout(null);
		{
			JLabel labelError = new JLabel(err);
			labelError.setForeground(Color.RED);
			labelError.setHorizontalAlignment(SwingConstants.CENTER);
			labelError.setBounds(15, 11, 370, 14);
			getContentPane().add(labelError);
		}
		{
			JButton btnOk = new JButton("OK");
			btnOk.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btnOk.setBounds(153, 37, 89, 23);
			getContentPane().add(btnOk);
		}
	}

}
