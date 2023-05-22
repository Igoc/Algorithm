import java.util.HashMap;
import java.util.Map;

public class InterestingParty {
    public int bestInvitation(String[] first, String[] second) {
        Map<String, Integer> topics = new HashMap<>();

        for (int index = 0; index < first.length; index++) {
            if (!topics.containsKey(first[index])) {
                topics.put(first[index], 0);
            }

            if (!topics.containsKey(second[index])) {
                topics.put(second[index], 0);
            }

            topics.replace(first[index], topics.get(first[index]) + 1);
            topics.replace(second[index], topics.get(second[index]) + 1);
        }

        int output = 0;

        for (String topic : topics.keySet()) {
            if (topics.get(topic) > output) {
                output = topics.get(topic);
            }
        }

        return output;
    }
}