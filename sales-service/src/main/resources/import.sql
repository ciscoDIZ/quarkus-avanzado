-- noinspection SqlNoDataSourceInspectionForFile
INSERT INTO customer(id, customerId, name, email) VALUES (100, 'c1', 'Cally Reynolds', 'penatibus.et@lectusa.com');
INSERT INTO customer(id, customerId, name, email) VALUES (200, 'c2', 'Sydney Bartlett', 'nibh@ultricesposuere.edu');
INSERT INTO customer(id, customerId, name, email) VALUES (300, 'c3', 'Hunter Newton', 'quam.quis.diam@facilisisfacilisis.org');
INSERT INTO productsale(id, sku, name, total) VALUES (40, 'KE001', 'K-Eco phone charger', 13.85);
INSERT INTO productsale(id, sku, name, total) VALUES (50, 'KE13W04', 'K-Eco Energy Bulbs 13W (4-pack)', 6.55);
INSERT INTO productsale(id, sku, name, total) VALUES (60, 'KE001', 'K-Eco phone charger', 13.85);
INSERT INTO productsale(id, sku, name, total) VALUES (70, 'KE325','K-Eco 325', 568.75);
INSERT INTO productsale(id, sku, name, total) VALUES (80, 'KE13W', 'K-Eco Energy Bulbs 13W', 2.24);
INSERT INTO productsale(id, sku, name, total) VALUES (90, 'KE275','K-Eco 275', 481.25);
INSERT INTO productsale(id, sku, name, total) VALUES (100, 'KE300','K-Eco 300', 525.00);
INSERT INTO customersale(id, customer_id) VALUES (110, 100);
INSERT INTO customersale(id, customer_id) VALUES (120, 200);
INSERT INTO customersale(id, customer_id) VALUES (130, 300);
INSERT INTO customersale_productsale(customersale_id, productsale_id) VALUES (110, 40);
INSERT INTO customersale_productsale(customersale_id, productsale_id) VALUES (110, 50);
INSERT INTO customersale_productsale(customersale_id, productsale_id) VALUES (120, 60);
INSERT INTO customersale_productsale(customersale_id, productsale_id) VALUES (120, 70);
INSERT INTO customersale_productsale(customersale_id, productsale_id) VALUES (130, 80);
INSERT INTO customersale_productsale(customersale_id, productsale_id) VALUES (130, 90);
INSERT INTO customersale_productsale(customersale_id, productsale_id) VALUES (130, 100);