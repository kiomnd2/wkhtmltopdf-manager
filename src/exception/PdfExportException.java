package exception;

public class PdfExportException extends RuntimeException{
    public PdfExportException() {
        super("PDF 추출중 오류가 발생했습니다.");
    }
}