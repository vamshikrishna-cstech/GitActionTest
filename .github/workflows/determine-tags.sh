#!/bin/bash

# Determine tags from feature files
# Ensure the feature files are located in the correct directory
FEATURE_DIR="src/test/resources/features"
if [ ! -d "$FEATURE_DIR" ]; then
  echo "Error: Feature directory '$FEATURE_DIR' does not exist."
  exit 1
fi

# Check if any feature files exist
feature_files=$(ls $FEATURE_DIR/*.feature 2> /dev/null)
if [ -z "$feature_files" ]; then
  echo "No feature files found in '$FEATURE_DIR'."
  exit 1
fi

# Extract tags from feature files
tags=$(grep -oP '@\K\w+' $FEATURE_DIR/*.feature | sort | uniq | tr '\n' ' ')
echo "Detected tags: $tags"

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
echo "TAG_STRING=$tag_string" >> $GITHUB_ENV
