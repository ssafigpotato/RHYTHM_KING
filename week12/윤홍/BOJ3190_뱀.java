package BOJ_3190;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	static List<int[]> direction;
	static int[] di = { 0, 1, 0, -1 };
	static int[] dj = { 1, 0, -1, 0 };
	static int[][] map;
	static int N;
	static int ans;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		// 지도 크기

		map = new int[N + 1][N + 1];

		int num = sc.nextInt();
		// 사과 숫자

		for (int i = 0; i < num; i++) {
			int A = sc.nextInt();
			int B = sc.nextInt();

			map[A][B] = 1;

			// 사과 위치 1로 기록해두기
		}

		int num2 = sc.nextInt();
		// 방향 전환 수
		direction = new ArrayList<>();

		for (int i = 0; i < num2; i++) {
			int A = sc.nextInt();
			String str = sc.next();
			int B = 0;
			if (str == "L") {
				B = -1;
			} else {
				B = 1;
			}

			int[] tmp = { A, B };

			direction.add(tmp);
			// 방향을 숫자로 바꿔서 저장
			// -1 이면 왼쪽 1이면 오른쪽으로 방향 전환
		}

		move();

		System.out.println(ans);
	}

	static void move() {
		Queue<int[]> snake_h = new LinkedList<>();
		Queue<int[]> snake_t = new LinkedList<>();

		snake_h.add(new int[] { 1, 1 });
		// 머리 먼저 넣어주기
		snake_t.add(new int[] { 1, 1 });
		// 꼬리 넣어주기

		map[1][1] = -1;

		int d = 0;
		// 현재 방향을 정하는 변수

		int cnt = 0;
		// 시간

		boolean flag = false;
		// 몸이랑 부딪히는거나 벽이라 부딪히는 것 판단용

		while (!flag) {

			System.out.println(cnt);

			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					System.out.print(map[i][j] + " ");
				}
				System.out.println();
			}
			System.out.println();

			cnt++;

			var curr_h = snake_h.poll();
			int snake_hX = curr_h[0];
			int snake_hY = curr_h[1];
			// 현재 뱀 머리 위치

			var curr_t = snake_t.poll();
			int snake_tX = curr_t[0];
			int snake_tY = curr_t[1];
			// 현재 뱀 꼬리 위치

			for (int i = 0; i < direction.size(); i++) {
				if (cnt - 1 == direction.get(i)[0]) {
					if (direction.get(i)[1] == -1) {
						d -= 1;
					} else {
						d += 1;
					}
				}
			}

			if (d < 0) {
				d = 3;
			} else if (d > 3) {
				d = 0;
			}
			// 범위를 초과할 경우 방지용

			int nx = snake_hX + di[d];
			int ny = snake_hY + dj[d];

			if (check(nx, ny)) {
				ans = cnt;
				flag = true;
			} else if (map[nx][ny] == 1) {
				// 사과를 만나는 경우
				map[nx][ny] = -1;
				snake_h.add(new int[] { nx, ny });
				// 머리는 이동한 좌표를 넣어주고
				snake_t.add(curr_t);
				// 꼬리는 그대로이므로 이동 없이 좌표 넣어주기
			} else if (map[nx][ny] == 0) {
				// 아무것도 없는 경우

				int[] tmp = tail_move(curr_t);
				// 꼬리 이동 후 다음 좌표 넣어주기
				snake_t.add(tmp);

				map[nx][ny] = -1;
				snake_h.add(new int[] { nx, ny });
				// 머리 이동한 좌표 넣어주기

			}

		}

	}

	// 벽이나 몸 부딪혔는지 확인용 메서드
	static boolean check(int x, int y) {
		return x < 1 || x > N || y < 1 || y > N || map[x][y] == -1;
	}

	static int[] tail_move(int[] arr) {
		int nx = arr[0];
		int ny = arr[1];
		map[nx][ny] = 0;

		int[] next = new int[2];

		for (int i = 0; i < 4; i++) {
			int X = nx + di[i];
			int Y = ny + dj[i];

			if (map[X][Y] == -1) {
				next[0] = X;
				next[1] = Y;
			}
		}
		return next;
	}

}
