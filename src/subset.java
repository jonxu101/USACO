/*
ID: jonxu101
LANG: JAVA
TASK: subset
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
import java.util.HashMap;
public class subset {
	public static void main(String[] args) {
		BufferedReader reader = null;
		BufferedWriter writer = null;
		try {
			File file = new File("subset.in");	
			reader = new BufferedReader(new FileReader(file));
			writer = new BufferedWriter(new FileWriter("subset.out"));
			int num=Integer.parseInt(reader.readLine());
			if(num%4==2 || num%4==1){
				writer.write(0+"\n");
			}else{
				long sum=(int)num*(num+1)/2;
				long hsum=(int)sum/2;
				long[] answer=new long[10000];
				answer[0]=1;
				for(int choose=1; choose<=num; choose++){
					for(int addto=choose*(choose+1)/2; addto>=0; addto--){
						answer[addto+choose]+=answer[addto];
					}
				}
				writer.write(answer[(int)hsum]/2+"\n");
//				System.out.println(answer[(int)hsum]/2);
			}
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
