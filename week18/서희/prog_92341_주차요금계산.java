import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        HashMap<Integer, String> inMap = new HashMap<>();
        HashMap<Integer, Integer> feeMap = new HashMap<>();
        List<Integer> carList = new ArrayList<>();
        
        // 요금 계산
        for(int i=0; i<records.length; i++) {
            String[] str = records[i].split(" ");
            int carNum = Integer.parseInt(str[1]);
            
            if(str[2].equals("IN")) {
                inMap.put(carNum, str[0]);
                carList.add(carNum);
            } else {
                String inTime = inMap.get(carNum);
                int fee = calFee(inTime, str[0], fees);
                feeMap.put(carNum, fee);
            }
        }
        
        // 차량번호가 작은 순서대로 정렬
        Collections.sort(carList);
        
        // 차량번호가 작은 순서대로 주차 요금 출력
        int[] answer = new int [carList.size()];
        
        for(int i=0; i<carList.size(); i++) {
            int carNum = carList.get(i);
            
            if(feeMap.containsKey(carNum)) {
                answer[i] = feeMap.get(carNum);
            } else {
                answer[i] = calFee(inMap.get(carNum), "23:59", fees);
            }
        }
        
        return answer;
    }
    
    public int calFee(String in, String out, int[] fees) {
        String[] inTimeArr = in.split(":");
        int inTime = Integer.parseInt(inTimeArr[0])*60 + Integer.parseInt(inTimeArr[1]);
        
        String[] outTimeArr = out.split(":");
        int outTime = Integer.parseInt(outTimeArr[0])*60 + Integer.parseInt(outTimeArr[1]);
        
        int overTime = (outTime - inTime) - fees[0];
        int totalFee = fees[1];
        
        if(overTime > 0) {
            int share = overTime / fees[2];
            int remainder = overTime % fees[2];
            
            if(remainder > 0) {
                share += 1;
            }
            
            totalFee += (share * fees[3]);
        }
        
        return totalFee;
    }
}