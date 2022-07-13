import java.io.*;
import java.util.*;
public class destroy{
	public static void main(String[] args)throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int t=Integer.parseInt(st.nextToken());
		while(t-->0) {
			st=new StringTokenizer(br.readLine());
			int n=Integer.parseInt(st.nextToken());
			int m=Integer.parseInt(st.nextToken());
			int d=Integer.parseInt(st.nextToken());
			Pair a[]=new Pair[m];
			for(int i=0;i<m;i++) {
				st=new StringTokenizer(br.readLine());
				int x=Integer.parseInt(st.nextToken())-1;
				int y=Integer.parseInt(st.nextToken())-1;
				a[i]=new Pair(x, y);
			}
			HashSet<Integer> hs=new HashSet<>();
			int rem[]=new int[d];
			for(int i=0;i<d;i++) {
				st=new StringTokenizer(br.readLine());
				rem[d-i-1]=Integer.parseInt(st.nextToken())-1;
				hs.add(rem[d-i-1]);
			}
			DSU dsu=new DSU(n);
			HashSet<Integer>heads=new HashSet<>();
			for(int i=0;i<m;i++) {
				if(!hs.contains(i)) {
					dsu.union(a[i].x, a[i].y);
				}
			}
			for(int i=0;i<n;i++) {
				heads.add(dsu.find(i));
			}
			int cur=0;
			int ans[]=new int[d+1];
			for(int i:heads) {
				int temp=dsu.sizeOfSet(i);
				cur+=temp*temp;
			}
			ans[0]=cur;
			for(int i=0;i<d;i++) {
				int seg=rem[i];
				int x=a[seg].x;
				int y=a[seg].y;
				if(dsu.isSameSet(x, y)) {
					dsu.union(x, y);
				}else {
					cur-=dsu.sizeOfSet(x)*dsu.sizeOfSet(x);
					cur-=dsu.sizeOfSet(y)*dsu.sizeOfSet(y);
					dsu.union(x, y);
					cur+=dsu.sizeOfSet(x)*dsu.sizeOfSet(x);
				}
				ans[i+1]=cur;
			}
			for(int i=d;i>=0;i--) {
				System.out.println(ans[i]);
			}
		}
	}
	static class Pair{
		int x;
		int y;
		public Pair(int x, int y) {
			this.x=x;this.y=y;
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
		public int sizeOfSet(int i) {
			return setSize[find(i)];
		}
	}
}
