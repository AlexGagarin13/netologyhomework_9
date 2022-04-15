class ChatService {
    private var chats: MutableMap<List<UInt>, MutableList<Message>> = mutableMapOf()
    private var userslist = mutableListOf<User>()
    private var lastId = 1U

    fun addUser(user: User): Boolean {
        if (userslist.contains(user)) {
            println("Данный пользователь уже существует!")
            return false
        } else {
            userslist.add(user)
        }
        return true
    }

    fun addMessage(message: Message): Int {
        val chatParticipants = mutableListOf(message.receiverId, message.senderId)
        val newMessage = message.copy(id = lastId++)
        if (!chats.containsKey(chatParticipants) && !chats.containsKey(chatParticipants.reversed())) {
            chats[chatParticipants] = mutableListOf(newMessage)
        } else {
            chats.forEach { (key, value) ->
                if (key.contains(message.senderId) && key.contains(message.receiverId)) {
                    chats[key] = value.plusElement(newMessage) as MutableList<Message>
                }
            }
        }
        return chats.size
    }

    fun deleteMessage(messageId: UInt): Boolean {
        val externalIterator = chats.iterator()
        externalIterator.forEach { entry ->
            val interiorIterator = entry.value.iterator()
            interiorIterator.forEach { message: Message ->
                if (message.id == messageId) {
                    val n = entry.value.filterNot { it.id == messageId } as MutableList
                    chats[entry.key] = n
                    if (entry.value.isEmpty()) {
                        externalIterator.remove()
                    }
                    return true
                }
            }
        }
        println("Сообщения с таким ID не найдено!!")
        return false
    }

    fun deleteChat(chatId: List<UInt>): Boolean {
        val iterator = chats.iterator()
        iterator.forEach {
            if (it.key == chatId || it.key == chatId.reversed()) {
                iterator.remove()
                return true
            }
        }
        println("Чат с данным Id не найден!!")
        return false
    }

    fun editingMessage(updatedMessage: Message): Boolean {
        chats.forEach { (_: List<UInt>, value: MutableList<Message>) ->
            value.forEach { message: Message ->
                if (message.id == updatedMessage.id) {
                    val newMessage = message.copy(
                        dateTime = updatedMessage.dateTime,
                        text = updatedMessage.text,
                        readStatus = true
                    )
                    value[value.indexOf(message)] = newMessage
                    return true
                }
            }
        }
        println("Сообщения с таким ID не найдено!!")
        return false
    }

    fun getChats(): MutableMap<Int, MutableList<Message>> {
        val chatList = mutableMapOf<Int, MutableList<Message>>()
        var count = 1
        chats.forEach { (_, value) ->
            chatList[count] = value
            count += 1
        }
        if(chatList.isEmpty()){
            println("Нет сообщений..")
        }
        return chatList
    }

    fun getUnreadChats(userId: UInt): MutableList<List<Message>> {
        val unreadChatList = mutableListOf<List<Message>>()

        chats.forEach { (key, value) ->
            if (key.contains(userId)) {
                val newList = value.filter { it.receiverId == userId && !it.readStatus }
                unreadChatList.add(newList)
            }
        }

        val iterator = unreadChatList.iterator()
        iterator.forEach {
            if (it.isEmpty()) {
                iterator.remove()
            }
        }
        return unreadChatList
    }

    fun getMessagesFromChat(chatId: List<UInt>, lastMessageId: UInt, numberOfMessages: Int): List<Message> {
        var chatMessageList = listOf<Message>()
        chats.forEach { (key: List<UInt>, value: List<Message>) ->
            if (key == chatId) {
                chatMessageList =
                    value.filter { it.id >= lastMessageId }.subList(0, numberOfMessages)

                chatMessageList.forEach { message ->
                    value[value.indexOf(message)] = message.copy(readStatus = true)
                }
            }
        }
        return chatMessageList
    }
}