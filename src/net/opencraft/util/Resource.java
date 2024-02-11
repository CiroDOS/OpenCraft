package net.opencraft.util;

public class Resource {

	public static final byte[] MISSING_TEXTURE = new byte[] { -119, 80, 78, 71, 13, 10, 26, 10, 0, 0, 0, 13, 73, 72, 68,
			82, 0, 0, 0, 2, 0, 0, 0, 2, 8, 2, 0, 0, 0, -3, -44, -102, 115, 0, 0, 0, 1, 115, 82, 71, 66, 0, -82, -50, 28,
			-23, 0, 0, 0, 4, 103, 65, 77, 65, 0, 0, -79, -113, 11, -4, 97, 5, 0, 0, 0, 9, 112, 72, 89, 115, 0, 0, 18,
			116, 0, 0, 18, 116, 1, -34, 102, 31, 120, 0, 0, 0, 17, 73, 68, 65, 84, 24, 87, 99, 0, -126, -59, -98, 75,
			-64, -104, -127, 1, 0, 21, -20, 3, 33, 2, -8, 93, -7, 0, 0, 0, 0, 73, 69, 78, 68, -82, 66, 96, -126 };

	protected String origin = "opencraft";
	protected String res_name;

	protected Resource() {
	}
	
	public Resource(String origin, String id) {
		this.res_name = id.toLowerCase();
		this.origin = origin.toLowerCase();
	}

	public Resource(String id) {
		this.res_name = id.toLowerCase();
	}

	public static Resource get(String origin, String id) {
		return new Resource(origin, id);
	}

	public static Resource get(String id) {
		return new Resource(id);
	}

	public String getName() {
		return this.res_name;
	}

	public String getOrigin() {
		return origin;
	}

	@Override
	public String toString() {
		return this.origin + ':' + this.res_name;
	}

}
