/*
ID: jonxu101
LANG: JAVA
TASK: rockers
*/
import java.io.*;
import java.util.*;
public class rockers {

	public static void main(String[] args) throws IOException{
		File file = new File("rockers.in");
		BufferedReader reader = new BufferedReader(new FileReader(file));
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("rockers.out")));
		StringTokenizer st=new StringTokenizer(reader.readLine());
		int numsongs=Integer.parseInt(st.nextToken());
		int length=Integer.parseInt(st.nextToken());
		int numcds=Integer.parseInt(st.nextToken());
		int[] songs=new int[numsongs];
		st=new StringTokenizer(reader.readLine());
		for(int i=0; i<numsongs; i++){
			songs[i]=Integer.parseInt(st.nextToken());
		}
		int[][][] dp=new int[numcds][length+1][numsongs];
		for(int i=0; i<numsongs; i++){
			if(songs[i]<=length){
				dp[0][songs[i]][i]=1;
			}
		}
		for(int i=0; i<numcds; i++){
			for(int j=0; j<=length; j++){
				for(int k=0; k<numsongs-1; k++){
					for(int l=k+1; l<numsongs; l++){
						if(length>=j+songs[l]){
							dp[i][j+songs[l]][l]=Math.max(dp[i][j+songs[l]][l], dp[i][j][k]+1);
						}else{
							if(i<numcds-1){
								if(songs[l]<=length){
									dp[i+1][songs[l]][l]=Math.max(dp[i+1][songs[l]][l], dp[i][j][k]+1);
								}
							}
						}
					}
				}
			}
		}
		int max=0;
		for(int i=0; i<numcds; i++){
			for(int j=0; j<=length; j++){
				for(int k=0; k<numsongs; k++){
					max=Math.max(max,dp[i][j][k]);
				}
			}
		}
//		System.out.println(dp[0][4][0]);
//		System.out.println(dp[1][5][3]);
//		System.out.println(max);
		writer.println(max);
		writer.close();
	}

}
