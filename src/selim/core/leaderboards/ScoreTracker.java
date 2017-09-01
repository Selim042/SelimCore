package selim.core.leaderboards;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import selim.core.SelimCore;

public class ScoreTracker {

	private static final File TRACKERS_FOLDER;
	private static final List<ScoreTracker> TRACKERS = new LinkedList<ScoreTracker>();

	private final List<Score> SCORES = new LinkedList<Score>();
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

	private ScoreTracker(String id) {
		this(id, null);
	}

	private ScoreTracker(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public Score getPlace(int place) {
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

	public Score getScore(Player player) {
		for (Score s : SCORES)
			if (s.getPlayer() != null && s.getPlayer().getUniqueId().equals(player.getUniqueId()))
				return s;
		Score score = new Score(this, player);
		SCORES.add(score);
		this.sort();
		return score;
	}

	public Score setScore(Player player, int data) {
		for (Score s : SCORES) {
			if (s.getPlayer() != null && s.getPlayer().equals(player)) {
				s.updateScore(data);
				// this.sort();
				return s;
			}
		}
		Score score = new Score(this, player, data);
		SCORES.add(score);
		this.sort();
		return score;
	}

	public Score setScore(Score score) {
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

	public static ScoreTracker getTracker(String id) {
		for (ScoreTracker st : TRACKERS)
			if (st.id.equals(id))
				return st;
		return null;
	}

	public static ScoreTracker getTracker(String id, String name) {
		for (ScoreTracker st : TRACKERS)
			if (st.id.equals(id))
				return st;
		ScoreTracker tracker = new ScoreTracker(id, name);
		TRACKERS.add(tracker);
		return tracker;
	}

	public static List<String> getTrackerIDs() {
		List<String> ids = new LinkedList<String>();
		for (ScoreTracker st : TRACKERS)
			ids.add(st.id);
		return ids;
	}

	public static void loadTrackers() {
		for (File file : TRACKERS_FOLDER.listFiles(new FilenameFilter() {

			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".tracker");
			}
		})) {
			if (file == null || !file.exists() || file.isDirectory()) {} else {
				try {
					BufferedReader stream = new BufferedReader(new FileReader(file));
					String line = stream.readLine();
					String name = file.getName().replaceAll("\\.tracker", "");
					ScoreTracker tracker = new ScoreTracker(name, line);
					line = stream.readLine();
					while (line != null && !line.equals("")) {
						tracker.setScore(
								Bukkit.getPlayer(UUID.fromString(line.substring(0, line.indexOf(':')))),
								Integer.valueOf(line.substring(line.indexOf(':') + 1)));
						line = stream.readLine();
					}
					TRACKERS.add(tracker);
					stream.close();
				} catch (IOException e) {}
			}
		}
	}

	public static void saveTrackers() {
		for (ScoreTracker tracker : TRACKERS) {
			File file = new File(
					TRACKERS_FOLDER.getAbsolutePath() + File.separator + tracker.id + ".tracker");
			if (file == null || !file.exists() || file.isDirectory() || tracker == null) {
				try {
					file.createNewFile();
				} catch (IOException e) {}
			} else {
				try {
					FileOutputStream stream = new FileOutputStream(file);
					writeString(stream, tracker.name + '\n');
					for (Score s : tracker.SCORES) {
						writeString(stream, s.getPlayer().getUniqueId() + ":"
								+ Integer.toString(s.getScore()) + '\n');
					}
					stream.close();
				} catch (IOException e) {}
			}
		}
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
