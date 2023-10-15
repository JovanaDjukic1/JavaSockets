package jun2023_drugi;

import java.io.IOException;
import java.net.*;

public class Server {
	private static final int port = 9999;
	
	public static void main(String[] args) {
		try(ServerSocket ss = new ServerSocket(port)){
			while(true) {
				Socket socket = ss.accept();
				new ServerThread(socket);
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}

}
