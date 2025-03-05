package algo_0305;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class BOJ6497_전력난 {
    static class Lamp implements Comparable<Lamp>{
        int u,v,weight;
        Lamp(int u, int v, int weight){
            this.u = u;
            this.v = v;
            this.weight = weight;
        }

        @Override
        public int compareTo(Lamp o){
            // 가중치 기준 오름차순
            return Integer.compare(this.weight, o.weight);
        }
    }
    static int m,n;
    static int[]parent;
    static List<Lamp> lamps;
    // 유니온 파인드: find연산(경로 압축)
    static int find(int x){
        if(parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }
    // 유니온 파인드: union연산
    static void union(int a, int b){
        int rootA = find(a);
        int rootB = find(b);
        if(rootA != rootB) parent[rootB] = rootA;
    }
    public static void main(String[] args) {
        // 위험하지 않게 최소한으로 불 켜야하는데(연결해야하는데)
        // 절약할 수 있는 최대 비용을 구하는것이므로
        // 기존 비용 - MST(최소신장트리 합)을 구하면됨!!
        Scanner sc = new Scanner(System.in);

        while(true){

            m = sc.nextInt(); // 집의 수
            n = sc.nextInt(); // 길의 수

            if(m == 0 && n == 0) break; // 종료

            parent = new int[m];
            lamps = new ArrayList<>();
            int total = 0;

            // 부모노드를 자기자신으로 초기화
            // 0번 노드부터!
            for(int i = 0; i <m; i++){
                parent[i] = i;
            }

            // 간선 입력받기
            for(int i = 0; i <n; i++){
                int x = sc.nextInt();
                int y = sc.nextInt();
                int z = sc.nextInt();

                lamps.add(new Lamp(x,y,z));
                total += z; // 기존 비용 합
            }

            // 간선 가중치 기준 오름차순 정렬
            Collections.sort(lamps); // 최소한의 연결비용을 만들기 위해서 오름차순 정렬
            int mst = 0; // c최소 신장 트리 비용
            int cnt = 0; // 선택된 간선 개수


            // 크루스칼 알고리즘
            for(Lamp l : lamps){
                if(find(l.u) != find(l.v)){ // 부모가 같지 않아 연결되지 않은 집이 있다면
                    union(l.u, l.v); // 연결시켜주고
                    mst += l.weight;
                    cnt++;

                    if(cnt == m -1) break; // 이렇게 연결시킨 간선의 수가 m-1이면 다 연결된 것!
                }
            }

            System.out.println(total - mst);

        }
    }
}
