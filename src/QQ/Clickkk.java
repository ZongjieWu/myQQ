package QQ;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

import javax.print.attribute.standard.OutputDeviceAssigned;

import org.xml.sax.InputSource;



public class Clickkk
{
	public static void main(String[] args)
	{
		
		
		 
		try {
			Socket s=new Socket("127.0.0.1",6666);
			OutputStream os=s.getOutputStream();
			PrintStream ps=new PrintStream(os);
			
			InputStream is=s.getInputStream();
			InputStreamReader isr=new InputStreamReader(is);
			BufferedReader br=new BufferedReader(isr);
			
			ps.println("客户端发送");
			System.out.println("客户端接收"+br.readLine());
			
			s.close();
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
