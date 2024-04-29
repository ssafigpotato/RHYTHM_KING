package BOJ_16236_아기상어;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

/* 
 * 일단 가장 가까운 나보다 작은 물고기를 찾아야 한다.
 * 그런데...! 가까운 물고기가 한 마리가 아니라면 가장 위쪽에 있는걸로
 * 그리고 위쪽인것마저 똑같은데 거리가 같으면 왼쪽부터 먹어야 하는걸 기억해야한다.
 * 그리고 매번 먹고 나서 먹을 수 있는 물고기 있는지 없는지 확인해주기
 * 
 * 
 *
 * 6
5 4 3 2 3 4
4 3 2 3 4 5
3 2 9 5 6 6
2 1 2 3 4 5
3 2 1 6 5 4
6 6 6 6 6 6

반례
5
3 3 3 3 3
3 3 3 3 3
3 2 9 2 1
3 1 3 3 3
3 3 3 3 3
 */

public class Main {

	static int N;
	static int[][] map;
	static Queue<int[]> fish;
	static boolean flag;
	static int[] di = { -1, 0, 0, 1 };
	static int[] dj = { 0, -1, 1, 0 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new int[N][N];
		fish = new LinkedList<>();
		flag = false;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
				if (map[i][j] == 9) {
					fish.add(new int[] { i, j, 2, 0, 0 });
					map[i][j] = 0;
					// 최초 물고기 사이즈 2로 잡아주기
				}
			}
		}
		int[] current = new int[5];
		int num = Integer.MIN_VALUE;
		while (!flag) {
			current = bfs();
			flag = check_fish(current[2]);
			if (num < current[3]) {
				num = current[3];
			}

			fish.add(new int[] { current[0], current[1], current[2], current[3], current[4] });
		}

		System.out.println(num);
	}

	static int[] bfs() {

		var curr = fish.poll();
		int x = curr[0];
		int y = curr[1];
		int size = curr[2];
		int move = curr[3];
		int fish_cnt = curr[4];

		boolean[][] visited = new boolean[N][N];
		visited[x][y] = true;
		Queue<int[]> q = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] a, int[] b) {
				if (a[3] != b[3]) { // 거리가 다르면 거리를 기준으로 비교
					return Integer.compare(a[3], b[3]);
				} else if (a[0] != b[0]) { // 위쪽이 다르면 위쪽을 기준으로 비교
					return Integer.compare(a[0], b[0]);
				} else { // 위쪽이 같을 경우 왼쪽을 기준으로 비교
					return Integer.compare(a[1], b[1]);
				}
			}
		});

		q.add(new int[] { x, y, size, move, fish_cnt });
		int[] now = new int[5];

		while (!q.isEmpty()) {
			int[] eat = q.poll();
			int now_x = eat[0];
			int now_y = eat[1];
			int now_size = eat[2];
			int now_move = eat[3];
			int now_fishCnt = eat[4];

			if (now_fishCnt == now_size) {
				now_size += 1;
				now_fishCnt = 0;
			}

			if (map[now_x][now_y] < now_size && map[now_x][now_y] != 0) {
				now[0] = now_x;
				now[1] = now_y;
				now[2] = now_size;
				now[3] = now_move;
				now[4] = now_fishCnt;
				map[now_x][now_y] = 0;
				break;
			}

			for (int d = 0; d < 4; d++) {
				int nx = now_x + di[d];
				int ny = now_y + dj[d];
				if (check(nx, ny) && !visited[nx][ny] && map[nx][ny] <= now_size) {
					if (map[nx][ny] < now_size && map[nx][ny] != 0) {
						visited[nx][ny] = true;
						q.add(new int[] { nx, ny, now_size, now_move + 1, now_fishCnt + 1 });
					} else {
						visited[nx][ny] = true;
						q.add(new int[] { nx, ny, now_size, now_move + 1, now_fishCnt });
					}
				}
			}
		}
		return now;
	}

	static boolean check(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}

	static boolean check_fish(int x) {
		boolean ans = true;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] < x && map[i][j] != 0) {
					ans = false;
				}
			}
		}
		return ans;
	}

}
