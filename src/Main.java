import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args)throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int n=Integer.parseInt(st.nextToken());
		int q=Integer.parseInt(st.nextToken());
		SegTree segT=new SegTree(n);
		int a[]=new int[n];
		for(int i=0;i<n;i++) {
			a[i]=Integer.parseInt(st.nextToken());
		}
		segT.build(a);
		
	}
	static class Pair implements Comparable<Pair>{
		int x;
		int y;
		public Pair(int x, int y) {
			this.x=x;this.y=y;
		}
		@Override
		public int compareTo(Pair o) {
			return x-o.x;
		}
	}
	static class SegTree{
		int size;
		long tree[];
		long[] lazy;
		public SegTree(int n) {
			size=1;
			while(size<n) {
				size*=2;
			}
			lazy=new long[2*n];
			tree=new long[2*size];
		}
		public void build(int a[]) {
			for(int i=0;i<a.length;i++) {
				tree[size+i]=a[i];
			}
			for(int i=size-1;i>=1;i--) {
				tree[i]=tree[2*i]+tree[2*i+1];
			}
		}
		public long sum(int node, int node_low, int node_high, int query_low, int query_high) {
			if(query_low<=node_low&&node_high<=query_high) {
				return tree[node];
			}
			if(query_high<node_low||query_low>node_high) {
				return 0;
			}
			push(node, node_low, node_high);
			int last_in_left=(node_low+node_high)/2;
			return sum(2*node, node_low, last_in_left, query_low, query_high)
					+sum(2*node+1, last_in_left+1, node_high, query_low, query_high);
		}
		//change
		public void setRecur(int node, int node_low, int node_high, int query_low, int query_high, int v) {
			if(query_low<=node_low&&node_high<=query_high) {
				tree[node]=v;
				return;
			}
			if(query_high<node_low||query_low>node_high) {
				return;
			}
			push(node, node_low, node_high);
			int last_in_left=(node_low+node_high)/2;
			setRecur(2*node, node_low, last_in_left, query_low, query_high, v);
			setRecur(2*node+1, last_in_left+1, node_high, query_low, query_high, v);
			tree[node]=tree[2*node]+tree[2*node+1];
		}
		public void push(int node, int low, int high) {
			if(lazy[node]==0) {
				return;
			}
			tree[2*node]+=(lazy[node]*(high-low+1)/2);
			tree[2*node+1]+=(lazy[node]*(high-low)/2);
			if(low<high) {
				lazy[2*node]=lazy[node];
				lazy[2*node+1]=lazy[node];
			}
			lazy[node]=0;
		}
	}
	static class FenwickTree{
		int arr[];
		public FenwickTree(int size) {
			arr=new int[size+1];
		}
		public void update(int index, int amt) {
			index++;
			while(index<arr.length) {
				arr[index]+=amt;
				index+=(index&-index);
			}
		}
		public void build(int a[]) {
			for(int i=0;i<a.length;i++) {
				update(i, a[i]);
			}
		}
		public void fastBuild(int a[]) {
			for(int i=1;i<=a.length;i++) {
				arr[i]+=a[i-1];
				int index=i+(i&-i);//next index that it contributes to
				if(index<=a.length) {
					arr[index]+=arr[i];
				}
			}
		}
		public long prefixSum(int index){
			long res=0;
			index+=1;
			while(index!=0) {
				res+=arr[index];
				index-=(index&-index);
			}
			return res;
		}
		//0 to n-1
		public long prefixRange(int index1, int index2) {
			index1--;
			index2--;
			return prefixSum(index2)-prefixSum(index1);
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
