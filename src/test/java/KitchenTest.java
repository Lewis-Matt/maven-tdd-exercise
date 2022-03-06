import org.junit.*;
import static org.junit.Assert.*;

public class KitchenTest {

    Kitchen emptyKitchen;
    Kitchen kitchenWithOne;
    Kitchen kitchenWithMany;
    // When writing tests, it is common to find that several tests need similar objects or variables created before they can be run. Annotating a public void method with @Before causes that method to be run before the @Test method. The @Before methods of superclasses will be run before those of the current class. It's like a default constructor for a Plain Java Object Class, and the name of the method does not really matter, but we suggest to use setUp()
    @Before
    public void setUp(){
        emptyKitchen = new Kitchen();

        kitchenWithOne = new Kitchen();
        kitchenWithOne.add("yellow corn");

        kitchenWithMany = new Kitchen();
        kitchenWithMany.add("blue corn");
        kitchenWithMany.add("yellow corn");
        kitchenWithMany.add("yellow corn");
        kitchenWithMany.add("white corn");
    }
    // The @Test annotation tells JUnit that the public void method to which it is attached can be run as a test case. To run the method, JUnit first constructs a fresh instance of the class then invokes the annotated method. Any exceptions thrown by the test will be reported by JUnit as a failure. If no exceptions are thrown, the test is assumed to have succeeded.
    @Test
    public void testIsEmpty(){
        assertTrue(emptyKitchen.isEmpty());
        assertFalse(kitchenWithOne.isEmpty());
    }

    @Test
    public void testSize(){
        assertEquals(0, emptyKitchen.size());
        assertEquals(1, kitchenWithOne.size());
        // Testing sizes is tricky, setting boundaries is always a good idea.
        // Test 0, 1 and many but not 2
        assertEquals(4, kitchenWithMany.size());
    }

    @Test
    public void testAdd(){
        // Making sure the emptyKitchen returns true
        assertTrue(emptyKitchen.isEmpty());

        // Add any tortilla to make it not empty anymore
        emptyKitchen.add("Any tortilla");

        // Making sure the emptyKitchen returns false once we add an element to it
        assertFalse(emptyKitchen.isEmpty());

        // Making sure the other instances of are not empty since they added items in the setUp method
        assertFalse(kitchenWithOne.isEmpty());
        assertFalse(kitchenWithMany.isEmpty());
    }

    @Test
    public void testRemove(){
        // Trying to pop from an empty kitchen
        emptyKitchen.pop();
        // The void method should catch the if statement and the size should be still 0
        assertEquals(0, emptyKitchen.size());

        kitchenWithOne.pop();
        assertEquals(0, kitchenWithOne.size());

        kitchenWithMany.pop();
        assertEquals(3, kitchenWithMany.size());
    }

    @Test
    public void testContains(){
        assertFalse(emptyKitchen.contains("blue corn"));
        assertTrue(kitchenWithOne.contains("yellow corn"));
        assertTrue(kitchenWithMany.contains("white corn"));
    }

}
