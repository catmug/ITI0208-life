INSERT INTO user (user_id, username, email, password) VALUES (NEXT VALUE FOR seq_user, 'darnmonster', 'lol@gmail.com', 'darn');
INSERT INTO user (user_id, username, email, password) VALUES (NEXT VALUE FOR seq_user, 'marko', 'marko@gmail.com', 'essas');
INSERT INTO category (category_id, name, user_id) VALUES (NEXT VALUE FOR seq_category, 'Riided', 1);
INSERT INTO category (category_id, name, user_id) VALUES (NEXT VALUE FOR seq_category, 'Toidukaubad', 1);
INSERT INTO category (category_id, name, user_id) VALUES (NEXT VALUE FOR seq_category, 'Meelelahutus', 1);
INSERT INTO category (category_id, name, user_id) VALUES (NEXT VALUE FOR seq_category, 'Kodutarbed', 1);
INSERT INTO category (category_id, name, user_id) VALUES (NEXT VALUE FOR seq_category, 'elektroonika', 1);
INSERT INTO expense(expense_id, category_id, user_id, insertion_time, comment, amount) VALUES (NEXT VALUE FOR seq_expense, 1, 2, '2017-08-16 09:37:38','useful', 24.50)
