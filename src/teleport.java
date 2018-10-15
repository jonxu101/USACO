/*
ID: jonxu101
LANG: JAVA
TASK: teleport
*/
import java.io.*;
import java.util.*;
public class teleport {

	public static void main(String[] args) throws IOException{
		File file = new File("teleport.in");
		BufferedReader reader = new BufferedReader(new FileReader(file));
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("teleport.out")));
		int n=Integer.parseInt(reader.readLine());
		long totaldistance=Long.MAX_VALUE;
		int negcounter=0;
		int poscounter=0;
		Manure[] manures=new Manure[n];
		int counter=0;
		for(int i=0; i<n; i++){
			StringTokenizer st=new StringTokenizer(reader.readLine());
			long aa =Long.parseLong(st.nextToken());
			long bb =Long.parseLong(st.nextToken());
			Manure m=new Manure(aa,bb,Math.abs(aa-bb));
			manures[i]=m;
			
			if(Math.abs(aa)>=Math.abs(aa-bb))
			{
				m.setinclude(false);
			}
		}
		for(Manure y:manures){
			long distance=0;
				for(Manure m:manures){
					if(m.include==true){
					if(m.dis<=Math.abs(m.a)+Math.abs(m.b-y.b)){
						distance+=m.dis;
					}else{
						distance+=Math.abs(m.b-y.b)+Math.abs(m.a);
					}
					if(distance>totaldistance){
						break;
					}
					}
					else
						distance+=m.dis;
				}
				
			if(distance<totaldistance){
				totaldistance=distance;
			}
		}
//		System.out.println(totaldistance);
		writer.println(totaldistance);
		writer.close();
//			if(Math.abs(aa) >= Math.abs(bb-aa))
//				totaldistance=totaldistance+Math.abs(bb-aa);
//			else{
//				Manure m=new Manure(aa,bb,Math.abs(aa-bb));
//				manures[counter] = m;
//			}
//			
//			if(bb<0){
//				negcounter++;
//			}else{
//				poscounter++;
//			}
//		}
//		
//		Manure[] manures2=new Manure[counter];
//		for(int i=0; i<counter;i++)
//		{
//			manures2[i]=manures[i];
//		}
//		
//		
//		long mindistance=Long.MAX_VALUE;
//		if((negcounter&1)==0){
//			
//		}else{
//			
//		}
//		for(int i=0; i<n; i++){
//			if(a[i]<0 && b[i]<0){
//				
//			}
//		}

	}
	public static long finddistance(Manure[] manures, Manure y) {
		long distance=0;
		for(Manure m:manures){
			if(m.dis<=Math.abs(m.a)+Math.abs(m.b-y.b)){
				distance+=m.dis;
			}else{
				distance+=Math.abs(m.b-y.b)+Math.abs(m.a);
			}
//			if(distance>totaldistance){
//				break;
//			}
		}
		return distance;
	}
	static class Manure implements Comparable<Manure>{
		public long a,b,dis;
		public boolean include=true;
		public Manure(long aa, long bb, long ddis){
			a=aa;
			b=bb;
			dis=ddis;
		}
		public void setinclude(boolean i){
			include=i;
		}
		public int compareTo(Manure s){
			return (int) (b-s.b);
		}
	}

}
