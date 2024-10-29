CREATE TABLE clients (
                         client_id INT PRIMARY KEY AUTO_INCREMENT,
                         first_name VARCHAR(50) NOT NULL,
                         last_name VARCHAR(50) NOT NULL,
                         dni VARCHAR(20) UNIQUE NOT NULL,
                         phone VARCHAR(20),
                         email VARCHAR(100) NOT NULL,
                         password VARCHAR(255) NOT NULL
);

CREATE TABLE admins (
                        admin_id INT PRIMARY KEY AUTO_INCREMENT,
                        first_name VARCHAR(50) NOT NULL,
                        last_name VARCHAR(50) NOT NULL,
                        dni VARCHAR(20) UNIQUE NOT NULL,
                        employee_number VARCHAR(20) UNIQUE NOT NULL,
                        phone VARCHAR(20),
                        password VARCHAR(255) NOT NULL
);

CREATE TABLE authors (
                         author_id INT PRIMARY KEY AUTO_INCREMENT,
                         first_name VARCHAR(50) NOT NULL,
                         last_name VARCHAR(50) NOT NULL,
                         nationality VARCHAR(50)
);

CREATE TABLE books (
                       book_id INT PRIMARY KEY AUTO_INCREMENT,
                       title VARCHAR(255) NOT NULL,
                       isbn VARCHAR(20) UNIQUE NOT NULL,
                       page_count INT NOT NULL,
                       genre VARCHAR(50),
                       edition VARCHAR(50),
                       available_copies INT DEFAULT 0
);

-- Intermidate table author-book
CREATE TABLE book_authors (
                              book_id INT,
                              author_id INT,
                              PRIMARY KEY (book_id, author_id),
                              FOREIGN KEY (book_id) REFERENCES books(book_id) ON DELETE CASCADE,
                              FOREIGN KEY (author_id) REFERENCES authors(author_id) ON DELETE CASCADE
);

CREATE TABLE publishers (
                            publisher_id INT PRIMARY KEY AUTO_INCREMENT,
                            name VARCHAR(255) NOT NULL,
                            address VARCHAR(255),
                            website VARCHAR(255),
                            email VARCHAR(100),
                            contact_number VARCHAR(20)
);

-- Intermidate table book-editorial
CREATE TABLE book_publishers (
                                 book_id INT,
                                 publisher_id INT,
                                 PRIMARY KEY (book_id, publisher_id),
                                 FOREIGN KEY (book_id) REFERENCES books(book_id) ON DELETE CASCADE,
                                 FOREIGN KEY (publisher_id) REFERENCES publishers(publisher_id) ON DELETE CASCADE
);

CREATE TABLE reservations (
                              reservation_id INT PRIMARY KEY AUTO_INCREMENT,
                              client_id INT NOT NULL,
                              admin_id INT,
                              reservation_date DATE NOT NULL,
                              due_date DATE,
                              is_approved BOOLEAN DEFAULT FALSE,
                              is_returned BOOLEAN DEFAULT FALSE,
                              FOREIGN KEY (client_id) REFERENCES clients(client_id) ON DELETE CASCADE,
                              FOREIGN KEY (admin_id) REFERENCES admins(admin_id)
);

-- Mocks
INSERT INTO clients (first_name, last_name, dni, phone, email, password) VALUES
('John', 'Doe', '12345678', '555-1234', 'john.doe@example.com', 'password123'),
('Jane', 'Smith', '87654321', '555-5678', 'jane.smith@example.com', 'password456');

INSERT INTO admins (first_name, last_name, dni, employee_number, phone, password) VALUES
('Alice', 'Johnson', '11223344', 'EMP001', '555-0001', 'adminpassword1'),
('Bob', 'Brown', '44332211', 'EMP002', '555-0002', 'adminpassword2');

INSERT INTO authors (first_name, last_name, nationality) VALUES
('George', 'Orwell', 'British'),
('F. Scott', 'Fitzgerald', 'American');

INSERT INTO books (title, isbn, page_count, genre, edition, available_copies) VALUES
('1984', '9780451524935', 328, 'Dystopian', '1st', 3),
('The Great Gatsby', '9780743273565', 180, 'Classic', '1st', 5);

INSERT INTO publishers (name, address, website, email, contact_number) VALUES
('Penguin Books', '123 Penguin Lane', 'www.penguin.com', 'info@penguin.com', '555-0003'),
('Scribner', '456 Scribner Ave', 'www.scribner.com', 'info@scripner.com', '555-0004');

INSERT INTO book_authors (book_id, author_id) VALUES
(1, 1), -- 1984 by George Orwell
(2, 2); -- The Great Gatsby by F. Scott Fitzgerald

INSERT INTO book_publishers (book_id, publisher_id) VALUES
(1, 1), -- 1984 published by Penguin Books
(2, 2); -- The Great Gatsby published by Scribner

INSERT INTO reservations (client_id, admin_id, reservation_date, due_date, is_approved, is_returned) VALUES
(1, 1, '2024-10-20', '2024-11-20', TRUE, FALSE),
(2, 2, '2024-10-21', '2024-11-21', FALSE, FALSE);


-- Store procedure
DELIMITER //

CREATE PROCEDURE deleteAdmin(IN adminEmployeeNumber VARCHAR(20))
BEGIN
    DELETE FROM admins WHERE employee_number = adminEmployeeNumber;
END //

DELIMITER ;