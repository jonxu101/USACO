/*
ID: jonxu101
LANG: JAVA
TASK: castle
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
public class castle {
	public static int maxarea=0;
	public static int amax=0;
	public static int[] optimizedroom=new int[3];
	public static ArrayList<int[]> unassignedrooms=new ArrayList<int[]>();
	public static int numrooms=0;
	public static String toBinary(String num){
		switch(num){
		case "0":
			return "0000";
		case "1":
			return "0001";
		case "2":
			return "0010";
		case "3":
			return "0011";
		case "4":
			return "0100";
		case "5": 
			return "0101";
		case "6":
			return "0110";
		case "7": 
			return "0111";
		case "8":
			return "1000";
		case "9":
			return "1001";
		case "10":
			return "1010";
		case "11":
			return "1011";
		case "12":
			return "1100";
		case "13":
			return "1101";
		case "14":
			return "1110";
		case "15":
			return "1111";				
		}
		return " ";
	}
	public static int[] findunassigned(int[][] floodedarea){
		for(int i=0; i<floodedarea.length; i++){
			for(int j=0; j<floodedarea[i].length; j++){
				if(floodedarea[i][j]==0){
					int[] coordinate={i,j};
					return coordinate;
				}
			}
		}
		int[] coordinate={-1,-1};
		return coordinate;
	}
	public static void flood(String[][] array){
//		ArrayList<Integer> areas=new ArrayList<Integer>();
		int[][] floodedarea=new int[array.length][array[0].length];
		int areanum=1;
		int[] unassigned={0,0};
//		System.out.println(Arrays.toString(unassigned));
		while(unassigned[0]!=-1){
			int area=1;
			unassignedrooms.add(unassigned);
			while(unassignedrooms.size()>0){
				unassigned=unassignedrooms.get(0);
				unassignedrooms.remove(0);
				floodedarea[unassigned[0]][unassigned[1]]=1;
	//			System.out.println(Arrays.toString(unassigned));
	//			System.out.println(Arrays.deepToString(floodedarea));
				floodedarea[unassigned[0]][unassigned[1]]=areanum;
				if(array[unassigned[0]][unassigned[1]].charAt(0)=='0'){
					if(floodedarea[unassigned[0]+1][unassigned[1]]==0){
						floodedarea[unassigned[0]+1][unassigned[1]]=-1;
						int[] pair={unassigned[0]+1,unassigned[1]};
						unassignedrooms.add(pair);
						area+=1;
					}
				}
				if(array[unassigned[0]][unassigned[1]].charAt(1)=='0'){
					if(floodedarea[unassigned[0]][unassigned[1]+1]==0){
						floodedarea[unassigned[0]][unassigned[1]+1]=-1;
						area+=1;
						int[] pair={unassigned[0],unassigned[1]+1};
						unassignedrooms.add(pair);
					}
				}
				if(array[unassigned[0]][unassigned[1]].charAt(2)=='0'){
					if(floodedarea[unassigned[0]-1][unassigned[1]]==0){
						floodedarea[unassigned[0]-1][unassigned[1]]=-1;
						area+=1;
						int[] pair={unassigned[0]-1,unassigned[1]};
						unassignedrooms.add(pair);
					}
				}
				if(array[unassigned[0]][unassigned[1]].charAt(3)=='0'){
					if(floodedarea[unassigned[0]][unassigned[1]-1]==0){
						floodedarea[unassigned[0]][unassigned[1]-1]=-1;
						area+=1;
						int[] pair={unassigned[0],unassigned[1]-1};
						unassignedrooms.add(pair);
					}
				}
	//			System.out.println(array[unassigned[0]][unassigned[1]]+" "+unassigned[0]+" "+unassigned[1]);
			}
//			areas.add(area);
			if(area>maxarea){
				maxarea=area;
			}
			numrooms+=1;
			unassigned=findunassigned(floodedarea);
		}
//		return areas;
	}
	public static void floodoneroom(String[][] squares, int[] coordinate){
		int[][] floodedarea=new int[squares.length][squares[0].length];
		int area=1;
		if(squares[coordinate[0]][coordinate[1]].charAt(1)=='1' && coordinate[1]!=squares[0].length-1){
			StringBuilder sb = new StringBuilder(squares[coordinate[0]][coordinate[1]]);
			sb.setCharAt(1, '0');
			squares[coordinate[0]][coordinate[1]]=sb.toString();
			floodedarea[coordinate[0]][coordinate[1]]=-1;
			int[] unassigned=coordinate;
			unassignedrooms.add(unassigned);
			while(unassignedrooms.size()>0){
				unassigned=unassignedrooms.get(0);
				unassignedrooms.remove(0);
				floodedarea[unassigned[0]][unassigned[1]]=1;
				if(squares[unassigned[0]][unassigned[1]].charAt(0)=='0'){
					if(floodedarea[unassigned[0]+1][unassigned[1]]==0){
						floodedarea[unassigned[0]+1][unassigned[1]]=-1;
						int[] pair={unassigned[0]+1,unassigned[1]};
						unassignedrooms.add(pair);
						area+=1;
					}
				}
				if(squares[unassigned[0]][unassigned[1]].charAt(1)=='0'){
					if(floodedarea[unassigned[0]][unassigned[1]+1]==0){
						floodedarea[unassigned[0]][unassigned[1]+1]=-1;
						area+=1;
						int[] pair={unassigned[0],unassigned[1]+1};
						unassignedrooms.add(pair);
					}
				}
				if(squares[unassigned[0]][unassigned[1]].charAt(2)=='0'){
					if(floodedarea[unassigned[0]-1][unassigned[1]]==0){
						floodedarea[unassigned[0]-1][unassigned[1]]=-1;
						area+=1;
						int[] pair={unassigned[0]-1,unassigned[1]};
						unassignedrooms.add(pair);
					}
				}
				if(squares[unassigned[0]][unassigned[1]].charAt(3)=='0'){
					if(floodedarea[unassigned[0]][unassigned[1]-1]==0){
						floodedarea[unassigned[0]][unassigned[1]-1]=-1;
						area+=1;
						int[] pair={unassigned[0],unassigned[1]-1};
						unassignedrooms.add(pair);
					}
				}
			}
			sb = new StringBuilder(squares[coordinate[0]][coordinate[1]]);
			sb.setCharAt(1, '1');
			squares[coordinate[0]][coordinate[1]]=sb.toString();
		}
		if(area>=amax){
			amax=area;
			optimizedroom[0]=coordinate[0];
			optimizedroom[1]=coordinate[1];
			optimizedroom[2]=1;
		}
		area=1;
		floodedarea=new int[squares.length][squares[0].length];
		if(squares[coordinate[0]][coordinate[1]].charAt(2)=='1' && coordinate[0]!=0){
			StringBuilder sb = new StringBuilder(squares[coordinate[0]][coordinate[1]]);
			sb.setCharAt(2, '0');
			squares[coordinate[0]][coordinate[1]]=sb.toString();
			floodedarea[coordinate[0]][coordinate[1]]=-1;
			int[] unassigned=coordinate;
			unassignedrooms.add(unassigned);
			while(unassignedrooms.size()>0){
				unassigned=unassignedrooms.get(0);
				unassignedrooms.remove(0);
				floodedarea[unassigned[0]][unassigned[1]]=1;
				if(squares[unassigned[0]][unassigned[1]].charAt(0)=='0'){
					if(floodedarea[unassigned[0]+1][unassigned[1]]==0){
						floodedarea[unassigned[0]+1][unassigned[1]]=-1;
						int[] pair={unassigned[0]+1,unassigned[1]};
						unassignedrooms.add(pair);
						area+=1;
					}
				}
				if(squares[unassigned[0]][unassigned[1]].charAt(1)=='0'){
					if(floodedarea[unassigned[0]][unassigned[1]+1]==0){
						floodedarea[unassigned[0]][unassigned[1]+1]=-1;
						area+=1;
						int[] pair={unassigned[0],unassigned[1]+1};
						unassignedrooms.add(pair);
					}
				}
				if(squares[unassigned[0]][unassigned[1]].charAt(2)=='0'){
					if(floodedarea[unassigned[0]-1][unassigned[1]]==0){
						floodedarea[unassigned[0]-1][unassigned[1]]=-1;
						area+=1;
						int[] pair={unassigned[0]-1,unassigned[1]};
						unassignedrooms.add(pair);
					}
				}
				if(squares[unassigned[0]][unassigned[1]].charAt(3)=='0'){
					if(floodedarea[unassigned[0]][unassigned[1]-1]==0){
						floodedarea[unassigned[0]][unassigned[1]-1]=-1;
						area+=1;
						int[] pair={unassigned[0],unassigned[1]-1};
						unassignedrooms.add(pair);
					}
				}
			}
			sb = new StringBuilder(squares[coordinate[0]][coordinate[1]]);
			sb.setCharAt(2, '1');
			squares[coordinate[0]][coordinate[1]]=sb.toString();
		}
		if(area>=amax){
			amax=area;
			optimizedroom[0]=coordinate[0];
			optimizedroom[1]=coordinate[1];
			optimizedroom[2]=2;
		}
	}
	public static void main(String[] args) {
		BufferedReader reader = null;
		BufferedWriter writer = null;
		try {
			File file = new File("castle.in");
			reader = new BufferedReader(new FileReader(file));
			writer = new BufferedWriter(new FileWriter("castle.out"));
			String[] dimensions=reader.readLine().split(" ");
			String[][] squares=new String[Integer.parseInt(dimensions[1])][Integer.parseInt(dimensions[0])];
			for(int i=0; i<Integer.parseInt(dimensions[1]); i++){
				squares[i]=reader.readLine().split(" ");
			}
			for(int i=0; i<squares.length; i++){
				for(int j=0; j<squares[i].length; j++){
//					System.out.println(i+" | "+j+" | "+squares[i][j]);
					squares[i][j]=toBinary(squares[i][j]);
				}
			}
//			System.out.println(Arrays.deepToString(squares));
			ArrayList<Integer> areas=new ArrayList<Integer>();
			long s=System.currentTimeMillis();
			flood(squares);
			long e=System.currentTimeMillis();
//			System.out.println(e-s);
			long as=System.currentTimeMillis();
			for(int j=squares[0].length-1; j>=0; j--){
				for(int i=0; i<squares.length; i++){
					int[] coordinates={i,j};
					floodoneroom(squares, coordinates);
				}
			}
			long ae=System.currentTimeMillis();
//			System.out.println(ae-as);
//			System.out.println(amax);
//			System.out.println(numrooms);
			optimizedroom[0]+=1;
			optimizedroom[1]+=1;
			if(optimizedroom[2]==1){
//				System.out.println(numrooms+"\n"+maxarea+"\n"+amax+"\n"+optimizedroom[0]+" "+optimizedroom[1]+" "+"E"+"\n");
				writer.write(numrooms+"\n"+maxarea+"\n"+amax+"\n"+optimizedroom[0]+" "+optimizedroom[1]+" "+"E"+"\n");
			}else if(optimizedroom[2]==2){
//				System.out.println(numrooms+"\n"+maxarea+"\n"+amax+"\n"+optimizedroom[0]+" "+optimizedroom[1]+" "+"N"+"\n");
				writer.write(numrooms+"\n"+maxarea+"\n"+amax+"\n"+optimizedroom[0]+" "+optimizedroom[1]+" "+"N"+"\n");
			}
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
