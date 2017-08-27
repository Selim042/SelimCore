package selim.demo;

public class Time {

	private byte hours;
	private byte mins;

	public Time(byte hours, byte mins) {
		this.hours = hours;
		this.mins = mins;
	}

	public byte getHours() {
		return this.hours;
	}

	public byte getMins() {
		return this.mins;
	}

	@Override
	public String toString() {
		return hours + ":" + mins;
	}

}
