package ca.jrvs.apps.practice;

public interface RegexExc {
    /**
     * return true if filename extension is jpg or jpeg (case sensitive)
     * @param filename
     * @return
     */
    boolean matchJpeg(String filename);

    /**
     * return true if ip is valid. For now, a valid ip is from 0.0.0.0 to 999.999.999.999
     * @param ip
     * @return
     */
    boolean matchIp(String ip);

    /**
     * return true if line is empty (e.g. empty, space, tab, etc.)
     * @param line
     * @return
     */
    boolean isEmptyLine(String line);
}
