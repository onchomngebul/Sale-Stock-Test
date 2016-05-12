import java.io.*;

public class BigData_Sort {
	
	/*
	Main Problem for Big Data processing is not RAM, but efficiency and effectivity in algorithm.
	For this case, counting algorithm is the best way. But the problem is limit of element in array.
	Array Index can only accomodate for integer data type and it was no more than 2.5 billion.
	So I change the algorithm little so it doesn't store all data in array anymore
	*/
	
	//Maximum Age for sorting purpose
	static int maxAge = 150;
	
	// The name of the file to open.
	static String fileName = "age.txt";
	
	public static void main(String [] args) {
        // reference one line at a time
        String line = null;

        try {
        	// FileReader reads text files
        	FileReader fileReader = new FileReader(fileName);

        	// wrap FileReader in BufferedReader.
        	BufferedReader bufferedReader = new BufferedReader(fileReader);
            
        	//declare variable
        	int counterAge[] = new int[maxAge + 1];
        	int age = 0;
            
        	//input data to list and counting data for each age
        	while((line = bufferedReader.readLine()) != null) {
        		age = Integer.parseInt(line);
        		counterAge[age]++;
        	}   
            
        	// close files.
        	bufferedReader.close();  
            
        	//preparing content
         	String content = "";
         	
         	/*
         	 I make little change in algorithm. 
         	 I just looping a number of elements that exist in sequence. 
         	 looks much more simple, but not hindered by amount elements in the array.
         	 */     
         	//sorting and fill content to write
         	for (int i = 1; i < counterAge.length; i++) {
			for (int j = 0; j < counterAge[i]; j++) {
				content += i + "\n";
			}
		}
         	//NOTE: there is no Extreme data in one data age, 
         	//for example for age 23 there is more than 2.5 billion data 
			
         	//for testing purpose
         	System.out.println(content);
                
        	//declare filename
        	File file = new File("sorted_" + fileName);
            
		// if file doesn't exists, then create it
         	if (!file.exists()) {
         		file.createNewFile();
         	}
         	
         	//wrap in buffered
         	FileWriter fw = new FileWriter(file.getAbsoluteFile());
         	BufferedWriter bw = new BufferedWriter(fw);
         	         	
         	//write and close
         	bw.write(content);
         	bw.close();
         	
         	System.out.println("Done");
            
        }
        catch(FileNotFoundException ex) {
        	System.out.println("Unable to open file '" + fileName + "'");                
        }
        catch(IOException ex) {
        	ex.printStackTrace();
        }
    }	
}
