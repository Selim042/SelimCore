package selim.core.leaderboards;

import org.bukkit.OfflinePlayer;

public class SignFormat {

	// private final String line1;
	private final String line2;
	private final String line3;
	private final String line4;

	public SignFormat(/* String line1, */String line2, String line3, String line4) {
		// this.line1 = line1;
		this.line2 = line2;
		this.line3 = line3;
		this.line4 = line4;
	}

	// public String getLine1Format() {
	// return this.line1;
	// }

	public String getLine2Format() {
		return this.line2;
	}

	public String getLine3Format() {
		return this.line3;
	}

	public String getLine4Format() {
		return this.line4;
	}

	// public String formatLine1(Score score, int place, String pluginName,
	// String... extra) {
	// return format(this.line1, score, place, pluginName, extra);
	// }

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
