package common.utils;

import java.util.Date;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

public final class RandomUtil {

    private RandomUtil() {
    }

    public static String generateString() {
        return generateString(generateNumberInRange(1, 20));
    }

    public static String generateString(int length) {
        return RandomStringUtils.randomAlphabetic(length);
    }

    public static String generateEmail() {
        String timestamp = String.valueOf(new Date().getTime());
        return "gk_" + generateString() + "@gk" + timestamp.substring(7) + ".com";
    }

    public static int generateNumberInRange(int from, int to) {
        return RandomUtils.nextInt(from, to);
    }

}
