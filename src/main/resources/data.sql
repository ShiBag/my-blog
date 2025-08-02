INSERT INTO person (name, username, password) VALUES
('Admin', 'admin', 'admin'),
('John Doe', 'john', 'pass123');

INSERT INTO category (category_name) VALUES
('Technology'),
('Programming'),
('Lifestyle');

INSERT INTO tag (tag_name) VALUES
('Java'),
('Spring Boot'),
('Hibernate'),
('Tutorial');

INSERT INTO post (post_name, post_content, person_id, category_id) VALUES
('First Blog Post', 'This is the content of the first blog post.', 1, 2),
('Hibernate and JPA Guide', 'A detailed guide on Hibernate and JPA mappings.', 1, 2),
('Lifestyle Tips', 'Simple lifestyle tips to improve productivity.', 2, 3);

INSERT INTO post_tags (post_id, tag_id) VALUES
(1, 1),
(1, 4);

INSERT INTO post_tags (post_id, tag_id) VALUES
(2, 3),
(2, 1),
(2, 2);

INSERT INTO post_tags (post_id, tag_id) VALUES
(3, 4);