import java.util.HashMap;
import java.util.Map;

class User {
    public String name;
    public Map<String, Integer> gifts;
    public int giftScore;

    public User(String name) {
        this.name = name;
        this.gifts = new HashMap<>();
        this.giftScore = 0;
    }
}

class Solution {
    public int solution(String[] friends, String[] gifts) {
        Map<String, User> users = new HashMap<>();

        for (String userName : friends) {
            User user = new User(userName);

            for (String friendName : friends) {
                user.gifts.put(friendName, 0);
            }

            users.put(userName, user);
        }

        for (String gift : gifts) {
            String[] names = gift.split(" ");
            User sender = users.get(names[0]);
            User receiver = users.get(names[1]);

            sender.gifts.replace(names[1], sender.gifts.get(names[1]) + 1);
            sender.giftScore++;
            receiver.giftScore--;
        }

        int answer = 0;

        for (User user : users.values()) {
            int giftNumber = 0;

            for (String friendName : friends) {
                int sentGiftNumber = user.gifts.get(friendName);
                int receivedGiftNumber = users.get(friendName).gifts.get(user.name);

                if (sentGiftNumber > receivedGiftNumber || sentGiftNumber == receivedGiftNumber && user.giftScore > users.get(friendName).giftScore) {
                    giftNumber++;
                }
            }

            answer = Math.max(answer, giftNumber);
        }

        return answer;
    }
}