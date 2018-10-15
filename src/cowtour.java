/*
ID: jonxu101
LANG: JAVA
TASK: cowtour
*/
import java.io.*;
import java.util.*;
public class cowtour {
	public static int[] getfield(int start){
		int[] included=new int[num];
		LinkedList<Integer> queue=new LinkedList<Integer>();
		included[start]=1;
		queue.add(start);
		while(!queue.isEmpty()){
			int nextnum=queue.removeFirst();
			for(int i=0; i<num; i++){
				if(connected[nextnum][i]==1){
					if(included[i]!=1){
						queue.add(i);
						included[i]=1;
					}
				}
			}
		}
		return included;
	}
	public static void getdiameter(){
//		double[][] distances=dis.clone();
		long s=System.currentTimeMillis();
		distances=new double[num][num];
		for(double[] d:distances){
			Arrays.fill(d, Double.POSITIVE_INFINITY);
		}
		for(int i=0; i<num; i++){
			for(int j=i+1; j<num; j++){
				distances[i][j]=dis[i][j];
				distances[j][i]=dis[i][j];
			}
		}
		for(int i=0; i<num; i++){
			distances[i][i]=0;
		}
		for(int k=0; k<num; k++){
			for(int i=0; i<num; i++){
				for(int j=0; j<num; j++){
//					System.out.println(Arrays.deepToString(distances));
					if(distances[i][j]>distances[i][k]+distances[k][j]){
						distances[i][j]=distances[i][k]+distances[k][j];
						distances[j][i]=distances[i][j];
					}
				}
			}
		}
		for(int i=0; i<num; i++){
			for(int j=0; j<num; j++){
				if(distances[i][j]>farthest[i] && distances[i][j]!=Double.POSITIVE_INFINITY && j!=i){
					farthest[i]=distances[i][j];
				}
			}
		}
	}
	public static int findnext(int[] a){
		for(int i=0; i<a.length; i++){
			if(a[i]==0){
				return i;
			}
		}
		return -1;
	}
	public static int[][] connected=null;
	public static int num=0;
	public static double diameter=-1;
	public static double[][] dis=null;
	public static long timespent=0;
	public static double[][] distances=null;
	public static double[] farthest=null;
	public static double[] diameters=null;
	public static void main(String[] args) {
		BufferedReader reader = null;
		BufferedWriter writer = null;
		try {
			File file = new File("cowtour.in");
			reader = new BufferedReader(new FileReader(file));
			writer = new BufferedWriter(new FileWriter("cowtour.out"));
			num=Integer.parseInt(reader.readLine());
			distances=new double[num][num];
			int[][] coordinates=new int[num][2];
			for(int i=0; i<num; i++){
				String[] thing=reader.readLine().split(" ");
				coordinates[i][0]=Integer.parseInt(thing[0]);
				coordinates[i][1]=Integer.parseInt(thing[1]);
			}
			connected=new int[num][num];
			for(int i=0; i<num; i++){
				String[] thing=reader.readLine().split("");
				int index=0;
				for(String s:thing){
					connected[i][index]=Integer.parseInt(s);
					index++;
				}
			}
			farthest=new double[num];
			diameters=new double[num];
			dis=new double[num][num];
			for(double[] d:dis){
				Arrays.fill(d, Double.POSITIVE_INFINITY);
			}
			for(int i=0; i<num-1; i++){
				for(int j=i+1; j<num; j++){
					if(connected[i][j]==1){
						dis[i][j]=(double) Math.sqrt(Math.pow((coordinates[i][0]-coordinates[j][0]),2)+Math.pow((coordinates[i][1]-coordinates[j][1]),2));
						dis[j][i]=dis[i][j];
					}
				}
			}
			getdiameter();
			int[] included=new int[num];
			int numpasture=0;
			int start=0;
			while(start!=-1){
				int[] thing=getfield(start);
				for(int i=0; i<num; i++){
					if(thing[i]==1){
						included[i]=1;
					}
				}
				numpasture++;
				start=findnext(included);
			}
			included=new int[num];
			start=0;
			int[] starts=new int[numpasture];
			int index=0;
			while(start!=-1){
				double maxdiameter=0;
				int[] thing=getfield(start);
				starts[index]=start;
				index++;
				for(int i=0; i<num; i++){
					if(thing[i]==1){
						if(farthest[i]>maxdiameter){
							maxdiameter=farthest[i];
						}
					}
				}
				for(int i=0; i<num; i++){
					if(thing[i]==1){
						diameters[i]=maxdiameter;
					}
				}
				for(int i=0; i<num; i++){
					if(thing[i]==1){
						included[i]=1;
					}
				}
				start=findnext(included);
			}
//			System.out.println(Arrays.toString(starts));
//			System.out.println(Arrays.toString(farthest));
//			System.out.println(Arrays.deepToString(distances));
			for(int i=0; i<num; i++){
				int[] things=getfield(i);
				for(int j=i+1; j<num; j++){
					if(things[j]==1){
						continue;
					}
					double a=Math.max(diameters[i], diameters[j]);
					double x=(double) Math.max(a,(farthest[i]+farthest[j]+Math.sqrt((coordinates[i][0]-coordinates[j][0])*(coordinates[i][0]-coordinates[j][0])+(coordinates[i][1]-coordinates[j][1])*(coordinates[i][1]-coordinates[j][1]))));
					if(x<diameter || diameter==-1){
						diameter=x;
					}
				}
			}
//			System.out.printf("%.6f%n", diameter);
			writer.write(String.format("%.6f%n", diameter));
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
