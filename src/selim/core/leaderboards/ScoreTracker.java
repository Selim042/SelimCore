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

import org.bukkit.entity.Player;

import selim.core.SelimCore;

public class ScoreTracker {

	private static final File TRACKERS_FOLDER;
	private static final List<ScoreTracker> TRACKERS = new LinkedList<ScoreTracker>();

	private final List<Score> SCORES = new LinkedList<Score>();
	private final String id;
	private String pluginName;
	private ScoreboardSignFormat format;
	private String[] extras;
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
		this(id, null, null);
	}

	private ScoreTracker(String id, ScoreboardSignFormat format) {
		this(id, null, format);
	}

	private ScoreTracker(String id, String pluginName, ScoreboardSignFormat format, String... extras) {
		this.id = id;
		this.pluginName = pluginName;
		this.format = format;
		this.extras = extras;
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

	public String getPluginName() {
		return this.pluginName;
	}

	public ScoreboardSignFormat getFormat() {
		return this.format;
	}

	public String[] getExtras() {
		return this.extras;
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
		return setScore(player.getUniqueId(), data);
	}

	public Score setScore(UUID uuid, int data) {
		for (Score s : SCORES) {
			if (s.getUUID() != null && s.getUUID().equals(uuid)) {
				s.updateScore(data);
				// this.sort();
				return s;
			}
		}
		Score score = new Score(this, uuid, data);
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

	public static ScoreTracker getTracker(String id, String pluginName, ScoreboardSignFormat format,
			String... extras) {
		for (ScoreTracker st : TRACKERS) {
			if (st.id.equals(id)) {
				if (st.pluginName == null)
					st.pluginName = pluginName;
				if (st.format == null)
					st.format = format;
				if (st.extras == null)
					st.extras = extras;
				return st;
			}
		}
		ScoreTracker tracker = new ScoreTracker(id, pluginName, format, extras);
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
					String id = file.getName().replaceAll("\\.tracker", "");
//					String pluginName = stream.readLine();
//					// String form1 = stream.readLine();
//					String form2 = stream.readLine();
//					String form3 = stream.readLine();
//					String form4 = stream.readLine();
//					int numExtras = Integer.valueOf(stream.readLine());
//					String[] extras = new String[numExtras];
//					for (int i = 0; i < numExtras; i++)
//						extras[i] = stream.readLine();
					String line = stream.readLine();
//					ScoreTracker tracker = new ScoreTracker(id, pluginName,
//							new SignFormat(/* form1, */form2, form3, form4), extras);
					ScoreTracker tracker = new ScoreTracker(id);
					while (line != null && !line.equals("")) {
						tracker.setScore(UUID.fromString(line.substring(0, line.indexOf(':'))),
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
					if (file != null)
						file.createNewFile();
				} catch (IOException e) {}
			} else {
				try {
					FileOutputStream stream = new FileOutputStream(file);
//					writeString(stream, tracker.pluginName + '\n');
					// writeString(stream, tracker.format.getLine1Format() +
					// '\n');
//					writeString(stream, tracker.format.getLine2Format() + '\n');
//					writeString(stream, tracker.format.getLine3Format() + '\n');
//					writeString(stream, tracker.format.getLine4Format() + '\n');
//					writeString(stream, Integer.toString(tracker.extras.length) + '\n');
//					for (int i = 0; i < tracker.extras.length; i++)
//						writeString(stream, tracker.extras[i] + '\n');
					for (Score s : tracker.SCORES)
						if (s.getUUID() != null)
							writeString(stream,
									s.getUUID() + ":" + Integer.toString(s.getScore()) + '\n');
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
