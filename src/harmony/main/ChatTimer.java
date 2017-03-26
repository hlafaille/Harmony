package harmony.main;

import java.util.TimerTask;

public class ChatTimer extends TimerTask{
	public  void run(){
		Chat.refreshChat();
	}
}
