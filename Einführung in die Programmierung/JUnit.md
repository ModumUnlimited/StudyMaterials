__Feel free to modify this document. Your contributions are very appreciated__

# JUnit Overview
Although Junit 3,4 and 5 provide the same functionality their API's are not the same. In order to stay consistent I will focus on JUnit 4 (since this is the version we have used in class). 


## Adding tests
To write tests create a new class and give it any name. Within this class add *non-static* methods. These methods should be preceded by the [`@Test`](https://github.com/junit-team/junit4/blob/master/src/main/java/org/junit/Test.java) annotation. The annotation can be found at `org.junit.Test` and must be imported (or you write `@org.junit.Test` instead). 

## Assertions
In order to actually carry out the testing one want's to compare the program output to a reference output, for this we have `assert..()` functions which can be found in [`org.junit.Assert`](https://github.com/junit-team/junit4/blob/master/src/main/java/org/junit/Assert.java). Besides the `fail(String message)` command which always fails there are the following important `assert..()` functions.


- `assertTrue(String message, boolean condition)`  
checks if a condition is true
- `assertEquals(String message, Object expected,Object actual) `   
Will perform `expected.equals(actual) `   
- `assertArrayEquals(String message, Object[] expecteds, Object[] actuals) `   
checks if two arrays contain the same elements at the same position and have same length
- `assertSame(String message, Object expected, Object actual)`   
checks if they refer to the same object `expected == actual`

_Note that all functions also exists without the leading `String message`_ argument.

**Important**: When using these `assert..()` functions they must be imported using a static import since they are static functions and not methods. e.g. `import static org.junit.Assert.*;`


## Running tests
First, it is important to make sure that JUnit 4 has been added to the Eclipse build-path. Behind the curtain, Eclipse will add `--classpath JUNIT_PATH` to the `javac` call when compiling. However, this is just the first half of the story. Now we need to run the tests. In Eclipse you can right-click with your mouse and there will be the option `run as -> Junit test`. 

Without Eclipse one would need to write a test runner which could look as follows: (inspired by [tutorialspoint.com](https://www.tutorialspoint.com/junit/junit_executing_tests.htm))
```java
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {
   public static void main(String[] args) {
      Result result = JUnitCore.runClasses(TestJunit.class);
      for (Failure failure : result.getFailures()) {
          System.out.println(failure.toString());
      }
      System.out.println(result.wasSuccessful());
   }
}
```

