package QQ;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.awt.Container;
import javax.activation.FileDataSource;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;



public class BqUI extends JFrame implements MouseListener
{
	JLabel [] bqicon;
	JLabel lbl_x;
	String iconlist[];
	ChatUI chatUI;
	public BqUI(ChatUI chatUI,int x,int y)
	{	
		this.chatUI=chatUI;
	
		lbl_x=new JLabel("X");
		lbl_x.setFont(new Font("正楷",Font.PLAIN,20));
		lbl_x.setForeground(Color.RED);
		lbl_x.setOpaque(false);
		
		Container con=getContentPane();
		con.setLayout(new FlowLayout(FlowLayout.LEFT));
		con.setBackground(Color.WHITE);
		
		File filed=new File("bq");
		System.out.println("我是BqUI类"+filed);
		iconlist=filed.list();
		bqicon=new JLabel[iconlist.length];
		for(int i=0;i<iconlist.length;i++)
		{
			
			bqicon[i]=new JLabel(new ImageIcon("bq/"+iconlist[i]));
			bqicon[i].setBorder(BorderFactory.createLineBorder(Color.WHITE,2));
			bqicon[i].addMouseListener(this);
			add(bqicon[i]);
			
		}
		lbl_x.addMouseListener(this);
		add(lbl_x);
		
		setAlwaysOnTop(true);
		setResizable(false);
		setUndecorated(true);
		setSize(300,320);
		//setLocationRelativeTo(null);
		setLocation(x,y);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public static void main(String[] args) 
	{
		//new BqUI(null);
	}

	public void mouseClicked(MouseEvent e) 
	{
		if(e.getClickCount()==2)
		{
			for(int i=0;i<iconlist.length;i++)
			{
				if(e.getSource()==bqicon[i])
				{
					chatUI.tpsend.insertIcon(bqicon[i].getIcon());
					dispose();
					break;
				}
			
			}
			if(e.getSource()==lbl_x)
			{
				dispose();
			}
		}
		
	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		for(int i=0;i<iconlist.length;i++)
		{
			if(e.getSource()==bqicon[i])
			{
				bqicon[i].setBorder(BorderFactory.createLineBorder(Color.BLUE,2));	
			}
			
		}
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		for(int i=0;i<iconlist.length;i++)
		{
			if(e.getSource()==bqicon[i])
			{
				bqicon[i].setBorder(BorderFactory.createLineBorder(Color.WHITE,2));	
			}
			
		}
		
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
