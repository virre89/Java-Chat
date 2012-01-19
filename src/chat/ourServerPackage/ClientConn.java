package chat.ourServerPackage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import chat.basic.ServerToUserMessageObject;
import chat.basic.UserObject;
import chat.basic.UserToServerMessageObject;

class ClientConn implements Runnable
{
	// Private members for ClientConn
	private Socket client;
	private int clientID;
	private Server server;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private UserObject userObject;
	
	// Constructor of ClientConn with inparameters
	public ClientConn(Socket client, int clientId, Server server, UserObject iUserObject, ObjectInputStream iIn)
	{
		this.client = client;
		this.clientID = clientId;
		this.server = server;
		this.userObject = iUserObject;
		this.in = iIn;
		try
		{
			this.out = new ObjectOutputStream(this.client.getOutputStream());
		}
		catch(IOException e)
		{
			System.err.println(e);
			return;
		}
	}
	
	// Runs everytime the thread runs, because it inherits runnable
	public void run()
	{	
		UserToServerMessageObject msg;
		try
		{
			while((msg = (UserToServerMessageObject) this.in.readObject()) != null)
			{				
				server.echoMessage(this.clientID, this.userObject.getUsername(), msg.getMessage());
			}
		}
		catch(IOException e)
		{
			// Store clientID as array keys , remove upon them
			// Print error message on disconnect
			server.removeClient(this.clientID);		
			server.echoServerMessage(this.userObject, "userDisconnect");
			
			return;
		}
		catch(ClassNotFoundException e)
		{
			System.err.println(e);
		}
	}
	
    public void sendMessage(String msg)
    {
		ServerToUserMessageObject obj = null;
		
    	try
		{
    		obj = new ServerToUserMessageObject(msg);
    		this.out.writeObject(obj);
		} 
    	catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
