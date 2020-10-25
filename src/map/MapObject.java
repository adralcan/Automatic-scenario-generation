package map;

public class MapObject {
    private String id;
    private String name;
    private String type;
    private int posX;
    private int posY;

    public String name() {
        return name;
    }

    public String type() {
        return type;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    private enum ObjectName {
        flag("flag"),
        arbolGrande("arbolGrande"),
        arbolGrande2("arbolGrande2"),
        arbolGrande3("arbolGrande3"),
        arbolGrande4("arbolGrande4"),
        piedraGrande("piedraGrande"),
        roca1("roca1"),
        roca2("roca2"),
        roca3("roca3"),
        roca_rompible("roca_rompible"),
        roquitas("roquitas"),
        valla1("valla1"),
        valla2("valla2"),
        vallaEsquina1("vallaEsquina1"),
        vallaEsquina2("vallaEsquina2"),
        vallaEsquina3("vallaEsquina3"),
        vallaEsquina4("vallaEsquina4"),
        arbusto("arbusto"),
        arbustoCuadrado("arbustoCuadrado"),
        flores("flores"),
        seta("seta"),
        setitas("setitas"),
        tronco("tronco"),
        tronco_tumbado_right("tronco_tumbado_right"),
        tronco_tumbado_left("tronco_tumbado_left"),
        springDownRight("springDownRight"),
        springDownLeft("springDownLeft"),
        springUpRight("springUpRight"),
        springUpLeft("springUpLeft"),
        teleporPlatform("teleporPlatform teleport"),
        hielo("hielo"),
        lupa("lupa"),
        mazo("mazo"),
        senal01("senal01"),
        buttonWoodActivate("buttonActivate wood"),
        buttonStoneActivate("buttonActivate stone"),
        puenteIfWoodRight("puenteIf wood right"),
        puenteIfWoodLeft("puenteIf wood left"),
        puenteIfStoneRight("puenteIf stone right"),
        puenteIfStoneLeft("puenteIf stone left"),
        dummyBridgeWoodRightOn("dummyBridge wood right on"),
        dummyBridgeWoodRightOff("dummyBridge wood right off"),
        dummyBridgeWoodLeftOn("dummyBridge wood left on"),
        dummyBridgeWoodLeftOff("dummyBridge wood left off"),
        dummyBridgeStoneRightOn("dummyBridge stone right on"),
        dummyBridgeStoneRightOff("dummyBridge stone right off"),
        dummyBridgeStoneLeftOn("dummyBridge stone left on"),
        dummyBridgeStoneLeftOff("dummyBridge stone left off"),
        entrada_puente_left_top("entrada_puente_left_top"),
        entrada_puente_left_bottom("entrada_puente_left_bottom"),
        entrada_puente_right_top("entrada_puente_right_top"),
        entrada_puente_right_bottom("entrada_puente_right_bottom");

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
        dummyBridge("dummyBridge"),
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

    public MapObject(String name, String type, int positionX, int positionY){
        this.name = MapObject.ObjectName.fromString(name);
        if (this.name == null) {
            throw new IllegalArgumentException("This object name does not exists: " + type);
        }
        this.type = MapObject.ObjectType.fromString(type);
        if (this.type == null) {
            throw new IllegalArgumentException("This object type does not exists: " + type);
        }
        this.posX = positionX;
        this.posY = positionY;
    }
}
