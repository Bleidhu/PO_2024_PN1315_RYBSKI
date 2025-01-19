import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNot
import io.kotest.matchers.shouldNotBe
import org.example.Animal
import org.example.BouncyMap
import org.example.MoveDirection
import org.example.Vector2d

class BouncyMapTest : ShouldSpec({
    should("place animal in empty map"){
        val bouncyMap = BouncyMap(height = 5, width = 5)
        val animal = Animal(Vector2d(0, 0), 0)

        bouncyMap.place(animal) shouldBe true;
    }

    should("be able to move to valid position"){
        val bouncyMap = BouncyMap(height = 5, width = 5)
        val validPosition = Vector2d(1,1)

        bouncyMap.canMoveTo(validPosition) shouldBe true;
    }

    should("not be able to move to invalid position"){
        val bouncyMap = BouncyMap(height = 5, width = 5)
        val invalidPosition = Vector2d(7,7)

        bouncyMap.canMoveTo(invalidPosition) shouldBe false;
    }

    should("not place animal on top of other"){
        val bouncyMap = BouncyMap(height = 5, width = 5)
        val animal1 = Animal(Vector2d(1,1), 0)
        val animal2 = Animal(Vector2d(1,1), 1)

        bouncyMap.place(animal1) shouldBe true;
        bouncyMap.place(animal2) shouldBe true;
        animal1.localizationOnMap shouldBe Vector2d(1,1);
        animal2.localizationOnMap shouldNotBe  Vector2d(1,1);
    }

    should("update animal position if it is in the map"){
        var animal = Animal(Vector2d(1,1), 0)
        val bouncyMap = BouncyMap(height = 5, width = 5)
        bouncyMap.place(animal) shouldBe true;
        animal.move(MoveDirection.FORWARD, bouncyMap)
        bouncyMap.place(animal) shouldBe true;
        animal.localizationOnMap shouldBe Vector2d(1,2);
        bouncyMap.objectAt(Vector2d(1,1)) shouldBe null;

    }

})