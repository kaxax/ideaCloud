package Core;

public class Message {
	private int id;
	private String sId;
	private String rId;
	private String msg;
	private int seen;
	public Message(String sender, String reciever, String text, int seen){
		this.sId = sender;
		this.rId = reciever;
		this.msg = text;
		this.seen = seen;
				
	}
	public void setId(int id){
		this.id = id;
		
	}
	public String getSender(){
		return this.sId;
	}
	public String getReciever(){
		return this.rId;
	}
	public String getMessage(){
		return this.msg;
	}
	public int getStatus(){
		return seen;
	}
}
