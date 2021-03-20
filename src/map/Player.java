package map;

import java.awt.dnd.InvalidDnDOperationException;
import java.util.List;
import java.util.Random;

public class Player {
    private final String character = "character";
    private String lookPosition;
    private int posX;
    private int posY;
    private int offset;
    private Boolean hasObject;

    public String getLookPosition() {
        return lookPosition;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public int getOffset() {
        return offset;
    }

    public Boolean getHasObject() {
        return hasObject;
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

    public Player(String lookPosition, int positionX, int positionY, int offset){
        this.lookPosition = LookOptions.fromString(lookPosition);
        if (this.lookPosition == null) {
            throw new IllegalArgumentException("This lookPosition does not exists: " + lookPosition);
        }
        this.posX = positionX;
        this.posY = positionY;
        this.offset = offset;
        hasObject = false;
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

    private void moveForwardBackward(String movement) {
        switch (this.lookPosition) {
            case "bottomLeft":
                posX = (movement.equals("advance")) ? posX - 1 : posX + 1;
                break;
            case "topLeft":
                posY = (movement.equals("advance")) ? posY - 1 : posY + 1;
                break;
            case "topRight":
                posX = (movement.equals("advance")) ? posX + 1 : posX - 1;
                break;
            case "bottomRight":
                posY = (movement.equals("advance")) ? posY + 1 : posY - 1;
                break;
        }
    }

    public Boolean CanMoveTo(String solutionPart, Tile[][] tiles, List<MapObject> mapObjectList) {
        Boolean canMove = true;
        int prevX = posX;
        int prevY = posY;
        switch (solutionPart) {
            case "advance":
            case "backwards":
                prevX = posX;
                prevY = posY;
                moveForwardBackward(solutionPart);
                if (posX < tiles[0].length && posY >= 0 && posY < tiles.length) {
                    if (offset == tiles[posY][posX].offset())
                        return true;
                    else {
                        posX = prevX;
                        posY = prevY;
                        return false;
                    }
                }
                posX = prevX;
                posY = prevY;
                return false;
            return false;
            case "jump":
                break;
            case "turnRight":
            case "turnLeft":
                turnLookPosition(solutionPart);
                return true;
            case "action":
                // Este bloque son dos acciones en una, 1. Recoger el objeto, 2. Usarlo

                // Si tiene un objeto se usa en el tile de delante
                if (hasObject) {
                    prevX = posX;
                    prevY = posY;
                    switch (this.lookPosition) {
                        case "bottomLeft":
                            posX--;
                            break;
                        case "topLeft":
                            posY--;
                            break;
                        case "topRight":
                            posX++;
                            break;
                        case "bottomRight":
                            posY++;
                            break;
                    }
                    if(offset == tiles[posY][posX].offset()) {
                        hasObject = false;
                        // TODO: Saber que objeto meter
                        mapObjectList.add(new MapObject("object", "collision", posX, posY));
                        posX = prevX;
                        posY = prevY;
                        return true;
                    }
                    posX = prevX;
                    posY = prevY;
                    return false;

                } else {
                    hasObject = true;
                    // TODO: Saber que objeto meter
                    mapObjectList.add(new MapObject("object", "pickup", posX, posY));
                }

                break;
        }
    }

}
