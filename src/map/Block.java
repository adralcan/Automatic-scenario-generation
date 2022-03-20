package map;

import java.util.Arrays;
import java.util.List;

public class Block {
    private String name;
    private int max;

    private final List<String> actionCategory = Arrays.asList("advance", "backwards", "turnLeft", "turnRight", "jump", "action");
    private final List<String> controlCategory = Arrays.asList("loop", "if", "ifElse", "whileLoop", "infiniteLoop");
    private final List<String> operatorCategory = Arrays.asList("not", "logic_operation");
    private final List<String> propertyCategory = Arrays.asList("bridgeCondition", "tilePositionCondition");

    public String getName() {
        return name;
    }

    public int getMax() {
        return max;
    }
    public void setMax(int newMax) {
        max = newMax;
    }

    private Boolean findBlockInList(String name, List<String> category) {
        for (String element : category) {
            if (element.contains(name)) {
                return true;
            }
        }
        return false;
    }

    public Block(String name, int max) {

    }
}
