/*
ID: jonxu101
LANG: JAVA
TASK: ride
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ride {

	public static void main(String[] args) {
		BufferedReader reader = null;
		ArrayList<String> list = new ArrayList<String>();
		try {
		    File file = new File("ride.in");
		    reader = new BufferedReader(new FileReader(file));

		    String line;
		    while ((line = reader.readLine()) != null) {
		    	    list.add(line);
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
		ArrayList<Integer> values = new ArrayList<Integer>();
		for(String str: list) {
			int total=1;
			for(int i=0; i<str.length(); i++) {
				int letter=str.charAt(i)-64;
				total=total*letter;
			}
			total=total%47;
			values.add(total);
		}
		if(values.get(0)==values.get(1)) {
			BufferedWriter writer = null;
			try {
			writer = new BufferedWriter(new FileWriter("ride.out"));
			writer.write("GO\n");
			} catch(IOException e) {
				
			} finally {
				try {
		        writer.close();
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		    }
		}
		else{
			BufferedWriter writer = null;
			try {
			writer = new BufferedWriter(new FileWriter("ride.out"));
			writer.write("STAY\n");
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

}
