package param;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Params {

    private HashSet<Param> params = new HashSet<>();


    public void addParam(Param param) {
        this.params.add(param);
    }

    public int getParamSize() {
        return this.params.size();
    }

    public boolean isEmpty() {
        return this.params.size() == 0;
    }

    public List<String> getParamsAsStringList() {
        List<String> commandLines = new ArrayList<>();
        for (Param param : params) {
            commandLines.add(param.getKey());
            commandLines.addAll(param.getValues());
        }
        return commandLines;
    }
}
