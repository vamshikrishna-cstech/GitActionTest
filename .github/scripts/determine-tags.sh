#!/bin/bash

# Script to determine feature files or tags based on changes
set -e

# Check for the event type
if [ "${GITHUB_EVENT_NAME}" == "pull_request" ]; then
  BASE_BRANCH="origin/main"
  PR_BRANCH="${GITHUB_HEAD_REF}"
  CHANGED_FILES=$(git diff --name-only $BASE_BRANCH...$PR_BRANCH 2>/dev/null || echo "No common base")

  # Extract changed feature files
  CHANGED_FEATURE_FILES=$(echo "$CHANGED_FILES" | grep -E '^src/test/resources/features/.*\.feature$' || echo "")
  
  if [ -z "$CHANGED_FEATURE_FILES" ]; then
    echo "No feature files changed. Skipping tests."
    echo "FEATURE_FILES=" >> $GITHUB_ENV
  else
    echo "FEATURE_FILES=$(echo "$CHANGED_FEATURE_FILES" | tr '\n' ' ')" >> $GITHUB_ENV
  fi
elif [ "${GITHUB_EVENT_NAME}" == "workflow_dispatch" ]; then
  # For manual triggers, use input tag
  echo "TAG_STRING=${{ github.event.inputs.tag }}" >> $GITHUB_ENV
else
  echo "Unsupported event type: ${GITHUB_EVENT_NAME}"
fi
