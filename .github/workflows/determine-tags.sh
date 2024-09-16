#!/bin/bash

# Detect changes in feature files
CHANGED_FEATURE_FILES=$(echo "${CHANGED_FILES}" | grep -E '\.feature$')

# Debugging: Output changed feature files
echo "Changed feature files: $CHANGED_FEATURE_FILES"

# Exit early if no feature files are changed
if [ -z "$CHANGED_FEATURE_FILES" ]; then
  echo "No feature files changed."
  echo "TAG_STRING=" >> $GITHUB_ENV
  exit 0
fi

# Extract tags from changed feature files
tags=$(grep -oP '@\w+' $CHANGED_FEATURE_FILES 2>/dev/null | sort | uniq | tr '\n' ' ')

# Debugging: Output the raw tags found
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

# Set default if no matching tags
if [ -z "$tag_string" ]; then
  echo "No smoketest or regression tags found. Running default: @regression"
  echo "TAG_STRING=@regression" >> $GITHUB_ENV
else
  echo "Tag string for Maven: $tag_string"
  echo "TAG_STRING=$tag_string" >> $GITHUB_ENV
fi
