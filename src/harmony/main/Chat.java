package harmony.main;

import java.awt.BorderLayout;
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
import de.btobastian.javacord.entities.message.MessageHistory;

import javax.swing.JTextPane;
import javax.swing.ListModel;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Chat extends JFrame {

	static JPanel contentPane;
	private JTextField textField;
	static String s1;
	static JList list;
	static JComboBox comboBox;
	/**
	 * Launch the application.
	 */
	public static void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Chat frame = new Chat();
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		Timer timer = new Timer();
		timer.schedule(new ChatTimer(), 0, 1000);
	}

	/**
	 * Create the frame.
	 */
	public Chat() {
		setTitle("Harmony - Chat");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 817, 537);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		comboBox = new JComboBox(Servers.server.getChannels().toArray());
		comboBox.setBounds(10, 11, 326, 20);
		contentPane.add(comboBox);
			
		textField = new JTextField();
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
		textField.setBounds(10, 479, 791, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		
		JButton btnSelect = new JButton("Select");
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSelect.setBounds(339, 10, 89, 23);
		contentPane.add(btnSelect);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 DefaultListModel listModel = (DefaultListModel) list.getModel();
			        listModel.removeAllElements();
			}
		});
		
		btnClear.setBounds(438, 10, 89, 23);
		contentPane.add(btnClear);
		
		
		
	}
	public static void refreshChat(){
		String s  = comboBox.getSelectedItem().toString();
	    System.out.println(s=s.replaceAll("[^0-9]", ""));
	    StringTokenizer tok = new StringTokenizer(s,"`~!@#$%^&*()-_+=\\.,><?");
	    s1 = "";
	    while(tok.hasMoreTokens()){
	        s1+= tok.nextToken();
	    }
	    Future<MessageHistory> future = Servers.server.getChannelById(s1).getMessageHistory(10);
	    try{
	    	//System.out.println(future.get().getMessages().toArray());
			list = new JList(future.get().getMessagesSorted().toArray());
			list.setBounds(10, 42, 791, 426);
			contentPane.add(list);
			list.updateUI();
	    } catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ExecutionException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
