#!/bin/sh

libdir=../../lib
bindir=.
srcdir=.

CP="$libdir/*"

test -e $bindir/com/prosysopc/ua/samples/server/SampleConsoleServer.class || javac -classpath "$CP" $srcdir/com/prosysopc/ua/samples/server/*.java $srcdir/com/prosysopc/ua/samples/server/compliancenodes/*.java

java -classpath $bindir:"$CP" com.prosysopc.ua.samples.server.SampleConsoleServer $*