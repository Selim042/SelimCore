package selim.core.leaderboards;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import selim.core.Helper;
import selim.core.SelimCore;
import selim.core.events.GameTickEvent;

public class ScoreboardManager implements Listener {

	private static final File BOARDS_FOLDER;
	private static final List<Scoreboard> BOARDS = new CopyOnWriteArrayList<Scoreboard>();

	static {
		File temp = SelimCore.getPlugin(SelimCore.class).getDataFolder();
		if (!temp.exists())
			temp.mkdirs();
		temp = new File(temp.getAbsolutePath() + File.separator + "boards");
		if (!temp.exists())
			temp.mkdirs();
		BOARDS_FOLDER = temp;
	}

	@EventHandler
	public void updateScoreBoards(GameTickEvent event) {
		for (Scoreboard sb : BOARDS) {
			Block block = sb.getLocation().getBlock();
			if (block.getType() == Material.WALL_SIGN) {
				ScoreTracker st = ScoreTracker.getTracker(sb.getTrackerID());
				if (st != null && st.hasUpdated())
					sb.update();
			} else {
				BOARDS.remove(sb);
			}
		}
		for (Scoreboard sb : BOARDS) {
			ScoreTracker ts = ScoreTracker.getTracker(sb.getTrackerID());
			if (ts != null)
				ts.setUpdated(false);
		}
	}

	public static void addScoreboard(Scoreboard board) {
		for (Scoreboard b : BOARDS) {
			if (b.getLocation().equals(board.getLocation()))
				BOARDS.remove(b);
		}
		BOARDS.add(board);
	}

	public static void loadScoreboards() {
		for (File file : BOARDS_FOLDER.listFiles(new FilenameFilter() {

			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".board");
			}
		})) {
			if (file == null || !file.exists() || file.isDirectory()) {} else {
				try {
					BufferedReader stream = new BufferedReader(new FileReader(file));
					// String line = stream.readLine();
					String name = file.getName().replaceAll("\\.board", "");
					Location location = Helper.locationFromString(name);
					BlockFace facing = BlockFace.valueOf(stream.readLine());
					String id = stream.readLine();
					int place = Integer.valueOf(stream.readLine());
					Scoreboard board = new Scoreboard(location, facing, id, place);
					BOARDS.add(board);
					stream.close();
				} catch (IOException e) {}
			}
		}
	}

	public static void saveScoreboards() {
		for (Scoreboard board : BOARDS) {
			File file = new File(BOARDS_FOLDER.getAbsolutePath() + File.separator
					+ Helper.locationToString(board.getLocation()) + ".board");
			if (file == null || !file.exists() || file.isDirectory() || board == null) {
				try {
					if (file != null)
						file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
				// return;
			} else {
				try {
					FileOutputStream stream = new FileOutputStream(file);
					writeString(stream, board.getFacing().name() + '\n');
					writeString(stream, board.getTrackerID() + '\n');
					writeString(stream, Integer.toString(board.getPlace()));
					stream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
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
