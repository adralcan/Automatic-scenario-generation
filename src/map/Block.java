package map;

import java.util.Arrays;
import java.util.List;

public class Block {
    private String name;
    private String category;
    private int max;

    private final List<String> actionCategory = Arrays.asList("advance", "backwards", "turnLeft, turnRight", "jump", "action");
    private final List<String> controlCategory = Arrays.asList("loop", "if", "ifElse", "whileLoop", "infiniteLoop");
    private final List<String> operatorCategory = Arrays.asList("bridgeCondition", "tilePositionCondition", "not", "logic_operation");

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;*
    }

    public int getMax() {
        return max;
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
        if (findBlockInList(name, actionCategory)) {
            category = "action";
        } else if (findBlockInList(name, controlCategory)) {
            category = "control";
        } else if (findBlockInList(name, operatorCategory)) {
            category = "operator";
        } else {
            throw new IllegalArgumentException("This block name does not exists: " + name);
        }
        this.name = name;
        this.max = max < 1 ? 0 : max;
    }
}
