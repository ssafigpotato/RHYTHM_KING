package BOJ1202;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        // 보석 갯수
        int K = sc.nextInt();
        // 가방 갯수

        /*
        문제의 접근 방식은 가방 하나당 하나의 보석만 들어갈 수 있는데, 들어가는 보석에 따른
        가치를 최대로 해야하는 것이다.

        1. 일단은 보석을 List에 담는데, 무게에 대한 오름차순으로 정렬을 한다.
        그 이유는 일단 무게가 가방의 용량 이하여야 가방에 담겨서 실제 가치를 측정 할 수 있기 때문에
        해당 조건에 맞는 편한 구현을 위해 오름 차순으로 정렬한다.
         */

        List<int[]> jewel = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            int A = sc.nextInt();
            int B = sc.nextInt();

            jewel.add(new int[]{A, B});
        }

        Collections.sort(jewel, Comparator.comparingInt(o -> o[0]));

        /*
        2. 이후에 가방 배열을 만들어 오름차순으로 정렬한다.
         */

        int[] bag = new int[K];
        for (int i = 0; i < K; i++) {
            bag[i] = sc.nextInt();
        }

        Arrays.sort(bag);

        /*
        3. 이제 가방에 들어갈 수 있는 모든 보석을 찾아 담는다.
        여기서 중요한 부분은 우선순위 큐에 대한 구현이다. 코드를 보면 알 수 있다.

        먼저, 가방에 대한 for문을 만들어 가방을 기준으로 정답을 계산한다.
        for문 안에 while문이 구현되어 있는데, 이는 보석 배열을 돌며 가방보다 작은 무게를 가진
        보석을 전부 미리 구현해놓은 오름차순 pq에 넣어놓기 위함이다.

        그렇게 while문을 돌고난 후에 pq가 비어있지 않다면 pq의 첫번째 값, 즉 가장 큰 값을 꺼내어
        정답에 더해준다.
         */
        int cnt = 0;
        long ans = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 0; i < K; i++) {
            while (cnt < N && jewel.get(cnt)[0] <= bag[i]) {
                pq.add(jewel.get(cnt)[1]);
                cnt++;
            }

            if (!pq.isEmpty()) {
                ans += pq.poll();
            }
        }

        System.out.println(ans);


    }
}