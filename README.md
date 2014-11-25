# Installation

Install sbt and run it in the project folder.

    sbt run

# Deploy

Tweak the `deploy.sh` script and run it. Be sure to have the same JVM version on the client and on the server.

## Service (ubuntu)


    service blog start


Service file example:

    env JAVA_OPTS="-Xms512m -Xmx512m"

    pre-start script
    rm -f /home/vjousse/scala/blog/RUNNING_PID
    mkdir -p /home/vjousse/scala/blog/logs
    chown -R vjousse:vjousse /home/vjousse/scala/blog/logs
    end script

    start on runlevel [2345]
    stop on runlevel [06]

    respawn

    script
    user="vjousse"
    path="/home/vjousse/scala/blog"
    executable="$path/bin/jousse-blog-play"
    args="-Dconfig.file=$path/application.conf -Dhttp.port=9001"
    output="$path/logs/output.log "
    exec start-stop-daemon --start --chuid $user --exec $executable -- $args > $output
    end script

# Documentation

Markdown parser: http://software.clapper.org/markwrap/
