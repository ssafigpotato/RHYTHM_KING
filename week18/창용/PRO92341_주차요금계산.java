import java.util.*;

class Solution {
    public int[] PRO22114_주차요금계산(int[] fees, String[] records) {

        List<Integer> answer = new ArrayList<>();

        // records를 순회하며 들어오는 차 기록을 담아줄 map1이다.
        Map<Integer, Integer> map1 = new HashMap<>();

        // 들어오고 나간 차를 비교하면서 차 번호와 시간차이를 담아줄 map2이다.
        Map<Integer, Integer> map2 = new HashMap<>();

        // 차 번호를 오름차순으로 정렬해줄 carNum이다.
        List<Integer> carNum = new ArrayList<>();

        int defaultTime = fees[0];
        int defaultMoney = fees[1];
        int unitTime = fees[2];
        int unitMoney = fees[3];

        for(String record: records){
            String[] data = record.split(" ");
            String time = data[0];
            int num = Integer.parseInt(data[1]);
            String inout = data[2];

            String[] times = time.split(":");
            int timePart1 = Integer.parseInt(times[0]) * 60;
            int timePart2 = Integer.parseInt(times[1]);
            Integer realTime1 = timePart1 + timePart2;


            if(inout.equals("IN")){
                // IN이라면 Map 내부에 넣는다.
                map1.put(num, realTime1);
            }else{
                if(map2.containsKey(num)){
                    // Out이라면 Map 내부에 같은 차 번호를 확인하고 시간 차이를 계산해준다.
                    int inTime = map1.get(num);
                    int finalTime = realTime1 - inTime;

                    int exTime = map2.get(num);
                    map2.put(num, exTime + finalTime);
                    map1.remove(num);
                }else{
                    // Out이라면 Map 내부에 같은 차 번호를 확인하고 시간 차이를 계산해준다.
                    int inTime = map1.get(num);
                    int finalTime = realTime1 - inTime;

                    // 계산된 시간을 map2에 넣어주고
                    map2.put(num, finalTime);

                    // 밖으로 나간 차에 대해서는 map1에서 제거해준다.
                    map1.remove(num);
                }
            }
        }

        // 이 상태에서 map1에는 23:59에 나가게 된 차들만이 존재한다.
        // map1에 남아있는 전체 key 목록을 가져와야 한다.

        int lastTime = 23 * 60 + 59;

        // 남아 있는 차 정보들을 가지고 23시 59분까지의 시간을 계산 후 map2에 넣는다.
        for(int key : map1.keySet()){
            // map2에 이미 있다면 put이 아니라 값을 변경해야한다.
            if(map2.containsKey(key)){
                int result = lastTime - map1.get(key);
                int exValue = map2.get(key);
                map2.put(key, result + exValue);
            }else{
                int result = lastTime - map1.get(key);
                map2.put(key, result);
            }

        }

        for(int key : map2.keySet()){
            carNum.add(key);
        }
        Collections.sort(carNum);

        for(int nums : carNum){
            // 차 번호 오름차순으로 가져오는 주차시간
            int parkingTime = map2.get(nums);

            // 계산해준 후, answer 배열에 넣고 반환하면 된다.
            // 1. 기본시간 이하인지 확인한다.
            if(parkingTime <= defaultTime){
                answer.add(defaultMoney);
            }else{
                int targetTime = parkingTime - defaultTime;

                int mok = targetTime / unitTime;

                if(targetTime % unitTime == 0){
                    answer.add(defaultMoney + (mok * unitMoney));
                }else{
                    answer.add(defaultMoney + (mok * unitMoney) + unitMoney);
                }
            }
        }


        int[] answer2 = new int[answer.size()];

        int cnt = 0;

        for(int i : answer){
            answer2[cnt++] = i;
        }

        return answer2;
    }
}
