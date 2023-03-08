# **CS209 Lecture 2**

## **• Generics** 

**• Introduced in JDK 5.0** 

**• Parameterized types: types like classes and interfaces can  be used as parameters**

**![image-20230221164953306](C:\Users\jimmylaw21\AppData\Roaming\Typora\typora-user-images\image-20230221164953306.png)**

**• E stands for “element”  (sometimes we use T)** 

**• E could be any nonprimitive type** 

**• All elements of the list  should be of type E**

**![image-20230221165143997](C:\Users\jimmylaw21\AppData\Roaming\Typora\typora-user-images\image-20230221165143997.png)**

**Avoid using raw types, but the code  List list = new ArrayList() could still compile (warning  instead of error) and run.**

### **Using Generics**

**Generic classes**

**![image-20230221165444184](C:\Users\jimmylaw21\AppData\Roaming\Typora\typora-user-images\image-20230221165444184.png)**

**Generic interfaces**

**public interface Comparable<T>**

**![image-20230221165534760](C:\Users\jimmylaw21\AppData\Roaming\Typora\typora-user-images\image-20230221165534760.png)**

**Generic methods: Methods that introduce their own type parameters**

**![image-20230221165615927](C:\Users\jimmylaw21\AppData\Roaming\Typora\typora-user-images\image-20230221165615927.png)**

### **Bounds for Type Variables**

**<T extends BoundingType>**

**• T could be any subtype of the bounding type** 

**• Both T and bounding type can be either a class or an  interface** 

**• Multiple bounds are allowed, separated by & (a class must  be the first one in the bounds list)**

**<T extends Animal & Comparable>	限制T的选取范围**

**![image-20230221170030239](C:\Users\jimmylaw21\AppData\Roaming\Typora\typora-user-images\image-20230221170030239.png)**

**限制T extends Comparable 才能使用compareTo方法**

### **Inheritance Rules for Generic Types**

**![image-20230221170406144](C:\Users\jimmylaw21\AppData\Roaming\Typora\typora-user-images\image-20230221170406144.png)**

**Compiler will complain on type mismatch: List has no relationship to List,  even though String is a subtype of Object**

### **Wildcards (通配符)**

**• Use“?”to create a relationship between generic types** 

**• List<?> could be List<Number>, List<Integer>, List<String>, etc.**

**![image-20230221170901081](C:\Users\jimmylaw21\AppData\Roaming\Typora\typora-user-images\image-20230221170901081.png)**

**• Unbounded: • List is a superclass of List for any T** 

**• Upper bounded: • List: a list of any type that is a subtype of T • Bounded by the superclass** 

**• Lower bounded: • List: a list of any type that is a supertype of T • Bounded by the subclass**

### **Type Erasure**

**![image-20230221171008634](C:\Users\jimmylaw21\AppData\Roaming\Typora\typora-user-images\image-20230221171008634.png)**

**• To be compatible with previous  versions, the implementation of Java  generics adopts the strategy of  pseudo generics** 

**• Java supports generics in syntax, but  the so -called “type erase” will be  carried out in the compilation stage  to replace all generic  representations (contents in angle  brackets) with specific types** 

**• To JVM, there is no generics at all**

## **• Abstract Data Type (ADT)** 

**Data Type: A data type is a set of values and a set of operations on those values**

**Primitive Types: values immediately map to  machine representations. operations immediately map  to machine instructions**

**![image-20230221172512591](C:\Users\jimmylaw21\AppData\Roaming\Typora\typora-user-images\image-20230221172512591.png)**

**Abstract Data Type (ADT):** 

**A type (or class) for objects whose behavior is defined by  a set of values and a set of operations.** 

**• How values are stored in memory is hidden from the client** 

**• How operations are implemented internally is hidden from client**

**![image-20230221172619435](C:\Users\jimmylaw21\AppData\Roaming\Typora\typora-user-images\image-20230221172619435.png)**

**![image-20230221172645525](C:\Users\jimmylaw21\AppData\Roaming\Typora\typora-user-images\image-20230221172645525.png)**

**![image-20230221172715179](C:\Users\jimmylaw21\AppData\Roaming\Typora\typora-user-images\image-20230221172715179.png)**

### **Operations of ADT**

**• Creators create new objects of the type (e.g., constructors)** 

**• Producers create new objects from old objects of the type • E.g., String.concat() concatenates two strings and produce a  new one**  

**• Observers takes an object of the abstract type and return  an object of a different type • E.g., List.size() returns an integer** 

**• Mutators change the object itself • E.g., List.add() changes the list • For immutable types, there is no mutator operation**

## **• Collections**

**![image-20230221173354171](C:\Users\jimmylaw21\AppData\Roaming\Typora\typora-user-images\image-20230221173354171.png)**

### **The Java  Collections  Framework**

**Collection: **

• A group of objects 

• Mainly used for data storage, data  retrieval, and data manipulation

**Framework: **

• A set of classes and interfaces which  provide a ready-made architecture.

**Collections Framework: **

• A unified architecture for representing  and manipulating collections 

• Reusable data structures & functionalities 

• Collections can be manipulated  independently of the details of their  representation

![image-20230221173902536](C:\Users\jimmylaw21\AppData\Roaming\Typora\typora-user-images\image-20230221173902536.png)

### The Iterable interface

• Iterable：可迭代的、可遍历的 

• Implementing this interface  allows an object to be the target  of the "foreach" statement.

![image-20230221173916043](C:\Users\jimmylaw21\AppData\Roaming\Typora\typora-user-images\image-20230221173916043.png)

An Iterable class could be iterated over using an Iterator

![image-20230221174338805](C:\Users\jimmylaw21\AppData\Roaming\Typora\typora-user-images\image-20230221174338805.png)

Iterators allow the  caller to remove  elements from the  underlying collection  during the iteration

![image-20230221174751600](C:\Users\jimmylaw21\AppData\Roaming\Typora\typora-user-images\image-20230221174751600.png)

![image-20230221174737009](C:\Users\jimmylaw21\AppData\Roaming\Typora\typora-user-images\image-20230221174737009.png)

### Set Interface

![image-20230221174911735](C:\Users\jimmylaw21\AppData\Roaming\Typora\typora-user-images\image-20230221174911735.png)

![image-20230221174943351](C:\Users\jimmylaw21\AppData\Roaming\Typora\typora-user-images\image-20230221174943351.png)

### List Interface

![image-20230221175008712](C:\Users\jimmylaw21\AppData\Roaming\Typora\typora-user-images\image-20230221175008712.png)

![image-20230221175022423](C:\Users\jimmylaw21\AppData\Roaming\Typora\typora-user-images\image-20230221175022423.png)

### Map Interface

![image-20230221175045285](C:\Users\jimmylaw21\AppData\Roaming\Typora\typora-user-images\image-20230221175045285.png)

![image-20230221175057535](C:\Users\jimmylaw21\AppData\Roaming\Typora\typora-user-images\image-20230221175057535.png)

### General-purpose Implementations

• The Collection framework provides several general-purpose  implementations of the Set, List , and Map interfaces 

• HashSet, ArrayList, and HashMap are most often used

![image-20230221175239240](C:\Users\jimmylaw21\AppData\Roaming\Typora\typora-user-images\image-20230221175239240.png)

### List Implementation

• ArrayList: internally uses an array to  store the elements 

![image-20230221175332877](C:\Users\jimmylaw21\AppData\Roaming\Typora\typora-user-images\image-20230221175332877.png)

• LinkedList: internally uses a doubly  linked list to store the elements.

![image-20230221175342834](C:\Users\jimmylaw21\AppData\Roaming\Typora\typora-user-images\image-20230221175342834.png)

![image-20230221175449716](C:\Users\jimmylaw21\AppData\Roaming\Typora\typora-user-images\image-20230221175449716.png)

### Map Implementation - HashMap

![image-20230221180240088](C:\Users\jimmylaw21\AppData\Roaming\Typora\typora-user-images\image-20230221180240088.png)

![image-20230221180320850](C:\Users\jimmylaw21\AppData\Roaming\Typora\typora-user-images\image-20230221180320850.png)

Overriding hashCode and equals

• hashCode() returns an integer value. By default, it converts the  internal address of the object into an integer 

• equals() checks if objects are equal. By default,  Object.equals(Object obj) { return (this == obj);} 

• If two objects are equal according to the equals(Object)  method, then calling the hashCode method on each of the two  objects must produce the same integer result (if you override  equals, you must override hashCode.).

![image-20230221180736203](C:\Users\jimmylaw21\AppData\Roaming\Typora\typora-user-images\image-20230221180736203.png)

![image-20230221180757379](C:\Users\jimmylaw21\AppData\Roaming\Typora\typora-user-images\image-20230221180757379.png)

### Choosing an Implementation – Set

![image-20230221180815101](C:\Users\jimmylaw21\AppData\Roaming\Typora\typora-user-images\image-20230221180815101.png)

### Common Implementation Behaviors 

• All implementations permit null elements, keys, and values  (except for TreeSet and TreeMap) 

• All are Serializable 

• None are synchronized (i.e., not thread-safe by default) 

  • Multiple threads could change the same collection, leading to inconsistent data 

• All have fail-fast iterators 

  • Detecting illegal concurrent modification during iteration and fail  quickly and cleanly

### Reusable Algorithms

Collections class in java represents an utility class in java.util package. It  contains exclusively static methods that operate on or return collections

![image-20230228164229955](C:\Users\jimmylaw21\AppData\Roaming\Typora\typora-user-images\image-20230228164229955.png)

### Why Generic (Reusable) Algorithms?

max() is a common algorithm for collections. But we do not want to write, test, debug this max for different types of collections

![image-20230228164445668](C:\Users\jimmylaw21\AppData\Roaming\Typora\typora-user-images\image-20230228164445668.png)

The max() algorithm is implemented to take any object that implements the Collection interface

![image-20230228164504770](C:\Users\jimmylaw21\AppData\Roaming\Typora\typora-user-images\image-20230228164504770.png)

### Sorting Algorithm

• sort() reorders a collection according to an ordering relationship

![image-20230228164818304](C:\Users\jimmylaw21\AppData\Roaming\Typora\typora-user-images\image-20230228164818304.png)

• String and Date both implement the Comparable interface (compareTo(T o)), allowing their objects to be sorted automatically

• Collections.sort(list) will throw a ClassCastException if elements do not implement Comparable

![image-20230228164907239](C:\Users\jimmylaw21\AppData\Roaming\Typora\typora-user-images\image-20230228164907239.png)

### The Comparator<T> Interface

• The Comparable interface is used to compare objects using one of their property as the default sorting order.

​	• Provide compareTo(T o)

​	• A comparable object can compare itself with another object 

• The Comparator interface is used to compare two objects of the same class by different properties

​	• Provide compare(T o1, T o2)

​	• Comparator is a separate class and external to the element type being compared

![image-20230228170046500](C:\Users\jimmylaw21\AppData\Roaming\Typora\typora-user-images\image-20230228170046500.png)

![image-20230228170052423](C:\Users\jimmylaw21\AppData\Roaming\Typora\typora-user-images\image-20230228170052423.png)

![image-20230228170057107](C:\Users\jimmylaw21\AppData\Roaming\Typora\typora-user-images\image-20230228170057107.png)

![image-20230228170100661](C:\Users\jimmylaw21\AppData\Roaming\Typora\typora-user-images\image-20230228170100661.png)

### Convenience Operation I 

• Arrays.asList(T… a) returns a List view of its array argument 

(allowing array to be “viewed” as list)

• Used as a bridge between array-based and collection-based APIs

List<String> list = Arrays.asList(new String[size]);

### Convenience Operation II 

• java.util.Collections is a class consists exclusively of static 

methods that operate on or return collections

• Collections.nCopies(int n, T o) returns an immutable list 

consisting of n copies of the object o

• Useful in combination with the List.addAll() method to grow lists

```java
List<Type> list = new ArrayList<Type>(Collections.nCopies(1000, (Type)null));

pets.addAll(Collections.nCopies(3, “cat”));
```

### Convenience Operation III

• Collections.singleton(T o) returns an immutable set containing 

only the specified object o

• Useful in combination with the removeAll() method to remove all 

occurrences of a specified element from a Collection

![image-20230228170235209](C:\Users\jimmylaw21\AppData\Roaming\Typora\typora-user-images\image-20230228170235209.png)

### Convenience Operation IV

• The Collections class provides methods to return the empty Set, List, and Map — emptySet(), emptyList(), and emptyMap()

• Used as input to methods that take a Collection of values but you 

don't want to provide any values

tourist.declarePurchases(Collections.emptySet());