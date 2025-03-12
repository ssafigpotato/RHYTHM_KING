package BOJ17135;

import java.util.*;

/*
각 궁수의 위치를 조합으로 담아서
조합에 따른 적 처치 수를 비교하면 될듯...?
 */

public class Main {

    static int N, M, D, count;
    static int[][] map;
    static List<List<Integer>> list;
    static int max = Integer.MIN_VALUE;
    static int[] di = {0, -1, 0, 1};
    static int[] dj = {-1, 0, 1, 0};
    static Queue<int[]> q;


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        D = sc.nextInt(); // 궁수 사거리

        map = new int[N + 1][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        int[] arr = new int[M];
        for (int i = 0; i < M; i++) {
            arr[i] = i;
        }
        boolean[] visited = new boolean[M];

        list = new ArrayList<>();
        combi(arr, visited, 0, 0, 3);
        // 조합시 주의 사항은 반드시 궁수가 3명이라는 것.

        int[][] tmp = new int[N + 1][M];
        for (int i = 0; i < N + 1; i++) {
            for (int j = 0; j < M; j++) {
                tmp[i][j] = map[i][j];

            }
        }

        // 각 조합에 대한 모든 경우 탐색
        for (int i = 0; i < list.size(); i++) {

            count = 0;
            boolean flag = false;

            for (int r = 0; r < N + 1; r++) {
                for (int c = 0; c < M; c++) {
                    map[r][c] = tmp[r][c];
                }
            }

            while (!flag) {

                flag = checkMap();
                // 지도에 적들 여부 확인

                Set<int[]> targets = new HashSet<>();

                for (int j = 0; j < list.get(i).size(); j++) {
                    shoot(N, list.get(i).get(j), targets);
                }

                for (int[] target : targets) {
                    if (map[target[0]][target[1]] == 1) {
                        map[target[0]][target[1]] = 0;
                        count++;
                    }
                }
                move();
            }
            if (max < count) {
                max = count;
            }
        }
        System.out.println(max);
    }

    // 지도 확인하면서 적들이 있는지 없는지 최후 확인용
    static boolean checkMap() {
        boolean flag = true;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1) {
                    flag = false;
                }
            }
        }
        return flag;
    }
    // 궁수가 활을 쏘는 메소드
    static void shoot(int x, int y, Set<int[]> targets) {

        boolean[][] visited = new boolean[N][M];
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y, 0});
        OUT:
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int nx = curr[0];
            int ny = curr[1];
            int distance = curr[2];

            if (distance > D) {
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nx2 = nx + di[i];
                int ny2 = ny + dj[i];
                int distance2 = distance + 1;

                if (check(nx2, ny2, distance2) && !visited[nx2][ny2]) {
                    if (map[nx2][ny2] == 1) {
                        targets.add(new int[]{nx2, ny2});
                        break OUT;
                    } else {
                        q.add(new int[]{nx2, ny2, distance2});
                        visited[nx2][ny2] = true;
                    }
                }
            }
        }
    }
    static boolean check(int x, int y, int d) {
        return x >= 0 && x < N && y >= 0 && y < M && d <= D;
    }

    // 적이 전진하는 메서드
    static void move() {
        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1) {
                    // 범위를 벗어나지 않는 경우
                    if (i + 1 < N) {
                        map[i + 1][j] = 1;
                        map[i][j] = 0;
                    } else {
                        // 범위를 벗어난 경우
                        map[i][j] = 0;
                    }
                }
            }
        }
    }
    static void combi(int[] arr, boolean[] visited, int start, int dp, int r) {
        if (dp == r) {
            List<Integer> li = new ArrayList<>();
            for (int i = 0; i < arr.length; i++) {
                if (visited[i]) {
                    li.add(arr[i]);
                }
            }
            list.add(li);
        }
        for (int i = start; i < arr.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                combi(arr, visited, i + 1, dp + 1, r);
                visited[i] = false;
            }
        }
    }
}