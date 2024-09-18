#!/bin/bash

# Get a list of changed files in the current PR or push
changed_files=$(git diff --name-only HEAD^ HEAD)

# Filter for .feature files
feature_files=$(echo "$changed_files" | grep -E '\.feature$')

# If no feature files are found, set feature_files to a default value
if [ -z "$feature_files" ]; then
  echo "No feature files changed."
fi

# Export feature files as an environment variable
echo "FEATURE_FILES=$feature_files" >> $GITHUB_ENV
