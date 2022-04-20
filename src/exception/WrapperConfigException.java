package exception;

public class WrapperConfigException extends RuntimeException{
    public WrapperConfigException() {
        super("명령어 실행 도중 오류가 발생했습니다.");
    }
}
