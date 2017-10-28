package QQ;

public class FriendInfo {

	private int qqcode;
	private String nickname;
	private String headimg;
	private String remark;
	public FriendInfo() {
	
	}
	public FriendInfo(int qqcode,String nickname,String headimg,String remark) {
		this.qqcode = qqcode;
		this.nickname = nickname;
		this.headimg = headimg;
		this.remark = remark;
	}
	public int getQqcode() {
		return qqcode;
	}
	public void setQqcode(int qqcode) {
		this.qqcode = qqcode;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getHeadimg() {
		return headimg;
	}
	public void setHeadimg(String headimg) {
		this.headimg = headimg;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
