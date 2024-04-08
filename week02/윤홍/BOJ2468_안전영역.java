package BOJ_2468_안전영역;

import java.util.Scanner;

public class Main {

	static int[][] arr;
	static int N;
	static boolean[][] visited;
	static int[] di = { -1, 0, 1, 0 };
	static int[] dj = { 0, 1, 0, -1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();

		arr = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				arr[i][j] = sc.nextInt();
			}
		}
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (arr[i][j] < min) {
					min = arr[i][j];
				}
				if (arr[i][j] > max) {
					max = arr[i][j];
				}
			}
		}

		int result = 0;

		for (int i = 0; i < max; i++) {
			visited = new boolean[N][N];
			int cnt = 0;
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (arr[r][c] > i && !visited[r][c]) {
						dfs(r, c, i);
						cnt++;
					}
				}
			}

			if (result < cnt)
				result = cnt;
		}
		System.out.println(result);

	}

	static void dfs(int i, int j, int x) {

		visited[i][j] = true;
		for (int d = 0; d < 4; d++) {
			int ni = i + di[d];
			int nj = j + dj[d];
			if (check(ni, nj) && !visited[ni][nj] && arr[ni][nj] > x) {
				visited[ni][nj] = true;
				dfs(ni, nj, x);

			}
		}
	}

	static boolean check(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}

}
