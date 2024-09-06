#!/bin/bash

# Determine tags from feature files
tags=$(grep -oP '@\K\w+' src/test/resources/features/*.feature 2>/dev/null | sort | uniq | tr '\n' ' ')

# Check if tags were found
if [ -z "$tags" ]; then
  echo "No tags found in feature files."
  exit 1
fi

# Generate a tag string for Maven command
tag_string=""
for tag in $tags; do
  if [[ $tag == "smoketest" || $tag == "regression" ]]; then
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
