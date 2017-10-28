package QQ;

import java.awt.event.ActionListener;

import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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



public class UpdateMyinfoUI extends JFrame implements ActionListener
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
	
	String syear[]={"1996","1997","1998","1999","2000"};
	String smonth[]={"1","2","3","4","5","6"};
	String sday[]={"1","2","3","4","5","6","7","8","9"};
	String snation[]={"汉族","孨族","朝鲜族","壮族","蒙古","族"};
	String sstar[]={"射手座","摩羯座","双鱼座","处女座","天秤座"};
	String sblood[]={"A","B","O","AB"};
	String shobbit[]={"篮球","唱歌","其他"};
	String sbirthcountry[]={"中国","美国","英国","德国"};
	String sbirthprovince[]={"江西","上海","北京"};
	String sbirthcity[]={"赣州","吉安","萍乡"};
	String sbirthcounty[]={"崇义","于都","安远"};
	String sheadimg[]={"HeadImg/0.png","HeadImg/1.png","HeadImg/2.png","HeadImg/3.png",
			"HeadImg/4.png","HeadImg/5.png","HeadImg/6.png"};
	ImageIcon[] headicon={new ImageIcon(sheadimg[0]),new ImageIcon(sheadimg[1]),
			new ImageIcon(sheadimg[2]),new ImageIcon(sheadimg[3]),
			new ImageIcon(sheadimg[4]),new ImageIcon(sheadimg[5]),
			new ImageIcon(sheadimg[6])};
	public UpdateMyinfoUI()
	{
	}
	public UpdateMyinfoUI(AccountVo myinfo,MainUI mainui)
	{
		super("用户信息修改");
		
		this.myinfo=myinfo;
		this.mainui=mainui;
		
		Toolkit toolkit=Toolkit.getDefaultToolkit();
		Image icon=toolkit.getImage("HeadImg/tubiao.png");
		setIconImage(icon);
		
		
		lbljb=new JLabel(new ImageIcon("HeadImg/000.jpg"));
		add(lbljb);
		lbljb.setLayout(null);
		
		btngp=new ButtonGroup();
//		lblqqcode=new JLabel("QQ账号");
//		txtqqcode=new JTextField(10);
		
		
		lbltitle=new JLabel("修改用户信息",JLabel.CENTER);
		lbltitle.setFont(new Font("宋体",Font.BOLD,25));
		lbltitle.setBounds(100,10,200,20);
		lbltitle.setForeground(Color.RED);
		
		lblnickname=new JLabel("昵称");
		lblnickname.setBounds(30,50,50,20);
		txtnickname=new JTextField(myinfo.getNickname());
		txtnickname.setBounds(60,50,100,20);
//		lblpassword=new JLabel("QQ密码");
//		lblpassword.setBounds(10,80,50,20);
//		txtpassword=new JPasswordField(10);
//		txtpassword.setBounds(60,80,100,20);
//		txtpassword.setEchoChar('*');
//		lblcfgpwd=new JLabel("确认密码");
//		txtpassword.setEchoChar('$');
//		lblcfgpwd.setBounds(5,110,60,20);
//		txtcfgpwd=new JPasswordField(10);
//		txtcfgpwd.setBounds(60,110,100,20);
		lblheadimg=new JLabel("我的头像");
		lblheadimg.setBounds(300,50,60,20);
		cbheadimg=new JComboBox(headicon);
		for(int i=0;i<sheadimg.length;i++)
		{
			System.out.println(sheadimg+"--"+myinfo.getHeadImage());
			if(sheadimg[i].equals(myinfo.getHeadImage()))
			{
				cbheadimg.setSelectedIndex(i);
				
				break;
			}
		}
		cbheadimg.setBounds(250,80,120,100);
		lblbirthday=new JLabel("生日");
		lblbirthday.setBounds(30,140,30,20);
		cbyear=new JComboBox(syear);
		for(int i=0;i<syear.length;i++)
		{
			if(syear[i].equals(myinfo.getBirthyear()))
			{
				cbyear.setSelectedIndex(i);
				break;
			}
		}
		cbyear.setBounds(60,140,55,20);
		//cbyear.setOpaque(false);
		cbmonth=new JComboBox(smonth);
		for(int i=0;i<smonth.length;i++)
		{
			if(smonth[i].equals(myinfo.getBirthmonth()))
			{
				cbmonth.setSelectedIndex(i);
				break;
			}
		}
		cbmonth.setBounds(115,140,55,20);
		//cbmonth.setOpaque(false);
		cbday=new JComboBox(sday);
		for(int i=0;i<sday.length;i++)
		{
			if(sday[i].equals(myinfo.getBirthday()))
			{
				cbday.setSelectedIndex(i);
				break;
			}
		}
		cbday.setBounds(170,140,55,20);
		//cbday.setOpaque(false);
		lblsex=new JLabel("性别");
		lblsex.setBounds(30,170,30,20);
	
		rbmale=new JRadioButton("帅哥");
		rbremale=new JRadioButton("美女");
		btngp=new ButtonGroup();
		btngp.add(rbmale);
		btngp.add(rbremale);
		
		if(myinfo.getSex().equals("帅哥"))
		{
			rbmale.setSelected(true);
		}else{
			rbremale.setSelected(true);
			System.out.println("rbremale");
		}
		
		
		
		rbmale.setBounds(60,170,80,20);
		rbmale.setOpaque(false);
		rbmale.setSelected(true);
		
		
		rbremale.setBounds(150,170,80,20);
		rbremale.setOpaque(false);
		
		
		
		
		
		lblnation=new JLabel("民族");
		lblnation.setBounds(30,200,30,20);
		cbnation=new JComboBox(snation);
		for(int i=0;i<snation.length;i++)
		{
			if(snation[i].equals(myinfo.getNation()))
			{
				cbnation.setSelectedIndex(i);
				break;
			}
		}
		cbnation.setBounds(60,200,70,20);
		lblstar=new JLabel("星座");
		lblstar.setBounds(150,200,30,20);
		cbstar=new JComboBox(sstar);
		for(int i=0;i<sstar.length;i++)
		{
			if(sstar[i].equals(myinfo.getStar()))
			{
				cbstar.setSelectedIndex(i);
				break;
			}
		}
		cbstar.setBounds(180,200,70,20);
		lblblood=new JLabel("血型");
		lblblood.setBounds(270,200,30,20);
		cbblood=new JComboBox(sblood);
		for(int i=0;i<sblood.length;i++)
		{
			if(sblood[i].equals(myinfo.getBlood()))
			{
				cbblood.setSelectedIndex(i);
				break;
			}
		}
		cbblood.setBounds(300,200,70,20);
		lblbirthaddr=new JLabel("出生地");
		lblbirthaddr.setBounds(15,230,40,20);
		cbbirthcountry=new JComboBox(sbirthcountry);
		for(int i=0;i<sbirthcountry.length;i++)
		{
			if(sbirthcountry[i].equals(myinfo.getBirthcountry()))
			{
				cbbirthcountry.setSelectedIndex(i);
				break;
			}
		}
		cbbirthcountry.setBounds(60,230,70,20);
		cbbirthprovince=new JComboBox(sbirthprovince);
		for(int i=0;i<sbirthprovince.length;i++)
		{
			if(sbirthprovince[i].equals(myinfo.getBirthprovince()))
			{
				cbbirthprovince.setSelectedIndex(i);
				break;
			}
		}
		cbbirthprovince.setBounds(140,230,70,20);
		cbbirthcity=new JComboBox(sbirthcity);
		for(int i=0;i<sbirthcity.length;i++)
		{
			if(sbirthcity[i].equals(myinfo.getBirthcity()))
			{
				cbbirthcity.setSelectedIndex(i);
				break;
			}
		}
		cbbirthcity.setBounds(220,230,70,20);
		cbbirthcounty=new JComboBox(sbirthcounty);
		for(int i=0;i<sbirthcounty.length;i++)
		{
			if(sbirthcounty[i].equals(myinfo.getBirthcounty()))
			{
				cbbirthcounty.setSelectedIndex(i);
				break;
			}
		}
		cbbirthcounty.setBounds(300,230,70,20);
		txtbirthaddr=new JTextField(50);
		txtbirthaddr.setBounds(60,255,310,20);
		
		
		lblhobbit=new JLabel("爱好");
		lblhobbit.setBounds(30,275,30,20);
		//cbhobbit=new JComboBox(shobbit);
		//cbhobbit.setBounds(1,1,11,1);
		rbball=new JRadioButton("球类");
		if(myinfo.getHobbit().equals("球类"))
		{
			rbball=new JRadioButton("球类",true);
		}
		
		rbball.setBounds(60,275,60,20);
		rbball.setOpaque(false);
		rbbook=new JRadioButton("书类");
		if(myinfo.getHobbit().equals("书类"))
		{
			rbbook=new JRadioButton("书类",true);
		}
		
		rbbook.setBounds(180,275,60,20);
		rbbook.setOpaque(false);
		
		rbchessandcard=new JRadioButton("棋牌");
		if(myinfo.getHobbit().equals("棋牌"))
		{
			rbchessandcard=new JRadioButton("棋牌",true);
		}
		
		rbchessandcard.setBounds(300,275,60,20);
		rbchessandcard.setOpaque(false);
		rbgame=new JRadioButton("游戏");
		if(myinfo.getHobbit().equals("游戏"))
		{
			rbgame=new JRadioButton("游戏",true);
		}
		
		rbgame.setBounds(60,305,60,20);
		rbgame.setOpaque(false);
		rbsport=new JRadioButton("运动");
		if(myinfo.getHobbit().equals("运动"))
		{
			rbsport=new JRadioButton("运动",true);
		}
		
		rbsport.setBounds(180,305,60,20);
		rbsport.setOpaque(false);
		rbother=new JRadioButton("其他");
		if(myinfo.getHobbit().equals("其他"))
		{
			rbother=new JRadioButton("其他",true);
		}
		
		rbother.setBounds(300,305,60,20);
		rbother.setOpaque(false);
//		lbladdr=new JLabel("IP地址");
//		lbladdr.setBounds(20,335,50,20);
//		txtaddr=new JTextField("192.168.1.21",10);
//		txtaddr.setBounds(60,335,130,20);
//		lblport=new JLabel("端口");
//		lblport.setBounds(200,335,50,20);
//		txtport=new JTextField("55555",10);	
//		txtport.setBounds(230,335,130,20);
		lblremark=new JLabel("说明");
		lblremark.setBounds(30,365,30,20);
		remark=new JTextArea(myinfo.getRemark());
		remark.setBounds(60,365,300,110);
		btnsubmit=new JButton("提交");
		btnsubmit.setBounds(60,485,80,50);
		btnabandon=new JButton("放弃");
		btnabandon.setBounds(280,485,80,50);
		
		
		
		
		lbljb.add(lbltitle);
		lbljb.add(lblnickname);
		lbljb.add(txtnickname);
//		lbljb.add(lblpassword);
//		lbljb.add(txtpassword);
//		lbljb.add(lblcfgpwd);
//		lbljb.add(txtcfgpwd);
		lbljb.add(lblheadimg);
		lbljb.add(cbheadimg);
		lbljb.add(lblbirthday);
		lbljb.add(cbyear);
		lbljb.add(cbmonth);
		lbljb.add(cbday);
		lbljb.add(lblsex);
		lbljb.add(rbmale);
		lbljb.add(rbremale);
		lbljb.add(lblnation);
		lbljb.add(cbnation);
		lbljb.add(lblstar);
		lbljb.add(cbstar);
		lbljb.add(lblblood);
		lbljb.add(cbblood);
		lbljb.add(lblbirthaddr);
		lbljb.add(cbbirthcountry);
		lbljb.add(cbbirthprovince);
		lbljb.add(cbbirthcity);
		lbljb.add(cbbirthcounty);
		lbljb.add(txtbirthaddr);
		lbljb.add(lblhobbit);
		lbljb.add(rbball);
		lbljb.add(rbbook);
		lbljb.add(rbchessandcard);
		lbljb.add(rbgame);
		lbljb.add(rbsport);
		lbljb.add(rbother);
//		lbljb.add(lbladdr);
//		lbljb.add(txtaddr);
//		lbljb.add(lblport);
//		lbljb.add(txtport);
		lbljb.add(btnsubmit);
		lbljb.add(btnabandon);
		
	
		lbljb.add(lblremark);
		lbljb.add(remark);
		
		
		btnabandon.addActionListener(this);
		btnsubmit.addActionListener(this);
	
		
	
		
		
	
		setResizable(false);
		setSize(400,600);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}
	public static void main(String[] args)
	{
		new UpdateMyinfoUI();
	}
	public void actionPerformed(ActionEvent e)
	{
		
		if(e.getSource()==btnabandon)
		{
			//System.exit(0);
			dispose();
		}else if(e.getSource()==btnsubmit)
		{
			if(txtnickname.getText().trim().equals(""))
			{
				JOptionPane.showMessageDialog(this,"请输入昵称");
				return;
			}
//			if(txtpassword.getText().trim().equals("")||txtcfgpwd.getText().trim().equals(""))
//			{
//				JOptionPane.showMessageDialog(this,"请输入QQ密码或确认密码");
//				return;
//			}
//			if(!txtpassword.getText().trim().equals(txtcfgpwd.getText().trim()))
//			{
//				JOptionPane.showMessageDialog(this,"QQ密码与确认密码不一致");
//				return;
//			}
//			if(txtaddr.getText().trim().equals("")||txtport.getText().trim().equals(""))
//			{
//				JOptionPane.showMessageDialog(this,"请输入IP或端口");
//				return;
//			}
//			if(txtpassword.getText().trim().length()<6)
//			{
//				JOptionPane.showMessageDialog(this,"QQ密码必须为6个以上字符");
//				return;
//			}
			
			myinfo.setNickname(txtnickname.getText().trim());
//			myinfo.setPassword(txtpassword.getText().trim());
			if(rbmale.isSelected())
			{
				myinfo.setSex("帅哥");
			}else{
				myinfo.setSex("美女");
			}
			myinfo.setStar(sstar[cbstar.getSelectedIndex()]);
			myinfo.setBlood(sblood[cbblood.getSelectedIndex()]);
			myinfo.setNation(snation[cbnation.getSelectedIndex()]);
			if(rbball.isSelected())
			{
				myinfo.setHobbit("球类\n");
			}
			if(rbbook.isSelected())
			{
				myinfo.setHobbit("书类\n");
			}
			
			if(rbchessandcard.isSelected())
			{
				myinfo.setHobbit("棋牌\n");
			}
			if(rbgame.isSelected())
			{
				myinfo.setHobbit("游戏\n");
			}
			if(rbother.isSelected())
			{
				myinfo.setHobbit("其他\n");
			}
			if(rbsport.isSelected())
			{
				myinfo.setHobbit("运动\n");
			}
//			Avo.setAddr(txtaddr.getText().trim());
			myinfo.setRemark(remark.getText().trim());
			myinfo.setHeadImage(sheadimg[cbheadimg.getSelectedIndex()]);
			myinfo.setBirthyear(syear[cbyear.getSelectedIndex()]);
			myinfo.setBirthmonth(smonth[cbmonth.getSelectedIndex()]);
			myinfo.setBirthday(sday[cbday.getSelectedIndex()]);
			myinfo.setBirthcountry(sbirthcountry[cbbirthcountry.getSelectedIndex()]);
			myinfo.setBirthprovince(sbirthprovince[cbbirthprovince.getSelectedIndex()]);
			myinfo.setBirthcity(sbirthcity[cbbirthcity.getSelectedIndex()]);
			myinfo.setBirthcounty(sbirthcounty[cbbirthcounty.getSelectedIndex()]);
			
			
			
			
			DateBaseDAO dBaseDAO=new DateBaseDAO();
			myinfo=dBaseDAO.updateAccountVo(myinfo);
//			txtqqcode.setText(Avo.getQqcode()+"");
//			txtport.setText("0");
//			System.out.println("保存成功");
			ImageIcon icon=new ImageIcon(myinfo.getHeadImage());
			String str=myinfo.getNickname()+"("+myinfo.getQqcode()+")【"+myinfo.getRemark()+"】";
			mainui.lblmyinfo.setIcon(icon);
			mainui.lblmyinfo.setText(str);
			dispose();
			
			JOptionPane.showMessageDialog(this,"恭喜你修改信息成功!!");
			
		}
	}

}
