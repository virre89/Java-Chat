package chat.basic;

import java.io.Serializable;

public class UserObject implements Serializable
{
	private String username;
	private int clientId;
	
	public UserObject(String iUsername)
	{
		this.username = iUsername;
	}
	
	// Getters
	public String getUsername()
	{
		return this.username;
	}
	
	public Integer getClientId()
	{
		return this.clientId;
	}
	
	// Setters
	public void setClientId(int iId)
	{
		this.clientId = iId;
	}
}
