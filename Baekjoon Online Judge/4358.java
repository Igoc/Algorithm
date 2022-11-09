import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        Map<String, Integer> treeSpeciesCount = new HashMap<>();
        int treeSpeciesTotalCount = 0;

        while (true) {
            String treeSpecies = in.readLine();

            if (treeSpecies == null) {
                break;
            }

            if (!treeSpeciesCount.containsKey(treeSpecies)) {
                treeSpeciesCount.put(treeSpecies, 0);
            }

            treeSpeciesCount.replace(treeSpecies, treeSpeciesCount.get(treeSpecies) + 1);
            treeSpeciesTotalCount++;
        }

        List<String> treeSpeciesList = treeSpeciesCount.keySet().stream()
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());

        for (String treeSpecies : treeSpeciesList) {
            double ratio = (double) treeSpeciesCount.get(treeSpecies) / (double) treeSpeciesTotalCount * 100;

            out.write(treeSpecies + ' ' + String.format("%.4f", ratio) + '\n');
        }

        in.close();
        out.flush();
        out.close();
    }
}