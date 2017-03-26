package harmony.main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.google.common.util.concurrent.FutureCallback;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.Server;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.listener.message.MessageCreateListener;

import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.StringTokenizer;
import java.util.concurrent.ExecutionException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Servers extends JFrame {

	private JPanel contentPane;

	public static Server server;
	/**
	 * Launch the application.
	 */
	public static void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Servers frame = new Servers();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	@SuppressWarnings("unchecked")
	public Servers() throws InterruptedException, ExecutionException {
		setTitle("Harmony - Servers");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox comboBox = new JComboBox(Login.api.getServers().toArray());
		comboBox.setMaximumRowCount(32);
		//comboBox.addItem(Login.api.getServers().toArray());
		comboBox.setBounds(95, 11, 253, 20);
		contentPane.add(comboBox);
		
		JButton btnConnect = new JButton("Connect!");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 String s  = comboBox.getSelectedItem().toString();
				    System.out.println(s=s.replaceAll("[^0-9]", ""));
				    StringTokenizer tok = new StringTokenizer(s,"`~!@#$%^&*()-_+=\\.,><?");
				    String s1 = "";
				    while(tok.hasMoreTokens()){
				        s1+= tok.nextToken();
				    }
				server = Login.api.getServerById(s1);
				Chat.main();
				//System.out.println(server.getId());
			}
		});
		btnConnect.setBounds(175, 42, 89, 23);
		contentPane.add(btnConnect);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(Login.api.getYourself().getAvatar().get()));
		lblNewLabel.setBounds(95, 76, 253, 184);
		contentPane.add(lblNewLabel);
	}

}
