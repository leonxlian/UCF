import java.io.*;
import java.util.*;

public class coffee {
	public static PrintWriter out;
	static HashMap<String, Long>hm;
	public static void main(String[] args)throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		out=new PrintWriter(System.out);
		int t=Integer.parseInt(st.nextToken());
		while(t-->0) {
			hm=new HashMap<>();
			st=new StringTokenizer(br.readLine());
			int n=Integer.parseInt(st.nextToken());
			int e=Integer.parseInt(st.nextToken());
			int p=Integer.parseInt(st.nextToken());
			for(int i=0;i<e;i++) {
				st=new StringTokenizer(br.readLine());
				int a=Integer.parseInt(st.nextToken())-1;
				int b=Integer.parseInt(st.nextToken())-1;
				long time=Long.parseLong(st.nextToken());
				hm.put(a+" "+b, time);
				hm.put(b+" "+a, time);
			}
			long d[][]=new long[n][n];
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					if(i==j) {
						continue;
					}
					d[i][j]=hm.getOrDefault(i+" "+j, Long.MAX_VALUE);
				}
			}
			for(int k=0;k<n;k++) {
				for(int i=0;i<n;i++) {
					for(int j=0;j<n;j++) {
						if(d[i][k]!=Long.MAX_VALUE&&d[k][j]!=Long.MAX_VALUE) {
							d[i][j]=Math.min(d[i][j], d[i][k]+d[k][j]);
						}
					}
				}
			}
			/*for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					out.print(d[i][j]);
				}
				out.println();
			}*/
			while(p-->0) {
				st=new StringTokenizer(br.readLine());
				int a=Integer.parseInt(st.nextToken())-1;
				int c=Integer.parseInt(st.nextToken())-1;
				int b=Integer.parseInt(st.nextToken())-1;
				if(d[a][c]==Long.MAX_VALUE||d[c][b]==Long.MAX_VALUE) {
					out.println(-1);
					continue;
				}
				out.println((long)(d[a][c]+d[c][b]));
			}
		}
		out.close();
	}
}
