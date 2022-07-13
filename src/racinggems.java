import java.io.*;
import java.util.*;
public class racinggems {
	public static PrintWriter out;
	public static void main(String[] args)throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		out=new PrintWriter(System.out);
		int t=Integer.parseInt(st.nextToken());
		while(t-->0) {
			st=new StringTokenizer(br.readLine());
			int n=Integer.parseInt(st.nextToken());
			int r=Integer.parseInt(st.nextToken());
			int w=Integer.parseInt(st.nextToken());
			int h=Integer.parseInt(st.nextToken());
			Pair a[]=new Pair[n];
			for(int i=0;i<n;i++) {
				int x=Integer.parseInt(st.nextToken());
				int y=Integer.parseInt(st.nextToken());
				a[i]=new Pair(x, y);
			}
			
		}
	}
	static class Pair implements Comparable<Pair>{
		long x;
		int y;
		public Pair(long x, int y) {
			this.x=x;this.y=y;
		}
		@Override
		public int compareTo(Pair o) {
			return y-o.y;
		}
	}
}
