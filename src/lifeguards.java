/*
ID: jonxu101
LANG: JAVA
TASK: lifeguards
*/
import java.io.*;
import java.util.*;
public class lifeguards {

	public static void main(String[] args) throws IOException{
		File file = new File("lifeguards.in");
		BufferedReader reader = new BufferedReader(new FileReader(file));
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("lifeguards.out")));
		int num=Integer.parseInt(reader.readLine());
		State[] states=new State[num*2];
		for(int i=0; i<num; i++){
			StringTokenizer st=new StringTokenizer(reader.readLine());
			State start=new State(i,Integer.parseInt(st.nextToken()));
			State end=new State(i,Integer.parseInt(st.nextToken()));
			states[i*2]=start;
			states[i*2+1]=end;
		}
		Arrays.sort(states);
		TreeSet<Integer> onduty=new TreeSet<Integer>();
		int[] alone=new int[num];
		int last=0;
		int covered=0;
		for(State s:states){
			if(onduty.size()==1){
				alone[onduty.first()]+=s.time-last;
			}
			if(!onduty.isEmpty()){
				covered+=s.time-last;
			}
			if(onduty.contains(s.index)){
				onduty.remove(s.index);
			}else{
				onduty.add(s.index);
			}
			last=s.time;
		}
		int max=0;
		for(int time:alone){
			max=Math.max(max, covered-time);
		}
//		System.out.println(max);
		writer.println(max);
		writer.close();
	}
	static class State implements Comparable<State>{
		public int index, time;
		public State(int a, int b){
			index=a;
			time=b;
		}
		public int compareTo(State s){
			return time-s.time;
		}
	}

}
