/*
ID: jonxu101
LANG: JAVA
TASK: ratios
*/
import java.io.*;
import java.util.*;
public class ratios {

	public static void main(String[] args) throws IOException{
		File file = new File("ratios.in");
		BufferedReader reader = new BufferedReader(new FileReader(file));
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("ratios.out")));
		StringTokenizer st=new StringTokenizer(reader.readLine());
		int[] want=new int[3];
		for(int i=0; i<3; i++){
			want[i]=Integer.parseInt(st.nextToken());
		}
		int[][] data=new int[3][3];
		for(int i=0; i<3; i++){
			st=new StringTokenizer(reader.readLine());
			for(int j=0; j<3; j++){
				data[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		int[] min=new int[3];
		Arrays.fill(min, Integer.MAX_VALUE);
		int n=0;
		for(int i=0; i<=101; i++){
			for(int j=0; j<=101; j++){
				for(int k=0; k<=101; k++){
					int a=0;
					int b=0;
					int c=0;
					for(int x=0; x<3; x++){
						if(x==0){
							a+=data[x][0]*i;
							b+=data[x][1]*i;
							c+=data[x][2]*i;
						}else if(x==1){
							a+=data[x][0]*j;
							b+=data[x][1]*j;
							c+=data[x][2]*j;
						}else{
							a+=data[x][0]*k;
							b+=data[x][1]*k;
							c+=data[x][2]*k;
						}
					}
//					System.out.println(a+","+b+","+c);
					if(a*want[1]==want[0]*b && b*want[2]==want[1]*c && !(i==0 && j==0 && k==0) && a%want[0]==0){
						if(min[0]==Integer.MAX_VALUE || min[0]+min[1]+min[2]>i+j+k){
							min[0]=i;
							min[1]=j;
							min[2]=k;
//							System.out.println(a+","+b+","+c);
							n=a/want[0];
						}
					}
				}
			}
		}
		if(n==0){
			writer.println("NONE");
		}else{
			for(int i=0; i<3; i++){
				writer.print(min[i]+" ");
//				System.out.print(min[i]+" ");
			}
//			System.out.println(n);
			writer.println(n);
		}
		writer.close();
	}

}
