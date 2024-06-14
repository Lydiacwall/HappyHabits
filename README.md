# Happy-Habits


## Front-end code 
Its in the path Happy-Habits\MobileApp\app\src\main\java\com\example\happyhabits
It has 4 folder:
1) core ->
2) di-> contains all the modules for all the different types of habit logging and also for the authentication and chat feautures .
3) feauture_application -> contains the functionalities of all the screens the app has. Each one has its own folder.
    For example lets take the feauture_sleep folder. It has three folder  :
         1) presentation -> it has the .kt files with the screens functionalities , its event handler , state and view Model
         2) domain -> is has the basic class of the feature , the use-cases the screen can do (the different functionts the screen has , such as sending the parameters ,the 
                      user has chosen to log their sleep ,to the server , and also the fuctions that the screen uses to send and receive information from the sever
          3)data -> it has the declarations of the functions that the presentation uses to send/receive data to/from the server and the formations the data is being 
                    send/receive 
