import java.io.*;
import java.util.HashMap;
import java.util.Map;

class User {
    public String id;
    public int friendNumber;
    public User parent;

    public User(String id) {
        this.id = id;
        this.friendNumber = 1;
        this.parent = this;
    }

    public void addFriend(User user) {
        User leftParent = findParent();
        User rightParent = user.findParent();

        if (leftParent.compareTo(rightParent) < 0) {
            leftParent.friendNumber += rightParent.friendNumber;
            rightParent.parent = leftParent;
        } else if (leftParent.compareTo(rightParent) > 0) {
            leftParent.parent = rightParent;
            rightParent.friendNumber += leftParent.friendNumber;
        }
    }

    public User findParent() {
        if (parent == this) {
            return this;
        }

        return parent = parent.findParent();
    }

    public int compareTo(User user) {
        return id.compareTo(user.id);
    }
}

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int testCaseNumber = Integer.parseInt(in.readLine());

        for (int testCase = 0; testCase < testCaseNumber; testCase++) {
            int F = Integer.parseInt(in.readLine());

            Map<String, User> users = new HashMap<>();

            for (int index = 0; index < F; index++) {
                String[] ids = in.readLine().split(" ");

                if (!users.containsKey(ids[0])) {
                    users.put(ids[0], new User(ids[0]));
                }

                if (!users.containsKey(ids[1])) {
                    users.put(ids[1], new User(ids[1]));
                }

                users.get(ids[0]).addFriend(users.get(ids[1]));

                out.write(users.get(ids[0]).findParent().friendNumber + "\n");
            }
        }

        in.close();
        out.flush();
        out.close();
    }
}