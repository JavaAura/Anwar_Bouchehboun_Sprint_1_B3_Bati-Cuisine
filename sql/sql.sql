
CREATE TYPE EtatProjet AS ENUM ('EN_COURS', 'TERMINE', 'ANNULE');
-- Create the client table

CREATE TABLE Client (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(255) NOT NULL UNIQUE,
    adresse VARCHAR(255) NOT NULL ,
    telephone VARCHAR(20) NOT NULL ,
    estProfessionnel BOOLEAN NOT NULL
);
-- Create the Projet table
CREATE TABLE Projet (
    id SERIAL PRIMARY KEY,
    nomProjet VARCHAR(255) NOT NULL,
    margeBeneficiaire DOUBLE ,
    coutTotal DOUBLE   ,
    etatProjet EtatProjet NOT NULL,
    client_id INTEGER NOT NULL,
    FOREIGN KEY (client_id) REFERENCES Client(id) ON DELETE CASCADE
);
-- Create the Devis table
CREATE TABLE Devis (
    id SERIAL PRIMARY KEY,
    montantEstime DOUBLE PRECISION NOT NULL,
    dateEmission DATE NOT NULL,
    dateValidite DATE NOT NULL,
    accepte BOOLEAN NULL,
    projet_id INTEGER NOT NULL,
    FOREIGN KEY (projet_id) REFERENCES Projet(id) ON DELETE CASCADE
);
-- Create the  Composant table

CREATE TABLE Composant (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(255) NOT NULL,
    typeComposant VARCHAR(50) NOT NULL,
    tauxTVA DOUBLE PRECISION NOT NULL,
    projet_id INTEGER NOT NULL,
    FOREIGN KEY (projet_id) REFERENCES Projet(id) ON DELETE CASCADE
);
-- Create MainOeuvre table inheriting from Composant
CREATE TABLE MainOeuvre (
    tauxHoraire DOUBLE PRECISION NOT NULL,
    heuresTravail DOUBLE PRECISION NOT NULL,
    productiviteOuvrier DOUBLE PRECISION NOT NULL
) INHERITS (Composant);
-- Create Materiel table inheriting from Composant
CREATE TABLE Materiel (
    coutUnitaire DOUBLE PRECISION NOT NULL,
    quantite DOUBLE PRECISION NOT NULL,
    coutTransport DOUBLE PRECISION NOT NULL,
    coefficientQualite DOUBLE PRECISION NOT NULL
) INHERITS (Composant);
