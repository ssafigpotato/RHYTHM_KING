package BOJ_1389_케빈베이컨의6단계법칙;

import java.util.Scanner;

public class Main {
	
	/* 플로이드-와샬 알고리즘
	 * 처음에는 다른 방식으로 풀어보려고 했지만 쉽지 않았음
	 * 결국 방법을 찾아보기 시작했고 플로이도-와샬이 정답임을 알게 됨
	 * 그런데 여기서 의문인 부분은 만약에 하나의 점에 2개 이상의 경유를 해야한다면?
	 * 결국 근데 경유지가 2개 이상이라는 것의 의미는 경유지가 1개인 값이 합쳐졌다는 의미이다.
	 * 경유지 1개 거친 값을 2개를 합치면 충분히 갱신 가능하다는 의미.
	 */

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt(); // 사람 수
		int M = sc.nextInt(); // 관계 수

		int[][] arr = new int[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (i == j) {
					arr[i][j] = 0;
				} else {
					arr[i][j] = 100;
				}
			}
		}

		for (int i = 0; i < M; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();

			arr[x][y] = arr[y][x] = 1;
		}

		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					arr[i][j] = Math.min(arr[i][j], arr[i][k] + arr[k][j]);
				}
			}
		}

		int min = Integer.MAX_VALUE;
		int ans = 0;

		for (int i = 1; i <= N; i++) {
			int sum = 0;
			for (int j = 1; j <= N; j++) {
				sum += arr[i][j];
			}
			if (sum < min) {
				min = sum;
				ans = i;
			}

		}
		System.out.println(ans);

	}

}
