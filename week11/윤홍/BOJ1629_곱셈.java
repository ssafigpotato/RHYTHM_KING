package BOJ_1629;

import java.util.Scanner;

/* 
 * 대표적인 반례 2147483647 2147483647 100001
 */

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int A = sc.nextInt();
		int B = sc.nextInt();
		int C = sc.nextInt();

		/*
		 * A가 C보다 작다면 나머지가 변하는 반대로 A가 C보다 작은 순간부터는 나머지가 일정하게 나온다.
		 */

//		int ans = 0;
//		boolean flag = false;
//
//		List<Integer> list = new ArrayList<>();
//
//		for (int i = 1; i <= B; i++) {
//
//			ans = (int) (Math.pow(A, i) % C);
//
//			if (list.contains(ans)) {
//				if (ans == list.get(list.size() - 1)) {
//					flag = true;
//				}
//				break;
//			} else {
//				list.add(ans);
//			}
//
//		}
//
//		System.out.println(list);
//
//		if (flag) {
//			System.out.println(list.get(list.size() - 1));
//		} else {
//			if (list.size() == B) {
//				System.out.println(list.get(list.size() - 1));
//			} else if (list.size() < B) {
//				System.out.println(list.get((int) (B % list.size()) - 1));
//			} else {
//				System.out.println(list.get(B));
//			}
//		}

		long ans = pow(A, B, C);

		System.out.println(ans);

	}

	static long pow(int a, int b, int c) {

		if (b == 0) {
			return 1;
		} else if (b == 1) {
			return a % c;
		} else if (b % 2 == 0) {
			var res = pow(a, b / 2, c);
			return (res * res) % c;
		} else {
			var res = pow(a, (b - 1) / 2, c);
			return ((res * res) % c * a) % c;
		}

	}

}
