package map;

import java.awt.dnd.InvalidDnDOperationException;
import java.util.Random;

public class Player {
    private final String character = "character";
    private String lookPosition;
    private int posX;
    private int posY;

    public String getLookPosition() {
        return lookPosition;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    private enum LookOptions {
        topLeft("topLeft", "bottomLeft", "topRight"),
        topRight("topRight", "topLeft", "bottomRight"),
        bottomLeft("bottomLeft", "topLeft", "bottomRight"),
        bottomRight("bottomRight", "bottomLeft", "topRight");
        private final String text;
        private final String turnLeftNext;
        private final String turnRightNext;
        LookOptions(String text, String turnLeftNext, String turnRightNext) {
            this.text = text;
            this.turnLeftNext = turnLeftNext;
            this.turnRightNext = turnRightNext;
        }
        public static String fromString(String text) {
            for (LookOptions b : LookOptions.values()) {
                if (b.text.equalsIgnoreCase(text)) {
                    return b.text;
                }
            }
            return null;
        }
        public static String turnLeft(String text) {
            for (LookOptions b : LookOptions.values()) {
                if (b.turnLeftNext.equalsIgnoreCase(text)) {
                    return b.turnLeftNext;
                }
            }
            return null;
        }
        public static String turnRight(String text) {
            for (LookOptions b : LookOptions.values()) {
                if (b.turnRightNext.equalsIgnoreCase(text)) {
                    return b.turnRightNext;
                }
            }
            return null;
        }
    }

    public Player(String lookPosition, int positionX, int positionY){
        this.lookPosition = LookOptions.fromString(lookPosition);
        if (this.lookPosition == null) {
            throw new IllegalArgumentException("This lookPosition does not exists: " + lookPosition);
        }
        this.posX = positionX;
        this.posY = positionY;
    }

    public static String randomLookPosition() {
        Random rnd = new Random();
        int direction = rnd.nextInt(4);
        String solution = "";
        switch (direction) {
            case 0:
                return solution = "topLeft";
            case 1:
                return solution = "topRight";
            case 2:
                return solution = "bottomLeft";
            case 3:
                return solution = "bottomRight";
        }
        return solution;
    }
    public void turnLookPosition(String turn) {
        switch (turn) {
            case "turnLeft":
                this.lookPosition = LookOptions.turnLeft(lookPosition);
                break;
            case "turnRight":
                this.lookPosition = LookOptions.turnRight(lookPosition);
                break;
        }
    }

}
