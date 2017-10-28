package QQ;

import java.io.Serializable;

import javax.swing.text.StyledDocument;

public class SendMsg implements Serializable
{
	public int cmd;
	public AccountVo myinfo;
	public AccountVo friendinfo;
	public StyledDocument doc;
	public byte b[];
	public String filename;
	
}
