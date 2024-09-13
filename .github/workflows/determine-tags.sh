#!/bin/bash

# Determine tags from feature files
FEATURE_DIR="src/test/resources/features"

# Check if the feature directory exists
if [ ! -d "$FEATURE_DIR" ]; then
  echo "Error: Feature directory '$FEATURE_DIR' does not exist."
  exit 1
else
  echo "Feature directory found: $FEATURE_DIR"
fi

# Check if there are any feature files
feature_files=$(ls $FEATURE_DIR/*.feature 2> /dev/null)
if [ -z "$feature_files" ]; then
  echo "No feature files found in '$FEATURE_DIR'."
  exit 1
else
  echo "Feature files found: $feature_files"
fi

# Extract tags from feature files
tags=$(grep -oP '@\K\w+' $FEATURE_DIR/*.feature | sort | uniq | tr '\n' ' ')
if [ -z "$tags" ]; then
  echo "No tags found in feature files."
  exit 1
else
  echo "Detected tags: $tags"
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

echo "Tag string for Maven: $tag_string"

# Export tag string as an environment variable
if [ -z "$tag_string" ]; then
  echo "No valid tags found for Maven execution."
else
  echo "Exporting TAG_STRING to environment: $tag_string"
  echo "TAG_STRING=$tag_string" >> $GITHUB_ENV
fi
