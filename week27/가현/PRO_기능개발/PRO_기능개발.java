import java.util.*;

/**
     * 풀이 방식:
     * 1. 각 작업이 완료되기까지 필요한 일수를 계산하여 큐에 저장
     * 2. 큐에서 작업을 하나씩 꺼내며, 같은 날 배포 가능한 작업 수를 계산
     * 3. 배포마다 완료된 작업 수를 리스트에 저장
     * 4. 결과 리스트를 배열로 변환하여 반환
*/

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        // 각 작업의 완료까지 필요한 일수를 저장하기 위한 Deque 생성
        Deque<Integer> daysQueue = new ArrayDeque<>();

        // 각 작업의 남은 진행도와 속도를 기반으로 필요한 작업 일수 계산
        for (int i = 0; i < progresses.length; i++) {
            int remainingProgress = 100 - progresses[i]; // 남은 진행도 계산
            int days = (int) Math.ceil((double) remainingProgress / speeds[i]); // 필요한 일수를 올림 처리하여 계산
            daysQueue.add(days); // 계산된 일수를 큐에 추가
        }

        // 배포마다 완료된 작업 수를 저장할 리스트
        List<Integer> result = new ArrayList<>();

        while (!daysQueue.isEmpty()) {
            // 첫 번째 작업의 완료일을 기준으로 배포 진행
            int deployDay = daysQueue.poll(); // 큐에서 첫 번째 작업의 완료일 추출
            int count = 1; // 현재 배포에서 완료될 작업 수 (첫 번째 작업 포함)

            // 큐에서 다음 작업들의 완료일을 확인하며 배포 가능 여부 판단
            while (!daysQueue.isEmpty() && daysQueue.peek() <= deployDay) {
                daysQueue.poll(); // 현재 배포일에 포함되는 작업 제거
                count++; // 완료된 작업 수 증가
            }

            result.add(count); // 이번 배포에서 완료된 작업 수 저장
        }

        // 1. 결과 리스트를 배열로 변환하여 반환
        // result.stream(): result 리스트를 스트림으로 변환
        // mapToInt(Integer::intValue): 각 요소(Integer)를 int로 변환
        // toArray(): 변환된 int 값을 배열로 변환
        // return result.stream().mapToInt(Integer::intValue).toArray();
        
        // 2. 배열 생성 후 각 요소를 복사하는 방식 사용
        int[] answer = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i); // 리스트의 각 요소를 배열로 복사
        }
        return answer;
    }
}
