import java.io.*;
import java.util.*;

public class party {
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
			int b[]=new int[n];
			st=new StringTokenizer(br.readLine());
			for(int i=0;i<n;i++) {
				a[i]=Integer.parseInt(st.nextToken());
			}
			st=new StringTokenizer(br.readLine());
			for(int i=0;i<n;i++) {
				b[i]=Integer.parseInt(st.nextToken());
			}
			long preA[]=new long[n+1];
			for(int i=0;i<n;i++) {
				preA[i+1]=preA[i]+a[i];
			}
			/*for(int i=0;i<=n;i++) {
				out.print(preA[i]+" ");
			}*/
			long preB[]=new long[n+1];
			for(int i=0;i<n;i++){
				preB[i+1]=preB[i]+b[i];
			}
			/*out.println();
			for(int i=0;i<=n;i++) {
				out.print(preB[i]+" ");
			}*/
			long diff[]=new long[n+1];
			for(int i=0;i<=n;i++) {
				diff[i]=preA[i]-preB[i];
			}
			//out.println();
			HashMap<Long, Long>hm=new HashMap<>();
			for(int i=1;i<=n;i++) {
				//out.print(diff[i]+" ");
				hm.put(diff[i], hm.getOrDefault(diff[i], (long) 0)+1);
			}
			long ans=0;
			for(long key:hm.keySet()) {
				if(key==0) {
					ans+=(long)((hm.get(key)*(hm.get(key)+1))/2);
				}else {
					ans+=(long)((hm.get(key)*(hm.get(key)-1))/2);
				}
			}
			out.println(ans);
		}
		out.close();
	}
}
