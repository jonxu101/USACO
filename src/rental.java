/*
ID: jonxu101
LANG: JAVA
TASK: rental
*/
import java.io.*;
import java.util.*;
public class rental {

	public static void main(String[] args) {
		BufferedReader reader = null;
		BufferedWriter writer = null;
		try {
			File file = new File("rental.in");
			reader = new BufferedReader(new FileReader(file));
			writer = new BufferedWriter(new FileWriter("rental.out"));
			String[] s=reader.readLine().split(" ");
			int numcows=Integer.parseInt(s[0]);
			int numstores=Integer.parseInt(s[1]);
			int numneighbors=Integer.parseInt(s[2]);
			Integer[] cows=new Integer[numcows];
			for(int i=0; i<numcows; i++){
				cows[i]=Integer.parseInt(reader.readLine());
			}
			int[][] stores=new int[numstores][2];
			for(int i=0; i<numstores; i++){
				StringTokenizer st=new StringTokenizer(reader.readLine());
				stores[i][0]=Integer.parseInt(st.nextToken());
				stores[i][1]=Integer.parseInt(st.nextToken());
			}
			Integer[] neighbors=new Integer[numneighbors];
			for(int i=0; i<numneighbors; i++){
				neighbors[i]=Integer.parseInt(reader.readLine());
			}
			Arrays.sort(cows);
			Arrays.sort(stores, new Comparator<int[]>() {
	             public int compare(int[] a, int[] b) {
	            	 if(a[1]>b[1]){
	            		 return -1;
	            	 }else if(a[1]==b[1]){
	            		 if(a[0]>b[0]){
	            			 return -1;
	            		 }else{
	            			 return 1;
	            		 }
	            	 }else{
	            		 return 1;
	            	 }
	             }
	        });
			Arrays.sort(neighbors,Collections.reverseOrder());
			long maxprofit=0;
			long total=0;
			for(int i=0; i<numneighbors; i++){
				total+=neighbors[i];
			}
			long milk=0;
			for(int i=numneighbors; i<numcows; i++){
				milk+=cows[i];
			}
			int index=0;
			while(milk!=0){
				if(stores[index][0]<milk){
					total+=stores[index][1]*stores[index][0];
					milk-=stores[index][0];
					stores[index][0]=0;
					index++;
				}else if(stores[index][0]==milk){
					total+=milk*stores[index][1];
					stores[index][0]=0;
					milk=0;
					index++;
				}else{
					total+=milk*stores[index][1];
					stores[index][0]-=milk;
					milk=0;
				}
				if(stores[numstores-1][0]==0 || index>=numstores){
					break;
				}
			}
//			System.out.println(total);
			if(total>maxprofit){
				maxprofit=total;
			}
			if(index<numstores && stores[numstores-1][0]!=0){
			for(int neighborindex=numneighbors-1; neighborindex>=0; neighborindex--){
				milk=cows[neighborindex];
				total-=neighbors[neighborindex];
				while(milk!=0){
					if(stores[index][0]<milk){
						total+=stores[index][1]*stores[index][0];
						milk-=stores[index][0];
						stores[index][0]=0;
						index++;
					}else if(stores[index][0]==milk){
						total+=milk*stores[index][1];
						stores[index][0]=0;
						milk=0;
						index++;
					}else{
						total+=milk*stores[index][1];
						stores[index][0]-=milk;
						milk=0;
					}
					if(stores[numstores-1][0]==0 || index>=numstores){
						break;
					}
				}
				if(stores[numstores-1][0]==0){
					break;
				}
				if(total>maxprofit){
					maxprofit=total;
				}
			}
			}
//			System.out.println(maxprofit);
			writer.write(maxprofit+"\n");
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
