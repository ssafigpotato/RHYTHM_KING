package BOJ13549;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int Soobin = sc.nextInt();
		int little = sc.nextInt();

		boolean[] visited = new boolean[100001];

		System.out.println(bfs(Soobin, little, visited));

	}

	static int bfs(int x, int y, boolean[] visited) {

		PriorityQueue<int[]> q = new PriorityQueue<>(new Comparator<>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				// TODO Auto-generated method stub
				if (o1[0] == o2[0]) {
					return o1[1] - o2[1];
				} else {
					return o1[0] - o2[0];
				}
			}

		});

		q.add(new int[] { x, 0 });
		visited[x] = true;
		int ans = 0;

		while (!q.isEmpty()) {
			var curr = q.poll();
			int now_x = curr[0];
			int time = curr[1];

			if (now_x == y) {
				ans = time;
				break;
			}

			for (int i = 0; i < 3; i++) {
				if (i == 0) {
					int nx = now_x * 2;
					if (check(nx) && !visited[nx]) {
						q.add(new int[] { nx, time });
						visited[nx] = true;
					}

				} else if (i == 1) {
					int nx = now_x + 1;
					if (check(nx) && !visited[nx]) {
						q.add(new int[] { nx, time + 1 });
						visited[nx] = true;
					}
				} else {
					int nx = now_x - 1;
					if (check(nx) && !visited[nx]) {
						q.add(new int[] { nx, time + 1 });
						visited[nx] = true;
					}
				}
			}
		}

		return ans;

	}

	static boolean check(int x) {
		return x >= 0 && x <= 100000;
	}
}
