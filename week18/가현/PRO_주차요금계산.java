import java.util.*;

class Solution {
    
    // 주어진 기록을 바탕으로 차량별로 주차 요금을 계산하는 함수
    public int[] solution(int[] fees, String[] records) {
        // 차량별 누적 주차 시간을 저장하는 맵 (차량 번호 -> 총 주차 시간)
        Map<String, Integer> totalTime = new HashMap<>();
        
        // 차량별 입차 시간을 저장하는 맵 (차량 번호 -> 입차 시간)
        Map<String, Integer> inTimes = new HashMap<>();
        
        // 하루의 마지막 시간인 23:59를 분으로 변환한 값 (23시간 * 60분 + 59분 = 1439분)
        int closingTime = 23 * 60 + 59;

        // 입/출차 기록을 순차적으로 처리
        for (int i = 0; i < records.length; i++) {
            // 각 기록을 공백을 기준으로 분리 (시간, 차량 번호, 입/출차 정보)
            String[] details = records[i].split(" ");
            int time = convertToMinutes(details[0]); // 시각을 분으로 변환
            String car = details[1];                 // 차량 번호
            String action = details[2];              // IN 또는 OUT (입차/출차 정보)

            if (action.equals("IN")) {
                // 입차한 경우, 차량 번호에 해당하는 입차 시간을 기록
                inTimes.put(car, time);
            } else {
                // 출차한 경우, 차량 번호에 해당하는 입차 시간을 꺼내고, 입차 기록에서 제거
                int inTime = inTimes.remove(car); 
                
                // 입차 시각과 출차 시각의 차이를 계산하여 총 주차 시간으로 추가
                int parkedTime = time - inTime; 
                
                // 누적 주차 시간을 맵에 저장 (해당 차량 번호의 기존 주차 시간에 더함)
                totalTime.put(car, totalTime.getOrDefault(car, 0) + parkedTime);
            }
        }

        // 출차 기록이 없는 차량 처리 (23:59에 출차된 것으로 간주)
        // 아직 inTimes에 남아 있는 차량들은 출차 기록이 없는 경우임
        for (String car : inTimes.keySet()) {
            int parkedTime = closingTime - inTimes.get(car); // 23:59에서 입차 시간 차감
            totalTime.put(car, totalTime.getOrDefault(car, 0) + parkedTime); // 누적 시간 추가
        }

        // 차량 번호를 오름차순으로 정렬하기 위해 차량 번호를 리스트로 변환
        List<String> cars = new ArrayList<>(totalTime.keySet());
        Collections.sort(cars); // 차량 번호 오름차순 정렬

        // 결과를 담을 배열 (차량 번호 순으로 요금을 저장)
        int[] result = new int[cars.size()];
        
        // 정렬된 차량 번호 순으로 각 차량의 주차 요금을 계산
        for (int i = 0; i < cars.size(); i++) {
            // 차량의 총 주차 시간을 기반으로 요금을 계산하여 result 배열에 저장
            result[i] = calculateFee(fees, totalTime.get(cars.get(i)));
        }

        return result; // 최종 결과 반환 (차량 번호 순으로 요금 배열)
    }

    // 시간 "HH:MM" 형식을 분(minute)으로 변환하는 함수
    // 예: "05:34" -> 5시간 * 60 + 34분 = 334분
    private int convertToMinutes(String time) {
        String[] parts = time.split(":"); // 시각을 ":"로 분리
        int hours = Integer.parseInt(parts[0]); // 시
        int minutes = Integer.parseInt(parts[1]); // 분
        return hours * 60 + minutes; // 시와 분을 합쳐서 분 단위로 반환
    }

    // 주차 요금을 계산하는 함수
    // fees 배열에는 [기본 시간, 기본 요금, 단위 시간, 단위 요금]이 들어있음
    // totalTime은 해당 차량의 총 주차 시간(분)
    private int calculateFee(int[] fees, int totalTime) {
        int basicTime = fees[0];  // 기본 시간 (분)
        int basicFee = fees[1];   // 기본 요금 (원)
        int unitTime = fees[2];   // 단위 시간 (분)
        int unitFee = fees[3];    // 단위 요금 (원)

        // 총 주차 시간이 기본 시간 이하면 기본 요금만 부과
        if (totalTime <= basicTime) {
            return basicFee;
        } else {
            // 총 주차 시간이 기본 시간을 초과하면, 초과 시간에 대해 단위 요금 계산
            int extraTime = totalTime - basicTime; // 초과 시간 계산
            // 초과 시간에 대해 올림 적용 후 단위 시간으로 나눠 단위 요금 계산
            return basicFee + (int) Math.ceil((double) extraTime / unitTime) * unitFee;
        }
    }
}
