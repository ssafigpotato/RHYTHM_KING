package BOJ1647;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

    static List<Edge>[] graph;

    static class Edge implements Comparable<Edge> {
        int e;
        int w;

        Edge(int e, int w) {
            this.e = e;
            this.w = w;
        }

        @Override
        public int compareTo(Edge e2) {
            return this.w - e2.w;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt(); // 마을 수
        int K = sc.nextInt(); // 간선 수

        graph = new ArrayList[N + 1]; // 마을 수보다 1 큰 배열 만들고
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < K; i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            int cost = sc.nextInt();

            graph[start].add(new Edge(end, cost));
            graph[end].add(new Edge(start, cost));
        }

        prim(1, N);

    }

    static void prim(int start, int n) {
        boolean[] visited = new boolean[n + 1];
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(start, 0));
        int max = Integer.MIN_VALUE;
        int total = 0;

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            int v = edge.e;
            int cost = edge.w;

            if (visited[v]) {
                continue;
            } else {
                visited[v] = true;
                total += cost;
                if (max < cost) {
                    max = cost;
                }
            }

            for (Edge e : graph[v]) {
                if (!visited[e.e]) {
                    pq.add(e);
                }
            }
        }
        total -= max;
        System.out.println(total);
    }
}
