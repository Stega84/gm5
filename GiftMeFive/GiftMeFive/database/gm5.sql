DROP DATABASE gm5;
CREATE DATABASE gm5;
USE gm5;

CREATE TABLE wishlist(
  id  int NOT NULL AUTO_INCREMENT,
  name VARCHAR(80) NOT NULL,
  enddate DATE NOT NULL,
  saved BOOLEAN NOT NULL DEFAULT 0,
  imagelink VARCHAR(80) NOT NULL DEFAULT 'getimage/24',
  PRIMARY KEY (id)
);

CREATE TABLE article(
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(80) NOT NULL,
  description TEXT NOT NULL,
  creationdate DATETIME NOT NULL DEFAULT NOW(),
  imagelink VARCHAR(80) NOT NULL DEFAULT 'getimage/1',
  productlink TEXT,
  wishlistId INT NOT NULL,
  PRIMARY KEY(id),
  FOREIGN KEY (wishlistId) REFERENCES wishlist(id) ON DELETE CASCADE
);

CREATE TABLE reservation(
  id INT NOT NULL,
  name VARCHAR(80) NOT NULL DEFAULT 'nicht reserviert',
  reserved BOOLEAN NOT NULL DEFAULT 0,
  PRIMARY KEY(id),
  FOREIGN KEY(id) REFERENCES article(id) ON DELETE CASCADE
);

CREATE TABLE categoryimage(
  id INT NOT NULL AUTO_INCREMENT,
  category MEDIUMBLOB NOT NULL,
  PRIMARY KEY(id)
);

CREATE TRIGGER trg_Artikel
AFTER INSERT ON article FOR EACH ROW
INSERT INTO reservation (id) VALUES (new.id);

CREATE VIEW Normal AS
SELECT *
FROM article
WHERE wishlistId = 1;

DELIMITER / /
CREATE PROCEDURE ShowWishlist(IN id INT)
BEGIN
SELECT *, wishlist.name AS wishlistname, reservation.reserved, reservation.name AS reservationname
FROM article JOIN wishlist ON wishlistId = wishlist.id JOIN reservation ON article.id = reservation.id
WHERE wishlistId = id ;
END 
/ / DELIMITER ;

DELIMITER / /
CREATE PROCEDURE ShowReservations(IN name VARCHAR(80))
BEGIN
SELECT *, wishlist.name AS wishlistname, reservation.reserved, reservation.name AS reservationname
FROM article JOIN wishlist ON wishlistId = wishlist.id JOIN reservation ON article.id = reservation.id
WHERE reservation.name = name;
END / /
DELIMITER ;


INSERT INTO categoryimage(id, category) VALUES(1, LOAD_FILE('/var/lib/mysql-files/default.jpg'));
INSERT INTO categoryimage(id, category) VALUES(2, LOAD_FILE('/var/lib/mysql-files/baby.webp'));
INSERT INTO categoryimage(id, category) VALUES(3, LOAD_FILE('/var/lib/mysql-files/beauty.webp'));
INSERT INTO categoryimage(id, category) VALUES(4, LOAD_FILE('/var/lib/mysql-files/bekleidung.webp'));
INSERT INTO categoryimage(id, category) VALUES(5, LOAD_FILE('/var/lib/mysql-files/buecherZeitschriften.webp'));
INSERT INTO categoryimage(id, category) VALUES(6, LOAD_FILE('/var/lib/mysql-files/buero.webp'));
INSERT INTO categoryimage(id, category) VALUES(7, LOAD_FILE('/var/lib/mysql-files/computer.webp'));
INSERT INTO categoryimage(id, category) VALUES(8, LOAD_FILE('/var/lib/mysql-files/dvd.webp'));
INSERT INTO categoryimage(id, category) VALUES(9, LOAD_FILE('/var/lib/mysql-files/drogerie.webp'));
INSERT INTO categoryimage(id, category) VALUES(10, LOAD_FILE('/var/lib/mysql-files/foto.webp'));
INSERT INTO categoryimage(id, category) VALUES(11, LOAD_FILE('/var/lib/mysql-files/games.webp'));
INSERT INTO categoryimage(id, category) VALUES(12, LOAD_FILE('/var/lib/mysql-files/garten.jpg'));
INSERT INTO categoryimage(id, category) VALUES(13, LOAD_FILE('/var/lib/mysql-files/gutschein.webp'));
INSERT INTO categoryimage(id, category) VALUES(14, LOAD_FILE('/var/lib/mysql-files/haustier.webp'));
INSERT INTO categoryimage(id, category) VALUES(15, LOAD_FILE('/var/lib/mysql-files/foto.webp'));
INSERT INTO categoryimage(id, category) VALUES(16, LOAD_FILE('/var/lib/mysql-files/haushalt.webp'));
INSERT INTO categoryimage(id, category) VALUES(17, LOAD_FILE('/var/lib/mysql-files/cd.webp'));
INSERT INTO categoryimage(id, category) VALUES(18, LOAD_FILE('/var/lib/mysql-files/schmuck.webp'));
INSERT INTO categoryimage(id, category) VALUES(19, LOAD_FILE('/var/lib/mysql-files/schuhe.webp'));
INSERT INTO categoryimage(id, category) VALUES(20, LOAD_FILE('/var/lib/mysql-files/software.webp'));
INSERT INTO categoryimage(id, category) VALUES(21, LOAD_FILE('/var/lib/mysql-files/spielzeug.webp'));
INSERT INTO categoryimage(id, category) VALUES(22, LOAD_FILE('/var/lib/mysql-files/sport.webp'));
INSERT INTO categoryimage(id, category) VALUES(23, LOAD_FILE('/var/lib/mysql-files/uhr.webp'));

INSERT INTO categoryimage(id, category) VALUES(24, LOAD_FILE('/var/lib/mysql-files/list_2.jpg'));
INSERT INTO categoryimage(id, category) VALUES(25, LOAD_FILE('/var/lib/mysql-files/main.jpg'));
INSERT INTO categoryimage(id, category) VALUES(26, LOAD_FILE('/var/lib/mysql-files/present.jpg'));
INSERT INTO categoryimage(id, category) VALUES(27, LOAD_FILE('/var/lib/mysql-files/presentgrafik.png'));

INSERT INTO wishlist (name, enddate) VALUES ('Stefan','2020-01-30');
INSERT INTO wishlist (name, enddate) VALUES ('Christoph','2020-02-28');

INSERT INTO article (name, description, productlink, wishlistId)
VALUES (
  'Grafikkarte',
  'MSI GeForce GTX 1060 6GT OCV1 6GB Nvidia GDDR5 1x HDMI, 1x DP, 1x DL-DVI-D, 2 Slot Afterburner OC, VR Ready, 4K-optimiert, Grafikkarte',
  'https://www.amazon.de/MSI-GeForce-FROZR-GDDR6-Express/dp/B082NQDX32?ref_=Oct_s9_apbd_onr_hd_bw_bT6uX9_1&pf_rd_r=DDY2YGTQCD2999CKAMJT&pf_rd_p=3fe1f878-c90c-5ebf-a3da-c362d1132227&pf_rd_s=merchandised-search-10&pf_rd_t=BROWSE&pf_rd_i=430161031',
  1);

INSERT INTO article (name, description, productlink, wishlistId)
VALUES (
  'Laptop',
  'Jumper EZbook S5 14 "Zoll Windows 10 Heim Laptop, FHD IPS Laptop Intel Atom E3950 Quad Core, 8 GB RAM',
  'https://www.amazon.de/Jumper-EZbook-Windows-Unterst%C3%BCtzt-tf-Karte/dp/B07W91KDKX/ref=sr_1_2_sspa?__mk_de_DE=%C3%85M%C3%85%C5%BD%C3%95%C3%91&keywords=laptop&qid=1579169593&s=computers&sr=1-2-spons&psc=1&spLa=ZW5jcnlwdGVkUXVhbGlmaWVyPUEzODdaMlc3QUZLVzZSJmVuY3J5cHRlZElkPUEwNzA1NzQyMUlTWElZMTRQRllHNSZlbmNyeXB0ZWRBZElkPUEwNjcyOTg4Mk5SUzdNUlQyNFdMQSZ3aWRnZXROYW1lPXNwX2F0ZiZhY3Rpb249Y2xpY2tSZWRpcmVjdCZkb05vdExvZ0NsaWNrPXRydWU=',
  1);

INSERT INTO article (name, description, imagelink, productlink, wishlistId)
VALUES (
  'Play Station 4',
  'Eine ganz normale Play Station. Kein Bundle oder so.',
  'image/ps4.webp',
  'https://www.amazon.de/PlayStation%C2%AE4-Pro-Konsole-1TB-schwarz/dp/B07HSJW7HK/ref=sr_1_1_sspa?__mk_de_DE=%C3%85M%C3%85%C5%BD%C3%95%C3%91&keywords=playstation+4&qid=1579097298&sr=8-1-spons&psc=1&spLa=ZW5jcnlwdGVkUXVhbGlmaWVyPUEzRk1RUUJTSFlPWk5MJmVuY3J5cHRlZElkPUEwMjkzODU5MlpNQ01FQ1lONFgzMiZlbmNyeXB0ZWRBZElkPUEwOTIwODcyMzUwQjRIVjRVNk5WSyZ3aWRnZXROYW1lPXNwX2F0ZiZhY3Rpb249Y2xpY2tSZWRpcmVjdCZkb05vdExvZ0NsaWNrPXRydWU=',
  1);

INSERT INTO article (name, description, imagelink, productlink, wishlistId)
VALUES (
  'Grafikkarte',
  'MSI GeForce GTX 1060 6GT OCV1 6GB Nvidia GDDR5 1x HDMI, 1x DP, 1x DL-DVI-D, 2 Slot Afterburner OC, VR Ready, 4K-optimiert, Grafikkarte',
  'image/graka.webp',
  'https://www.amazon.de/MSI-GeForce-FROZR-GDDR6-Express/dp/B082NQDX32?ref_=Oct_s9_apbd_onr_hd_bw_bT6uX9_1&pf_rd_r=DDY2YGTQCD2999CKAMJT&pf_rd_p=3fe1f878-c90c-5ebf-a3da-c362d1132227&pf_rd_s=merchandised-search-10&pf_rd_t=BROWSE&pf_rd_i=430161031',
  2);

INSERT INTO article (name, description, imagelink, productlink, wishlistId)
VALUES (
  'Laptop',
  'Jumper EZbook S5 14 "Zoll Windows 10 Heim Laptop, FHD IPS Laptop Intel Atom E3950 Quad Core, 8 GB RAM',
  'image/lp.webp',
  'https://www.amazon.de/Jumper-EZbook-Windows-Unterst%C3%BCtzt-tf-Karte/dp/B07W91KDKX/ref=sr_1_2_sspa?__mk_de_DE=%C3%85M%C3%85%C5%BD%C3%95%C3%91&keywords=laptop&qid=1579169593&s=computers&sr=1-2-spons&psc=1&spLa=ZW5jcnlwdGVkUXVhbGlmaWVyPUEzODdaMlc3QUZLVzZSJmVuY3J5cHRlZElkPUEwNzA1NzQyMUlTWElZMTRQRllHNSZlbmNyeXB0ZWRBZElkPUEwNjcyOTg4Mk5SUzdNUlQyNFdMQSZ3aWRnZXROYW1lPXNwX2F0ZiZhY3Rpb249Y2xpY2tSZWRpcmVjdCZkb05vdExvZ0NsaWNrPXRydWU=',
  2);

INSERT INTO article (name, description, imagelink, productlink, wishlistId)
VALUES (
  'Play Station 4',
  'Eine ganz normale Play Station. Kein Bundle oder so.',
  'https://images-na.ssl-images-amazon.com/images/I/711AM5GuE2L._SX342_.webp',
  'https://www.amazon.de/PlayStation%C2%AE4-Pro-Konsole-1TB-schwarz/dp/B07HSJW7HK/ref=sr_1_1_sspa?__mk_de_DE=%C3%85M%C3%85%C5%BD%C3%95%C3%91&keywords=playstation+4&qid=1579097298&sr=8-1-spons&psc=1&spLa=ZW5jcnlwdGVkUXVhbGlmaWVyPUEzRk1RUUJTSFlPWk5MJmVuY3J5cHRlZElkPUEwMjkzODU5MlpNQ01FQ1lONFgzMiZlbmNyeXB0ZWRBZElkPUEwOTIwODcyMzUwQjRIVjRVNk5WSyZ3aWRnZXROYW1lPXNwX2F0ZiZhY3Rpb249Y2xpY2tSZWRpcmVjdCZkb05vdExvZ0NsaWNrPXRydWU=',
  2);
