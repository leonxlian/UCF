import java.io.*;
import java.util.*;
public class railroad {
	public static PrintWriter out;
	static int a[], b[], c[];
	static int m, n;
	public static void main(String[] args)throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		out=new PrintWriter(System.out);
		int t=Integer.parseInt(st.nextToken());
		/*for(int l=1;l<=2;l++) {
			for(int i=0;i<1000;i++) {
				if(i==999) {
					out.println(999);
					continue;
				}
				out.print(i+" ");
			}
		}
		for(int i=0;i<1000;i++) {
			if(i==999) {
				out.println(999+" "+999);
				continue;
			}
			out.print(i+" "+i+" ");
		}*/
		while(t-->0) {
			long start=System.currentTimeMillis();
			st=new StringTokenizer(br.readLine());
			n=Integer.parseInt(st.nextToken());
			m=Integer.parseInt(st.nextToken());
			a=new int[n];
			b=new int[m];
			st=new StringTokenizer(br.readLine());
			for(int i=0;i<n;i++) {
				a[i]=Integer.parseInt(st.nextToken());
			}
			st=new StringTokenizer(br.readLine());
			for(int i=0;i<m;i++) {
				b[i]=Integer.parseInt(st.nextToken());
			}
			c=new int[2000];
			st=new StringTokenizer(br.readLine());
			for(int i=0;i<n+m;i++) {
				c[i]=Integer.parseInt(st.nextToken());
			}
			boolean ans=dfs(0, 0, 0);
			//boolean ans=dfs(197, 99, 98);
			out.println(ans?"possible":"not possible");
			long end=System.currentTimeMillis();
			//out.println(end-start);
		}
		out.close();
	}
	static boolean dfs(int cur, int ind1, int ind2) {//, int last1, int last2) {
		if(ind1==n&&ind2==m||cur==n+m) {
			return true;
		}
		//out.println("here"+" "+cur+" "+ind1+" "+ind2);
		if(ind1==n) {
			if(b[ind2]==c[cur]) {
				//out.println("here"+" "+cur+" "+ind1+" "+ind2);
				boolean ok=dfs(cur+1, ind1, ind2+1);//choose left or right
				if(ok) {
					return true;
				}
			}
			return false;
		}else if(ind2==m) {
			if(a[ind1]==c[cur]) {
				//out.println("here"+" "+cur+" "+ind1+" "+ind2);
				boolean ok=dfs(cur+1, ind1+1, ind2);//choose left or right
				if(ok) {
					return true;
				}
			}
			return false;
		}else {
			if(a[ind1]==b[ind2]) {
				if(a[ind1]!=c[cur]) {//both do not equal
					//out.println("here"+" "+cur+" "+ind1+" "+ind2);
					return false;
				}else {
					boolean ok=dfs(cur+1, ind1+1, ind2);//choose left or right
					if(ok) {
						return true;
					}
					boolean ok1=dfs(cur+1, ind1, ind2+1);//choose left or right
					if(ok1) {
						return true;
					}
				}
			}else if(a[ind1]==c[cur]){
				//out.println("here"+" "+cur+" "+ind1+" "+ind2);
				boolean ok=dfs(cur+1, ind1+1, ind2);
				if(ok) {
					return true;
				}
			}else if(b[ind2]==c[cur]) {
				//out.println("here"+" "+cur+" "+ind1+" "+ind2);
				boolean ok=dfs(cur+1, ind1, ind2+1);
				if(ok) {
					return true;
				}
			}
		}
		return false;
	}
}
//0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45 46 47 48 49 50 51 52 53 54 55 56 57 58 59 60 61 62 63 64 65 66 67 68 69 70 71 72 73 74 75 76 77 78 79 80 81 82 83 84 85 86 87 88 89 90 91 92 93 94 95 96 97 98 99
//0 0 1 1 2 2 3 3 4 4 5 5 6 6 7 7 8 8 9 9 10 10 11 11 12 12 13 13 14 14 15 15 16 16 17 17 18 18 19 19 20 20 21 21 22 22 23 23 24 24 25 25 26 26 27 27 28 28 29 29 30 30 31 31 32 32 33 33 34 34 35 35 36 36 37 37 38 38 39 39 40 40 41 41 42 42 43 43 44 44 45 45 46 46 47 47 48 48 49 49 50 50 51 51 52 52 53 53 54 54 55 55 56 56 57 57 58 58 59 59 60 60 61 61 62 62 63 63 64 64 65 65 66 66 67 67 68 68 69 69 70 70 71 71 72 72 73 73 74 74 75 75 76 76 77 77 78 78 79 79 80 80 81 81 82 82 83 83 84 84 85 85 86 86 87 87 88 88 89 89 90 90 91 91 92 92 93 93 94 94 95 95 96 96 97 97 98 98 99 99
/*
1
100 100
0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45 46 47 48 49 50 51 52 53 54 55 56 57 58 59 60 61 62 63 64 65 66 67 68 69 70 71 72 73 74 75 76 77 78 79 80 81 82 83 84 85 86 87 88 89 90 91 92 93 94 95 96 97 98 99
0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45 46 47 48 49 50 51 52 53 54 55 56 57 58 59 60 61 62 63 64 65 66 67 68 69 70 71 72 73 74 75 76 77 78 79 80 81 82 83 84 85 86 87 88 89 90 91 92 93 94 95 96 97 98 99
0 0 1 1 2 2 3 3 4 4 5 5 6 6 7 7 8 8 9 9 10 10 11 11 12 12 13 13 14 14 15 15 16 16 17 17 18 18 19 19 20 20 21 21 22 22 23 23 24 24 25 25 26 26 27 27 28 28 29 29 30 30 31 31 32 32 33 33 34 34 35 35 36 36 37 37 38 38 39 39 40 40 41 41 42 42 43 43 44 44 45 45 46 46 47 47 48 48 49 49 50 50 51 51 52 52 53 53 54 54 55 55 56 56 57 57 58 58 59 59 60 60 61 61 62 62 63 63 64 64 65 65 66 66 67 67 68 68 69 69 70 70 71 71 72 72 73 73 74 74 75 75 76 76 77 77 78 78 79 79 80 80 81 81 82 82 83 83 84 84 85 85 86 86 87 87 88 88 89 89 90 90 91 91 92 92 93 93 94 94 95 95 96 96 97 97 98 98 99 99
*/