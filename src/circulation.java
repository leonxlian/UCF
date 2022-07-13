import java.io.*;
import java.util.*;
public class circulation {
	public static PrintWriter out;
	public static void main(String[] args)throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		out=new PrintWriter(System.out);
		int t=Integer.parseInt(st.nextToken());
		while(t-->0) {
			st=new StringTokenizer(br.readLine());
			int n=Integer.parseInt(st.nextToken());
			Triple edges[]=new Triple[n-1];
			for(int i=0;i<n-1;i++) {
				st=new StringTokenizer(br.readLine());
				int a=Integer.parseInt(st.nextToken())-1;
				int b=Integer.parseInt(st.nextToken())-1;
				int v=Integer.parseInt(st.nextToken());
				edges[i]=new Triple(a, b, v);
			}
			Arrays.sort(edges);
			for(int i=0;i<n-1;i++) {
				out.println(edges[i].x+" "+edges[i].y+" "+edges[i].val);
			}
			DSU dsu=new DSU(n);
			for(int i=0;i<n-1;i++) {
				if(!dsu.isSameSet(edges[i].x, edges[i].y)) {
					dsu.union(edges[i].x, edges[i].y);
				}
			}
			for(int i=0;i<n;i++) {
				
			}
		}
		out.close();
	}
	static class Triple implements Comparable<Triple>{
		int x;
		int y;
		int val;
		public Triple(int x, int y, int val) {
			this.x=x;this.y=y;this.val=val;
		}
		public int compareTo(Triple o) {
			return -Integer.compare(val, o.val);
		}
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
	}
}
