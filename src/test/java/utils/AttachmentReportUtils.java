package utils;

import com.google.common.io.ByteStreams;
import io.qameta.allure.Attachment;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collection;
import javax.annotation.Nullable;
import javax.imageio.ImageIO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

/**
 * @author KuK QA
 */
public final class AttachmentReportUtils {

    private static final Logger LOGGER = LogManager.getLogger(AttachmentReportUtils.class);

    private AttachmentReportUtils() {
    }

    /**
     * takes a screenshot of the whole page and saves it to user directory
     */
    @Attachment(value = "Page screenshot", type = "image/png")
    public static byte[] attachFullPageScreenshot(final WebDriver driver, String screenshotName) {
        try {
            return ByteStreams.toByteArray(takeFullPageScreenShotAsInputStream(driver, screenshotName, null));
        } catch (IOException e) {
            LOGGER.info("IOException was caught on converting InputStream to byte[] because {}", e.getMessage());
        }
        return new byte[0];
    }

    /**
     * takes a screenshot of the whole page and saves it to user directory with sub-directory
     */
    @Attachment(value = "Page screenshot", type = "image/png")
    public static byte[] attachFullPageScreenshot(final WebDriver driver, String screenshotName, String subDir) {
        try {
            return ByteStreams.toByteArray(takeFullPageScreenShotAsInputStream(driver, screenshotName, subDir));
        } catch (IOException e) {
            LOGGER.info("IOException was caught on converting InputStream to byte[] because {}", e.getMessage());
        }
        return new byte[0];
    }

    /**
     * takes a screenshot of selected elements and saves it to user directory
     */
    @Attachment(value = "{screenshotName}", type = "image/png")
    public static byte[] attachElementsScreenshot(
        final WebDriver driver, final Collection<WebElement> elements, String screenshotName) {
        try {
            return ByteStreams.toByteArray(takeElementScreenShotAsInputStream(driver, elements, screenshotName, null));
        } catch (IOException e) {
            LOGGER.info("IOException was caught on converting InputStream to byte[] because {}", e.getMessage());
        }
        return new byte[0];
    }

    /**
     * takes a screenshot of selected elements and saves it to user directory
     */
    @Attachment(value = "{screenshotName}", type = "image/png")
    public static byte[] attachElementScreenshot(final WebDriver driver, final WebElement element, String screenshotName) {
        try {
            return ByteStreams.toByteArray(takeElementScreenShotAsInputStream(driver, Arrays.asList(element), screenshotName, null));
        } catch (IOException e) {
            LOGGER.info("IOException was caught on converting InputStream to byte[] because {}", e.getMessage());
        }
        return new byte[0];
    }

    /**
     * takes a screenshot of selected elements and saves it to user directory with sub-directory
     */
    @Attachment(value = "{screenshotName}", type = "image/png")
    public static byte[] attachElementScreenshot(final WebDriver driver, final WebElement element, String screenshotName, String subDir) {
        try {
            return ByteStreams.toByteArray(takeElementScreenShotAsInputStream(driver, Arrays.asList(element), screenshotName, subDir));
        } catch (IOException e) {
            LOGGER.info("IOException was caught on converting InputStream to byte[] because {}", e.getMessage());
        }
        return new byte[0];
    }

    /**
     * Attach text to allure report
     */
    @Attachment(value = "{attachmentTitle}")
    public static String attachText(String text, String attachmentTitle) {
        return text;
    }

    private static InputStream takeElementScreenShotAsInputStream(
        final WebDriver driver, final Collection<WebElement> elements, String screenshotName, @Nullable String subDir) {
        boolean isFullscreen = elements == null || elements.size() == 0;
        String folderPath = System.getProperty("user.dir") + "/testReport/" + getScreenShotFolderPath(subDir);
        String time = ZonedDateTime.now().format(DateTimeFormatter.ofPattern("'T'HH:mm:ss.SSSZ"));
        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        String destinationFullPath = folderPath + "/" + screenshotName + "-" + time + (isFullscreen ? "-FullScreen.png" : ".png");
        FileInputStream inputStream;

        try {
            final AShot aShot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(500));
            Screenshot screenshot = isFullscreen
                ? aShot.takeScreenshot(driver)
                : aShot.takeScreenshot(driver, elements);
            ImageIO.write(screenshot.getImage(), "PNG", new File(destinationFullPath));

            File file = new File(destinationFullPath);
            inputStream = new FileInputStream(file);

        } catch (Exception e) {
            throw new RuntimeException("Exception while taking full screenshot " + e.getMessage());
        }
        return inputStream;
    }

    private static InputStream takeFullPageScreenShotAsInputStream(final WebDriver driver, String screenshotName, @Nullable String subDir) {
        return takeElementScreenShotAsInputStream(driver, null, screenshotName, subDir);
    }

    private static String getScreenShotFolderPath(@Nullable String subDir) {
        String env = System.getProperty("Env", "local");
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String extraPath = subDir != null ? "/" + subDir.replaceAll("[\\\\/:*?\"<>|]", "") : "";
        return "screenshot/" + env + "/screenshot-" + date + extraPath;
    }
}
