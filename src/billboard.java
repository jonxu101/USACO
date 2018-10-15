import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
public class billboard {
	public static long intersectionarea(int[] billboard, int[] truck){
		if(billboard[2]<truck[0] || billboard[0]>truck[2] || billboard[1]>truck[3] || billboard[3]<truck[1]){
			return 0;
		}else if(billboard[2]<truck[2] && billboard[3]<truck[3] && billboard[0]>truck[0] && billboard[1]>truck[1]){
			return (long)(billboard[2]-billboard[0])*(long)(billboard[3]-billboard[1]);
		}else if(billboard[2]>truck[2] && billboard[3]>truck[3] && billboard[0]<truck[0] && billboard[1]<truck[1]){
			return (long)(truck[2]-truck[0])*(long)(truck[3]-truck[1]);
		}else{
			int[] xvalues={billboard[0], billboard[2], truck[0], truck[2]};
			int[] yvalues={billboard[1], billboard[3], truck[1], truck[3]};
			Arrays.sort(xvalues);
			Arrays.sort(yvalues);
			return (long)(xvalues[2]-xvalues[1])*(long)(yvalues[2]-yvalues[1]);
		}
	}
	public static void main(String[] args) {
		BufferedReader reader = null;
		BufferedWriter writer = null;
		try {
			File file = new File("billboard.in");
			reader = new BufferedReader(new FileReader(file));
			writer = new BufferedWriter(new FileWriter("billboard.out"));
			String[] sbillboarda=reader.readLine().split(" ");
			String[] sbillboardb=reader.readLine().split(" ");
			String[] struck=reader.readLine().split(" ");
			int[] billboarda=new int[4];
			int[] billboardb=new int[4];
			int[] truck=new int[4];
			int[] intersectcoordinates=new int[4];
			int index=0;
			for(String s:sbillboarda){
				billboarda[index]=Integer.parseInt(s);
				index++;
			}
			index=0;
			for(String s:sbillboardb){
				billboardb[index]=Integer.parseInt(s);
				index++;
			}
			index=0;
			for(String s:struck){
				truck[index]=Integer.parseInt(s);
				index++;
			}
			long area=intersectionarea(billboarda, truck);
			area+=intersectionarea(billboardb, truck);
//			System.out.println(intersectionarea(billboarda, truck)+" | "+intersectionarea(billboardb, truck));
			writer.write((long)(billboarda[3]-billboarda[1])*(long)(billboarda[2]-billboarda[0])+(long)(billboardb[3]-billboardb[1])*(long)(billboardb[2]-billboardb[0])-area+"\n");
//			System.out.println((long)(billboarda[3]-billboarda[1])*(long)(billboarda[2]-billboarda[0])+(long)(billboardb[3]-billboardb[1])*(long)(billboardb[2]-billboardb[0])-area);
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
