package QQ;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream.GetField;
import java.util.Date;
import java.text.SimpleDateFormat;

import javax.sound.midi.VoiceStatus;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.Scrollable;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.text.Element;

public class ChatUI extends JFrame implements ItemListener,ActionListener,KeyListener
{
	JLabel lbltitle,lblboy,lblgirl;
	JTextPane tpsend,tpreceive;
	JButton btnsend,btnclose;
	JButton btnshake,btnFile,btnColor,btnFace;
	JComboBox cbfont,cbsize;
	AccountVo myinfo,friendinfo;
	String sfont[]={"宋体","楷书","黑体","华文正楷"};
	String ssize[]={"8","12","16","20","24","28","32"};
	Font font;
	int x;
	int y;
	JLabel lblbj;
	JButton btnpeeling;
	public ChatUI(AccountVo myinfo,AccountVo friendinfo)
	{
		super("聊天窗口");
		
		
	
		
//		this.myinfo=myinfo;
//		this.friendinfo=friendinfo;
//		
//		String str=myinfo.getNickname()+"("+myinfo.getQqcode()+")"+"正在和"+friendinfo.getNickname()+"("+friendinfo.getQqcode()+")"+"聊天";	
//		//setTitle(str);
//		setIconImage(new ImageIcon(friendinfo.getHeadImage()).getImage());
//	
//		lbltitle=new JLabel(str,new ImageIcon(friendinfo.getHeadImage()),JLabel.LEFT);
//		lbltitle.setOpaque(false);
	
		
//		tpreceive=new JTextPane();
//		//tpreceive.setOpaque(false);
//		tpreceive.setEditable(false);
//	
//		
//		tpsend=new JTextPane();
//		
//		btnclose=new JButton("关闭");
//		btnsend=new JButton("发送");
//		btnshake=new JButton(new ImageIcon("HeadImg/zd.png"));
//		btnshake.setMargin(new Insets(0,0,0,0));
//	
//		btnFile=new JButton("文件");
//		btnColor=new JButton("颜色");
//		btnFace=new JButton(new ImageIcon("HeadImg/sendFace.png"));
//		btnFace.setMargin(new Insets(0,0,0,0));
//		
//		cbfont=new JComboBox(sfont);
//		cbsize=new JComboBox(ssize);
//		
//		lblboy=new JLabel(new ImageIcon("HeadImg/boy.gif"));
//		lblgirl=new JLabel(new ImageIcon("HeadImg/girl.gif"));
//		
//		
//		JPanel centerPanel=new JPanel(new GridLayout(2,1,1,1));
//		centerPanel.setOpaque(false);
//		JPanel btnPanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
//		btnPanel.setOpaque(false);
//		JPanel sendPanel =new JPanel(new BorderLayout());
//		sendPanel.setOpaque(false);
//		JPanel buttomPanel=new JPanel(new FlowLayout(FlowLayout.RIGHT));
//		buttomPanel.setOpaque(false);
//		JPanel rightPanel=new JPanel(new GridLayout(2,1,1,1));	
//		//rightPanel.setOpaque(false);
//		
//		btnPanel.add(btnshake);
//		btnPanel.add(btnFile);
//		btnPanel.add(btnColor);
//		btnPanel.add(btnFace);
//		btnPanel.add(cbfont);
//		btnPanel.add(cbsize);
//		
//		buttomPanel.add(btnsend);
//		buttomPanel.add(btnclose);
//		
//		rightPanel.add(lblboy);
//		rightPanel.add(lblgirl);
//		
//	
//		
//		
//		sendPanel.add(btnPanel,BorderLayout.NORTH);
//		sendPanel.add(tpsend,BorderLayout.CENTER);
//		sendPanel.add(buttomPanel,BorderLayout.SOUTH);
//		centerPanel.add(new JScrollPane(tpreceive));
//		centerPanel.add(new JScrollPane(sendPanel));
//		
//		
//		add(lbltitle,BorderLayout.NORTH);
//		add(centerPanel,BorderLayout.CENTER);
//		add(rightPanel,BorderLayout.EAST);
		
		
		
		lblbj=new JLabel(new ImageIcon("HeadImg/00001.jpg"));	
		lblbj.setLayout(null);
		add(lblbj);
		
		
		
//		String str="吴宗杰";
//		setIconImage(new ImageIcon("HeadImg/1_h.png").getImage());
//		lbltitle=new JLabel(str,new ImageIcon("HeadImg/1_h.png"),JLabel.LEFT);
		
		this.myinfo=myinfo;
		this.friendinfo=friendinfo;
		
		String str=myinfo.getNickname()+"("+myinfo.getQqcode()+")"+"正在和"+friendinfo.getNickname()+"("+friendinfo.getQqcode()+")"+"聊天";	
		//setTitle(str);
		setIconImage(new ImageIcon(friendinfo.getHeadImage()).getImage());
		lbltitle=new JLabel(str,new ImageIcon(friendinfo.getHeadImage()),JLabel.LEFT);
		lbltitle.setBounds(0,0,600,70);
		lbltitle.setOpaque(false);
	
		
		tpreceive=new JTextPane();
		tpreceive.setBounds(0,70,400,150);
		tpreceive.setOpaque(false);
		tpreceive.setEditable(false);
		
		JScrollPane jPane=new JScrollPane(tpreceive);
		jPane.setOpaque(false);
		jPane.setBounds(0,70,400,150);
		jPane.getViewport().setOpaque(false);
//		jPane.setBorder(border)
		
		btnshake=new JButton(new ImageIcon("HeadImg/zd.png"));
		btnshake.setBounds(0,230,20,20);
		btnshake.setMargin(new Insets(0,0,0,0));
		
	
		cbfont=new JComboBox(sfont);
		cbfont.setBounds(30,230,60,30);
		cbsize=new JComboBox(ssize);
		cbsize.setBounds(100,230,60,30);
		btnColor=new JButton("颜色");
		btnColor.setBounds(170,230,60,30);
		btnFace=new JButton(new ImageIcon("HeadImg/sendFace.png"));
		btnFace.setBounds(240,230,20,20);
		btnFace.setMargin(new Insets(0,0,0,0));
		
		btnFile=new JButton("文件");
		btnFile.setBounds(270,230,60,30);
		
		
		tpsend=new JTextPane();
		tpsend.setOpaque(false);
		tpsend.setBounds(0,270,400,150);
		
		JScrollPane jPane2=new JScrollPane(tpsend);
		jPane2.setOpaque(false);
		jPane2.getViewport().setOpaque(false);
		jPane2.setBounds(0,270,400,150);
		
		btnpeeling=new JButton("换肤");
		btnpeeling.setBounds(190,430,60,30);
		btnsend=new JButton("发送");
		btnsend.setBounds(260,430,60,30);
		btnclose=new JButton("关闭");
		btnclose.setBounds(330,430,60,30);
		
		
		

		lblboy=new JLabel(new ImageIcon("HeadImg/boy.gif"));
		lblboy.setBounds(0,0,0,0);
		lblgirl=new JLabel(new ImageIcon("HeadImg/girl.gif"));
		lblgirl.setBounds(0,0,0,0);
		
		
		
		lblbj.add(lbltitle);
		lblbj.add(jPane);
		//lblbj.add(tpreceive);
		lblbj.add(btnshake);
		lblbj.add(btnFile);
		lblbj.add(btnColor);
		lblbj.add(btnFace);
		lblbj.add(cbfont);
		lblbj.add(cbsize);
		lblbj.add(jPane2);
		//lblbj.add(tpsend);
		lblbj.add(lblboy);
		lblbj.add(lblgirl);
		lblbj.add(btnpeeling);
		lblbj.add(btnclose);
		lblbj.add(btnsend);
		
		
		btnsend.addActionListener(this);
		btnclose.addActionListener(this);
		cbfont.addItemListener(this);
		cbsize.addItemListener(this);
		btnshake.addActionListener(this);
		btnFile.addActionListener(this);
		btnColor.addActionListener(this);
		btnFace.addActionListener(this);
		btnpeeling.addActionListener(this);
		tpsend.addKeyListener(this);
		
		setResizable(false);
		setSize(600,500);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		
	}
	
	public static void main(String[] args)
	{
		//new ChatUI(null,null);
		new ChatUI(null,null);
	}

	public void updatefont()
	{
		String ft=sfont[cbfont.getSelectedIndex()];
		String sz=ssize[cbsize.getSelectedIndex()];
		font=new Font(ft,Font.PLAIN,Integer.parseInt(sz));
		tpsend.setFont(font);
	}
	public void itemStateChanged(ItemEvent e) 
	{
		
		if(e.getSource()==cbfont)
		{
			updatefont();
		}else if(e.getSource()==cbsize)
		{
			updatefont();
		}
	}

	public void appendView(String name, StyledDocument xx)throws BadLocationException {
		
		StyledDocument vdoc = tpreceive.getStyledDocument();//获取接收框的内容
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String time = sdf.format(date);//获取时分秒
		
		SimpleAttributeSet as = new SimpleAttributeSet();
		
		String s =name + "    " + time + "\n";

		vdoc.insertString(vdoc.getLength(), s, as);
		int end = 0;
		while (end < xx.getLength()) {
			Element e0 = xx.getCharacterElement(end);
			
			SimpleAttributeSet as1 = new SimpleAttributeSet();
			StyleConstants.setForeground(as1, StyleConstants.getForeground(e0.getAttributes()));
			StyleConstants.setFontSize(as1, StyleConstants.getFontSize(e0.getAttributes()));
			StyleConstants.setFontFamily(as1, StyleConstants.getFontFamily(e0.getAttributes()));
			
			s = e0.getDocument().getText(end, e0.getEndOffset() - end);
			
			if("icon".equals(e0.getName())){
				vdoc.insertString(vdoc.getLength(), s, e0.getAttributes());
			}
			else{
				vdoc.insertString(vdoc.getLength(), s, as1);

			}
			end = e0.getEndOffset(); 
		}
	
		vdoc.insertString(vdoc.getLength(), "\n", as);
		
	
		tpreceive.setCaretPosition(vdoc.getLength());
	}
	public void shake()
	{
		int x=this.getLocation().x;
		int y=this.getLocation().y;
		for(int i=0;i<20;i++)
		{
			if(i/5==0)
			{
				if(i/2==0)
				{
					this.setLocation(x+5,y+5);
				}else
				{
					this.setLocation(x-5,y-5);
				}
			}
			
			try {
				Thread.sleep(50);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==btnColor)
		{
			JColorChooser colorChooser=new JColorChooser();
			Color color=colorChooser.showDialog(this, "请选择字体颜色",Color.BLACK);
			tpsend.setForeground(color);
		}else if(e.getSource()==btnsend)
		{
			try {
				appendView(myinfo.getNickname(), tpsend.getStyledDocument());
				SendMsg msg=new SendMsg();
				msg.cmd=Cmd.CMD_SEND;
				msg.friendinfo=friendinfo;
				msg.myinfo=myinfo;
				msg.doc=tpsend.getStyledDocument();
				SendCmd.send(msg);
				tpsend.setText("");
			} catch (BadLocationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}else if(e.getSource()==btnFile)
		{
			FileDialog fdg=new FileDialog(this,"请发送64k以下的文件",FileDialog.LOAD);
			fdg.show();
			String filename=fdg.getDirectory()+fdg.getFile();
			if(filename==null || filename.equals("nullnull")){
				return;
			}
			try {
				FileInputStream fis=new FileInputStream(filename);
				int size=fis.available();
				byte b[]=new byte[size];
				fis.read(b);
				SendMsg msg=new SendMsg();
				msg.myinfo=myinfo;
				msg.friendinfo=friendinfo;
				msg.b=b;
				msg.cmd=Cmd.CMD_FILE;
				msg.filename=fdg.getFile();
				SendCmd.send(msg);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}else if(e.getSource()==btnFace)
		{
			//int x,y;
			x=this.getLocation().x+200;
			y=this.getLocation().y;
			new BqUI(this,x,y);
			
		}else if(e.getSource()==btnshake)
		{
			//shake();
			String str=myinfo.getNickname()+"抖了一下你";
			tpsend.setText(str);
			try {
				appendView(myinfo.getNickname(), tpsend.getStyledDocument());
			} catch (BadLocationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			SendMsg msg=new SendMsg();
			msg.cmd=Cmd.CMD_SHAKE;
			msg.myinfo=myinfo;
			msg.friendinfo=friendinfo;
			msg.doc=tpsend.getStyledDocument();
			SendCmd.send(msg);
			tpsend.setText("");
			
			
		}else if(e.getSource()==btnclose)
		{
			dispose();
		}else if(e.getSource()==btnpeeling)
		{
			JFileChooser fc=new JFileChooser();
			fc.setDialogType(JFileChooser.OPEN_DIALOG);
			fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
			fc.setDialogTitle("更换皮肤");
			if(fc.showOpenDialog(this)==fc.APPROVE_OPTION)
			{
				File file=fc.getSelectedFile();
				String filepath=file.getPath();
				lblbj.setIcon(new ImageIcon(filepath));
			}
		}
	}

	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()==10)
		{
			try {
				appendView(myinfo.getNickname(), tpsend.getStyledDocument());
				SendMsg msg=new SendMsg();
				msg.cmd=Cmd.CMD_SEND;
				msg.friendinfo=friendinfo;
				msg.myinfo=myinfo;
				msg.doc=tpsend.getStyledDocument();
				SendCmd.send(msg);
				tpsend.setText("");
			} catch (BadLocationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
