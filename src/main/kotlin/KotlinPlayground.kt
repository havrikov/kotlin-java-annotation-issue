class KotlinPlayground {
    @ExampleJavaAnnotation("wrong type")
    private val foo: String = ""

    @ExampleJavaAnnotation(Nonexistent::class)
    internal val bar: Int = 42

    @ExampleJavaAnnotation(Any, Strange, java.util.List<*> + "of things", 42)
    val baz: Boolean = false

    @ExampleJavaAnnotation({ `an entire` -> borked(lambda) })
    var qux: Int? = null
}
