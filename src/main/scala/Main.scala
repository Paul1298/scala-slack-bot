import com.slack.api.Slack

object Main extends App {
  val token = ""
  val slack = new Slack
  val methodsClient = slack.methods(token)

  val receiverEmail = "poul1298@gmail.com"
  val authorEmail = "poul1298@gmail.com"

  TaskStatusChange(authorEmail, "Before", "After").sendMessage(methodsClient, receiverEmail, "caption")
  TasksCompleted(List("1", "2", "3", "4")).sendMessage(methodsClient, receiverEmail, "caption")
  NewAssignee(authorEmail).sendMessage(methodsClient, receiverEmail, "caption")

}
