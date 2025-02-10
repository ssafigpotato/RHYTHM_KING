import java.util.*;

public class Main {
    // 📌 [1] 노드 클래스 정의
    // - 다익스트라에서 사용할 Node 객체
    // - 현재 도시(city)와 해당 도시까지의 비용(cost)을 저장
    static class Node implements Comparable<Node> {
        int city, cost;

        public Node(int city, int cost) {
            this.city = city;
            this.cost = cost;
        }

        // 우선순위 큐에서 비용 기준으로 정렬 (오름차순)
        @Override
        public int compareTo(Node other) {
            return this.cost - other.cost;
        }
    }

    // 📌 [2] 전역 변수 선언
    static List<List<Node>> graph = new ArrayList<>(); // 그래프 인접 리스트
    static int[] dist; // 최단 거리 저장 배열
    static final int INF = Integer.MAX_VALUE; // 무한대 값 설정

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 📌 [3] 입력 받기
        int N = sc.nextInt(); // 도시 개수
        int M = sc.nextInt(); // 버스 개수

        // 그래프 초기화 (도시 개수만큼 리스트 생성)
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        // 📌 [4] 버스 노선 정보 입력
        // - 출발 도시(from), 도착 도시(to), 비용(cost)
        for (int i = 0; i < M; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            int cost = sc.nextInt();
            graph.get(from).add(new Node(to, cost));
        }

        // 출발점과 도착점 입력
        int start = sc.nextInt();
        int end = sc.nextInt();

        // 📌 [5] 다익스트라 실행 후 결과 출력
        System.out.println(dijkstra(start, end, N));

        sc.close();
    }

    // 📌 [6] 다익스트라 알고리즘 (우선순위 큐 활용)
    static int dijkstra(int start, int end, int N) {
        PriorityQueue<Node> pq = new PriorityQueue<>(); // 우선순위 큐 (최소 힙)
        dist = new int[N + 1]; // 최단 거리 배열
        Arrays.fill(dist, INF); // 초기값을 무한대로 설정
        dist[start] = 0; // 시작점의 거리는 0

        pq.offer(new Node(start, 0)); // 시작점 추가

        while (!pq.isEmpty()) {
            Node cur = pq.poll(); // 현재 비용이 가장 적은 도시 꺼내기
            int now = cur.city;
            int nowCost = cur.cost;

            // 이미 처리된 노드면 스킵
            if (nowCost > dist[now]) continue;

            // 📌 [7] 인접 도시 확인 및 비용 갱신
            for (Node next : graph.get(now)) {
                int nextCity = next.city;
                int nextCost = nowCost + next.cost;

                // 기존 비용보다 더 저렴하면 갱신 후 큐에 추가
                if (nextCost < dist[nextCity]) {
                    dist[nextCity] = nextCost;
                    pq.offer(new Node(nextCity, nextCost));
                }
            }
        }

        // 📌 [8] 최단 거리 결과 반환
        return dist[end];
    }
}
