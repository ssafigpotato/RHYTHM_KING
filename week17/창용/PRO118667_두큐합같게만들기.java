import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class PRO118667_두큐합같게만들기 {
     public int solution(int[] queue1, int[] queue2) {
         // 첫 번째 큐를 생성한다.
        List<Integer> que1 = new ArrayList<>();

        // 두 번째 큐를 생성한다.
        List<Integer> que2 = new ArrayList<>();

        // 첫 번째 큐 숫자들의 합을 계산할 sum1를 생성한다.
        int sum1 = 0;

        // 두 번째 큐 숫자들의 합을 계산할 sum2를 생성한다.
        int sum2 = 0;

        // 첫 번째 큐 배열 내부를 순회하면서 리스트에 넣는다.
        for(int i : queue1){
            que1.add(i);
            sum1 += i;
        }

        // 두 번째 큐 배열 내부를 순회하면서 큐2 리스트에 넣는다.
        for(int i : queue2){
            que2.add(i);
            sum2 += i;
        }

        // 큐1 리스트의 길이를 저장한다.
        int one = que1.size();

        // 큐2 리스트의 길이를 저장한다.
        int two = que2.size();

        // 두 리스트의 길이 합을 구한다.
        int sum = one + two;

        // 정답 제출을 위한 변수 cnt를 생성한다.
        int cnt = 0;

        // 두 큐 내부 숫자의 합을을 더했을 때 짝수가 아니라면 두 큐의 합을 같게 만들 수 없으니 바로 -1을 반환한다.
        if((sum1 + sum2) % 2 != 0){
            return -1;
        }

        // 두 큐의 합이 일치할 때까지 반복하는 while문
        while(sum1 != sum2){
            // 만약 첫 번째 큐의 숫자 합이 두 번째 큐의 숫자 합보다 크다면
            if(sum1 > sum2){
                // 첫 번째 큐의 첫 번째 숫자를 가져와서 두 번째 큐의 마지막에 넣는다.
                int num = que1.get(0);
                sum1 -= num;
                sum2 += num;

                que1.remove(0);
                que2.add(num);
            }else{
                // 반대로 두 번째 큐의 숫자합이 두 번째 큐의 숫자 합보다 크다면
                // 두 번째 큐의 첫 번째 숫자를 가져와서 첫 번째 큐의 마지막에 넣는다.
                int num = que2.get(0);
                sum1 += num;
                sum2 -= num;

                que2.remove(0);
                que1.add(num);
            }
            // 한 번 작업할 때마다 카운트해준다.
            cnt++;

            // 두 큐의 총 숫자의 갯수 합의 두배만큼 시행했음에도 두 큐의 합을 같게 만들지 못했다면 실패처리하고 -1을 반환한다.
            // 1 1 8 9 10 1
            // 1 1 1 1 1 1
            if(sum * 2 + 1<= cnt){
                return -1;
            }
        }


        return cnt;
    }
}
