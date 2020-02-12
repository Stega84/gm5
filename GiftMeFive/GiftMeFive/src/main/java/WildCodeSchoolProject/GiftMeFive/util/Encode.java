package WildCodeSchoolProject.GiftMeFive.util;

public class Encode {

	public Encode() {
		super();
	}

	final static private long KEY = 2546278l;

	public long encode(long id) {

		long keyEncode = id * KEY;
		return keyEncode;
	}

	public long decode(long id) {

		if (id % KEY == 0) {
			long keyEncode = id / KEY;
			return keyEncode;
		} else {
			return 0;
		}
	}
}
