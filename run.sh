#!/bin/bash

PARAMS="$*"

if [ "A$PARAMS" == "A" ]
then
  PARAMS="--help"
fi

export MAVEN_OPTS=-Dfile.encoding=utf-8

mvn exec:java -Dexec.mainClass="TCPServer" -Dexec.args="$PARAMS"

