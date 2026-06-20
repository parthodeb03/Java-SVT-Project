import org.example.StudentAccount;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StudentAccountTest {

    @Test
    public void testAccountCreation() throws Exception {
        StudentAccount s1 = new StudentAccount();

        s1.accountCreation(
                "Partho",
                "parthodeb2003",
                "241-134-016",
                "SWE",
                6,
                "A",
                "1234",
                "1234"
        );

        assertEquals("Partho", s1.getName());
        assertNotEquals("221-115-001", s1.getId());
        assertEquals("SWE", s1.getDept());
        assertEquals(6, s1.getBatch());
        assertEquals("A", s1.getSection());
        assertEquals("241-134-016",s1.getId());


        assertThrows(Exception.class,()->{
            s1.accountCreation(
                    "Partho",
                    "parthodeb2003",
                    "241-134-016",
                    "SWE",
                    6,
                    "A",
                    "1234",
                    "1236"
            );
        });

    }

    @Test
    public void testLogin() throws Exception {
        StudentAccount s1 = new StudentAccount();

        s1.accountCreation(
                "Partho",
                "parthodeb2003",
                "241-134-016",
                "SWE",
                6,
                "A",
                "1234",
                "1234"

        );

        assertTrue(s1.logIn("parthodeb2003","1234"));
        assertFalse(s1.logIn("parthodeb016","1234"));
        assertFalse(s1.logIn("parthodeb016","1236"));

    }

    @Test
    public void testLogout() throws Exception {
        StudentAccount s1 = new StudentAccount();

        s1.accountCreation(
                "Partho",
                "parthodeb2003",
                "241-134-016",
                "SWE",
                6,
                "A",
                "1234",
                "1234"
        );

        s1.logIn("parthodeb2003","1234");
        s1.logout();

        assertFalse(s1.isLoggedIn());
    }
}