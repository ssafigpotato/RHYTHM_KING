    package BOJ17396;

    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStreamReader;
    import java.util.*;

    public class Main {

        static int N, M;
        static ArrayList<ArrayList<Node>> graph;

        static class Node{
            int idx;
            long cost;

            public Node(int idx, long cost){
                this.idx = idx;
                this.cost = cost;
            }
        }

        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            // 분기점의 수
            M = Integer.parseInt(st.nextToken());
            // 분기점을 잇는 선의 수

            // 각 분기점이 적의 시야에 보이는지 여부를 나타내는 수
            Set<Integer> visible = new HashSet<>();
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < N; i++){
                if(Integer.parseInt(st.nextToken()) == 1 && i != N - 1){
                    visible.add(i);
                }
            }

            // 그래프 만들어주기
            graph = new ArrayList<>();
            for(int i = 0; i < N; i++){
                graph.add(new ArrayList<>());
            }

            // 비용 초기화해놓기
            long[] dist = new long[N];
            for(int i = 0; i < N; i++){
                dist[i] = Long.MAX_VALUE;
            }

            // 각 분기점들이 이어져 있는지와 그 길을 가는데 걸리는 시간 (양방향이다)
            for(int i = 0; i < M; i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                // 출발점
                int b = Integer.parseInt(st.nextToken());
                // 도착점
                int c = Integer.parseInt(st.nextToken());
                // 비용
                graph.get(a).add(new Node(b, c));
                graph.get(b).add(new Node(a, c));
            }

            int start = 0;

            PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> Long.compare(o1.cost, o2.cost));
            pq.offer(new Node(start, 0));
            dist[start] = 0;

            while(!pq.isEmpty()){
                Node currNode = pq.poll();

                if(dist[currNode.idx] < currNode.cost){
                    continue;
                }

                for(int i = 0; i < graph.get(currNode.idx).size(); i++){
                    Node nextNode = graph.get(currNode.idx).get(i);

                    if(!visible.contains(nextNode.idx) && dist[nextNode.idx] > currNode.cost + nextNode.cost){
                        dist[nextNode.idx] = currNode.cost + nextNode.cost;
                        pq.offer(new Node(nextNode.idx, dist[nextNode.idx]));
                    }
                }
            }

            if(dist[N - 1] == Long.MAX_VALUE){
                System.out.println(-1);
            } else{
                System.out.println(dist[N - 1]);
            }


        }
    }
