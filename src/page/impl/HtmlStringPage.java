package page.impl;

import exception.PdfExportException;
import org.apache.commons.io.FileUtils;
import page.Clearable;
import page.Page;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

public class HtmlStringPage implements Page.HTML_STRING, Clearable {

    private String source;

    private File tempDirectory;

    public HtmlStringPage(String source) {
        this.source = source;
    }

    public String getSource() {
        return source;
    }

    public File getTempDirectory() {
        return tempDirectory;
    }

    public void setTempDirectory(File tempDirectory) {
        this.tempDirectory = tempDirectory;
    }

    @Override
    public String getSourcePath() {
        if (this.tempDirectory == null) this.tempDirectory = FileUtils.getTempDirectory();
        try {
            File tempFile = new File(this.tempDirectory, "pdfFile_" + LocalDateTime.now() +".html");
            tempFile.createNewFile();
            FileUtils.writeStringToFile(tempFile, getSource(), "UTF-8");
            return tempFile.getAbsolutePath();
        } catch (IOException e) {
            throw new PdfExportException();
        }
    }

    @Override
    public boolean clear() throws IOException {
        if (this.tempDirectory.exists()) {
            return this.tempDirectory.delete();
        }
        return false;
    }
}
