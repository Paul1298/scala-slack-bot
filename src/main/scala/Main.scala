import com.slack.api.Slack

object Main extends App {

  val token = "xoxb-2483664990468-2560251786099-QDyATB3Fu4qdQTJcdaVxB3Fc"
  val slack = new Slack
  val methods = slack.methods(token)

  val a = new TaskStatusChange(methods)
  val email = "poul1298@gmail.com"

  a.sendMessage(email, "", "", "")
}
