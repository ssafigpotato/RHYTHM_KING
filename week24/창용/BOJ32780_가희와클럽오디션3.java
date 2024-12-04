import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ32780_가희와클럽오디션3 {
    
    /*
    
    주어지는 lv에 따라 상하좌우, 또는 팔방향 and 역방향 화살표가 등장할 수 있는데,
    가능한 모든 경우의 수를 출력하는 문제
    
    
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int lv = Integer.parseInt(st.nextToken());

        int reverseCnt = 6;

        int num;
        int reverse = 2;

        if(lv <= 5){
            num = 4;
            reverse = 1;
        }else{
            num = 8;
        }

        long result = 1;

        for(int i = 0; i < lv; i++){
            if(reverseCnt == 0){
                result = (result * num);
            }else{
                result = (result * (num * reverse));
                reverseCnt--;
            }

        }

        System.out.println(result % 1000000007);

    }

}