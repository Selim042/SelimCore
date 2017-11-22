package selim.core.games;

import selim.core.SignFormat;

public class GameSignFormat extends SignFormat {

	public GameSignFormat(String line2, String line3, String line4) {
		super(null, line2, line3, line4);
	}

	public String formatLine1(Game game) {
		return '[' + game.getPlugin().getName() + ']';
	}

	public String formatLine2(Game game) {
		return format(this.line2, game, game.getPlugin().getName(), game.getExtras());
	}

	public String formatLine3(Game game) {
		return format(this.line3, game, game.getPlugin().getName(), game.getExtras());
	}

	public String formatLine4(Game game) {
		return format(this.line4, game, game.getPlugin().getName(), game.getExtras());
	}

	private static String format(String line, Game game, String pluginName, String... extra) {
		if (game == null)
			return line;
		line = line.replace("[[MAP]]", game.getMapName())
				.replace("[[STATUS]]", game.getStatus().getChatColor() + game.getStatus().getText())
				.replace("[[CURRENT_PLAYERS]]", Integer.toString(game.getCurrentPlayers()))
				.replace("[[MAX_PLAYERS]]", Integer.toString(game.getMaxPlayers()));
		for (int i = 0; i < extra.length; i++)
			line = line.replace("[[EXTRA" + i + "]]", extra[i]);
		return line;
	}

}
