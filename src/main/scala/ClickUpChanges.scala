import com.slack.api.methods.MethodsClient
import com.slack.api.methods.request.users.UsersLookupByEmailRequest

abstract class ClickUpChanges(methodsClient: MethodsClient) {
  def lookupByEmail(userEmail: String): String = {
    val request = UsersLookupByEmailRequest.builder()
      .email(userEmail)
      .build()
    val response = methodsClient.usersLookupByEmail(request)

    if (response.isOk) {
      response.getUser.getId
    }
    else {
      //todo
      val errorCode = response.getError // e.g., "invalid_auth", "channel_not_found"
      println(response)
      throw new Exception
    }
  }

  def sendMessage(userEmail: String, caption: String, statusBefore: String, statusAfter: String): Unit
}
