package exception;

public class NotFoundWkHtmlToPdfException extends RuntimeException{
    public NotFoundWkHtmlToPdfException() {
        super("wkhtmltopdf 를 찾을 수 없습니다.");
    }
}
