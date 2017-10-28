package QQ;

import java.io.Serializable;

public class Friendvo implements Serializable
{
	private int friendId;
	private int myQQcode;
	private int friedQQcode;
	private int groupName;
	public int getFriendId() {
		return friendId;
	}
	public void setFriendId(int friendId) {
		this.friendId = friendId;
	}
	public int getMyQQcode() {
		return myQQcode;
	}
	public void setMyQQcode(int myQQcode) {
		this.myQQcode = myQQcode;
	}
	public int getFriedQQcode() {
		return friedQQcode;
	}
	public void setFriedQQcode(int friedQQcode) {
		this.friedQQcode = friedQQcode;
	}
	public int getGroupName() {
		return groupName;
	}
	public void setGroupName(int groupName) {
		this.groupName = groupName;
	}
	
	
}
