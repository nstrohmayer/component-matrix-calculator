import application.AbstractFileAnalyzer

internal class JavaFileAnalyzer(
    override val compatibleFileEnding: String = "java",
    override val concreteKeyword: String = "class ",
    override val abstractKeywords: List<String> = listOf("interface ", "abstract class "),
    override val commentStart: String = "//"
) : AbstractFileAnalyzer()

internal class KotlinFileAnalyzer(
    override val compatibleFileEnding: String = "kt",
    override val concreteKeyword: String = "class ",
    override val abstractKeywords: List<String> = listOf("interface ", "abstract class "),
    override val commentStart: String = "//"
) : AbstractFileAnalyzer()
