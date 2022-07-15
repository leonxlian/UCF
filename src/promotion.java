import java.io.*;
import java.util.*;
public class promotion {
	public static PrintWriter out;
	public static void main(String[] args)throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		out=new PrintWriter(System.out);
		int t=Integer.parseInt(st.nextToken());
		while(t-->0) {
			st=new StringTokenizer(br.readLine());
			int n=Integer.parseInt(st.nextToken());
			int q=Integer.parseInt(st.nextToken());
			int a[]=new int[n];
			st=new StringTokenizer(br.readLine());
			for(int i=0;i<n;i++) {
				a[i]=Integer.parseInt(st.nextToken());
			}
			int pre[]=new int[n+1];
			for(int i=0;i<n;i++) {
				pre[i+1]=pre[i]+a[i];
			}
			while(q-->0) {
				st=new StringTokenizer(br.readLine());
				int num=Integer.parseInt(st.nextToken());
				int av=Integer.parseInt(st.nextToken());
				long bounds=av*num;
				int count=0;
				for(int i=0;i+num<n;i++) {
					if(pre[i+num]-pre[i]>=bounds) {
						count++;
					}
				}
				out.println(count);
			}
		}
		out.close();
	}
}
