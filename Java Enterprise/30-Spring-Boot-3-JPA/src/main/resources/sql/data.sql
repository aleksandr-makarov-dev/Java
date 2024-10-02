-- Insert data into companies table
INSERT INTO companies (name) VALUES ('TechCorp');
INSERT INTO companies (name) VALUES ('MediHealth');
INSERT INTO companies (name) VALUES ('EduSoft');
INSERT INTO companies (name) VALUES ('GreenEnergy');
INSERT INTO companies (name) VALUES ('AutoWorks');

-- Insert data into company_locales table (English and Russian)
INSERT INTO company_locales (company_id, lang, description) VALUES (1, 'en', 'A leading tech company');
INSERT INTO company_locales (company_id, lang, description) VALUES (1, 'ru', 'Ведущая технологическая компания');
INSERT INTO company_locales (company_id, lang, description) VALUES (2, 'en', 'Healthcare provider');
INSERT INTO company_locales (company_id, lang, description) VALUES (2, 'ru', 'Поставщик медицинских услуг');
INSERT INTO company_locales (company_id, lang, description) VALUES (3, 'en', 'Education software solutions');
INSERT INTO company_locales (company_id, lang, description) VALUES (3, 'ru', 'Решения для образовательного программного обеспечения');
INSERT INTO company_locales (company_id, lang, description) VALUES (4, 'en', 'Renewable energy company');
INSERT INTO company_locales (company_id, lang, description) VALUES (4, 'ru', 'Компания по возобновляемой энергетике');
INSERT INTO company_locales (company_id, lang, description) VALUES (5, 'en', 'Automotive parts manufacturer');
INSERT INTO company_locales (company_id, lang, description) VALUES (5, 'ru', 'Производитель автозапчастей');

-- Insert data into users table
INSERT INTO users (username, birth_date, firstname, lastname, role, company_id) VALUES ('jdoe', '1990-06-15', 'John', 'Doe', 'Engineer', 1);
INSERT INTO users (username, birth_date, firstname, lastname, role, company_id) VALUES ('asmith', '1985-03-22', 'Alice', 'Smith', 'Manager', 2);
INSERT INTO users (username, birth_date, firstname, lastname, role, company_id) VALUES ('bclark', '1995-12-01', 'Bob', 'Clark', 'Analyst', 3);
INSERT INTO users (username, birth_date, firstname, lastname, role, company_id) VALUES ('djohnson', '1988-07-30', 'David', 'Johnson', 'Technician', 4);
INSERT INTO users (username, birth_date, firstname, lastname, role, company_id) VALUES ('mgarcia', '1992-09-10', 'Maria', 'Garcia', 'Engineer', 5);

-- Insert data into payments table
INSERT INTO payments (amount, receiver_id) VALUES (1500, 1);
INSERT INTO payments (amount, receiver_id) VALUES (2000, 2);
INSERT INTO payments (amount, receiver_id) VALUES (1800, 3);
INSERT INTO payments (amount, receiver_id) VALUES (2200, 4);
INSERT INTO payments (amount, receiver_id) VALUES (2500, 5);

-- Insert data into chats table
INSERT INTO chats (name) VALUES ('Project Alpha');
INSERT INTO chats (name) VALUES ('Healthcare Support');
INSERT INTO chats (name) VALUES ('Education Forum');
INSERT INTO chats (name) VALUES ('Energy Innovations');
INSERT INTO chats (name) VALUES ('Automotive R&D');

-- Insert data into user_chats table
INSERT INTO user_chats (user_id, chat_id) VALUES (1, 1);
INSERT INTO user_chats (user_id, chat_id) VALUES (2, 2);
INSERT INTO user_chats (user_id, chat_id) VALUES (3, 3);
INSERT INTO user_chats (user_id, chat_id) VALUES (4, 4);
INSERT INTO user_chats (user_id, chat_id) VALUES (5, 5);
