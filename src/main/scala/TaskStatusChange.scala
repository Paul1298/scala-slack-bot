import com.slack.api.methods.MethodsClient
import com.slack.api.methods.request.chat.ChatPostMessageRequest
import com.slack.api.model.block.Blocks.{asBlocks, context, section}
import com.slack.api.model.block.ContextBlock
import com.slack.api.model.block.composition.BlockCompositions.markdownText
import com.slack.api.model.block.element.BlockElements.asContextElements

class TaskStatusChange(methodsClient: MethodsClient) extends ClickUpChanges(methodsClient) {
  // todo add User who change status (name / slack link)
  override def sendMessage(userEmail: String, caption: String, statusBefore: String, statusAfter: String): Unit = {
    val message = asBlocks(
      section(section => section.text(markdownText(s"User changed *<CaptionLink.com|$caption>* status:"))),
      context((context: ContextBlock.ContextBlockBuilder) => context
        .elements(asContextElements(
          markdownText(s"$statusBefore :arrow_right: $statusAfter")
        ))
      )
    )

    val request = ChatPostMessageRequest
      .builder()
      .channel(lookupByEmail(userEmail))
      .text("") // notification message
      .blocks(message)
      .build()

    methodsClient.chatPostMessage(request)
  }
}
