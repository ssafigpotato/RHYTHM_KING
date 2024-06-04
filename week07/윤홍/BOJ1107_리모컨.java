package BOJ_1107;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	static List<Integer> combi;
	static List<Integer> remote;
	static int Goal, ans;
	static int max = Integer.MAX_VALUE;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		Goal = sc.nextInt();

		int Goal_length = String.valueOf(Goal).length();

		int numbers = sc.nextInt();

		remote = new ArrayList<>();
		combi = new ArrayList<>();
		List<Integer> nums = new ArrayList<>();
		for (int i = 0; i <= 9; i++) {
			remote.add(i);
		}

		for (int i = 0; i < numbers; i++) {
			remote.remove(Integer.valueOf(sc.nextInt()));
		}

		for (int i = 1; i <= 6; i++) {
			combination(remote, new int[i], 0, i);
		}

		if (Goal == 100) {
			System.out.println(0);
		} else if (remote.size() == 0) {
			System.out.println(Math.abs(Goal - 100));
		} else {
			if (Math.abs(Goal - 100) <= String.valueOf(ans).length() + Math.abs(Goal - ans)) {
				System.out.println(Math.abs(Goal - 100));
			} else {
				System.out.println(String.valueOf(ans).length() + Math.abs(Goal - ans));
			}
		}
	}

	static void combination(List<Integer> list, int[] out, int dp, int r) {
		if (dp == r) {
			int num = 0;
			for (int i = 0; i < out.length; i++) {
				num += out[i] * Math.pow(10, out.length - i - 1);
			}

			if (Math.abs(Goal - num) < max) {
				max = Math.abs(Goal - num);
				ans = num;
			}

			return;

		}

		for (int i = 0; i < list.size(); i++) {
			out[dp] = list.get(i);
			combination(list, out, dp + 1, r);
		}
	}

}