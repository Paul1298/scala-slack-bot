import com.slack.api.Slack

object Main extends App {
  val token = "xoxb-2483664990468-2560251786099-QDyATB3Fu4qdQTJcdaVxB3Fc"
  val slack = new Slack
  val methodsClient = slack.methods(token)

  val receiverEmail = "poul1298@gmail.com"
  val authorEmail = "poul1298@gmail.com"

//  TaskStatusChangeNotification(authorEmail, "Before", "After").sendMessage(methodsClient, receiverEmail, "caption", "capLink.com")
  TasksCompletedNotification(List("1", "2", "3", "4")).sendMessage(methodsClient, receiverEmail, "caption", "capLink")
//  NewAssigneeNotification(authorEmail).sendMessage(methodsClient, receiverEmail, "caption", "capLink")

}
