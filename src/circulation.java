import java.io.*;
import java.util.*;
public class circulation {
	public static PrintWriter out;
	public static void main(String[] args)throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		out=new PrintWriter(System.out);
		int t=Integer.parseInt(st.nextToken());
		while(t-->0) {
			st=new StringTokenizer(br.readLine());
			int n=Integer.parseInt(st.nextToken());
			Triple edges[]=new Triple[n-1];
			for(int i=0;i<n-1;i++) {
				st=new StringTokenizer(br.readLine());
				int a=Integer.parseInt(st.nextToken())-1;
				int b=Integer.parseInt(st.nextToken())-1;
				int v=Integer.parseInt(st.nextToken());
				edges[i]=new Triple(a, b, v);
			}
		}
		out.close();
	}
	static class Triple implements Comparable<Triple>{
		int x;
		int y;
		int val;
		public Triple(int x, int y, int val) {
			this.x=x;this.y=y;this.val=val;
		}
		public int compareTo(Triple o) {
			return -Integer.compare(val, o.val);
		}
	}
}
