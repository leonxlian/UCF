import java.io.*;
import java.util.*;
public class snooze {
	public static PrintWriter out;
	public static void main(String[] args)throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		out=new PrintWriter(System.out);
		int t=Integer.parseInt(st.nextToken());
		while(t-->0) {
			st=new StringTokenizer(br.readLine());
			int n=Integer.parseInt(st.nextToken());
			long len=Long.parseLong(st.nextToken());
			long a[]=new long[n];
			st=new StringTokenizer(br.readLine());
			for(int i=0;i<n;i++) {
				a[i]=Long.parseLong(st.nextToken());
			}
			Arrays.sort(a);
			TreeMap<Long, Long>hm=new TreeMap<>();
			for(int i=0;i<n-1;i++) {
				if(a[i+1]-a[i]==0) {
					continue;
				}
				hm.put(a[i+1]-a[i]-1, hm.getOrDefault((a[i+1]-a[i]-1), (long) 0)+1);
			}
			long max=0;
			long arr[]=new long[hm.size()];
			int index=0;
			ArrayList<Long>al=new ArrayList<>();
			for(long key:hm.keySet()) {
				arr[index]=hm.get(key);
				index++;
				al.add(key);
			}
			long pre[]=new long[arr.length+1];
			
			for(int i=arr.length-1;i>=0;i--) {
				pre[i]=pre[i+1]+arr[i];
			}
			index=0;
			/*for(int i=0;i<=arr.length;i++) {
				out.print(pre[i]+" ");
			}*/
			for(int i=al.size()-1;i>=0;i--) {
				long cur=(long)(pre[i]*al.get(i));
				max=Math.max(max, cur);
			}
			out.println(max);
		}
		out.close();
	}
}
