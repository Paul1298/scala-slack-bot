import com.slack.api.methods.MethodsClient
import com.slack.api.model.block.LayoutBlock

case class NewAssigneeNotification(authorEmail: String) extends ChangeNotifications with SlackBlocks {
  def notificationMessage(caption: String): String = s"Now you are assignee of '$caption'"

  def messageContext(methodsClient: MethodsClient, caption: String, captionLink: String): List[LayoutBlock] = {
    val authorId: String = lookupByEmail(methodsClient, authorEmail).getId

    List(sectionWithMarkdown(s"Now you are assignee of *<$captionLink|$caption>* created by <@$authorId>."))
  }
}
