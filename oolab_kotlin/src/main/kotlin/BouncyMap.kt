package org.example

class BouncyMap(val width: Int, val height: Int): WorldMap{
    private val lowerLeft: Vector2d = Vector2d(0, 0);
    private val upperRight: Vector2d = Vector2d(width-1, height-1);
    val bounds: Boundary = Boundary(lowerLeft, upperRight);
    private var animals: HashMap<Vector2d, Animal> = HashMap();
    override fun place(animal: Animal): Boolean {
        if(animals.containsValue(animal)){
            for (pos in animals.keys){
                if (animals[pos] == animal){
                    animals.remove(pos)
                    break
                }
            }
        }
        val animalProposedLocalisation = animal.localizationOnMap
        if(!isOccupied(animalProposedLocalisation)){
            animals[animalProposedLocalisation] = animal;
        } else{
            var randomPos: Vector2d? = animals.randomFreePosition(upperRight);
            if(randomPos != null) {
                animal.localizationOnMap = randomPos;
                animals[randomPos] = animal;
            } else{
                randomPos = animals.randomPosition();
                if(randomPos != null){
                    animals.remove(randomPos);
                    animal.localizationOnMap = randomPos;
                    animals[randomPos] = animal
                } else {
                    return false;
                    }
                }
            }
        return true;
    }


    override fun move(animal: Animal, direction: MoveDirection) {
        val oldPosition = animal.localizationOnMap;
        animal.move(direction, this)

        animals.remove(oldPosition);
        place(animal);

    }

    override fun isOccupied(position: Vector2d): Boolean {
        return objectAt(position) != null;
    }

    override fun objectAt(position: Vector2d): Animal? {
        if (animals.containsKey(position)) {
            return animals[position];
        } else {
            return null;
        }
    }


    override fun canMoveTo(position: Vector2d): Boolean {
       return position.follows(lowerLeft) && position.precedes(upperRight);
    }
}