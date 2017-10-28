package QQ;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.plaf.metal.MetalIconFactory.FolderIcon16;
import javax.swing.table.AbstractTableModel;
public class FindUI extends JFrame implements ActionListener,MouseListener

{
	JLabel lblqqcode,lblnickname,lblsex,lblage,lblbirthaddr,lblstatue;
	JTextField txtqqcode,txtnickname;
	JComboBox cbsex,cbage,cbbirthcountry,cbbirthprovince,cbbirthcity,cbbirthcounty,cbstatue;
	JButton btnfind,btnadd,btnclose;
	AccountVo myinfo;
	JTable datatable;
	Vector<String> vhead;
	Vector<Vector<Object>> vdata;
	JLabel lblbj;
	
	DateBaseDAO dateBaseDAO=new DateBaseDAO();
	public FindUI()
	{
		init();
	}
	public FindUI(AccountVo myinfo)
	{
		this.myinfo=myinfo;
		System.out.println("myinfo="+myinfo);
		
		init();
	}
	public void init()
	{
		lblbj=new JLabel(new ImageIcon("HeadImg/00001.jpg"));	
		lblbj.setLayout(null);
		add(lblbj);
		JPanel topPanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
		lblqqcode=new JLabel("qq账号");
		lblnickname=new JLabel("昵称");
		lblsex=new JLabel("性别");
		lblage=new JLabel("出生年份");
		lblbirthaddr=new JLabel("所在地");
		lblstatue=new JLabel("状态");
		txtqqcode=new JTextField(10);
		txtnickname=new JTextField(10);
		String ssex[]={"不选择","帅哥","美女"};
		cbsex=new JComboBox(ssex);
		String sage[]={"不选择","1996","1997","1998","1999","2000"};
		
	
		
		cbage=new JComboBox(sage);
		String addrcountry[]={"不选择","中国","美国","英国","德国"};
		cbbirthcountry=new JComboBox(addrcountry);
		String addrprovince[]={"不选择","江西","上海","北京"};
		cbbirthprovince=new JComboBox(addrprovince);
		String addrcity[]={"不选择","赣州","吉安","萍乡"};
		cbbirthcity=new JComboBox(addrcity);
		String addrcounty[]={"不选择","崇义","于都","安远"};
		cbbirthcounty=new JComboBox(addrcounty);
		String statue[]={"不选择","在线","离线","忙碌","隐身"};
		cbstatue=new JComboBox(statue);
		topPanel.add(lblqqcode);
		topPanel.add(txtqqcode);
		topPanel.add(lblnickname);
		topPanel.add(txtnickname);
		topPanel.add(lblsex);
		topPanel.add(cbsex);
		topPanel.add(lblage);
		topPanel.add(cbage);
		topPanel.add(lblstatue);
		topPanel.add(cbstatue);
		topPanel.add(lblbirthaddr);
		topPanel.add(cbbirthcountry);
		topPanel.add(cbbirthprovince);
		topPanel.add(cbbirthcity);
		topPanel.add(cbbirthcounty);
		add(topPanel,BorderLayout.NORTH);
		
		
	    vhead=new Vector<String>();
	    String clumname[]={"头像","QQ号码","昵称","性别","年","月","日","国","省","市","县","状态","备注"};
	    for(int i=0;i<clumname.length;i++)
	    {
			vhead.addElement(clumname[i]);
		}
	    
		String sql = "select headImage,qqcode,nickName,sex,birthyear,birthmonth,birthday," +
				"birthcountry,birthprovince,birthcity,birthcounty,onlinestatu,remark from account";
		
		vdata=dateBaseDAO.findFriend(sql);
		
		datatable = new JTable(new MyTableModel(vhead,vdata));
		datatable.setRowHeight(60);
		add(new JScrollPane(datatable));
		
		
		

	    JPanel	buttom=new JPanel(new FlowLayout(FlowLayout.RIGHT));
	    btnfind=new JButton("查找");
	    btnclose=new JButton("关闭");
	    btnadd=new JButton("添加好友");
	    buttom.add(btnfind);
	    buttom.add(btnadd);
	    buttom.add(btnclose);
	    add(buttom,BorderLayout.SOUTH);
	    
	    
	    btnfind.addActionListener(this);
	    btnadd.addActionListener(this);
	    btnclose.addActionListener(this);
	    datatable.addMouseListener(this);
		
		
		setResizable(false);
	    setSize(1000,500);
	    setVisible(true);
	    setLocationRelativeTo(null);
	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    
		
	}
	public static void main(String[] args)
	{
		new FindUI(null);
	}
	
	
	 class MyTableModel extends AbstractTableModel {
	    	Vector<String> vhead;
	    	Vector<Vector<Object>> data;
	        public MyTableModel(Vector<String> vhead,Vector<Vector<Object>> data) {
	        	this.vhead = vhead;
	        	this.data = data;
	        }
	        
	        public int getColumnCount() {
	            return vhead.size();
	        }
	     
	        public int getRowCount() {
	            return data.size();
	        }
	     
	        public String getColumnName(int col) {
            	
	        	// System.out.println("col====="+vhead.get(col));
	            return (vhead.get(col));
	           
	        }
	       
	        public Object getValueAt(int row, int col) {
	        	Vector rowData = (Vector)vdata.get(row);
	        	if(col==0)
	        	{
	        		return new ImageIcon(rowData.get(col).toString());
	        	}else {
	        		return rowData.get(col);
				}
	           
	        }
	        @Override
	        public Class<?> getColumnClass(int c) {
	        	if(c==0)
	        	{
	        		  return ImageIcon.class;
	        	}else {
					return super.getColumnClass(c);
				}
	          
	        }
	        
	        public boolean isCellEditable(int row, int col)
	        {
	            
	            return false;
	        }
	       
	        public void setValueAt(Object value, int row, int col) {
	        	Vector rowData = (Vector)vdata.get(row);
	        	rowData.set(col, value);
	            fireTableCellUpdated(row, col);
	        }
	    }


	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnfind)
		{
			System.out.println("查找好友");
			String sql = "select headImage,qqcode,nickName,sex,birthyear,birthmonth,birthday," +
			"birthcountry,birthprovince,birthcity,birthcounty,onlinestatu,remark from account";
			sql+=" where 1=1 ";
			System.out.println("查找好友2");
			String qqcode=txtqqcode.getText().trim();
			String nickname=txtnickname.getText().trim();
			System.out.println("查找好友3");
			if(!qqcode.equals(""))
			{
				sql+=" and qqcode="+qqcode;
			}
			System.out.println("查找好友4");
			if(!nickname.equals(""))
			{
				sql+=" and nickName like '%"+nickname+"%'";
			}
			String sex=cbsex.getSelectedItem().toString();
			System.out.println("查找好友5");
			if(!sex.equals("不选择"))
			{
				sql+=" and sex='"+sex+"'";
			}
			String age=cbage.getSelectedItem().toString();
			System.out.println("查找好友6");
			if(!age.equals("不选择"))
			{
				sql+=" and birthyear='"+age+"'";
			}
			System.out.println("查找好友7");
			String birthcountry=cbbirthcountry.getSelectedItem().toString();
			if(!birthcountry.equals("不选择"))
			{
				sql+=" and birthcountry='"+birthcountry+"'";
			}
			System.out.println("查找好友8");
			String birthprovince=cbbirthprovince.getSelectedItem().toString();
			if(!birthprovince.equals("不选择"))
			{
				sql+=" and birthprovince='"+birthprovince+"'";
			}
			System.out.println("查找好友9");
			String birthcity=cbbirthcity.getSelectedItem().toString();
			if(!birthcity.equals("不选择"))
			{
				sql+=" and birthcity='"+birthcity+"'";
			}
			String birthcounty=cbbirthcounty.getSelectedItem().toString();
			if(!birthcounty.equals("不选择"))
			{
				sql+=" and birthcounty='"+birthcounty+"'";
			}
			System.out.println("查找好友10");
			String onlinestatue=cbstatue.getSelectedItem().toString();
			if(!onlinestatue.equals("不选择"))
			{
				sql+=" and onlinestatu='"+onlinestatue+"'";
			}
		
			sql+=" order by birthyear";
			
			vdata=dateBaseDAO.findFriend(sql);
			
			datatable.setModel(new MyTableModel(vhead,vdata));
		
		}else if(e.getSource()==btnadd)
		{
			int index=datatable.getSelectedRow();
			if(index>=0)
			{
				
					Vector row=(Vector)vdata.get(index);
					int qqcode=Integer.parseInt(row.get(1).toString());
					if(qqcode==myinfo.getQqcode())
					{
						JOptionPane.showMessageDialog(this, "不能添加自己为好友");
						return;
					}
					if(dateBaseDAO.isFriend(myinfo.getQqcode(),qqcode))
					{
						JOptionPane.showMessageDialog(this, "已是好友,无需再次添加");
						return;
						
					}
					AccountVo friendinfo=dateBaseDAO.getSelectFriend(qqcode);
					String str="是否添加"+friendinfo.getNickname()+"为好友";
					if(JOptionPane.showConfirmDialog(this,str,"添加好友",JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION)
					{
						SendMsg msg=new SendMsg();
						msg.cmd=Cmd.CMD_ADDFRI;
						msg.myinfo=myinfo;
						msg.friendinfo=friendinfo;
						System.out.println("发送添加好友请求");
						SendCmd.send(msg);
						JOptionPane.showMessageDialog(this,("等待"+friendinfo.getNickname()+"确认"));
					}
					
				
			}
		}else if(e.getSource()==btnclose)
		{
			dispose();
		}
		
		
	}
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==datatable)
		{
			int index=datatable.getSelectedRow();
			if(index>=0)
			{
				if(e.getClickCount()==2)
				{
					Vector row=(Vector)vdata.get(index);
					int qqcode=Integer.parseInt(row.get(1).toString());
					if(qqcode==myinfo.getQqcode())
					{
						JOptionPane.showMessageDialog(this, "不能添加自己为好友");
						return;
					}
					if(dateBaseDAO.isFriend(myinfo.getQqcode(),qqcode))
					{
						JOptionPane.showMessageDialog(this, "已是好友,无需再次添加");
						return;
						
					}
					AccountVo friendinfo=dateBaseDAO.getSelectFriend(qqcode);
					String str="是否添加"+friendinfo.getNickname()+"为好友";
					if(JOptionPane.showConfirmDialog(this,str,"添加好友",JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION)
					{
						SendMsg msg=new SendMsg();
						msg.cmd=Cmd.CMD_ADDFRI;
						msg.myinfo=myinfo;
						msg.friendinfo=friendinfo;
						SendCmd.send(msg);
						JOptionPane.showMessageDialog(this,("等待"+friendinfo.getNickname()+"确认"));
					}
					
				}
			}
			
		}
	}
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
