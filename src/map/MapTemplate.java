package map;

import java.util.ArrayList;
import java.util.List;

public class MapTemplate {
    private String type;
    private int width;
    private int height;
    private List<Block> blocks;
    private List<Tile> tiles;
    private List<MapObject> objects;
    private List<Player> player;
    private List<String> solution;

    // Other attrs from json file that content map info
    // ...

    public MapTemplate(String type, int w, int h, String solution){
        width = w;
        height = h;
        tiles = new ArrayList<>(width*height);
    }

    // Other attrs from json file that content map info
}
