import java.io.*;
import java.util.*;

public class cookie {
	public static PrintWriter out;
	public static void main(String[] args)throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		out=new PrintWriter(System.out);
		int t=Integer.parseInt(st.nextToken());
		while(t-->0) {
			st=new StringTokenizer(br.readLine());
			long a[]=new long[3];
			a[0]=Long.parseLong(st.nextToken());
			a[1]=Long.parseLong(st.nextToken());
			a[2]=Long.parseLong(st.nextToken());
			long ppl=Long.parseLong(st.nextToken());
			Arrays.sort(a);
			long ans=0;
			long diff=a[1]-a[0];
			ans+=a[0];
			a[1]=diff;
			ans+=a[1];
			diff=a[2]-a[1];
			out.println(ans>=ppl?0:ppl-ans);
		}
		out.close();
	}
}
