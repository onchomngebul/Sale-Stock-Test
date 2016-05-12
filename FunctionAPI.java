import java.io.*;
import java.util.Map;
import java.util.TreeMap;


public class FunctionAPI {

	//using treemap, so it will order ascending and for better latency purpose
	//Source : 
	//http://stackoverflow.com/questions/7057430/treemap-or-hashmap-faster
	static Map<Integer, String> blackList = new TreeMap<Integer, String>();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		initialize("blacklist.txt");
		
		//for testing purpose
		System.out.println(check_blacklist("Aslam4", 29083452));
	}
	
	//initialize(blacklist)
	//This function takes string input, which is the file name of the blacklist you have,
	//and called when the API server is starting. 	
	public static void initialize(String blacklist){
		
		// reference one line at a time
        String line = null;

        try {
            // FileReader reads text files
            FileReader fileReader = new FileReader(blacklist);

            // wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            
            //declare temp variable
            String[] split = new String[2];
            
            //input data to treeMap
            while((line = bufferedReader.readLine()) != null) {
            	split = line.split(" ");
            	blackList.put(Integer.parseInt(split[1]), split[0]);
            }   
            
            // close files.
            bufferedReader.close();  
            
          //for testing purpose
//            for (Map.Entry<Integer, String> entry : blackList.entrySet())
//            {
//                System.out.println(entry.getKey() + "-/-" + entry.getValue());
//            }
            
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + blacklist + "'");                
        }
        catch(IOException ex) {
        	ex.printStackTrace();
        }
	}
	
	//check_blacklist(name, phone_number)
	//This function takes 2 arguments, name(string) and phone number(int). 
	//This function is called whenever the API is called, 
	//and return boolean the input name and phone number is in the blacklist.
	public static Boolean check_blacklist(String name, int phone_number){

		return blackList.containsKey(phone_number) && blackList.containsValue(name);

	}
}
