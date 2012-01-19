package chat.basic;

import java.io.Serializable;

public class ServerToUserMessageObject implements Serializable
{
	private String message;
	
	public ServerToUserMessageObject(String iMessage)
	{
		this.message = iMessage;
	}
	
	public String getMessage()
	{
		return this.message;
	}
}
