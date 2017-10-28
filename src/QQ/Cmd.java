package QQ;

public interface Cmd
{
	public static final int CMD_ONLINE=1000;
	public static final int CMD_OFFLINE=1001;
	public static final int CMD_LEVEL=1002;
	public static final int CMD_BUYS=1003;
	public static final int CMD_SEND=1004;
	public static final int CMD_FILE=1005;	
	public static final int CMD_SHAKE=1006;
	public static final int CMD_ADDFRI=1007;
	public static final int CMD_AGREE=1008;
	public static final int CMD_REFUSE=1009;
	public static final int CMD_CHANGESTATUE=1010;
	public static final int CMD_DELETEFRIEND=1011;

	public static final String[] STATUS={"在线","离线","忙碌","隐身"};
	public static final String[] GROUPNAME={"同学","好友","家人","黑名单"};
}
