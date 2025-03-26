package algo_0324;

import java.util.Scanner;

public class BOJ2343_기타레슨 {
    static int N,M; // 강의 수, 블루레이 수
    static int[] arr; // 강의 길이 저장
    static int sum = 0; // 강의 길이 합
    static int max = 0; // 가장 긴 강의
    static int binarySearch(){ // 최소크기를 찾는 이분탐색 메소드
        int left = max; // 제일 긴 강의 하나를 담을 수 있어야함
        int right = sum; // 최대크기
        int ans = sum; // 초기값

        while(left <= right){
            int mid = (left + right)/2;

            if(check(mid)){ // 최소크기가 mid일 때 강의 다 나눠짐 -> 조건 만족
                ans = mid; // 일단 정답 후보로 갱신
                right = mid - 1; // 더 작은 후보 탐색해보기
            }else {
                left = mid + 1; // 더 큰 후보 탐색해야함
            }
        }
        return ans;
    }
    static boolean check(int mid){ // 최소크기가 mid일 때 블루레이 수 M개 이하로 강의를 나눌 수 있는가? 여부 메소드
        int cnt = 1; // 사용한 블루레이 수
        int total = 0; // 현재 블루레이에 담긴 강의 길이 합

        for(int i = 0; i <N; i++){ // 강의 전체에 대해서
            if(total + arr[i] > mid){ // 이번에 새로 담은 강의 길이와 지난 강의 길이를 합쳐서 mid를 넘으면
                // 새로운 블루레이를 꺼내고, 새 블루레이에 이번 강의부터 담음 (초기화)
                cnt++;
                total = arr[i];
            }else {
                total += arr[i]; // 현재 블루레이에 강의 추가
            }
        }

        return cnt <= M; // 블루레이 수 M개 이하로 가능하면 true반환 아니면 false
    }
    public static void main(String[] args) {
        // 강의를 길이대로 정렬 (내림차순)하고 하려했는데, 순서가 바뀌면 안되므로 떙
        // 강의가 100,000개, M이 1, 각 강의 길이는 10,000인 경우 블루레이 최대 크기 -> 1,000,000,000(int 가능)
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        arr = new int[N];


        for(int i = 0; i <N; i++){
            int len = sc.nextInt(); // 강의 순인건지 길이 순은 아님!
            arr[i] = len;
            sum += len;
            max = Math.max(max, len);
        }

        System.out.println(binarySearch());

        sc.close();


    }
}
