package chat.basic;

import chat.ourServerPackage.*;
import java.io.IOException;

public class ServerController
{
	public static void main(String[] args) throws IOException
	{

		Server server = new Server();
		server.initialize();
	}
}
