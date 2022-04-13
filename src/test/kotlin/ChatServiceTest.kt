import org.junit.Test
import org.junit.Assert.*

class ChatServiceTest {

    @Test
    fun addUserSuccessfully() {
        val user1 = User(1u, "Alex", "Ford", emptyList(), emptyList())
        val service = ChatService()

        val result = service.addUser(user1)

        assertTrue(result)
    }

    @Test
    fun addUserNotSuccessfully() {
        val user1 = User(1u, "Alex", "Ford", emptyList(), emptyList())
        val service = ChatService()

        service.addUser(user1)
        val result = service.addUser(user1)

        assertFalse(result)
    }
}