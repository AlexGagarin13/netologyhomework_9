data class User(
    val userId: UInt,
    val userName: String,
    val userLastName: String,
    var incomingMessages: List<Message>,
    var outgoingMessages: List<Message>
)