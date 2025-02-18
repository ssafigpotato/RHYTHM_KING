package algo_0218;

import java.util.*;

public class BOJ1446_지름길2 {
    // 지름길 경로가 겹치는 곳에서는 지름길을 연속해서 사용할 수 있음!
    // -> 가 아니라 한 번 타면 끝까지 이용해야하는거고
    // 중간에 갈아탄게 아니라 그냥 그 지름길을 이용하지 않은 것인듯
    static class Node implements Comparable<Node>{
        int start;
        int end;
        int weight;

        Node(int start, int end, int weight){
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o){
            // start기준 오름차순
            // start가 같다면 end 기준 오름차순
            if(this.start == o.start){
                return Integer.compare(this.end, o.end);
            }
            return Integer.compare(this.start, o.start);
        }
    }
    static int N,D;
    static List<Node> list; // 지름길 정보 저장
    static int[]distance; // 최단 거리 저장 배열

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); // 지름길 개수
        D = sc.nextInt(); // 고속도로 길이
        list = new ArrayList<>();
        distance=  new int[D+1]; // 0에서 각 위치(i)까지 가는 최단 거리 저장

        // 지름길 정보 입력
        for(int i = 0; i <N; i++){
            int start = sc.nextInt();
            int end = sc.nextInt();
            int weight = sc.nextInt();

            // 도착지점이 고속도로 끝을 넘으면 의미가 없음
            // D에 딱 도달해야하는데 역주행은 못한다고 했으니까
            if(end > D) continue;

            list.add(new Node(start, end, weight));
        }

        // list 정렬
        Collections.sort(list);

        // distance 배열 초기화
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[0] = 0; // 출발지인 0은 0
        int idx = 0; // 현재 처리중인 지름길 인덱스
        int move = 0; // 현재 이동한 위치

        while(move < D){
            // move가 D가 되면 종료 -> 한 칸씩 탐색
            // 1) 현재 위치(move)에서 시작하는 지름길이 있는지 확인
            if(idx < list.size()){ // idx가 list 사이즈 넘으면 get할 때 에러나니까
                Node path = list.get(idx);

                if(path.start == move) { // 현 위치(move)에서 시작하는 지름길이 있으면
                    // 해당 지름길의 도착지점에
                    // 지름길을 사용했을 때 소요되는 거리와 직진시 거리를 비교해서 더 작은 걸로 갱신
                    distance[path.end] = Math.min(distance[move] + path.weight, distance[path.end] );
                    idx++; // 다음 지름길 확인 -> idx 증가
                } else {// 현 위치에서 시작하는 지름길이 없으면 그냥 직진거리로 갱신
                    distance[move+1] = Math.min(distance[move+1], distance[move] + 1);
                    move++; // 다음 위치로 이동
                }
            } else {
                // 2) 더이상 처리할 지름길이 없다면 직진만 비교
                // 직진했을 때의 거리와 기존 거리 비교 후 최솟값 갱신
                // 기존 거리란?)
                // 지름길을 이용했더라면 지름길과 직진을 비교
                // 아니라면 MAXVALUE와 직진 거리를 비교해서 갱신 하겠지
                distance[move+1] = Math.min(distance[move+1], distance[move] +1 );
                move++;
            }
            System.out.println(move+"일 때: 0으로부터의 최단거리: "+distance[move]);
        }

        System.out.println(distance[D]);

    }
}
