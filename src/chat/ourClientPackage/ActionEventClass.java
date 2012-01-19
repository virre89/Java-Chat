package chat.ourClientPackage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


	public class ActionEventClass implements ActionListener
	{
		
		// Action
		public void actionPerformed(ActionEvent event)
		{
			// Checks the name actionCommand string passed 
			
			// NOTE: Current string dependencies , between this and GUI's ActionCommands
			// TODO Fix a constant class
			if(event.getActionCommand() == "ConnectButtonCmd")
			{
				System.out.println("Clicked join button, launch verifyDetails method");
				ClientController.verifyDetails();
			}
			
			if(event.getActionCommand() == "SendButtonCmd")
			{
				System.out.println("Clicked send button, launch sendMessageToServer method");
				ClientController.sendMessageToServer();
			}
			
		}
	}