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

    @Test
    fun addMessageNewChat() {
        val service = ChatService()
        val user1 = User(1u, "Alex", "Ford", emptyList(), emptyList())
        val user2 = User(2u, "Tom", "Alein", emptyList(), emptyList())
        val message1 = Message(dateTime = "15.02.22", text = "Привет", senderId = 1u, receiverId = 2u)

        service.addUser(user1)
        service.addUser(user2)
        val result = service.addMessage(message1)

        assertEquals(1, result)
    }

    @Test
    fun addMessageExistingChat() {
        val service = ChatService()
        val user1 = User(1u, "Alex", "Ford", emptyList(), emptyList())
        val user2 = User(2u, "Tom", "Alein", emptyList(), emptyList())
        val message1 = Message(dateTime = "15.02.22", text = "Привет", senderId = 1u, receiverId = 2u)
        val message2 = Message(dateTime = "15.02.22", text = "Привет", senderId = 2u, receiverId = 1u)

        service.addUser(user1)
        service.addUser(user2)
        service.addMessage(message1)
        val result = service.addMessage(message2)

        assertEquals(1, result)
    }

    @Test
    fun deleteMessageTrue() {
        val service = ChatService()
        val user1 = User(1u, "Alex", "Ford", emptyList(), emptyList())
        val user2 = User(2u, "Tom", "Alein", emptyList(), emptyList())
        val message1 = Message(dateTime = "15.02.22", text = "Привет", senderId = 1u, receiverId = 2u)

        service.addUser(user1)
        service.addUser(user2)
        service.addMessage(message1)
        val result = service.deleteMessage(1u)

        assertTrue(result)
    }

    @Test
    fun deleteMessageFalse() {
        val service = ChatService()
        val user1 = User(1u, "Alex", "Ford", emptyList(), emptyList())
        val user2 = User(2u, "Tom", "Alein", emptyList(), emptyList())
        val message1 = Message(dateTime = "15.02.22", text = "Привет", senderId = 1u, receiverId = 2u)

        service.addUser(user1)
        service.addUser(user2)
        service.addMessage(message1)
        val result = service.deleteMessage(2u)

        assertFalse(result)
    }

    @Test
    fun deleteChatTrue() {
        val service = ChatService()
        val user1 = User(1u, "Alex", "Ford", emptyList(), emptyList())
        val user2 = User(2u, "Tom", "Alein", emptyList(), emptyList())
        val message1 = Message(dateTime = "15.02.22", text = "Привет", senderId = 1u, receiverId = 2u)

        service.addUser(user1)
        service.addUser(user2)
        service.addMessage(message1)
        val result = service.deleteChat(listOf(1u, 2u))

        assertTrue(result)
    }

    @Test
    fun deleteChatFalse() {
        val service = ChatService()
        val user1 = User(1u, "Alex", "Ford", emptyList(), emptyList())
        val user2 = User(2u, "Tom", "Alein", emptyList(), emptyList())
        val message1 = Message(dateTime = "15.02.22", text = "Привет", senderId = 1u, receiverId = 2u)

        service.addUser(user1)
        service.addUser(user2)
        service.addMessage(message1)
        val result = service.deleteChat(listOf(1u, 3u))

        assertFalse(result)
    }

    @Test
    fun editingMessageTrue() {
        val service = ChatService()
        val user1 = User(1u, "Alex", "Ford", emptyList(), emptyList())
        val user2 = User(2u, "Tom", "Alein", emptyList(), emptyList())
        val message1 = Message(dateTime = "15.02.22", text = "Привет", senderId = 1u, receiverId = 2u)
        val updatedMessage = Message(id = 1u, dateTime = "15.02.22", text = "Привет!", senderId = 1u, receiverId = 2u)

        service.addUser(user1)
        service.addUser(user2)
        service.addMessage(message1)
        val result = service.editingMessage(updatedMessage)

        assertTrue(result)
    }

    @Test
    fun editingMessageFalse() {
        val service = ChatService()
        val user1 = User(1u, "Alex", "Ford", emptyList(), emptyList())
        val user2 = User(2u, "Tom", "Alein", emptyList(), emptyList())
        val message1 = Message(dateTime = "15.02.22", text = "Привет", senderId = 1u, receiverId = 2u)
        val updatedMessage = Message(id = 2u, dateTime = "15.02.22", text = "Привет!", senderId = 1u, receiverId = 2u)

        service.addUser(user1)
        service.addUser(user2)
        service.addMessage(message1)
        val result = service.editingMessage(updatedMessage)

        assertFalse(result)
    }

    @Test
    fun getChats() {
        val service = ChatService()
        val user1 = User(1u, "Alex", "Ford", emptyList(), emptyList())
        val user2 = User(2u, "Tom", "Alein", emptyList(), emptyList())
        val message1 = Message(id = 1U, dateTime = "15.02.22", text = "Привет", senderId = 1u, receiverId = 2u)


        service.addUser(user1)
        service.addUser(user2)
        service.addMessage(message1)
        val result = service.getChats()

        assertEquals(mutableMapOf(Pair((1), mutableListOf(message1))), result)
    }

    @Test
    fun getUnreadChats() {
        val service = ChatService()
        val user1 = User(1u, "Alex", "Ford", emptyList(), emptyList())
        val user2 = User(2u, "Tom", "Alein", emptyList(), emptyList())
        val message1 = Message(id = 1U, dateTime = "15.02.22", text = "Привет", senderId = 1u, receiverId = 2u)
        val user3 = User(3u, "Lucy", "Ford", emptyList(), emptyList())
        val message2 = Message(id = 2U, dateTime = "15.02.22", text = "Привет", senderId = 3u, receiverId = 2u)

        service.addUser(user1)
        service.addUser(user2)
        service.addUser(user3)
        service.addMessage(message1)
        service.addMessage(message2)
        val result = service.getUnreadChats(2U)

        assertEquals(2, result.size)
    }

    @Test
    fun getMessagesFromChat() {
        val service = ChatService()
        val user1 = User(1u, "Alex", "Ford", emptyList(), emptyList())
        val user2 = User(2u, "Tom", "Alein", emptyList(), emptyList())
        val message1 = Message(id = 1U, dateTime = "15.02.22", text = "Привет", senderId = 1u, receiverId = 2u)

        service.addUser(user1)
        service.addUser(user2)
        service.addMessage(message1)
        val result = service.getMessagesFromChat(listOf(2u, 1u), 1u, 1)

        assertEquals(listOf(message1), result)
    }
}