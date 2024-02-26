import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

class Element {
    public int index;
    public int value;

    public Element(int index, int value) {
        this.index = index;
        this.value = value;
    }
}

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(tokenizer.nextToken());
        int L = Integer.parseInt(tokenizer.nextToken());

        tokenizer = new StringTokenizer(in.readLine());

        Deque<Element> deque = new ArrayDeque<>();

        for (int i = 0; i < N; i++) {
            Element element = new Element(i, Integer.parseInt(tokenizer.nextToken()));

            while (!deque.isEmpty() && deque.peekFirst().index < i - L + 1) {
                deque.pollFirst();
            }

            while (!deque.isEmpty() && deque.peekLast().value >= element.value) {
                deque.pollLast();
            }

            deque.offerLast(element);

            out.write(deque.peekFirst().value + " ");
        }

        in.close();
        out.flush();
        out.close();
    }
}