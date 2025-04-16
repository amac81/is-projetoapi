-- dados de exemplo tb_user
INSERT INTO tb_user (username, password, name, age, nif) VALUES ('joao', '$2a$10$FxIVtBa17WwEf04sN365B.1kEIPkZWJs9VC0Lpv8s8.F6gsRxMD9u', 'João Almeida', 29, '901234567');
INSERT INTO tb_user (username, password, name, age, nif) VALUES ('carla', '$2a$10$BuCtj4aqd1zbwmBx0q4Mpu5sZASyJmtjx1IvMHjJcpiWWlrL/K9nu', 'Carla Ribeiro', 34, '912345678');
INSERT INTO tb_user (username, password, name, age, nif) VALUES ('pedro', '$2a$10$BuCtj4aqd1zbwmBx0q4Mpu5sZASyJmtjx1IvMHjJcpiWWlrL/K9nu', 'Pedro Costa', 40, '923456789');
INSERT INTO tb_user (username, password, name, age, nif) VALUES ('ines', '$2a$10$BuCtj4aqd1zbwmBx0q4Mpu5sZASyJmtjx1IvMHjJcpiWWlrL/K9nu', 'Inês Dias', 26, '934567890');
INSERT INTO tb_user (username, password, name, age, nif) VALUES ('luis', '$2a$10$BuCtj4aqd1zbwmBx0q4Mpu5sZASyJmtjx1IvMHjJcpiWWlrL/K9nu', 'Luís Fernandes', 38, '945678901');
INSERT INTO tb_user (username, password, name, age, nif) VALUES ('sofia', '$2a$10$BuCtj4aqd1zbwmBx0q4Mpu5sZASyJmtjx1IvMHjJcpiWWlrL/K9nu', 'Sofia Martins', 31, '456789012');
INSERT INTO tb_user (username, password, name, age, nif) VALUES ('rui', '$2a$10$FxIVtBa17WwEf04sN365B.1kEIPkZWJs9VC0Lpv8s8.F6gsRxMD9u', 'Rui Gomes', 50, '567890123');
INSERT INTO tb_user (username, password, name, age, nif) VALUES ('ana', '$2a$10$FxIVtBa17WwEf04sN365B.1kEIPkZWJs9VC0Lpv8s8.F6gsRxMD9u', 'Ana Costa', 23, '678901234');
INSERT INTO tb_user (username, password, name, age, nif) VALUES ('carlos', '$2a$10$FxIVtBa17WwEf04sN365B.1kEIPkZWJs9VC0Lpv8s8.F6gsRxMD9u', 'Carlos Pereira', 47, '789012345');

-- dados de exemplo tb_account
INSERT INTO tb_account (accountnumber, holder_id, balance, creationdate) VALUES (1000000001, 1, 1500.50, '2023-01-15');
INSERT INTO tb_account (accountnumber, holder_id, balance, creationdate) VALUES (1000000002, 2, 235.75, '2022-11-03');
INSERT INTO tb_account (accountnumber, holder_id, balance, creationdate) VALUES (1000000003, 1, 9820.00, '2021-06-21');
INSERT INTO tb_account (accountnumber, holder_id, balance, creationdate) VALUES (1000000004, 4, 125.00, '2024-02-10');
INSERT INTO tb_account (accountnumber, holder_id, balance, creationdate) VALUES (1000000005, 2, 450.25, '2023-09-05');
INSERT INTO tb_account (accountnumber, holder_id, balance, creationdate) VALUES (1000000006, 6, 5000.00, '2020-12-01');
INSERT INTO tb_account (accountnumber, holder_id, balance, creationdate) VALUES (1000000007, 7, 742.30, '2023-04-18');
INSERT INTO tb_account (accountnumber, holder_id, balance, creationdate) VALUES (1000000008, 5, 85.75, '2024-01-25');
INSERT INTO tb_account (accountnumber, holder_id, balance, creationdate) VALUES (1000000009, 9, 3210.90, '2022-07-14');
INSERT INTO tb_account (accountnumber, holder_id, balance, creationdate) VALUES (1000000010, 1, 110.00, '2023-10-30'); 

-- dados de exemplo tb_accountmovement
