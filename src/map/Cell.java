package map;
import java.util.HashMap;
import java.util.Map;

public class Cell {

    private String style;
    private int z;
    private final Map<String, String> styles = new HashMap<String, String>(){{
        put("grass", "grass");
        put("sand", "sand");
        put("stone", "stone");
        put("water", "water");
    }};
    public Cell() {
        style = styles.get("grass");
        z = 0;
    }
    public Cell(String style) {
        if (styles.get(style) == null) {
            System.out.println(style + " does not exist as a Cell type");
        }
        else {
            this.style = styles.getOrDefault(style, "grass");
        }
    }
    public Cell(int z) {
        this.style = "string";
        this.z = z;
    }
    public Cell(String style, int z) {
        if (styles.get(style) == null) {
            System.out.println(style + " does not exist as a Cell type");
        }
        else {
            this.style = style;
        }
        this.z = z;
    }
    public String randomStyle(){
        int random = (int) (Math.random() * styles.size());
        return styles.keySet().toArray(new String [styles.size()])[random];
    }
}
