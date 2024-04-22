package BOJ_1654_랜선자르기;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int K = sc.nextInt();


		int[] arr = new int[N];
		long sum = 0;
		for (int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
			sum += arr[i];
		}

//		for (int i = 0; i < N; i++) {
//			System.out.println(arr[i] / 186);
//		}

		long end = sum / K;
		long start = 1;
		int cnt = 0;
		long result = 0;
		while (start <= end) {

			long mid = (start + end) / 2;
			cnt = 0;
			for (int i = 0; i < N; i++) {
				cnt += (arr[i] / mid);
			}
//			System.out.println(cnt);
			if (cnt < K) {
				end = mid - 1;
			} else { 
				start = mid + 1;
			}
		}
		System.out.println(start - 1);

	}
}