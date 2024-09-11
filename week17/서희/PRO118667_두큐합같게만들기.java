import java.util.*;

class Solution {
    public int solution(int[] arr1, int[] arr2) {
        long queue1Sum = 0;
        long queue2Sum = 0;
        
        Queue<Integer> queue1 = new LinkedList<>();
        for (int i = 0; i < arr1.length; i++) {
            queue1.add(arr1[i]);
            queue1Sum += arr1[i];
        }
        
        Queue<Integer> queue2 = new LinkedList<>();
        for (int i = 0; i < arr2.length; i++) {
            queue2.add(arr2[i]);
            queue2Sum += arr2[i];
        }
        
        int time = 0;
        int maxOperations = arr1.length * 3; // 최대 작업 횟수

        while (queue1Sum != queue2Sum && time <= maxOperations) {
            time++;
            
            if (queue1Sum < queue2Sum) {
                int temp = queue2.poll();
                queue1.add(temp);
                
                queue2Sum -= temp;
                queue1Sum += temp;
            } else {
                int temp = queue1.poll();
                queue2.add(temp);
                
                queue1Sum -= temp;
                queue2Sum += temp;
            }
        }
        
        // while문이 끝이 났음에도 두 개의 합이 같지 않으면 각 큐의 원소 합을 같게 만들 수 없는 경우
        if (queue1Sum != queue2Sum) {
            return -1;
        }
        
        return time;
    }
}
