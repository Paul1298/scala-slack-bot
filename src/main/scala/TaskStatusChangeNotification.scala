import com.slack.api.methods.MethodsClient
import com.slack.api.model.block.LayoutBlock

case class TaskStatusChangeNotification(authorEmail: String, statusBefore: String, statusAfter: String) extends ChangeNotifications with SlackBlocks {
  def notificationMessage(caption: String): String = s"'$caption' status changed"

  def messageContext(methodsClient: MethodsClient, caption: String, captionLink: String): List[LayoutBlock] = {
    val authorId: String = lookupByEmail(methodsClient, authorEmail).getId

    List(
      sectionWithMarkdown(s"<@$authorId> changed *<$captionLink|$caption>* status:"),
      statusChange(statusBefore, statusAfter)
    )
  }

}
