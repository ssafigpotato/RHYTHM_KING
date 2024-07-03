package BOJ_15650;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class Main {

	/*
	 * N과 M을 활용해서 순열을 찾는 문제, N까지의 숫자로 M개를 이루는 순열을 중복없이 출력하면 되는 단순한 문제 같다.
	 */

	static List<int[]> list = new ArrayList<>();
	static HashSet<int[]> set = new HashSet<int[]>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int M = sc.nextInt();

		int[] arr = new int[N];

		for (int i = 1; i <= N; i++) {
			arr[i - 1] = i;
		}

		boolean[] visited = new boolean[N];
		int[] out = new int[M];

		permutation(arr, out, visited, 0, M);

	}

	static void permutation(int[] arr, int[] out, boolean[] visited, int dp, int r) {

		if (dp == r) {
//			int[] tmp = new int[r];
//			for (int i = 0; i < r; i++) {
//				for (int j = 0; j < out.length; j++) {
//					if (out[j] != 0) {
//						tmp[i] = out[j];
//					}
//				}
//			}
			boolean flag = true;
			for (int i = out.length - 1; i >= 1; i--) {
				if (out[i] <= out[i - 1]) {
					flag = false;
				}
			}
			if (flag) {
				for (int i = 0; i < out.length; i++) {
					System.out.print(out[i] + " ");
				}
				System.out.println();
			}
			return;
		}

		for (int i = 0; i < arr.length; i++) {
			if (!visited[i]) {
				visited[i] = true;
				out[dp] = arr[i];
				permutation(arr, out, visited, dp + 1, r);
				visited[i] = false;
			}
		}

	}
}
