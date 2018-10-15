/*
ID: jonxu101
LANG: JAVA
TASK: agrinet
*/
import java.io.*;
import java.util.*;
public class agrinet {

	public static void main(String[] args) {
		BufferedReader reader = null;
		BufferedWriter writer = null;
		try {
			File file = new File("agrinet.in");
			reader = new BufferedReader(new FileReader(file));
			writer = new BufferedWriter(new FileWriter("agrinet.out"));
			int num=Integer.parseInt(reader.readLine());
			int[][] weight=new int[num][num];
			String line=reader.readLine();
			int index=0;
			int x=0;
			while(line!=null){
				String[] l=line.split(" ");
				for(String s:l){
					weight[index][x]=Integer.parseInt(s);
					x++;
				}
				if(x==num){
					x=0;
					index++;
				}
				line=reader.readLine();
			}
			boolean[] intree=new boolean[num];
			double[] distances=new double[num];
			Arrays.fill(distances, Double.POSITIVE_INFINITY);
			int[] source=new int[num];
			int treesize=1;
			int treecost=0;
			intree[0]=true;
			for(int i=1; i<num; i++){
				distances[i]=weight[0][i];
				source[i]=0;
			}
			while(treesize<num){
				int node=-1;
				int mindistance=-1;
				for(int i=0; i<num; i++){
					if((mindistance==-1 || weight[i][source[i]]<mindistance) && !intree[i]){
						node=i;
						mindistance=weight[i][source[i]];
					}
				}
				intree[node]=true;
				treecost+=distances[node];
				treesize++;
				for(int i=0; i<num; i++){
					if(distances[i]>weight[i][node]){
						distances[i]=weight[i][node];
						source[i]=node;
					}
				}
			}
//			System.out.println(treecost);
			writer.write(treecost+"\n");
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
