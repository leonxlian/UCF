import java.io.*;
import java.util.*;
public class travel {
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
			String s=st.nextToken();
			int a[]=new int[k];
			char ch[]=s.toCharArray();
			ArrayDeque<Integer>q=new ArrayDeque<>();
			int temp=k;
			for(int i=0;i<n-k+1;i++) {
				q.add(ch[i]-'0');
			}
			int index=n-k+1;
			for(int i=0;i<k;i++) {
				int max=-1;
				for(int j=9;j>=0;j--) {
					if(q.contains(j)) {
						max=j;
						a[i]=max;
						break;
					}
				}
				while(q.getFirst()!=max) {
					int cur=q.poll();
					if(q.size()==0) {
						break;
					}
				}
				if(q.size()>0) {
					q.poll();
				}
				temp--;
				for(int j=index;j<Math.min(n, n-temp+1);j++) {
					q.add(ch[j]-'0');
				}
				index=n-temp+1;
			}
			for(int i=0;i<k;i++) {
				out.print(a[i]);
			}
			out.println();
		}
		out.close();
	}
}
