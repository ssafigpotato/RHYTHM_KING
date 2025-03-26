package BOJ3079;

import java.util.*;

public class Main {
    public static long minImmigrationTime(int N, int M, int[] times) {
        long left = 1; // 최소 시간
        long right = (long) Arrays.stream(times).max().getAsInt() * M; // 최악의 경우 최대 시간
        long result = right; // 최소 시간 저장 변수

        while (left <= right) {
            long mid = (left + right) / 2; // 중간 시간 설정
            long totalPeople = 0; // mid 시간 동안 심사 가능한 총 인원

            // mid 시간 내에 각 심사대에서 처리 가능한 사람 수 합산
            for (int time : times) {
                totalPeople += mid / time;
                if (totalPeople >= M) break; // 이미 M명을 넘으면 더 계산할 필요 없음
            }

            if (totalPeople >= M) { // M명 이상을 심사할 수 있으면 시간을 줄여본다.
                result = mid;
                right = mid - 1;
            } else { // M명 미만이면 시간을 늘려야 한다.
                left = mid + 1;
            }
        }
        return result; // 최소 시간 반환
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // 심사대 개수
        int M = sc.nextInt(); // 친구 수
        int[] times = new int[N];

        for (int i = 0; i < N; i++) {
            times[i] = sc.nextInt();
        }

        System.out.println(minImmigrationTime(N, M, times));
    }
}
