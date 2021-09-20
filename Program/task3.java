import java.io.*;
import java.util.*;
import java.text.DecimalFormat;

public class task3 {
  public static void main(String[] args) throws Exception {
	  
	Scanner scannerInput = new Scanner(new File("task1output.txt"));
	String task1output = scannerInput.useDelimiter("\\Z").next();
    //System.out.println("Output File of task1: \n" + task1output);
	
	scannerInput = new Scanner(new File(args[0]));
	String task2output = scannerInput.useDelimiter("\\Z").next();
    //System.out.println("Output File of task2: \n" + task2output);
	
	LinkedHashMap<String,String> tf = new LinkedHashMap<String,String>();
	LinkedHashMap<String,String> to = new LinkedHashMap<String,String>();
	LinkedHashMap<String,Double> idf = new LinkedHashMap<String,Double>();
	LinkedHashMap<String,String> tf_idf = new LinkedHashMap<String,String>();
	LinkedHashMap<String,Integer> maxto = new LinkedHashMap<String,Integer>();
	
	String task1documents[] = task1output.split("\n");
	double total_documents = task1documents.length;
	for(int i=0; i<task1documents.length; i++){
		String[] documents = task1documents[i].split("\t");
		String heading = documents[0];
		String terms[] = documents[1].split("\\s+");
		int maxlength = 0;
		for(String term : terms){
			int length = documents[1].split(term).length-1;
			tf.put(term, null);
			if(length>maxlength){
				maxlength = length;
				maxto.put(heading, maxlength);
			}
		}
	}
	DecimalFormat df = new DecimalFormat("#.###");
	for(String toKey:tf.keySet()){
		for(int i=0; i<task1documents.length; i++){
			String[] documents = task1documents[i].split("\t");
			String heading = documents[0];
			double length = documents[1].split(toKey).length-1;
			String oldHeadingtf = tf.get(toKey);
			String oldHeadingto = to.get(toKey);
			if(oldHeadingtf==null){
				tf.put(toKey, heading+":"+String.valueOf(df.format(length/maxto.get(heading))));
			}
			else{
				tf.put(toKey, oldHeadingtf+","+heading+":"+String.valueOf(df.format(length/maxto.get(heading))));
			}			
			if(oldHeadingto==null){
				to.put(toKey, heading+":"+String.valueOf((int)length));
			}
			else{
				to.put(toKey, oldHeadingto+","+heading+":"+String.valueOf((int)length));
			}			
		}
	}
	String[] task2document = task2output.split("\n");
	for(String document:task2document){
		String[] line = document.split("\\s+");
		int tad = line.length-1;
		idf.put(line[0], Math.log10(total_documents/tad));
	}
	for(String key : tf.keySet()){
		String[] data = tf.get(key).split(",");
		for(String values : data){
			String[] value = values.split(":");
			double tfValue = Double.parseDouble(value[1]);
			double idfValue = idf.get(key);
			double tf_idf_value = tfValue*idfValue;
			String tf_idf_round = String.valueOf(df.format(tf_idf_value));
			String old_tf_idf = tf_idf.get(key);
			if(old_tf_idf == null){
				tf_idf.put(key, tf_idf_round);
			}
			else{
				tf_idf.put(key, old_tf_idf + "\t" + tf_idf_round);
			}
		}		
	}
	
    System.out.println("\nto:\n");
	for(String key : to.keySet()){
		if(key.length()>7){
			System.out.print("\t");
			break;
		}
		
	}
	
	for(String keyMaxto : maxto.keySet()){
		System.out.print("\t" + keyMaxto);
	}
	System.out.println();
	for(String key : to.keySet()){
		String[] tos = to.get(key).split(",");
		String toValue = null;
		for(String too: tos){
			String[] toValues = too.split(":");
			if(toValue == null)
				toValue = toValues[1];
			else
				toValue = toValue + "\t" + toValues[1];
		}
		if(key.length()>7)
			System.out.println(key + "\t" + toValue);
		else
			System.out.println(key + "\t\t" + toValue);
		
	}
	
	
    System.out.println("\ntf:\n");
	for(String key : tf.keySet()){
		if(key.length()>7){
			System.out.print("\t");
			break;
		}
		
	}
	for(String keyMaxto : maxto.keySet()){
		System.out.print("\t" + keyMaxto);
	}
	System.out.println();
	for(String key : tf.keySet()){
		String[] tfs = tf.get(key).split(",");
		String tfValue = null;
		for(String tff: tfs){
			String[] tfValues = tff.split(":");
			if(tfValue == null)
				tfValue = tfValues[1];
			else
				tfValue = tfValue + "\t" + tfValues[1];
		}
		if(key.length()>7)
			System.out.println(key + "\t" + tfValue);
		else
			System.out.println(key + "\t\t" + tfValue);
		
	}
	
    System.out.println("\nidf:\n");
	for(String key : idf.keySet()){
		if(key.length()>7)
			System.out.println(key + "\t" + df.format(idf.get(key)));
		else
			System.out.println(key + "\t\t" + df.format(idf.get(key)));
	}
	
	File output_file = new File(args[1]);
	PrintWriter out = (new PrintWriter(new FileWriter(output_file)));
	System.out.println("\ntf-idf:\n");
	for(String key : tf_idf.keySet()){
		if(key.length()>7){
			System.out.print("\t");
			out.write("\t");
			break;
		}
	}
	for(String keyMaxto : maxto.keySet()){
		System.out.print("\t" + keyMaxto);
		out.write("\t" + keyMaxto);
	}
	System.out.println();
	out.write(System.getProperty("line.separator"));
	for(String key : tf_idf.keySet()){
		String output_value = tf_idf.get(key);
		if(key.length()>7){
			System.out.println(key + "\t" + output_value);
			out.write(key + "\t" + output_value);
			out.write(System.getProperty("line.separator"));
		}
		else{
			System.out.println(key + "\t\t" + output_value);
			out.write(key + "\t\t" + output_value);
			out.write(System.getProperty("line.separator"));
		}
	}
	out.close();
  }
}
