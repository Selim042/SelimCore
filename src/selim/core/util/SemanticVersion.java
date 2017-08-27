package selim.core.util;

public class SemanticVersion implements Comparable<SemanticVersion> {

	private final int MAJOR;
	private final int MINOR;
	private final int PATCH;

	public SemanticVersion(double num) {
		SemanticVersion s = SemanticVersion.valueOf(Double.toString(num));
		MAJOR = s.MAJOR;
		MINOR = s.MINOR;
		PATCH = s.PATCH;
	}

	public SemanticVersion(int major) {
		this(major, 0, 0);
	}

	public SemanticVersion(int major, int minor) {
		this(major, minor, 0);
	}

	public SemanticVersion(int major, int minor, int patch) {
		this.MAJOR = major;
		this.MINOR = minor;
		this.PATCH = patch;
	}

	public int getMajorVersion() {
		return this.MAJOR;
	}

	public int getMinorVersion() {
		return this.MINOR;
	}

	public int getPatchVersion() {
		return this.PATCH;
	}

	public static SemanticVersion valueOf(String in) throws IllegalArgumentException {
		int first, second, third;
		if (in.matches("\\d*\\.\\d*\\.\\d*")) {
			first = Integer.valueOf(in.substring(0, in.indexOf('.')));
			String in2 = in.substring(in.indexOf('.') + 1, in.length());
			second = Integer.valueOf(in2.substring(0, in2.indexOf('.')));
			third = Integer.valueOf(in2.substring(in2.indexOf('.') + 1));
			return new SemanticVersion(first, second, third);
		} else {
			if (in.matches("\\d*\\.\\d*")) {
				first = Integer.valueOf(in.substring(0, in.indexOf('.')));
				second = Integer.valueOf(in.substring(in.indexOf('.') + 1));
				return new SemanticVersion(first, second);
			} else {
				if (in.matches("\\d*")) {
					first = Integer.valueOf(in);
					return new SemanticVersion(first);
				} else
					throw new IllegalArgumentException(in + " is not valid");
			}
		}
	}

	@Override
	public String toString() {
		return this.MAJOR + "." + this.MINOR + "." + this.PATCH;
	}

	@Override
	public int compareTo(SemanticVersion o) {
		if (this.MAJOR > o.MAJOR)
			return 1;
		else if (this.MAJOR < o.MAJOR)
			return -1;
		else {
			if (this.MINOR > o.MINOR)
				return 1;
			else if (this.MINOR < o.MINOR)
				return -1;
			else {
				if (this.PATCH > o.PATCH)
					return 1;
				else if (this.PATCH < o.PATCH)
					return -1;
				else
					return 0;
			}
		}
	}

}
