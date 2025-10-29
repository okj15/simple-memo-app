#!/bin/sh

set -e

DIR="$(cd "$(dirname "$0")" && pwd)"
GRADLE_WRAPPER_JAR="$DIR/gradle/wrapper/gradle-wrapper.jar"
if [ ! -f "$GRADLE_WRAPPER_JAR" ]; then
  echo "Gradle wrapper JAR not found. Please download gradle-wrapper.jar." >&2
  exit 1
fi

exec "${DIR}/gradle/wrapper/gradle-wrapper.jar" "$@"
