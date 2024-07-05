package BOJ_3190;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
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
			char str = sc.next().charAt(0);
			int B = 0;
			if (str == 'L') {
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

		Deque<int[]> snake = new ArrayDeque<int[]>();

		snake.addFirst(new int[] { 1, 1 }); // 머리 넣어주기
		map[1][1] = -1; // 뱀의 시작위치 -1로 저장해두기

		int cnt = 0; // 이동하는 시간

		int d = 0; // 방향 조정용

		while (true) {

			cnt++;

			for (int i = 0; i < direction.size(); i++) {
				if (cnt - 1 == direction.get(i)[0]) {
					if (direction.get(i)[1] == -1) {
						d -= 1;
					} else if (direction.get(i)[1] == 1) {
						d += 1;
					}
				}
			}

			// 여기서 이제 d가 범위를 0 ~ 3 범위를 벗어나는 것을 방지해주기 위해서 값 조정

			if (d < 0) {
				d = 3;
			} else if (d > 3) {
				d = 0;
			}

			// 여기서부터 이제 뱀의 머리를 이동해준다.
			var curr_head = snake.peekFirst();

			int now_x = curr_head[0];
			int now_y = curr_head[1];

			int nx = now_x + di[d];
			int ny = now_y + dj[d];

			// 종료 조건
			if (check(nx, ny)) {
				ans = cnt;
				break;
			}

			// 사과를 만났을 경우

			if (map[nx][ny] == 1) {
				map[nx][ny] = -1;
				// 지도의 값을 뱀의 몸 값으로 바꿔주고

				snake.addFirst(new int[] { nx, ny }); // 이동한 머리의 좌표도 넣어준다.
				// 그리고 꼬리는 이동 x

				// 아무것도 없는 곳일 경우

			} else if (map[nx][ny] == 0) {
				map[nx][ny] = -1;
				// 머리는 이동하기 때문에 -1로 값 변경

				snake.addFirst(new int[] { nx, ny });
				// 이동한 머리의 좌표 삽입

				var curr_tail = snake.pollLast();

				map[curr_tail[0]][curr_tail[1]] = 0;
				// 꼬리가 당겨지고 원래 있던 꼬리의 위치는 다시 0으로 바꿔주기
			}

		}

	}

	// 범위를 벗어나거나 몸이랑 부딪히면 종료하는 조건
	static boolean check(int x, int y) {
		return x < 1 || x > N || y < 1 || y > N || map[x][y] == -1;
	}

}
