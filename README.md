#Get rich by buying and selling Crypto

####`Video Demo:` https://youtu.be/iMypnt1OQWE

####`Description:` In this project the initial idea was to build a web application 

####that allowed the user to interact with cryptocurrencies, that way the person that is into this practice

####would be able to increase their ability to invest into the raising world of cryptos.

####In this project i am just sending the back end part of it that is divided en two

####microservices, the purpose of dividing this into two microservices was to practice

####the concepts of the AMQP protocol through Rabbit mq.

----

##Setup

For you to be able to run this project locally you will have to open the /src folder
the /resources forlder and in there change the credentials of your own remote AMQP service
or delete the personalized configuration, so that it runs with the default credentials,
that are localhost as host of the rabbit mq service, with the username and password of guest.

I would recommend to you that you use IntelliJ so that it by its own downloads the project
dependencies, and finally you fill by able to execute the service by running the spring application
in the /src folder and in the class, `MicroServiceReceiverPublisherApplication`.

##Project structure:

`Config`Here you will find the configuration class where i set up everything related 
with rabbit mq, the three different queues for the three different types of actions, tha are general actions,
selling actions and buying actions; the topic exchange and and the three different routing keys that
are associated to the three different queues.

`dto`Here is the data structure that contains all the information of the cryptocurrency and 
the action associated with the user that executed the action

`Routers` Here i expose an entry point through HTTP post method to receive the information form 
the client side.

`UseCases` Here i execute the use case that publishes the messages to the three different queues 
depends on the one that it belongs.


#I hope that you find this project useful somehow and here i leave the link that will take you to the other microservice


##Consumer MicroService: https://github.com/santiagoposadag/finalProject-Cs50-microservice-consumer
