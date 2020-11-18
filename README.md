# Kotlin Java Annotation Bug

This project serves as a reproducer for the issue [KT-43422](https://youtrack.jetbrains.com/issue/KT-43422) with the Kotlin compiler.

#### TL;DR
Executing `./gradlew build` succeeds when it should not.  
You can examine the generated code in the `build` directory.

#### The bug
Given an annotation defined in Java, it is possible to compile Kotlin code that improperly uses it.

```java
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
public @interface ExampleJavaAnnotation {
    Class<?> value();
}
```

The following Kotlin class compiles without any complaints!

```kotlin
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
```
