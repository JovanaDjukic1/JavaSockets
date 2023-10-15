package jun2023_drugi;
import java.net.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.io.*;

public class ServerThread extends Thread{
	private BufferedReader in ;
	private PrintWriter out;
	private Socket socket;
	static private ArrayList<String>questions;
	
	public ServerThread(Socket socket) {
		this.socket=socket;
		try {
			this.in= new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.out=new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
		}catch(IOException e) {
			e.printStackTrace();
		}
		start();
	}
	
	private ArrayList citaj(String fileName) {
		questions = new ArrayList<String>();
		try {
		BufferedReader fin = new BufferedReader(new FileReader(fileName));
		String s;
		while((s=fin.readLine())!=null) {
			questions.add(s);
		}
		fin.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return questions;
	}
	
	public void run() {
		try {
			String request;
			String rez="";
			do {
			request = in.readLine();
			BufferedReader fin = new BufferedReader(new FileReader("src/movies.txt"));
			String b;
			
			while((b=fin.readLine())!=null) {
				String film = b.split("-")[0];
				String ocjena = b.split("-")[1];
				System.out.println(film);
				System.out.println(ocjena);
				System.out.println(request);
				if(film.equals(request)) {
					rez=film;
				}
				System.out.println(rez);
			}
			out.println("Request:"+request);
			}while(rez.equals(""));
			
			String request1="";
			String rez1="";
			
			do {
				request1=in.readLine();
				if(Integer.parseInt(request1) >= 1 && Integer.parseInt(request1) <= 10) {
					rez1=request1;
				}
				out.println(rez1);
				
			}while(rez1.equals(""));
			
		 
			
		    BufferedReader reader = new BufferedReader( new FileReader("src/movies.txt"));
		    StringBuffer inputBuffer = new StringBuffer();
		    String b;
			while((b=reader.readLine())!=null) {
				String film = b.split("-")[0];
				String ocjena = b.split("-")[1];
				if(film.equals(request)) {
					b=request+"-"+request1;
					inputBuffer.append(b);
					inputBuffer.append('\n');
				}
				else {
					inputBuffer.append(b);
					inputBuffer.append('\n');
				}
			}
		   System.out.println("Inputgbufer"+inputBuffer);
			reader.close();
			FileOutputStream fileOut = new FileOutputStream("src/movies.txt");
	        fileOut.write(inputBuffer.toString().getBytes());
	        fileOut.close();
		    out.println("You have successfully rated the movie "+request+" with a rating "+request1+"\n"+"The current rating is "+request1);
			
			
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
