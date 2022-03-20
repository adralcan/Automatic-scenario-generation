package map;

import javafx.util.Pair;

import java.sql.Struct;
import java.util.*;

public class MapTemplate {
    private String type;
    private int width;
    private int height;
    private List<String> solution;
    private Map<String, Block> blocks;
    private List<MapObject> objects;
    private List<Player> player;

    // Other attrs from json file that content map info
    // ...

    public MapTemplate(String type, int w, int h, String solution){
        this.type = type;
        width = w;
        height = h;
        if (solution != null)
            fillBlocksAndSolution(solution.split(""));

        buildTemplate();
    }

    private void updateBlockFromBlockMap(String typeBlock) {
        Block updatedBlock;
        if(blocks.containsKey(typeBlock)) {
            updatedBlock = blocks.get(typeBlock);
            updatedBlock.setMax(updatedBlock.getMax() + 1);
        }
        else {
            updatedBlock = new Block(typeBlock, 1);
        }
        blocks.put(typeBlock, updatedBlock);
    }

    private void fillBlocksAndSolution(String[] blocksInitials) {
        String block = "";
        for (String blocksInitial : blocksInitials) {
            switch (blocksInitial) {
                case "A":
                    block = "advance";
                    break;
                case "B":
                    block = "backwards";
                    break;
                case "J":
                    block = "jump";
                    break;
                case "T":
                    block = "turnRight";
                    break;
                case "t":
                    block = "turnLeft";
                    break;
                case "H":
                    block = "action";
                    break;
            }
            updateBlockFromBlockMap(block);
            solution.add(block);
        }
    }

    private void buildTemplate() {
        Random rnd = new Random();
        int coordsX[] = new int[4];
        int tempsX[] = new int[4];
        int coordsY[] = new int[4];
        int tempsY[] = new int[4];
        Arrays.fill(coordsX, rnd.nextInt(width));
        tempsX = coordsX;
        Arrays.fill(coordsY, rnd.nextInt(height));
        tempsY = coordsY;
        Player lookPositions[] = new Player[]{
                new Player("bottomLeft", coordsX[0], coordsY[0], 0),
                new Player("topLeft", coordsX[1], coordsY[1], 0),
                new Player("topRight", coordsX[2], coordsY[2], 0),
                new Player("bottomRight", coordsX[3], coordsY[3], 0)};

        for (String solutionPart : solution) {

        }
    }
}
