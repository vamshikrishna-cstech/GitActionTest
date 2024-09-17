#!/bin/bash

# Determine tags from feature files
# This script assumes that feature files are located in src/test/resources/features

tags=$(grep -oP '@\K\w+' src/test/resources/features/*.feature | sort | uniq | tr '\n' ' ')
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

# Check if tag_string is empty and set a default value if needed
if [ -z "$tag_string" ]; then
  tag_string="None"
fi

echo "Tag string for Maven: $tag_string"

# Export tag string as an environment variable
echo "TAG_STRING=$tag_string" >> $GITHUB_ENV
