import com.slack.api.model.block.Blocks.{actions, context}
import com.slack.api.model.block.composition.BlockCompositions.{asOptions, markdownText, plainText}
import com.slack.api.model.block.composition.OptionObject
import com.slack.api.model.block.element.BlockElements.{asContextElements, asElements, staticSelect}
import com.slack.api.model.block.{ActionsBlock, ContextBlock}

trait Functions {
  def statusChange(statusBefore: String, statusAfter: String): ContextBlock = {
    context((context: ContextBlock.ContextBlockBuilder) => context
      .elements(asContextElements(
        markdownText(s"$statusBefore :arrow_right: $statusAfter")
      ))
    )
  }

  def taskListAsSelectMenu(taskList: List[String]): ActionsBlock = {
    def buildOption(text: String): OptionObject = {
      OptionObject.builder()
        .text(plainText(text))
        .build()
    }

    actions((actions: ActionsBlock.ActionsBlockBuilder) => actions
      .elements(asElements(
        staticSelect(select => select
          .placeholder(plainText("Tasks"))
          .options(asOptions(
            taskList.map(buildOption): _*
          )))
      )))
  }
}
