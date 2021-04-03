package map;

import java.awt.dnd.InvalidDnDOperationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Player {
    private final String character = "character";
    private String lookPosition;
    private int posX;
    private int posY;
    private int offset;
    private List<String> backpack;

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
        backpack = new ArrayList<>();
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
                posX = (movement.equals("advance") || movement.equals("jump")) ? posX - 1 : posX + 1;
                break;
            case "topLeft":
                posY = (movement.equals("advance") || movement.equals("jump")) ? posY - 1 : posY + 1;
                break;
            case "topRight":
                posX = (movement.equals("advance") || movement.equals("jump")) ? posX + 1 : posX - 1;
                break;
            case "bottomRight":
                posY = (movement.equals("advance") || movement.equals("jump")) ? posY + 1 : posY - 1;
                break;
        }
    }

    public Boolean CanMoveTo(String solutionPart, Tile[][] tiles, List<MapObject> mapObjectList) {
        Boolean canMove = true;
        int prevX = posX;
        int prevY = posY;
        switch (solutionPart) {
            default:
                return false;
            case "advance":
            case "backwards":
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
            case "jump":
                moveForwardBackward(solutionPart);
                if (posX < tiles[0].length && posY >= 0 && posY < tiles.length) {
                    if (offset > tiles[posY][posX].offset())
                        return true;
                    else if (offset < tiles[posY][posX].offset()) {
                        return tiles[posY][posX].offset() - offset <= 30;
                    } else {
                        posX = prevX;
                        posY = prevY;
                        return false;
                    }
                }
                posX = prevX;
                posY = prevY;
                return false;
            case "turnRight":
            case "turnLeft":
                turnLookPosition(solutionPart);
                return true;
            case "action":
                // Este bloque son dos acciones en una, 1. Recoger el objeto, 2. Usarlo
                // Si tiene un objeto se usa en el tile de delante
                if (!backpack.isEmpty()) {
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
                        mapObjectList.add(new MapObject(backpack.get(0).equals("lupa")? "hielo" : "roca_rompible",
                                "collision", posX, posY));
                        backpack.clear();
                        posX = prevX;
                        posY = prevY;
                        return true;
                    }
                    posX = prevX;
                    posY = prevY;
                    return false;
                } else {
                    backpack.add("object");
                    // TODO: Saber que objeto meter
                    mapObjectList.add(new MapObject("object", "pickup", posX, posY));
                    return true;
                }
        }
    }
}
