package BOJ13549;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	static PriorityQueue<int[]> walk = new PriorityQueue<>(new Comparator<>() {

		@Override
		public int compare(int[] o1, int[] o2) {
			// TODO Auto-generated method stub
			return o1[1] - o2[1];
		}
	});

	static Queue<int[]> teleport = new LinkedList<>();
	static boolean flag;
	static boolean[] visited = new boolean[100001];
	static int Soobin, little, ans;
	static int[] di = { -1, 1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		Soobin = sc.nextInt();
		little = sc.nextInt();

		teleport.add(new int[] { Soobin, 0 });
		walk.add(new int[] { Soobin, 0 });
		visited[Soobin] = true;

		if (Soobin > little) {
			System.out.println(Soobin - little);
		} else {
			while (!flag) {
				bfs_teleport();
				bfs_walk();
			}

			System.out.println(ans);
		}
	}

	static int bfs_teleport() {

		while (!teleport.isEmpty()) {
			var curr = teleport.poll();
			int now_x = curr[0];
			int time = curr[1];

			if (now_x == little) {
				ans = time;
				flag = true;
				break;
			}

			int nx = now_x * 2;

			if (check(nx) && !visited[nx]) {
				teleport.add(new int[] { nx, time });
				walk.add(new int[] { nx, time });
				visited[nx] = true;
			}
		}

		return ans;

	}

	static int bfs_walk() {

		while (!walk.isEmpty()) {
			var curr = walk.poll();
			int now_x = curr[0];
			int time = curr[1];

			if (now_x == little) {
				ans = time;
				flag = true;
				break;
			}

			for (int i = 0; i < 2; i++) {

				int nx = now_x + di[i];

				if (check(nx) && !visited[nx]) {
					teleport.add(new int[] { nx, time + 1 });
					visited[nx] = true;
				}
			}
		}
		return ans;
	}

	static boolean check(int x) {
		return x >= 0 && x <= 100000;
	}
}
