import com.slack.api.methods.MethodsClient
import com.slack.api.model.block.Blocks.{asBlocks, section}
import com.slack.api.model.block.LayoutBlock
import com.slack.api.model.block.composition.BlockCompositions.markdownText

case class TaskStatusChange(authorEmail: String, statusBefore: String, statusAfter: String) extends Changes with Functions {
  def messageContext(methodsClient: MethodsClient, caption: String): java.util.List[LayoutBlock] = {
    val authorId: String = lookupByEmail(methodsClient, authorEmail).getId

    asBlocks(
      section(section => section.text(markdownText(s"<@$authorId> changed *<TaskLink.com|$caption>* status:"))),
      statusChange(statusBefore, statusAfter)
    )
  }
}
