#!/bin/bash

# Debugging: List all feature files being scanned
echo "Scanning the following feature files:"
ls src/test/resources/features/*.feature

# Extract tags from feature files
tags=$(grep -oP '@\w+' src/test/resources/features/*.feature 2>/dev/null | sort | uniq | tr '\n' ' ')

# Debugging: Output the raw tags found
echo "Tags found: $tags"

# Check if any tags were found
if [ -z "$tags" ]; then
  echo "No tags found in feature files."
  echo "TAG_STRING=" >> $GITHUB_ENV
  exit 0
fi

# Generate a tag string for Maven command
tag_string=""
for tag in $tags; do
  if [[ $tag == "@smoketest" || $tag == "@regression" ]]; then
    tag_string+="$tag or "
  fi
done

# Remove trailing "or "
tag_string=${tag_string% or }

# Check if no smoketest or regression tags were found
if [ -z "$tag_string" ]; then
  echo "No smoketest or regression tags found. Running all tests."
  echo "TAG_STRING=" >> $GITHUB_ENV
else
  echo "Tag string for Maven: $tag_string"
  echo "TAG_STRING=$tag_string" >> $GITHUB_ENV
fi