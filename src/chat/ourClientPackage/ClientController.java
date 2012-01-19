package chat.ourClientPackage;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.*;

import javax.swing.JFrame;
import javax.swing.JTextField;

import chat.basic.UserObject;
import chat.basic.UserToServerMessageObject;


public class ClientController extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Socket server;
	private static ObjectOutputStream out;
	private static JTextField textfield;
	private static JTextField usernameTextfield;
	private static ChatGUI gui;
	
	public static void main(String[] args)
	{		
		// Create GUI Frame , Draw Menu		
		gui = new ChatGUI();
		gui.createMenuGUI();
		
		// Get usernameTextfield
		usernameTextfield = gui.getUsernameTextField();
	}
	
	public static void verifyDetails()
	{
		// Gets the variables for validation
		// Construct the object we need
		String username = usernameTextfield.getText();
		
		// Do validation
		if(!username.isEmpty())
		{
			// Create userObject from validated data
			UserObject userObject = new UserObject(username);
			
			// Send the object to connect function
			connectToServer(userObject);
		}
	}
	
	// Attempt to connect
	public static void connectToServer(UserObject username)
	{
		gui.getContentPane().removeAll();
		gui.createChatGUI();
		
		// Attempt basic connection, TODO/ maybe handle it as one large exception with the general Exception e class
		// Opening output stream to server
		// Sends the username to the server
		try
		{
			server = new Socket("localhost", 63400);
			out = new ObjectOutputStream(server.getOutputStream());
			out.writeObject(username);
		}
		catch(UnknownHostException e)
		{	            
			System.err.println(e);
            System.exit(1);				
		}
		catch(IOException e)
		{
			gui.getTextArea().append("Connection failed or refused \n");
			System.err.println(e);
		}
		
		// Get Textfield
		textfield = gui.getTextField();

	
		// Thread looping for incoming messages from the server
		ServerConn serverConnection;
		try
		{
			serverConnection = new ServerConn(server, gui.getTextArea());
			Thread t = new Thread(serverConnection);
			t.start();
		} 
		catch (IOException e1)
		{
			gui.getTextArea().append("Client thread could not start/run \n");
			e1.printStackTrace();
		}

	}
	
	// Sends to server the users input and resets textfield
	public static void sendMessageToServer()
	{
		// Construct message object
		System.out.println("Send message object to server");
		UserToServerMessageObject msg = new UserToServerMessageObject(textfield.getText());
		
		try
		{
			out.writeObject(msg);
			textfield.setText(null);
		} 
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
