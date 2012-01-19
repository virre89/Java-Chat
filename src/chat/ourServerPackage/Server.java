package chat.ourServerPackage;
import chat.basic.UserObject;

import java.net.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Server 
{
	static int clientID = 0;
	static Socket client;
	static ObjectInputStream in;
	static String message;
	static UserObject userObject;
	final Map<Integer,ClientConn> clientConnections = new HashMap<Integer,ClientConn>();
	
	public void initialize() throws IOException
	{	
		ServerSocket server = null;
		// Attempts to start server on defined socket, prints message
		try
		{
			server = new ServerSocket(63400);
			System.out.println("Server started");
		}
		catch(IOException e)
		{
			System.err.println("Could not listen on port 63400");
			System.err.println(e);
			System.exit(1);
		}
		
		
		// Looping new client acceptions, creates an inreader and gets the username
		Socket client = null;
		while(true)
		{
			try
			{
				client = server.accept();
				in = new ObjectInputStream(client.getInputStream());
				UserObject iUserObject;
				if((iUserObject = (UserObject) in.readObject()) != null)
				{
					Server.userObject = iUserObject;
					Server.userObject.setClientId(Server.clientID);
				}
				else
				{
					System.exit(1);
					return;
				}
			}
			catch(IOException e)
			{
                System.err.println("Accept failed.");
                System.err.println(e);
                System.exit(1);				
			}
			catch(ClassNotFoundException e)
			{
				System.err.println(e);
				System.exit(1);
			}
			
			
			// Creates and starts a new thread, passes the client and generated clientId, plus updates the clientId
			ClientConn newCon = new ClientConn(client,clientID,this,Server.userObject,in);
			clientConnections.put(clientID,newCon);
			Thread t = new Thread(newCon);
			t.start();
			
			this.echoServerMessage(Server.userObject, "userConnect");
			clientID++;
		}
	}
	
	public void removeClient(int clientId)
	{
		clientConnections.remove(clientId);
	}
	
	public void echoMessage(int id, String username, String msg)
    {			
		for(Map.Entry<Integer,ClientConn> eachclient : clientConnections.entrySet())
        {
			ClientConn eC = eachclient.getValue();
			eC.sendMessage(id + " " + username + " says: " + msg);
        }
		
		System.out.println(id + " " + username +" says: " + msg);
    }
	
	public void echoServerMessage(UserObject iUserObject, String type)
    {
		String msg = null;


		
		if(type == "userConnect")
		{
			msg = "User connected with ID " + iUserObject.getClientId() + " as \"" + iUserObject.getUsername() + "\".";
		}
		if(type == "userDisconnect")
		{
			msg = "User with ID " + iUserObject.getClientId() + " ("+ iUserObject.getUsername() + ") disconnected.";
		}
		
		if(msg != null)
		{
			for(Map.Entry<Integer,ClientConn> eachclient : clientConnections.entrySet())
	        {	
				ClientConn eC = eachclient.getValue();
				eC.sendMessage(msg);
	        }
			
			
			
			System.out.println(msg);
		}
    }
}
