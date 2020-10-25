package map;

public class MapObject {
    private String id;
    private String name;
    private String type;
    private int posX;
    private int posY;

    private enum ObjectName {
        none("none");


        private String text;

        ObjectName(String text) {
            this.text = text;
        }

        public String getText() {
            return this.text;
        }

        public static String fromString(String text) {
            for (MapObject.ObjectName b : MapObject.ObjectName.values()) {
                if (b.text.equalsIgnoreCase(text)) {
                    return b.text;
                }
            }
            return null;
        }
    }

    private enum ObjectType {
        none("none"),
        endLevel("endLevel"),
        collision("collision"),
        jump("jump"),
        pickup("pickup"),
        buttonToggle("buttonToggle"),
        puenteIf("puenteIf"),
        teleport("teleport");

        private String text;

        ObjectType(String text) {
            this.text = text;
        }

        public String getText() {
            return this.text;
        }

        public static String fromString(String text) {
            for (MapObject.ObjectType b : MapObject.ObjectType.values()) {
                if (b.text.equalsIgnoreCase(text)) {
                    return b.text;
                }
            }
            return null;
        }
    }

    public MapObject(String type, int positionX, int positionY){
        this.type = MapObject.ObjectType.fromString(type);
        if (this.type == null) {
            throw new IllegalArgumentException("This obejct type does not exists: " + type);
        }
        this.posX = positionX;
        this.posY = positionY;
    }
}
