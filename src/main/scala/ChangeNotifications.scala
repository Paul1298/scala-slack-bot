import com.slack.api.methods.MethodsClient
import com.slack.api.methods.request.chat.ChatPostMessageRequest
import com.slack.api.methods.request.users.UsersLookupByEmailRequest
import com.slack.api.model.User
import com.slack.api.model.block.Blocks.asBlocks
import com.slack.api.model.block.LayoutBlock

trait ChangeNotifications {
  def notificationMessage(caption: String): String

  def messageContext(methodsClient: MethodsClient, caption: String, captionLink: String): List[LayoutBlock]

  def lookupByEmail(methodsClient: MethodsClient, userEmail: String): User = {
    val request = UsersLookupByEmailRequest.builder()
      .email(userEmail)
      .build()
    val response = methodsClient.usersLookupByEmail(request)

    if (response.isOk) {
      response.getUser
    }
    else {
      val errorCode = response.getError
      throw new Exception(s"$errorCode in this workspace with email: $userEmail")
    }
  }

  def sendMessage(methodsClient: MethodsClient, receiverEmail: String, caption: String, captionLink: String): Unit = {
    val request = ChatPostMessageRequest
      .builder()
      .channel(lookupByEmail(methodsClient, receiverEmail).getId)
      .text(notificationMessage(caption))
      .blocks(asBlocks(messageContext(methodsClient, caption, captionLink): _*))
      .build()

    methodsClient.chatPostMessage(request)
  }
}
