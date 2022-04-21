package config;

import com.sun.tools.javac.util.StringUtils;
import exception.NotFoundWkHtmlToPdfException;
import exception.WrapperConfigException;
import page.Clearable;
import page.Page;
import param.Param;
import param.Params;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class PdfConfig {

    private final String wkHtmltoPdfCommand = "wkhtmltopdf";

    private final String STDINOUT = "-";

    private HashSet<Page> pages = new HashSet<>();

    private Params params;

    public PdfConfig() {
        this.params = new Params();
    }

    public void addParam(Param param) {
        this.params.addParam(param);
    }

    public void addPage(Page page) {
        this.pages.add(page);
    }



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

    public String[] getWkhtmltopdfCommand() {
        List<String> commandLine = new ArrayList<>();

        commandLine.add(this.wkHtmltoPdfCommand);

        if (!params.isEmpty()) {
            commandLine.addAll(params.getParamsAsStringList());
        }

        for (Page page : pages) {
            commandLine.add(page.getSourcePath());
        }

        return commandLine.toArray(new String[0]);
    }

    public void clearTemp() {
        pages.forEach(page -> {
            if (page instanceof Clearable) {
                Clearable c = (Clearable) page;
                c.clear();
            }
        });
    }

    public String getCommand() {
        return String.join("", getWkhtmltopdfCommand());
    }
}
