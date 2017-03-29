package harmony.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.google.common.util.concurrent.FutureCallback;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.entities.message.MessageHistory;
import de.btobastian.javacord.listener.message.MessageCreateListener;
import harmony.servermanager.AdminPanel;

import javax.swing.JTextPane;
import javax.swing.ListModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import java.awt.Toolkit;

public class Chat extends JFrame {

	static JPanel contentPane;
	private JTextField textField;
	static String s1;
	static JList list;
	static JComboBox comboBox;
	static JLabel topic;
	static JList usersList;
	static JScrollPane scrollPane_1;
	private JButton btnAdmin;
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
					Chat frame = new Chat();
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
	public Chat() throws InterruptedException, ExecutionException {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Chat.class.getResource("/harmony/main/harmony-heart-icon-79964.png")));
		setTitle("Harmony - Chat");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1107, 537);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		
		Login.api.registerListener(new MessageCreateListener() {
			  @Override
			  public void onMessageCreate(DiscordAPI api, Message message) {
				  refreshChat();
			  }
			});
		contentPane.setLayout(null);

		
		comboBox = new JComboBox(Servers.server.getChannels().toArray());
		comboBox.setBounds(10, 12, 326, 22);
		contentPane.add(comboBox);
			
		textField = new JTextField();
		textField.setBounds(10, 479, 791, 20);
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER){
					Servers.server.getChannelById(s1).sendMessage(textField.getText());
					textField.setText("");
					refreshChat();
				}
			}
		});
		contentPane.add(textField);
		textField.setColumns(10);
		
		
		JButton btnSelect = new JButton("Select");
		btnSelect.setBounds(346, 11, 89, 23);
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshChat();
			}
		});
		contentPane.add(btnSelect);
		
		JButton btnDisconnect = new JButton("Disconnect");
		btnDisconnect.setBounds(445, 11, 89, 23);
		btnDisconnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login.api.disconnect();
				setVisible(false);
				Servers.main();
				dispose();
				
			}
		});
		contentPane.add(btnDisconnect);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(813, 45, 3, 454);
		separator.setOrientation(SwingConstants.VERTICAL);
		contentPane.add(separator);
		
		topic = new JLabel("");
		topic.setBounds(558, 14, 421, 14);
		topic.setFont(new Font("Tahoma", Font.ITALIC, 11));
		
		contentPane.add(topic);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(822, 45, 275, 454);
		contentPane.add(scrollPane);
		
		usersList = new JList();
		scrollPane.setViewportView(usersList);
		usersList.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		usersList.setListData(Servers.server.getMembers().toArray());
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 45, 791, 423);
		contentPane.add(scrollPane_1);
		JScrollBar vertical = scrollPane_1.getVerticalScrollBar();
		vertical.setValue( vertical.getMaximum() );
	    
		
		
		list = new JList();
		scrollPane_1.setViewportView(list);
		list.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		list.setAutoscrolls(true);
		list.setEnabled(false);
		list.setForeground(new Color(0,0,0));
		
		btnAdmin = new JButton("Admin");
		btnAdmin.setEnabled(false);
		btnAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminPanel.main();
				setVisible(false);
				dispose();
				
			}
		});
		btnAdmin.setBounds(1008, 12, 89, 23);
		contentPane.add(btnAdmin);
		list.updateUI();
		refreshChat();
		
	}
	
	public static void refreshChat(){
		String s  = comboBox.getSelectedItem().toString();
	    System.out.println(s=s.replaceAll("[^0-9]", ""));
	    StringTokenizer tok = new StringTokenizer(s,"`~!@#$%^&*()-_+=\\.,><?");
	    s1 = "";
	    
	    while(tok.hasMoreTokens()){
	        s1+= tok.nextToken();
	    }
	    usersList.setListData(Servers.server.getMembers().toArray());
	    topic.setText(Servers.server.getChannelById(s1).getTopic());
	    Future<MessageHistory> future = Servers.server.getChannelById(s1).getMessageHistory(40);
	    try{
	    	//System.out.println(future.get().getMessages().toArray());
			list.setListData(future.get().getMessagesSorted().toArray());
			list.setAutoscrolls(true);
			list.setEnabled(false);
			list.setForeground(new Color(0,0,0));
		
	    } catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ExecutionException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
