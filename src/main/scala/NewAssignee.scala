import com.slack.api.methods.MethodsClient
import com.slack.api.model.block.Blocks.{asBlocks, section}
import com.slack.api.model.block.LayoutBlock
import com.slack.api.model.block.composition.BlockCompositions.markdownText

case class NewAssignee(authorEmail: String) extends Changes {
  def messageContext(methodsClient: MethodsClient, caption: String): java.util.List[LayoutBlock] = {
    val authorId: String = lookupByEmail(methodsClient, authorEmail).getId

    asBlocks(
      section(section => section.text(markdownText(s"Now you are assignee of *<CaptionLink.com|$caption>* created by <@$authorId>."))),
    )
  }
}
