import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(tokenizer.nextToken());
        int K = Integer.parseInt(tokenizer.nextToken());

        int[] electronicsSequence = Arrays.stream(in.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .map(electronics -> electronics - 1)
                .toArray();

        List<Queue<Integer>> electronicsOrders = new ArrayList<>();

        for (int electronics = 0; electronics < K; electronics++) {
            electronicsOrders.add(new LinkedList<>());
        }

        for (int order = 0; order < K; order++) {
            electronicsOrders.get(electronicsSequence[order]).offer(order);
        }

        int[] powerStrip = new int[N];
        boolean[] pluggedElectronics = new boolean[K];
        int remainingPowerOutletNumber = N;
        int output = 0;

        for (int electronics : electronicsSequence) {
            electronicsOrders.get(electronics).poll();

            if (pluggedElectronics[electronics]) {
                continue;
            }

            if (remainingPowerOutletNumber > 0) {
                powerStrip[N - remainingPowerOutletNumber] = electronics;
                pluggedElectronics[electronics] = true;
                remainingPowerOutletNumber--;
            } else {
                int unpluggedPowerOutlet = 0;
                int unpluggedElectronicsOrder = 0;

                for (int powerOutlet = 0; powerOutlet < N; powerOutlet++) {
                    int currentElectronics = powerStrip[powerOutlet];

                    if (electronicsOrders.get(currentElectronics).isEmpty()) {
                        unpluggedPowerOutlet = powerOutlet;

                        break;
                    }

                    int currentElectronicsOrder = electronicsOrders.get(currentElectronics).peek();

                    if (currentElectronicsOrder > unpluggedElectronicsOrder) {
                        unpluggedPowerOutlet = powerOutlet;
                        unpluggedElectronicsOrder = currentElectronicsOrder;
                    }
                }

                pluggedElectronics[powerStrip[unpluggedPowerOutlet]] = false;
                powerStrip[unpluggedPowerOutlet] = electronics;
                pluggedElectronics[electronics] = true;
                output++;
            }
        }

        out.write(String.valueOf(output));

        in.close();
        out.flush();
        out.close();
    }
}