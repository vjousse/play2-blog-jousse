title: "PHP: You love it or you leave it"
date: "2012-03-07"
description: "I do love PHP. Well, to be more precise, I used to love PHP a lot, and I still respect it and people using/improving it. You can do whatever you want with PHP, from the very quick and dirty way, to the bloated framework way (oh c'mon). But it's maybe the right time to move on."
---
## PHP is great to start with

I do love PHP. Well, to be more precise, I used to love PHP a lot, and I still respect it and people using/improving it. You can do whatever you want with PHP, from the very quick and dirty way, to the bloated framework way (oh c'mon). But it's maybe the **right time to move on**.

I think this is why people of my generation love PHP, **they grew with it like I did**. They have learnt Java at school while they were doing PHP and fun stuff at home in the meantime. And to be honest, between *Struts/Spring/Hibernate/PutWhateverJavaFrameworkWorksForYouHere* and `echo $_GET['myXssAttack'];` the later was a lot more lovely.

## Then you've learnt with it, the easy way

Some time ago, sessions were implemented in PHP 4. I can remember when I was still using PHP 3 that it was a big change. We even had classes! Well, some stuff to aggregate other stuff inside the same class name to be precise. But it was great.

Then we started to have **design patterns**, like the "real java developers had"! My lovely singleton. And then, symfony1 was born (still with my lovely singleton). Symfony1 was a big change, it was putting different components together to automate a lot of things. It was like "WOW", we can do real **crazy "professional" stuff** (who said over-engineered?) with PHP! I have learnt a lot of things thanks to symfony1 and its beautiful documentation. Separation of concerns, Active Record (using Propel), XSS/CSRF protections, coding standards, unit and functional testing, etc. **I was an adult programmer.**

And the problem when you're an adult programmer is that you start to **think by yourself**. As every symfony1 guy, I was totally excited by Symfony2, just because 2 is twice better than 1. But something was strange to me. I had the feeling that, even if all the concepts were making sense, **something was wrong**. Writing tons of XML to manage separation of concerns, having to hint types in functions, but not all the types, and only in function signatures ... **I was back 10 years ago**, when I made the choice not to go with the Java boilerplate.

I realised that Symfony2 was trying to **fix the language**, and that we were using "good java practices" with the **wrong tool: PHP**.

## Then you move away from it, the easy way

I was at a point where, moving from PHP to something else was easier than it could have been for me some years ago, it's what we can call the "experience" maybe. I learnt a lot with it, but fixing the language by writing abstractions on top of it (also called Symfony components ;) ) was not going to be my next challenge. Because yes, **we need challenges**. And I am pretty convinced that it's why people love Symfony so much. It's giving challenges into something that is far from being sexy or challenging as a language: PHP.

Well guys, I totally respect what you are able to do with PHP, but you do not think that it's time to move on? To let PHP be a fabulous scripting language for simple stuff like [Rasmus said](http://toys.lerdorf.com/archives/38-The-no-framework-PHP-MVC-framework.html) and to manage complexity with languages that can handle it in a cleaner way? Scala, Haskell, Clojure, Erlang and **functional programming** in general should be your next challenge. If you make the choice to stay with PHP, please, **don't do another Java with it**.
