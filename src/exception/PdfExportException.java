package exception;

public class PdfExportException extends RuntimeException{


    public PdfExportException(Exception e) {
        this(e.getMessage());
    }

    public PdfExportException(String s) {
        super("PDF 추출중 오류가 발생했습니다 : " + s);
    }
}
