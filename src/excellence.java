import java.io.*;
import java.util.*;

public class excellence {
	public static PrintWriter out;
	public static void main(String[] args)throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		out=new PrintWriter(System.out);
		int t=Integer.parseInt(st.nextToken());
		while(t-->0) {
			st=new StringTokenizer(br.readLine());
			int n=Integer.parseInt(st.nextToken());
			int a[]=new int[n];
			for(int i=0;i<n;i++) {
				st=new StringTokenizer(br.readLine());
				a[i]=Integer.parseInt(st.nextToken());
			}
			Arrays.sort(a);
			long min=Long.MAX_VALUE;
			for(int i=0;i<n/2;i++) {
				min=Math.min(min, (long) a[i]+a[n-i-1]);
			}
			out.println(min);
		}
		out.close();
	}
}
