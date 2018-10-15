/*
ID: jonxu101
LANG: JAVA
TASK: camelot
*/
import java.io.*;
import java.util.*;
public class camelot {
	public static int min=Integer.MAX_VALUE;
	public static int[][] moves=new int[30][26];
	public static ArrayList<Integer> xknights=new ArrayList<Integer>();
	public static ArrayList<Integer> yknights=new ArrayList<Integer>();
	public static int x=0;
	public static int y=0;
	public static void findDis(int[] king, int dis){
		for(int i=0; i<xknights.size(); i++){
			int p=moves[Math.abs(xknights.get(i)-king[0])][Math.abs(yknights.get(i)-king[1])];
			dis+=p;
			for(int a=0; a<x; a++){
				for(int j=0; j<y; j++){
					int holder=dis;
					for(int k=0; k<xknights.size(); k++){
						if(k==i){
							holder+=moves[Math.abs(king[0]-a)][Math.abs(king[1]-j)];
						}else{
							holder+=moves[Math.abs(xknights.get(k)-a)][Math.abs(yknights.get(k)-j)];
						}
					}
					min=Math.min(holder, min);
				}
			}
			dis-=p;
		}
	}
	public static int dis(int[] a, int[] knight){
		return moves[Math.abs(knight[0]-a[0])][Math.abs(knight[1]-a[1])];
	}
	public static void solve(int[] king){
		int[] coord=new int[2];
		for(int i=-3; i<=3; i++){
			for(int j=-3; j<=3; j++){
				if(Math.abs(i)+Math.abs(j)>3){
					continue;
				}else{
					if(king[0]+i>=0 && king[0]+i<x && king[1]+j>=0 && king[1]+j<y){
						coord[0]=king[0]+i;
						coord[1]=king[1]+j;
						findDis(coord,Math.abs(i)+Math.abs(j));
					}
				}
			}
		}
	}
	public static void main(String[] args) throws IOException{
		File file = new File("C://Jonathan/Java Programs/ComputingOlympiad/src/camelot.in");
		BufferedReader reader = new BufferedReader(new FileReader(file));
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("camelot.out")));
		StringTokenizer st=new StringTokenizer(reader.readLine());
		x=Integer.parseInt(st.nextToken());
		y=Integer.parseInt(st.nextToken());
		st=new StringTokenizer(reader.readLine());
		int[] king=new int[2];
		king[1]=(int)st.nextToken().charAt(0)-65;
		king[0]=Integer.parseInt(st.nextToken())-1;
		String s=reader.readLine();
		while(s!=null){
			st=new StringTokenizer(s);
			while(st.hasMoreTokens()){
				yknights.add((int)st.nextToken().charAt(0)-65);
				xknights.add(Integer.parseInt(st.nextToken())-1);
			}
			s=reader.readLine();
		}
		for(int i=0; i<30; i++){
			Arrays.fill(moves[i], Integer.MAX_VALUE);
		}
//		System.out.println(Arrays.toString(xknights.toArray()));
//		System.out.println(Arrays.toString(yknights.toArray()));
		moves[0][0]=0;
		LinkedList<Integer> xcoord=new LinkedList<Integer>();
		LinkedList<Integer> ycoord=new LinkedList<Integer>();
		xcoord.add(0);
		ycoord.add(0);
		while(!xcoord.isEmpty()){
			int xx=xcoord.removeFirst();
			int yy=ycoord.removeFirst();
			if(xx+1<30 && yy+2<26){
				if(moves[xx][yy]+1<moves[xx+1][yy+2]){
					moves[xx+1][yy+2]=moves[xx][yy]+1;
					xcoord.add(xx+1);
					ycoord.add(yy+2);
				}
			}
			if(xx-1>=0 && yy+2<26){
				if(moves[xx][yy]+1<moves[xx-1][yy+2]){
					moves[xx-1][yy+2]=moves[xx][yy]+1;
					xcoord.add(xx-1);
					ycoord.add(yy+2);
				}
			}
			if(xx-1>=0 && yy-2>=0){
				if(moves[xx][yy]+1<moves[xx-1][yy-2]){
					moves[xx-1][yy-2]=moves[xx][yy]+1;
					xcoord.add(xx-1);
					ycoord.add(yy-2);
				}
			}
			if(xx+1<30 && yy-2>=0){
				if(moves[xx][yy]+1<moves[xx+1][yy-2]){
					moves[xx+1][yy-2]=moves[xx][yy]+1;
					xcoord.add(xx+1);
					ycoord.add(yy-2);
				}
			}
			if(xx+2<30 && yy+1<26){
				if(moves[xx][yy]+1<moves[xx+2][yy+1]){
					moves[xx+2][yy+1]=moves[xx][yy]+1;
					xcoord.add(xx+2);
					ycoord.add(yy+1);
				}
			}
			if(xx-2>=0 && yy+1<26){
				if(moves[xx][yy]+1<moves[xx-2][yy+1]){
					moves[xx-2][yy+1]=moves[xx][yy]+1;
					xcoord.add(xx-2);
					ycoord.add(yy+1);
				}
			}
			if(xx-2>=0 && yy-1>=0){
				if(moves[xx][yy]+1<moves[xx-2][yy-1]){
					moves[xx-2][yy-1]=moves[xx][yy]+1;
					xcoord.add(xx-2);
					ycoord.add(yy-1);
				}
			}
			if(xx+2<30 && yy-1>=0){
				if(moves[xx][yy]+1<moves[xx+2][yy-1]){
					moves[xx+2][yy-1]=moves[xx][yy]+1;
					xcoord.add(xx+2);
					ycoord.add(yy-1);
				}
			}
		}
//		for(int i=0; i<30; i++){
//			System.out.println(Arrays.toString(moves[i]));
//		}
		for(int i=0; i<x; i++){
			for(int j=0; j<y; j++){
				int dis=0;
				for(int k=0; k<xknights.size(); k++){
					dis+=moves[Math.abs(xknights.get(k)-i)][Math.abs(yknights.get(k)-j)];
				}
				dis+=Math.abs(king[0]-i)+Math.abs(king[1]-j);
				min=Math.min(dis, min);
			}
		}
		System.out.println(min);
		solve(king);
		System.out.println(min);
		writer.println(min);
		writer.close();
	}

}
