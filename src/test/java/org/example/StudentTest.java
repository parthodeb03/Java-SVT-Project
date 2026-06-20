package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StudentTest {

    @Test
    void testSettersAndGetters() {

        Student student = new Student();

        student.setName("Partho");
        student.setId("241-134-016");
        student.setDept("SWE");
        student.setBatch(6);
        student.setSection("A");

        assertEquals("Partho", student.getName());
        assertEquals("241-134-016", student.getId());
        assertEquals("SWE", student.getDept());
        assertEquals(6, student.getBatch());
        assertEquals("A", student.getSection());

    }


}