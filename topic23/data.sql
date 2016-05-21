USE hibernate;

CREATE TABLE book_shelves (
  id INTEGER PRIMARY KEY AUTO_INCREMENT,
  shelf_name VARCHAR(128) NOT NULL
);

CREATE TABLE books (
  id            INTEGER PRIMARY KEY AUTO_INCREMENT,
  book_name     VARCHAR(128) NOT NULL,
  author        VARCHAR(64)  NOT NULL,
  publish_date  DATE         NOT NULL,
  book_shelf_id INTEGER      NULL,
  FOREIGN KEY (book_shelf_id) REFERENCES book_shelves (id)
);
