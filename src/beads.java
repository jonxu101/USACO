/*
ID: jonxu101
LANG: JAVA
TASK: beads
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class beads {

	public static void main(String[] args) {
		BufferedReader reader = null;
		BufferedWriter writer=null;
		String beads;
		try {
		    File file = new File("beads.in");
		    reader = new BufferedReader(new FileReader(file));
		    String absolutely_unused_variable=reader.readLine();
		    beads=reader.readLine();
		    int total=0;
			for(int i = 0; i < beads.length(); i++){
				int llongest=0;
				int rlongest=0;
				char prevbead='w';
				for(int x = i-1; x>=i-beads.length(); x--) {
					int a=x;
					if(a<0) {
						a=beads.length()+x;
					}
					char bead=beads.charAt(a);
					if(bead==prevbead || bead=='w' || prevbead=='w') {
						if(bead!='w') {
							prevbead=bead;
						}
						llongest+=1;
					} else {
						break;
					}
				}
				prevbead='w';
				for(int y = i; y<beads.length()+i; y++) {
					int b=y;
					if(b>=beads.length()) {
						b=y-beads.length();
					}
					char bead=beads.charAt(b);
					if(bead==prevbead || bead=='w' || prevbead=='w') {
						if(bead!='w') {
							prevbead=bead;
						}
						rlongest+=1;
					} else {
						break;
					}
				}
				if (total<(llongest+rlongest)) {
					total=llongest+rlongest;
				}
			}
			if(total>beads.length()) {
				total=beads.length();
			}
			writer = new BufferedWriter(new FileWriter("beads.out"));
			writer.write(total+"\n");
		} catch (IOException e) {
		    e.printStackTrace();
		} finally {
		    try {
		        reader.close();
		        writer.close();
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		}
		
	}

}
