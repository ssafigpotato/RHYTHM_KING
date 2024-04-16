package BOJ_9205_맥주마시면서걸어가기;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	static Queue<int[]> start;
	static Queue<int[]> goal;
	static int end_x, end_y;
	static boolean flag = false;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt(); // 편의점 수

			int start_x = sc.nextInt();
			int start_y = sc.nextInt();

			start = new LinkedList<>();
			goal = new LinkedList<>();

			start.add(new int[] { start_x, start_y });

			for (int i = 0; i < N; i++) {
				int x = sc.nextInt();
				int y = sc.nextInt();

				goal.add(new int[] { x, y });
			}

			end_x = sc.nextInt();
			end_y = sc.nextInt();

			goal.add(new int[] { end_x, end_y });

			flag = false;

			bfs();

			if (flag) {
				System.out.println("happy");
			} else {
				System.out.println("sad");
			}

		}

	}

	static void bfs() {
		while (!start.isEmpty()) {
			var curr = start.poll();

			int x = curr[0];
			int y = curr[1];

			if (x == end_x && y == end_y) {
				flag = true;
				return;
			}

			int num = goal.size();
			for (int i = 0; i < num; i++) {
					var ng = goal.poll();

					int nx = ng[0];
					int ny = ng[1];

					if (Math.abs(x - nx) + Math.abs(y - ny) <= 1000) {
						start.add(new int[] { nx, ny });
					} else {
						goal.add(new int[] { nx, ny });
					}

			}
		}

	}

}
