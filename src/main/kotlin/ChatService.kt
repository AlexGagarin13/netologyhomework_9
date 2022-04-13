class ChatService {
    private var chats: MutableMap<List<UInt>, MutableList<Message>> = mutableMapOf()
    private var userslist = mutableListOf<User>()

    fun addUser(user: User): Boolean {
        if (userslist.contains(user)) {
            println("Данный пользователь уже существует!")
            return false
        } else {
            userslist.add(user)
        }
        return true
    }

    fun addMessage(receiverId: UInt, senderId: UInt, message: Message): Int {
        val chatParticipants = mutableListOf(receiverId, senderId)
        return if (chats.contains(chatParticipants)) {
            chats.getValue(chatParticipants)
                .add(message.copy(id = message.id + 1U))
            println("Сообщений ${chats.size}")
            chats.size
        }else {
            val messages = mutableListOf(message)
            chats[chatParticipants] = messages
            println(chats.size)
            chats.size
        }
    }
}