package challenges;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

/**
 * gakshintala created on 5/10/20.
 */
public class FootBallSelection {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int applicationsRows = Integer.parseInt(bufferedReader.readLine().trim());
        int applicationsColumns = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<String>> applications = new ArrayList<>();

        IntStream.range(0, applicationsRows).forEach(i -> {
            try {
                applications.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        List<List<String>> result = getSelectionStatus(applications);

        result.stream()
                .map(
                        r -> r.stream()
                                .collect(joining(" "))
                )
                .map(r -> r + "\n")
                .collect(toList())
                .forEach(e -> {
                    try {
                        bufferedWriter.write(e);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });

        bufferedReader.close();
        bufferedWriter.close();
    }

    public static List<List<String>> getSelectionStatus(List<List<String>> applications) {
        final List<Player> allPlayers = applications.stream()
                .map(FootBallSelection::toPlayer).collect(Collectors.toList());
        final List<Player> fitPlayers = allPlayers.stream()
                .filter(player -> player.height >= 5.8 && player.bmi <= 23)
                .collect(toList());

        final List<Player> strikersOnly = fitPlayers.stream().filter(player -> player.scores >= 50 && player.defends < 30).collect(toList());
        final List<Player> defendersOnly = fitPlayers.stream().filter(player -> player.scores < 50 && player.defends >= 30).collect(toList());
        final List<Player> both = fitPlayers.stream().filter(player -> player.scores >= 50 && player.defends >= 30).collect(toList());

        if (both.size() % 2 == 1) {
            if (strikersOnly.size() > defendersOnly.size()) {
                both.add(strikersOnly.remove(0));
            } else {
                both.add(defendersOnly.remove(0));
            }
        }
        int minOfBoth = Math.min(strikersOnly.size(), defendersOnly.size());
        final Set<Player> strikersOnlySelected = new HashSet<>(strikersOnly.subList(0, minOfBoth));
        strikersOnlySelected.addAll(both.subList(0, both.size() / 2 + 1));
        final Set<Player> defendersOnlySelected = new HashSet<>(defendersOnly.subList(0, minOfBoth));
        defendersOnlySelected.addAll(both.subList((both.size() / 2) + 1, both.size()));

        return allPlayers.stream().map(player -> {
            if (strikersOnlySelected.contains(player)) {
                return player.toSelectedList("STRIKER");
            } else if (defendersOnlySelected.contains(player)) {
                return player.toSelectedList("DEFENDER");
            } else {
                return player.toRejectedList();
            }
        }).collect(Collectors.toList());
    }

    private static Player toPlayer(List<String> attributes) {
        Player player = new Player();
        player.name = attributes.get(0);
        player.height = Float.parseFloat(attributes.get(1));
        player.bmi = Float.parseFloat(attributes.get(2));
        player.scores = Integer.parseInt(attributes.get(3));
        player.defends = Integer.parseInt(attributes.get(4));
        return player;
    }

}

class Player {
    String name;
    float height;
    float bmi;
    int scores;
    int defends;

    public List<String> toSelectedList(String role) {
        ArrayList<String> list = new ArrayList<String>();
        list.add(name);
        list.add("SELECT");
        list.add(role);
        return list;
    }

    public List<String> toRejectedList() {
        ArrayList<String> list = new ArrayList<String>();
        list.add(name);
        list.add("REJECT NA");
        return list;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;

        Player player = (Player) o;

        if (Float.compare(player.height, height) != 0) return false;
        if (Float.compare(player.bmi, bmi) != 0) return false;
        if (scores != player.scores) return false;
        if (defends != player.defends) return false;
        return name != null ? name.equals(player.name) : player.name == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (height != +0.0f ? Float.floatToIntBits(height) : 0);
        result = 31 * result + (bmi != +0.0f ? Float.floatToIntBits(bmi) : 0);
        result = 31 * result + scores;
        result = 31 * result + defends;
        return result;
    }
}
