package BOJ1722;

import java.util.*;

/*
순열의 순서라는 이름 그대로 순열을 다 만들어 찾으려다 보니 메모리 초과가 발생. 범위가 1에서 20이기 때문에 20!값을 찾으려면 당연한 결과
그렇기 때문에 순열을 다 찾는다기보다는 수학적인 방식으로 접근해서 푸는 문제였다.
예를 들어 N이 4로 주어졌을 때 1,2,3,4라는 값중에서 만들어지는 순열의 순서나 값을 찾아야하는 것이다.
첫번째 숫자가 정해지면 만들 수 있는 값은 3!인 12개이다. 한마디로 주어지는 순서의 값이 12미만이면 첫번째 값이 1로 정해지는 것. 그리고 12이상 24미만이면 2로...
이런식으로 차례대로 값들을 찾으면 된다.
 */


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
