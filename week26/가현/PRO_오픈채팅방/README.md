# 프로그래머스 오픈채팅방

## 1. 문제 요약

카카오톡 오픈채팅방에서는 닉네임을 변경하거나 가상의 닉네임으로 입장할 수 있습니다. 주어진 기록(`record`)에 따라 채팅방 입장, 퇴장, 닉네임 변경 등의 이벤트를 처리한 후, 최종적으로 출력되어야 할 메시지를 반환하는 프로그램을 작성해야 합니다.

**주요 기능**:

- `Enter`: 유저가 채팅방에 입장하며 닉네임 등록/갱신.
- `Leave`: 유저가 채팅방에서 퇴장.
- `Change`: 유저가 닉네임을 변경.

---

## 2. 코드 설명

### 2.1 주요 흐름

1. **닉네임 관리**: `userMap`을 사용해 유저 ID와 닉네임을 매핑하여 관리.
2. **이벤트 기록 저장**: 메시지 기록을 `messages` 리스트에 저장하여 나중에 출력 형식에 맞게 변환.
3. **최종 출력 생성**: 메시지 리스트를 순회하면서 `userMap`에 저장된 닉네임과 이벤트를 결합해 결과 생성.

---

## 3. 코드 상세 설명

### 3.1 유저 정보 저장 및 기록 분석

```java
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
```

- **`Enter`**: 유저가 입장하며 닉네임을 `userMap`에 저장하고 메시지 기록.
- **`Leave`**: 유저가 퇴장하며 해당 이벤트만 메시지 기록에 추가.
- **`Change`**: `userMap`에서 유저 ID에 해당하는 닉네임을 업데이트.

---

### 3.2 결과 메시지 생성

```java
String[] answer = new String[messages.size()];
int index = 0;

for(String[] message : messages){
    String userId = message[0];
    String action = message[1];
    String nickname = userMap.get(userId);
    answer[index++] = nickname + action;
}
```

- `messages` 리스트를 순회하며 각 이벤트에 대응하는 닉네임과 액션을 결합.
- `userMap`을 이용해 현재 유저 ID에 대응하는 닉네임을 가져옴.

---

## 4. 시간 복잡도

### **입력 분석**

- `record`의 최대 길이: \( 100,000 \)

### **시간 복잡도 분석**

1. **닉네임 저장 및 기록 분석**:
   - 각 `record` 처리: \( O(1) \)
   - 전체 반복: \( O(n) \)
2. **결과 메시지 생성**:
   - `messages` 순회: \( O(n) \)

**총 시간 복잡도**: \( O(n) \)

### **공간 복잡도**

- `userMap`: 최대 \( O(n) \)
- `messages`: 최대 \( O(n) \)

**총 공간 복잡도**: \( O(n) \)

---

## 5. 예제

### Input

```text
["Enter uid1234 Muzi", "Enter uid4567 Prodo", "Leave uid1234", "Enter uid1234 Prodo", "Change uid4567 Ryan"]
```

### Output

```text
["Prodo님이 들어왔습니다.", "Ryan님이 들어왔습니다.", "Prodo님이 나갔습니다.", "Prodo님이 들어왔습니다."]
```
