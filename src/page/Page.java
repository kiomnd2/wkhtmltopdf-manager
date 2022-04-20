package page;

public interface Page {
    interface HTML_STRING extends Page {};
    interface URL extends Page {};
    interface FILE extends Page {};

    String getSourcePath();
}
