package agh.ics.oop.model;

public enum MapDirection {
    NORTH, EAST, SOUTH, WEST;

    public String toString() {
        return switch (this) {
            case EAST -> "Wschód";
            case WEST -> "Zachód";
            case NORTH -> "Północ";
            case SOUTH -> "Południe";
        };
    }
    public MapDirection next(){
        return MapDirection.values()[(ordinal() + 1) % MapDirection.values().length];
    }
    public MapDirection previous(){
        return MapDirection.values()[(ordinal() - 1 + MapDirection.values().length) % MapDirection.values().length];
    }
    public Vector2d toUnitVector(){
        return switch(this){
            case EAST -> new Vector2d(1, 0);
            case NORTH -> new Vector2d(0,1);
            case WEST -> new Vector2d(-1, 0);
            case SOUTH -> new Vector2d(0, -1);
        };
    }
}
