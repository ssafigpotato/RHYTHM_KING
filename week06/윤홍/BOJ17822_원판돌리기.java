package BOJ_17822_원판돌리기;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	static int N, M;
	static int[][] circle;
	static int[][] copy;
	static int[] di = { -1, 0, 1, 0 };
	static int[] dj = { 0, 1, 0, -1 };
	static boolean[][] visited;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		// 원판 갯수

		M = sc.nextInt();
		// 숫자의 갯수

		int T = sc.nextInt();
		// 회전하는 횟수

		circle = new int[N][M];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				circle[i][j] = sc.nextInt();
			}
		}

		for (int i = 0; i < T; i++) {

			int x = sc.nextInt();
			// 돌리는 원판
			int d = sc.nextInt();
			// 돌리는 방향, 0이면 시계, 1이면 반시계
			int k = sc.nextInt();
			// 돌리는 횟수
			move(x, d, k);
			visited = new boolean[N][M];
			remove();
			for (int r = 0; r < N; r++) {
				circle[r] = Arrays.copyOfRange(copy[r], 0, M);
			}
		}

		int sum = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(copy[i][j] + " ");
				sum += copy[i][j];
			}
			System.out.println();
		}
		System.out.println(sum);
	}

	static void move(int x, int d, int k) {

		copy = new int[N][M];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if ((i + 1) % x == 0) {
					if (d == 0) {

						int m = j;

						m += k;

						while (m >= M) {
							m -= M;
						}
						copy[i][m] = circle[i][j];
					} else {
						int m = j;

						m -= k;
						while (m < 0) {
							m += M;
						}
						copy[i][m] = circle[i][j];

					}
				} else {
					copy[i] = Arrays.copyOfRange(circle[i], 0, M);
				}
			}
		}
	}

	static void remove() {

		int num = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				int cnt = 1;

				for (int d = 0; d < 4; d++) {
					int ni = i + di[d];
					int nj = j + dj[d];

					if (nj == -1 && copy[i][j] != 0) {
						nj += M;
						if (copy[ni][nj] == copy[i][j]) {
							cnt++;
						}
					} else if (nj == M && copy[i][j] != 0) {
						nj -= M;
						if (copy[ni][nj] == copy[i][j]) {
							cnt++;
						}
					} else if (check(ni, nj) && copy[ni][nj] == copy[i][j] && copy[i][j] != 0) {
						cnt++;

					}

					if (cnt >= 2) {
						num++;
						bfs(i, j, copy[i][j]);
						break;
					}

				}

			}
		}

		if (num == 0) {

			minus();
		}
	}

	static void minus() {
		int sum = 0;
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (copy[i][j] > 0) {
					sum += copy[i][j];
					cnt++;
				}
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (copy[i][j] > 0 && copy[i][j] > sum / cnt) {
					copy[i][j] -= 1;
				} else if (copy[i][j] > 0 && sum % cnt > 0) {
					if (copy[i][j] <= sum / cnt) {
						copy[i][j] += 1;
					}
				} else if (copy[i][j] > 0 && sum % cnt == 0) {
					if (copy[i][j] < sum / cnt) {
						copy[i][j] += 1;
					}
				}
			}
		}

	}

	static void bfs(int x, int y, int r) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] { x, y });
		copy[x][y] = 0;
		while (!q.isEmpty()) {
			int[] curr = q.poll();
			int now_x = curr[0];
			int now_y = curr[1];
			for (int d = 0; d < 4; d++) {
				int nx = now_x + di[d];
				int ny = now_y + dj[d];

				if (check(nx, ny) && copy[nx][ny] == r && !visited[nx][ny]) {
					visited[nx][ny] = true;
					q.add(new int[] { nx, ny });
					copy[nx][ny] = 0;
				} else if (ny == -1) {
					ny += M;
					if (check(nx, ny) && copy[nx][ny] == r && !visited[nx][ny]) {
						visited[nx][ny] = true;

						q.add(new int[] { nx, ny });
						copy[nx][ny] = 0;
					}
				} else if (ny == M) {
					ny -= M;
					if (check(nx, ny) && copy[nx][ny] == r && !visited[nx][ny]) {
						visited[nx][ny] = true;

						q.add(new int[] { nx, ny });
						copy[nx][ny] = 0;
					}
				}

			}
		}
	}

	static boolean check(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < M;
	}
}
