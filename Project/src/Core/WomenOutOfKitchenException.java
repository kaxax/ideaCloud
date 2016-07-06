package Core;

public class WomenOutOfKitchenException  extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WomenOutOfKitchenException(){};
	
	public WomenOutOfKitchenException(String text){
		super(text);
	}
}
