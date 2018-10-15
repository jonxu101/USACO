/*
ID: jonxu101
LANG: JAVA
TASK: milk
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
public class milk {
	
	public static void main(String[] args) {
		BufferedReader reader = null;
		BufferedWriter writer = null;
		try {
			File file = new File("milk.in");
			reader = new BufferedReader(new FileReader(file));
			writer = new BufferedWriter(new FileWriter("milk.out"));
			String[] integersInString = reader.readLine().split(" ");
			int unitsneeded=Integer.parseInt(integersInString[0]);
			int totalfarmers=Integer.parseInt(integersInString[1]);
			int[][] farmerdata=new int[totalfarmers][2];
			String line = reader.readLine();
			int index=0;
			while (line != null) {
		    	String[] intInString = line.split(" ");
		    	int[] farmer=new int[2];
		    	for (int i = 0; i < 2; i++) {
		    	    farmer[i]=Integer.parseInt(intInString[i]);
		    	}
		    	farmerdata[index]=farmer;
		    	line=reader.readLine();
		    	index+=1;
		    }
			java.util.Arrays.sort(farmerdata, new java.util.Comparator<int[]>() {
	             public int compare(int[] a, int[] b) {
	                  return Integer.compare(a[0],b[0]);
	             }
	        });
//			System.out.println(Arrays.deepToString(farmerdata));
			int cost=0;
			for(int[] set:farmerdata){
				int costperunit=set[0];
				int units=set[1];
				if(units<unitsneeded){
					unitsneeded-=units;
					cost=cost+units*costperunit;
				}else{
					cost=cost+unitsneeded*costperunit;
					break;
				}
			}
			writer.write(cost+"\n");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null)
					reader.close();
				if (writer != null)
					writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
