package algo_0218;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BOJ1446_지름길 {
    // 지름길 경로가 겹치는 곳에서는 지름길을 연속해서 사용할 수 있음!
    static class Node{
        int start;
        int end;
        int weight;

        Node(int start, int end, int weight){
            this.start = start;
            this.end = end;
            this.weight = weight;
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

        // 초기 가정 - 모든 위치까지는 일단 직진한다고 가정
        for(int i = 0; i <=D; i++){
            distance[i] = i;
        }

        // 0부터 D까지 한칸씩 이동하면서 최단 거리 계산
        for(int i = 0; i <= D; i++){
            // 지름길 없는 도로인 경우
            // 바로 이전 거리에서 +1해서 갱신(직진)
            // 아래 코드처럼 갱신을 안해주면
            // 예를 들어 예제 1번의 경우
            // 50일 때는 지름길 이용해서 최단거리 10나오는데
            // 51일 때는 갱신을 안해줘서 최단거리가 그대로 11이 아닌 51이 나와버림
            // 이렇게 되면 그 후로도 다 꼬임..
            if(i > 0){
                // 직진한거랑 지름길로 온 거랑 거리 비교해서 적은 걸로 갱신
                distance[i] = Math.min(distance[i], distance[i-1] +1 );
            }

            // 지름길의 경우
            for(Node s : list){ // 지름길 중에서
                if(s.start == i){
                    // 현 위치(i)에서 시작하는 지름길이 있다면
                    // 그 지름길의 끝 위치를
                    // 지름길을 사용한 거리 (distance[i] + s.weight) 와
                    // 직진으로 간 거리 (distance[s.end]) 를 비교해서
                    // 더 작은 값으로 갱신
                    distance[s.end] = Math.min(distance[s.end], distance[i] + s.weight );
                }
            }
            // System.out.println(i+"일 때: 0에서부터 최단거리: "+distance[i]);
        }

        System.out.println(distance[D]);

    }
}
