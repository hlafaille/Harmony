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
import harmony.servermanager.CreateServer;

import javax.swing.JComboBox;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.StringTokenizer;
import java.util.concurrent.ExecutionException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Font;
import javax.swing.JSeparator;
import java.awt.Toolkit;

public class Servers extends JFrame {

	private static JPanel contentPane;

	public static Server server;
	
	static JComboBox comboBox;
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
					Servers frame = new Servers();
					frame.setVisible(true);
					refreshServers();
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
		setIconImage(Toolkit.getDefaultToolkit().getImage(Servers.class.getResource("/harmony/main/harmony-heart-icon-79964.png")));
		setTitle("Harmony - Servers");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		comboBox = new JComboBox(Login.api.getServers().toArray());
		comboBox.setMaximumRowCount(32);
		//comboBox.addItem(Login.api.getServers().toArray());
		comboBox.setBounds(16, 46, 253, 20);
		contentPane.add(comboBox);
		
		JButton btnConnect = new JButton("Connect!");
		btnConnect.setFont(new Font("Tahoma", Font.BOLD, 11));
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
				setVisible(false);
				dispose();
				//System.out.println(server.getId());
			}
		});
		btnConnect.setBounds(279, 45, 155, 23);
		contentPane.add(btnConnect);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(Login.api.getYourself().getAvatar().get()));
		lblNewLabel.setBounds(16, 110, 150, 150);
		contentPane.add(lblNewLabel);
		
		JLabel lblChooseAServer = new JLabel("choose a server.");
		lblChooseAServer.setFont(new Font("Segoe UI Light", Font.PLAIN, 21));
		lblChooseAServer.setBounds(10, 11, 166, 20);
		contentPane.add(lblChooseAServer);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 35, 424, 2);
		contentPane.add(separator);
		
		JLabel serverIcon = new JLabel("");
		serverIcon.setIcon(new ImageIcon(Servers.class.getResource("/harmony/main/rsz_harmony-heart-icon-79964.png")));
		serverIcon.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(Login.api.getYourself().getAvatar().get()));
		serverIcon.setBounds(284, 110, 150, 150);
		contentPane.add(serverIcon);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login.api.disconnect();
				Login.main(null);
				setVisible(false);
				dispose();
			}
		});
		btnLogout.setBounds(345, 11, 89, 23);
		contentPane.add(btnLogout);
		
		JButton btnNewButton = new JButton("Create");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateServer.main();
				setVisible(false);
				dispose();
			}
		});
		btnNewButton.setBounds(279, 76, 155, 23);
		contentPane.add(btnNewButton);
	}
	public static void refreshServers(){
		DefaultComboBoxModel model = new DefaultComboBoxModel( Login.api.getServers().toArray() );
		comboBox.setModel(model);
	}
}
