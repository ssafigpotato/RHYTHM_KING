package BOJ23289;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static int[][] ans;
    static int R, C;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        R = sc.nextInt();
        // 세로 길이
        C = sc.nextInt();
        // 가로 길이
        int K = sc.nextInt();
        // 조사하는 칸들이 모두 K 이상이면 종료


        int[][] map = new int[R][C];
        int[][] tmp = new int[R][C];

        List<int[]> check = new ArrayList<>();
        // 확인용 check 배열
        List<int[]> fan = new ArrayList<>();
        // 온풍기 위치 저장

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                map[i][j] = sc.nextInt();
                if (map[i][j] == 5) {

                    // 온도를 체크해야 하는 좌표들 저장
                    check.add(new int[]{i, j});
                } else if (map[i][j] != 0) {

                    // 온풍기 좌표들 저장
                    fan.add(new int[]{i, j, map[i][j]});
                }

                tmp[i][j] = map[i][j];
            }
        }
        /*
        0: 빈 칸
        1: 방향이 오른쪽인 온풍기가 있음
        2: 방향이 왼쪽인 온풍기가 있음
        3: 방향이 위인 온풍기가 있음
        4: 방향이 아래인 온풍기가 있음
        5: 온도를 조사해야 하는 칸

         t가
         0인 경우 (x, y)와 (x-1, y) 사이에 벽이 있는 것이고,
         1인 경우에는 (x, y)와 (x, y+1) 사이에 벽이 있는 것이다.
         */

        int N = sc.nextInt();

        List<int[]> wall = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            int A = sc.nextInt();
            int B = sc.nextInt();
            int T = sc.nextInt();

            wall.add(new int[]{A, B, T});
        }

        /*
        바람이 퍼지는 방식

            3
          4 3
        5 4 3
          4 3
            3

         계속해서 이런 방식으로 퍼진다.
         벽을 감안해서 이제 돌리면 된다.

         */

        /*
        단계별로 구현을 하면 된다.

        1. 온풍기 위치 저장 배열에 따른 바람 생성
        2. tmp 배열에 바람 저장
        3. 모든 온풍기 바람 계산 끝났으면 온도 조절 실행
        4. 가장 바깥쪽 라인 1씩 감소
        5. cnt + 1
        6. 칸들 검사
         */
        ans = new int[R][C];

    }

    static void wind(List<int[]> fan, List<int[]> wall) {
        for (int i = 0; i < fan.size(); i++) {
            int x = fan.get(i)[0];
            int y = fan.get(i)[1];
            int dir = fan.get(i)[2];

            int cnt = 0;
            while (cnt != 6) {

                // 오른쪽
                if (dir == 1) {


                }


            }


        }
    }

    static boolean check(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }

}
