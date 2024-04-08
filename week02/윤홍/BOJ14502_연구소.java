package BOJ_14502_연구소;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	static int N, M;
	static int[][] arr;
	static int[][] copy;
	static List<List<int[]>> com = new ArrayList<>();
	static int[] di = { -1, 0, 1, 0 };
	static int[] dj = { 0, 1, 0, -1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		arr = new int[N][M];
		copy = new int[N][M];
		List<int[]> list = new ArrayList<>();
		Queue<int[]> virus = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				arr[i][j] = sc.nextInt();
				if (arr[i][j] == 0) {
					list.add(new int[] { i, j });
				}
			}
		}
		for (int i = 0; i < N; i++) {
			copy[i] = Arrays.copyOf(arr[i], M);
		}
		boolean[] visited = new boolean[list.size()];

		combination(list, visited, 0, 0, 3);

		int max = 0;

		for (int i = 0; i < com.size(); i++) {
			for (int j = 0; j < com.get(i).size(); j++) {
				var curr = com.get(i).get(j);
				int x = curr[0];
				int y = curr[1];

				arr[x][y] = 1;
			}

			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					if (arr[r][c] == 2) {
						virus.add(new int[] { r, c });
					}
				}
			}

			while (!virus.isEmpty()) {
				var curr = virus.poll();
				int x = curr[0];
				int y = curr[1];

				for (int d = 0; d < 4; d++) {
					int nx = x + di[d];
					int ny = y + dj[d];
					if (check(nx, ny)) {
						arr[nx][ny] = 2;
						virus.add(new int[] { nx, ny });
					}
				}
			}
			int sum = 0;
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					if (arr[r][c] == 0) {
						sum++;
					}
				}
			}
			if (max < sum) {
				max = sum;
			}
			for (int n = 0; n < N; n++) {
				arr[n] = Arrays.copyOf(copy[n], M);
			}
		}
		System.out.println(max);

	}

	static void combination(List<int[]> list, boolean[] visited, int start, int dp, int r) {
		if (dp == r) {
			List<int[]> arr = new ArrayList<>();
			for (int i = 0; i < list.size(); i++) {
				if (visited[i]) {
					arr.add(list.get(i));
				}
			}
			com.add(arr);
			return;
		}
		for (int i = start; i < list.size(); i++) {
			if (!visited[i]) {
				visited[i] = true;
				combination(list, visited, i + 1, dp + 1, r);
				visited[i] = false;
			}
		}
	}

	static boolean check(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < M && arr[x][y] == 0;
	}

}
