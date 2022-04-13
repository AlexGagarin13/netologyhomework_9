fun main() {
    val user1 = User(1u, "Alex", "Ford", emptyList(), emptyList())
    val user2 = User(2u, "Tom", "Alein", emptyList(), emptyList())
    val service = ChatService()
    val message1 = Message(dateTime =  "15.02", text = "Привет", senderId = 1u, receiverId = 2u )
    val message2 = Message(dateTime =  "15.02", text = "Привет", senderId = 1u, receiverId = 2u )

    service.addUser(user1)
    service.addUser(user2)
    service.addMessage(2u, 1u, message1 )
    service.addMessage(2u, 1u, message2 )

}