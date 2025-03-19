package BOJ1722;

import java.util.*;

public class Main {
    static boolean[] visited;
    static long[] fact;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int k = sc.nextInt();

        visited = new boolean[N + 1];
        fact = new long[N + 1];
        factorial(N);

        if (k == 1) { // k가 1이면 num번째 순열 찾기
            long num = sc.nextLong();
            findPermutation(N, num);
        } else { // k가 2이면 주어진 순열의 순서 찾기
            int[] arr = new int[N];
            for (int i = 0; i < N; i++) {
                arr[i] = sc.nextInt();
            }
            findOrder(N, arr);
        }
    }

    static void factorial(int n) {
        fact[0] = 1;
        for (int i = 1; i <= n; i++) {
            fact[i] = fact[i - 1] * i;
        }
    }

    static void findPermutation(int N, long num) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 1; j <= N; j++) {
                if (visited[j]) continue;
                if (num > fact[N - i - 1]) {
                    num -= fact[N - i - 1];
                } else {
                    result.add(j);
                    visited[j] = true;
                    break;
                }
            }
        }
        for (int i : result) {
            System.out.print(i + " ");
        }
    }

    static void findOrder(int N, int[] arr) {
        long order = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 1; j < arr[i]; j++) {
                if (!visited[j]) {
                    order += fact[N - i - 1];
                }
            }
            visited[arr[i]] = true;
        }
        System.out.println(order);
    }
}
