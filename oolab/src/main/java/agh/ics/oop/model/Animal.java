package agh.ics.oop.model;

import java.util.Objects;

public class Animal {
    static final Vector2d lowerMapBoundary = new Vector2d(0, 0);
    static final Vector2d upperMapBoundary = new Vector2d(4, 4);
    private MapDirection facingDirection = MapDirection.NORTH;
    private Vector2d localizationOnMap;

    public Animal() {
        facingDirection = MapDirection.NORTH;
        localizationOnMap = new Vector2d(2, 2);
    }

    public Animal(Vector2d localizationOnMap) {
        this.localizationOnMap = localizationOnMap;
        facingDirection = MapDirection.NORTH;
    }

    public Vector2d getLocalizationOnMap() {
        return localizationOnMap;
    }

    public MapDirection getFacingDirection() {
        return facingDirection;
    }

    public void move(MoveDirection direction) {
        switch (direction) {
            case LEFT -> facingDirection = facingDirection.previous();
            case RIGHT -> facingDirection = facingDirection.next();
            case FORWARD -> {
                if (this.localizationOnMap.add(this.facingDirection.toUnitVector()).follows(lowerMapBoundary)
                        && this.localizationOnMap.add(this.facingDirection.toUnitVector()).precedes(upperMapBoundary))
                    localizationOnMap = localizationOnMap.add(facingDirection.toUnitVector());
            }
            case BACKWARD -> {
                if (this.localizationOnMap.subtract(this.facingDirection.toUnitVector()).follows(lowerMapBoundary)
                        && this.localizationOnMap.subtract(this.facingDirection.toUnitVector()).precedes(upperMapBoundary))
                    localizationOnMap = localizationOnMap.subtract(facingDirection.toUnitVector());
            }
        }
    }

    @Override
    public String toString() {
        return String.format("(position: %s , orientation %s)", localizationOnMap.toString(), facingDirection.toString());
    }

    public boolean isAt(Vector2d position) {
        return Objects.equals(localizationOnMap, position);
    }
}
