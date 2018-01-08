# priceBasket
Show and tell for spring 4 mvc integration with Jboss Drools

This is a simple example of integrating Jboss drools in a spring boot application.

The application accepts some Json representing a shopping cart, through endpoint /checkout. The application then returns a json representation of the subtotal, total discount and total for that shopping cart. Discounts have been written using Jboss drools.

To run application navigate from command line to project home and run following command:

mvn package && java -jar target/pricebasket-0.1.0.jar

This will spin up spring boot application. Again from command line you can run curl commands of below format:

curl -XPOST localhost:8080/checkout -H "Content-Type: application/json" -d '{"basket":["soup","bread","apples" ,"apples", "apples"]}'

and you will recieve back json of the format:

{"subtotal":4.45,"discount":1.65,"total":2.8}

Items available for purchase are as follows with price:

soup, 0.65
bread, 0.8
milk, 1.3
apples, 1.0