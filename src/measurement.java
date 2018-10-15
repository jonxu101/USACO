import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
public class measurement {
	public static void main(String[] args) {
		BufferedReader reader = null;
		BufferedWriter writer = null;
		try {
			File file = new File("measurement.in");
			reader = new BufferedReader(new FileReader(file));
			writer = new BufferedWriter(new FileWriter("measurement.out"));
			String[] smeasurement=reader.readLine().split(" ");
			int measurement=Integer.parseInt(smeasurement[0]);
			int initialproduction=Integer.parseInt(smeasurement[1]);
			String[][] smeasurements=new String[measurement][3];
			HashMap<Integer, Integer> rates=new HashMap<Integer, Integer>();
			for(int i=0; i<measurement; i++){
				smeasurements[i]=reader.readLine().split(" ");
			}
			int[][] measurements=new int[measurement][3];
			HashSet<Integer> cownums=new HashSet<Integer>();
			for(int i=0; i<measurement; i++){
				measurements[i][0]=Integer.parseInt(smeasurements[i][0]);
				measurements[i][1]=Integer.parseInt(smeasurements[i][1]);
				measurements[i][2]=Integer.parseInt(smeasurements[i][2]);
				rates.put(measurements[i][1], initialproduction);
				cownums.add(measurements[i][1]);
			}
			Arrays.sort(measurements, new Comparator<int[]>() {
	             public int compare(int[] a, int[] b) {
	                  return Integer.compare(a[0],b[0]);
	             }
	        });
//			for(int[] thing:measurements){
//				writer.write(thing[0]+" "+thing[1]+" "+thing[2]+"\n");
//			}
			int changes=0;
			int maxrate=0;
			HashSet<Integer> maxcows=new HashSet<Integer>();
			rates.put(1000000001,initialproduction);
			cownums.add(1000000001);
			maxcows.add(1000000001);
			for(int[] change:measurements){
//				System.out.println("before:"+rates.get(change[1]));
				int currentcow=change[1];
				int rate=rates.get(currentcow);
				rate+=change[2];
//				System.out.println("After:"+rate);
//				System.out.println("max:"+maxrate);
				rates.put(currentcow,rate);
				if(rate>maxrate){
//					System.out.println(rate);
					maxrate=rate;
					if(!maxcows.contains(currentcow) || maxcows.size()>1){
						maxcows.clear();
						maxcows.add(currentcow);
						changes++;
					}
				}else if(rate==maxrate){
					maxcows.add(currentcow);
					changes++;
				}else if(rate<maxrate && maxcows.contains(currentcow) && maxcows.size()>1){
					maxcows.remove(currentcow);
					changes++;
				}else if(rate<maxrate && maxcows.contains(currentcow) && maxcows.size()==1){
					int max=0;
					maxcows.clear();
					for(int cow:cownums){
						if(rates.get(cow)>max){
							max=rates.get(cow);
						}
					}
					maxrate=max;
					for(int cow:cownums){
						if(rates.get(cow)==max){
							maxcows.add(cow);
						}
					}
					if(!maxcows.contains(currentcow) || maxcows.size()>1){
						changes++;
					}
				}
			}
			writer.write(changes+"\n");
//			System.out.println(changes);
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
