package ui;

import java.util.Arrays;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum TestPlatform {
    LOCAL("local"),
    LINUX("linux"),
    SELENOID("selenoid"),
    WINDOWS("windows"),
    MAC("mac");

    private String name;

    public static TestPlatform getBySystemProperty(String platform) {
        return Arrays.stream(TestPlatform.values())
            .filter(item -> item.getName().equalsIgnoreCase(platform))
            .findFirst()
            .orElseThrow(() -> new IllegalStateException("getBySystemProperty: Provided platform " + platform + " does not exist!"));
    }

}
