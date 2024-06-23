package BOJ_1931;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();

		List<int[]> list = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			int start = sc.nextInt();
			int end = sc.nextInt();

			list.add(new int[] { start, end });
		}

		int cnt = 0;
		int time = 0;

		Collections.sort(list, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[1] - o2[1];
			}
		});

		for (int i = 0; i < N; i++) {
			if (time <= list.get(i)[0]) {
				cnt++;
				time = list.get(i)[1];
			}
		}

		System.out.println(cnt);

	}
}
