package harmony.servermanager;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.border.TitledBorder;

import harmony.main.Chat;
import harmony.main.Servers;

import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.Window.Type;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.StringTokenizer;

import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminPanel extends JFrame {

	private JPanel contentPane;
	static JList voice;
	static JList text;
	static JList users;
	static AdminPanel frame;
	/**
	 * Launch the application.
	 */
	public static void main() {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new AdminPanel();
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
	public AdminPanel() {
		setResizable(false);
		setTitle("Harmony - Admin Panel");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 758, 962);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAdminPanel = new JLabel("admin panel");
		lblAdminPanel.setFont(new Font("Segoe UI Light", Font.PLAIN, 22));
		lblAdminPanel.setBounds(10, 11, 128, 39);
		contentPane.add(lblAdminPanel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Text Channels", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(20, 53, 381, 427);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton createTextBtn = new JButton("Create");
		createTextBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateTextChannel.main();
				refreshText();
			}
		});
		createTextBtn.setBounds(10, 22, 89, 23);
		panel.add(createTextBtn);
		
		JButton delTextBtn = new JButton("Delete");
		delTextBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s  = text.getSelectedValue().toString();
			    System.out.println(s=s.replaceAll("[^0-9]", ""));
			    StringTokenizer tok = new StringTokenizer(s,"`~!@#$%^&*()-_+=\\.,><?");
			    String s1 = "";
			    while(tok.hasMoreTokens()){
			        s1+= tok.nextToken();
			    }
				Servers.server.getChannelById(s1).delete();
				refreshText();
			}
		});
		delTextBtn.setBounds(109, 22, 89, 23);
		panel.add(delTextBtn);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 55, 361, 361);
		panel.add(scrollPane);
		
		text = new JList();
		scrollPane.setViewportView(text);
		refreshText();
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Users", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(411, 53, 318, 864);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnKick = new JButton("Kick");
		btnKick.setBounds(10, 21, 89, 23);
		panel_1.add(btnKick);
		
		JButton btnBan = new JButton("Ban");
		btnBan.setBounds(109, 21, 89, 23);
		panel_1.add(btnBan);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 55, 298, 798);
		panel_1.add(scrollPane_1);
		
		users = new JList();
		scrollPane_1.setViewportView(users);
		refreshUsers();
		
		JLabel servername = new JLabel("");
		servername.setHorizontalAlignment(SwingConstants.RIGHT);
		servername.setBounds(470, 28, 259, 14);
		contentPane.add(servername);
		servername.setText(Servers.server.getName());
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Voice Channels", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setBounds(20, 490, 381, 427);
		contentPane.add(panel_2);
		
		JButton button = new JButton("Create");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateVoiceChannel.main();
				refreshVoice();
			}
		});
		button.setBounds(10, 22, 89, 23);
		panel_2.add(button);
		
		JButton button_1 = new JButton("Delete");
		button_1.setBounds(109, 22, 89, 23);
		panel_2.add(button_1);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 55, 361, 361);
		panel_2.add(scrollPane_2);
		
		voice = new JList();
		scrollPane_2.setViewportView(voice);
		refreshVoice();
		
		JLabel lblonlyWorksIf = new JLabel("(Only works if you have permissions)");
		lblonlyWorksIf.setBounds(135, 28, 176, 14);
		contentPane.add(lblonlyWorksIf);

	}
	 @Override
	    public void dispose(){
		 	setVisible(false);
		 	Chat.main();
	    }
	public static void reshow(){
		frame.setVisible(true);
		refreshText();
		refreshVoice();
		refreshUsers();
		
		text.setListData(Servers.server.getChannels().toArray());
		text.updateUI();
	}
	public static void refreshText(){
		text.setListData(Servers.server.getChannels().toArray());
		text.updateUI();
	}
	public static void refreshVoice(){
		voice.setListData(Servers.server.getVoiceChannels().toArray());
	}
	public static void refreshUsers(){
		users.setListData(Servers.server.getMembers().toArray());
	}
}
