package BOJ_2470_두용액;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);

//		System.out.println(Arrays.toString(arr));

		int start = 0;
		int end = arr.length - 1;
		int mid = 0;
		int min = Integer.MAX_VALUE;
		int ans_x = 0;
		int ans_y = 0;

		while (start < end) {

			mid = arr[start] + arr[end];

			if (min > Math.abs(mid)) {
				min = Math.abs(mid);
				ans_x = arr[start];
				ans_y = arr[end];
//				System.out.println(min);
			}

			if (mid < 0) {
				start++;
			} else if (mid > 0) {
				end--;
			} else {
				break;
			}
//			System.out.println(mid);

		}
		StringBuilder sb = new StringBuilder("");
		sb.append(ans_x);
		sb.append(" ");
		sb.append(ans_y);

		System.out.println(sb);
	}
}
