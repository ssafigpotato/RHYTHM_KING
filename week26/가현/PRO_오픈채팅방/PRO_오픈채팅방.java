import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        
        Map<String, String> userMap = new HashMap<>();
        
        List<String[]> messages = new ArrayList<>();
        
        for(String come : record) {
            String[] part = come.split(" ");
            String command = part[0];
            String userId = part[1];
            
            if(command.equals("Enter")){
                String nickname = part[2];
                userMap.put(userId, nickname);
                messages.add(new String[]{userId, "님이 들어왔습니다."});
            } 
            else if(command.equals("Leave")){
                messages.add(new String[]{userId, "님이 나갔습니다."});
            }
            else if(command.equals("Change")){
                String nickname = part[2];
                userMap.put(userId, nickname);
            }
        }
        
        String[] answer = new String[messages.size()];
        int index = 0;
        
        for(String[] message : messages){
            String userId = message[0];
            String action = message[1];
            String nickname = userMap.get(userId);
            answer[index++] = nickname + action;
        }
        
        return answer;
    }
}