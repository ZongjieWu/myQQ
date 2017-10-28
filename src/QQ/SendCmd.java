package QQ;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import java.net.InetAddress;
import java.net.SocketException;
import java.util.Vector;



public class SendCmd
{
	public static void send(SendMsg msg)
	{
		try {
			DatagramSocket socket=new DatagramSocket();
			ByteArrayOutputStream bos=new ByteArrayOutputStream();
			ObjectOutputStream oos=new ObjectOutputStream(bos);
			oos.writeObject(msg);
			oos.flush();
			byte[] b=bos.toByteArray();
			InetAddress addr=InetAddress.getByName(msg.friendinfo.getAddr());
			int port =msg.friendinfo.getPort();
			DatagramPacket packet=new DatagramPacket(b,0,b.length,addr,port);
			socket.send(packet);
			socket.close();
			oos.close();
			
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void sendAll(Vector<AccountVo> allInfo,AccountVo myInfo,int cmd)
	{
		System.out.println("allInfo.size="+allInfo.size());
		for(AccountVo acc:allInfo)
		{
			if(!acc.getOnlinestatu().equals(Cmd.STATUS[1])&&acc.getQqcode()!=myInfo.getQqcode())
			{
				System.out.println("群发登录,qqcode="+acc.getQqcode());
				SendMsg msg=new SendMsg();
				msg.cmd=cmd;
				msg.myinfo=myInfo;
				msg.friendinfo=acc;
				send(msg);
				
			}
		}
	}
	
}
