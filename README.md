# PetStoreTestFramework

Available tests could be executed by running PetStoreApiTests.class

Given the limited time available for the task, I focused primarily on implementing positive test cases for the GET, POST, PUT, and DELETE methods. However, there are several areas within the testing framework which could be improved:
1. Validation Enhancements:
   Current State: Validation is basic, focusing on a limited set of response attributes.
   Suggested Improvement: Expand the validation logic to cover all relevant fields of the response. This could involve creating a dedicated method for thorough response validation, ensuring that both the structure and the content of the response meet expected criteria.
2. Error Handling Improvements:
   Current State: The framework currently lacks comprehensive error handling, particularly for non-200 HTTP status codes.
   Suggested Improvement: Implement more detailed error handling strategies, such as checking for non-200 status codes explicitly and throwing or logging an appropriate exception. Additionally, failing tests should provide meaningful messages to facilitate quicker debugging and issue resolution.
3. Model Classes for Payloads:
   Current State: The framework directly uses JSON strings or manually crafted objects for request payloads.
   Suggested Improvement: Introduce model classes for entities such as Pet and User. Utilize these model classes to generate request payloads. For example, use PetStoreOrder.class to create and manipulate PetStoreOrder objects, which can then be serialized into JSON payloads. 
4. Reporting and Logging:
   Current State: Reporting and logging capabilities are minimal, limiting the visibility into test execution details.
   Suggested Improvement: Integrate Allure Reports to provide an interactive report of test executions. Additionally, increase the granularity and coverage of log statements throughout the test execution flow.

## Possible Test cases for https://petstore.swagger.io

### Pets Endpoint
### Add a New Pet
1. Test adding a pet with valid details (all required fields filled).
2. Test adding a pet with missing required fields.
3. Test adding a pet with invalid data types for fields.

### Find Pet by ID
1. Test retrieving a pet using a valid pet ID.
2. Test retrieving a pet using a non-existent pet ID.
3. Test retrieving a pet using an invalid ID format.

### Update an Existing Pet
1. Test updating an existing pet's name and status with valid values.
2. Test updating a pet that does not exist.
3. Test updating a pet with invalid field values.

### Find Pets by Status
1. Test retrieving pets with each of the available statuses (available, pending, sold).
2. Test retrieving pets with multiple statuses in a single request.
3. Test retrieving pets with an invalid status.

### Delete a Pet
1. Test deleting a pet with a valid ID.
2. Test deleting a pet with a non-existent ID.
3. Test deleting a pet without proper authorization (if applicable).

### Store Endpoint
### Place an Order for a Pet
1. Test placing an order with valid order details.
2. Test placing an order with a non-existent pet ID.
3. Test placing an order with invalid quantities or dates.

### Find Purchase Order by ID
1. Test retrieving an order using a valid order ID.
2. Test retrieving an order using a non-existent order ID.
3. Test retrieving a pet using an invalid ID format.

### Delete Purchase Order
1. Test deleting an order with a valid order ID.
2. Test deleting an order with a non-existent order ID.

### User Endpoint
### Create User
1. Test creating a user with valid details.
2. Test creating a user with missing required fields.
3. Test creating a user with existing username
### Get User by Username
1. Test retrieving user details with a valid username.
2. Test retrieving user details with a non-existent username.
### Update User
1. Test updating user details with valid values.
2. Test updating a non-existent user.
### Delete User
1. Test deleting a user with a valid username.
2. Test deleting a non-existent user.
### User Login and Logout
1. Test logging in with valid credentials.
2. Test logging in with invalid credentials