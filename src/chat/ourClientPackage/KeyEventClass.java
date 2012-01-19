package chat.ourClientPackage;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyEventClass implements KeyListener
{
	private String functionChoice;
	
	// Constructor takes parameter for keyPress below to determine which function to run
	public KeyEventClass(String choice)
	{
		this.functionChoice = choice;
	}
	
	/* Currently only supports different ** ENTER/RETURN ** presses, build out to support more keys with private members and constructor method */
	
	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyChar() == KeyEvent.VK_ENTER)
		{
			if(this.functionChoice == "sendMsgToServer")
			{
				System.out.println("Pressed enter, run sendMessageToServerMethod");
				ClientController.sendMessageToServer();
			}
			
			if(this.functionChoice == "verifyDetails")
			{
				System.out.println("Pressed enter, run verifyDetailsMethod");
				ClientController.verifyDetails();
			}
		}	
	}
	public void keyTyped(KeyEvent e){}
	public void keyReleased(KeyEvent e){}
	
}
