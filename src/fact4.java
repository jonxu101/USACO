/*
ID: jonxu101
LANG: JAVA
TASK: fact4
*/
import java.io.*;
import java.util.*;
public class fact4 {
	public static int getdigit(int x, int i){
		if(i%5==0){
			x=x*i;
			if(i==25){
				System.out.println(x*i);
			}
			while(x%10==0){
				x=x/10;
			}
			x=x%10000;
		}else{
			x=(x*i)%10000;
		}
		return x;
	}
	public static void main(String[] args) throws IOException{
		File file = new File("fact4.in");
		BufferedReader reader = new BufferedReader(new FileReader(file));
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("fact4.out")));
		StringTokenizer st=new StringTokenizer(reader.readLine());
		int n=Integer.parseInt(st.nextToken());
		int x=1;
		for(int i=2; i<=n; i++){
			x=getdigit(x,i);
		}
//		System.out.println(x%10);
		writer.println(x%10);
		writer.close();
	}

}
