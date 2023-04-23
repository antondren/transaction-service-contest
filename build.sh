#!/bin/bash

# Exit on any error
set -e

# Ensure the script is run from the project's root directory
cd "$(dirname "$0")"

./gradlew clean build

echo "Service build completed successfully."
