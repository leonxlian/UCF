import java.io.*;
import java.util.*;

public class car {
	public static PrintWriter out;
	public static void main(String[] args)throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		out=new PrintWriter(System.out);
		int t=Integer.parseInt(st.nextToken());
		while(t-->0) {
			st=new StringTokenizer(br.readLine());
			String s=st.nextToken();
			int n=s.length();
			if(s.equals("CAR")) {
				out.println("Arup");
				continue;
			}
			char ch[]=s.toCharArray();
			boolean ans=false;
			int indexC=-1;
			for(int i=0;i<n;i++) {
				if(ch[i]=='C') {
					indexC=i;
					break;
				}
			}
			if(indexC==-1) {
				out.println("Unknown");
				continue;
			}
			int indexA=-1;
			for(int i=indexC+1;i<n;i++) {
				if(ch[i]=='A') {
					indexA=i;
					break;
				}
			}
			if(indexA==-1) {
				out.println("Unknown");
				continue;
			}
			int indexR=-1;
			for(int i=indexA+1;i<n;i++) {
				if(ch[i]=='R') {
					indexR=i;
					break;
				}
			}
			if(indexR==-1) {
				out.println("Unknown");
				continue;
			}else {
				out.println("Travis");
			}
		}
		out.close();
	}
}
