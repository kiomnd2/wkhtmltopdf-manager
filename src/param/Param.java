package param;

import java.util.Arrays;
import java.util.HashSet;

public class Param {

    private final String key;

    private final HashSet<String> values = new HashSet<>();

    public Param(String key, String ... value) {
        this.key = key;
        this.values.addAll(Arrays.asList(value));
    }

    public String getKey() {
        return key;
    }

    public HashSet<String> getValues() {
        return values;
    }
}
