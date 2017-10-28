package QQ;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import sun.audio.AudioData;
import sun.audio.AudioDataStream;
import sun.audio.AudioPlayer;

import com.sun.corba.se.spi.orbutil.fsm.Input;
public class Sound 
{
	public Sound(int cmd)
	{
		try {

			String filename="";
			switch (cmd)
			{
				case Cmd.CMD_ONLINE:
					filename="sound/system.wav";
					break;
				case Cmd.CMD_AGREE:
					filename="sound/Global.wav";
					break;
				case Cmd.CMD_SEND:
					filename="sound/Audio.wav";
					break;
				case Cmd.CMD_OFFLINE:
					filename="sound/tweet.wav";
					break;
				case Cmd.CMD_SHAKE:
					filename="sound/shake.wav";
					break;
				case Cmd.CMD_REFUSE:
					filename="sound/msg.wav";
					break;
				default:
					break;
			}
		try {
			if(filename.equals(null)||filename.equals(""))
			{
				return;
			}
			File file=new File(filename);
			InputStream is = new FileInputStream(file);
			int size=is.available();
			byte bt[]=new byte[size];
			is.read(bt,0,size);
			AudioData aData=new AudioData(bt);
			AudioDataStream ads=new AudioDataStream(aData);
			AudioPlayer.player.start(ads);
		} catch (FileNotFoundException e) {
			// TODO: handle exception
		}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
//	public static void main(String[] args)
//	{
//		new Sound(Cmd.CMD_SEND);
//	}
}
