package algo_0226;

import java.util.Scanner;

public class BOJ17352_여러분의다리가되어드리겠습니다 {
    static int N;
    static int[]parent; // 부모 노드 저장 배열
    /** 1) 경로 압축을 적용한 find연산 -> (부모를 루트로 직접 갱신)
     * 모든 노드를 직접 루트로 변경
     * 최상단 부모를 찾을 때 find(x)를 실행하며 모든 경로를 평탄화(flatten)하여 최적화
     * 다음 find() 호출 시 O(1)에 가깝게 동작하므로 매우 효율적!
     **/
    static int find(int x){
        if(parent[x] != x){
            parent[x] = find(parent[x]); // 경로 압축
        }
        return parent[x];
    }
    /** 2) Union연산 -> 두 집합을 병합
     * */
    static void union(int a, int b){
        int rootA = find(a);
        int rootB = find(b);
        if(rootA != rootB){
            parent[rootB] = rootA; // rootB를 rootA밑으로 병합 -> rootB의 부모를 rootA로 만들어주기
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        parent = new int[N+1]; // 1~N까지

        // 1. 초기화 (각 섬은 자기 자신이 부모)
        // 예제1) parent = {0,1,2,3,4}
        for(int i = 1; i <=N; i++){
            parent[i] = i;
        }

        // 2. N-2개의 다리 정보 입력 받아 유니온 연산 수행
        // 이 다리들은 연결되어 잇으므로
        // 예제1) 이 연산을 수행하고 나면
        // parent = {0,1,1,1,4}가 됨
        for(int i= 0; i <N-2; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            union(a,b); // 두 섬을 같은 집합으로 병합
        }

        // 3. 서로 다른 두 개의 집합(루트가 다른 섬 -> 연결되어있지 않은 섬) 찾기
        // 예제1) 1,2,3은 union연산으로 한 집합이 됨!
        // 그런데 4는 다른 루트를 가지고 있으니
        // a에 첫번째로 발견되는 1의 루트를 넣고, (1의 루트 = 1(자기자신))
        // b에는 1의 루트와는 다른 4의 루트를 저장 (4의 루트 = 4(자기자신))
        int a = -1;
        int b = -1;
        for(int i = 1; i <=N; i++){ // N개의 섬에 대해서
            int root = find(i); // 해당 섬의 루트를 찾아서
            if(a == -1){ // 첫번째로 발견한 루트를 저장!
                a = root;
            }else if(a != root){ // 저장해뒀던 a의 루트와 다른 루트를 발견하면
                b = root; // 그 루트를 b에 저장
                break; // 딱 두개만 하면 되니까 더 할 필요 없음
            }
        }

        System.out.println(a+" "+b);

    }
}
