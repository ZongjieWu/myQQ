package QQ;

import java.awt.event.ActionListener;

import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Toolkit;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;



public class LookinfoUI extends JFrame implements MouseListener
{
	JLabel lblqqcode,lblpassword,lblcfgpwd,lblnickname;
	JLabel lblbirthday,lblsex,lblnation,lblstar,lblblood;
	JLabel lbladdr,lblport,lblremark,lblhobbit,lblheadimg;
	JLabel lbljb,lbltitle,lblbirthaddr;
	JTextField txtqqcode,txtnickname;
	JTextField txtaddr,txtport,txtbirthaddr;
	JPasswordField txtpassword,txtcfgpwd;
	JComboBox cbyear,cbmonth,cbday,cbnation,cbstar,cbblood;
	JComboBox cbhobbit,cbheadimg,cbbirthcountry;
	JComboBox cbbirthprovince,cbbirthcounty,cbbirthcity;
	JTextArea remark;
	JRadioButton rbmale,rbremale,rbball,rbbook,rbmusic;
	JRadioButton rbchessandcard,rbgame,rbother,rbsport;
	ButtonGroup btngp;
	JButton btnsubmit,btnabandon;
	AccountVo myinfo;
	MainUI mainui;
	
	public LookinfoUI()
	{
	}
	public LookinfoUI(AccountVo myinfo)
	{
		super("用户信息");
		
		this.myinfo=myinfo;
		this.mainui=mainui;
		
		Toolkit toolkit=Toolkit.getDefaultToolkit();
		Image icon=toolkit.getImage("HeadImg/tubiao.png");
		setIconImage(icon);
		
		
		lbljb=new JLabel(new ImageIcon("HeadImg/000.jpg"));
		add(lbljb);
		lbljb.setLayout(null);
		
		
		
		lbltitle=new JLabel("用户信息",JLabel.CENTER);
		lbltitle.setFont(new Font("宋体",Font.BOLD,25));
		lbltitle.setBounds(100,10,200,20);
		lbltitle.setForeground(Color.RED);
		
		lblqqcode=new JLabel("QQ账号: "+myinfo.getQqcode());	
		lblqqcode.setBounds(30,50,150,20);
		lblnickname=new JLabel("昵称: "+myinfo.getNickname());
		lblnickname.setBounds(30,90,500,20);
		lblheadimg=new JLabel(new ImageIcon(myinfo.getHeadImage()));
		lblheadimg.setBounds(150,50,200,200);
		lblbirthday=new JLabel("生日: "+myinfo.getBirthyear()+"年"+myinfo.getBirthmonth()+"月"+myinfo.getBirthday()+"日");
		lblbirthday.setBounds(30,140,200,20);
		lblsex=new JLabel("性别: "+myinfo.getSex());
		lblsex.setBounds(30,170,200,20);
		lblnation=new JLabel("民族: "+myinfo.getNation());
		lblnation.setBounds(30,200,200,20);
		lblstar=new JLabel("星座: "+myinfo.getStar());
		lblstar.setBounds(150,200,100,20);
		lblblood=new JLabel("血型: "+myinfo.getBlood());
		lblblood.setBounds(270,200,100,20);
		lblbirthaddr=new JLabel("出生地: "+myinfo.getBirthcountry()+myinfo.getBirthprovince()+"省"+myinfo.getBirthcity()+"市"+myinfo.getBirthcounty()+"县");
		lblbirthaddr.setBounds(15,230,300,20);
		lblhobbit=new JLabel("爱好: "+myinfo.getHobbit());
		lblhobbit.setBounds(30,275,100,20);
		lblremark=new JLabel("说明: "+myinfo.getRemark());
		lblremark.setBounds(30,365,200,20);
		
		
		lbljb.add(lbltitle);
		lbljb.add(lblqqcode);
		lbljb.add(lblnickname);
		lbljb.add(lblheadimg);
		
		lbljb.add(lblbirthday);
		lbljb.add(lblsex);
		lbljb.add(lblnation);
		lbljb.add(lblstar);
		lbljb.add(lblblood);
		lbljb.add(lblbirthaddr);
		lbljb.add(lblhobbit);
		lbljb.add(lblremark);
		
		addMouseListener(this);
		setResizable(false);
		setSize(400,600);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}
	public static void main(String[] args)
	{
		new LookinfoUI();
	}
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		dispose();
	}
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	

}
