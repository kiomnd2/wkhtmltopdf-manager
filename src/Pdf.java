import config.PdfConfig;
import exception.PdfExportException;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Pdf {

    private PdfConfig pdfConfig;

    private long timeout;

    private int workerThreadNumber;

    public Pdf(PdfConfig pdfConfig) {
        this.pdfConfig = pdfConfig;
        this.setTimeout(30);
        this.setWorkerThreadNumber(2);
    }

    private PdfConfig getPdfConfig() {
        return pdfConfig;
    }

    private void setPdfConfig(PdfConfig pdfConfig) {
        this.pdfConfig = pdfConfig;
    }

    public long getTimeout() {
        return timeout;
    }

    private void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    public int getWorkerThreadNumber() {
        return workerThreadNumber;
    }

    private void setWorkerThreadNumber(int workerThreadNumber) {
        this.workerThreadNumber = workerThreadNumber;
    }

    /**
     * PDF 생성
     */
    public byte[] getPDF() {
        ExecutorService executorService = Executors.newFixedThreadPool(this.workerThreadNumber);

        try {
            Process process = Runtime.getRuntime().exec(pdfConfig.getCommand());

            try (InputStream isr = process.getInputStream()) {
                process.waitFor(this.timeout, TimeUnit.SECONDS);

                if (process.exitValue() == 1) { // 종료 안되면
                    byte[] errorStream = IOUtils.toByteArray(process.getErrorStream());
                    throw new PdfExportException(new String(errorStream));
                }
                return IOUtils.toByteArray(isr);
            }
        } catch (Exception e) {
            throw new PdfExportException(e);
        } finally {
            executorService.shutdown();
            clearTemp();
        }
    }

    private void clearTemp() {pdfConfig.clearTemp();}
}
