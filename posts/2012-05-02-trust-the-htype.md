title: "Trust the h^Wtype"
description: "I've recently switched (back) to compiled languages like Scala or Haskell. Doing so, I've re-discovered the joy of having a typing system and a compiler. You may want to do so too."
date: "2012-05-02"
---
## Unit testing: your own compiler

I used to write a lot of PHP code, mostly using the symfony framework. When I discovered unit testing with PHP, it was like I had discovered a new way of programming. It was not required anymore to open a web browser to check if my code was working, I could just launch a command line, and the red or green lights would be telling me if my code was working (or at least, not throwing trivial warnings, notices or exceptions). It was a revolution for me.

It took me a while to realize that for each and every project that I was doing in PHP, half of my tests were doing the job of a compiler: checking the expected output type, checking that methods names have been changed everywhere when refactoring, .... What was the point in re-inventing the wheel?

## Type inference: a taste of dynamic language

I was re-inventing the wheel because languages with a compiler were (to me) really verbose for a little added value. Having to tell to Java that _"foo"_ must be treated as a string and _2_ as an integer, and _3.2_ as a float number, and _new User()_ as a _User_ was adding a lot of boilerplate to the code:

    #!java
    String test = "foo";
    int two = 2;
    float three = 3.2;
    User user = new User();

In PHP it was more easy:

    #!php
    <?php
    $test = "foo";
    $two = 2;
    $three = 3.2;
    $user = new User();

But what I loosed in PHP was that substracting a number to a string should not be permitted:

    #!php
    <?php
    $bar = $test - $two;

But obviously in PHP it just works. I mean, it does not complain:

    #!php
    <?php
    echo $bar; // -2

And it displays ... the number -2 (seriously? ;) ).
Even if it had resulted in a warning or notice, it would only be at runtime. And I need to write a test to reproduce it.

So what about something like that (example in Scala):

    #!scala
    val test = "foo"
    val two = 2
    val three = 3.2
    val user = new User

You don't have to specify the types (like in PHP), but it complains when you try to do:

    #!scala
    val bar = test - two

Here is the error message when trying to compile:

    error: value - is not a member of java.lang.String
    val bar = test - two
                ^
    one error found

You can see that the compiler has guessed (aka inferred) the type of _test_. The type issue is then raised early, without having to write/launch tests for it. With such type inference, you have the benefit of dymamic languages (you don't have to care about defining types) with the benefit of a strong type system (check errors at compile time, reduce number of trivial unit tests).

## The compiler is your friend

If you are using a language like PHP (or some other dynamic language) and you are writing tons of unit tests to cover your (hypothetical? ;) ) refactoring, specifying types hints in function parameters (typical in PHP those days) you should really ask yourself if you are using the right tool for the job.

Using a srongly typed language with type inference (like Scala or Haskell) could save you plenty of time and ease your refactoring sessions. You'll have the unique sensation that: "once it compiles, it will most likely work". You should think twice about it. Join the hype and compile like a pro.
