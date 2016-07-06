package Core;

public class Notification {
	private int id;
	private String sId;
	private String rId;
	private int seen;
	public Notification(String sender, String reciever, int seen){
		this.sId = sender;
		this.rId = reciever;
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
	public int getStatus(){
		return seen;
	}
}
