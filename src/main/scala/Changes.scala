import com.slack.api.methods.MethodsClient
import com.slack.api.methods.request.chat.ChatPostMessageRequest
import com.slack.api.methods.request.users.UsersLookupByEmailRequest
import com.slack.api.model.User
import com.slack.api.model.block.LayoutBlock

trait Changes {
  def messageContext(methodsClient: MethodsClient, caption: String): java.util.List[LayoutBlock]

  def lookupByEmail(methodsClient: MethodsClient, userEmail: String): User = {
    val request = UsersLookupByEmailRequest.builder()
      .email(userEmail)
      .build()
    val response = methodsClient.usersLookupByEmail(request)

    if (response.isOk) {
      response.getUser
    }
    else {
      //todo
      val errorCode = response.getError // e.g., "invalid_auth", "channel_not_found"
      println(response)
      throw new Exception
    }
  }

  def sendMessage(methodsClient: MethodsClient, receiverEmail: String, caption: String): Unit = {
    val request = ChatPostMessageRequest
      .builder()
      .channel(lookupByEmail(methodsClient, receiverEmail).getId)
      .text("") // notification message
      .blocks(messageContext(methodsClient, caption))
      .build()

    methodsClient.chatPostMessage(request)
  }
}
