**How to set up the project**
<br><br>
We will need to create an user called "prueba" with the password "12345" on our MySQL instance and grant it permissions to read, create, update and delete registries using the commands found below, alternatively we can modify the "application.properties" file to configure our own username and password combo:
<br><br><br>
*CREATE USER 'prueba'@'localhost' IDENTIFIED BY '12345';*
<br><br>
*GRANT CREATE, ALTER, DROP, INSERT, UPDATE, DELETE, SELECT, REFERENCES, RELOAD on \*.\* TO 'prueba'@'localhost' WITH GRANT OPTION;*
<br><br><br>
As this is a microservice application we will have multiple databases, one for each microservice, therefore is necessary to create them using the following commands:
<br><br><br>
*CREATE DATABASE productService;*
<br>
*CREATE DATABASE orderService;*
<br>
*CREATE DATABASE paymentService;*
<br>
*CREATE DATABASE userService;*
<br><br><br>
It's not necessary to create the databases tables manually, as the application will do it automatically, however it's required that we insert some values in certain tables of specific databases, to begin we should insert the following values in the "status" table of the "orderService" database
<br><br><br>
INSERT INTO status(name) VALUES ("pending");
<br>
INSERT INTO status(name) VALUES ("paid");
<br>
INSERT INTO status(name) VALUES ("shipped");
<br>
INSERT INTO status(name) VALUES ("delivered");
<br><br><br>
Then we should insert the following values in the "roles" table of the "userService" database:
<br><br><br>
*INSERT INTO roles(name) VALUES('ROLE_USER');*
<br>
*INSERT INTO roles(name) VALUES('ROLE_MODERATOR');*
<br>
*INSERT INTO roles(name) VALUES('ROLE_ADMIN');*
<br><br><br>
**How to run the application**
<br><br>
The applications consists of four microservices and one service registry, its important to first run the service registry application, as it would help the microservices found each other, this will run on port 8761.
<br><br><br>
Next we should run the product microservice, as its the only one that doesn't depend on any other microservice for its functionality, it will run on port 8083 and we can use it to get all products from its database, insert, update and delete them.
<br><br><br>
Then we should run the order microservice as it will only depend on the product microservice for its functionality, it will run on port 8082 and we can use it to generate new orders which will include existing products.
<br><br><br>
Next we should run the payment microservice as it will depend on the order microservice for its functionality, it will run on port 8081 and we can use it to process a new payment which will change the order status.
<br><br><br>
Finally we should run the user microservice as it will depend on the order and payment microservices for its functionality, it will run on port 8080 and we can use it to register new user, login with existing users, create new orders and payments.
<br><br><br>
The application uses JWT tokens that will be returned in a cookie when a login request is successful, reducing the need to consistently authenticate ourselves for future requests, at least until the token expires.
<br><br><br>
**API endpoints and sample requests for testing**
<br><br>
The application has multiple endpoints for each microservice:
<br><br><br>
**Product microservice**
<br><br><br>
*GET /products*
<br><br><br>
This will return a list with all the products registered in the "products" table of the "productService" database
<br><br><br>
*POST /products*
<br><br><br>
We can send a request body with the properties of an product object to create and insert it in the "products" table of the "productService" database as presented below:
<br><br><br>
*{*
*"name": "soap",*
*"description": "bathing soap",*
*"stock": 5,*
*"price": 20*
*}*
<br><br><br>
*PUT /products/{id}*
<br><br><br>
We can replace {id} with the number of the id of an specific product in the "products" table of the "productService" database and send a request body with the properties of a product object to update that specific product details, as presented below:
<br><br><br>
*{*
*"name": "soap",*
*"description": "bathing soap",*
*"stock": 0,*
*"price": 20*
*}*
<br><br><br>
*DELETE /products/{id}*
<br><br><br>
We can replace {id} with the number of the id of an specific product in the "products" table of the "productService" database, it will delete its registry
<br><br><br>
*POST /orderProducts*
<br><br><br>
We can send an array of numbers that represent the ids of different products registered in the "products" table of the "productService" database as presented below and it will return us the objects that represent those products
<br><br><br>
*[1, 3, 5]*
<br><br><br>
This endpoint was created to be used by the "order" microservice, as it will have the ids of the products that conforms a specific order but not their details
<br><br><br>
**Order microservice**
<br><br><br>
*GET /orders*
<br><br><br>
This will return a list with all the orders registered in the "orders" table of the "orderService" database
<br><br><br>
*POST /orders*
<br><br><br>
We can send a request body with the properties of an order object to create and insert it in the "orders" table of the "orderService" database as presented below:
<br><br><br>
*{*
*"status": {*
*"id": 1*
*},*
*"date": "2025-05-06",*
*"productsId": [1, 4, 5]*
*}*
<br><br><br>
As mentioned before, it any of the products in the "products" array has an stock of 0, the order will not be created as it wouldn't be fullfiled.
<br><br><br>
This method will also be used by the "user" microservice to provide an user with the ability to place new orders
<br><br><br>
*PUT /updateOrderStatus*
<br><br><br>
We can send a request body with the properties of a "PaymentDTO" object to update that specific order status, as presented below:
<br><br><br>
*{*
*"orderId": 2,*
*"statusId": 2*
*}*
<br><br><br>
This endpoint was created to be used by the "payment" microservice, as it will process the payments and update the order status accordingly
<br><br><br>
**Payment microservice**
<br><br><br>
*POST /payments*
<br><br><br>
We can send a request body with the properties of an payment object to create and insert it in the "payments" table of the "paymentService" database as presented below:
<br><br><br>
*{*
*"orderId": 1*
*}*
<br><br><br>
This will also automatically create a "PaymentDTO" object and send it to the order microservice to update the selected order status to "paid"
<br><br><br>
This method will also be used by the "user" microservice to provide an user with the ability to make new payments
<br><br><br>
**User microservice**
<br><br>
This microservice will have three authentication endpoints
<br><br><br>
*POST /api/auth/signup*
<br><br><br>
We can use it to register new users by sending a request body with the properties of an uesr object, as presented below:
<br><br><br>
{
"username": "BartSimpson25",
"email": "futurama@gmail.com",
"password": "aycaramba",
"role": ["user"]
}
<br><br><br>
We can't have two users with the same username or email, note that we are sending a roles array therefore an user can have multiple roles
<br><br><br>
*POST /api/auth/signin*
<br><br><br>
We can use it to login with a registered user by sending a request body its credentials, as presented below:
<br><br><br>
*{*
*"username": "BartSimpson25",*
*"password": "aycaramba"*
*}*
<br><br><br>
This will return us a cookie with a JWT token to authenticate ourselves in further requests
<br><br><br>
*POST /api/auth/signout*
<br><br><br>
We can use it to logout an already authenticated user, this will invalidate the cookie with its JWT token as it will expire automatically
<br><br><br>
The user microservice also has two functional endpoints:
<br><br><br>
*POST /newOrder*
<br><br><br>
We can send a request body with the properties of an order object to create and insert it in the "orders" table of the "orderService" database as presented below (Authentication with an "user" or "admin" role required):
<br><br><br>
*{*
*"status": {*
*"id": 1*
*},*
*"date": "2025-05-06",*
*"productsId": [1, 4, 5]*
*}*
<br><br><br>
*POST /newPayment*
<br><br><br>
We can send a request body with the properties of an payment object to create and insert it in the "payments" table of the "paymentService" database as presented below (Authentication with an "user" or "admin" role required):
<br><br><br>
*{*
*"orderId": 1*
*}*
<br><br><br>
This will also automatically create a "PaymentDTO" object and send it to the order microservice to update the selected order status to "paid"
