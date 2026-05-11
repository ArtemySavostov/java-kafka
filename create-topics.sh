#!/bin/bash

# Wait for Kafka to be ready
echo "Waiting for Kafka to be ready..."
until kafka-topics.sh --bootstrap-server localhost:9092 --list > /dev/null 2>&1; do
  echo "Kafka not ready, sleeping for 2 seconds..."
  sleep 2
done

echo "Kafka is ready. Creating topics..."

# Create task-created topic with 3 partitions
kafka-topics.sh --bootstrap-server localhost:9092 \
  --create \
  --topic task-created \
  --partitions 3 \
  --replication-factor 1 \
  --if-not-exists

echo "Topic 'task-created' created with 3 partitions"

# List topics to verify
kafka-topics.sh --bootstrap-server localhost:9092 --list
