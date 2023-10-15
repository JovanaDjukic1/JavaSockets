package jul2023;
import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.io.*;

public class Server {
	private static final int port = 9999;
	private static final int buff_size=1024;
	
	public static String citaj(String fileName,String izlaznaValuta) {
		String putanja = "src/"+izlaznaValuta+".txt";
		System.out.println(putanja);
		String rez="";
		try {
		BufferedReader in = new BufferedReader(new FileReader(putanja));
		System.out.println("src//"+izlaznaValuta+".txt");
		String b;
		while((b=in.readLine())!=null) {
		
			String one = b.split(" ")[0];
			String two = b.split(" ")[1];
			if (two.equals(fileName)) {
			   rez=one;
			   break;
			}
		}
		in.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return rez;
	}
	
	public static void main(String[] args) {
		while(true) {
		try(DatagramSocket socket = new DatagramSocket(port)){
			DatagramPacket request = new DatagramPacket(new byte[buff_size],buff_size);
			socket.receive(request);
			String pom = new String(request.getData(),0,request.getLength(),StandardCharsets.UTF_8);
			System.out.println("Pom"+pom);
			String prvaValuta = pom.split(" ")[0];
			String IznosDrugaValuta = pom.split(" ")[1];
			String iznos = IznosDrugaValuta.split(":")[0];
			String drugaValuta = IznosDrugaValuta.split(":")[1];
			String rez = citaj(prvaValuta,drugaValuta);
			
			if (rez.equals("")) {
				String out = "There is no currency";
				DatagramPacket response=new DatagramPacket(out.getBytes(),out.length(),request.getAddress(),request.getPort());
				socket.send(response);
			}
			else {
				float a = Float.parseFloat(rez);
				float konacno = Float.parseFloat(iznos)*a;
				String konacanstring = Float.toString(konacno);
				DatagramPacket response=new DatagramPacket(konacanstring.getBytes(),konacanstring.length(),request.getAddress(),request.getPort());
				socket.send(response);
				
			}
			
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	}
}
