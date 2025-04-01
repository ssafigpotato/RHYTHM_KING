package algo_0401;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class BOJ2531_회전초밥3 {
    static int N; // 회전 초밥 벨트에 놓인 접시의 수
    static int d, k, c; // 초밥 가짓수, 연속해서 먹는 접시 수, 쿠폰 번호
    static int[] sushi;
    static int twoPointer(){
        int []cnt = new int[d+1]; // 각 초밥 종류가 몇 번 등장했는지 기록. 초밥: 1~d번까지
        int curr = 0; // 현재 종류 수

        // 맨 초기 윈도우 설정 (0번부터 k-1번 초밥까지의 종류 세기
        for(int i = 0; i < k; i++){
            if(cnt[sushi[i]] == 0) curr++; // 한번도 등장한적이 없는 경우에만 종류++
            cnt[sushi[i]]++;
        }
        int max = curr; // 현재까지 최고는 0번~k-1번 초밥의 종류개수임
        if(cnt[c] == 0) max++; // 0~k-1번 초밥에 쿠폰 초밥(c)가 없으면 하나 더 먹을 수 있음


        // 슬라이딩 윈도우
        for(int i = 1; i <N; i++){
            int remove = sushi[i-1]; // 맨 왼쪽 초밥을 제거하고
            cnt[remove]--; // 해당 종류의 초밥 수 --
            if(cnt[remove] == 0) curr--; // 해당 초밥이 등장하지 않게 되었으면 현재 종류를 줄여줌

            int add = sushi[(i+k-1) % N]; // 오른쪽 새 초밥 하나 추가 (회전하는걸 고려!!!)
            if(cnt[add] == 0) curr++; // 이전에 없던 새 초밥의 등장이므로 현재 종류 ++
            cnt[add]++;

            // max값과 현재 종류(curr) 비교
            // tmp를 따로 두는 이유?
            // 쿠폰 초밥은 일시적으로 추가되는 초밥이고,
            // curr은 다음 윈도우에도 유지되면서 초밥에 따라 하나씩 빼거나 더해야하므로
            // 쿠폰초밥과 슬라이딩 윈도우로 얻은 curr을 분리해서 연산하기 위해
            // tmp를 따로 둠!
            // 따라서 tmp : 쿠폰 초밥까지 고려한 <이번 턴의 결과>를 위한 임시 값
            int tmp = curr;
            if(cnt[c] == 0) tmp++; // 이번 턴에서 쿠폰초밥이 등장하지 않았다면 tmp++

            max = Math.max(max, tmp);

        }

        return max;

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
