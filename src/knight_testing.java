import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class knight_testing {
	
	public static void main(String[] args) {
		
		Knight[] knights = new Knight[5];

		knights[0] = new Knight(1900, 10);
		knights[1] = new Knight(1800, 3);
		knights[2] = new Knight(1950, 20);
		knights[3] = new Knight(1950, 10);
		knights[4] = new Knight(1850, 10);
		
		Arrays.sort(knights);
		System.out.println(Arrays.toString(knights));
		
		Arrays.sort(knights, (a, b) -> -Integer.compare(a.qual, b.qual));
		System.out.println(Arrays.toString(knights));
		
		PriorityQueue<Knight> queue = new PriorityQueue<>(new Comparator<Knight>() {
			@Override
			public int compare(Knight o1, Knight o2) {
				if (o1.qual == o2.qual) return Integer.compare(o1.start, o2.start);
				return -Integer.compare(o1.qual, o2.qual);
			}
		});
	}
	public static class Knight implements Comparable<Knight> {
		int start;
		int qual;
		
		public Knight(int start, int qual) {
			this.start = start;
			this.qual = qual;
		}
		
		@Override
		public String toString() {
			return String.format("Knight[start=%d,qual=%d]", this.start, this.qual);
		}

		@Override
		public int compareTo(Knight o) {
			return Integer.compare(this.start, o.start);
		}
	}
}
