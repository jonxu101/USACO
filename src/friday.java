/*
ID: jonxu101
LANG: JAVA
TASK: friday
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class friday {

	public static void main(String[] args) {
		BufferedReader reader = null;
		BufferedWriter writer=null;
		try {
		    File file = new File("friday.in");
		    reader = new BufferedReader(new FileReader(file));
		    int n=Integer.parseInt(reader.readLine());
		    int counter[]=new int[7];
		    int weekday=2;
		    int months[]={31,28,31,30,31,30,31,31,30,31,30,31};
		    for(int y=0; y<n; y++) {
		    	int year=y+1900;
		    	if(year%4!=0 || year%100==0) {
		    		if(year%400==0) {
		    			months[1]=29;
		    		}else {
		    			months[1]=28;
		    		}
		    	} else {
		    		months[1]=29;
		    	}
		    	for(int month: months) {
		    		for(int d=1; d<=month; d++) {
		    			if(weekday>6) {
		    				weekday=0;
		    			}
		    			if(d==13) {
		    				counter[weekday]+=1;
		    			}
		    			weekday+=1;
		    		}
		    	}
		    }
			writer = new BufferedWriter(new FileWriter("friday.out"));
			for(int num=0; num<7; num++) {
				if(num!=6) {
					writer.write(counter[num]+" ");
				} else {
					writer.write(counter[num]+"\n");
				}
			}
		} catch (IOException e) {
		    e.printStackTrace();
		} finally {
		    try {
		        reader.close();
		        writer.close();
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		}

	}

}
