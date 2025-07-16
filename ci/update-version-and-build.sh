#!/bin/bash

# This script is designed to be used with @semantic-release/exec.
# It updates the 'mod_version' in gradle.properties and then runs a Gradle build.

# Check if a version argument is provided
if [ -z "$1" ]; then
  echo "Error: No version argument provided."
  echo "Usage: $0 <new_version>"
  exit 1
fi

NEW_VERSION="$1"
GRADLE_PROPERTIES="gradle.properties"

echo "Attempting to update mod_version in $GRADLE_PROPERTIES to $NEW_VERSION..."

# Check if gradle.properties exists
if [ ! -f "$GRADLE_PROPERTIES" ]; then
  echo "Error: $GRADLE_PROPERTIES not found in the current directory."
  exit 1
fi

# Use sed to find and replace the mod_version line.
# The 'i' flag edits the file in place.
# The regex looks for a line starting with 'mod_version=' and replaces the entire line.
sed -i "s/^mod_version=.*/mod_version=$NEW_VERSION/" "$GRADLE_PROPERTIES"

# Check if the sed command was successful
if [ $? -eq 0 ]; then
  echo "Successfully updated mod_version to $NEW_VERSION in $GRADLE_PROPERTIES."
else
  echo "Error: Failed to update mod_version in $GRADLE_PROPERTIES."
  exit 1
fi
# Disabled autobuild to save on Github Action minutes
#echo "Running ./gradlew build..."
#
## Execute the Gradle build command
#./gradlew build
#
## Check the exit status of the Gradle build
#if [ $? -eq 0 ]; then
#  echo "Gradle build completed successfully."
#  exit 0
#else
#  echo "Error: Gradle build failed."
#  exit 1
#fi
