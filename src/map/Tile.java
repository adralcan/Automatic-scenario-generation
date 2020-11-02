package map;

public class Tile {
    private String name;
    private int offset;
    private int posX;
    private int posY;

    public String name() {
        return name;
    }

    public int offset() {
        return offset;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    private enum tileName {
        grass01("grass01"),
        grass02("grass02"),
        grass03("grass03"),
        grass04("grass04"),
        grass05("grass05"),
        grass06("grass06"),
        grassborder01("grassborder01"),
        grassborder02("grassborder02"),
        grassborder03("grassborder03"),
        rock01("rock01"),
        rock02("rock02"),
        rock03("rock03"),
        rock04("rock04"),
        sand01("sand01"),
        sand02("sand02"),
        sand03("sand03"),
        sand04("sand04"),
        stone01("stone01"),
        stone02("stone02"),
        stone03("stone03"),
        stone04("stone04"),
        stone05("stone05"),
        rockTall("rockTall"),
        stoneTall("stoneTall"),
        water01("water01"),
        water02("water02"),
        water03("water03"),
        water04("water04"),
        water05("water05"),
        water06("water06"),
        water07("water07"),
        water08("water08"),
        water09("water09"),
        water10("water10"),
        water11("water11"),
        water12("water12"),
        water13("water13"),
        water14("water14"),
        water15("water15"),
        water16("water16"),
        water17("water17"),
        water18("water18"),
        water19("water19"),
        water20("water20"),
        water21("water21"),
        water22("water22"),
        water23("water23"),
        water24("water24"),
        water25("water25"),
        water26("water26"),
        water27("water27"),
        water28("water28"),
        water29("water29"),
        water30("water30"),
        water31("water31"),
        water32("water32"),
        water33("water33"),
        water34("water34"),
        water35("water35"),
        water36("water36"),
        water37("water37"),
        water38("water38"),
        water39("water39"),
        water40("water40"),
        teleport_red("teleport_red"),
        teleport_blue("teleport_blue"),
        teleport_white("teleport_white");

        private String text;

        tileName(String text) {
            this.text = text;
        }

        public String getText() {
            return this.text;
        }

        public static String fromString(String text) {
            for (Tile.tileName b : Tile.tileName.values()) {
                if (b.text.equalsIgnoreCase(text)) {
                    return b.text;
                }
            }
            return null;
        }
    }

    public Tile(String name, int offset, int positionX, int positionY){
        this.name = Tile.tileName.fromString(name);
        if (this.name == null) {
            throw new IllegalArgumentException("This object name does not exists: " + name);
        }
        this.offset = offset;
        this.posX = positionX;
        this.posY = positionY;
    }
}
