#!/bin/sh

libdir=../../lib
bindir=.
srcdir=.

CP="$libdir/*"

test -e $bindir/com/prosysopc/ua/samples/client/SimpleClient.class || javac -classpath "$CP" $srcdir/com/prosysopc/ua/samples/client/*.java

java -classpath $bindir:"$CP" com.prosysopc.ua.samples.client.SimpleClient $* 