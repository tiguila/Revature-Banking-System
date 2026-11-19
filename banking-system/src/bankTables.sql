

-- 1. DROP EXISTING TABLES
DROP TABLE IF EXISTS transactions;
DROP TABLE IF EXISTS accounts;
DROP TABLE IF EXISTS users;



-- 2. CREATE USERS, accounts, transactions TABLEs

CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    userType VARCHAR(50) NOT NULL,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);


CREATE TABLE accounts (
    account_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL UNIQUE,
    account_number INT NOT NULL UNIQUE,
    balance DECIMAL(15, 2) NOT NULL DEFAULT 0.00,
    status VARCHAR(20) NOT NULL DEFAULT 'pending',

    CONSTRAINT fk_account_user
    FOREIGN KEY (user_id)
    REFERENCES users(user_id),

    CONSTRAINT chk_account_balance
    CHECK (balance >= 0)
);


CREATE TABLE transactions (
    transaction_id INT AUTO_INCREMENT PRIMARY KEY,
    account_id INT NOT NULL,
    transaction_type VARCHAR(20) NOT NULL,
    amount DECIMAL(15, 2) NOT NULL,
    transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_transaction_account
        FOREIGN KEY (account_id)
        REFERENCES accounts(account_id),

    CONSTRAINT chk_transaction_type
        CHECK (transaction_type IN ('deposit', 'withdraw', 'transfer')),

    CONSTRAINT chk_transaction_amount
        CHECK (amount > 0)
);







-- ==========================================
-- 5. INSERT DUMMY USERS, ACCOUNTS, TRANSACTIONS data
-- ==========================================

INSERT INTO users (userType, username, password)
VALUES
    ('employee', 'admin', 'hashed_password_1'),
    ('customer', 'john', 'hashed_password_2'),
    ('customer', 'mary', 'hashed_password_3'),
    ('customer', 'david', 'hashed_password_4'),
    ('employee', 'manager', 'hashed_password_5');


INSERT INTO accounts (user_id, account_number, balance, status)
VALUES
    (2, 100001, 5000.00, 'approved'),
    (3, 100002, 2500.00, 'approved'),
    (4, 100003, 1000.00, 'pending');


INSERT INTO transactions (account_id, transaction_type, amount)
VALUES
    (1, 'deposit', 5000.00),
    (1, 'withdraw', 500.00),
    (1, 'deposit', 500.00),
    (2, 'deposit', 2500.00),
    (2, 'withdraw', 200.00),
    (3, 'deposit', 1000.00),
    (1, 'transfer', 300.00);


SELECT * FROM users;

SELECT * FROM accounts;

SELECT * FROM transactions;


