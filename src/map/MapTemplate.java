package map;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MapTemplate {
    private String type;
    private int width;
    private int height;
    private Map<String, Block> blocks;
    private List<Tile> tiles;
    private List<MapObject> objects;
    private List<Player> player;
    private List<String> solution;

    // Other attrs from json file that content map info
    // ...

    public MapTemplate(String type, int w, int h, String solution){
        this.type = type;
        width = w;
        height = h;
        tiles = new ArrayList<>();
        for(int y = 0; y <  height; y++) {
            for(int x = 0; x <  height; x++) {
                tiles.add(new Tile("grass01", 0, x, y));
            }
        }
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
        for (int i = 0; i < blocksInitials.length; i++) {
            switch (blocksInitials[i]) {
                case "A":
                    updateBlockFromBlockMap("advance");
                    solution.add("advance");
                    break;
                case "B":
                    updateBlockFromBlockMap("backwards");
                    solution.add("backwards");
                    break;
                case "J":
                    updateBlockFromBlockMap("jump");
                    solution.add("jump");
                    break;
                case "T":
                    updateBlockFromBlockMap("turnRight");
                    solution.add("turnRight");
                    break;
                case "t":
                    updateBlockFromBlockMap("turnLeft");
                    solution.add("turnLeft");
                    break;
                case "H":
                    updateBlockFromBlockMap("action");
                    solution.add("action");
                    break;
            }
        }
    }

    private void buildTemplate(){
        Random rnd = new Random();
        int x = rnd.nextInt(width);
        int y = rnd.nextInt(height);
        Player realPlayer = new Player(Player.randomLookPosition(), x, y);
        // TODO: Construir la posicion de la bandera a partir de la solucion proporcionada
    }
}
