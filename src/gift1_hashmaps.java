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
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class gift1_hashmaps {

	public static void main(String[] args) {
		BufferedReader reader = null;
		Map<String, Integer> map = new HashMap<String, Integer>();
		int number;
		try {
		    File file = new File("gift1.in");
		    reader = new BufferedReader(new FileReader(file));
		    number=Integer.parseInt(reader.readLine());
		    String line;
		    for(int counter=1; counter<=number; counter++) {
		    	line = reader.readLine();
		    	map.put(line, 0);
		    }
			int total=0;
			int recipients=0;
			int amount;
			int remainder;
			for(int counter=1; counter<=number; counter++) {
				String person=reader.readLine();
				String values=reader.readLine();
				StringTokenizer st = new StringTokenizer(values);
				String val = st.nextToken();
				total=Integer.parseInt(val);
				val = st.nextToken();
				recipients=Integer.parseInt(val);
				if(recipients!=0) {
					amount=(int)(total/recipients);
					remainder=total%recipients;
					map.put(person, map.get(person)+remainder-total);
					for(int counter2=1; counter2<=recipients; counter2++) {
						String aperson=reader.readLine();
						map.put(aperson,map.get(aperson)+amount);
					}
				}
			}

		} catch (IOException e) {
		    e.printStackTrace();
		} finally {
		    try {
		        reader.close();
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		}
		BufferedWriter writer = null;
		try {
		writer = new BufferedWriter(new FileWriter("gift1.out"));
		for (String key : map.keySet()) {
		    writer.write(key + " " + map.get(key));
		    writer.newLine();
		}
		} catch(IOException e) {
			
		} finally {
			try {
	        writer.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    }
		
	}

}
