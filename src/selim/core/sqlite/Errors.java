package selim.core.sqlite;

/***
 * Taken and slightly modified from
 * <a href="https://www.spigotmc.org/threads/how-to-sqlite.56847/">SpigotMC</a>.
 */
public class Errors {

	public static String sqlConnectionExecute() {
		return "Couldn't execute MySQL statement: ";
	}

	public static String sqlConnectionClose() {
		return "Failed to close MySQL connection: ";
	}

	public static String noSQLConnection() {
		return "Unable to retreive MYSQL connection: ";
	}

	public static String noTableFound() {
		return "Database Error: No Table Found";
	}

}
