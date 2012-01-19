package chat.basic;

import java.io.Serializable;

public class UserToServerMessageObject implements Serializable
{
	private String message;
	
	public UserToServerMessageObject(String iMessage)
	{
		this.message = iMessage;
	}
	
	public String getMessage()
	{
		return this.message;
	}
}
