package page.impl;

import page.Page;

public class URLPage implements Page.URL{

    private final URL url;

    public URLPage(URL url) {
        this.url = url;
    }

    @Override
    public String getSourcePath() {
        return this.url.getSourcePath();
    }
}
