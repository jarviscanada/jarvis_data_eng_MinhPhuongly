package ca.jrvs.apps.practice;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexExcImp implements RegexExc{

    @Override
    public boolean matchJpeg(String filename) {
        Pattern pattern = Pattern.compile("[\\w][\\w|-]*(\\.jpeg|\\.jpg)$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(filename);
        return matcher.find();
    }

    @Override
    public boolean matchIp(String ip) {
        Pattern pattern = Pattern.compile("^(\\d{1,3}\\.){3}\\d{1,3}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(ip);
        return matcher.find();
    }

    @Override
    public boolean isEmptyLine(String line) {
        Pattern pattern = Pattern.compile("^\\s*$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(line);
        return matcher.find();
    }
}
