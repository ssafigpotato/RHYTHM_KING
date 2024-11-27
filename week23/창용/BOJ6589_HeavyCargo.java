import java.io.*;
import java.util.*;

public class BOJ6589_HeavyCargo {

    static class Edge {
        String end;
        int capacity;

        Edge(String end, int capacity) {
            this.end = end;
            this.capacity = capacity;
        }
    }

    static Map<String, List<Edge>> graph;
    static Map<String, Integer> capacities;
    static Set<String> visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int scene = 0;
        StringBuilder result = new StringBuilder();

        while (true) {
            scene++;
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());

            if (n == 0 && r == 0) break;

            graph = new HashMap<>();

            // 입력된 도시를 저장하기 위한 집합
            Set<String> citySet = new HashSet<>();

            for (int i = 0; i < r; i++) {
                st = new StringTokenizer(br.readLine());
                String a = st.nextToken();
                String b = st.nextToken();
                int limit = Integer.parseInt(st.nextToken());

                citySet.add(a);
                citySet.add(b);

                graph.computeIfAbsent(a, k -> new ArrayList<>()).add(new Edge(b, limit));
                graph.computeIfAbsent(b, k -> new ArrayList<>()).add(new Edge(a, limit));
            }

            st = new StringTokenizer(br.readLine());
            String start = st.nextToken();
            String end = st.nextToken();

            int maxCapacity = modifiedDijkstra(start, end);

            // 출력 결과를 StringBuilder에 저장
            result.append(String.format("Scenario #%d\n", scene));
            result.append(String.format("%d tons\n", maxCapacity));
            result.append("\n");
        }

        // 마지막 케이스 뒤에 불필요한 개행 제거
        System.out.print(result.toString().stripTrailing());
    }

    public static int modifiedDijkstra(String start, String end) {
        // 우선순위 큐: 현재까지의 최대 최소 용량이 큰 순서대로 탐색
        PriorityQueue<Node> queue = new PriorityQueue<>((a, b) -> b.capacity - a.capacity);
        capacities = new HashMap<>();
        visited = new HashSet<>();

        queue.offer(new Node(start, Integer.MAX_VALUE));
        capacities.put(start, Integer.MAX_VALUE);

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            if (visited.contains(current.name)) continue;
            visited.add(current.name);

            // 목적지에 도달하면 최대 용량 반환
            if (current.name.equals(end)) {
                return capacities.get(end);
            }

            for (Edge edge : graph.getOrDefault(current.name, new ArrayList<>())) {
                int minCapacity = Math.min(capacities.get(current.name), edge.capacity);
                if (!capacities.containsKey(edge.end) || minCapacity > capacities.get(edge.end)) {
                    capacities.put(edge.end, minCapacity);
                    queue.offer(new Node(edge.end, minCapacity));
                }
            }
        }

        // 목적지에 도달할 수 없는 경우 0 반환
        return 0;
    }

    static class Node {
        String name;
        int capacity;

        Node(String name, int capacity) {
            this.name = name;
            this.capacity = capacity;
        }
    }
}
