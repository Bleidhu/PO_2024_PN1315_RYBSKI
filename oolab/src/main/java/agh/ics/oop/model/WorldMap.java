package agh.ics.oop.model;

/**
 * The interface responsible for interacting with the map of the world.
 * Assumes that Vector2d and MoveDirection classes are defined.
 *
 * @author apohllo, idzik
 */
public interface WorldMap<T, P> extends MoveValidator<P> {

    /**
     * Place a object on the map.
     *
     * @param inhabitant The inhabitant to place on the map.
     * @return True if the inhabitant was placed. The inhabitant cannot be placed if the move is not valid.
     */
    boolean place(T inhabitant);

    /**
     * Moves an inhabitant (if it is present on the map) according to specified direction.
     * If the move is not possible, this method has no effect.
     */
    void move(T object, MoveDirection direction);

    /**
     * Return true if given position on the map is occupied. Should not be
     * confused with canMove since there might be empty positions where the inhabitant
     * cannot move.
     *
     * @param position Position to check.
     * @return True if the position is occupied.
     */
    boolean isOccupied(P position);

    /**
     * Return an inhabitant at a given position.
     *
     * @param position The position of the inhabitant.
     * @return inhabitant or null if the position is not occupied.
     */
    T objectAt(P position);

    boolean canMoveTo(P position);
}
