package selim.core.leaderboards;

import org.bukkit.OfflinePlayer;

import selim.core.SignFormat;

public class ScoreboardSignFormat extends SignFormat {

	public ScoreboardSignFormat(String line2, String line3, String line4) {
		super(null, line2, line3, line4);
	}

	public String formatLine1(Score score, int place, String pluginName, String... extra) {
		return format(this.line1, score, place, pluginName, extra);
	}

	public String formatLine2(Score score, int place, String pluginName, String... extra) {
		return format(this.line2, score, place, pluginName, extra);
	}

	public String formatLine3(Score score, int place, String pluginName, String... extra) {
		return format(this.line3, score, place, pluginName, extra);
	}

	public String formatLine4(Score score, int place, String pluginName, String... extra) {
		return format(this.line4, score, place, pluginName, extra);
	}

	private static String format(String line, Score score, int place, String pluginName,
			String... extra) {
		if (score != null) {
			OfflinePlayer player = score.getPlayer();
			int iScore = score.getScore();
			if (player != null)
				line = line.replace("[[PLAYER]]", player.getName());
			else
				line = line.replace("[[PLAYER]]", "");
			line = line.replace("[[SCORE]]", Integer.toString(iScore));
		}
		line = line.replace("[[PLACE]]", getPlaceString(place));
		// line.replace("[[PLUGIN]]", pluginName);
		for (int i = 0; i < extra.length; i++)
			line = line.replace("[[EXTRA" + i + "]]", extra[i]);
		return line.contains("[[") ? "" : line;
	}

	private static String getPlaceString(int place) {
		String placeString = Integer.toString(place);
		switch (place) {
		case 1:
			placeString += "st";
			break;
		case 2:
			placeString += "nd";
			break;
		case 3:
			placeString += "rd";
			break;
		default:
			placeString += "th";
			break;
		}
		return placeString;
	}

}
