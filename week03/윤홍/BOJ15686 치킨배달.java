package BOJ_15686_치킨배달;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	static int N, M, ans;
	static int[][] map;
	static List<int[]> num = new ArrayList<>();
	static Queue<List<int[]>> chickenResult = new LinkedList<>();
	static int[] di = { 1, 0, -1, 0 };
	static int[] dj = { 0, -1, 0, 1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt(); // 지도 크기
		M = sc.nextInt(); // 존재할 수 있는 최대 치킨집의 수

		map = new int[N][N];
		int[][] copy = new int[N][N];

		List<int[]> chickenComb = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
				if (map[i][j] == 2) {
					chickenComb.add(new int[] { i, j });
					map[i][j] = 0;
				}
			}
		}

		for (int i = 0; i < N; i++) {
			copy[i] = Arrays.copyOf(map[i], N);
		}

		// 일단 내가 생각한 방법은 치킨집의 최대수를 유지하면서 짤 수 있는 조합들을 구하기
		boolean[] visited = new boolean[chickenComb.size()];

		comb(visited, chickenComb, 0, 0, M);
		// 여기서 조합을 완성하고 조합을 담은 Queue를 활용하여 경우의 수 따져보기...인데

//		System.out.println(chickenResult.size());

		int min = Integer.MAX_VALUE;

//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < N; j++) {
//				System.out.print(copy[i][j] + " ");
//			}
//			System.out.println();
//		}

		while (!chickenResult.isEmpty()) {

			for (int i = 0; i < N; i++) {
				map[i] = Arrays.copyOf(copy[i], N);
			}

			ans = 0;

			var curr = chickenResult.poll();

//			System.out.println(curr.size());

			for (int i = 0; i < M; i++) {
				int[] num2 = curr.get(i);
				map[num2[0]][num2[1]] = 2;
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == 1) {
						bfs(i, j, 0);
					}
				}
			}
			if (ans < min) {
				min = ans;
			}

		}
		System.out.println(min);
	}

	private static void bfs(int x, int y, int dp) {
		Queue<int[]> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		q.add(new int[] { x, y, dp });
		while (!q.isEmpty()) {
			var curr = q.poll();
			int now_x = curr[0];
			int now_y = curr[1];
			int count = curr[2];

			if (map[now_x][now_y] == 2) {
				ans += count;
				return;
			}

			for (int d = 0; d < 4; d++) {
				int nx = now_x + di[d];
				int ny = now_y + dj[d];
				if (check(nx, ny) && !visited[nx][ny]) {
					visited[nx][ny] = true;
					q.add(new int[] { nx, ny, count + 1 });
				}
			}
		}

	}

	static boolean check(int x, int y) {
		return x >= 0 && y >= 0 && x < N && y < N;
	}

	static void comb(boolean[] visited, List<int[]> list, int start, int dp, int r) {
		if (dp == r) {
			num = new LinkedList<>();
			for (int i = 0; i < list.size(); i++) {
				if (visited[i]) {
					num.add(list.get(i));
				}
			}
			chickenResult.add(num);
			return;

		}

		for (int i = start; i < list.size(); i++) {
			if (!visited[i]) {
				visited[i] = true;
				comb(visited, list, i + 1, dp + 1, r);
				visited[i] = false;
			}
		}
	}
}
