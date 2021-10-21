import com.slack.api.model.block.Blocks.{actions, context, section}
import com.slack.api.model.block.composition.BlockCompositions.{asOptions, markdownText, plainText}
import com.slack.api.model.block.composition.OptionObject
import com.slack.api.model.block.element.BlockElements.{asContextElements, asElements, staticSelect}
import com.slack.api.model.block.{ActionsBlock, ContextBlock, LayoutBlock}

trait SlackBlocks {
  def sectionWithMarkdown(text: String): LayoutBlock = {
    section(_.text(markdownText(text)))
  }

  def statusChange(statusBefore: String, statusAfter: String): ContextBlock = {
    context(asContextElements(
      markdownText(s"$statusBefore :arrow_right: $statusAfter"))
    )
  }

  def taskListAsSelectMenu(taskList: List[String]): ActionsBlock = {
    def buildOption(text: String): OptionObject = {
      OptionObject.builder()
        .text(plainText(text))
        .build()
    }

    actions(asElements(
      staticSelect(_
        .placeholder(plainText("Tasks"))
        .options(asOptions(
          taskList.map(buildOption): _*
        )))
    ))
  }
}
