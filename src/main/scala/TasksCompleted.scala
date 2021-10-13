import com.slack.api.methods.MethodsClient
import com.slack.api.model.block.Blocks.{asBlocks, section}
import com.slack.api.model.block.LayoutBlock
import com.slack.api.model.block.composition.BlockCompositions.markdownText

case class TasksCompleted(taskList: List[String]) extends Changes with Functions {
  def messageContext(methodsClient: MethodsClient, caption: String): java.util.List[LayoutBlock] = {
    asBlocks(
      section(section => section.text(markdownText(s"*<CaptionLink.com|$caption>* completed!"))),
      taskListAsSelectMenu(taskList)
    )
  }
}

