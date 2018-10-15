/*
ID: jonxu101
LANG: JAVA
TASK: nuggets
*/
import java.io.*;
import java.util.*;
public class nuggets {

    public static void main(String[] args) throws IOException{
        File file = new File("nuggets.in");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("nuggets.out")));
        StringTokenizer st=new StringTokenizer(reader.readLine());
        int num=Integer.parseInt(st.nextToken());
        int[] values=new int[num];
        int product=1;
        for(int i=0; i<num; i++) {
            values[i]=Integer.parseInt(reader.readLine());
            product=product*values[i];
        }
        LinkedList<Integer> queue=new LinkedList<Integer>();
        boolean[] dp;
        queue.add(0);
        dp=new boolean[65537];
        while(!queue.isEmpty()) {
            int a=queue.removeFirst();
            if(dp[a]==false) {
                dp[a]=true;
                for(int i=0; i<num; i++) {
                    int x=a+values[i];
                    if(x<=65536 && dp[x]==false) {
                        queue.add(x);
                    }
                }
            }
        }
        boolean gcd=false;
        for(int i=2; i<=256; i++){
        	boolean x=true;
        	for(int j=0; j<num; j++){
        		if(values[j]%i!=0){
        			x=false;
        		}
        	}
        	if(x==true){
        		gcd=true;
        		break;
        	}
        }
        int ans=0;
        for(int i=0; i<65537; i++) {
            if(dp[i]==false) {
                ans=i;
            }
        }
//        System.out.println(ans);
        if(gcd){
//        	System.out.println(0);
        	writer.println("0");
        }else{
//        	System.out.println(ans);
        	writer.println(ans);
        }
        writer.close();
       
    }

}

