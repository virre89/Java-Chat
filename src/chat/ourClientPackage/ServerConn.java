package chat.ourClientPackage;

import java.net.*;
import java.awt.TextArea;
import java.io.*;

import chat.basic.ServerToUserMessageObject;

public class ServerConn implements Runnable
{
	private ObjectInputStream in = null;
	private TextArea textArea = null;
	
	public ServerConn(Socket iServer, TextArea iTextarea) throws IOException
	{
		this.in = new ObjectInputStream(iServer.getInputStream());
		this.textArea = iTextarea; 
	}
	
	public void run()
	{
		ServerToUserMessageObject msg;
		try
		{
			while((msg = (ServerToUserMessageObject) in.readObject()) != null)
			{
				textArea.append(msg.getMessage() + "\n");
				System.out.println(msg);
			}
		}
		catch(IOException e)
		{
			System.err.println(e);
			textArea.append("Lost Connection to server\n");
			return;
		}
		catch(ClassNotFoundException e)
		{
			
		}
	}
}
