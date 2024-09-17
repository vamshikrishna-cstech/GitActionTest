#!/bin/bash

# Check if this step is running in a pull request
if [ "${{ github.event_name }}" = "pull_request" ]; then
  # Fetch the latest changes from the main branch
  git fetch origin main

  # Get the list of changed files in the PR
  changed_files=$(git diff --name-only HEAD $(git merge-base HEAD origin/main))
  echo "Changed files: $changed_files"
else
  # If not a PR, set changed_files to an empty value
  changed_files=""
  echo "Changed files: $changed_files"
fi

# Filter the changed files to include only .feature files
feature_files=$(echo "$changed_files" | grep -E '\.feature$')
echo "Feature files: $feature_files"

# Set the output
echo "::set-output name=features::$feature_files"
