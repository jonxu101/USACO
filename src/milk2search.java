import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class milk2search {

	public static void main(String[] args) {
		BufferedReader reader = null;
		try {
		    File file = new File("C://Jonathan/Java Programs/ComputingOlympiad/src/milk2.in");
		    reader = new BufferedReader(new FileReader(file));
		    int n=Integer.parseInt(reader.readLine());
		    int[][] Array=new int[n][2];
		    for(int a=0; a<n; a++){
		    	String[] integersInString = reader.readLine().split(" ");
		    	for (int i = 0; i < integersInString.length; i++) {
		    	    Array[a][i] = Integer.parseInt(integersInString[i]);
		    	}
		    }
		    for(int i=1; i<1000; i++){
		    	for(int j=0; j<i; j++){
		    		if(Array[i][1]-Array[j][0]==912)
		    			System.out.println(i+","+j);
		    	}
		    }
		} catch (IOException e) {
		    e.printStackTrace();
		} finally {
		    try {
		    	if(reader!=null)
		    		reader.close();
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		}
	}

}
