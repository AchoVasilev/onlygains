CREATE TABLE roles
(
    id          UUID PRIMARY KEY,
    name        VARCHAR(20),
    created_at  TIMESTAMPTZ NOT NULL DEFAULT (now() at time zone 'utc'),
    modified_at TIMESTAMPTZ,
    is_deleted  BOOLEAN     NOT NULL DEFAULT FALSE
);

CREATE TABLE users
(
    id          UUID PRIMARY KEY,
    email       VARCHAR(50) NOT NULL,
    password    VARCHAR     NOT NULL,
    first_name  VARCHAR(50) NOT NULL,
    last_name   VARCHAR(50) NOT NULL,
    image_url   VARCHAR,
    created_at  TIMESTAMPTZ NOT NULL DEFAULT (now() at time zone 'utc'),
    modified_at TIMESTAMPTZ,
    is_deleted  BOOLEAN     NOT NULL DEFAULT FALSE,
    role_id     UUID REFERENCES roles (id)
);

CREATE TABLE categories
(
    id              UUID PRIMARY KEY,
    name            VARCHAR(100),
    translated_name VARCHAR(100),
    image_url       VARCHAR,
    modified_at     TIMESTAMPTZ,
    created_at      TIMESTAMPTZ NOT NULL DEFAULT (now() at time zone 'utc'),
    is_deleted      BOOLEAN     NOT NULL DEFAULT FALSE
);

CREATE TABLE posts
(
    id           UUID PRIMARY KEY,
    title        VARCHAR(100) NOT NULL,
    text         VARCHAR      NOT NULL,
    preview_text VARCHAR      NOT NULL,
    created_at   TIMESTAMPTZ  NOT NULL DEFAULT (now() at time zone 'utc'),
    modified_at  TIMESTAMPTZ,
    is_deleted   BOOLEAN      NOT NULL DEFAULT FALSE,
    category_id  UUID REFERENCES categories (id),
    user_id      UUID REFERENCES users (id)
);

CREATE TABLE comments
(
    id          UUID PRIMARY KEY,
    text        VARCHAR     NOT NULL,
    created_at  TIMESTAMPTZ NOT NULL DEFAULT (now() at time zone 'utc'),
    modified_at TIMESTAMPTZ,
    is_deleted  BOOLEAN     NOT NULL DEFAULT FALSE,
    post_id     UUID REFERENCES posts (id),
    user_id     UUID REFERENCES users (id),
    parent_id   UUID REFERENCES comments (id)
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
    url         VARCHAR     NOT NULL,
    created_at  TIMESTAMPTZ NOT NULL DEFAULT (now() at time zone 'utc'),
    modified_at TIMESTAMPTZ,
    is_deleted  BOOLEAN     NOT NULL DEFAULT FALSE,
    post_id     UUID REFERENCES posts (id)
);

CREATE TABLE tags
(
    id              UUID PRIMARY KEY,
    name            VARCHAR(30),
    translated_name VARCHAR(30),
    created_at      TIMESTAMPTZ NOT NULL DEFAULT (now() at time zone 'utc'),
    modified_at     TIMESTAMPTZ,
    is_deleted      BOOLEAN     NOT NULL DEFAULT FALSE
);

CREATE TABLE posts_tags
(
    post_id UUID REFERENCES posts (id),
    tag_id  UUID REFERENCES tags (id),
    CONSTRAINT posts_tags_pk PRIMARY KEY (post_id, tag_id)
);

CREATE TABLE workout_history
(
    id          UUID PRIMARY KEY,
    created_at  TIMESTAMPTZ NOT NULL DEFAULT (now() at time zone 'utc'),
    modified_at TIMESTAMPTZ,
    is_deleted  BOOLEAN     NOT NULL DEFAULT FALSE
);

CREATE TABLE workouts
(
    id                 UUID PRIMARY KEY,
    workout_history_id UUID REFERENCES workout_history (id),
    created_at         TIMESTAMPTZ NOT NULL DEFAULT (now() at time zone 'utc'),
    modified_at        TIMESTAMPTZ,
    is_deleted         BOOLEAN     NOT NULL DEFAULT FALSE
);

CREATE TABLE workout_templates
(
    id          UUID PRIMARY KEY,
    name        VARCHAR,
    workout_id  UUID REFERENCES workouts (id),
    created_at  TIMESTAMPTZ NOT NULL DEFAULT (now() at time zone 'utc'),
    modified_at TIMESTAMPTZ,
    is_deleted  BOOLEAN     NOT NULL DEFAULT FALSE
);

CREATE TABLE muscle_groups
(
    id              UUID PRIMARY KEY,
    name            VARCHAR(50),
    translated_name VARCHAR(100),
    created_at      TIMESTAMPTZ NOT NULL DEFAULT (now() at time zone 'utc'),
    modified_at     TIMESTAMPTZ,
    is_deleted      BOOLEAN     NOT NULL DEFAULT FALSE
);

CREATE TABLE exercises
(
    id                  UUID PRIMARY KEY,
    name                VARCHAR(100) NOT NULL,
    translated_name     VARCHAR(150) NOT NULL,
    description         VARCHAR,
    image_url           VARCHAR,
    gif_url             VARCHAR,
    workout_template_id UUID REFERENCES workout_templates (id),
    parent_id           UUID REFERENCES exercises (id),
    created_at          TIMESTAMPTZ  NOT NULL DEFAULT (now() at time zone 'utc'),
    modified_at         TIMESTAMPTZ,
    is_deleted          BOOLEAN      NOT NULL DEFAULT FALSE
);

CREATE TABLE exercises_workouts
(
    exercise_id UUID REFERENCES exercises (id),
    workout_id  UUID REFERENCES workouts (id),
    CONSTRAINT exercise_workout_id PRIMARY KEY (exercise_id, workout_id)
);

CREATE TABLE exercises_musclegroups
(
    exercise_id    UUID REFERENCES exercises (id),
    musclegroup_id UUID REFERENCES muscle_groups (id),
    CONSTRAINT exercise_muscle_group_id PRIMARY KEY (exercise_id, musclegroup_id)
);

CREATE TABLE sets
(
    id          UUID PRIMARY KEY,
    weight      NUMERIC(4, 2),
    weight_type VARCHAR(3),
    exercise_id UUID REFERENCES exercises (id),
    created_at  TIMESTAMPTZ NOT NULL DEFAULT (now() at time zone 'utc'),
    modified_at TIMESTAMPTZ,
    is_deleted  BOOLEAN     NOT NULL DEFAULT FALSE
);

CREATE TABLE equipment
(
    id          UUID PRIMARY KEY,
    name        VARCHAR,
    created_at  TIMESTAMPTZ NOT NULL DEFAULT (now() at time zone 'utc'),
    modified_at TIMESTAMPTZ,
    is_deleted  BOOLEAN     NOT NULL DEFAULT FALSE
);

CREATE TABLE exercises_equipment
(
    exercise_id  UUID REFERENCES exercises (id),
    equipment_id UUID REFERENCES equipment (id),
    CONSTRAINT exercise_equipment_id PRIMARY KEY (exercise_id, equipment_id)
);