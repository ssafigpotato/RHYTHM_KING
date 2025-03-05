import java.io.*;
import java.util.*;

// 간선을 나타내는 클래스 (Step 1)
class Edge implements Comparable<Edge> {
    int u, v, weight;

    public Edge(int u, int v, int weight) {
        this.u = u;
        this.v = v;
        this.weight = weight;
    }

    // 가중치를 기준으로 정렬하기 위해 compareTo 구현 (Step 2)
    @Override
    public int compareTo(Edge other) {
        return Integer.compare(this.weight, other.weight);
    }
}

public class Main {
    static int[] parent; // 각 노드의 부모를 저장하는 배열 (Union-Find 자료구조) (Step 3)

    // 루트 노드를 찾는 함수 (경로 압축 기법 적용) (Step 4)
    public static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]); // 경로 압축 적용
    }

    // 두 개의 집합을 합치는 함수 (Step 5)
    public static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA != rootB) {
            parent[rootB] = rootA; // 한쪽을 다른 쪽에 합침
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 1. 정점(V)과 간선(E) 개수 입력받기 (Step 6)
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        List<Edge> edges = new ArrayList<>(); // 간선 리스트
        parent = new int[V + 1]; // 부모 배열 초기화
        
        // 2. 부모 배열 초기화: 자기 자신을 부모로 설정 (Step 7)
        for (int i = 1; i <= V; i++) {
            parent[i] = i;
        }

        // 3. 간선 정보 입력받기 (Step 8)
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            edges.add(new Edge(u, v, weight));
        }

        // 4. 간선을 가중치 기준으로 정렬 (Step 9)
        Collections.sort(edges);

        int mstWeight = 0; // 최소 스패닝 트리의 총 가중치 (Step 10)
        int edgeCount = 0; // 선택된 간선의 개수

        // 5. 크루스칼 알고리즘 수행 (Step 11)
        for (Edge edge : edges) {
            // 두 정점이 같은 집합에 속해있지 않다면 선택 (Step 12)
            if (find(edge.u) != find(edge.v)) {
                union(edge.u, edge.v); // 두 정점을 같은 집합으로 병합 (Step 13)
                mstWeight += edge.weight; // 가중치 누적 (Step 14)
                edgeCount++; // 선택한 간선 개수 증가 (Step 15)
            }
            
            // 6. MST는 V-1개의 간선으로 구성됨, 모두 선택되면 종료 (Step 16)
            if (edgeCount == V - 1) break;
        }

        // 7. 최소 스패닝 트리의 가중치 출력 (Step 17)
        System.out.println(mstWeight);
    }
}
