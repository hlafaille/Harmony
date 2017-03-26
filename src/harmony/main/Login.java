package harmony.main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.google.common.util.concurrent.FutureCallback;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.Javacord;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class Login extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static DiscordAPI api;
	private JTextField textField;
	private JPasswordField passwordField;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setTitle("Harmony - Login");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 342, 205);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(100, 76, 225, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(100, 107, 225, 20);
		contentPane.add(passwordField);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				api = Javacord.getApi(textField.getText(), passwordField.getText());
				api.connect(new FutureCallback<DiscordAPI>() {
					  @Override
					  public void onSuccess(final DiscordAPI api) {
						  System.out.println("Connected to Discord!");
						  System.out.println(api.getServers());
						  Servers.main();
					  }

					  @Override
					  public void onFailure(Throwable t) {
						  System.out.println("FAILED!");
					    t.printStackTrace();
					  }
					});
			}
		});
		btnNewButton.setBounds(100, 138, 225, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblHarmony = new JLabel("Harmony");
		lblHarmony.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblHarmony.setBounds(10, 11, 102, 23);
		contentPane.add(lblHarmony);
		
		JLabel lblEmail = new JLabel("E-Mail:");
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setBounds(44, 79, 46, 14);
		contentPane.add(lblEmail);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setBounds(27, 110, 63, 14);
		contentPane.add(lblPassword);
	}
}
