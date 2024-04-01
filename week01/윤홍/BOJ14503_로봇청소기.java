package BOJ_14503_로봇청소기;

import java.util.Scanner;

public class Main {

	static int N, M;
	static int[][] map;
	static int[] di = { -1, 0, 1, 0 };
	static int[] dj = { 0, 1, 0, -1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();

		map = new int[N][M];

		int startRow = sc.nextInt();
		int startCol = sc.nextInt();
		int direction = sc.nextInt();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		clean(startRow, startCol, direction);
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] == 2)
					cnt++;
			}
		}
		System.out.println(cnt);

	}

	static void clean(int x, int y, int d) {
		map[x][y] = 2;

		for (int i = 0; i < 4; i++) {
			/*
			 * 4방탐색이지만 반시계 방향으로 90씩 돈다는 조건이 있었다. 원래 설정했던 4방 탐색이 시계방향으로 12, 3, 6, 9였으니까 반대로
			 * 돌기 위한 조건을 내가 직접 설정해줘야한다는걸 깨달음 i와 관계 없이 내가 d의 값을 임의로 모듈화 진행
			 */
			d = (d + 3) % 4;
			int nx = x + di[d];
			int ny = y + dj[d];

			if (check(nx, ny) && map[nx][ny] == 0) {
				map[nx][ny] = 2;
				clean(nx, ny, d);
				return;
			}
		}
		/* 문제는 4방으로 청소를 안한 구역이 없을 경우 즉 위의 조건에 걸리지 않는 경우
		 * 후진을 하며 다시 탐색을 해야한다.
		 * 후진의 경우에는 내가 위에서 구현한 것과 비슷하게 d + 2를 해주면 반대 방향으로 이동하게 된다.
 		 */
		int dir = (d + 2) % 4;
		int bx = x + di[dir];
		int by = y + dj[dir];
		if(check(bx, by) && map[bx][by] != 1) {
			clean(bx, by, d); // 여기서 중요한 점은 후진의 경우에도 앞을 보면서 뒤로 이동한다는 점 
		}
			
		
	}

	static boolean check(int x, int y) {
		return x >= 0 && y >= 0 && x < N && y < M;
	}
}
