import exception.NotFoundWkHtmlToPdfException;
import exception.WrapperConfigException;
import sun.security.util.IOUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PdfConfig {

    private final String wkHtmltoPdfCommand = "wkhtmltopdf";

    private final String STDINOUT = "-";

    public static String findExcutable(int timeout) {
        String osName = System.getProperty("os.name").toLowerCase();
        String cmd = osName.contains("windows") ? "where.exe wkhtmltopdf" : "which wkhtmltopdf";
        String result = "";
        try {
            Process p = Runtime.getRuntime().exec(cmd);
            p.waitFor(timeout, TimeUnit.SECONDS);

            try (BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
                result = br.lines().collect(Collectors.joining());
                if (result.isEmpty()) {
                    throw new NotFoundWkHtmlToPdfException();
                }
            }

        } catch (Exception e) {
            throw new WrapperConfigException();
        }
        return result;
    }

}
