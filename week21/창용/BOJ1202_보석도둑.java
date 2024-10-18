import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.*;

/**
 *
 * 문제 설명 :
 *
 * 1개 이상의 보석이 주어지며, 보석의 무게와 가격을 알 수 있다.
 * 또한, 1개 이상의 가방이 주어지며, 각 가방이 담을 수 있는 최대 무게를 알 수 있다.
 *
 * 중요한 것은, 가방에는 '1개의 보석만' 담을 수 있다는 것이다.
 * 가방이 1억 톤의 내용물을 담을 수 있어도 1그램의 보석을 넣으면 더 이상 다른 보석을 넣지 못하는 것이다.
 *
 * 보석과 가방의 정보가 주어지면 가방에 넣을 수 있는 보석의 최대 가치를 출력하는 것이 문제의 요구사항이다.
 *
 */

/**
 *
 * 나의 생각 :
 *
 * 뭐야 쉬운데? 아니면 내가 똑똑해져버린걸까??
 *
 * 두 개의 priority queue를 생성하여, 하나의 큐에는 보석을 가격 기준으로 '내림차순' 해주었고,
 * 다른 하나의 큐에는 가방을 용량 기준으로 '오름차순' 해주었다.
 *
 * 가장 비싼 보석부터 가져와서 가장 용량이 작은 가방부터 체크하면서 보석을 넣는다면!
 * 예외 상황 없이, 가방에 넣은 보석들로 최대 가격을 가져올 수 있어!
 *
 * 땡~~ 3%에서 '틀려'버렸다.
 *
 * 엥???? 이거 왜 안됨??
 *
 */

/**
 *
 * 문제 해결법 :
 *
 * 관건은 내 로직은 시간 초과가 발생할 수밖에 없다는 것이다.
 *
 * 주어지는 보석과 가방은 최대 30만개가 주어질 수 있는데, 내 로직을 적용했을 때 최악의 경우 30만 팩토리얼에 해당하는 연산이 실행되어,
 * 절대 문제의 조건에 명시된 시간내로 풀 수 없게 된다.
 *
 * 그렇다면 어떻게 최적화하여 짧은 시간내에 최대 보석 가격을 가져올 수 있도록 가방에 보석을 넣을 수 있을까?
 *
 * 1) 먼저 보석을 '무게' 기준으로 오름차순 정렬한 배열 또는 리스트를 만든다. 단, 무게가 같다면 가격 기준 내림차순 정렬을 한다.
 *
 * -> 오잉? 무게 중심으로 오름차순 정렬하면 최대 가치를 못가져 오지 않나??
 *
 * 2) 다음으로는 가방을 '허용량' 기준으로 오름차순 정렬한다.
 *
 * -> 이부분은 내가 한 것과 같은데 1)은 뭐지....
 *
 * 3) 들어오는 보석 객체에 대해서 보석의 가격 기준으로 내림차순 정렬을 하도록 설정한 priority queue를 생성한다.
 *
 * ------??
 *
 * 이렇게 세 개의 자료구조를 생성 했습니다.
 * 보석을 무게 기준으로 오름차순 정렬한 것,
 * 가방을 허용량 기준으로 오름차순 정렬한 것,
 * 보석 가격 기준으로 내림차순 정렬이 될 priority queue
 *
 * 이제 이들을 이용할 것인데,
 * 제가 사용했던 로직은 보석을 먼저 고르고 그에 맞는 가방을 찾기 시작했지만,
 * 여기서는 다릅니다. 먼저 허용량이 가장 작은 '가방'을 고릅니다.
 *
 * 이후, '해당 가방의 허용량보다 같거나 작은 모든 보석을 생성해둔 priority queue에 넣습니다!!!'
 *
 * 그러면 생성해둔 priority queue에는 고른 가방의 허용량 보다 작은 무게의 보석들이 들어있고, 가격 기준으로 내림차순 되어있습니다.
 * 거기서 가장 높은 가치의 보석을 가져와서 고른 가방에 넣습니다.
 *
 * 엥?? 이게 뭐가 다른건데??
 *
 * 자 상상해봅시다. 제가 처음 시도한 로직은 최악의 경우 30만개의 가방을 전부 체크해야 보석을 넣을 수 있고,
 * 그다음에는 29만 9999번째 가방을 전부체크해야 보석을 넣을 수 있을 가능성이 존재합니다.
 *
 * 그런데 제가 레퍼런스를 조사해서 적용한 로직은 다릅니다.
 * 가장 허용량이 작은 가방에 들어갈 수 있는 모든 보석을 가져오고 그 중 가치가 가장 높은 보석을 넣으면,
 * 나머지 허용량보다 작은 보석들 중에서 선택받지 않은 보석들은 '그대로' priority queue에 남아있습니다.
 *
 * 이후, 다음 가방을 고르고 탐색을 진행할 때, 다음 가방의 허용량보다 가벼운 보석들만을 priority queue에 넣어주기만 하면,
 * 바로 가방에 넣을 보석을 고를 수 있습니다.
 *
 * 이 차이를 알 수 있겠습니까??
 *
 * 쓸데없는 연산 과정이 확 줄어들게 됩니다.
 *
 * 말주변이 없어서 의미가 잘 전달되지 않을 수 있으니 그림을 그려서 추가해보도록 하겠습니다.
 *
 *
 */

/**
 *
 * 내가 문제를 틀린 이후, 스스로 해결하지 못했던 가장 큰 이유는 '틀렸습니다' 판정을 받았기 때문이다.
 *
 * 그러나 레퍼런스를 찾아서 로직을 이해해본 결과 나의 로직은 단지 시간초과가 발생할 뿐, 예외 상황을 만들어내지는 않는 것 같았다.
 *
 * 이게 시간초과가 문제의 오류로 틀렸습니다가 나오는 것인지, 아니면 내가 찾지 못한 예외 상황이 있는 것인지는 모르겠다.
 *
 *
 */

class Jewel {
    int weight;
    int value;

    Jewel(int weight, int value) {
        this.weight = weight;
        this.value = value;
    }

}

public class BOJ1202_보석도둑 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int jewelCnt = Integer.parseInt(st.nextToken());
        int bagCnt = Integer.parseInt(st.nextToken());

        // 보석 배열
        Jewel[] jewels = new Jewel[jewelCnt];

        // 가방 배열
        int[] bags = new int[bagCnt];

        long answer = 0;

        for(int i = 0; i < jewelCnt; i++){
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            jewels[i] = new Jewel(weight, value);
        }

        for(int i = 0; i < bagCnt; i++){
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            bags[i] = weight;
        }

        // 보석 배열을 보석의 무게 기준으로 오름차순 청렬
        Arrays.sort(jewels, new Comparator<Jewel>() {
            @Override
            public int compare(Jewel o1, Jewel o2) {
                if(o1.weight == o2.weight){
                    return o2.value - o1.value;
                }

                return o1.weight - o2.weight;
            }
        });

        // 가방 배열을 가방의 무게 기준으로 오름차순 정렬
        Arrays.sort(bags);

        PriorityQueue<Integer> pq = new PriorityQueue(Comparator.reverseOrder());

        // 보석을 하나씩 가져오기 위한 index
        int j = 0;

        for(int i = 0; i < bagCnt; i++){
            // 가방 가져오기
            int bagWeight = bags[i];

            // outofbounds 방지 및 가방보다 같거나 가벼운 보석들만을 pq에 넣기 위한 while문
            while(j < jewels.length && jewels[j].weight <= bagWeight){
                pq.offer(jewels[j++].value);
            }

            if(!pq.isEmpty()){
                answer += pq.poll();
            }

        }

        System.out.println(answer);

    }
}
