### Bati-Cuisine

## Description du projet

BatiCuisine est une application Java destinée aux professionnels de la construction et de la rénovation de cuisines. L'application calcule le coût total des travaux en tenant compte des matériaux utilisés et du coût de la main-d'œuvre, cette dernière étant facturée à l'heure.

​

## Fonctionnalités

- Gestion des Projets

Ajouter un client associé au projet.
Gérer les composants (matériaux et main-d'œuvre).
Associer un devis au projet pour estimer les coûts avant le début des travaux.
Suivre l'état du projet (En cours, Terminé, Annulé).

- Gestion des Composants

Gérer les coûts des matériaux et de la main-d'œuvre.
Calculer les coûts basés sur le taux horaire, les heures travaillées, et la productivité des ouvriers.

- Gestion des Clients

Enregistrer les informations de base d’un client.
Différencier les clients professionnels et particuliers pour l'application de remises.
Calculer et appliquer des remises spécifiques selon le type de client.

- Création de Devis

Générer des devis incluant une estimation des coûts des matériaux et de la main-d'œuvre.
Inclure des dates d’émission et de validité pour chaque devis.
Indiquer si le devis a été accepté par le client.

- Calcul des Coûts

Intégrer les coûts des composants dans le calcul du coût total du projet.
Appliquer une marge bénéficiaire et gérer les ajustements basés sur la qualité des matériaux ou la productivité.

- Affichage des Détails et Résultats

Afficher les détails complets du projet, y compris les informations client et les composants utilisés.
Générer un récapitulatif détaillé des coûts totaux.

## Technologies Utilisées

- **Language:** Java 8
- **Database:** PostgreSQL
- **Dependency Management:** Maven
- **Framework:** JDBC for database interactions
- **Project Management:** Git, JIRA
- **Design pattern:** Singleton, Repository Pattern ..
- **Application organisée en couches:** Service,...
- **UML Modeling:** StarUML

## Instructions d'Installation :

1. Cloner le dépôt :

```bash
git `clone https://github.com/anwar-bouchehboun/S1_Brief-3.git`
```

2. Accéder au répertoire du projet :
   `cd bati-cuisine`

### Conditions préalables

- Java 8 or later
- PostgreSQL installed and configured
- Maven for dependency management

## Configurer la base de données :

- Install PostgreSQL.
- Create a database named `BatiCuisine`.

`CREATE DATABASE BatiCuisine;` 

- Import the SQL structure file located at `./sql/sql.sql`. 
- Configure database connection settings in `src/main/java/org/Bebilo/utilitaire/DbConnection.java`.

### Pour exécuter l'application, exécutez la commande suivante :

`Bati-Cuisine2\out\artifacts\Bati_Cuisine2_jar`
`java -jar Bati-Cuisine2.jar`

### Planification du Projet

- Pour suivre l'avancement et les tâches liées à ce projet, veuillez consulter notre planification JIRA :
 `https://anouarab95.atlassian.net/jira/software/projects/BATI/boards/6/backlog?epics=visible`


### Conception UML(Diagramme ) :
<img width="486" alt="class" src="https://github.com/user-attachments/assets/8b523568-8a5c-499c-97ec-caa2472a2109">


### Contact
Pour toute question ou suggestion, veuillez contacter :
- **Nom :** Anwar Bouchehboun
- **Email :** anuoar.ab95@gmail.com
