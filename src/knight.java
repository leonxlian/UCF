import java.io.*;
import java.util.*;

public class knight {
	//public static PrintWriter out;
	public static void main(String[] args)throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		//out=new PrintWriter(System.out);
		int t=Integer.parseInt(st.nextToken());
		while(t-->0) {
			//long ss=System.currentTimeMillis();
			st=new StringTokenizer(br.readLine());
			int n=Integer.parseInt(st.nextToken());
			Knight a[]=new Knight[n];
			TreeSet<Knight>ts=new TreeSet<>();
			for(int i=0;i<n;i++) {
				st=new StringTokenizer(br.readLine());
				int s=Integer.parseInt(st.nextToken());
				int e=Integer.parseInt(st.nextToken());
				int qual=Integer.parseInt(st.nextToken());
				int time=Integer.parseInt(st.nextToken());
				a[i]=new Knight(s, e, qual, time, i);
				ts.add(a[i]);
			}
			a[0]=ts.first();
			System.out.println(a[0].i+1+" "+a[0].start+" "+Math.min(a[0].end, a[0].start+a[0].t)+" ");
			int last=Math.min(a[0].end, a[0].start+a[0].t);
			ts.remove(ts.first());
			for(int i=1;i<n;i++) {
				Knight cur=ts.first();
				if(cur.end<=last) {
					ts.remove(cur);
				}else if(cur.start>last) {
					break;
				}else {
					int maxq=cur.q;
					Knight max=cur;
					for(Knight next:ts) {
						if(next==cur) {
							continue;
						}
						if(next.start>last) {
							break;
						}
						if(next.end<=last) {
							break;
						}
						if(next.q>maxq) {
							maxq=next.q;
							max=next;
						}
					}
					System.out.println(max.i+1+" "+last+" "+Math.min(max.end, last+max.t)+" ");
					last=Math.min(max.end, last+max.t);
					ts.remove(max);
				}
			}
			//long end=System.currentTimeMillis();
			//out.println(end-ss);
		}
		//out.close();
	}
	static class Knight implements Comparable<Knight>{
		int start;
		int end;
		int q;
		int t;
		int i;
		public Knight(int start, int end, int q, int t, int i) {
			this.start=start;this.end=end;this.q=q;this.t=t;this.i=i;
		}
		public int compareTo(Knight o) {
			if(start==o.start) {
				return q-o.q;
			}
			return start-o.start;
		}
	}
}
