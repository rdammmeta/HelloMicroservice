package hello;

public class EnvironmentUtil {

	/**
	 * @Title: getEnvironmentVariable
	 * @Description: Gets the value of an environment variable.
	 * @param name
	 * @return String
	 */
	public static String getEnvironmentVariable(String name) {
		String value = System.getenv(name);
		if (value != null) {
			return value;
		}
		value = System.getProperty(name);
		if (value != null) {
			return value;
		}
		System.err.println("ERROR: The environment variable " + name + " was not found.");
		return null;
	}

}
