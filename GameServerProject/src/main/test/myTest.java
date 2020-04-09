
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;

public class myTest {
	
	
		
		@Test
		public void firstTest()
		{
			try {

				Socket socket=new Socket("localhost",34567);
				ObjectOutputStream writer = new ObjectOutputStream(socket.getOutputStream());

		    	JSONObject jsonOut=new JSONObject();
		    	JSONArray arrIn=new JSONArray();
		    	jsonOut.put("type", "New-Game");
		    	jsonOut.put("game", "Tic Tac Tow");
		    	jsonOut.put("opponent","Random");				
				writer.writeObject(jsonOut);
				System.out.println("the messege is:");
				System.out.println(jsonOut.toString());
				JSONObject jsonIn = null;
				try {
					ObjectInputStream reader = new ObjectInputStream(socket.getInputStream());
					jsonIn = (JSONObject) reader.readObject();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				 String type=  (String) jsonIn.get("type");
				 int id=  (int) jsonIn.get("ID");
				 arrIn=(JSONArray) jsonIn.get("board");				 
				 System.out.println("type: "+type+" ID: "+id);
				 System.out.println(arrIn.toString());

			
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		
		}

	}

