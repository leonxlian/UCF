import java.io.*;
import java.util.*;
public class error {
	public static PrintWriter out;
	public static void main(String[] args)throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		out=new PrintWriter(System.out);
		int t=Integer.parseInt(st.nextToken());
		while(t-->0) {
			st=new StringTokenizer(br.readLine());
			int n=Integer.parseInt(st.nextToken());
			String a[]=new String[n];
			int b[]=new int[n];
			int c[]=new int[n];
			int d[]=new int[n];
			HashSet<String>hs=new HashSet<>();
			boolean ans=true;
			for(int i=0;i<n;i++) {
				st=new StringTokenizer(br.readLine());
				a[i]=st.nextToken();
				b[i]=Integer.parseInt(st.nextToken());
				c[i]=Integer.parseInt(st.nextToken());
				d[i]=Integer.parseInt(st.nextToken());
				if(c[i]+d[i]!=100||c[i]<=0||d[i]<=0||c[i]%10!=0||d[i]%10!=0) {
					ans=false;
				}
				if(b[i]>30||b[i]<=0) {
					ans=false;
				}
				hs.add(a[i]);
			}
			if(hs.size()!=n||!ans) {
				out.println("Internal Error (response 0)");
				continue;
			}else {
				out.println("Correct!");
			}
		}
		out.close();
	}
}
