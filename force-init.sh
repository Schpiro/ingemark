#!/bin/bash
set -e

# Start Postgres in the background
docker-entrypoint.sh postgres &

# Wait for DB to accept connections
until pg_isready -U "$POSTGRES_USER" -d "$POSTGRES_DB"; do
  echo "Waiting for Postgres..."
  sleep 2
done

echo "Cleaning up database..."
psql -U "$POSTGRES_USER" -d "$POSTGRES_DB" -c "DROP SCHEMA public CASCADE; CREATE SCHEMA public;"

echo "Running init.sql..."
psql -U "$POSTGRES_USER" -d "$POSTGRES_DB" -f /docker-entrypoint-initdb.d/init.sql

# Keep Postgres in the foreground
wait -n
