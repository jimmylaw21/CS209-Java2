# CS209 Lecture 3

## Functional Programming

![image-20230228172546770](C:\Users\jimmylaw21\AppData\Roaming\Typora\typora-user-images\image-20230228172546770.png)

• Functional programming is a **programming** **paradigm** (编程范式)

• A programming paradigm refers to the way of thinking about 

things and the way of solving problems

![image-20230228172624812](C:\Users\jimmylaw21\AppData\Roaming\Typora\typora-user-images\image-20230228172624812.png)

• Basic unit of computation is function

• Primary characteristics

• Functions are treated as first-class citizens

• No side effects

• Immutability

• Recursion

### First-class functions

Functions are treated like any other variables

![image-20230228172727619](C:\Users\jimmylaw21\AppData\Roaming\Typora\typora-user-images\image-20230228172727619.png)

**Functions can be** **passed as arguments**

![image-20230228172738717](C:\Users\jimmylaw21\AppData\Roaming\Typora\typora-user-images\image-20230228172738717.png)

**Functions can be assigned** **to a variable**

![image-20230228172750157](C:\Users\jimmylaw21\AppData\Roaming\Typora\typora-user-images\image-20230228172750157.png)

**Functions can be returned**

### Higher Order Functions

• A higher order function

​	• Takes another function as argument

​	• Returns another function as result

• A common usage scenario: data processing

​	• map(), filtering(), reduce(), etc.

​	• More on these later

### No Side Effects

• Side effects: Events that are caused by a system with a 

​	**limited scope**, whose effects are felt **outside of that scope**

• Pure functions have no side effects (cannot change external 

​	states)

|   **Impure function**    |          **Side Effects**          |
| :----------------------: | :--------------------------------: |
|   writeFile(filename)    |     External files are changed     |
|  updateDatabase(table)   | External database table is changed |
| sendAjaxRequest(request) |  External server state is changed  |

• Pure functions **always** produce the same output for the same input (regardless of the history)

|   **Impure function**    | **Input** |          **Possible Output**           |
| :----------------------: | :-------: | :------------------------------------: |
|   writeFile(filename)    | filename  |  Success or failures given file state  |
|   queryDatabase(table)   |   table   |  Different results given table state   |
| sendAjaxRequest(request) |  request  | Different responses given server state |

![image-20230228173451002](C:\Users\jimmylaw21\AppData\Roaming\Typora\typora-user-images\image-20230228173451002.png)

### Immutability

• Variables, once defined, never change their values (eliminate 

side effects)

• Pure functional programs do not have assignment statements

![image-20230228173626357](C:\Users\jimmylaw21\AppData\Roaming\Typora\typora-user-images\image-20230228173626357.png)

### What about loops?

• No “while” or “for” loop in functional programming

• How do we perform iterations though?

![image-20230228173657226](C:\Users\jimmylaw21\AppData\Roaming\Typora\typora-user-images\image-20230228173657226.png)

### Advantages of Functional Programming

• Easy to debug, test, and parallelize

​	• Same input => same output (deterministic)

​	• No side effects

​	• Data are immutable

• Complexity is dramatically reduced (architectural simplicity)

​	• The only interaction with the external system is via the argument and return value (API)

**OO style**: Object methods interact with the object states

**Procedural style**: external state is often manipulated from within the function

## Lambda Expressions

• Syntax

• Type inference

• Use cases

• Method references

• Java Functional Interfaces

### Java Lambda Expressions

• Introduced in Java 8

​		• Java’s first step into functional programming

• A Java lambda expression

​		• is an anonymous function with no name/identifier

​		• can be created without belonging to any class

​		• can be passed as a parameter to another function

​		• are callable anywhere in the program

### Lambda Syntax

![image-20230228174117474](C:\Users\jimmylaw21\AppData\Roaming\Typora\typora-user-images\image-20230228174117474.png)

### Lambda Usage

• Lambda: a shortcut to define an implementation of a 

​	functional interface

• Functional interface is an interface with a single abstract 

​	method (e.g., Comparator<T> interface only has one 

​	abstract method int compare(T o1, T o2))

### Example: sorting a string list by element’s length

![image-20230228174407818](C:\Users\jimmylaw21\AppData\Roaming\Typora\typora-user-images\image-20230228174407818.png)

![image-20230228174412262](C:\Users\jimmylaw21\AppData\Roaming\Typora\typora-user-images\image-20230228174412262.png)

**Classic way**

• Explicitly creating a class that implements the Comparator interface

• Verbose

![image-20230228174427519](C:\Users\jimmylaw21\AppData\Roaming\Typora\typora-user-images\image-20230228174427519.png)

**Using the anonymous class**

• Don’t have to declare a name for it

• Declare and instantiate the class at the same time

• Anonymous class can be used only once

• Shorter code, but still verbose

**Using lambda in Java 8**

```java
Collections.sort(strList, (s1, s2) -> Integer.compare(s1.length(), s2.length()));
```

### Matching Lambdas to Functional Interfaces

![image-20230228174751769](C:\Users\jimmylaw21\AppData\Roaming\Typora\typora-user-images\image-20230228174751769.png)

1. Matching lambda to interface

​		• By comparing caller method’s parameter type

2. Matching lambda to interface’s abstract method

​		• The interface must have only one abstract (unimplemented) method

3. Matching method parameters and return type

​		• Parameter types and return type must match

### Matching lambda to interface’s abstract method

• From Java 8, an interface could have both default methods and static methods (method with implementations/concrete methods)

• A functional interface must have only one abstract (unimplemented) method

• But it can have multiple default and static methods

![image-20230228175044040](C:\Users\jimmylaw21\AppData\Roaming\Typora\typora-user-images\image-20230228175044040.png)

**Example**

• **This interface can be** **implemented by lambda**

• **Although it has 3 methods,** **only 1 is unimplemented**

### Lambda Parameters

• Zero Parameter

```java
() -> System.out.println("No parameter");
```

• One Parameter

```java
(param) -> System.out.println("1 parameter:"+ param);

param -> System.out.println("1 parameter:"+ param);
```

• Multiple Parameters

```java
(param1, param2) -> System.out.println("2 parameters:" + param1 + ", " + param2);
```

### Type Inference

 - Compiler obtains most of the type information from *generics*

 - Compiler won’t be able to infer types if raw type (e.g., List) is used instead of the parameterized type (e.g., List<String>)

 - ![image-20230228175336917](C:\Users\jimmylaw21\AppData\Roaming\Typora\typora-user-images\image-20230228175336917.png)

![image-20230228175329745](C:\Users\jimmylaw21\AppData\Roaming\Typora\typora-user-images\image-20230228175329745.png)

![image-20230228175340170](C:\Users\jimmylaw21\AppData\Roaming\Typora\typora-user-images\image-20230228175340170.png)

Use List<String> strList = new ArrayList<String>() to remove all the warnings and errors!

### Lambda Function Body

 - One statement

```java
(param) -> System.out.println("1 parameter:"+ param);
```

 - Multiple statements (with curly braces)

```java
(param) -> {
	System.out.println("1 parameter:"+ param);
	return 0;
}
```

 -  Return 

```java
(param1, param2) -> {return param1 > param2;
(param1, param2) -> param1 > param2;
```

### Does the code work?

![image-20230228175805665](C:\Users\jimmylaw21\AppData\Roaming\Typora\typora-user-images\image-20230228175805665.png)

### More Use Cases

 - Use Case I: create & “instantiate” a functional interface

```java
public interface MyInterface{
	// abstract method
	double getPiValue();
}
MyInterface ref = () -> 3.1415;
System.out.println("Pi = " + ref.getPiValue());}
```

 - The lambda expression implements the abstract method; therefore, we 

could “instantiate” the interface

 -  Strictly, we are instantiating an anonymous class that implements 

MyInterface

 - Use Case II: executing the same operation when iterating elements

![image-20230228180604699](C:\Users\jimmylaw21\AppData\Roaming\Typora\typora-user-images\image-20230228180604699.png)

### Method Reference
