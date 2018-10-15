/*
ID: jonxu101
LANG: JAVA
TASK: prefix
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
public class prefix {
	public static boolean primitivecheck(int start, int end, char[] string, HashMap<String, Boolean> primitives){
		StringBuilder sb=new StringBuilder();
		for(int i=start; i<=end; i++){
			sb.append(string[i]);
		}
//			System.out.println(sb.toString());
		if(primitives.containsKey(sb.toString())){
			return true;
		}
		return false;
	}
	public static void main(String[] args) {
		BufferedReader reader = null;
		BufferedWriter writer = null;
		try {
			File file = new File("prefix.in");
			reader = new BufferedReader(new FileReader(file));
			writer = new BufferedWriter(new FileWriter("prefix.out"));
			String currentline=reader.readLine();
			HashMap<String, Boolean> primitives=new HashMap<String, Boolean>();
			while(currentline.charAt(0)!='.'){
				String[] primitive=currentline.split(" ");
				for(String s:primitive){
					primitives.put(s, true);
				}
				currentline=reader.readLine();
			}
			StringBuilder sb=new StringBuilder();
			currentline=reader.readLine();
			while(currentline!=null){
				sb.append(currentline);
				currentline=reader.readLine();
			}
			char[] string=sb.toString().toCharArray();
			int length=string.length;
			int[] abletoreach=new int[length];
			abletoreach[0]=1;
			for(int start=0; start<length; start++){
				if(start>0){
					if(abletoreach[start-1]==0){
						continue;
					}
				}
				for(int end=start; end<start+10 && end<length; end++){
					if(primitivecheck(start, end, string,primitives)){
						abletoreach[end]=1;
					}
				}
//				System.out.println(Arrays.toString(abletoreach));
			}//
			int x=0;
			for(int end=0; end<10 && end<length; end++){
				if(primitivecheck(0, end, string,primitives)){
					x=1;
					break;
				}
			}
			if(x==0){
				writer.write("0"+"\n");
			}else{
				for(int i=length-1; i>=0; i--){
					if(abletoreach[i]==1){
//						System.out.println(i+1);
						writer.write(i+1+"\n");
						break;
					}
				}
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
