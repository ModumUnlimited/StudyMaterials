This is a list of some random features that are weird in Java. Feel free to add more.

# Java Quirks
## Printing
When you print in Java, let's say `println(1 + 2 + "Hallo" + 2 + 1)`, you get `3Hallo21` as output. *This is because Java first sees `1+2` and performs addition, but upon seeing the string it switches to string concatenation*.

## Polymorphism
Assume you have the following classes:

```java
static  class Animal{
	void makeSound(){
		print("some generic sound");
	}
}

static  class Horse extends Animal{
	void directions(){
		print("do you know da wae");
	}
}
```

What will `Animal horse1= new Horse(); horse1.directions();` print? It will give you an error . It must first be upcasted: `Animal horse1 = new Horse(); ((Horse)horse1).directions();`


## Operator Precedence
### Logic
The following is a list of how strong operators bind;
1. `==`
2. `&&`
3. `||`

### Arithmetic Precedence
Some arithmetic expressions such as `*`, `/`, `%` have the same precedence. So what happens when we see an expression such as `15/5%3` will be evaluated as `((15/5)%3)` from left to right.


## Multidimensional Arrays
All of the following are equivalent:

```java
int[][] arr1 = ...;
int[] arr2[] = ...;
int arr3[][] = ...;
```

But in order to stay consistent you should always use the first.

## Boxed Integers
Assume we have the following piece of code:

```java
Integer one1 = 1;
Integer one2 = 1;
System.out.println(one1 == one2);
```

it will print `true`. However what happens now:

```java
Integer one1 = 12222;
Integer one2 = 12222;
System.out.println(one1 == one2);
```

It will print `false`. To explain this we have to understand how Java deals with these boxed integers. Every integer between -128 and 127 gets an Integer Object assigned to it when the JVM boots. Whenever we create an integer with a value in this range it will use the object it has already created. But when we go out of this range it will actually call the constructor and *build* an integer object for every assignment. This explains the behaviour above.

## Shortcut (Kurzschluss)
In order to evaluate an expression of booleans (e.g. for an if-statement), Java will process it from left to right (while still following the appropriate binding strenghts, as in Operator Precedence/Logic). However, it will also attempt to optimise the runtime by drawing a conclusion for the overall value of the expression as soon as possible.

For series of `&&` expressions, Java will escape from the evaluation as soon as one of the terms is `false`, returning an overall `false` for the expression.
For `||`, Java will escape as soon as one of them is `true`, returning an overall `true`.

```java
if((a/b > 0) && (b != 0)) {}
```

In the above code, should `b` be 0, the program will encounter an ArithmeticException due to a div by 0 (unless `a` is a double, in which case we'd compare infinity>0 - another of java's quirks).

```java
if((b != 0) && (a/b > 0)) {}
```

In this version of the (nearly)equivalent code (i.e. it serves the same intended function), should `b` be 0, java will escape from the evaluation after finding that `b != 0` is false, and the program will continue on to any `else` blocks or bypass the `if` block entirely. No exception will be produced as `a/b > 0` will never be evaluated.

This quirk is important to understand as if one of the terms should simultaneously produce a change in a variable, such as a term `++a > 0`, the evaluation or bypassing of it can produce an offset of 1 from the expected value of `a`.

## Methods and Types

```java
public class B {
	public void foo(B obj) {
		System.out.print("B1 ");
	}
	public void foo(C obj) {
		System.out.print("B2 ");
	}
}

public class C extends B {
  
  public void foo(B obj) {
    System.out.print("C1 ");
  }
  public void foo(C obj) {
    System.out.print("C2 ");
  }

  public static void main(String[] args) {
    B c = new C();
    B b = new B();
    b.foo(c);
    c.foo(b);
    c.foo(c);
  }
}
```

**Output:**

`B1 C1 C1`

*Example shamelessly stolen from Mickey Vänskä's practice slides (Week 11)*

The reason for this output is that **while methods resolve on the static type of the argument, they resolve on the dynamic type of the receiver**.

In this code, all static types are of `B`, therefore no calls with `System.out.print(*2 );` are made.

Simply changing the above code to

```
...
C c = new C();
B b = new B();
...
```

would change the output to

`B2 C1 C2`


Note that changing it to

```
C c = new C();
B b = c;
```

would result in

`C2 C1`