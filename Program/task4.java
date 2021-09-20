import java.io.*;
import java.util.*;
import java.text.DecimalFormat;

public class task4 {
  public static void main(String[] args) throws Exception {
	Scanner scannerInput = new Scanner(new File(args[0]));
	String inputWords = scannerInput.useDelimiter("\\Z").next();
	ArrayList<String> values1List = new ArrayList<>();
	ArrayList<String> values2List = new ArrayList<>();
	HashMap<String,String> invertMap = new HashMap<String,String>();
    System.out.println("Task 4 Initializing....");
    System.out.println("Input File: ");
    System.out.println(inputWords);
	String document1 = args[1];
	String document2 = args[2];
	int document1index = 0;
	int document2index = 0;
	String[] inputLines = inputWords.split("\n");
	String[] headings = inputLines[0].split("\\s+");
	for(int i=0; i<headings.length; i++){
		if(headings[i].equals(document1))
			document1index = i;
		if(headings[i].equals(document2))
			document2index = i;
	}
	for(int i=1; i<inputLines.length; i++){
		String[] values = inputLines[i].split("\\s+");
		for(int j=0; j<values.length; j++){
			if(j==document1index)
				values1List.add(values[j]);
			if(j==document2index)
				values2List.add(values[j]);
		}
	}
	System.out.println(headings[document1index]);
	System.out.println(values1List);
	System.out.println(headings[document2index]);
	System.out.println(values2List);

	double dotProduct = 0;
	for(int i=0; i<values1List.size(); i++){
		dotProduct = dotProduct + Double.parseDouble(values1List.get(i))*Double.parseDouble(values2List.get(i));
	}
	double summationValues1List = 0;
	for(int i=0; i<values1List.size(); i++){
		summationValues1List = summationValues1List + (Double.parseDouble(values1List.get(i))*Double.parseDouble(values1List.get(i)));
	}
	double modulusValues1List = Math.sqrt(summationValues1List);
	
	double summationValues2List = 0;
	for(int i=0; i<values2List.size(); i++){
		summationValues2List = summationValues2List + (Double.parseDouble(values1List.get(i))*Double.parseDouble(values1List.get(i)));
	}
	double modulusValues2List = Math.sqrt(summationValues2List);
	
	DecimalFormat df = new DecimalFormat("#.###");
	double cousineSimilarity = dotProduct / (modulusValues1List * modulusValues2List);
	String cs = "Cousine Similarity of document " + args[1] + " and " + args[2] + " is " + "= " + String.valueOf(df.format(cousineSimilarity));
	System.out.println(cs);
	File output_file = new File("task4output.txt");
	PrintWriter out = (new PrintWriter(new FileWriter(output_file)));
	out.write(cs);
	out.close();
  }
}
