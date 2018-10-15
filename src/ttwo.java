/*
ID: jonxu101
LANG: JAVA
TASK: ttwo
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
class cow {
	private int x, y, d;
	cow(int[] position){
		x=position[0];
		y=position[1];
		d=position[2];
	}
	public int[] getPosition(){
		int[] coordinates={x,y};
		return coordinates;
	}
	public void move(){
		if(d==0){
			if(y==0){
				d=1;
			}else if(ttwo.data[y-1][x].equals("*")){
				d=1;
			}else{
				y-=1;
			}
		}else if(d==1){
			if(x==9){
				d=2;
			}else if(ttwo.data[y][x+1].equals("*")){
				d=2;
			}else{
				x+=1;
			}
		}else if(d==2){
			if(y==9){
				d=3;
			}else if(ttwo.data[y+1][x].equals("*")){
				d=3;
			}else{
				y+=1;
			}
		}else{
			if(x==0){
				d=0;
			}else if(ttwo.data[y][x-1].equals("*")){
				d=0;
			}else{
				x-=1;
			}
		}
	}
	public boolean compareTo(cow c){
		if(this.x==c.getPosition()[0] && this.y==c.getPosition()[1]){
			return true;
		}else{
			return false;
		}
	}
}
public class ttwo {
	public static String[][] data=new String[10][10];
	public static boolean donecheck(cow farmer, cow[] cows, int minutes, cow[] origcows, cow origfarmer){
		if(minutes==1000000){
			return true;
		}
		int num=0;
		for(cow c:cows){
			if(c.compareTo(farmer)){
				num++;
			}
		}
		if(num==cows.length){
			return true;
		}
//		if(!origfarmer.compareTo(farmer)){
//			return false;
//		}
//		for(int i=0; i<origcows.length;i++){
//			if(!origcows[i].compareTo(cows[i])){
//				return false;
//			}
//		}
		return false;
	}
	public static void main(String[] args) {
		BufferedReader reader = null;
		BufferedWriter writer = null;
		try {
			File file = new File("ttwo.in");
			reader = new BufferedReader(new FileReader(file));
			writer = new BufferedWriter(new FileWriter("ttwo.out"));
			String currentline=reader.readLine();
			data[0]=currentline.split("");
			int i=1;
			currentline=reader.readLine();
			while(currentline!=null){
				data[i]=currentline.split("");
				currentline=reader.readLine();
				i++;
			}
			int numcow=0;
			for(int x=0; x<data.length; x++){
				for(int y=0; y<data.length; y++){
					if(data[y][x].equals("C")){
						numcow++;
					}
				}
			}
//			System.out.println(Arrays.deepToString(data));
			int cownumber=0;
			int farmernum=0;
			cow farmer=null;
			cow[] originalcows=new cow[numcow];
			cow originalfarmer=null;
			cow[] cows=new cow[numcow];
			for(int y=0; y<data.length; y++){
				for(int x=0; x<data.length; x++){
					if(cownumber==numcow && farmernum==1){
						continue;
					}
					if(data[y][x].equals("C")){
						int[] position={x,y,0};
						cow acow=new cow(position);
						cows[cownumber]=acow;
						originalcows[cownumber]=new cow(position);
						cownumber++;
					}else if(data[y][x].equals("F")){
						int[] position={x,y,0};
						farmer=new cow(position);
						originalfarmer=new cow(position);
						farmernum++;
					}
				}
			}
			int minutes=0;
			boolean donestatus=false;
			while(!donestatus){
				for(cow c:cows){
					c.move();
//					System.out.println("C: "+Arrays.toString(c.getPosition()));
				}
				farmer.move();
//				System.out.println("F: "+Arrays.toString(farmer.getPosition()));
				minutes++;
//				if(minutes==1){
////					System.out.println("C: "+Arrays.toString(cows[0].getPosition())+"F: "+Arrays.toString(farmer.getPosition()));
//				}
				donestatus=donecheck(farmer,cows,minutes,originalcows,originalfarmer);
			}
			if(minutes!=1000000){
				writer.write(minutes+"\n");
//				System.out.println(minutes);
			}else{
				writer.write("0\n");
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
