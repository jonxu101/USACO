/*
ID: jonxu101
LANG: JAVA
TASK: gift1
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class gift1 {
	public static int find_index(String[] array, String element) {
		for(int counter=0; counter<array.length; counter++) {
			if(array[counter].equals(element)) {
				return counter;
			}
		}
		return 0;
	}
	public static void main(String[] args) {
		BufferedReader reader = null;
		BufferedWriter writer = null;
		int number;
		try {
		    File file = new File("gift1.in");
		    reader = new BufferedReader(new FileReader(file));
		    number=Integer.parseInt(reader.readLine());
		    String names[]=new String[number];
		    int values[]=new int[number];
		    String name;
		    for(int counter=0; counter<number; counter++) {
		    	name = reader.readLine();
		    	names[counter]=name;
		    	values[counter]=0;
		    }
			int total=0;
			int recipients=0;
			int amount;
			int remainder;
			for(int counter=0; counter<number; counter++) {
				String person=reader.readLine();
				String value=reader.readLine();
				StringTokenizer st = new StringTokenizer(value);
				String val = st.nextToken();
				total=Integer.parseInt(val);
				val = st.nextToken();
				recipients=Integer.parseInt(val);
				if(recipients!=0) {
					amount=(int)(total/recipients);
					remainder=total%recipients;
					int index=find_index(names, person);
					values[index]=values[index]+remainder-total;
					for(int counter2=1; counter2<=recipients; counter2++) {
						String aperson=reader.readLine(); 
						int i=find_index(names, aperson);
						values[i]=values[i]+amount;
					}
				}
			}
			writer = new BufferedWriter(new FileWriter("gift1.out"));
			for (int counter=0; counter<names.length; counter++) {
			    writer.write(names[counter] + " " +values[counter]);
			    writer.newLine();
			}
		} catch (IOException e) {
		    e.printStackTrace();
		} finally {
		    try {
		    if (reader!=null){
		        reader.close();
		    }
		    if (writer!=null){
		        writer.close();
		    }
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		}
		
	}

}
