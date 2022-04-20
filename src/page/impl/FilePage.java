package page.impl;

import page.Page;

import java.io.File;

public class FilePage implements Page.FILE {

    private final File filePath;

    public FilePage(File filePath) {
        this.filePath = filePath;
    }

    @Override
    public String getSourcePath() {
        return this.filePath.getAbsolutePath();
    }
}
