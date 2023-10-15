package jun2023_drugi;
import java.io.IOException;
import java.util.*;
import java.net.*;
import java.io.*;

public class Klijent {
	
	private static final int port = 9999;
	private static final String hostname = "localhost";
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Wellcome");
		String o="";
		String naziv;
		String ocjena;
		do {
			
		   if(o.equals("end")) {
			   break;
		   }
		   
			
			try(Socket socket = new Socket(hostname,port)){
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
				String request="";
				do {
				   System.out.println("Enter the name of the movie that you want to rate:");
				   BufferedReader fin = new BufferedReader(new FileReader("src/movies.txt"));
				   String b;
				while((b=fin.readLine())!=null){
					System.out.println(b);
				}
				   naziv = scan.nextLine();
				   if(naziv.equals("end"))
					   System.exit(0);
				   out.println(naziv);
			       request = in.readLine();
				   System.out.println(request);
				}
				while(request.equals(""));
				
				
				
				String request1="";
				do {
					System.out.println("Enter the grade:");
					ocjena = scan.nextLine();
					if(ocjena.equals("end"))
						   System.exit(0);
					out.println(ocjena);
				    request1=in.readLine();
				}
				while(request1.equals(""));
				 String request2=in.readLine();
				 System.out.println(request2);
				 String response = in.readLine();
				 System.out.println(response);
				 System.out.println("Enter end if you want to end. Enter continue if you want to continue");
				 o=scan.nextLine();
			} catch(IOException e) {
				e.printStackTrace();
			}
		
		}while(o.equals("continue"));
	}

}
