package QQ;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.Vector;

import javax.swing.ImageIcon;
import java.awt.image.*;
public class DateBaseDAO
{
	public int getQQcode()
	{
		int qqcode=0;
		boolean bExist=false;
		
		Connection conn=DBConn.openDB();
		String sql="select * from account where qqcode=?";
		try {
		

			PreparedStatement ps=conn.prepareStatement(sql);
			while(!bExist)
			{
				Random random=new Random();
				qqcode=random.nextInt(899999999)+1000000000;
				ps.setInt(1,qqcode);
			
//				AccountVo accountVo=new AccountVo;
//				accountVo.setQqcode();
				ResultSet r=ps.executeQuery();
				if(!r.next())
				{
					bExist=true;
					break;
				}
			}
			ps.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return qqcode;
	}
	public int getPort()
	{
		int port=0;
		boolean bExist=false;
		
		Connection conn=DBConn.openDB();
		String sql="select * from account where onlinestatu<>? and port=?";
		try {
		
			
			PreparedStatement ps=conn.prepareStatement(sql);
			while(!bExist)
			{
				Random random=new Random();
				port=random.nextInt(55535)+10000;
				ps.setString(1,Cmd.STATUS[1]);
				
				ps.setInt(2, port);
				ResultSet r=ps.executeQuery();
				if(!r.next())
				{
					bExist=true;
					break;
				}
			}
			ps.close();
			//conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return port;
	}
	public AccountVo saveAccountVo(AccountVo aVo)
	{
		aVo.setQqcode(getQQcode());
		aVo.setPort(getPort());
		aVo.setOnlinestatu(Cmd.STATUS[1]);//离线
		
		Connection conn=DBConn.openDB();
		String sql="insert into account values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		try {
			int i=1;
			//private String onlinestatu;
			PreparedStatement pstmt=conn.prepareStatement(sql);
			
			pstmt.setInt(i++, aVo.getQqcode());
			pstmt.setString(i++, aVo.getNickname());
			pstmt.setString(i++, aVo.getPassword());
			pstmt.setString(i++, aVo.getSex());
			pstmt.setString(i++, aVo.getStar());
			pstmt.setString(i++, aVo.getBlood());
			pstmt.setString(i++, aVo.getNation());
			pstmt.setString(i++,aVo.getHobbit());
			pstmt.setString(i++, aVo.getAddr());
			pstmt.setInt(i++, aVo.getPort());	
			pstmt.setString(i++, aVo.getRemark());
			pstmt.setString(i++, aVo.getOnlinestatu());
			pstmt.setString(i++, aVo.getHeadImage());
			pstmt.setString(i++, aVo.getBirthyear());
			pstmt.setString(i++, aVo.getBirthmonth());
			pstmt.setString(i++, aVo.getBirthday());
			pstmt.setString(i++, aVo.getBirthcountry());
			pstmt.setString(i++, aVo.getBirthprovince());
			pstmt.setString(i++,aVo.getBirthcity());
			pstmt.setString(i++,aVo.getBirthcounty());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return aVo;
	}
	public AccountVo updateAccountVo(AccountVo aVo)
	{
		Connection conn=DBConn.openDB();
		String sql="update account set nickName=?,sex=?,star=?,blood=?,nation=?,hobbit=?,remark=?," +
				"headImage=?,birthyear=?,birthmonth=?,birthday=?,birthcountry=?,birthprovince=?," +
				"birthcity=?,birthcounty=? where qqcode=?";
		
		try {
			int i=1;
			//private String onlinestatu;
			PreparedStatement pstmt=conn.prepareStatement(sql);
			
			
			pstmt.setString(i++, aVo.getNickname());
			//pstmt.setString(i++, aVo.getPassword());
			pstmt.setString(i++, aVo.getSex());
			pstmt.setString(i++, aVo.getStar());
			pstmt.setString(i++, aVo.getBlood());
			pstmt.setString(i++, aVo.getNation());
			pstmt.setString(i++,aVo.getHobbit());
			//pstmt.setString(i++, aVo.getAddr());
			//pstmt.setInt(i++, aVo.getPort());	
			pstmt.setString(i++, aVo.getRemark());
			//pstmt.setString(i++, aVo.getOnlinestatu());
			pstmt.setString(i++, aVo.getHeadImage());
			pstmt.setString(i++, aVo.getBirthyear());
			pstmt.setString(i++, aVo.getBirthmonth());
			pstmt.setString(i++, aVo.getBirthday());
			pstmt.setString(i++, aVo.getBirthcountry());
			pstmt.setString(i++, aVo.getBirthprovince());
			pstmt.setString(i++,aVo.getBirthcity());
			pstmt.setString(i++,aVo.getBirthcounty());
			pstmt.setInt(i++, aVo.getQqcode());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return aVo;
	}
	public AccountVo login(AccountVo account)
	{
		// TODO Auto-generated method stub
		Connection conn=DBConn.openDB();
		String sql="select * from account where qqcode=? and password=?";
		try {
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, account.getQqcode());
			pstmt.setString(2, account.getPassword());
			ResultSet rSet=pstmt.executeQuery();
			if(rSet.next())
			{
				//aVo.setQqcode(aVo.getQqcode());
				account.setNickname(rSet.getString("nickName").trim());
				//aVo.setPassword(rSet.getString("password").trim());
				account.setSex(rSet.getString("sex").trim());
				account.setStar(rSet.getString("star").trim());
				account.setBlood(rSet.getString("blood").trim());
				account.setNation(rSet.getString("nation").trim());
				account.setHobbit(rSet.getString("hobbit").trim());
				
				InetAddress address=null;
				try {
					address=InetAddress.getLocalHost();
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String ip=address.getHostAddress();
				account.setAddr(ip);
//				
				int port = getPort();
				account.setPort(port);
				account.setRemark(rSet.getString("remark").trim());
				account.setOnlinestatu(Cmd.STATUS[0]);
				account.setHeadImage(rSet.getString("headImage").trim());
				account.setBirthyear(rSet.getString("birthyear").trim());
				account.setBirthmonth(rSet.getString("birthmonth").trim());
				account.setBirthday(rSet.getString("birthday").trim());
				account.setBirthcountry(rSet.getString("birthcountry").trim());
				
				
				account.setBirthprovince(rSet.getString("birthprovince").trim());
				account.setBirthcity(rSet.getString("birthcity").trim());
				account.setBirthcounty(rSet.getString("birthcounty").trim());
				
				sql="update account set addr='"+ip+"' ,port="+port+",onlinestatu='"+Cmd.STATUS[0]+"' where qqcode="+account.getQqcode();
				System.out.println(sql);
			 //sql =    "update account set port="+port +",onlinestatus='" + Cmd.STATUS[0] + "' where qqcode="+account.getQqCode();
				Statement statement=conn.createStatement();
				statement.executeUpdate(sql);
				pstmt.close();
				statement.close();
				
			}else
			{
				account=null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return account;
	}
	public Vector<AccountVo> getMyfriend(int myqqcode)
	{
		Connection conn=DBConn.openDB();
		Vector<AccountVo> vmyFriend=new Vector<AccountVo>();
		//int qqcode=0;
		String sql="select a.*,f.groupname from account a inner join friends f on a.qqcode=f.friendqqcode where myqqcode=?;";
		try {
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, myqqcode);
			
			ResultSet rSet=pstmt.executeQuery();
			while(rSet.next())
			{
				AccountVo account= new AccountVo();
				account.setQqcode(rSet.getInt("qqcode"));
				account.setNickname(rSet.getString("nickName").trim());
				//aVo.setPassword(rSet.getString("password").trim());
				account.setSex(rSet.getString("sex").trim());
				account.setStar(rSet.getString("star").trim());
				account.setBlood(rSet.getString("blood").trim());
				account.setNation(rSet.getString("nation").trim());
				account.setHobbit(rSet.getString("hobbit").trim());
				account.setAddr(rSet.getString("addr").trim());
				account.setPort(rSet.getInt("port"));
				
				
				account.setRemark(rSet.getString("remark").trim());
				account.setOnlinestatu(rSet.getString("onlinestatu").trim());
				account.setHeadImage(rSet.getString("headImage").trim());
				account.setBirthyear(rSet.getString("birthyear").trim());
				account.setBirthmonth(rSet.getString("birthmonth").trim());
				account.setBirthday(rSet.getString("birthday").trim());
				account.setBirthcountry(rSet.getString("birthcountry").trim());
				
				
				account.setBirthprovince(rSet.getString("birthprovince").trim());
				account.setBirthcity(rSet.getString("birthcity").trim());
				account.setBirthcounty(rSet.getString("birthcounty").trim());
				account.setGroupname(rSet.getString("groupname").trim());
				
				vmyFriend.add(account);
			}
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vmyFriend;
		
	}
	
	public void movegroup(int myqqcode,int friendqqcoe,String groupname)
	{
		System.out.println("进行分组");
		String string="update friends set groupname=? where myqqcode=? and friendqqcode=?";
		Connection conn=DBConn.openDB();
		
		try {
			PreparedStatement pstmt=conn.prepareStatement(string);
			int i=1;
			pstmt.setString(i++, groupname);
			pstmt.setInt(i++,myqqcode);
			pstmt.setInt(i++,friendqqcoe);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public Vector<Vector<Object>> findFriend(final String sql){
		System.out.println("____________________________");
		Vector<Vector<Object>> vdata = new Vector<Vector<Object>>();
		Connection conn = DBConn.openDB();
		System.out.println("____________________________1");
		try{
			//int port =getPort();
			Statement pstmt = conn.createStatement();
			ResultSet rs = pstmt.executeQuery(sql);
			System.out.println("____________________________2");
			while(rs.next()){
				System.out.println("____________________________3");
				Vector rowData = new Vector();	
				rowData.addElement(rs.getString("headImage").trim());
				rowData.addElement(rs.getInt("qqcode"));
				rowData.addElement(rs.getString("nickName").trim());
				rowData.addElement(rs.getString("sex").trim());
				rowData.addElement(rs.getInt("birthyear"));			
				rowData.addElement(rs.getString("birthmonth").trim());
				rowData.addElement(rs.getString("birthday").trim());
				rowData.addElement(rs.getString("birthcountry").trim());
				rowData.addElement(rs.getString("birthprovince").trim());
				rowData.addElement(rs.getString("birthcity").trim());
				rowData.addElement(rs.getString("birthcounty").trim());
				rowData.addElement(rs.getString("onlinestatu").trim());
				rowData.addElement(rs.getString("remark"));
				vdata.addElement(rowData);
			}
			System.out.println("____________________________");
			pstmt.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return vdata;
	}
	
	public AccountVo getSelectFriend(int myqqcode)
	{
		Connection conn=DBConn.openDB();
		AccountVo account=new AccountVo();
		//int qqcode=0;
		String sql="select * from account where qqcode=?;";
		try {
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, myqqcode);
			
			ResultSet rSet=pstmt.executeQuery();
			while(rSet.next())
			{
				
				account.setQqcode(rSet.getInt("qqcode"));
				account.setNickname(rSet.getString("nickName").trim());
	
				account.setSex(rSet.getString("sex").trim());
				account.setStar(rSet.getString("star").trim());
				account.setBlood(rSet.getString("blood").trim());
				account.setNation(rSet.getString("nation").trim());
				account.setHobbit(rSet.getString("hobbit").trim());
				account.setAddr(rSet.getString("addr").trim());
				account.setPort(rSet.getInt("port"));
				
				
				account.setRemark(rSet.getString("remark").trim());
				account.setOnlinestatu(rSet.getString("onlinestatu").trim());
				account.setHeadImage(rSet.getString("headImage").trim());
				account.setBirthyear(rSet.getString("birthyear").trim());
				account.setBirthmonth(rSet.getString("birthmonth").trim());
				account.setBirthday(rSet.getString("birthday").trim());
				account.setBirthcountry(rSet.getString("birthcountry").trim());
				
				
				account.setBirthprovince(rSet.getString("birthprovince").trim());
				account.setBirthcity(rSet.getString("birthcity").trim());
				account.setBirthcounty(rSet.getString("birthcounty").trim());
				
				
				
			}
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return account;
		
	}
	public void addfriend(int myqqcode,int friendqqcode)
	{
		Connection conn=DBConn.openDB();
		String sql="insert into friends values(?,?,?,?)";
		try {
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, 0);
			pstmt.setInt(2,myqqcode);
			pstmt.setInt(3, friendqqcode);
			pstmt.setString(4, Cmd.GROUPNAME[1]);
			pstmt.executeUpdate();
			pstmt.setInt(1, 0);
			pstmt.setInt(2, friendqqcode);
			pstmt.setInt(3,myqqcode);
			pstmt.setString(4, Cmd.GROUPNAME[1]);
			pstmt.executeUpdate();
			pstmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void deletefriend(int myqqcode,int friendqqcode)
	{
		Connection conn=DBConn.openDB();
		String sql="delete friends where myqqcode=? and friendqqcode=?";
		try {
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,myqqcode);
			pstmt.setInt(2, friendqqcode);
			pstmt.executeUpdate();
			pstmt.setInt(1, friendqqcode);
			pstmt.setInt(2,myqqcode);
			pstmt.executeUpdate();
			pstmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public boolean isFriend(int myqqcode,int friendqqcode)
	{
		Connection conn=DBConn.openDB();
		AccountVo account=new AccountVo();
		boolean isok=false;
		String sql="select * from friends where myqqcode=? and friendqqcode=?;";
		try {
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, myqqcode);
			pstmt.setInt(2, friendqqcode);
			ResultSet rSet=pstmt.executeQuery();
			if(rSet.next())
			{
				
				isok=true;
				
			}
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isok;
		
	}	
	public void changestatue(int qqcode,String statue)
	{
		Connection conn=DBConn.openDB();
		String sql="update account set onlinestatu=? where qqcode=?";
		
		try {
			
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, statue);
			pstmt.setInt(2, qqcode);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
