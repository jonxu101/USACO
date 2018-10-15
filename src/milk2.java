/*
ID: jonxu101
LANG: JAVA
TASK: milk2
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class milk2 {

	public static void main(String[] args) {
		BufferedReader reader = null;
		BufferedWriter writer=null;
		try {
		    File file = new File("milk2.in");
		    reader = new BufferedReader(new FileReader(file));
		    int n=Integer.parseInt(reader.readLine());
		    int[][] Array=new int[n][2];
		    for(int a=0; a<n; a++){
		    	String[] integersInString = reader.readLine().split(" ");
		    	for (int i = 0; i < integersInString.length; i++) {
		    	    Array[a][i] = Integer.parseInt(integersInString[i]);
		    	}
		    }
		    int maxt=0;
		    int idle=0;
		    int maxnum=0;
		    java.util.Arrays.sort(Array, java.util.Comparator.comparingInt(a -> a[0]));
		    /*System.out.println("sorted data:");
		    for(int m=0;m<n;m++){
		       for(int j=0; j<2;j++){
		    	   System.out.print(Array[m][j]+" ");
		       }
		       System.out.println();
		    } */
		    	
		    if(n>1){
			    for(int i=1; i<n; i++) {
			    	int minnum=Array[i][0];
			    	for(int a=0; a<i;a++){
			    		if(Array[a][1]>maxnum){
			    			maxnum=Array[a][1];
			    		}
			    	}
			    	for(int a=i+1; a<n;a++){
			    		if(Array[a][0]<minnum){
			    			minnum=Array[a][0];
			    		}
			    	}
			    	if(maxnum<Array[i][0] && Array[i][0]<=minnum){
			    		if(Array[i][0]-maxnum>idle){
			    			idle=Array[i][0]-maxnum;
			    		}
			    	}
			    }			    
			    int t;
			    maxt=Array[0][1]-Array[0][0];
			    for(int a=0; a<n; a++){
			    	maxnum=Array[a][1];
			    	int x=a;
			    	if(x<n-1){
				    	while(maxnum>=Array[x+1][0]){
				    		if(Array[x+1][1]>maxnum){
				    			maxnum=Array[x+1][1];
				    		}
				    		x+=1;
				    		if(x==n-1){
				    			break;
				    		}
				    	}
				    	t=maxnum-Array[a][0];
			    	} else{
			    		t=Array[n-1][1]-Array[n-1][0];
			    	}
			    	System.out.println(x+","+a+","+t+","+maxt);
			    	if(t>maxt)
			    		maxt=t;
			    }			    
		    } else {
		    	maxt=Array[0][1]-Array[0][0];
		    	idle=0;
		    }
		    
			writer = new BufferedWriter(new FileWriter("milk2.out"));
			writer.write(maxt+" "+idle+"\n");
		} catch (IOException e) {
		    e.printStackTrace();
		} finally {
		    try {
		    	if(reader!=null)
		    		reader.close();
		    	if (writer!=null)
		    		writer.close();
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		}

	}

}
