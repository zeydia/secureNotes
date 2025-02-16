use securenotes;

INSERT INTO roles(role_name) values('ROLE_USER');
INSERT INTO roles(role_name) values('ROLE_ADMIN');

INSERT INTO users(username, email, password, role_id)
    values('user1','user1@email.com','passeruser1',1);
INSERT INTO users(username, email, password, role_id)
    values('admin1','admin1@email.com','passeradmin1',2);

SELECT * from roles;
SELECT * from users;