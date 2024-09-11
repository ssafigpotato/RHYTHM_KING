import java.util.*;

class Solution {

    public int solution(int[] queue1, int[] queue2) {
        int answer = -1;


        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();


        int sum1 = 0;
        int sum2 = 0;

        for(int i = 0; i < queue1.length; i++){
            q1.add(queue1[i]);
            sum1 += queue1[i];
        }

        for(int i = 0; i < queue2.length; i++){
            q2.add(queue2[i]);
            sum2 += queue2[i];
        }


        int cnt = 0;
        boolean flag = false;

        while(!flag){

            cnt++;

            if(sum1 > sum2){


                int x = q1.poll();
                sum1 -= x;

                q2.add(x);
                sum2 += x;




            }else{


                int x = q2.poll();
                sum2 -= x;

                q1.add(x);
                sum1 += x;
            }

            if(sum1 == sum2){
                flag = true;
            }

            if(q1.size() == 0 || q2.size() == 0){
                break;
            }


        }

        if(flag){
            return cnt;
        } else{
            return answer;
        }
    }
}