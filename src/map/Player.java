package map;

import java.awt.dnd.InvalidDnDOperationException;

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
        topLeft("topLeft"),
        topRight("topRight"),
        bottomLeft("bottomLeft"),
        bottomRight("bottomRight");
        private String text;
        LookOptions(String text) {
            this.text = text;
        }
        public String getText() {
            return this.text;
        }
        public static String fromString(String text) {
            for (LookOptions b : LookOptions.values()) {
                if (b.text.equalsIgnoreCase(text)) {
                    return b.text;
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
}
