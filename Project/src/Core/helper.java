package Core;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;


public class helper {
	public ArrayList<String> getMessages(String first, String second) throws IOException{
		ArrayList<String> strList = null;
		try {

			Connection con = Pool.getPool().getConnection();

			Database db = new Database(con);
			strList = db.getOldMessages(first, second);
			con.close();
			for(int i=0; i<strList.size(); i++){
				//System.out.println(strList.get(i));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return strList;
	}
	public ArrayList<String> getList(String user) throws IOException{
		
		ArrayList<String> names = new ArrayList<String>();
		try {

			Connection con = Pool.getPool().getConnection();

			Database db = new Database(con);
			names = db.getPeers(user);
			
			con.close();
			return names;
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public String getPeers(String user) throws IOException{
		ArrayList<String> names = getList(user);
		String result ="";
		StringBuffer values = new StringBuffer();
		for (int i = 0; i < names.size(); ++i) {
		    if (values.length() > 0) {
		        values.append(',');
		    }
		    values.append(names.get(i));
		
		    
		}
		result = values.toString();
		return result;
	}
	
}
