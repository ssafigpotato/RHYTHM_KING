package BOJ18405;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

    static int[] di = {-1, 0, 1, 0};
    static int[] dj = {0, -1, 0, 1};
    static PriorityQueue<int[]> pq;
    static int[][] map;
    static int N, answer;

    public static void main(String[] args) {
        /*
        바이러스가 퍼지는데 작은 수부터 퍼진다는 것을 염두해두고 하면된다.
         */
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        // 시험관 정보의 길이
        int K = sc.nextInt();
        // K열은 몇초후의 어디의 결과 값이 어디인지 맞추는 것

        map = new int[N][N];

        pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[3] == o2[3]) {
                    return o1[2] - o2[2];
                } else {
                    return o1[3] - o2[3];
                }
            }
        });

        answer = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        int[] ans = new int[3];
        for (int i = 0; i < 3; i++) {
            ans[i] = sc.nextInt();
        }

        bfs(ans[0]);

        answer = map[ans[1] - 1][ans[2] - 1];
        System.out.println(answer);
    }

    static void bfs(int x) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] != 0) {
                    pq.add(new int[]{i, j, map[i][j], 0});
                }
            }
        }
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int r = curr[0];
            int c = curr[1];
            int num = curr[2];
            int tmp = curr[3];

            if (tmp == x) {
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nr = r + di[i];
                int nc = c + dj[i];
                if (check(nr, nc)) {
                    map[nr][nc] = num;
                    pq.add(new int[]{nr, nc, num, tmp + 1});

                }
            }
        }
    }

    static boolean check(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N && map[x][y] == 0;
    }
}
