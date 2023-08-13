CREATE TABLE roles
(
    id          UUID PRIMARY KEY,
    created_at  TIMESTAMPTZ NOT NULL DEFAULT (now() at time zone 'utc'),
    modified_at TIMESTAMPTZ,
    name        VARCHAR
);

CREATE TABLE users
(
    id          UUID PRIMARY KEY,
    created_at  TIMESTAMPTZ NOT NULL DEFAULT (now() at time zone 'utc'),
    modified_at TIMESTAMPTZ,
    email       VARCHAR     NOT NULL,
    password    VARCHAR     NOT NULL,
    first_name  VARCHAR     NOT NULL,
    last_name   VARCHAR     NOT NULL,
    role_id     UUID REFERENCES roles (id)
);

CREATE TABLE categories
(
    id          UUID PRIMARY KEY,
    created_at  TIMESTAMPTZ NOT NULL DEFAULT (now() at time zone 'utc'),
    modified_at TIMESTAMPTZ,
    name        VARCHAR
);

CREATE TABLE posts
(
    id          UUID PRIMARY KEY,
    created_at  TIMESTAMPTZ NOT NULL DEFAULT (now() at time zone 'utc'),
    modified_at TIMESTAMPTZ,
    title       VARCHAR     NOT NULL,
    text        VARCHAR     NOT NULL,
    category_id UUID REFERENCES categories (id)
);

CREATE TABLE comments
(
    id          UUID PRIMARY KEY,
    created_at  TIMESTAMPTZ NOT NULL DEFAULT (now() at time zone 'utc'),
    modified_at TIMESTAMPTZ,
    text        VARCHAR     NOT NULL,
    post_id     UUID REFERENCES posts (id)
);

CREATE TABLE likes
(
    id          UUID PRIMARY KEY,
    created_at  TIMESTAMPTZ NOT NULL DEFAULT (now() at time zone 'utc'),
    modified_at TIMESTAMPTZ,
    comment_id  UUID REFERENCES comments (id)
);


CREATE TABLE dislikes
(
    id          UUID PRIMARY KEY,
    created_at  TIMESTAMPTZ NOT NULL DEFAULT (now() at time zone 'utc'),
    modified_at TIMESTAMPTZ,
    comment_id  UUID REFERENCES comments (id)
);

CREATE TABLE category_images
(
    id          UUID PRIMARY KEY,
    created_at  TIMESTAMPTZ NOT NULL DEFAULT (now() at time zone 'utc'),
    modified_at TIMESTAMPTZ,
    category_id UUID REFERENCES categories (id)
);

CREATE TABLE post_images
(
    id          UUID PRIMARY KEY,
    created_at  TIMESTAMPTZ NOT NULL DEFAULT (now() at time zone 'utc'),
    modified_at TIMESTAMPTZ,
    post_id     UUID REFERENCES posts (id)
);

CREATE TABLE user_images
(
    id          UUID PRIMARY KEY,
    created_at  TIMESTAMPTZ NOT NULL DEFAULT (now() at time zone 'utc'),
    modified_at TIMESTAMPTZ,
    user_id     UUID REFERENCES users (id)
);

CREATE TABLE tags
(
    id          UUID PRIMARY KEY,
    created_at  TIMESTAMPTZ NOT NULL DEFAULT (now() at time zone 'utc'),
    modified_at TIMESTAMPTZ,
    post_id     UUID REFERENCES posts (id)
)