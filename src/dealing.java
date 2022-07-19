/*
 * 50601
 */
import java.io.*;
import java.util.*;
public class dealing {
	public static PrintWriter out;
	public static void main(String[] args)throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		out=new PrintWriter(System.out);
		int t=Integer.parseInt(st.nextToken());
		/*int count=0;
		for(int i=640000;i>=639920;i--) {
			for(int j=1;j<=50;j++) {
				out.println(i+" "+(j));
				count++;
			}
		}
		out.println(count);*/
		while(t-->0) {
			st=new StringTokenizer(br.readLine());
			int n=Integer.parseInt(st.nextToken());
			int k=Integer.parseInt(st.nextToken());
			if(k>=n) {
				out.println(2);
				continue;
			}
			int b[]=new int[n];
			int index=1;
			for(int i=0;i<k;i++) {
				int cur=i;
				while(cur+k<n) {
					cur+=k;
				}
				while(cur>=0) {
					b[cur]=index;
					cur-=k;
					index++;
				}
			}
			/*for(int i:a) {
				out.print(i+" ");
			}
			out.println();
			for(int i:b) {
				out.print(i+" ");
			}*/
			int moves=1;
			int pos=0;
			pos=b[pos]-1;
			//System.out.println(n+" "+k);
			while(pos!=0) {
				pos=b[pos]-1;
				moves++;
			}
			out.println(moves);
		}
		out.close();
	}
}

