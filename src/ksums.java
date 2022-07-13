import java.io.*;
import java.util.*;
public class ksums {
	public static PrintWriter out;
	public static void main(String[] args)throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		out=new PrintWriter(System.out);
		int t=Integer.parseInt(st.nextToken());
		while(t-->0) {
			st=new StringTokenizer(br.readLine());
			int n=Integer.parseInt(st.nextToken());
			int k=Integer.parseInt(st.nextToken());
			st=new StringTokenizer(br.readLine());
			int a[]=new int[n];
			long sum=0;
			for(int i=0;i<n;i++) {
				a[i]=Integer.parseInt(st.nextToken());
				if(i<k) {
					sum+=a[i];
				}
			}
			TreeSet<Pair>ts=new TreeSet<>(new Comparator<Pair>() {
				@Override
				public int compare(Pair o1, Pair o2) {
					if(o1.x==o2.x) {
						return Integer.compare(o1.y, o2.y);
					}
					return -Long.compare(o1.x, o2.x);
				}
			});
			int ind=1;
			ts.add(new Pair(sum, 1));
			for(int i=k;i<n;i++) {
				ind++;
				sum+=a[i];
				sum-=a[i-k];
				ts.add(new Pair(sum, ind));
			}
			while(!ts.isEmpty()) {
				Pair cur=ts.first();
				ts.remove(cur);
				out.print(cur.y+" ");
			}
			out.println();
		}
		out.close();
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
