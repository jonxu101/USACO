/*
ID: jonxu101
LANG: JAVA
TASK: reststops
*/
import java.io.*;
import java.util.*;
public class reststops {

	public static void main(String[] args) throws IOException{
		File file = new File("reststops.in");
		BufferedReader reader = new BufferedReader(new FileReader(file));
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("reststops.out")));
		StringTokenizer st=new StringTokenizer(reader.readLine());
		long length=Long.parseLong(st.nextToken());
		int numstops=Integer.parseInt(st.nextToken());
		long farmerspd=Long.parseLong(st.nextToken());
		long cowspd=Long.parseLong(st.nextToken());
		long galongime=farmerspd-cowspd;
		long[][] stops=new long[numstops][2];
		for(int i=0; i<numstops; i++){
			st=new StringTokenizer(reader.readLine());
			stops[i][0]=Integer.parseInt(st.nextToken());
			stops[i][1]=Integer.parseInt(st.nextToken());
		}
		Arrays.sort(stops, new Comparator<long[]>() {
            public int compare(long[] a, long[] b) {
            	return (int) (b[1]-a[1]);
            }
		});
		long traveled=0;
		long tastiness=0;
		for(long[] stop:stops){
			if(stop[0]>traveled || traveled==0){
				tastiness+=stop[1]*(stop[0]-traveled)*galongime;
				traveled=stop[0];
			}else{
				continue;
			}
		}
//		System.out.println(tastiness);
		writer.println(tastiness);
		writer.close();
	}

}
