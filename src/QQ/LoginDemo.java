package QQ;

import java.awt.Color;

import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

import javax.swing.AbstractListModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory.Default;

public class LoginDemo extends JFrame implements ActionListener, MouseListener,MouseMotionListener,
		ItemListener {
	JLabel lblbj, lblqqaccount, lblpassword, lblregister, lblretrievepassword;
	JLabel lbl_, lblx, lblrememberpassword, lblautologin, lblheadimage;
	//JTextField txtpassword;
	JPasswordField txtpassword;
	JCheckBox cbrememberpasswordf, cblblautologinf;
	JComboBox cbqqcount;
	JButton btnlogin;
	HashMap<Integer, AccountVo> user = null;

	public LoginDemo() {
		super("QQ登入界面");
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image image = toolkit.getImage("HeadImg/tubiao.png");
		setIconImage(image);

		lblbj = new JLabel(new ImageIcon("HeadImg/444.jpg"));
		add(lblbj);
		lblbj.setLayout(null);

		lblqqaccount = new JLabel("QQ账户");
		lblqqaccount.setBounds(70, 185, 50, 20);

		cbqqcount = new JComboBox();
		cbqqcount.setBounds(125, 180, 200, 30);
		cbqqcount.setEditable(true);
		cbqqcount.setToolTipText("账号");

		lblregister = new JLabel("账号注册");
		lblregister.setBounds(330, 185, 60, 20);

		lblpassword = new JLabel("QQ密码");
		lblpassword.setBounds(70, 220, 50, 20);

		txtpassword = new JPasswordField();
		txtpassword.setBounds(125, 220, 200, 30);
		txtpassword.setToolTipText("密码");

		lblretrievepassword = new JLabel("找回密码");
		lblretrievepassword.setBounds(330, 225, 60, 20);

		btnlogin = new JButton("登入");
		btnlogin.setBounds(175, 255, 100, 30);

		lbl_ = new JLabel("_");
		lbl_.setForeground(Color.RED);
		lbl_.setFont(new Font("楷体", Font.PLAIN, 20));
		lbl_.setBounds(450, 20, 20, 20);

		lblx = new JLabel("x");
		lblx.setForeground(Color.RED);
		lblx.setFont(new Font("楷体", Font.PLAIN, 20));
		lblx.setBounds(480, 20, 20, 20);

		lblheadimage = new JLabel(new ImageIcon("HeadImg/1112.jpg"));
		lblheadimage.setBounds(150, 10, 200, 200);

		lblrememberpassword = new JLabel("记住密码");
		lblrememberpassword.setBounds(70, 250, 70, 50);
		cbrememberpasswordf = new JCheckBox();
		cbrememberpasswordf.setBounds(120, 250, 50, 50);
		cbrememberpasswordf.setOpaque(false);

		lblautologin = new JLabel("重新登入");
		lblautologin.setBounds(330, 250, 70, 50);
		cblblautologinf = new JCheckBox();
		cblblautologinf.setBounds(310, 250, 50, 50);
		cblblautologinf.setOpaque(false);

		lblbj.add(lblqqaccount);
		lblbj.add(cbqqcount);
		lblbj.add(lblregister);
		lblbj.add(lblpassword);
		lblbj.add(txtpassword);
		lblbj.add(lblretrievepassword);
		lblbj.add(btnlogin);
		lblbj.add(lbl_);
		lblbj.add(lblx);
		lblbj.add(lblheadimage);

		lblbj.add(lblrememberpassword);
		lblbj.add(lblautologin);

		lblbj.add(cbrememberpasswordf);
		lblbj.add(cblblautologinf);

		lblregister.addMouseListener(this);
		lbl_.addMouseListener(this);
		lblx.addMouseListener(this);

		cbqqcount.addActionListener(this);
		cbrememberpasswordf.addActionListener(this);
		btnlogin.addActionListener(this);
		cbqqcount.addItemListener(this);
		this.addMouseMotionListener(this);
		
		
		
		setAlwaysOnTop(true);
		readFile();// 读取文件信息
		setUndecorated(true);
		setSize(500, 300);
		show(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) 
	{
		new LoginDemo();
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnlogin){
			String qqcode="";
			String pwd = txtpassword.getText().trim();
			System.out.println(pwd);
			if(cbqqcount.getSelectedItem() == null || cbqqcount.getSelectedItem().toString().equals(""))
			{
				JOptionPane.showMessageDialog(this, "请输入QQ号码");
				return;
			}
			if(pwd.equals("")){
				JOptionPane.showMessageDialog(this, "请输入登录密码");
				return;
			}
			qqcode = cbqqcount.getSelectedItem().toString();
			
			AccountVo account = new AccountVo();
			account.setQqcode(Integer.parseInt(qqcode));
			account.setPassword(pwd);
			System.out.println("   "+pwd);
			account = new DateBaseDAO().login(account);
			if(account==null){
				JOptionPane.showMessageDialog(this, "登录失败，用户名或密码错误!");
				return;
			}else{
				//JOptionPane.showMessageDialog(this, "登录成功");
				//保存登录成功的QQ号码到文件中
				save(account);
				dispose();
				new MainUI(account);
			}
			
			
		}else if(e.getSource()==cbrememberpasswordf){
			JOptionPane.showMessageDialog(this, "记住密码");
		}
		
	}
	
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == lblregister) {
			new RegUi();
		}
		if (e.getSource() == lbl_) {
			this.setState(JFrame.HIDE_ON_CLOSE);
		}
		if (e.getSource() == lblx) {
			System.exit(0);
		}
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
		x=this.getX();
		y=this.getY();
	}

	public void mouseReleased(MouseEvent e) {
	}

	// 保存登录成功的QQ号码到文件中
	public void save(AccountVo account){
		HashMap<Integer, AccountVo> user =null;
		File file = new File("QQ2.txt");
		try {
			if(!file.exists()){// 第一次使用QQ
					file.createNewFile();
					user = new HashMap<Integer, AccountVo>();
			}else{
				FileInputStream fis = new FileInputStream(file);
				ObjectInputStream ois = new ObjectInputStream(fis);
				// 读取文件内容
				user = (HashMap<Integer, AccountVo>)ois.readObject();
				fis.close();
				ois.close();
			}
			// 新登录的用户信息保存到hashMap中
			user.put(account.getQqcode(), account);
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("QQ2.txt"));
			oos.writeObject(user);
			oos.flush();
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//读取文件内容
	public void readFile(){
		try{
			File file = new File("QQ2.txt");
			
			//文件不存在就结束
			if(!file.exists()&&file.length()==0){
				return;
			}
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);
			//成员变量，不是局部变量
			user = (HashMap<Integer, AccountVo>)ois.readObject();
			Set<Integer> set = user.keySet();
			Iterator<Integer> it = set.iterator();
			while(it.hasNext()){
				cbqqcount.addItem(it.next());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	public void itemStateChanged(ItemEvent e) {
		if(e.getSource()==cbqqcount){
			if(cbqqcount.getSelectedItem()!=null && user!=null && !cbqqcount.getSelectedItem().equals("")){
				int qqcode = Integer.parseInt(cbqqcount.getSelectedItem().toString());
				System.out.println(qqcode);
				AccountVo account = user.get(qqcode);
				if(account!=null){
	//				System.out.println(qqcode);
	//				System.out.println(account.getPassword());
					txtpassword.setText(account.getPassword());
					lblheadimage.setIcon(new ImageIcon(account.getHeadImage()));
				}
			}
		}
	}
	int x,y;
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		this.setLocation(e.getX()+x, e.getY()+y);
		x=this.getX();
		y=this.getY();
	}

	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}


