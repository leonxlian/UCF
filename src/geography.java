import java.io.*;
import java.util.*;
public class geography {
	public static PrintWriter out;
	public static void main(String[] args)throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		out=new PrintWriter(System.out);
		int t=Integer.parseInt(st.nextToken());
		while(t-->0) {
			st=new StringTokenizer(br.readLine());
			int n=Integer.parseInt(st.nextToken());
			int m=Integer.parseInt(st.nextToken());
			DSU dsu=new DSU(n);
			HashSet<Integer>heads=new HashSet<>();
			for(int i=0;i<m;i++) {
				st=new StringTokenizer(br.readLine());
				int x=Integer.parseInt(st.nextToken())-1;
				int y=Integer.parseInt(st.nextToken())-1;
				dsu.union(x, y);
			}
			for(int i=0;i<n;i++) {
				heads.add(dsu.find(i));
			}
			long ans=0;
			for(int val:heads) {
				ans+=(long)(dsu.sizeOfSet(val)-1)*(dsu.sizeOfSet(val))/2;
			}
			out.println(ans);
		}
		out.close();
	}
	static class DSU{
		int setSize[];
		int numSets;
		int rank[];
		int parents[];
		public DSU(int n) {
			rank=new int[n];
			parents=new int[n];
			numSets=n;
			parents=new int[n];
			setSize=new int[n];
			for(int i=0;i<n;i++) {
				parents[i]=i;
				setSize[i]=1;
			}
		}
		public int find(int i) {
			return parents[i]==i?i:(parents[i]=find(parents[i]));
		}
		public void union(int i, int j) {
			if(isSameSet(i, j)) {
				return;
			}
			numSets--;
			int x=find(i);
			int y=find(j);
			if(rank[x]>rank[y]) {
				parents[y]=x;
				setSize[x]+=setSize[y];
			}else {
				parents[x]=y;
				setSize[y]+=setSize[x];
				if(rank[x]==rank[y]) {
					rank[y]++;
				}
			}
		}
		public boolean isSameSet(int i, int j) {
			return find(i)==find(j);
		}
		public int numDisjointSets(int i) {
			return numSets;
		}
		public int sizeOfSet(int i) {
			return setSize[find(i)];
		}
	}
}
