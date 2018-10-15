/*
ID: jonxu101
LANG: JAVA
TASK: spin
*/
import java.io.*;
import java.util.*;
public class spin {
	public static int[][] wedges=new int[5][10];
	public static int[] numwedges= new int[5];
	public static int[] speed=new int[5];
	public static void update(){
		int wedge=0;
		for(int num:numwedges){
			for(int i=0; i<num*2; i+=2){
				wedges[wedge][i]+=speed[wedge];
				wedges[wedge][i]=wedges[wedge][i]%360;
			}
			wedge++;
		}
	}
	public static boolean test(int wedge, int i){
		int num=numwedges[wedge];
		int s=1;
		for(int x=0; x<num*2; x+=2){
			if(wedges[wedge][x]<i && i-wedges[wedge][x]<=wedges[wedge][x+1]){
				s=0;
				break;
			}else if(wedges[wedge][x]==i){
				s=0;
				break;
			}else if(wedges[wedge][x]>i && wedges[wedge][x]+wedges[wedge][x+1]-360>=i){
				s=0;
				break;
			}else{
				continue;
			}
		}
		if(s==0){
			return true;
		}else{
			return false;
		}
	}
	public static boolean passthrough(){
//		for(int i=0; i<5; i++){
//			System.out.println(Arrays.toString(wedges[i]));
//		}
		for(int i=0; i<359; i++){
			int x=0;
			for(int wedge=0; wedge<5; wedge++){
				if(!test(wedge,i)){
					x=1;
					break;
				}
			}
			if(x==0){
				return true;
			}
		}
		return false;
	}
	public static void main(String[] args) throws IOException{
		File file = new File("spin.in");
		BufferedReader reader = new BufferedReader(new FileReader(file));
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("spin.out")));
		for(int i=0; i<5; i++){
			StringTokenizer st=new StringTokenizer(reader.readLine());
			speed[i]=Integer.parseInt(st.nextToken());
			numwedges[i]=Integer.parseInt(st.nextToken());
			for(int j=0; j<numwedges[i]*2; j++){
				wedges[i][j]=Integer.parseInt(st.nextToken());
			}
		}
//		System.out.println(Arrays.toString(speed));
//		System.out.println(Arrays.toString(numwedges));
		int time;
		int ans=-1;
		if(passthrough()){
			ans=0;
//			System.out.println(ans);
			writer.println(ans);
		}else{
			for(time=1; time<360; time++){
				update();
				for(int i=0; i<5; i++){
//					System.out.println(Arrays.toString(wedges[i]));
				}
				if(passthrough()){
					ans=time;
					break;
				}
			}
			if(ans==-1){
//				System.out.println("none");
				writer.println("none");
			}else{
//				System.out.println(ans);
				writer.println(ans);
			}
		}
		writer.close();
		
	}

}
