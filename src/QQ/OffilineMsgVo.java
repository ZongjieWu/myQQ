package QQ;

import java.io.Serializable;

public class OffilineMsgVo implements Serializable 
{
	private int msgid;
	private int myQQcode;
	private int friendQQcode;
	private int cmd;
	private int msg;
	public int getMsgid() {
		return msgid;
	}
	public void setMsgid(int msgid) {
		this.msgid = msgid;
	}
	public int getMyQQcode() {
		return myQQcode;
	}
	public void setMyQQcode(int myQQcode) {
		this.myQQcode = myQQcode;
	}
	public int getFriendQQcode() {
		return friendQQcode;
	}
	public void setFriendQQcode(int friendQQcode) {
		this.friendQQcode = friendQQcode;
	}
	public int getCmd() {
		return cmd;
	}
	public void setCmd(int cmd) {
		this.cmd = cmd;
	}
	public int getMsg() {
		return msg;
	}
	public void setMsg(int msg) {
		this.msg = msg;
	}
	
}
