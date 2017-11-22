package selim.core.games;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;

public class GameStatus {

	public static final GameStatus LOADING = new GameStatus("LOADING", DyeColor.ORANGE);
	public static final GameStatus WAITING = new GameStatus("WAITING", DyeColor.LIME, ChatColor.GREEN);
	public static final GameStatus PLAYING = new GameStatus("PLAYING", DyeColor.YELLOW);

	private final String text;
	private final DyeColor blockColor;
	private final ChatColor chatColor;

	public GameStatus(String text) {
		this(text, null, ChatColor.RESET);
	}

	public GameStatus(String text, DyeColor blockColor) {
		this.text = text;
		this.blockColor = blockColor;
		this.chatColor = ChatColor.RESET;
	}

	public GameStatus(String text, ChatColor chatColor) {
		this.text = text;
		this.chatColor = chatColor;
		this.blockColor = null;
	}

	public GameStatus(String text, DyeColor blockColor, ChatColor chatColor) {
		this.text = text;
		this.blockColor = blockColor;
		this.chatColor = chatColor;
	}

	public String getText() {
		return this.text;
	}

	public DyeColor getBlockColor() {
		return this.blockColor;
	}

	public ChatColor getChatColor() {
		return this.chatColor;
	}

	@Override
	public String toString() {
		return "GameStatus:" + this.text;
	}

}
