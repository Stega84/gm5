DROP DATABASE gm5;
CREATE DATABASE gm5;
USE gm5;

CREATE TABLE Wunschliste(
  id  int NOT NULL AUTO_INCREMENT,
  name VARCHAR(80) NOT NULL,
  enddatum DATE NOT NULL,
  hashID VARCHAR(80),
  gespeichert BOOLEAN NOT NULL DEFAULT 0,
  PRIMARY KEY (id)
);

CREATE TABLE Artikel(
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(80) NOT NULL,
  beschreibung TEXT NOT NULL,
  datum DATETIME NOT NULL DEFAULT NOW(),
  bildlink VARCHAR(80) NOT NULL DEFAULT 'image/default.jpg',
  produktlink TEXT,
  preis VARCHAR (45),
  wunschliste_id INT NOT NULL,
  PRIMARY KEY(id),
  FOREIGN KEY (wunschliste_id) REFERENCES Wunschliste(id) ON DELETE CASCADE
);

CREATE TABLE Reservierung(
  id INT NOT NULL,
  name VARCHAR(80) NOT NULL DEFAULT 'nicht reserviert',
  reserviert BOOLEAN NOT NULL DEFAULT 0,
  PRIMARY KEY(id),
  FOREIGN KEY(id) REFERENCES Artikel(id) ON DELETE CASCADE
);

CREATE TRIGGER trg_Artikel
AFTER INSERT ON Artikel FOR EACH ROW
INSERT INTO Reservierung (id) VALUES (new.id);

CREATE VIEW Normal AS
SELECT *
FROM Artikel
WHERE wunschliste_id = 1;

INSERT INTO Wunschliste (name, enddatum, hashID) VALUES ('Stefan','2020-01-30',NULL);
INSERT INTO Wunschliste (name, enddatum, hashID) VALUES ('Christoph','2020-02-28',NULL);

INSERT INTO Artikel (name, beschreibung, produktlink, preis,wunschliste_id)
VALUES (
  'Grafikkarte',
  'MSI GeForce GTX 1060 6GT OCV1 6GB Nvidia GDDR5 1x HDMI, 1x DP, 1x DL-DVI-D, 2 Slot Afterburner OC, VR Ready, 4K-optimiert, Grafikkarte',
  'https://www.amazon.de/MSI-GeForce-FROZR-GDDR6-Express/dp/B082NQDX32?ref_=Oct_s9_apbd_onr_hd_bw_bT6uX9_1&pf_rd_r=DDY2YGTQCD2999CKAMJT&pf_rd_p=3fe1f878-c90c-5ebf-a3da-c362d1132227&pf_rd_s=merchandised-search-10&pf_rd_t=BROWSE&pf_rd_i=430161031',
  '350',
  1);

INSERT INTO Artikel (name, beschreibung, produktlink, preis,wunschliste_id)
VALUES (
  'Laptop',
  'Jumper EZbook S5 14 "Zoll Windows 10 Heim Laptop, FHD IPS Laptop Intel Atom E3950 Quad Core, 8 GB RAM',
  'https://www.amazon.de/Jumper-EZbook-Windows-Unterst%C3%BCtzt-tf-Karte/dp/B07W91KDKX/ref=sr_1_2_sspa?__mk_de_DE=%C3%85M%C3%85%C5%BD%C3%95%C3%91&keywords=laptop&qid=1579169593&s=computers&sr=1-2-spons&psc=1&spLa=ZW5jcnlwdGVkUXVhbGlmaWVyPUEzODdaMlc3QUZLVzZSJmVuY3J5cHRlZElkPUEwNzA1NzQyMUlTWElZMTRQRllHNSZlbmNyeXB0ZWRBZElkPUEwNjcyOTg4Mk5SUzdNUlQyNFdMQSZ3aWRnZXROYW1lPXNwX2F0ZiZhY3Rpb249Y2xpY2tSZWRpcmVjdCZkb05vdExvZ0NsaWNrPXRydWU=',
  '400',
  1);

INSERT INTO Artikel (name, beschreibung, bildlink, produktlink, preis,wunschliste_id)
VALUES (
  'Play Station 4',
  'Eine ganz normale Play Station. Kein Bundle oder so.',
  'image/ps4.jpg',
  'https://www.amazon.de/PlayStation%C2%AE4-Pro-Konsole-1TB-schwarz/dp/B07HSJW7HK/ref=sr_1_1_sspa?__mk_de_DE=%C3%85M%C3%85%C5%BD%C3%95%C3%91&keywords=playstation+4&qid=1579097298&sr=8-1-spons&psc=1&spLa=ZW5jcnlwdGVkUXVhbGlmaWVyPUEzRk1RUUJTSFlPWk5MJmVuY3J5cHRlZElkPUEwMjkzODU5MlpNQ01FQ1lONFgzMiZlbmNyeXB0ZWRBZElkPUEwOTIwODcyMzUwQjRIVjRVNk5WSyZ3aWRnZXROYW1lPXNwX2F0ZiZhY3Rpb249Y2xpY2tSZWRpcmVjdCZkb05vdExvZ0NsaWNrPXRydWU=',
  '350',
  1);

INSERT INTO Artikel (name, beschreibung, bildlink, produktlink, preis,wunschliste_id)
VALUES (
  'Grafikkarte',
  'MSI GeForce GTX 1060 6GT OCV1 6GB Nvidia GDDR5 1x HDMI, 1x DP, 1x DL-DVI-D, 2 Slot Afterburner OC, VR Ready, 4K-optimiert, Grafikkarte',
  'image/graka.jpg',
  'https://www.amazon.de/MSI-GeForce-FROZR-GDDR6-Express/dp/B082NQDX32?ref_=Oct_s9_apbd_onr_hd_bw_bT6uX9_1&pf_rd_r=DDY2YGTQCD2999CKAMJT&pf_rd_p=3fe1f878-c90c-5ebf-a3da-c362d1132227&pf_rd_s=merchandised-search-10&pf_rd_t=BROWSE&pf_rd_i=430161031',
  '350',
  2);

INSERT INTO Artikel (name, beschreibung, bildlink, produktlink, preis,wunschliste_id)
VALUES (
  'Laptop',
  'Jumper EZbook S5 14 "Zoll Windows 10 Heim Laptop, FHD IPS Laptop Intel Atom E3950 Quad Core, 8 GB RAM',
  'image/lp.jpg',
  'https://www.amazon.de/Jumper-EZbook-Windows-Unterst%C3%BCtzt-tf-Karte/dp/B07W91KDKX/ref=sr_1_2_sspa?__mk_de_DE=%C3%85M%C3%85%C5%BD%C3%95%C3%91&keywords=laptop&qid=1579169593&s=computers&sr=1-2-spons&psc=1&spLa=ZW5jcnlwdGVkUXVhbGlmaWVyPUEzODdaMlc3QUZLVzZSJmVuY3J5cHRlZElkPUEwNzA1NzQyMUlTWElZMTRQRllHNSZlbmNyeXB0ZWRBZElkPUEwNjcyOTg4Mk5SUzdNUlQyNFdMQSZ3aWRnZXROYW1lPXNwX2F0ZiZhY3Rpb249Y2xpY2tSZWRpcmVjdCZkb05vdExvZ0NsaWNrPXRydWU=',
  '400',
  2);

INSERT INTO Artikel (name, beschreibung, bildlink, produktlink, preis,wunschliste_id)
VALUES (
  'Play Station 4',
  'Eine ganz normale Play Station. Kein Bundle oder so.',
  'image/ps4.jpg',
  'https://www.amazon.de/PlayStation%C2%AE4-Pro-Konsole-1TB-schwarz/dp/B07HSJW7HK/ref=sr_1_1_sspa?__mk_de_DE=%C3%85M%C3%85%C5%BD%C3%95%C3%91&keywords=playstation+4&qid=1579097298&sr=8-1-spons&psc=1&spLa=ZW5jcnlwdGVkUXVhbGlmaWVyPUEzRk1RUUJTSFlPWk5MJmVuY3J5cHRlZElkPUEwMjkzODU5MlpNQ01FQ1lONFgzMiZlbmNyeXB0ZWRBZElkPUEwOTIwODcyMzUwQjRIVjRVNk5WSyZ3aWRnZXROYW1lPXNwX2F0ZiZhY3Rpb249Y2xpY2tSZWRpcmVjdCZkb05vdExvZ0NsaWNrPXRydWU=',
  '350',
  2);
