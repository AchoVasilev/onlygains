CREATE TABLE post(
    id UUID PRIMARY KEY,
    created_at TIMESTAMPTZ NOT NULL DEFAULT (now() at time zone 'utc'),
    modified_at TIMESTAMPTZ,
    title VARCHAR,
    text VARCHAR,
)