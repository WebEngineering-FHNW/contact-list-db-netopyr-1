# Contact-List DB
Contact-List that reads its data from a DB.
 
Goals of this assignment:
* Become familiar with Spring Data JPA.
* Replace dummy implementation of persistence layer.
* Write integration tests for each layer of a layered architecture.

## Assigment 1
Replace dummy implementation of contact-list storage.
1. Familiarize yourself with the code, in particular with Contact and ContactService.
1. Introduce a database for storing contacts.
   * Introduce a Repository.
   * Add the required annotations to Contact.
   * Rewrite the ContactService to use the repository.

## Assignment 2
Write integration tests for all layers.
1. Write an integration test for each layer.
1. Each test should create a new contact and check its availability.

## Assignment 3
Extend persistence layer.
1. Extend the integration tests to also check the job and company.
1. Make the required changes to show the job and company of the selected contact.
1. Now add a check for the phone numbers in the integration tests.
1. Fix the code to include the phone numbers on the screen.

## Assignment 4
Add some simple search capability to the contact-list
1. Allow the user to search for a contact by name.
   * If there is more than one hit, it is sufficient to show just one of them.
1. Do not search through the data yourself, but use the database capabilities.
