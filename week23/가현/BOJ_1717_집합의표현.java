/*
 * Union-Find 알고리즘 설명:
 * - Union-Find는 여러 원소가 포함된 집합을 효율적으로 관리하는 자료구조입니다.
 * - 핵심 연산:
 *   1. `find(x)`: x가 속한 집합의 대표(루트)를 찾습니다.
 *      - 경로 압축(Path Compression)을 사용하여 루트를 찾는 동안
 *        방문한 모든 노드의 부모를 루트로 설정합니다.
 *        예: 
 *        초기 상태: parent = [0, 1, 2, 3, 4]
 *        find(3) 호출 → 루트는 1
 *        압축 후: parent = [0, 1, 2, 1, 4]
 *   2. `union(a, b)`: a와 b가 속한 두 집합을 합칩니다.
 *      - 두 집합의 루트를 찾아 하나의 부모 아래에 병합합니다.
 *        예:
 *        union(1, 3) 호출 전: parent = [0, 1, 2, 3, 4]
 *        union(1, 3) 호출 후: parent = [0, 1, 2, 1, 4]
 * 
 * 문제 예시:
 * 입력:
 * 7 8
 * 0 1 3
 * 1 1 7
 * 0 7 6
 * 1 7 1
 * 0 3 7
 * 0 4 2
 * 0 1 1
 * 1 1 1
 * 
 * 처리 과정:
 * 1. 초기 상태에서 각 원소는 독립적인 집합입니다.
 *    parent = [0, 1, 2, 3, 4, 5, 6, 7]
 * 2. `0 1 3`: 1과 3을 같은 집합으로 합칩니다.
 *    결과: parent = [0, 1, 2, 1, 4, 5, 6, 7]
 * 3. `1 1 7`: 1과 7이 같은 집합인지 확인합니다. 결과는 NO입니다.
 * 4. 이후 연산을 처리하며 결과를 출력합니다.
 * 
 * 출력:
 * NO
 * NO
 * YES
 */

import java.io.*;
import java.util.*;

public class Main {
    static int[] parent; // 각 원소의 부모를 저장하는 배열

    /*
     * Find 연산:
     * - 특정 원소가 속한 집합의 대표(루트)를 찾습니다.
     * - 경로 압축(Path Compression)을 사용하여 탐색 경로를 최적화합니다.
     * - parent[x] = find(parent[x]): x가 속한 집합의 루트를 parent[x]에 저장합니다.
     *   이를 통해 다음 호출 시 탐색 시간을 줄입니다.
     */
    static int find(int x) {
        if (parent[x] == x) {
            return x; // x가 자기 자신이면 루트 노드
        }
        // 경로 압축 최적화: x의 부모를 루트 노드로 변경
        return parent[x] = find(parent[x]);
    }

    /*
     * Union 연산:
     * - 두 집합을 합칩니다.
     * - 두 원소의 루트를 찾아 하나의 부모 아래에 연결합니다.
     */
    static void union(int a, int b) {
        int rootA = find(a); // a가 속한 집합의 루트
        int rootB = find(b); // b가 속한 집합의 루트
        if (rootA != rootB) {
            // rootB를 rootA 아래에 연결 (임의의 방식)
            parent[rootB] = rootA;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken()); // 원소의 개수
        int m = Integer.parseInt(st.nextToken()); // 연산의 개수

        // 초기화: 각 원소는 자기 자신이 부모 (자기 자신만 포함하는 집합)
        parent = new int[n + 1]; // 0부터 n까지 사용
        for (int i = 0; i <= n; i++) {
            parent[i] = i; // 초기화: 모든 원소는 자기 자신이 부모
        }

        // m개의 연산을 처리
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken()); // 명령 유형 (0 또는 1)
            int a = Integer.parseInt(st.nextToken()); // 첫 번째 원소
            int b = Integer.parseInt(st.nextToken()); // 두 번째 원소

            if (command == 0) {
                // 0 a b: 합집합 연산
                union(a, b);
            } else if (command == 1) {
                // 1 a b: 같은 집합인지 확인
                if (find(a) == find(b)) {
                    sb.append("YES\n"); // 같은 집합에 속함
                } else {
                    sb.append("NO\n"); // 다른 집합에 속함
                }
            }
        }

        // 결과 출력
        System.out.print(sb);
    }
}
