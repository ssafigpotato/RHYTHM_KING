package algo_0401;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class BOJ2531_회전초밥2 {
    static int N; // 회전 초밥 벨트에 놓인 접시의 수
    static int d, k, c; // 초밥 가짓수, 연속해서 먹는 접시 수, 쿠폰 번호
    static int[] sushi;
    static int twoPointer(){
        int cnt = 0; // 먹은 초밥 개수
        int start = 0;
        int end = start+k-1;
        Set<Integer> set = new HashSet<>();

        while(start<N){ // sushi는 회전하므로 sushi[N-1]가 start이면 sushi[0], sushi[1]... sushi[k-2]를 먹을 수 있음
            // 따라서 N-1까지 가능

            for(int i = start; i <=end; i++){
                set.add(sushi[i % N]); // set에 담아서 중복 제거
                // 회전처리를 위해 i % N 사용
            }

            if(set.contains(c)){ // 쿠폰번호에 해당하는 초밥 있으면 추가 x
                cnt = Math.max(cnt, set.size());
            }else { // 쿠폰번호에 해당하는 초밥 안 포함하고 있으면 그것도 추가로 먹기
                cnt = Math.max(cnt, set.size()+1);
            }

            set.clear();
            start++;
            end++;

        }
        return cnt;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        d = sc.nextInt();
        k = sc.nextInt();
        c = sc.nextInt();
        sushi = new int[N];

        for(int i = 0; i <N; i++){
            sushi[i] = sc.nextInt();
        }

        System.out.println(twoPointer());

    }
}
