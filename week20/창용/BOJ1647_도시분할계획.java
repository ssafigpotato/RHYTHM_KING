import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 *
 * 최소 스패닝 트리와 크루스칼 알고리즘에 대해 정리할 것.
 *
 * 최소 스패닝 트리 : 연결된 그래프에서 모든 노드를 포함하는 '최소 비용'의 간선들로 이루어진 트리 구조이다.
 *
 * 크루스칼 알고리즘 : 최소 스패닝 트리를 찾는 대표적인 알고리즘으로 그리디 알고리즘 방식으로 동작한다.
 * 과정)
 * 1: 간선 정렬 - 그래프의 모든 간선을 가중치의 오름차순으로 정렬한다. (보통 Comparable을 이용)
 * 2: 사이클 확인 - 유니온파인드 알고리즘을 통해 사이클이 생기지 않도록 확인.
 * 3: 간선을 차례로 선택하면서 트리에 추가.
 * 4: n - 1개의 간선 선택 시 종료: n개의 노드에서는 n - 1 개의 간선을 선택하면 완성이다.
 *
 */

class Edge implements Comparable<Edge>{
    int start;
    int end;
    int weight;

    Edge(int start, int end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge o) {
        return weight - o.weight;
    }
}

public class BOJ1647_도시분할계획 {
    static int[] parent; // 각 노드의 부모를 담을 배열
    static ArrayList<Edge> edgeList; // 각 간선 객체를 담을 리스트

    public static void main(String[] args) throws IOException {

        // 입출력을 위한 라이브러리들
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 집의 개수 N
        int N = Integer.parseInt(st.nextToken());
        // 길의 개수 M
        int M = Integer.parseInt(st.nextToken());

        edgeList = new ArrayList<>();
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            edgeList.add(new Edge(start, end, weight));
        }

        parent = new int[N + 1];
        for(int i = 1; i <= N; i++){
            parent[i] = i;
        }

        Collections.sort(edgeList);

        // 모든 집이 N - 1개의 길로 연결되도록 만듦
        int ans = 0;
        int bigCost = 0;
        for(int i = 0; i < edgeList.size(); i++){
            Edge edge = edgeList.get(i);

            // 두 간선의 가장 위의 조상을 확인하여 무한 사이클이 발생하지 않도록 점검
            if(find(edge.start) != find(edge.end)){
                ans += edge.weight;
                union(edge.start, edge.end);

                // 잘라낼 간선 하나 중 가장 큰 가중치를 가진 간선을 없애기 위해 bigCost 변수에 값을 넣어준다.
                // 오름차순으로 정렬된 것을 탐색하고 있으므로, 가장 마지막의 제일 큰 수치가 bigCost에 저장된다.
                bigCost = edge.weight;
            }
        }

        System.out.println(ans - bigCost);

    }

    public static int find(int x){
        if(x == parent[x]) return x;

        return parent[x] = find(parent[x]);
    }

    public static void union(int x, int y){
        x = find(x);
        y = find(y);

        if(x != y){
            parent[y] = x;
        }
    }
}
