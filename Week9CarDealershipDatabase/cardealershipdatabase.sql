CREATE DATABASE IF NOT EXISTS CarDealershipDatabase;
USE CarDealershipDatabase;

CREATE TABLE dealerships (
	dealership_id	INT AUTO_INCREMENT PRIMARY KEY,
    name			VARCHAR(50) NOT NULL,
    address			VARCHAR(50),
    phone			VARCHAR(12)
);

CREATE TABLE vehicles (
	vin				INT PRIMARY KEY,
    brand			VARCHAR(50),
    model			VARCHAR(50),
    year			INT,
    color			VARCHAR(50),
    type			VARCHAR(50),
    sold			boolean
);

CREATE TABLE inventory (
	dealership_id	INT,
    vin				INT
);

CREATE TABLE sales_contracts (
	sales_id		INT AUTO_INCREMENT PRIMARY KEY,
    name			VARCHAR(50),
    address			VARCHAR(50),
    phone			VARCHAR(12),
    date			VARCHAR(10),
    price			INT,
    vin				INT,
    FOREIGN KEY (vin) REFERENCES vehicles(vin)
);

CREATE TABLE lease_contracts (
	lease_id		INT AUTO_INCREMENT PRIMARY KEY,
    name			VARCHAR(50),
    address			VARCHAR(50),
    phone			VARCHAR(12),
    date			VARCHAR(10),
    price			INT,
    vin				INT,
    FOREIGN KEY (vin) REFERENCES vehicles(vin)
);


INSERT INTO dealerships (name, address, phone) VALUES
('Metro Motors', '123 Main St', '555-123-4567'),
('Auto Galaxy', '456 Elm Ave', '555-234-5678'),
('DriveTime Deals', '789 Oak Blvd', '555-345-6789');

INSERT INTO vehicles (vin, brand, model, year, color, type, sold) VALUES
(783456789, 'Toyota', 'Camry', 2022, 'Silver', 'Sedan', FALSE),
(915273846, 'Honda', 'Civic', 2023, 'Black', 'Sedan', TRUE),
(674382915, 'Ford', 'F-150', 2021, 'Blue', 'Truck', FALSE),
(839104562, 'Chevrolet', 'Tahoe', 2020, 'White', 'SUV', TRUE),
(104857392, 'Tesla', 'Model 3', 2023, 'Red', 'Electric', TRUE),
(234981076, 'Jeep', 'Wrangler', 2019, 'Green', 'SUV', TRUE);

INSERT INTO inventory (dealership_id, vin) VALUES
(1, 783456789),
(1, 915273846),
(2, 674382915),
(2, 839104562),
(3, 104857392),
(3, 234981076);

INSERT INTO sales_contracts (name, address, phone, date, price, vin) VALUES
('Alice Johnson', '321 Pine St', '555-111-2222', '2025-05-01', 22000, 915273846),
('Bob Smith', '654 Maple Rd', '555-333-4444', '2025-05-15', 45000, 104857392);

INSERT INTO lease_contracts (name, address, phone, date, price, vin) VALUES
('Carlos Reyes', '987 Cedar Ln', '555-555-6666', '2025-04-20', 15000, 839104562),
('Dana Lee', '135 Birch Ave', '555-777-8888', '2025-06-01', 18000, 234981076);

-- updating sales contract for test script
UPDATE vehicles 
SET sold = TRUE 
WHERE vin = 783456789;

INSERT INTO sales_contracts (name, address, phone, date, price, vin) VALUES
('Evelyn Brooks', '789 Walnut Dr', '555-999-0000', '2025-06-08', 25000, 783456789);


