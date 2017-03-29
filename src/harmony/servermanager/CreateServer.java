package harmony.servermanager;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import de.btobastian.javacord.entities.Server;
import harmony.main.Login;
import harmony.main.Servers;

import javax.swing.JLabel;
import java.awt.Font;import java.awt.JobAttributes.DialogType;

import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CreateServer extends JFrame {

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
					try {
			            // Set System L&F
			        UIManager.setLookAndFeel(
			            UIManager.getSystemLookAndFeelClassName());
				    } 
				    catch (UnsupportedLookAndFeelException e) {
				       // handle exception
				    }
				    catch (ClassNotFoundException e) {
				       // handle exception
				    }
				    catch (InstantiationException e) {
				       // handle exception
				    }
				    catch (IllegalAccessException e) {
				       // handle exception
				    }
					CreateServer frame = new CreateServer();
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
	public CreateServer() {
		setResizable(false);
		setTitle("Harmony - Create Server");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 323, 152);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCreateAServer = new JLabel("create a server");
		lblCreateAServer.setVerticalAlignment(SwingConstants.TOP);
		lblCreateAServer.setFont(new Font("Segoe UI Light", Font.PLAIN, 24));
		lblCreateAServer.setBounds(10, 0, 188, 47);
		contentPane.add(lblCreateAServer);
		
		txtServerName = new JTextField();
		txtServerName.setText("Server Name");
		txtServerName.setBounds(10, 46, 288, 20);
		contentPane.add(txtServerName);
		txtServerName.setColumns(10);
		
		btnCreate = new JButton("Create!");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login.api.createServer(txtServerName.getText());
				setVisible(false);
				Servers.main();
				dispose();
			}
		});
		btnCreate.setBounds(218, 89, 89, 23);
		contentPane.add(btnCreate);
	}
}
