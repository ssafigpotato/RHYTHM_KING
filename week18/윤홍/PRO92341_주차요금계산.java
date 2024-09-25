import java.util.*;
import java.text.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {

        List<Long> list = new ArrayList<>();

        int defaultTime = fees[0];
        int defaultFee = fees[1];
        int unitTime = fees[2];
        int unitFee = fees[3];

        HashMap<String, String> map = new HashMap<>();
        HashMap<String, Long> result = new HashMap<>();


        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

        for(int i = 0; i < records.length; i++){
            String[] recordsChange = records[i].split(" ");

            if(recordsChange[2].equals("IN")){
                map.put(recordsChange[1], recordsChange[0]);
            }else{
                // map에서 차량 번호로 들어온 시간 찾기
                String inTime = map.get(recordsChange[1]);
                map.remove(recordsChange[1]);
                // 비교용 나간 시간
                String outTime = recordsChange[0];
                long diff = 0;
                try{
                    Date timeOut = sdf.parse(outTime);
                    Date timeIn = sdf.parse(inTime);

                    long timeMilOut = timeOut.getTime();
                    long timeMilIn = timeIn.getTime();

                    diff = (timeMilOut - timeMilIn) / (1000 * 60);
                }
                catch(ParseException e){
                    e.printStackTrace();
                }


                if(result.containsKey(recordsChange[1])){
                    long now = result.get(recordsChange[1]);
                    now += diff;
                    result.put(recordsChange[1], now);
                } else{
                    result.put(recordsChange[1], diff);
                }
            }

        }


        for( String key : map.keySet()){

            try{
                long num = result.getOrDefault(key, 0L);
                Date timeIn = sdf.parse(map.get(key));
                Date timeOut = sdf.parse("23:59");


                long timeMilOut = timeOut.getTime();
                long timeMilIn = timeIn.getTime();

                long diff = (timeMilOut - timeMilIn) / (1000 * 60);

                num += diff;

                result.put(key, num);
            }
            catch(ParseException e){
                e.printStackTrace();
            }

        }

        List<String> keySet = new ArrayList<>(result.keySet());
        Collections.sort(keySet);

        // int defaultTime = fees[0];
        // int defaultFee = fees[1];
        // int unitTime = fees[2];
        // int unitFee = fees[3];

        int[] answer = new int[result.size()];
        int cnt = -1;
        for(String key : keySet){
            cnt++;
            long num = result.get(key);
            int fee = 0;
            if(num <= defaultTime){
                fee = defaultFee;
            } else{
                fee = defaultFee;
                if((num - defaultTime) / unitTime * unitTime < num - defaultTime){
                    fee += unitFee;

                }
                fee += (num - defaultTime) / unitTime * unitFee;
            }
            answer[cnt] = fee;
        }



        return answer;
    }
}