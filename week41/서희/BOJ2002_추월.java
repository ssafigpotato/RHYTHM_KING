package boj_2002_추월;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map.Entry;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());	// 차의 개수
		
		HashMap<String, Integer> sequence = new HashMap<String, Integer>();
		for(int i=1; i<=n; i++) {
			sequence.put(br.readLine(), i);		// key = 차량번호, value = 터널에 들어간 순서
		}
		
		int answer  = 0;
		for(int i=1; i<=n; i++) {
			String outCar = br.readLine();
			
			// 아직 터널을 빠져나가지 못한 차량들 중에 나보다 먼저 터널에 들어갔던 차량이 있다면 해당 차량은 추월한 것
			for (Entry<String, Integer> entry : sequence.entrySet()) {
				if(entry.getValue() < sequence.get(outCar)) {
					answer++;
					break;
				}
			}
			
			sequence.remove(outCar);	// 터널을 빠져나갔으므로 제거
		}
		
		System.out.println(answer);
	}
}