import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;


/**
 *
 * 주어지는 무게의 수치에 맞게 최대 3개 까지의 물건 조합을 가져올 수 있다면 1을 출력하고,
 * 최대 3개의 물건 조합으로 주어지는 무게를 맞출 수 없다면 0을 출력하는 문제이다.
 *
 * 어라, 3개의 물건을 조합해서 무게를 맞춰야 하면 투포인터가 아니라 쓰리포인터가 아닌가?
 * 쓰리포인터가 원래 가능한건가? 아니면 그냥 조합법을 사용해 1 ~ 3개의 물건 조합으로 주어지는 무게값이 나오는지 확인해야하나?
 * 그러면 이건 투포인터 문제가 아닌데?!
 *
 */


public class BOJ18114_블랙프라이데이 {

    public static void main(String[] args) throws IOException {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());

        // 물건의 갯수 cnt
        int cnt = Integer.parseInt(st.nextToken());

        // 조합으로 맞춰야 하는 무제 weight
        int weight = Integer.parseInt(st.nextToken());

        // 물건들의 무게를 넣어둘 things
        List<Integer> things = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < cnt; i++){
            things.add(Integer.parseInt(st.nextToken()));

            // 요구되어지는 무게와 일치하는 물건이 존재할 경우 바로 1을 출력하고 종료.
            if (things.get(i) == weight){
                System.out.println(1);
                return;
            }
        }

        // List 정렬
        Collections.sort(things);

        // 투포인터 방식으로 사용될 start와 end
        int start = 0;
        int end = cnt - 1;

        // 물건의 무게 총합을 넣어줄 total
        int total;

        while(start < end){

            total = things.get(start) + things.get(end);

            if(total > weight){
                end--;
            }else if(total < weight){

                // 투포인터의 계산에서 추가적으로 요구되는 무게까지 필요한 무게가 존재하는지 확인하여
                // 존재한다면 1을 출력하고 종료.
                if(things.indexOf(weight - total) < end && things.indexOf(weight - total) > start){
                    System.out.println(1);
                    return;
                }

                // 처음에는 중복조건만을 피해주기 위해 start와 end에 해당하는 index와 겹치지 않게만 해주었지만,
                // indexOf 함수는 매개변수로 사용하는 값이 list에 존재하지 않는다면 -1을 반환하기 때문에,
                // 조합을 맞춰줄 물건이 존재하지 않더라도 해당 조건문을 통과해버리게 된다.
//                if(things.indexOf(weight - total) != end && things.indexOf(weight - total) != start){
//                    System.out.println(1);
//                    return;
//                }
                start++;
            }else{
                System.out.println(1);
                return;
            }
        }

        System.out.println(0);

    }
}
