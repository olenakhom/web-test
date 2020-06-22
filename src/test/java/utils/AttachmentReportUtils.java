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
import javax.imageio.ImageIO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
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
            return ByteStreams.toByteArray(takeFullPageScreenShotAsInputStream(driver, screenshotName));
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

    private static InputStream takeFullPageScreenShotAsInputStream(final WebDriver driver, String screenshotName) {
        String folderPath = System.getProperty("user.dir") + "/testReport/" + getScreenShotFolderPath();
        String time = ZonedDateTime.now().format(DateTimeFormatter.ofPattern("'T'HH:mm:ss.SSSZ"));
        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        String destinationFullPath = folderPath + "/" + screenshotName + "-" + time + "-FullScreen.png";
        FileInputStream inputStream;

        try {
            Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(500))
                .takeScreenshot(driver);
            ImageIO.write(screenshot.getImage(), "PNG", new File(destinationFullPath));

            File file = new File(destinationFullPath);
            inputStream = new FileInputStream(file);

        } catch (Exception e) {
            throw new RuntimeException("Exception while taking full screenshot " + e.getMessage());
        }
        return inputStream;
    }

    private static String getScreenShotFolderPath() {
        String env = System.getProperty("Env", "local");
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return "screenshot/" + env + "/screenshot-" + date;
    }

}
