class Solution {
    // 부모 노드를 저장할 배열 선언
    private int[] parent;

    // find 함수: 특정 노드의 루트 부모를 찾는 함수
    // 경로 압축(Path Compression)을 적용하여 트리의 깊이를 줄임
    private int find(int x) {
        // 현재 노드의 부모가 자기 자신이 아니면 (즉, 루트 노드가 아니면)
        if (parent[x] != x) {
            // 재귀적으로 부모 노드를 찾고, 
            // 현재 노드의 부모를 루트 노드로 갱신 (경로 압축)
            parent[x] = find(parent[x]);
        }
        // 최종적으로 루트 노드를 반환
        return parent[x];
    }

    // union 함수: 두 노드를 같은 집합으로 합치는 함수
    private void union(int x, int y) {
        // 각 노드의 루트 부모를 찾음
        int rootX = find(x);
        int rootY = find(y);

        // 두 노드의 루트가 다를 때만 합집합 수행
        if (rootX != rootY) {
            // y의 루트 부모를 x의 루트 부모로 변경하여 같은 집합으로 합침
            parent[rootY] = rootX;
        }
    }

    public int solution(int n, int[][] computers) {
        int answer = 0;
        // 1. parent 배열 초기화
        // 각 컴퓨터를 하나의 집합으로 보고 자기 자신이 부모가 되도록 설정
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        // 2. 네트워크 연결 상태를 확인하여 Union 연산 수행
        // computers 배열을 순회하며 각 컴퓨터의 연결 상태 확인
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // i번 컴퓨터와 j번 컴퓨터가 연결되어 있는 경우
                // 단, 자기 자신과의 연결(i == j)은 제외
                if (i != j && computers[i][j] == 1) {
                    // i와 j를 같은 네트워크 집합으로 합침
                    union(i, j);
                }
            }
        }

        // 3. 네트워크 개수 계산
        // 각 컴퓨터의 루트 부모를 확인하여 네트워크의 수를 셈
        for (int i = 0; i < n; i++) {
            // find를 호출하여 부모 노드를 최신화 (경로 압축)
            // i번 컴퓨터의 루트 부모가 자기 자신인 경우, 하나의 네트워크의 루트 노드임
            if (find(i) == i) {
                // 네트워크 개수 증가
                answer++;
            }
        }

        // 4. 최종적으로 네트워크 개수를 반환
        return answer;
    }
}
