

import java.util.Scanner;

public class Main {
    static Integer[]dp; // null을 사용하기 위해 Integer배열로 설정
    static int N;
    static int recur(int N) {

        if (dp[N] == null) { // 아직 계산되지 않았다면,
            // 6으로 나눠지는 경우
            // N-1, N/3, N/2 중 최솟값을 구하고 + 1
            if (N % 6 == 0) {

                dp[N] = Math.min(recur(N - 1), Math.min(recur(N / 3), recur(N / 2))) + 1;
            }
            // 3으로만 나눠지는 경우
            // N/ 3, N-1 중 최솟값 구하고 + 1
            else if (N % 3 == 0) {
                dp[N] = Math.min(recur(N / 3), recur(N - 1)) + 1;
            }
            // 2로만 나눠지는 경우
            // N/2, N-1 중 최솟값 구하고 + 1
            else if (N % 2 == 0) {
                dp[N] = Math.min(recur(N / 2), recur(N - 1)) + 1;
            }
            // 2와 3으로 나누어지지 않는 경우
            // N-1 하고 + 1
            else {
                dp[N] = recur(N - 1) + 1;
            }
        }
        return dp[N];
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        dp = new Integer[N+1]; // 메모이제이션 배열
        // 각 숫자 N을 1로 만드는 최소 연산 횟수를 저장
        dp[0] = dp[1] = 0;
        // 숫자 1은 이미 1이므로 연산이 필요 없음 

        System.out.println(recur(N));


    }
}
