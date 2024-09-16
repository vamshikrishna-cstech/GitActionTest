#!/bin/bash

# Debugging: List all feature files being scanned
echo "Scanning for changed feature files in: $CHANGED_FILES"

# Extract feature files from changed files
CHANGED_FEATURE_FILES=$(echo "$CHANGED_FILES" | grep 'src/test/resources/features' || echo "No feature files changed")

if [ -z "$CHANGED_FEATURE_FILES" ]; then
  echo "No changed feature files found."
  echo "CHANGED_FEATURE_FILES=" >> $GITHUB_ENV
  exit 0
fi

echo "Changed feature files: $CHANGED_FEATURE_FILES"
echo "CHANGED_FEATURE_FILES=$CHANGED_FEATURE_FILES" >> $GITHUB_ENV

# Extract tags from feature files
tags=$(grep -oP '@\w+' $CHANGED_FEATURE_FILES 2>/dev/null | sort | uniq | tr '\n' ' ')

echo "Tags found: $tags"

# Check if any tags were found
if [ -z "$tags" ]; then
  echo "No tags found in changed feature files."
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

if [ -z "$tag_string" ]; then
  echo "No smoketest or regression tags found. Running default tests."
  echo "TAG_STRING=" >> $GITHUB_ENV
else
  echo "Tag string for Maven: $tag_string"
  echo "TAG_STRING=$tag_string" >> $GITHUB_ENV
fi
