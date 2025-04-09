

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int T, N;
    static HashMap<String, Integer> friends;  // 이름 -> 고유 ID 매핑
    static int[]parent; // 각 노드의 대표(부모)를 저장하는 배열
    static int[]size; // 집합 크기 ( 각 대표 노드가 속한 집합의 크기를 저장하는 배열)
    // 두 집합을 합치고, 합쳐진 집합의 크기를 반환하는 함수
    static int union(int x, int y){
        int parent_x = find(x);
        int parent_y = find(y);

        if(parent_x != parent_y){
            // 두 집합이 다르면 합친다: y의 대표를 x의 대표로 변경
            parent[parent_y] = parent_x;
            size[parent_x] += size[parent_y]; // 새 대표 집합의 크기 갱신
        }
        return size[find(parent_x)]; // 합쳐진 집합의 크기 반환
    }
    // x가 속한 집합의 대표(부모)를 찾는 함수 (경로 압축 포함)
    static int find(int x){
        if(parent[x] != x){
            parent[x] = find(parent[x]); // 경로 압축
        }
        return parent[x];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 친구관계가 생길 때마다
        // 이번 친구 기준으로 네트워크 탐색 -> dfs -> 시간초과!
        // 유니온파인드로 풀어야함...
        T = Integer.parseInt(br.readLine());
        for(int t = 0; t < T; t++){
            N = Integer.parseInt(br.readLine());
            parent = new int[N*2]; // 최대사람 수는 2명씩 N명이 다 다른사람이면 최대 N*2
            size = new int[N*2];
            friends = new HashMap<>();
            int id = 0; // 새로 등장하는 이름에 부여할 ID

            for(int i = 0; i <N; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                String f1 = st.nextToken();
                String f2 = st.nextToken();

                // f1이 처음 등장한 이름이면 ID 부여 및 초기 설정
                if(!friends.containsKey(f1)){
                    friends.put(f1, id);
                    parent[id] = id; // 자기자신을 부모로 두기
                    size[id] = 1; // id(번인 f1이)가 속한 집합의 크기( 처음엔 본인만 있으므로 크기 1)
                    id++;
                }

                // f2가 처음 등장한 이름이면 ID 부여 및 초기 설정
                if(!friends.containsKey(f2)){
                    friends.put(f2, id);
                    parent[id] = id;
                    size[id] = 1;
                    id++;
                }

                int f1_id = friends.get(f1);
                int f2_id = friends.get(f2);

                // 집합 합치고 크기 출력 ( 두 사람의 네트워크를 합치고, 합쳐진 네트워크 크기 출력)
                System.out.println(union(f1_id, f2_id));

            }

        }
    }
}
