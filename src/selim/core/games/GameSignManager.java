package selim.core.games;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
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
import org.bukkit.event.player.PlayerInteractEvent;

import selim.core.Helper;
import selim.core.SelimCore;
import selim.core.events.GameJoinEvent;
import selim.core.events.GameLeaveEvent;
import selim.core.events.GameStatusChangeEvent;

public class GameSignManager implements Listener {

	private static final File BOARDS_FOLDER;
	private static final List<GameSign> SIGNS = new CopyOnWriteArrayList<GameSign>();

	static {
		File temp = SelimCore.getPlugin(SelimCore.class).getDataFolder();
		if (!temp.exists())
			temp.mkdirs();
		temp = new File(temp.getAbsolutePath() + File.separator + "gamesigns");
		if (!temp.exists())
			temp.mkdirs();
		BOARDS_FOLDER = temp;
	}

	@EventHandler
	public void updateGameSigns(GameStatusChangeEvent event) {
		updateGameSigns(event.getGame());
	}

	@EventHandler
	public void updateGameSigns(GameJoinEvent event) {
		updateGameSigns(event.getGame());
	}

	@EventHandler
	public void updateGameSigns(GameLeaveEvent event) {
		updateGameSigns(event.getGame());
		event.getGame().leaveGame(event.getPlayer());
	}

	@EventHandler
	public void onPlayerClick(PlayerInteractEvent event) {
		Block block = event.getClickedBlock();
		if (block != null && block.getType() == Material.WALL_SIGN) {
			GameSign gs = GameSignManager.getGameSign(block);
			if (gs != null)
				gs.getGame().joinGame(event.getPlayer());
		}
	}

	private static void updateGameSigns(Game game) {
		for (GameSign gs : SIGNS) {
			if (gs == null) {
				SIGNS.remove(gs);
				continue;
			}
			if (game != null && !gs.getGame().equals(game))
				continue;
			Block block = gs.getLocation().getBlock();
			if (block.getType() == Material.WALL_SIGN)
				gs.update();
			else
				SIGNS.remove(gs);
		}
	}

	public static void addGameSign(GameSign gameSign) {
		for (GameSign gs : SIGNS) {
			if (gs.getLocation().equals(gameSign.getLocation())) {
				SIGNS.remove(gs);
				break;
			}
		}
		SIGNS.add(gameSign);
	}

	public static GameSign getGameSign(Block block) {
		if (block == null || block.getType() != Material.WALL_SIGN)
			return null;
		for (GameSign gs : SIGNS)
			if (gs.getLocation().equals(block.getLocation()))
				return gs;
		return null;
	}

	public static void loadGameSigns() {
		for (File file : BOARDS_FOLDER.listFiles(new FilenameFilter() {

			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".gamesign");
			}
		})) {
			if (file == null || !file.exists() || file.isDirectory()) {} else {
				try {
					BufferedReader stream = new BufferedReader(new FileReader(file));
					// String line = stream.readLine();
					String name = file.getName().replaceAll("\\.gamesign", "");
					Location location = Helper.locationFromString(name);
					BlockFace facing = BlockFace.valueOf(stream.readLine());
					String gameId = stream.readLine();
					String mapId = stream.readLine();
					Game game = Game.getGame(gameId, mapId);
					GameSign board = new GameSign(location, facing, game);
					SIGNS.add(board);
					stream.close();
					board.update();
				} catch (IOException e) {}
			}
		}
	}

	public static void saveGameSigns() {
		for (GameSign gs : SIGNS) {
			File file = new File(BOARDS_FOLDER.getAbsolutePath() + File.separator
					+ Helper.locationToString(gs.getLocation()) + ".gamesign");
			if (file == null || !file.exists() || file.isDirectory() || gs == null) {
				try {
					if (file != null) {
						file.createNewFile();
						writeData(file, gs);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				// return;
			} else {
				writeData(file, gs);
			}
		}
	}

	private static void writeData(File file, GameSign gs) {
		try {
			FileOutputStream stream = new FileOutputStream(file);
			writeString(stream, gs.getFacing().name() + '\n');
			writeString(stream, gs.getGame().getId() + '\n');
			writeString(stream, gs.getGame().getMapName());
			stream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
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
