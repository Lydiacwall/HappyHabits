# Happy-Habits


## Front-end code 
Its in the path Happy-Habits\MobileApp\app\src\main\java\com\example\happyhabits
It has 4 folder:
1) core ->
2) di-> contains all the modules for all the different types of habit logging and also for the authentication and chat feautures .
3) feauture_application -> contains the functionalities of all the screens the app has. Each one has its own folder.

    For example lets take the feauture_sleep folder. It has three folder  :
   1. presentation -> it has the .kt files with the screens functionalities , its event handler , state and view Model
   2. domain -> is has the basic class of the feature , the use-cases the screen can do (the different functionts the screen has , such as sending the parameters ,the 
                      user has chosen to log their sleep ,to the server , and also the fuctions that the screen uses to send and receive information from the sever
   3. data -> it has the declarations of the functions that the presentation uses to send/receive data to/from the server and the formations the data is being 
                    send/receive
4) feature _authentication -> similiar with the stucture of feature_sleep

## Back-end code 
Inside the API-Server > Happy Habits App you can see the main project of the server
The code is divided in the following folders:
1. Configurations -> Used components for data manipulation, convertion etc
2. Controllers -> Main endpoints of the app
3. Forms -> Data format sended/received from/to the server
4. Hubs -> The main hub for the chat feature
5. Mode -> The model classes represented in the mongo db
6. PdfConverters -> Pdf converter classes dependending on the of habit
7. Properties -> Main elements for project initialization
8. Repositories -> Main repositories for executing CRUD instructions on the db
9. Services -> Main services for interaction between controller and repository
10. Program.cs -> main initialization class
