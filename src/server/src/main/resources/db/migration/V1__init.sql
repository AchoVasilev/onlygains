CREATE TABLE roles
(
    id          UUID,
    name        VARCHAR(20),
    created_at  TIMESTAMPTZ NOT NULL DEFAULT (now() at time zone 'utc'),
    modified_at TIMESTAMPTZ,
    is_deleted  BOOLEAN     NOT NULL DEFAULT FALSE,

    CONSTRAINT pk_role_id PRIMARY KEY (id)
);

CREATE TABLE users
(
    id                 UUID,
    email              VARCHAR(50) NOT NULL,
    password           VARCHAR     NOT NULL,
    first_name         VARCHAR(50) NOT NULL,
    last_name          VARCHAR(50) NOT NULL,
    image_url          VARCHAR,
    workout_profile_id UUID,
    created_at         TIMESTAMPTZ NOT NULL DEFAULT (now() at time zone 'utc'),
    modified_at        TIMESTAMPTZ,
    is_deleted         BOOLEAN     NOT NULL DEFAULT FALSE,
    role_id            UUID,

    CONSTRAINT pk_user_id PRIMARY KEY (id),
    CONSTRAINT fk_role_id FOREIGN KEY (role_id) REFERENCES roles (id)
);

CREATE TABLE categories
(
    id              UUID,
    name            VARCHAR(100),
    translated_name VARCHAR(100),
    image_url       VARCHAR,
    modified_at     TIMESTAMPTZ,
    created_at      TIMESTAMPTZ NOT NULL DEFAULT (now() at time zone 'utc'),
    is_deleted      BOOLEAN     NOT NULL DEFAULT FALSE,

    CONSTRAINT pk_category_id PRIMARY KEY (id)
);

CREATE TABLE posts
(
    id           UUID,
    title        VARCHAR(100) NOT NULL,
    text         VARCHAR      NOT NULL,
    preview_text VARCHAR      NOT NULL,
    category_id  UUID,
    user_id      UUID,
    created_at   TIMESTAMPTZ  NOT NULL DEFAULT (now() at time zone 'utc'),
    modified_at  TIMESTAMPTZ,
    is_deleted   BOOLEAN      NOT NULL DEFAULT FALSE,

    CONSTRAINT pk_post_id PRIMARY KEY (id),
    CONSTRAINT fk_category_id FOREIGN KEY (category_id) REFERENCES categories (id),
    CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE comments
(
    id          UUID,
    text        VARCHAR     NOT NULL,
    post_id     UUID,
    user_id     UUID,
    parent_id   UUID,
    created_at  TIMESTAMPTZ NOT NULL DEFAULT (now() at time zone 'utc'),
    modified_at TIMESTAMPTZ,
    is_deleted  BOOLEAN     NOT NULL DEFAULT FALSE,

    CONSTRAINT pk_comment_id PRIMARY KEY (id),
    CONSTRAINT fk_post_id FOREIGN KEY (post_id) REFERENCES posts (id),
    CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users (id),
    CONSTRAINT fk_parent_id FOREIGN KEY (parent_id) REFERENCES comments (id)
);

CREATE TABLE likes
(
    id          UUID,
    comment_id  UUID,
    created_at  TIMESTAMPTZ NOT NULL DEFAULT (now() at time zone 'utc'),
    modified_at TIMESTAMPTZ,
    is_deleted  BOOLEAN     NOT NULL DEFAULT FALSE,

    CONSTRAINT pk_like_id PRIMARY KEY (id),
    CONSTRAINT fk_comment_id FOREIGN KEY (comment_id) REFERENCES comments (id)
);


CREATE TABLE dislikes
(
    id          UUID,
    comment_id  UUID,
    created_at  TIMESTAMPTZ NOT NULL DEFAULT (now() at time zone 'utc'),
    modified_at TIMESTAMPTZ,
    is_deleted  BOOLEAN     NOT NULL DEFAULT FALSE,

    CONSTRAINT pk_dislike_id PRIMARY KEY (id),
    CONSTRAINT fk_comment_id FOREIGN KEY (comment_id) REFERENCES comments (id)
);

CREATE TABLE post_images
(
    id          UUID,
    url         VARCHAR     NOT NULL,
    post_id     UUID,
    created_at  TIMESTAMPTZ NOT NULL DEFAULT (now() at time zone 'utc'),
    modified_at TIMESTAMPTZ,
    is_deleted  BOOLEAN     NOT NULL DEFAULT FALSE,

    CONSTRAINT pk_post_image_id PRIMARY KEY (id),
    CONSTRAINT fk_post_id FOREIGN KEY (post_id) REFERENCES posts (id)
);

CREATE TABLE tags
(
    id              UUID,
    name            VARCHAR(30),
    translated_name VARCHAR(30),
    created_at      TIMESTAMPTZ NOT NULL DEFAULT (now() at time zone 'utc'),
    modified_at     TIMESTAMPTZ,
    is_deleted      BOOLEAN     NOT NULL DEFAULT FALSE,

    CONSTRAINT pk_tag_id PRIMARY KEY (id)
);

CREATE TABLE posts_tags
(
    post_id UUID,
    tag_id  UUID,

    CONSTRAINT pk_posts_tags PRIMARY KEY (post_id, tag_id)
);

CREATE TABLE workout_history
(
    id          UUID,
    workout_id  UUID,
    created_at  TIMESTAMPTZ NOT NULL DEFAULT (now() at time zone 'utc'),
    modified_at TIMESTAMPTZ,
    is_deleted  BOOLEAN     NOT NULL DEFAULT FALSE,

    CONSTRAINT pk_workout_history_id PRIMARY KEY (id)
);

CREATE TABLE workout_templates
(
    id                           UUID,
    name                         VARCHAR,
    original_workout_template_id UUID,
    created_at                   TIMESTAMPTZ NOT NULL DEFAULT (now() at time zone 'utc'),
    modified_at                  TIMESTAMPTZ,
    is_deleted                   BOOLEAN     NOT NULL DEFAULT FALSE,

    CONSTRAINT pk_workout_template_id PRIMARY KEY (id)
);

CREATE TABLE original_workout_templates
(
    id          UUID,
    name        VARCHAR,
    created_at  TIMESTAMPTZ NOT NULL DEFAULT (now() at time zone 'utc'),
    modified_at TIMESTAMPTZ,
    is_deleted  BOOLEAN     NOT NULL DEFAULT FALSE,

    CONSTRAINT pk_original_workout_template_id PRIMARY KEY (id)
);

CREATE TABLE workouts
(
    id                  UUID,
    workout_template_id UUID,
    finished_at         TIMESTAMPTZ,
    duration            INTERVAL             DEFAULT NULL,
    status              VARCHAR(15),
    created_at          TIMESTAMPTZ NOT NULL DEFAULT (now() at time zone 'utc'),
    modified_at         TIMESTAMPTZ,
    is_deleted          BOOLEAN     NOT NULL DEFAULT FALSE,

    CONSTRAINT pk_workout_id PRIMARY KEY (id)
);

CREATE TABLE workout_exercises
(
    id                           UUID,
    exercise_id                  UUID,
    name                         VARCHAR,
    original_workout_template_id UUID,
    workout_template_id          UUID,
    created_at                   TIMESTAMPTZ NOT NULL DEFAULT (now() at time zone 'utc'),
    modified_at                  TIMESTAMPTZ,
    is_deleted                   BOOLEAN     NOT NULL DEFAULT FALSE,

    CONSTRAINT pk_workout_exercise_id PRIMARY KEY (id),
    CONSTRAINT fk_original_workout_template_id FOREIGN KEY (original_workout_template_id) REFERENCES original_workout_templates (id),
    CONSTRAINT fk_workout_template_id FOREIGN KEY (workout_template_id) REFERENCES workout_templates (id)
);

CREATE TABLE sets
(
    id                  UUID,
    weight              NUMERIC(4, 2),
    repetitions         INTEGER,
    weight_type         VARCHAR(3),
    workout_exercise_id UUID,
    created_at          TIMESTAMPTZ NOT NULL DEFAULT (now() at time zone 'utc'),
    modified_at         TIMESTAMPTZ,
    is_deleted          BOOLEAN     NOT NULL DEFAULT FALSE,

    CONSTRAINT pk_set_id PRIMARY KEY (id),
    CONSTRAINT fk_exercise_id FOREIGN KEY (workout_exercise_id) REFERENCES workout_exercises (id)
);

CREATE TABLE muscle_groups
(
    id              VARCHAR(100),
    name            VARCHAR(50),
    translated_name VARCHAR(100),
    created_at      TIMESTAMPTZ NOT NULL DEFAULT (now() at time zone 'utc'),
    modified_at     TIMESTAMPTZ,
    is_deleted      BOOLEAN     NOT NULL DEFAULT FALSE,

    CONSTRAINT pk_muscle_group_id PRIMARY KEY (id)
);

CREATE TABLE exercises
(
    id                            UUID,
    name                          VARCHAR(100) NOT NULL,
    translated_name               VARCHAR(150) NOT NULL,
    description                   VARCHAR,
    image_url                     VARCHAR,
    gif_url                       VARCHAR,
    main_muscle_groups_ids        VARCHAR,
    synergistic_muscle_groups_ids VARCHAR,
    created_at                    TIMESTAMPTZ  NOT NULL DEFAULT (now() at time zone 'utc'),
    modified_at                   TIMESTAMPTZ,
    is_deleted                    BOOLEAN      NOT NULL DEFAULT FALSE,

    CONSTRAINT pk_exercise_id PRIMARY KEY (id)
);

CREATE TABLE exercises_variations
(
    exercise_id  UUID,
    variation_id UUID,

    CONSTRAINT pk_exercise_variation PRIMARY KEY (exercise_id, variation_id)
);

CREATE TABLE exercises_musclegroups
(
    exercise_id    UUID,
    musclegroup_id VARCHAR,

    CONSTRAINT exercise_muscle_group_id PRIMARY KEY (exercise_id, musclegroup_id)
);

CREATE TABLE equipment
(
    id          UUID,
    name        VARCHAR,
    created_at  TIMESTAMPTZ NOT NULL DEFAULT (now() at time zone 'utc'),
    modified_at TIMESTAMPTZ,
    is_deleted  BOOLEAN     NOT NULL DEFAULT FALSE,

    CONSTRAINT pk_equipment_id PRIMARY KEY (id)
);

CREATE TABLE exercises_equipment
(
    exercise_id  UUID,
    equipment_id UUID,

    CONSTRAINT exercise_equipment_id PRIMARY KEY (exercise_id, equipment_id)
);

CREATE TABLE todo_items
(
    id          UUID,
    name        VARCHAR,
    is_done     BOOLEAN     NOT NULL DEFAULT FALSE,
    user_id     UUID,
    created_at  TIMESTAMPTZ NOT NULL DEFAULT (now() at time zone 'utc'),
    modified_at TIMESTAMPTZ,
    is_deleted  BOOLEAN     NOT NULL DEFAULT FALSE,

    CONSTRAINT pk_todo_item_id PRIMARY KEY (id),
    CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE workout_profile
(
    id           UUID,
    user_id      UUID,
    age          INTEGER,
    gender       CHAR,
    weight       NUMERIC(4, 2),
    weight_type  VARCHAR(2),
    height       NUMERIC(4, 2),
    height_type  VARCHAR(2),
    body_fat     NUMERIC(2, 2),
    percent      CHAR,
    bmi          NUMERIC(4, 2),
    calories     NUMERIC(6, 2),
    unit_type    VARCHAR(5),
    bmr_equation VARCHAR,

    created_at   TIMESTAMPTZ NOT NULL DEFAULT (now() at time zone 'utc'),
    modified_at  TIMESTAMPTZ,
    is_deleted   BOOLEAN     NOT NULL DEFAULT FALSE,

    CONSTRAINT pk_workout_profile_id PRIMARY KEY (id)
)