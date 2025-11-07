INSERT INTO customers(id, first_name, last_name, email) VALUES
                                                            (1, 'Sam', 'Smith', 'sam@example.com'),
                                                            (2, 'Jane', 'Doe', 'jane@example.com');

INSERT INTO accounts(id, iban, balance, customer_id) VALUES
                                                         (1, 'FR76 3000 6000 0112 3456 7890 189', 1000.00, 1),
                                                         (2, 'FR76 3000 6000 0112 3456 7890 190', 2500.00, 2);