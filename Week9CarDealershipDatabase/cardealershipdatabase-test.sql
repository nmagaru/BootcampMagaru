SELECT * FROM dealerships;

-- search by dealership id, return all vehicle info
SELECT d.dealership_id, d.name, v.*
FROM vehicles v
JOIN inventory i ON i.vin = v.vin
JOIN dealerships d ON d.dealership_id = i.dealership_id
WHERE d.dealership_id = 3;		

-- search by VIN
SELECT *
FROM vehicles
WHERE vin = 783456789;

-- search by VIN, return dealership info
SELECT d.*
FROM vehicles v
JOIN inventory i ON i.vin = v.vin
JOIN dealerships d ON d.dealership_id = i.dealership_id 
WHERE v.vin = 783456789;

-- search by vehicle info, return all dealership info
SELECT d.*, v.*
FROM dealerships d
JOIN inventory i ON i.dealership_id = d.dealership_id
JOIN vehicles v ON v.vin = i.vin 
WHERE v.brand = 'Chevrolet' OR v.model = 'Wrangler' OR v.color = 'Red';

-- search by date range, return all sales info
SELECT sc.*, i.dealership_id
FROM sales_contracts sc
JOIN inventory i ON i.vin = sc.vin
WHERE i.dealership_id = 1
-- should filter out added sales contract (date 2025-06-08)
AND date BETWEEN '2025-05-01' AND '2025-06-01';