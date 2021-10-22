import com.slack.api.model.block.Blocks.{context, section}
import com.slack.api.model.block.composition.BlockCompositions.{markdownText, plainText}
import com.slack.api.model.block.element.BlockElements.asContextElements
import com.slack.api.model.block.{ContextBlock, LayoutBlock}

trait SlackBlocks {
  def sectionWithMarkdown(text: String): LayoutBlock = {
    section(_.text(markdownText(text)))
  }

  def statusChange(statusBefore: String, statusAfter: String): ContextBlock = {
    context(asContextElements(
      markdownText(s"$statusBefore :arrow_right: $statusAfter"))
    )
  }

  def taskBulletedList(taskList: List[String]): LayoutBlock = {
    section(_.text(
      plainText(taskList.map("• " ++ _ ++ "\n").mkString)
    ))
  }
}
