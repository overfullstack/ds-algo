package challenges;

import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/** gakshintala created on 5/10/20. */
public class FootBallSelection {
	static void main() throws IOException {
		var bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		var bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		var applicationsRows = Integer.parseInt(bufferedReader.readLine().trim());
		List<List<String>> applications = new ArrayList<>();
		IntStream.range(0, applicationsRows)
				.forEach(
						_ -> {
							try {
								applications.add(
										Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
												.toList());
							} catch (IOException ex) {
								throw new RuntimeException(ex);
							}
						});
		var result = getSelectionStatus(applications);
		result.stream()
				.map(r -> String.join(" ", r))
				.map(r -> r + "\n")
				.forEach(
						e -> {
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
		final var allPlayers = applications.stream().map(FootBallSelection::toPlayer).toList();
		final var fitPlayers =
				allPlayers.stream().filter(player -> player.height >= 5.8 && player.bmi <= 23).toList();
		final var strikersOnly =
				fitPlayers.stream().filter(player -> player.scores >= 50 && player.defends < 30).toList();
		final var defendersOnly =
				fitPlayers.stream().filter(player -> player.scores < 50 && player.defends >= 30).toList();
		final var both =
				fitPlayers.stream()
						.filter(player -> player.scores >= 50 && player.defends >= 30)
						.collect(toList());

		if (both.size() % 2 == 1) {
			if (strikersOnly.size() > defendersOnly.size()) {
				both.add(strikersOnly.removeFirst());
			} else {
				both.add(defendersOnly.removeFirst());
			}
		}
		var minOfBoth = Math.min(strikersOnly.size(), defendersOnly.size());
		final Set<Player> strikersOnlySelected = new HashSet<>(strikersOnly.subList(0, minOfBoth));
		strikersOnlySelected.addAll(both.subList(0, both.size() / 2 + 1));
		final Set<Player> defendersOnlySelected = new HashSet<>(defendersOnly.subList(0, minOfBoth));
		defendersOnlySelected.addAll(both.subList((both.size() / 2) + 1, both.size()));

		return allPlayers.stream()
				.map(
						player -> {
							if (strikersOnlySelected.contains(player)) {
								return player.toSelectedList("STRIKER");
							} else if (defendersOnlySelected.contains(player)) {
								return player.toSelectedList("DEFENDER");
							} else {
								return player.toRejectedList();
							}
						})
				.toList();
	}

	private static Player toPlayer(List<String> attributes) {
		var player = new Player();
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
		var list = new ArrayList<String>();
		list.add(name);
		list.add("SELECT");
		list.add(role);
		return list;
	}

	public List<String> toRejectedList() {
		var list = new ArrayList<String>();
		list.add(name);
		list.add("REJECT NA");
		return list;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Player player)) return false;

		if (Float.compare(player.height, height) != 0) return false;
		if (Float.compare(player.bmi, bmi) != 0) return false;
		if (scores != player.scores) return false;
		if (defends != player.defends) return false;
		return Objects.equals(name, player.name);
	}

	@Override
	public int hashCode() {
		var result = name != null ? name.hashCode() : 0;
		result = 31 * result + (height != +0.0f ? Float.floatToIntBits(height) : 0);
		result = 31 * result + (bmi != +0.0f ? Float.floatToIntBits(bmi) : 0);
		result = 31 * result + scores;
		result = 31 * result + defends;
		return result;
	}
}
