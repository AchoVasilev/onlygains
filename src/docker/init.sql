SELECT 'CREATE DATABASE myactivepal'
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'myactivepal')\gexec
