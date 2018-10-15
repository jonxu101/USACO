/*
ID: jonxu101
LANG: JAVA
TASK: wormhole
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class wormhole {
	public static int choose(int num){
		return (num*(num-1))/2;
	}
	public static int factorial(int num){
		int total=1;
		for(int n=2; n<=num; n++){
			total=total*n;
		}
		return total;
	}
	public static int numpair(int num){
		int total=1;
		for(int n=num; n>0; n-=2){
			total=total*choose(n);
		}
		return total/factorial(num/2);
	}
	public static long power(int n, int e) {
		{
			if (e == 0)
				return 1;
			if (e == 1)
				return n;
			if (e % 2 == 0)
				return power(n * n, e / 2); // even a=(a^2)^b/2
			else
				return n * power(n * n, e / 2); // odd a=a*(a^2)^b/2

		}
	}
	public static HashMap<Integer, Integer> connections(int[][] pairing){
		HashMap<Integer, Integer> connections=new HashMap<Integer, Integer>();
		for(int[] pair:pairing){
			connections.put(pair[0], pair[1]);
			connections.put(pair[1], pair[0]);
		}
		return connections;
	}
	public static int nextwormhole(int point, int[][] coordinates){
		int x=coordinates[point-1][0];
		int y=coordinates[point-1][1];
		int nextpoint=0;
		int smallx=-1;
		int index=1;
		for(int[] coordinate:coordinates){
			if(index==point){
				index+=1;
				continue;
			}
			if(coordinate[1]!=y){
				index+=1;
				continue;
			}else{
				if(coordinate[0]<=x){
					index+=1;
					continue;
				}else{
					if(smallx==-1){
						smallx=coordinate[0];
						nextpoint=index;
						index+=1;
						continue;
					}else if(coordinate[0]<smallx){
						smallx=coordinate[0];
						nextpoint=index;
						index+=1;
						continue;
					}
				}
			}
			index+=1;
		}
		return nextpoint;
	}
	public static boolean infiniteloopcheck(int[][] pairing, int[][] coordinates){
		HashMap<Integer, Integer> wormholeconnections=connections(pairing);
//		for (Integer name: wormholeconnections.keySet()){
//
//            String key =name.toString();
//            String value = wormholeconnections.get(name).toString();  
//            System.out.print(key + " " + value+" | ");  
//
//		}
		boolean done = false;
		for(int point=1; point<=coordinates.length; point++){
			int p=point;
			while(done!=true){
				p=nextwormhole(p, coordinates);
//				System.out.println(p);
				if(p==0){
					break;
				}
				p=wormholeconnections.get(p);
//				System.out.println(p);
				if(p==point){
					done=true;
					return done;
				}
			}
//			p=point;
//			while(done!=true){
//				p=wormholeconnections.get(p);
//				if(p==point){
//					done=true;
//					return done;
//				}
//				p=nextwormhole(p, coordinates);
//				if(p==0){
//					break;
//				}
//			}
		}
		return done;
	}
	public static int[][][] pairings(ArrayList<Integer> remaining, int[][][] pairings, int[] indexes){
		ArrayList<Integer> dummyremaining=new ArrayList<Integer>(remaining);
		if(remaining.size()==0){
			return pairings;
		}
		int index=indexes[remaining.size()/2-1];
//		System.out.println(remaining.size());
//		System.out.println("dummy array"+Arrays.toString(dummyremaining.toArray()));
		for(int i=1; i<dummyremaining.size(); i++){
			int[] addpair=new int[2];
//			System.out.println(i);
			addpair[0]=dummyremaining.get(0);
			addpair[1]=dummyremaining.get(i);
//			System.out.println(remaining.size()/2-1);
			int maxindex=index+numpair(remaining.size()-2);
			while(index<maxindex){
				pairings[index][remaining.size()/2-1]=addpair;
				index+=1;
			}
			indexes[remaining.size()/2-1]=index;
			dummyremaining.remove(i);
			dummyremaining.remove(0);
//			System.out.println("remaining"+Arrays.toString(remaining.toArray()));
			pairings(dummyremaining,pairings,indexes);
			dummyremaining=(ArrayList<Integer>) remaining.clone();
		}
		return pairings;
		
	}
	public static void main(String[] args) {
		BufferedReader reader = null;
		BufferedWriter writer = null;
		try {
			File file = new File("wormhole.in");
			reader = new BufferedReader(new FileReader(file));
			writer = new BufferedWriter(new FileWriter("wormhole.out"));
			int num=Integer.parseInt(reader.readLine());
			int[][] coordinates=new int[num][2];
			ArrayList<Integer> possint=new ArrayList<Integer>();
			int[][][] pairings=new int[numpair(num)][num/2][2];
			for(int i=1; i<=num; i++){
				possint.add(i);
			}
			int[] indexes=new int[num/2+2];
			pairings=pairings(possint, pairings,indexes);
			for(int i=0; i<num; i++){
				String line=reader.readLine();
				String[] intinline=line.split(" ");
				coordinates[i][0]=Integer.parseInt(intinline[0]);
				coordinates[i][1]=Integer.parseInt(intinline[1]);
			}
//			System.out.println(Arrays.deepToString(coordinates));
//			System.out.println(nextwormhole(3,coordinates));
			int answer=0;
			for(int[][] pairing: pairings){
				if(infiniteloopcheck(pairing, coordinates)==true){
					answer+=1;
				}
			}
//			System.out.println(answer);
			writer.write(answer+"\n");
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
