import java.util.*;
class Solution {
    static Map<String, Integer> parseMap;
    public int[] solution(int[] fees, String[] records) {
        // 출입시간을 기록하기 위한 map
        Map<String, String> map = new HashMap<>();

        parseMap = new TreeMap<>();

        for (int i = 0; i < records.length; i++) {
            String[] tempRecords = records[i].split(" ");
            // 입차라면
            if (tempRecords[2].equals("IN")) {
                map.put(tempRecords[1], tempRecords[0]);
                // 출차라면 입차시간, 출차시간, 차량번호를 parse에 넘김
            } else {
                parse(map.get(tempRecords[1]), tempRecords[0], tempRecords[1]);
                map.remove(tempRecords[1]);
            }
        }

        // map에 남아있는 차량이 있다면
        if (!map.isEmpty()) {
            for (String s : map.keySet()) {
                parse(map.get(s), "23:59", s);
            }
        }

        // 계산 메서드 호출
        cul(fees);
        
        // map에 value를 담아오기 위한 list
        List<Integer> list = new ArrayList<>();
        for (String key : parseMap.keySet()) {
            list.add(parseMap.get(key));
        }
        
        // list를 array로 변환
        int[] answer = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }

        return answer;
    }
    
    // 총 주차 시간을 계산하기 위한 메서드
    static void parse(String in, String out, String carNum) {
        // 출차 시간을 분 단위로 변환
        String[] outTemp = out.split(":");
        int outMin = Integer.parseInt(outTemp[0]) * 60 + Integer.parseInt(outTemp[1]);
        // 입차 시간을 분 단위로 변환
        String[] inTemp = in.split(":");
        int inMin = Integer.parseInt(inTemp[0]) * 60 + Integer.parseInt(inTemp[1]);
        // 총 주차시간
        int dif = outMin - inMin;
        // 주차 시간 업데이트
        parseMap.put(carNum, parseMap.getOrDefault(carNum, 0) + dif);
    }
    
    // 주차 요금을 계산하기 위한 메서드
    static void cul(int[] fees) {
        for (String key : parseMap.keySet()) {
            // 주차시간이 기본 시간보다 작다면
            if (parseMap.get(key) < fees[0]) {
                parseMap.put(key, fees[1]);
            } else {
                // 주차시간에서 기본시간 빼기
                double addTime = parseMap.get(key) - fees[0];
                // 단위 시간으로 나누고 올림처리
                addTime = Math.ceil(addTime / fees[2]);
                // 총 요금
                int result = (int) (addTime * fees[3] + fees[1]);
                // 요금 map에 업데이트
                parseMap.put(key, result);
            }
        }
    }
}