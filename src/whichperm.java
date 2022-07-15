import java.io.*;
import java.util.*;
public class whichperm {
	public static PrintWriter out;
	static ArrayList<ArrayList<Integer>>al;
	static int a[];
	static int n;
	static ArrayList<Integer>ans;
	public static void main(String[] args)throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		out=new PrintWriter(System.out);
		int t=Integer.parseInt(st.nextToken());
		while(t-->0) {
			st=new StringTokenizer(br.readLine());
			n=Integer.parseInt(st.nextToken());
			HashSet<Integer>hs=new HashSet<>();
			al=new ArrayList<>();
			st=new StringTokenizer(br.readLine());
			a=new int[n-1];
			ans=new ArrayList<>();
			for(int i=0;i<n;i++) {
				al.add(new ArrayList<Integer>());
			}
			for(int i=0;i<n-1;i++) {
				a[i]=Integer.parseInt(st.nextToken());
			}
			boolean exists=false;
			//for(int i=0;i<n;i++) {
				ArrayList<Integer>al=new ArrayList<>();
				boolean ok=dfs(0, al, 0);
				if(ok) {
					exists=true;
				}
			//}
			if(exists) {
				for(int i=0;i<ans.size();i++) {
					if(i==0) {
						out.print(ans.get(i));
						continue;
					}
					out.print(" "+ans.get(i));
				}
			}else {
				out.print("impossible");
			}
			out.println();
		}
		out.close();
	}
	static boolean dfs(int cur, ArrayList<Integer> vis, int index) {
		if(index==n-1) {
			vis.add(cur);
			ans=vis;
			return true;
		}
		vis.add(cur);
		if(cur-a[index]>=0&&!vis.contains(cur-a[index])) {
			return dfs(cur-a[index], vis, index+1);
		}
		if(cur+a[index]<n&&!vis.contains(cur+a[index])) {
			return dfs(cur+a[index], vis, index+1);
		}
		return false;
	}
}
/*
			
			for(int i=0;i<n;i++) {
				hs.add(i);
			}
			int a[]=new int[n-1];
			int cur=0;
			hs.remove(0);
			StringBuilder ans=new StringBuilder();
			ans.append(0);
			boolean ok=true;
			for(int i=0;i<n-1;i++) {
				if(hs.contains(cur-a[i])){
					ans.append(" "+(cur-a[i]));
					cur-=a[i];
					hs.remove(cur);
				}else if(hs.contains(cur+a[i])) {
					ans.append(" "+(cur+a[i]));
					cur+=a[i];
					hs.remove(cur);
				}else {
					ok=false;
					break;
				}
			}
*/
			
