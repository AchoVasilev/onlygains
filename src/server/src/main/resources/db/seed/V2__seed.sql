INSERT INTO roles (id, name)
VALUES ('ca51ab2e-288c-4519-aa29-3ea4d1daf444', 'ADMINISTRATOR'),
       ('25da1a4b-3b85-4398-b977-b429d5030b6c', 'USER'),
       ('ac3d9fd0-1725-466e-b0a7-00b9cd2161a7', 'MODERATOR'),
       ('d5a0270c-4b4a-40b7-af60-ca274f5858b6', 'WRITER');

INSERT INTO categories(id, name, translated_name, image_url)
VALUES ('5206274b-b473-49c1-bcca-6516838a9f1e', 'Рецепти', 'Recipes',
        'https://res.cloudinary.com/dpo3vbxnl/image/upload/v1691940539/onlygains/categories/istock-1155240408_stpuam.jpg'),
       ('a1f06571-2f00-43a8-9ab9-401c802237cd', 'Упражнения', 'Exercises',
        'https://res.cloudinary.com/dpo3vbxnl/image/upload/v1691940754/onlygains/categories/shutterstock_635024150opt_featured_1614948744_1200x672_acf_cropped_a40vos.jpg'),
       ('9fd30320-8e71-4a76-aff8-8b82fccb68e7', 'Тренировъчни програми', 'Workouts',
        'https://res.cloudinary.com/dpo3vbxnl/image/upload/v1691941043/onlygains/categories/TheNotebook_pd92nm.jpg'),
       ('c40af472-04fc-4c51-aeb6-22818645f824', 'Фитнес зали', 'Gyms',
        'https://res.cloudinary.com/dpo3vbxnl/image/upload/v1691941224/onlygains/categories/brutally-hardcore-gyms-you-need-to-train-at-before-you-die-652x400-10-1496399800_ahp7xa.jpg'),
       ('5e01b7ad-c631-470e-b330-54ac9a503b57', 'Хранителни режими', 'Diets',
        'https://res.cloudinary.com/dpo3vbxnl/image/upload/v1691941490/onlygains/categories/image04-4_hgly6c.jpg'),
       ('272e0edd-11e8-4709-bbba-5d36d77dd428', 'Сред природата', 'Nature',
        'https://res.cloudinary.com/dpo3vbxnl/image/upload/v1691942376/onlygains/categories/hiking-trail-names_fgpox2.jpg');

INSERT INTO tags (id, name, translated_name)
VALUES ('1f3684e0-5555-4bc2-8108-dde28193f888', 'Тежести', 'Weights'),
       ('d66abc66-2667-4ffb-893e-dba25ddecc57', 'Спорт', 'Sport'),
       ('d66abc66-2667-4ffb-893e-dba25ddecc56', 'Здраве', 'Health'),
       ('87c2c878-679d-4c3b-8333-b8c2f1a4f49e', 'Тонус', 'Tonus'),
       ('ca496044-baa1-4564-8515-6b4d51f12b74', 'HIIT', 'HIIT'),
       ('f5f39d62-f409-400f-8925-9aa95e86fc85', 'Табата', 'Tabata'),
       ('4912997c-1c20-4ed3-a2c0-a2b0a68e7a99', 'Диета', 'Diet'),
       ('44e6a935-af76-488f-a13f-4016d860e6d4', 'Упражнения', 'Exercises'),
       ('80c63c9c-9452-46f9-85a6-470c5732e160', 'Здравословно хранене', 'Healthy eating'),
       ('b713d11c-6ada-41f4-a5db-0418905decc8', 'Сила', 'Strength'),
       ('314e4587-4050-40ba-9edf-0062649f0a30', 'Релеф', 'Lean'),
       ('79282808-3073-45df-9b11-5c9d1ff3b2e5', 'Топене на мазнини', 'Fat burning'),
       ('ab76b5af-88c5-48ad-a8c3-08ba63a81d3e', 'Фитнес', 'Fitness'),
       ('2e8ccd31-7ce3-473b-bfb6-47580d2aa87c', 'Интензивност', 'Intensity'),
       ('ebc28cda-b2c4-476f-97fa-71734ee3f626', 'Мускулна маса', 'Muscle mass'),
       ('f7bbcd9e-7b46-470a-ac6c-8ae6ff4d09a1', 'Покачване на килограми', 'Weight gain'),
       ('3abf9fc4-0938-4e04-8e85-109ee99a6cc7', 'Сваляне на килограми', 'Weight loss'),
       ('70ea46a7-5d88-4e9f-b1ae-7a252747e9f8', 'Десерт', 'Dessert'),
       ('8f0ba4e9-b5a0-4a0b-8427-999480849c86', 'Вкусно', 'Tasty'),
       ('c9258d43-d99f-4858-b1a4-0f96ab6100b6', 'Щанги', 'Barbells'),
       ('5dc1dbf8-657a-4682-bfd4-38b4b2f88915', 'Дъмбели', 'Dumbbells'),
       ('946a92e4-ae8d-4743-922e-4ab74f31e703', 'Машини', 'Machines'),
       ('355bbe0a-cfba-4ec6-9972-421e18ce84ba', 'Рамена', 'Shoulders'),
       ('338d7d75-aae1-46b5-ac0d-78cab83458b2', 'Трапец', 'Traps'),
       ('c11bd458-8412-4787-9f25-fe5530d70572', 'Бицепс', 'Biceps'),
       ('051f2a07-aeaa-44e1-8a01-741e84902cd7', 'Трицепс', 'Triceps'),
       ('2c8328d4-8083-47f1-b6c6-15043e44ba95', 'Предмишници', 'Forearms'),
       ('c9b6d6d7-2e1f-4082-a944-dc644c806b3e', 'Ръце', 'Arms'),
       ('bbc690ba-a27b-4a09-b60c-4c213f469e2c', 'Хват', 'Grip'),
       ('fb47b86f-1c96-4d5d-9181-e8c33893934a', 'Крака', 'Legs'),
       ('5d65ea63-d62b-428e-b7ff-dbdb83042e8d', 'Гръб', 'Back'),
       ('d62653d9-225c-480d-8b4e-be8f1273d653', 'Гърди', 'Chest'),
       ('edbc0cd1-4a2e-492c-8a02-d957eaf1c31e', 'Бедра', 'Thighs'),
       ('45d12960-ccbf-4f68-8a3b-10747f4f3c76', 'Прасци', 'Calves'),
       ('370c5813-f6e8-43e4-a226-e8c90b1e241b', 'Добавки', 'Supplements'),
       ('5ba7e62d-b994-4c92-aa12-a80f98725fda', 'Стероиди', 'Steroids'),
       ('25734373-1968-4d16-b711-740b2491b536', 'Зала', 'Gym'),
       ('388163ac-20b4-4fc4-a2f5-be6a3dde70c6', 'Навън', 'Outside'),
       ('bbc4f909-19ad-48aa-a929-c4762c1fc2e0', 'Свободни тежести', 'Free weights'),
       ('07afe232-ee79-422a-a478-dd2010f96fe8', 'Crossfit', 'Crossfit'),
       ('f69d1b00-0b7d-4798-a977-a0e2926981b0', 'Олимпийско вдигане', 'Olympic lifting'),
       ('e3681a04-cd3d-48eb-99dd-0281ecd072d0', 'Трибой', 'Powerlifting'),
       ('7fe40023-5d53-45da-8713-a4410d7290b3', 'Многобой', 'Strongman'),
       ('5dcc20a4-2504-4946-a9e8-dd66c2835268', 'Мускул', 'Muscle'),
       ('316e55bb-7b8d-488d-962b-cc352efdaf81', 'Тренировка', 'Training'),
       ('2296058b-80dd-4045-ba76-a86bf75a1ae1', 'Природа', 'Nature'),
       ('41f70ba5-eab3-49a3-882a-0842e73cd2a3', 'Йога', 'Yoga'),
       ('a57490d2-9c88-45a0-81c7-a624094c3a6d', 'Пилатес', 'Pilates'),
       ('f91d9f5b-d476-4e8f-a424-8aacb5e4a435', 'Гъвкавост', 'Flexibility'),
       ('f13cd4e4-5a81-47ae-93c8-272bc3cba357', 'Кардио', 'Cardio'),
       ('409056d7-a703-4b0c-bd0f-ad7ba1e853d6', 'Тичане', 'Jogging'),
       ('578c0d21-9feb-4594-aba4-bfb246aa8545', 'Бягане', 'Running'),
       ('ea2d83e7-e54c-4ac2-95e7-c12b1ef1d356', 'Без екипировка', 'No equipment'),
       ('a5c973af-325a-42fc-8be6-f9f5986a7774', 'С екипировка', 'Equipment'),
       ('a63c0832-c6f1-47ee-a2f6-5a0b07429d38', 'Сладко', 'Sweat'),
       ('d8dcb11b-b6fc-4dff-b12d-bd3daed81747', 'Здравословно', 'Healthy'),
       ('d3452b11-d51b-4913-a1c4-1d54fcf8ebb1', 'Хранене', 'Eating'),
       ('b5b687f8-83d8-4582-836c-e5db0234169f', 'Чисто хранене', 'Clean eating'),
       ('cbdf4660-fded-44b1-9eef-791b55601ff9', 'Почивка', 'Rest'),
       ('1d61ce5a-0b59-4fd2-bfc1-49dffaebd0c6', 'Калистеника', 'Calisthenics');

INSERT INTO muscle_groups(id, name, translated_name)
VALUES ('1e42a13d-dcb7-4537-ab3e-c84daf504f5f', 'Трапец', 'Trapezius'),
       ('8ee7fe64-6486-4471-9e02-1009948a1d37', 'Гръб', 'Back'),
       ('c8fa17ab-256a-4817-8e84-4ca7060ced6a', 'Рамене', 'Shoulders'),
       ('4470c20a-e7c8-4089-a7b4-05a96b9c4e6a', 'Гърди', 'Chest'),
       ('d596483c-2535-4b76-9652-61489e471587', 'Бицепс', 'Biceps'),
       ('1635c593-f4a4-4e2f-92ae-0e60f3260c16', 'Трицепс', 'Triceps'),
       ('4ab706c5-77b6-44a0-a4fd-d83682a14f6b', 'Предмишници', 'Forearms'),
       ('4aeb6abd-8ca5-4e98-8d7b-406917fe4e4c', 'Коси коремни мускули', 'Obliques'),
       ('1f0725fe-1e74-4d49-b8d6-4344a63c7a55', 'Коремни мускули', 'Abs'),
       ('0ab13618-7f46-48dd-8eb3-8042bff418eb', 'Аддуктори', 'Adductors'),
       ('b5ea9934-5772-438f-b966-1649d2ad55a8', 'Прасци', 'Calves'),
       ('4ee6b101-edd5-4459-a851-4e2441684702', 'Задни бедра', 'Hamstrings'),
       ('a9193d4c-92d3-464b-b23a-a48d093b8811', 'Глутеус', 'Glutes'),
       ('a6bffb8d-54dc-4f76-8ad8-0fec7dd71b08', 'Четириглав бедрен мускул', 'Quads'),
       ('eef7134e-6b59-46a5-9c92-308a5fc0d3ee', 'Кръст', 'Lower back');