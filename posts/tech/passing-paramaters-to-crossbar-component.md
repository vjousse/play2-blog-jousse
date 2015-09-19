title: "How to pass paramaters to a crossbar.io/autobahn python component"
date: "2015-09-19"
---

Recently, I've be playing with [crossbar.io](http://crossbar.io), a [WAMP](http://wamp.ws/) router and [Autobahn|Python](http://autobahn.ws/python/) a library for writing WAMP compenents. It's really awesome to create [reactive apps](http://www.reactivemanifesto.org/) and to mix heterogenous applications together (python, JS, scala, PHP, whatever).

One of the main problem of crossbar/autobahn is the lack of a clear documentation. You often have to go through a lot of examples to try to understand how to do some stuff. One of the problem I had at the beginning was: "How can I pass custom options to my crossbar/authobahn component"? So here it is.

## Command line options

When you run your autobahn component in your console, you certainly have something like that in your python file:

```Python
# File test.py

from twisted.internet.defer import inlineCallbacks
from autobahn.twisted.wamp import ApplicationRunner
from autobahn.twisted.wamp import ApplicationSession

class AppSession(ApplicationSession):

    @inlineCallbacks
    def onJoin(self, details):
        # Your code here
        pass


if __name__ == '__main__':

    runner = ApplicationRunner(
        url="ws://127.0.0.1:8080/ws",
        realm="realm1")

    runner.run(AppSession)
```

Then you run it like that:

```
python test.py
```

Let's say you want to pass some parameters from the command line:

```
python test.py value1 value2
```

Now in your main code:

```Python
import sys

#...

if __name__ == '__main__':

    # Don't do this in real life
    args1 = sys.argv[1]
    args2 = sys.argv[2]

    runner = ApplicationRunner(
        url="ws://127.0.0.1:8080/ws",
        realm="realm1")

    runner.run(AppSession)
```

But how can you use the values in your AppSession class, as you are not responsible for creating it?

Well, you can pass a parameter called `extra` to the ApplicationRunner constructor and then use it in your code with `self.config.extra`.

```Python
# -*- coding: utf-8 -*-
import sys
from twisted.internet.defer import inlineCallbacks
from autobahn.twisted.wamp import ApplicationRunner
from autobahn.twisted.wamp import ApplicationSession

class AppSession(ApplicationSession):

    @inlineCallbacks
    def onJoin(self, details):
        print("Joined")

        # Do some stuff with your values here
        print(self.config.extra['v1'])
        print(self.config.extra['v2'])


if __name__ == '__main__':

    args1 = sys.argv[1]
    args2 = sys.argv[2]

    runner = ApplicationRunner(
        url="ws://127.0.0.1:8080/ws",
        realm="realm1",
        extra={'v1':args1, 'v2':args2})

    runner.run(AppSession)
```


## Crossbar configuration options

If you run your component in production using crossbar.io and not the command line, you can pass the parameters in your crossbar.io config file:

```Json

      {
         "type": "container",
         "options": {
            "pythonpath": [".."]
         },
         "components": [
            {
               "type": "class",
               "classname": "mithril.mithril.AppSession",
               "realm": "realm1",
                "extra": {
                    "v1": "value1",
                    "v2": "value2"
                },
               "transport": {
                  "type": "websocket",
                  "endpoint": {
                     "type": "tcp",
                     "host": "127.0.0.1",
                     "port": 8080
                  },
                  "url": "ws://127.0.0.1:8080/ws"
               }
            }
         ]
      }
```

Here your are! Enjoy.
