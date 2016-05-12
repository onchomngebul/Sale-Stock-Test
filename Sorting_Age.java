import java.io.*;
import java.util.*;

public class Sorting_Age {
	
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
            
            //declare list
            List<Integer> listAge = new ArrayList<Integer>();
            
            //input data to list
            while((line = bufferedReader.readLine()) != null) {
                listAge.add(Integer.parseInt(line));
            }   
            
            // close files.
            bufferedReader.close();  
            
            //sorting begin, it using counting sort algorithm
            Integer[] arrayAge = listAge.toArray(new Integer[listAge.size()]);
            int[] sortedAge = countingSort(arrayAge, maxAge);
                
            //declare filename
            File file = new File("sorted_" + fileName);
            
        	// if file doesn't exists, then create it
         	if (!file.exists()) {
         		file.createNewFile();
         	}
         	
         	//wrap in buffered
         	FileWriter fw = new FileWriter(file.getAbsoluteFile());
         	BufferedWriter bw = new BufferedWriter(fw);
         	
         	//preparing content
         	String content = "";
         	for (int i : sortedAge) {
         		content += i + "\n";
			}
         	
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
	
	//Because "Age" is integer with maximum limit, Counting Sort algorithm is the best
	static int[] countingSort(Integer[] arrayAge, int maxValue) {
        int counterAge[] = new int[maxValue + 1];
        
        for (int i = 0; i < arrayAge.length; i++)
            counterAge[arrayAge[i]]++;
        
        for (int i = 1; i < maxValue; i++)
            counterAge[i] += counterAge[i-1];
        
        int sortedAge[] = new int[arrayAge.length];
        
        for (int i = arrayAge.length-1; i >= 0; i--)
            sortedAge[--counterAge[arrayAge[i]]] = arrayAge[i];
        
        return sortedAge;
        //source of code
        //http://www.opendatastructures.org/ods-java/11_2_Counting_Sort_Radix_So.html
    }
	
	/*
	 however, this algorithm can not be used to handle Big Data due to the limited 
	 amount elements in the array (the maximum array elements is 2 ^ 31-6)
	 Can't handle for 7 Billion people data of the earth
	 
	 more explanation at BigData_Sort.java
	 */
}


