INSERT INTO category (id, name) VALUES (1, 'Electronics');
INSERT INTO brand (id, name) VALUES (1, 'Samsung');
INSERT INTO vendor (id, name) VALUES (1, 'TechVendor');
INSERT INTO site (id, name) VALUES (1, 'Main Site');
INSERT INTO warehouse (id, name) VALUES (1, 'Central Warehouse');
INSERT INTO stock (id, quantity) VALUES (1, 100);
INSERT INTO uom (id, name) VALUES (1, 'Piece');
INSERT INTO product_lot (id, lot_code, date_manufactured, date_expiry) VALUES (1, 'LOT123', '2024-01-01', '2025-01-01');
INSERT INTO product (id, name, sku, description, unit_price, quantity, gst_percentage, unit_of_measure, status, category_id, brand_id, vendor_id, site_id, warehouse_id, stock_id, uom_id, product_lot_id)
VALUES (1, 'Smartphone', 'SM123', 'Latest model', 500.0, 50, 18.0, 'Piece', 'Available', 1, 1, 1, 1, 1, 1, 1, 1);