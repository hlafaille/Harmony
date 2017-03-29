package harmony.servermanager;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import harmony.main.Login;
import harmony.main.Servers;

public class CreateTextChannel extends JFrame {

	private JPanel contentPane;

	private JTextField txtServerName;
	private JButton btnCreate;
	/**
	 * Launch the application.
	 */
	public static void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateTextChannel frame = new CreateTextChannel();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CreateTextChannel() {
		setResizable(false);
		setTitle("Harmony - Create Server");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 323, 152);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCreateAServer = new JLabel("create a text channel");
		lblCreateAServer.setVerticalAlignment(SwingConstants.TOP);
		lblCreateAServer.setFont(new Font("Segoe UI Light", Font.PLAIN, 24));
		lblCreateAServer.setBounds(10, 0, 258, 47);
		contentPane.add(lblCreateAServer);
		
		txtServerName = new JTextField();
		txtServerName.setBounds(10, 46, 288, 20);
		contentPane.add(txtServerName);
		txtServerName.setColumns(10);
		
		btnCreate = new JButton("Create!");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Servers.server.createChannel(txtServerName.getText());
				setVisible(false);
				AdminPanel.reshow();
				AdminPanel.refreshText();
				dispose();
			}
		});
		btnCreate.setBounds(218, 89, 89, 23);
		contentPane.add(btnCreate);
	}

}
