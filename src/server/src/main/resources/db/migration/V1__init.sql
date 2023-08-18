CREATE TABLE roles
(
    id          UUID PRIMARY KEY,
    created_at  TIMESTAMPTZ NOT NULL DEFAULT (now() at time zone 'utc'),
    modified_at TIMESTAMPTZ,
    name        VARCHAR(20),
    is_deleted  BOOLEAN     NOT NULL DEFAULT FALSE
);

CREATE TABLE users
(
    id          UUID PRIMARY KEY,
    created_at  TIMESTAMPTZ NOT NULL DEFAULT (now() at time zone 'utc'),
    modified_at TIMESTAMPTZ,
    email       VARCHAR(50) NOT NULL,
    password    VARCHAR     NOT NULL,
    first_name  VARCHAR(50) NOT NULL,
    last_name   VARCHAR(50) NOT NULL,
    is_deleted  BOOLEAN     NOT NULL DEFAULT FALSE,
    role_id     UUID REFERENCES roles (id)
);

CREATE TABLE categories
(
    id          UUID PRIMARY KEY,
    created_at  TIMESTAMPTZ NOT NULL DEFAULT (now() at time zone 'utc'),
    modified_at TIMESTAMPTZ,
    name        VARCHAR(100),
    translated_name VARCHAR(100),
    is_deleted  BOOLEAN     NOT NULL DEFAULT FALSE,
    image_url   VARCHAR
);

CREATE TABLE posts
(
    id          UUID PRIMARY KEY,
    created_at  TIMESTAMPTZ NOT NULL DEFAULT (now() at time zone 'utc'),
    modified_at TIMESTAMPTZ,
    title       VARCHAR     NOT NULL,
    text        VARCHAR     NOT NULL,
    is_deleted  BOOLEAN     NOT NULL DEFAULT FALSE,
    category_id UUID REFERENCES categories (id),
    user_id     UUID REFERENCES users (id)
);

CREATE TABLE comments
(
    id          UUID PRIMARY KEY,
    created_at  TIMESTAMPTZ NOT NULL DEFAULT (now() at time zone 'utc'),
    modified_at TIMESTAMPTZ,
    text        VARCHAR     NOT NULL,
    is_deleted  BOOLEAN     NOT NULL DEFAULT FALSE,
    post_id     UUID REFERENCES posts (id)
);

CREATE TABLE likes
(
    id          UUID PRIMARY KEY,
    created_at  TIMESTAMPTZ NOT NULL DEFAULT (now() at time zone 'utc'),
    modified_at TIMESTAMPTZ,
    is_deleted  BOOLEAN     NOT NULL DEFAULT FALSE,
    comment_id  UUID REFERENCES comments (id)
);


CREATE TABLE dislikes
(
    id          UUID PRIMARY KEY,
    created_at  TIMESTAMPTZ NOT NULL DEFAULT (now() at time zone 'utc'),
    modified_at TIMESTAMPTZ,
    is_deleted  BOOLEAN     NOT NULL DEFAULT FALSE,
    comment_id  UUID REFERENCES comments (id)
);

CREATE TABLE post_images
(
    id          UUID PRIMARY KEY,
    created_at  TIMESTAMPTZ NOT NULL DEFAULT (now() at time zone 'utc'),
    modified_at TIMESTAMPTZ,
    is_deleted  BOOLEAN     NOT NULL DEFAULT FALSE,
    post_id     UUID REFERENCES posts (id)
);

CREATE TABLE user_images
(
    id          UUID PRIMARY KEY,
    created_at  TIMESTAMPTZ NOT NULL DEFAULT (now() at time zone 'utc'),
    modified_at TIMESTAMPTZ,
    is_deleted  BOOLEAN     NOT NULL DEFAULT FALSE,
    user_id     UUID REFERENCES users (id)
);

CREATE TABLE tags
(
    id          UUID PRIMARY KEY,
    created_at  TIMESTAMPTZ NOT NULL DEFAULT (now() at time zone 'utc'),
    modified_at TIMESTAMPTZ,
    name        VARCHAR(30),
    is_deleted  BOOLEAN     NOT NULL DEFAULT FALSE,
    post_id     UUID REFERENCES posts (id)
)