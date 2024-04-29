package BOJ_11053_가장긴증가하는부분수열;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int[] arr = new int[N];
		int[] dp = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
			dp[i] = 1;
		}

		for (int i = 0; i < N; i++) {

			for (int j = 0; j < i; j++) {

				if (arr[i] > arr[j]) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}

			}
		}
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < N; i++) {
			if (max < dp[i]) {
				max = dp[i];
			}
		}

		System.out.println(max);
	}

}
