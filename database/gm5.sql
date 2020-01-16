DROP DATABASE gm5;
CREATE DATABASE gm5;
USE gm5;

CREATE TABLE Wunschliste(
  id  int NOT NULL AUTO_INCREMENT,
  Name VARCHAR(80) NOT NULL,
  Enddatum DATE NOT NULL,
  hashID VARCHAR(80),
  PRIMARY KEY (id)
);

CREATE TABLE Artikel(
  id INT NOT NULL AUTO_INCREMENT,
  Name VARCHAR(80) NOT NULL,
  Beschreibung TEXT NOT NULL,
  Datum DATETIME NOT NULL DEFAULT NOW(),
  Bildlink VARCHAR(80),
  Produktlink TEXT,
  Preis VARCHAR (45),
  wunschliste_id INT NOT NULL,
  PRIMARY KEY(id),
  FOREIGN KEY (wunschliste_id) REFERENCES Wunschliste(id) ON DELETE CASCADE
);

CREATE TABLE Reservierung(
  artikel_id INT NOT NULL,
  Name VARCHAR(80) NOT NULL DEFAULT 'nicht reserviert',
  Reserviert BOOLEAN NOT NULL DEFAULT 0,
  PRIMARY KEY(artikel_id),
  FOREIGN KEY(artikel_id) REFERENCES Artikel(id) ON DELETE CASCADE
);

CREATE TRIGGER trg_Artikel
AFTER INSERT ON Artikel FOR EACH ROW
  INSERT INTO Reservierung (artikel_id) VALUES (new.id);


INSERT INTO Wunschliste (Name, Enddatum, hashID) VALUES ('Stefan','2020-01-30',NULL);
INSERT INTO Wunschliste (Name, Enddatum, hashID) VALUES ('Christoph','2020-02-28',NULL);

INSERT INTO Artikel (Name, Beschreibung, Bildlink, Produktlink, Preis,wunschliste_id)
VALUES (
  'Grafikkarte',
  'MSI GeForce GTX 1060 6GT OCV1 6GB Nvidia GDDR5 1x HDMI, 1x DP, 1x DL-DVI-D, 2 Slot Afterburner OC, VR Ready, 4K-optimiert, Grafikkarte',
  NULL,
  'https://www.amazon.de/MSI-GeForce-FROZR-GDDR6-Express/dp/B082NQDX32?ref_=Oct_s9_apbd_onr_hd_bw_bT6uX9_1&pf_rd_r=DDY2YGTQCD2999CKAMJT&pf_rd_p=3fe1f878-c90c-5ebf-a3da-c362d1132227&pf_rd_s=merchandised-search-10&pf_rd_t=BROWSE&pf_rd_i=430161031',
  '350',
  1);

INSERT INTO Artikel (Name, Beschreibung, Bildlink, Produktlink, Preis,wunschliste_id)
VALUES (
  'Laptop',
  'Jumper EZbook S5 14 "Zoll Windows 10 Heim Laptop, FHD IPS Laptop Intel Atom E3950 Quad Core, 8 GB RAM',
  NULL,
  'https://www.amazon.de/Jumper-EZbook-Windows-Unterst%C3%BCtzt-tf-Karte/dp/B07W91KDKX/ref=sr_1_2_sspa?__mk_de_DE=%C3%85M%C3%85%C5%BD%C3%95%C3%91&keywords=laptop&qid=1579169593&s=computers&sr=1-2-spons&psc=1&spLa=ZW5jcnlwdGVkUXVhbGlmaWVyPUEzODdaMlc3QUZLVzZSJmVuY3J5cHRlZElkPUEwNzA1NzQyMUlTWElZMTRQRllHNSZlbmNyeXB0ZWRBZElkPUEwNjcyOTg4Mk5SUzdNUlQyNFdMQSZ3aWRnZXROYW1lPXNwX2F0ZiZhY3Rpb249Y2xpY2tSZWRpcmVjdCZkb05vdExvZ0NsaWNrPXRydWU=',
  '400',
  1);

INSERT INTO Artikel (Name, Beschreibung, Bildlink, Produktlink, Preis,wunschliste_id)
VALUES (
  'Play Station 4',
  'Eine ganz normale Play Station. Kein Bundle oder so.',
  NULL,
  'https://www.amazon.de/PlayStation%C2%AE4-Pro-Konsole-1TB-schwarz/dp/B07HSJW7HK/ref=sr_1_1_sspa?__mk_de_DE=%C3%85M%C3%85%C5%BD%C3%95%C3%91&keywords=playstation+4&qid=1579097298&sr=8-1-spons&psc=1&spLa=ZW5jcnlwdGVkUXVhbGlmaWVyPUEzRk1RUUJTSFlPWk5MJmVuY3J5cHRlZElkPUEwMjkzODU5MlpNQ01FQ1lONFgzMiZlbmNyeXB0ZWRBZElkPUEwOTIwODcyMzUwQjRIVjRVNk5WSyZ3aWRnZXROYW1lPXNwX2F0ZiZhY3Rpb249Y2xpY2tSZWRpcmVjdCZkb05vdExvZ0NsaWNrPXRydWU=',
  '350',
  1);

INSERT INTO Artikel (Name, Beschreibung, Bildlink, Produktlink, Preis,wunschliste_id)
VALUES (
  'Grafikkarte',
  'MSI GeForce GTX 1060 6GT OCV1 6GB Nvidia GDDR5 1x HDMI, 1x DP, 1x DL-DVI-D, 2 Slot Afterburner OC, VR Ready, 4K-optimiert, Grafikkarte',
  NULL,
  'https://www.amazon.de/MSI-GeForce-FROZR-GDDR6-Express/dp/B082NQDX32?ref_=Oct_s9_apbd_onr_hd_bw_bT6uX9_1&pf_rd_r=DDY2YGTQCD2999CKAMJT&pf_rd_p=3fe1f878-c90c-5ebf-a3da-c362d1132227&pf_rd_s=merchandised-search-10&pf_rd_t=BROWSE&pf_rd_i=430161031',
  '350',
  2);

INSERT INTO Artikel (Name, Beschreibung, Bildlink, Produktlink, Preis,wunschliste_id)
VALUES (
  'Laptop',
  'Jumper EZbook S5 14 "Zoll Windows 10 Heim Laptop, FHD IPS Laptop Intel Atom E3950 Quad Core, 8 GB RAM',
  NULL,
  'https://www.amazon.de/Jumper-EZbook-Windows-Unterst%C3%BCtzt-tf-Karte/dp/B07W91KDKX/ref=sr_1_2_sspa?__mk_de_DE=%C3%85M%C3%85%C5%BD%C3%95%C3%91&keywords=laptop&qid=1579169593&s=computers&sr=1-2-spons&psc=1&spLa=ZW5jcnlwdGVkUXVhbGlmaWVyPUEzODdaMlc3QUZLVzZSJmVuY3J5cHRlZElkPUEwNzA1NzQyMUlTWElZMTRQRllHNSZlbmNyeXB0ZWRBZElkPUEwNjcyOTg4Mk5SUzdNUlQyNFdMQSZ3aWRnZXROYW1lPXNwX2F0ZiZhY3Rpb249Y2xpY2tSZWRpcmVjdCZkb05vdExvZ0NsaWNrPXRydWU=',
  '400',
  2);

INSERT INTO Artikel (Name, Beschreibung, Bildlink, Produktlink, Preis,wunschliste_id)
VALUES (
  'Play Station 4',
  'Eine ganz normale Play Station. Kein Bundle oder so.',
  NULL,
  'https://www.amazon.de/PlayStation%C2%AE4-Pro-Konsole-1TB-schwarz/dp/B07HSJW7HK/ref=sr_1_1_sspa?__mk_de_DE=%C3%85M%C3%85%C5%BD%C3%95%C3%91&keywords=playstation+4&qid=1579097298&sr=8-1-spons&psc=1&spLa=ZW5jcnlwdGVkUXVhbGlmaWVyPUEzRk1RUUJTSFlPWk5MJmVuY3J5cHRlZElkPUEwMjkzODU5MlpNQ01FQ1lONFgzMiZlbmNyeXB0ZWRBZElkPUEwOTIwODcyMzUwQjRIVjRVNk5WSyZ3aWRnZXROYW1lPXNwX2F0ZiZhY3Rpb249Y2xpY2tSZWRpcmVjdCZkb05vdExvZ0NsaWNrPXRydWU=',
  '350',
  2);
