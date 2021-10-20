import com.slack.api.methods.MethodsClient
import com.slack.api.model.block.LayoutBlock

case class TasksCompletedNotification(taskList: List[String]) extends ChangeNotifications with SlackBlocks {
  def notificationMessage(caption: String): String = s"$caption completed"

  def messageContext(methodsClient: MethodsClient, caption: String): List[LayoutBlock] = List(
    sectionWithMarkdown(s"*<CaptionLink.com|$caption>* completed!"),
    taskListAsSelectMenu(taskList)
  )
}

