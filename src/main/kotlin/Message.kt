data class Message(
    val id: UInt = 0u,
    val dateTime: String,
    val text: String?,
    val readStatus: Boolean = false,
    val senderId: UInt,
    val receiverId: UInt
)