This is a list of some random features that are weird in Java. Feel free to add more.

# Java Quirks
## Printing
When you print in Java, let's say `println(1 + 2 + "Hallo" + 2 + 1)`, you get `3Hallo21` as output. *This is because Java first sees `1+2` and performs addition, but upon seeing the string it switches to string concatenation*.

## Polymorphism
Assume you have the following classes:
```
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
```
int[][] arr1 = ...;
int[] arr2[] = ...;
int arr3[][] = ...;
```
But in order to stay consistent you should always use the first.

## Boxed Integers
Assume we have the following piece of code:
```
Integer one1 = 1;
Integer one2 = 1;
System.out.println(one1 == one2);
```
it will print `true`. However what happens now:
```
Integer one1 = 12222;
Integer one2 = 12222;
System.out.println(one1 == one2);
```
It will print `false`. To explain this we have to understand how Java deals with these boxed integers. Every integer between -128 and 127 gets an Integer Object assigned to it when the JVM boots. Whenever we create an integer with a value in this range it will use the object it has already created. But when we go out of this range it will actually call the constructor and *build* an integer obejct for every assignment. This explains the behaviour above.
