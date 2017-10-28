package QQ;

import javax.swing.ImageIcon;


import javax.swing.JFrame;
import javax.swing.JLabel;

import com.sun.awt.AWTUtilities;

import java.awt.Color;
import java.awt.Toolkit;

public class TipUI extends JFrame
{
	JLabel lbltip;
	public TipUI(AccountVo myinfo)
	{
		String str=	myinfo.getNickname();
		if(myinfo.getOnlinestatu().equals(Cmd.STATUS[0]))
		{
			str+="上线了";
		}else if(myinfo.getOnlinestatu().equals(Cmd.STATUS[1])){
			str+="下线了";
		}
		
		String headimg=statuechange(myinfo);
		getContentPane().setBackground(Color.YELLOW);
		lbltip=new JLabel(str,new ImageIcon(headimg),JLabel.RIGHT);
		add(lbltip);
		
		setAlwaysOnTop(true);
		setSize(200,100);
		Toolkit toolkit=Toolkit.getDefaultToolkit();
		int width=toolkit.getScreenSize().width-200;
		int height=toolkit.getScreenSize().height;
		
		setVisible(true);
		for(int i=0;i<100;i++)
		{
			setLocation(width,height-i);
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for(int i=100;i>0;i--)
		{
			//AWTUtilities.setWindowOpacity(this,i*0.01f);
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		dispose();
	}
	public static void main(String[] args)
	{
		new TipUI(null);
	}
	
	public String statuechange(AccountVo myinfo)
	{
		String status=myinfo.getOnlinestatu();
		String filename=myinfo.getHeadImage();
		String headimg=myinfo.getHeadImage();
		if(status.equals(Cmd.STATUS[0]))
		{
			filename=headimg;
		}else if(status.equals(Cmd.STATUS[1]))
		{
			int pos=headimg.indexOf('.');
			String pre=headimg.substring(0,pos);
			String fix=headimg.substring(pos,headimg.length());
			filename=pre+"_h"+fix;
		}
		return filename;
	}
	
	
}
