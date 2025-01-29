// BR, ST, SB로 통과! 
package algo_0125;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1946_신입사원 {
    static int T;
    static int N;
    static int [][]map; // 서류 기준으로 오름차순 정렬필요
    // 서류 기준으로 높은 사람을 정렬하고, 거기서 면접이 더 높은 사람을 찾으면 됨!!! <-- 결국 서류, 면접 순위 둘 다 높을 수록 좋은 것임!!
    // 처음에는 서류, 면접을 독립적으로 생각해서 문제 이해를 못했음...
    //
    // 1. 기본적으로 key인 서류 순위를 기준으로 오름차순
    // 2. 각 지원자마다 자기보다 서류순위가 높은 사람들 중에서 면접순위가 높은 사람이 없다면 cnt++
    // 예) 1번 지원자: 1 3
    //    2번 지원자: 2 2
    //    3번 지원자: 3 1 이라고 가정
    // 1번 지원자는 자기보다 높은 사람이 없으므로 합격
    // 2번 지원자는 자기보다 높은 순위인 1번 지원자의 면접이 3순위이므로 2 < 3 이라서 합격
    // 3번 지원자는 자기보다 높은 순위인 1번 지원자는 면접이 3순위, 2번 지원자는 2순위, 자기는 1순위 이므로 합격
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());;

        for(int t = 0; t<T; t++){
            N = Integer.parseInt(br.readLine());;
            map = new int[N][2];

            // 1. 서류 순위 기준으로 오름차순 정렬
            for(int i = 0; i <N; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                map[i][0] = Integer.parseInt(st.nextToken());;
                map[i][1] = Integer.parseInt(st.nextToken());;
            }

            Arrays.sort(map, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return Integer.compare(o1[0], o2[0]); // 첫번째 값 기준 비교 오름차순
                }
            });
            // Arrays.sort(map, Comparator.comparingInt(o -> o[0]));
            // Arrays.sort(applicants, (o1, o2) -> Integer.compare(o1[0], o2[0]));

            // 2. 오름차순 된 키(서류 순위)를 돌면서
            // 면접 순위에 따라 합격여부 결정
            int cnt = 0;
            int min = Integer.MAX_VALUE;
            for(int i = 0; i <N; i++){


                // 1. 현재 지원자의 면접순위가 기존의 min값보다 작다면
                // 면접이 높은 순위인 것이므로 cnt++
                // min 값 갱신
                if(map[i][1] < min){
                    // System.out.println("value: "+value+"일 때, 그 전까지 min: "+min+"이므로 합격!");
                    cnt++;
                    min = map[i][1];
                }

            }
            sb.append(cnt).append("\n");

        }

        System.out.println(sb);
    }
}
