package selim.core.leaderboards;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import selim.core.SelimCore;

public class ScoreTracker<T extends Comparable<T> & Serializable> {

	private static final File TRACKERS_FOLDER;
	private static final List<ScoreTracker<?>> TRACKERS = new LinkedList<ScoreTracker<?>>();

	private final List<Score<T>> SCORES = new LinkedList<Score<T>>();
	private final String id;
	private final String name;
	private boolean updated = false;

	static {
		File temp = SelimCore.getPlugin(SelimCore.class).getDataFolder();
		if (!temp.exists())
			temp.mkdirs();
		temp = new File(temp.getAbsolutePath() + File.separator + "scores");
		if (!temp.exists())
			temp.mkdirs();
		TRACKERS_FOLDER = temp;
	}

	public ScoreTracker(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public Score<T> getPlace(int place) {
		int truePlace = place - 1;
		if (truePlace < 0 || truePlace >= SCORES.size())
			return null;
		return SCORES.get(truePlace);
	}

	public String getID() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public void sort() {
		SCORES.sort(null);
		this.updated = true;
	}

	public boolean hasUpdated() {
		return this.updated;
	}

	public void setUpdated(boolean updated) {
		this.updated = updated;
	}

	public Score<T> getScore(Player player) {
		for (Score<T> s : SCORES)
			if (s.getPlayer().equals(player))
				return s;
		Score<T> score = new Score<T>(this, player);
		SCORES.add(score);
		this.sort();
		return score;
	}

	public Score<T> setScore(Player player, T data) {
		for (Score<T> s : SCORES) {
			if (s.getPlayer().equals(player)) {
				s.updateScore(data);
				this.sort();
				return s;
			}
		}
		Score<T> score = new Score<T>(this, player, data);
		SCORES.add(score);
		this.sort();
		return score;
	}

	public Score<T> setScore(Score<T> score) {
		for (int i = 0; i < SCORES.size(); i++) {
			if (SCORES.get(i).getPlayer().equals(score.getPlayer())) {
				SCORES.set(i, score);
				this.sort();
				return score;
			}
		}
		SCORES.add(score);
		this.sort();
		return score;
	}

	public static ScoreTracker<?> getTracker(String id) {
		for (ScoreTracker<?> st : TRACKERS)
			if (st.id.equals(id))
				return st;
		return null;
	}

	public static ScoreTracker<?> loadTracker(String id) {
		File file = new File(TRACKERS_FOLDER.getAbsolutePath() + File.separator + id);
		if (file == null || !file.exists() || file.isDirectory())
			return null;
		try {
			BufferedReader stream = new BufferedReader(new FileReader(file));
			String line = stream.readLine();
			@SuppressWarnings("rawtypes")
			ScoreTracker<?> tracker = new ScoreTracker(id, line);
			line = stream.readLine();
			while (line != null && !line.equals("")) {
				tracker.setScore(Bukkit.getPlayer(UUID.fromString(line.substring(0, line.indexOf(':')))),
						line.substring(line.indexOf(':' + 1)));
				PRICES.put(Integer.valueOf(line.substring(0, line.indexOf(':'))),
						Integer.valueOf(line.substring(line.indexOf(':') + 1)));
				line = stream.readLine();
			}
			stream.close();
		} catch (IOException e) {}
	}

	public static void saveTracker(String id) {
		File file = new File(TRACKERS_FOLDER.getAbsolutePath() + File.separator + id);
		if (file == null || !file.exists() || file.isDirectory() || getTracker(id) == null)
			return;
		try {
			FileOutputStream stream = new FileOutputStream(file);
			ScoreTracker<?> tracker = getTracker(id);
			writeString(stream, getTracker(id).name + '\n');
			for (Score<?> s : tracker.SCORES) {
				writeString(stream, s.getPlayer().getUniqueId() + ":");;
				ObjectOutputStream oos = new ObjectOutputStream(stream);
				oos.writeObject(s.getScore());
			}
			stream.close();
		} catch (IOException e) {}
	}

	private static void writeString(FileOutputStream stream, String string) {
		byte[] bytes = string.getBytes();
		try {
			stream.write(bytes);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
