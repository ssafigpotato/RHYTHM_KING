import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ22114_창영이의점프 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        int blockNum = Integer.parseInt(st.nextToken());
        int stride = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        List<Integer> blockDist = new ArrayList<>();

        for(int i = 0; i < blockNum - 1; i++){
            blockDist.add(Integer.parseInt(st.nextToken()));
        }

        int startPoint = 0;
        int point = -1;
        int prevUsedIndex = 0; // 이것에 대한 분석 필요
        int answer = 0;
        boolean flag = false;

        while(true){
            point++;
            if(point == blockNum - 1){
                break;
            }

            if(blockDist.get(point) <= stride){

            } else if (blockDist.get(point) > stride && !flag) {
                prevUsedIndex = point + 1;
                flag = true;
            } else{
                startPoint = prevUsedIndex;
                prevUsedIndex = point + 1;
            }


            answer = Math.max(point  - startPoint +2, answer);
        }

        System.out.println(answer);

    }
}