package BOJ14938;

import java.util.Scanner;

public class Main {

    static final int INF = 987654321;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //지역 갯수
        int n = sc.nextInt();
        //수색 가능 범위
        int m = sc.nextInt();
        //길의 갯수
        int r = sc.nextInt();

        // 각 구역에 있는 아이템의 수 (순차적으로 나열)
        int[] itemCnt = new int[n + 1];
        for (int i = 1; i < itemCnt.length; i++) {
            itemCnt[i] = sc.nextInt();
        }

        // 서강 그라운드 맵 완성 (노드용)
        int[][] map = new int[n][n];

        /*
        플로이드워셜 알고리즘을 활용해야 풀수 있는 문제.
        일단 높은 숫자로 박아놓고 (INF)
        입력받은 위치의 값들을 바꿔준다.
        본인에서 본인으로 오는건 0으로 초기화 해주고
        양방향으로 설정
         */

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    map[i][j] = 0;
                } else {
                    map[i][j] = INF;
                }
            }
        }


        for (int i = 0; i < r; i++) {
            int A = sc.nextInt() - 1;
            int B = sc.nextInt() - 1;

            map[A][B] = map[B][A] = sc.nextInt();
        }

        // 여기까지 입력 완료.
        /*
        여기서부터 플로이드워셜 알고리즘 시작
        참고 블로그는 요기 https://sskl660.tistory.com/61
         */

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
                }
            }
        }

//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                System.out.print(map[i][j] + " ");
//            }
//            System.out.println();
//        }

        int max = Integer.MIN_VALUE;
        for(int i = 0; i < n; i++){

            int cnt = 0;

            for(int j = 0; j < n; j++){
                if(map[i][j] <= m){
                    cnt += itemCnt[j + 1];
                }
            }

            if(cnt > max){
                max = cnt;
            }
        }

        System.out.println(max);


    }
}
