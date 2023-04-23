#!/bin/bash

# Exit on any error
set -e

# Ensure the script is run from the project's root directory
cd "$(dirname "$0")"

# Build the project
bash build.sh

java -cp build/libs/*.jar com.contest.transactionservice.TransactionServiceApplication