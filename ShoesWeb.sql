create database ShoesWeb;
drop database ShoesWeb;

use ShoesWeb;

CREATE TABLE users (
  id INT IDENTITY(1,1) PRIMARY KEY,
  fullname NVARCHAR(250) NOT NULL,
  email NVARCHAR(250) NOT NULL UNIQUE,
  password NVARCHAR(250) NOT NULL,
  roleid INT NOT NULL,
  phone NVARCHAR(20)
);

CREATE TABLE products (
  id INT IDENTITY(1,1) PRIMARY KEY,
  name NVARCHAR(450) NOT NULL,
  description NVARCHAR(450) NOT NULL, 
  category NVARCHAR(450) NOT NULL,
  price FLOAT NOT NULL,
  stock INT NOT NULL,
  image NVARCHAR(450) NOT NULL
);

CREATE TABLE orders (
  oid INT IDENTITY(1,1) PRIMARY KEY,
  totalprice FLOAT NOT NULL,
  address VARCHAR(450) NOT NULL,
  note VARCHAR(450) NOT NULL,
  date VARCHAR(450) NOT NULL,
  uid INT NOT NULL,
  CONSTRAINT fk_user FOREIGN KEY (uid) REFERENCES users(id)
);

CREATE TABLE orders_details (
  oid INT NOT NULL,
  pid INT NOT NULL,
  price FLOAT NOT NULL, 
  quantity INT NOT NULL,
  CONSTRAINT fk_order FOREIGN KEY (oid) REFERENCES orders(oid),
  CONSTRAINT fk_product FOREIGN KEY (pid) REFERENCES products(id)  
);

INSERT INTO products (name, description, category, price, stock, image)
VALUES
('Nike Air Force 1', 'Classic sports shoes from Nike', 'Sports Shoes', 120, 50, 'image1.jpg'),
('Adidas Ultraboost', 'Premium running shoes from Adidas', 'Running Shoes', 180, 50, 'image2.jpg'),
('Converse Chuck Taylor', 'Indispensable commemorative shoes', 'Sports Shoes', 55, 50, 'image3.jpg'),
('Vans Old Skool', 'Stylish sports shoes from Vans', 'Sports Shoes', 60, 50, 'image4.jpg'),
('Balenciaga Speed Trainer', 'High-end fashion shoes from Balenciaga', 'Fashion Shoes', 795, 50, 'image5.jpg'),
('Gucci Ace Embroidered', 'Luxury fashion shoes from Gucci', 'Fashion Shoes', 650, 50, 'image6.jpg'),
('New Balance 990v5', 'Comfortable running shoes from New Balance', 'Running Shoes', 175, 50, 'image7.jpg'),
('Reebok Club C 85', 'Classic sports shoes from Reebok', 'Sports Shoes', 70, 50, 'image8.jpg'),
('Puma Suede Classic', 'Classic sports shoes from Puma', 'Sports Shoes', 65, 50, 'image9.jpg'),
('Under Armour HOVR Phantom', 'Modern running shoes from Under Armour', 'Running Shoes', 130, 50, 'image10.jpg'),
('Asics Gel-Kayano 26', 'Reliable running shoes from Asics', 'Running Shoes', 160, 50, 'image11.jpg'),
('Jordan Air Jordan 1', 'Legendary sports shoes from Jordan', 'Sports Shoes', 160, 50, 'image12.jpg'),
('Timberland 6 Inch Premium Waterproof Boot', 'Waterproof boots from Timberland', 'Boots', 198, 50, 'image13.jpg'),
('Dr. Martens 1460', 'Classic boots from Dr. Martens', 'Boots', 150, 50, 'image14.jpg'),
('Birkenstock Arizona', 'Comfortable sandals from Birkenstock', 'Sandals', 100, 50, 'image15.jpg'),
('Crocs Classic Clog', 'Comfortable sandals from Crocs', 'Sandals', 45, 50, 'image16.jpg'),
('Yeezy Boost 350 V2', 'Fashion shoes from Yeezy', 'Fashion Shoes', 220, 50, 'image17.jpg'),
('Off-White x Nike Air Presto', 'Sports shoes from Off-White and Nike', 'Sports Shoes', 160, 50, 'image18.jpg'),
('Prada Cloudbust Thunder', 'Fashion shoes from Prada', 'Fashion Shoes', 895, 50, 'image19.jpg'),
('Louis Vuitton Archlight', 'Fashion shoes from Louis Vuitton', 'Fashion Shoes', 1090, 50, 'image20.jpg'),
('Nike Air Max 97', 'Classic sports shoes from Nike', 'Sports Shoes', 170, 50, 'image21.jpg'),
('Adidas NMD R1', 'Comfortable running shoes from Adidas', 'Running Shoes', 140, 50, 'image22.jpg'),
('Converse One Star', 'Stylish sports shoes from Converse', 'Sports Shoes', 85, 50, 'image23.jpg'),
('Vans Sk8-Hi', 'High-top sports shoes from Vans', 'Sports Shoes', 80, 50, 'image24.jpg'),
('Balenciaga Track Trainer', 'High-end fashion shoes from Balenciaga', 'Fashion Shoes', 895, 50, 'image25.jpg'),
('Gucci Rhyton', 'Luxury fashion shoes from Gucci', 'Fashion Shoes', 890, 50, 'image26.jpg'),
('New Balance 574', 'Classic running shoes from New Balance', 'Running Shoes', 80, 50, 'image27.jpg'),
('Reebok Classic Leather', 'Classic sports shoes from Reebok', 'Sports Shoes', 75, 50, 'image28.jpg'),
('Puma RS-X', 'Modern sports shoes from Puma', 'Sports Shoes', 110, 50, 'image29.jpg'),
('Under Armour Project Rock 1', 'Training shoes from Under Armour', 'Training Shoes', 120, 50, 'image30.jpg'),
('Asics Gel-Nimbus 21', 'Reliable running shoes from Asics', 'Running Shoes', 150, 50, 'image31.jpg'),
('Jordan Air Jordan 4', 'Legendary sports shoes from Jordan', 'Sports Shoes', 200, 50, 'image32.jpg'),
('Timberland Earthkeepers', 'Eco-friendly boots from Timberland', 'Boots', 160, 50, 'image33.jpg'),
('Dr. Martens 1490', 'Classic boots from Dr. Martens', 'Boots', 180, 50, 'image34.jpg'),
('Birkenstock Gizeh', 'Comfortable sandals from Birkenstock', 'Sandals', 95, 50, 'image35.jpg'),
('Crocs LiteRide Clog', 'Comfortable sandals from Crocs', 'Sandals', 55, 50, 'image36.jpg'),
('Yeezy Boost 700', 'Fashion shoes from Yeezy', 'Fashion Shoes', 300, 50, 'image37.jpg'),
('Off-White x Nike Air Max 90', 'Sports shoes from Off-White and Nike', 'Sports Shoes', 160, 50, 'image38.jpg'),
('Prada America''s Cup', 'Fashion shoes from Prada', 'Fashion Shoes', 690, 50, 'image39.jpg'),
('Louis Vuitton Run Away', 'Fashion shoes from Louis Vuitton', 'Fashion Shoes', 965, 50, 'image40.jpg'),
('Nike Air VaporMax', 'Modern sports shoes from Nike', 'Sports Shoes', 190, 50, 'image41.jpg'),
('Adidas Yung-1', 'Retro running shoes from Adidas', 'Running Shoes', 120, 50, 'image42.jpg'),
('Converse Jack Purcell', 'Classic sports shoes from Converse', 'Sports Shoes', 65, 50, 'image43.jpg'),
('Vans Authentic', 'Original sports shoes from Vans', 'Sports Shoes', 50, 50, 'image44.jpg'),
('Balenciaga Triple S', 'Trendy fashion shoes from Balenciaga', 'Fashion Shoes', 975, 50, 'image45.jpg'),
('Gucci Screener', 'Vintage fashion shoes from Gucci', 'Fashion Shoes', 870, 50, 'image46.jpg'),
('New Balance X-90', 'Modern running shoes from New Balance', 'Running Shoes', 110, 50, 'image47.jpg'),
('Reebok Aztrek', 'Retro sports shoes from Reebok', 'Sports Shoes', 90, 50, 'image48.jpg'),
('Puma Thunder Spectra', 'Chunky sports shoes from Puma', 'Sports Shoes', 120, 50, 'image49.jpg'),
('Under Armour Curry 6', 'Basketball shoes from Under Armour', 'Basketball Shoes', 130, 50, 'image50.jpg');


INSERT INTO users (fullname, email, password, roleid, phone)
VALUES
('User 1', 'user1@gmail.com', '123', 0, '0123456789'),
('Admin 1', 'admin1@gmail.com', '123', 1, '0123456789');
