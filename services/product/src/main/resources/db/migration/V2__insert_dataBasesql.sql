-- Inserciones para la tabla "category"
INSERT INTO category (id, description, name)
VALUES (nextval('category_seq'), 'Electronics and gadgets', 'Electronics');

INSERT INTO category (id, description, name)
VALUES (nextval('category_seq'), 'Clothing and fashion', 'Clothing');

INSERT INTO category (id, description, name)
VALUES (nextval('category_seq'), 'Books and stationery', 'Books');

-- Inserciones para la tabla "product"
INSERT INTO product (id, description, name, aviable_quantity, price, category_id)
VALUES (nextval('product_seq'), 'Smartphone with 64GB storage', 'Smartphone', 100, 699.99,
        (SELECT id FROM category WHERE name = 'Electronics'));

INSERT INTO product (id, description, name, aviable_quantity, price, category_id)
VALUES (nextval('product_seq'), 'Wireless headphones', 'Headphones', 50, 199.99,
        (SELECT id FROM category WHERE name = 'Electronics'));

INSERT INTO product (id, description, name, aviable_quantity, price, category_id)
VALUES (nextval('product_seq'), 'Cotton T-shirt', 'T-shirt', 200, 19.99,
        (SELECT id FROM category WHERE name = 'Clothing'));

INSERT INTO product (id, description, name, aviable_quantity, price, category_id)
VALUES (nextval('product_seq'), 'Novel by famous author', 'Novel', 150, 14.99,
        (SELECT id FROM category WHERE name = 'Books'));
