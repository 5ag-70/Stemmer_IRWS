import java.io.*;
import java.util.*;

public class task1 {
  public static void main(String[] args) throws Exception {
    FileReader file = new FileReader("input1.txt");
    BufferedReader reader = new BufferedReader(file);
    String text = "";
    String input = reader.readLine();
    while (input != null){
    text += input;
    input = reader.readLine();
    }
    System.out.println("Input File: " + text);
    File f2 = new File("task1output.txt");
    PrintWriter out = (new PrintWriter(new FileWriter(f2)));
	Scanner scannerInput = new Scanner(new File("input1.txt"));
	String inputWords = scannerInput.useDelimiter("\\Z").next();
    inputWords = inputWords.replaceAll("[^a-zA-Z0-9\t\n]"," "); //remove everything except a-z, A-Z, 0-9 and tab.
	inputWords = inputWords.replaceAll("( )+", " ").toLowerCase(); //remove any and make lowercase
	Scanner scannerStopWords = new Scanner(new File("StopWordList.txt"));
	String stopWords = scannerStopWords.useDelimiter("\\Z").next();
	String stopWordsArray = stopWords.replaceAll("\r", "").replaceAll("\n","|");
    String line = inputWords.replaceAll("\\b("+stopWordsArray+")\\b\\s*","");
    out.write(line);
    out.write(System.getProperty("line.separator"));
	reader.close();
    out.close();
    Stemmer stemmer = new Stemmer();
	String[] fileName = new String[]{"task1output.txt"};
	String stemmerOutput = stemmer.pass(fileName);
	String heading = "", body = "", outputWords = "";
	String[] documents = stemmerOutput.split("\n");
	for(int i=0;i<documents.length; i++){
		heading = documents[i].substring(0, documents[i].indexOf("\t")).toUpperCase();
		body = documents[i].substring(documents[i].indexOf("\t"));
		outputWords = outputWords + heading + body + "\n";
	}
    File f3 = new File("task1output.txt");
    PrintWriter output = (new PrintWriter(new FileWriter(f3)));
    output.write(outputWords);
    output.write(System.getProperty("line.separator"));
    output.close();
    System.out.println("Output File: \n" + outputWords);
  }
}
