package activities;

import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Activity1 {

    static ArrayList<String> list;

    @BeforeEach
    public void setUp(){
        list = new ArrayList<String>();
        list.add("alpha");
        list.add("beta");
    }

    @Test
    @Order(1)
    public void insertTest(){
        assertEquals(2, list.size(), "Wrong size");
        list.add("gamma");
        assertEquals(3, list.size(), "Wrong size");
        assertEquals("alpha", list.get(0), "Wrong element");
        assertEquals("beta", list.get(1), "Wrong element");
        assertEquals("gamma", list.get(2), "Wrong element");

    }

    @Test
    @Order(2)
    public void replaceTest(){
        assertEquals(2, list.size(), "Wrong size");
        list.add("charles");
        System.out.println(list);
        assertEquals(3, list.size(), "Wrong size");
        list.set(1, "berry");
        assertEquals("alpha", list.get(0), "Wrong element");
        assertEquals("berry", list.get(1), "Wrong element");
        assertEquals("charles", list.get(2), "Wrong element");

    }
}
