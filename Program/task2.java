import java.io.*;
import java.util.*;

public class task2 {
  public static void main(String[] args) throws Exception {
	Scanner scannerInput = new Scanner(new File(args[0]));
	String inputWords = scannerInput.useDelimiter("\\Z").next();
	LinkedHashMap<String,String> invertMap = new LinkedHashMap<String,String>();
    System.out.println("Task 2 Initializing....");
    System.out.println("Input File: ");
    System.out.println(inputWords);
	String line[] = inputWords.split("\n");
	for (int i=0; i<line.length; i++) {
		String heading[] = line[i].split("\t");
		String word[] = heading[1].split("\\s");
		for (int j=0; j<word.length; j++) {
			String oldHeading = invertMap.get(word[j]);
			if(oldHeading == null || oldHeading.equals(heading[0])){
				invertMap.put(word[j], heading[0]);
			}
			else if(oldHeading.contains(heading[0])) {
				invertMap.put(word[j], oldHeading);
			}
			else {
				invertMap.put(word[j], oldHeading + "," + heading[0]);
			}
		}
	}
	System.out.print("Output file:\n");
	File output_file = new File(args[1]);
	PrintWriter out = (new PrintWriter(new FileWriter(output_file)));
	for (String key : invertMap.keySet()) {
		String value = invertMap.get(key);
		if(value.contains(",")) {
			String[] values = value.split(",");
			value = "";
			for(int i=0; i<values.length; i++){
				value = value + values[i] + "\t";
			}
			
		}
		String output_value;
		if(key.length()>7)
			output_value = key + "\t" + value;
		else
			output_value = key + "\t\t" + value;
		System.out.print(output_value);
		System.out.println();
		out.write(output_value);
		out.write(System.getProperty("line.separator"));
	}
    out.close();
  }
}
