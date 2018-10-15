/*
ID: jonxu101
LANG: JAVA
TASK: numtri
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
public class numtri {

	public static void main(String[] args) {
		BufferedReader reader = null;
		BufferedWriter writer = null;
		try {
			File file = new File("numtri.in");
			reader = new BufferedReader(new FileReader(file));
			writer = new BufferedWriter(new FileWriter("numtri.out"));
			int length=Integer.parseInt(reader.readLine());
			int[][] values=new int[length][];
			for(int i=0; i<length; i++){
				String[] nums=reader.readLine().split(" ");
				int[] intnums=new int[i+1];
				int index=0;
				for(String num: nums){
					int a=Integer.parseInt(num);
					intnums[index]=a;
					index+=1;
				}
				values[i]=intnums;
			}
//			System.out.println(Arrays.deepToString(values));
			for(int layer=length-1; layer>=1; layer--){
				for(int index=0; index<layer; index++){
						values[layer-1][index]=values[layer-1][index]+Math.max(values[layer][index+1],values[layer][index]);
				}
			}
			writer.write(values[0][0]+"\n");
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
