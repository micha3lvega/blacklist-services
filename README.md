[![es](https://img.shields.io/badge/lang-es-yellow.svg)](https://github.com/micha3lvega/blacklist-services/README.es.md)

## BlackList Services
Project to validate a string by calculating the edit distance between the strings using the Levenshtein algorithm.

### Description
The project is a microservice developed in Spring that validates if a key is in a blacklist stored in an H2 database. Additionally, it ensures that keys with minor variations from those already existing in the database cannot be entered. For example, if the database contains the string **password**, sending strings like **passw0rd**, **P@ssword** to the service will return that the string is *invalid* due to their similarity. The strings in the blacklist are contained in a database script named *data.sql*. The *Levenshtein* algorithm is employed to perform this comparison.

### Levenshtein Algorithm
The Levenshtein algorithm is a technique that calculates the "edit distance" between two text strings. In the context of this project, it is used to measure the similarity between the new key entered and the prohibited keys already present in the database. The Levenshtein distance counts the minimum number of operations required to transform one string into another, considering character insertions, deletions, and substitutions.

The **data.sql** file contains over 200 words that are considered the most insecure and commonly used by users when creating a password, according to **NordPass**. You can find more details at https://nordpass.com/es/most-common-passwords-list.

The logic implemented in the **isValidChain** method uses this algorithm to assess if the new key shares similarities with the prohibited key, ensuring consistency in the database and preventing minimal variations in the string.

### Requirements  
To run this project, you will need:

- Java
- Maven
- Git (optional, for cloning the repository)

### Installation
Follow these steps to run the project locally:

1. Clone this repository: `git clone https://github.com/micha3lvega/blacklist-services` (or you can also download it as a ZIP file).
2. Navigate to the project directory: `cd blacklist-services`
3. Run the command: `mvn clean install`
4. Launch the application: `mvn spring-boot:run`
  
### Usage
You can validate a password by sending a *POST* request to the **validate** endpoint using curl in the following manner:

```bash
curl -X 'POST' \
  'http://localhost:8080/api/v1/blacklist/validate' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '"love123"'
```

This curl command sends a POST request to the local server on port 8080, targeting the endpoint: /api/v1/blacklist/validate/, with the password "passw0rd" included in the URL. Make sure to replace http://localhost:8080 with your server's address if it differs.

## Testing
Here's an example of the words that the service might receive, which word is in the database, and the expected response from the service:

- Word Received: "p@ssw0rd"
- Word in the Database: "password"
- Service Response: Invalid

This demonstrates that "p@ssw0rd" is similar to "password" and therefore, according to the service's criteria, it would be considered invalid.

Apologies for the confusion earlier. It seems you're looking for a table format to display the comparison between the word in the database, the received word, and the resulting validation response. Here's the table:

| Database Word | Received Word | Result |
|-------------- |---------------|--------|
| "love123"     | "love124"     | Invalid|
| "love123"     | "l0ve123"     | Invalid|
| "maria"       | "mari4"       | Invalid|
| "maria"       | "m4ri4"       | Invalid|
| "maria"       | "m4r14"       | Invalid|

This table demonstrates different scenarios where the service checks the similarity between the words in the database and the received words, and based on that, determines if the word is valid or invalid.

|Palabra BD|Palabra recibida|Resultado
|--|--|--|
|love123| love124 |false
|medellin| m3d3llin |false
|medellin| m3d3ll1n |true

### Contribution
Everyone is welcome to contribute to the project! If you wish to contribute, simply fork the repository, make your changes, and send a pull request. Please ensure that your contributions follow the contribution guidelines.

### License
This project is under the Creative Commons License.
Copyright (c) 2023 Michael Vega Carrillo
Creative Commons License - Non-Commercial (CC BY-NC)
This license allows others to remix, modify, and build upon the original work for non-commercial purposes. If possible, acknowledging the author in some way would be appreciated.

By using the software under this license, you are allowed to:

- Share: copy and redistribute the material in any medium or format.
- Adapt: remix, transform, and build upon the material.

### Restrictions:

Commercial Use: The material cannot be used for commercial purposes without prior authorization.
This is just a summary of (and not a substitute for) the license. To view the full version of the legal agreement, please refer to the LICENSE file in the repository.

### Contact
@Micha3lVega