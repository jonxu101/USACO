/*
ID: jonxu101
LANG: JAVA
TASK: mootube
*/
import java.io.*;
import java.util.*;
public class mootube {
	public static void getrelevances(int start){
		distances=new double[numvids][numvids];
		for(double[] d:distances){
			Arrays.fill(d, Double.POSITIVE_INFINITY);
		}
		for(int i=0; i<numvids; i++){
			for(int j=i+1; j<numvids; j++){
				distances[i][j]=relevances[i][j];
				distances[j][i]=relevances[i][j];
			}
		}
		for(int i=0; i<numvids; i++){
			distances[i][i]=0;
		}
		for(int k=0; k<numvids; k++){
			for(int i=0; i<numvids; i++){
				for(int j=0; j<numvids; j++){
//					System.out.println(Arrays.deepToString(distances));
					if(distances[i][j]>distances[i][k]+distances[k][j]){
						if(relevances[i][k]<relevances[i][j]){
							relevances[i][j]=relevances[i][k];
							relevances[j][i]=relevances[i][j];
						}
						if(relevances[j][k]<relevances[i][j]){
							relevances[i][j]=relevances[j][k];
							relevances[j][i]=relevances[i][j];
						}
						distances[i][j]=distances[i][k]+distances[k][j];
						distances[j][i]=distances[i][j];
					}
				}
			}
		}
	}
	public static int numvids=0;
	public static int numquestions=0;
	public static double[][] relevances=null;
	public static double[][] distances=null;
	public static int[] included=null;
	public static void main(String[] args) {
		BufferedReader reader = null;
		BufferedWriter writer = null;
		try {
			File file = new File("mootube.in");
			reader = new BufferedReader(new FileReader(file));
			writer = new BufferedWriter(new FileWriter("mootube.out"));
			String[] s=reader.readLine().split(" ");
			numvids=Integer.parseInt(s[0]);
			numquestions=Integer.parseInt(s[1]);
			relevances=new double[numvids][numvids];
			for(double[] a: relevances){
				Arrays.fill(a, Double.POSITIVE_INFINITY);
			}
			for(int k=0; k<numvids-1; k++){
				StringTokenizer st=new StringTokenizer(reader.readLine());
				int i=Integer.parseInt(st.nextToken())-1;
				int j=Integer.parseInt(st.nextToken())-1;
				relevances[i][j]=Double.parseDouble(st.nextToken());
				relevances[j][i]=relevances[i][j];
			}
			included=new int[numvids];
			getrelevances(0);
//			System.out.println(Arrays.deepToString(relevances));
			for(int i=0; i<numquestions; i++){
				StringTokenizer st=new StringTokenizer(reader.readLine());
				int k=Integer.parseInt(st.nextToken());
				int v=Integer.parseInt(st.nextToken())-1;
				int counter=0;
				for(int j=0; j<numvids; j++){
//					System.out.println(numvids);
//					System.out.println(v+" "+j);
					if(relevances[v][j]>=k && relevances[v][j]!=Double.POSITIVE_INFINITY){
						counter++;
					}
				}
				System.out.println(counter);
				writer.write(counter+"\n");
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
