package selim.core;

public abstract class SignFormat {

	protected final String line1;
	protected final String line2;
	protected final String line3;
	protected final String line4;

	public SignFormat(String line1, String line2, String line3, String line4) {
		this.line1 = line1;
		this.line2 = line2;
		this.line3 = line3;
		this.line4 = line4;
	}

	public String getLine1Format() {
		return this.line1;
	}

	public String getLine2Format() {
		return this.line2;
	}

	public String getLine3Format() {
		return this.line3;
	}

	public String getLine4Format() {
		return this.line4;
	}

}
