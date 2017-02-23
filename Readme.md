Spiegel: create Java objects with Java objects
==============================================

Spiegel is a library that allows you to create and modify the state of Java
objects using objects. A class that implements Spiegel's interfaces can be
asked these questions:

- What elements do I need to create a new instance of this class?
- What are their possible values?
- What elements of an object's state can I modify?

All these questions can be answered (more or less) using Java's reflection
capabilities. Spiegel is a wrapper around these capabilities to make their
use easier.

## Example

Suppose you have a class like this one:

    class Foo
    {
      int a;
      int b;
      Bar c;
      
      public Foo(int a, String b, Bar c)
      {
        this.a = a;
        this.b = b.length();
        this.c = c;
      }
    }

A user in your program 

<!-- :wrap=hard:maxLineLen=76: -->