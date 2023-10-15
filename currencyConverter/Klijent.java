package jul2023;
import java.io.IOException;
import java.util.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.io.*;

public class Klijent {
	private static final int port = 9999;
	private static final String hostname = "localhost";
	private static final int buff_size = 1024;
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		while(true) {
			System.out.println("First currency");
			String a = scan.nextLine();
			if(a.equals("zavrsi"))
			{
				break;
			}
			System.out.println("Amount");
			String b = scan.nextLine();
			if(b.equals("zavrsi"))
			{
				break;
			}
			System.out.println("Second currency");
			String c = scan.nextLine();
			if(c.equals("zavrsi"))
			{
				break;
			}
			String d = a +" "+ b + ":" + c;
			
		
		try(DatagramSocket socket = new DatagramSocket()){
			byte[] buff = d.getBytes(StandardCharsets.UTF_8);
			InetAddress addr = InetAddress.getByName(hostname);
			DatagramPacket request = new DatagramPacket(buff,buff.length,addr,port);
			socket.send(request);
			DatagramPacket response = new DatagramPacket(new byte[buff_size],buff_size);
			socket.receive(response);
			String rez = new String(response.getData(),0,response.getLength(),StandardCharsets.UTF_8);
			System.out.println("Amount:  "+rez);
		} catch(IOException e ) {
			e.printStackTrace();
		}
	}
	}
}
