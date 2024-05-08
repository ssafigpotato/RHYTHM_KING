package BOJ_16928_뱀과사다리게임;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int ans;
	static int[] map;
	static boolean[] visited;
	static int[] dice = { 1, 2, 3, 4, 5, 6 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		map = new int[101];
		visited = new boolean[101];

		for (int i = 0; i < map.length; i++) {
			map[i] = i;
		}

		int A = sc.nextInt();
		int B = sc.nextInt();
		for (int i = 0; i < A + B; i++) {
			int start = sc.nextInt();
			int end = sc.nextInt();
			map[start] = end;
		}

		bfs();

		System.out.println(ans);

	}

	static void bfs() {
		Queue<int[]> q = new LinkedList<>();
		visited[1] = true;
		q.add(new int[] { map[1], 0 });
		while (!q.isEmpty()) {
			var curr = q.poll();
			int now_x = curr[0];
			int cnt = curr[1];

			if (now_x == 100) {
				ans = cnt;
			}
			for (int i = 0; i < 6; i++) {
				int nx = now_x + dice[i];
				if (check(nx) && !visited[nx]) {
					visited[nx] = true;
					int r = map[nx];
					q.add(new int[] { r, cnt + 1 });
				}
			}
		}
	}

	static boolean check(int x) {
		return x <= 100;
	}
}
