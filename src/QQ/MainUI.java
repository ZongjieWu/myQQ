package QQ;

import java.awt.BorderLayout;


import java.util.Hashtable;
import java.awt.AWTException;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.print.DocFlavor.URL;
import javax.security.auth.Refreshable;
import javax.swing.AbstractListModel;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTabbedPane;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.plaf.metal.MetalBorders.Flush3DBorder;

import sun.applet.resources.MsgAppletViewer;






public class MainUI extends JFrame implements ActionListener,MouseListener,ItemListener,WindowListener
{
	JLabel lblbj,lblmyinfo;
	JTabbedPane tabpanel;
	JList lstfriend,lstmate,lstfamliy,lstblacklist;
	JButton btnfind,btnpeeling;
	JComboBox cbState;
	AccountVo myinfo,friendinfo;
	Vector<AccountVo> vallinfo,vmate,vfamily,vfriend,vblacklist;
	DateBaseDAO dateBaseDAO=new DateBaseDAO();
	JPopupMenu pmmenu;
	JMenuItem  miChat,mifriend,mifamily,mimate,miblacklist,milookinfo,midelete;
	PopupMenu popupMenu;
	MenuItem miOpen,miExit,miOnline,miLevel,miBuys;
	TrayIcon trayIcon;
	JButton btntaopao;
	Hashtable<Integer,ChatUI> chatWin=new Hashtable<Integer,ChatUI>();
	public MainUI()
	{
		
	}
	
	public MainUI(AccountVo myinfo)
	{
		this.myinfo=myinfo;
		
		setIconImage(new ImageIcon(myinfo.getHeadImage()).getImage());
		
		lblbj=new JLabel(new ImageIcon("MainUIimage/2221.jpg"));
		lblbj.setLayout(new BorderLayout());
		lblbj.setOpaque(false);
		add(lblbj);
			
		vallinfo=new Vector<AccountVo>();
		vmate=new Vector<AccountVo>();
		vfamily=new Vector<AccountVo>();
		vfriend=new Vector<AccountVo>();
		vblacklist=new Vector<AccountVo>();	
		
		lstfriend=new JList();
		lstfamliy=new JList();
		lstmate=new JList();
		lstblacklist=new JList();
		
		reflush();
		
		lstfriend.setBackground(new Color(0,0,0,0));
		lstfriend.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lstfriend.setOpaque(false);
		lstfamliy.setBackground(new Color(0,0,0,0));
		lstfamliy.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lstfamliy.setOpaque(false);
		lstmate.setBackground(new Color(0,0,0,0));
		lstmate.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lstmate.setOpaque(false);
		lstblacklist.setBackground(new Color(0,0,0,0));
		lstblacklist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lstblacklist.setOpaque(false);
		
		lstfriend.updateUI();
		lstfamliy.updateUI();
		lstmate.updateUI();
		lstblacklist.updateUI();
		
		
		lstfriend.addMouseListener(this);
		lstfamliy.addMouseListener(this);
		lstmate.addMouseListener(this);
		lstblacklist.addMouseListener(this);
		
		
		UIManager.put("TabbedPane.contentOpaque", false);
		tabpanel=new JTabbedPane();
		tabpanel.setOpaque(false);
		
		tabpanel.addTab("好友", lstfriend);
		tabpanel.addTab("同学", lstmate);
		tabpanel.addTab("家人",lstfamliy);
		tabpanel.addTab("黑名单", lstblacklist);
		lblbj.add(tabpanel);
		
		JPanel buttomPanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
		btnfind=new JButton("查找");
		btnfind.setOpaque(false);
		btnpeeling=new JButton("换肤");
		
		cbState=new JComboBox(Cmd.STATUS);
		cbState.removeItemAt(1);
		
		btntaopao=new JButton("淘宝");
		buttomPanel.add(btntaopao);
		
		buttomPanel.add(btnfind);
		buttomPanel.add(btnpeeling);
		buttomPanel.add(cbState);
		
		lblbj.add(buttomPanel,BorderLayout.SOUTH);
		
		
		ImageIcon icon=new ImageIcon(myinfo.getHeadImage());
		String nicknameString=myinfo.getNickname()+"("+myinfo.getQqcode()+")【"+myinfo.getRemark()+"】";
		lblmyinfo=new JLabel(nicknameString,icon,JLabel.LEFT);
		lblmyinfo.setOpaque(false);
		lblbj.add(lblmyinfo,BorderLayout.NORTH);
		
		
		
		
		
		 btnfind.addActionListener(this);
		 btnpeeling.addActionListener(this);
		 lblmyinfo.addMouseListener(this);
		 cbState.addItemListener(this);
		 btntaopao.addActionListener(this);
		 addWindowListener(this);
		
		 
		 createMenu();
		 new ReceiveThread().start();
		 SendCmd.sendAll(vallinfo, myinfo, Cmd.CMD_ONLINE);
		// setUndecorated(true);
		setResizable(false);
		trayMenu();
		setTrayIcon();
		setSize(300,730);
		setVisible(true);
		// setLocationRelativeTo(null);
		setLocation(700,0);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	public void trayMenu()
	{
		popupMenu=new PopupMenu();
		miOpen=new MenuItem("打开主界面");
		miExit=new MenuItem("退出");
		miOnline=new MenuItem("在线");
		miLevel=new MenuItem("离开");
		miBuys=new MenuItem("忙碌");
		popupMenu.add(miOpen);
		popupMenu.add(miExit);
		popupMenu.add(miOnline);
		popupMenu.add(miLevel);
		popupMenu.add(miBuys);
		
		miOpen.addActionListener(this);
		miExit.addActionListener(this);
		miOnline.addActionListener(this);
		miLevel.addActionListener(this);
		miBuys.addActionListener(this);
		
	}
	public void setTrayIcon()
	{
		Image image=new ImageIcon("HeadImg/tubiao.png").getImage();
		trayIcon=new TrayIcon(image,"吴宗杰QQ",popupMenu);
		trayIcon.setImageAutoSize(true);
 	}
	public void createMenu()
	{
		pmmenu=new JPopupMenu();
		miChat=new JMenuItem("聊天");
		mifriend=new JMenuItem("移至好友");
		mifamily=new JMenuItem("移至家人");
		mimate=new JMenuItem("移至同学");
		milookinfo=new JMenuItem("查看资料");
		miblacklist=new JMenuItem("移至黑名单");
		midelete=new JMenuItem("删除好友");
		
		
		 
	
		pmmenu.add(miChat);
		pmmenu.add(mifriend);
		pmmenu.add(mifamily);
		pmmenu.add(mimate);
		pmmenu.add(milookinfo);
		pmmenu.add(miblacklist);
		pmmenu.add(midelete);
		
		
		
		
		
		miChat.addActionListener(this);
		mifriend.addActionListener(this);
		mifamily.addActionListener(this);
		mimate.addActionListener(this);
		milookinfo.addActionListener(this);
		miblacklist.addActionListener(this);
		midelete.addActionListener(this);
		
		
		
	}
	public static void main(String[] args)
	{
		new MainUI();
	}

	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource()==btnfind)
		{
			new FindUI(myinfo);
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
		}else if(e.getSource()==milookinfo)
		{
			//friendinfo=vfriend.get(lstfriend.getSelectedIndex());
			new LookinfoUI(friendinfo);
		}else if(e.getSource()==miChat)
		{
			new ChatUI(myinfo,friendinfo);
		}else if(e.getSource()==mimate)
		{
			
			dateBaseDAO.movegroup(myinfo.getQqcode(), friendinfo.getQqcode(),Cmd.GROUPNAME[0]);
			reflush();
		}else if(e.getSource()==mifriend)
		{
			dateBaseDAO.movegroup(myinfo.getQqcode(), friendinfo.getQqcode(),Cmd.GROUPNAME[1]);
			reflush();
		}else if(e.getSource()==mifamily)
		{
			dateBaseDAO.movegroup(myinfo.getQqcode(), friendinfo.getQqcode(),Cmd.GROUPNAME[2]);
			reflush();
		}else if(e.getSource()==miblacklist)
		{
			dateBaseDAO.movegroup(myinfo.getQqcode(), friendinfo.getQqcode(),Cmd.GROUPNAME[3]);
			reflush();
		}else if(e.getSource()==miOpen)
		{
			SystemTray systemTray=SystemTray.getSystemTray();
			systemTray.remove(trayIcon);
			MainUI.this.setVisible(true);
			MainUI.this.setState(JFrame.NORMAL);
		}else if (e.getSource()==miOnline)
		{
			statuechange();
			String statue=Cmd.STATUS[0];
			dateBaseDAO.changestatue(myinfo.getQqcode(), statue);
			SendCmd.sendAll(vallinfo, myinfo, Cmd.CMD_CHANGESTATUE);
		}else if (e.getSource()==miBuys) {
			statuechange();
			String statue=Cmd.STATUS[2];
			dateBaseDAO.changestatue(myinfo.getQqcode(), statue);
			SendCmd.sendAll(vallinfo, myinfo, Cmd.CMD_CHANGESTATUE);
		}else if (e.getSource()==miLevel) {
			
			statuechange();
			String statue=Cmd.STATUS[3];
			dateBaseDAO.changestatue(myinfo.getQqcode(), statue);
			SendCmd.sendAll(vallinfo, myinfo, Cmd.CMD_CHANGESTATUE);
		}else if (e.getSource()==miExit) {
			dateBaseDAO.changestatue(myinfo.getQqcode(), Cmd.STATUS[1]);
			myinfo.setOnlinestatu(Cmd.STATUS[1]);
			SendCmd.sendAll(vallinfo, myinfo, Cmd.CMD_OFFLINE);
			System.exit(0);
		}else if (e.getSource()==midelete) {
			if(JOptionPane.showConfirmDialog(null,"您确定要删除好友？","删除好友",JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION)
			{
				dateBaseDAO.deletefriend(myinfo.getQqcode(), friendinfo.getQqcode());
				reflush();
		
			}
			
			SendMsg msg=new SendMsg();
			msg.cmd=Cmd.CMD_DELETEFRIEND;
			msg.myinfo=myinfo;
			msg.friendinfo=friendinfo;
			SendCmd.send(msg);
		}else if (e.getSource()==btntaopao) 
		{
			try {  
				String URL="https://www.taobao.com/";
			//	String URL="file:///" + new File(URL).getAbsolutePath().replace("\\","/");
				URI uri = new URI(URL);  
				Desktop desktop = null;  
				if (Desktop.isDesktopSupported()) {  
					desktop = Desktop.getDesktop();  
				}  
				if (desktop != null)  
					desktop.browse(uri);  
			} catch (IOException ioe) {  
				ioe.printStackTrace();  
			} catch (URISyntaxException e1) {  
				e1.printStackTrace();  
			}  
			
		}
		
	}
	
	public void reflush()
	{
		vallinfo=dateBaseDAO.getMyfriend(myinfo.getQqcode());
	
		// System.out.println(vallinfo);
		vmate.clear();
		vfamily.clear();
		vfriend.clear();
		vblacklist.clear();	
		for(AccountVo acc:vallinfo)
		{
			String groupname=acc.getGroupname();
			
			if(groupname.equals(Cmd.GROUPNAME[0]))
			{
				vmate.add(acc);
				System.out.println(vmate);
			}else if(groupname.equals(Cmd.GROUPNAME[1]))
			{
			
				vfriend.add(acc);
				
			}else if(groupname.equals(Cmd.GROUPNAME[2]))
			{
				vfamily.add(acc);
				System.out.println(vfriend);

			}else if(groupname.equals(Cmd.GROUPNAME[3]))
			{
				vblacklist.add(acc);
				System.out.println(vfriend);

			}
		}
		
		lstfriend.setModel(new DataModal(vfriend));
		lstfamliy.setModel(new DataModal(vfamily));
		lstmate.setModel(new DataModal(vmate));
		lstblacklist.setModel(new DataModal(vblacklist));
		
		lstfriend.setCellRenderer(new MyHeadImg(vfriend));
		lstfamliy.setCellRenderer(new MyHeadImg(vfamily));
		lstmate.setCellRenderer(new MyHeadImg(vmate));
		lstblacklist.setCellRenderer(new MyHeadImg(vblacklist));
		
	}
	class DataModal extends AbstractListModel
	{

		Vector<AccountVo> data;
		public DataModal(){}
		public DataModal(Vector<AccountVo> data)
		{
			this.data=data;
		}
		
		public Object getElementAt(int index)
		{
			// TODO Auto-generated method stub
			AccountVo info=data.get(index);
			return info.getNickname()+"("+info.getQqcode()+")【"+info.getRemark()+"】";
		}

		public int getSize() 
		{
			// TODO Auto-generated method stub
			return data.size();
		}
		
	}
	class MyHeadImg extends DefaultListCellRenderer
	{
		Vector< AccountVo > data;
		public MyHeadImg(Vector< AccountVo > data)
		{
			this.data=data;
		}
		public Component getListCellRendererComponent(JList list,Object values,int index,boolean isSelected,boolean cellHasFocus)
		{
			Component c=super.getListCellRendererComponent(list,values,index,isSelected,cellHasFocus);
			if(index>=0&&index<data.size())				  
			{
				AccountVo acc=data.get(index); 
				String status=acc.getOnlinestatu();
				String headimg=acc.getHeadImage();
				String filename="";
				if(status.equals(Cmd.STATUS[0]))
				{
					filename=headimg;
				}else if(status.equals(Cmd.STATUS[1]))
				{
					int pos=headimg.indexOf('.');
					String pre=headimg.substring(0,pos);
					String fix=headimg.substring(pos,headimg.length());
					filename=pre+"_h"+fix;
				}else if(status.equals(Cmd.STATUS[2]))
				{
					int pos=headimg.indexOf('.');
					String pre=headimg.substring(0,pos);
					String fix=headimg.substring(pos,headimg.length());
					filename=pre+"_l"+fix;
				}else if(status.equals(Cmd.STATUS[3]))
				{
					int pos=headimg.indexOf('.');
					String pre=headimg.substring(0,pos);
					String fix=headimg.substring(pos,headimg.length());
					filename=pre+"_w"+fix;
				}

     			System.out.println("filename--"+filename);

				setIcon(new ImageIcon(filename));
				setText(acc.getNickname()+"("+acc.getQqcode()+")【"+acc.getRemark()+"】");
				
			}
			if(isSelected)
			{
				setBackground(list.getSelectionBackground());
				setForeground(list.getSelectionForeground());
			}else {
				setBackground(list.getBackground());
				setForeground(list.getForeground());
			}
			setEnabled(list.isEnabled());
			setFont(list.getFont());
			setOpaque(false);
			return this;
			
			
		}
		
	}
	public ChatUI openChat()
	{
		ChatUI chatUI=chatWin.get(friendinfo.getQqcode());
		if(chatUI==null)
		{
			chatUI=new ChatUI(myinfo,friendinfo);
			chatWin.put(friendinfo.getQqcode(), chatUI);
		}
		chatUI.setVisible(true);
		return chatUI;
	}
	public void mouseClicked(MouseEvent e) 
	{
		
		
		if(e.getSource()==lblmyinfo)
		{
			if(e.getClickCount()==2)
			{
				new UpdateMyinfoUI(myinfo,this);	
			}
			
		}else if(e.getSource()==lstfriend)
		{
			
			if(lstfriend.getSelectedIndex()>=0)
			{
				friendinfo = vfriend.get(lstfriend.getSelectedIndex());
				
			}
			
				
		    if(e.getClickCount()==2){//双击
		    	if(lstfriend.getSelectedIndex()>=0)
		    	{
					openChat();
				};
					
			}
			
			if(e.getButton()==3)
			{
				if(lstfriend.getSelectedIndex()>=0)
				{
					pmmenu.show(lstfriend,e.getX(),e.getY());
				}
				
			}
			
		}else if(e.getSource()==lstfamliy)
		{
			if(lstfamliy.getSelectedIndex()>=0)
			{
				friendinfo = vfamily.get(lstfamliy.getSelectedIndex());
				System.out.println("家人列表");
				
			}
			if(lstfamliy.getSelectedIndex()>=0)
			{
				
				 if(e.getClickCount()==2){//双击
				    	if(lstfamliy.getSelectedIndex()>=0)
				    	{
							openChat();
						};
							
					}
			}
			if(e.getButton()==3){
				if(lstfamliy.getSelectedIndex()>=0)
				{
					pmmenu.show(lstfamliy,e.getX(),e.getY());
				}
			}
		}else if(e.getSource()==lstmate)
		{
			if(lstmate.getSelectedIndex()>=0)
			{
				friendinfo = vmate.get(lstmate.getSelectedIndex());
				
			}
			if(lstmate.getSelectedIndex()>=0)
			{
				
				 if(e.getClickCount()==2){//双击
				    	if(lstmate.getSelectedIndex()>=0)
				    	{
							openChat();
						};
							
					}
			}
			if(e.getButton()==3)
			{
				if(lstmate.getSelectedIndex()>=0)
				{
					pmmenu.show(lstmate,e.getX(),e.getY());
				}
			}
		}else if(e.getSource()==lstblacklist)
		{
			if(lstblacklist.getSelectedIndex()>=0)
			{
				
				friendinfo=vblacklist.get(lstblacklist.getSelectedIndex());
				
			}
			if(e.getButton()==3)
			{
				if(lstblacklist.getSelectedIndex()>=0)	
				{
					pmmenu.show(lstblacklist,e.getX(),e.getY());
				}
			}
		}
		
	}

	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	class ReceiveThread extends Thread
	{

		@Override
		public void run()
		{
			DatagramSocket socket=null;
			try {
				socket = new DatagramSocket(myinfo.getPort());
			} catch (SocketException e1) {
				e1.printStackTrace();
			}
			while(true)
			{
				try {
					byte b[] = new byte[1024*512];
					//接收信息
					
					DatagramPacket pack = new DatagramPacket(b,0,b.length);
					
					socket.receive(pack);
					System.out.println("上线了！");
					ByteArrayInputStream bais = new ByteArrayInputStream(pack.getData());
					ObjectInputStream ois = new ObjectInputStream(bais);
					SendMsg msg = (SendMsg)ois.readObject();
					friendinfo=msg.myinfo;
					myinfo=msg.friendinfo;
					System.out.println("friendinfo="+friendinfo);
					System.out.println("myinfo="+myinfo);
					switch(msg.cmd){
					case Cmd.CMD_ONLINE: //登录（上线)
						reflush();
						new Sound(Cmd.CMD_ONLINE);
						new TipUI(friendinfo);
						break;
					case Cmd.CMD_BUYS:
					case Cmd.CMD_LEVEL:
						reflush();
						break;
					case Cmd.CMD_OFFLINE:
						reflush();
						new Sound(Cmd.CMD_OFFLINE);
						new TipUI(friendinfo);
						break;
					case Cmd.CMD_CHANGESTATUE:
						reflush();
						break;
					case Cmd.CMD_SEND:	
						System.out.println("接收聊天消息。。。");
						new Sound(Cmd.CMD_SEND);
						//ChatUI chatUI=chatWin.get(myinfo.getQqcode());
						ChatUI chatUI=openChat();
//						if(chatUI==null)
//						{
//							
//							chatUI=new ChatUI(msg.friendinfo,msg.myinfo);				
//							chatWin.put(myinfo.getQqcode(),chatUI);
//						}
						
						chatUI.setVisible(true);
						try {
							chatUI.appendView(msg.myinfo.getNickname(),msg.doc );
							
						} catch (Exception e) {
							// TODO: handle exception
						}
						
						break;
					case Cmd.CMD_SHAKE:
						
						//chatUI=chatWin.get(myinfo.getQqcode());
						chatUI=openChat();
//						if(chatUI==null)
//						{
//							
//							chatUI=new ChatUI(msg.friendinfo,msg.myinfo);				
//							chatWin.put(myinfo.getQqcode(),chatUI);
//						}
						new Sound(Cmd.CMD_SHAKE);
						chatUI.setVisible(true);
						try {
							chatUI.appendView(msg.myinfo.getNickname(),msg.doc );
							
						} catch (Exception e) {
							// TODO: handle exception
						}
						chatUI.shake();
						break;
					case Cmd.CMD_ADDFRI:
						System.out.println("接收到好友请求");
					
						
						System.out.println(friendinfo);
						String str=friendinfo.getNickname()+"请求添加好友，是否同意？";
						SendMsg msg2=new SendMsg();
						if(JOptionPane.showConfirmDialog(null,str,"添加好友",JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION)
						{
							msg2.cmd=Cmd.CMD_AGREE;
							dateBaseDAO.addfriend(myinfo.getQqcode(), friendinfo.getQqcode());
							new Sound(msg.cmd);
							reflush();
							//JOptionPane.showMessageDialog(null, (friendinfo.getNickname()+"已是您的好友"));
							
						}else{
							msg2.cmd=Cmd.CMD_REFUSE;
						}
						msg2.myinfo=myinfo;
						msg2.friendinfo=friendinfo;
						SendCmd.send(msg2);
					case Cmd.CMD_AGREE:	
						reflush();
						new Sound(Cmd.CMD_AGREE);
						JOptionPane.showMessageDialog(null, (myinfo.getNickname()+"已是您的好友"));
						break;
					case Cmd.CMD_REFUSE:
						new Sound(Cmd.CMD_REFUSE);
						JOptionPane.showMessageDialog(null, (friendinfo.getNickname()+"拒绝了你"));
						break;
					case Cmd.CMD_DELETEFRIEND:
						reflush();
						break;
					case Cmd.CMD_FILE:
						str=friendinfo.getNickname()+"发送了一个文件，是否接收？";
						if(JOptionPane.showConfirmDialog(null,str ,"文件接收",JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION)
						{
							
							JFileChooser fileChooser=new JFileChooser("");
							fileChooser.setDialogType(JFileChooser.SAVE_DIALOG);
							fileChooser.setDialogTitle("接收文件");
							if(fileChooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION)
							{
								String ext=msg.filename.substring(msg.filename.indexOf("."),msg.filename.length());
								String string=fileChooser.getSelectedFile().getAbsolutePath()+ext;
								FileOutputStream fos=new FileOutputStream(string);
								fos.write(msg.b);
								fos.flush();
								fos.close();
								
								
								
							}
							
						}
					default:
						break;
					}
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
				
			
		}
		
	}
	
	public void itemStateChanged(ItemEvent e)
	{
		if(e.getSource()==cbState)
		{
			statuechange();
			String statue=cbState.getSelectedItem().toString();
			dateBaseDAO.changestatue(myinfo.getQqcode(), statue);
			SendCmd.sendAll(vallinfo, myinfo, Cmd.CMD_CHANGESTATUE);
			
		}
		
	}
	public void statuechange()
	{
		String status=cbState.getSelectedItem().toString();
		String filename=myinfo.getHeadImage();
		String headimg=myinfo.getHeadImage();
		if(status.equals(Cmd.STATUS[0]))
		{
			filename=headimg;
		}else if(status.equals(Cmd.STATUS[2]))
		{
			int pos=headimg.indexOf('.');
			String pre=headimg.substring(0,pos);
			String fix=headimg.substring(pos,headimg.length());
			filename=pre+"_l"+fix;
		}else if(status.equals(Cmd.STATUS[3]))
		{
			int pos=headimg.indexOf('.');
			String pre=headimg.substring(0,pos);
			String fix=headimg.substring(pos,headimg.length());
			filename=pre+"_w"+fix;
		}
		lblmyinfo.setIcon(new ImageIcon(filename));
	}

	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		dateBaseDAO.changestatue(myinfo.getQqcode(), Cmd.STATUS[1]);
		myinfo.setOnlinestatu(Cmd.STATUS[1]);
		SendCmd.sendAll(vallinfo, myinfo, Cmd.CMD_OFFLINE);
	}

	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		SystemTray systemTray=SystemTray.getSystemTray();
		if(trayIcon!=null)
		{
			systemTray.remove(trayIcon);
		}
		try {
			systemTray.add(trayIcon);
			MainUI.this.setVisible(false);
		} catch (AWTException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


}
