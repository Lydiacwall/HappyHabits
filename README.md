# Happy Habits: Upscale the way you manage your Health ⚕️

<p align="center">
  <img src="./image-removebg-preview.png" alt="Happy Habits logo" width="200" height="200">
</p>

Are you tired of endless notes on papers, unorganized web and mobile apps for daily tracking. Do you find tiring to log your activities every day ? Are you as a doctor tired of managing your patient daily info ?

`Happy Habits` is a mobile android app, that is designed both for patients and for users. It is a suitable application for daily tracking activity and also bridges the gap between patient and doctor.

### `Happy Habits` is divided into 2 sections:
- 📱 [Mobile-App](../Mobile-App/): Find the frontend and the [UI-UX](#ui-ux) of our app. It has been implemented to be user friendly for the patients and the doctors.
- 🖥️ [API-Server](../API-Server/): This is the core of our app. Here is the implementation of the backend. Every here is connected to serve all users. It manages the CRUD operations to the database and functions as a hub for the chat communication.

## 🔑Who can use `Happy Habits` ?
- 🤒 Patients: First and foremost anybody can use the application. But it is especially designed for users who want to:
  * 🔒 Authenticate, sign up and log in.
  * 🗒️ Track their daily activities such as food and medication consumption and others (toilet, workout, sleep, symptoms and mood).
  * 📊 Check statistics for some activities.
  * 💬 Chat and send to doctors their log record.
- 🩺 Doctors: Doctors can also make good use of our app, in order to connect, communicate and receive the records of their patients via email.

To all of the above, patients and doctors can connect using the QR code feature, shown in the [UI-UX](#ui-ux) section.

> ❗ However, the qr code feature can only connect a patient and a doctor, but not two users who belong to the same group
## 🖼️Architecture

  * ### Top Level Architecture
    Here is a diagram which showcases the main components communicate and share information between them. 

    ![General Logic Architecture](./general-logic.png)
  * ### Frontend Level Architecture
    As the diagram below shows, frontend design follow the [M(odel)-V(iew)-V(iew)M(odel)](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93viewmodel) architecture, and also some characteristics of the [Clean](https://www.freecodecamp.org/news/a-quick-introduction-to-clean-architecture-990c014448d2/) architecture.
    
    ![Frontend Layer Architecture](./Frontend-Layer-Architecture.png)
  * ### Backend Level Architecture
    On the other hand, backend design is constructed on the simple Controller-Service-Repository pattern, following the principles of the REST Api architecture.
    ![Backend Layer Architecture](./Backend-Layer-Architecture.png)


## 🧰🛠️Libraries & Tools
Below there is a list of the main libraries that were used to build our application.
On the mobile app, we used:
  * Retrofit, for the connection with the backend on the Data Layer
  * Coompose, for the UI design
  * Hilt-Dagger, for the Dependency Injection
On the Api, we used:
  * AspNetCore, for the development of the Api
  * MongoDbDriver, for the connection with MongoDb
  * SignalR, for the bidirectional communication between users
  * DinkToPdf, for the creation of PDF files sent to doctors from patients

## 🎨UI-UX
This section provides a comprehensive overview of various screens from the mobile application, categorized by features and user types. Below are the sections:

### Doctor Only Screens

|  |  |  |  |
| --- | --- | --- | --- |
| ![Chat Page](./Mobile-App/screenshots1/screenshots1/doctor_only/doctor_chat.png) | ![HomePage](./Mobile-App/screenshots1/screenshots1/doctor_only/doctor_homepage.png) | ![Inbox](./Mobile-App/screenshots1/screenshots1/doctor_only/doctor_inbox.png) | ![Profile](./Mobile-App/screenshots1/screenshots1/doctor_only/doctor_profile.png) |
| **Chat Page**<br>Allows doctors to chat with patients. | **HomePage**<br>Main dashboard for doctors. | **Inbox**<br>Doctors receive messages and notifications here. | **Profile**<br>Doctor’s personal profile and settings. |

---

### General Screens

|  |  |  |  |
| --- | --- | --- | --- |
| ![Choose Role](./Mobile-App/screenshots1/screenshots1/general/choose_role_screen.png) | ![Choose Role Selected](./Mobile-App/screenshots1/screenshots1/general/choose_role_selected_screen.png) | ![LogIn Error](./Mobile-App/screenshots1/screenshots1/general/error_login.png) | ![Loading...](./Mobile-App/screenshots1/screenshots1/general/get_started_screen.png) |
| **Choose Role**<br>Choose the user role for accessing the app. | **Choose Role Selected**<br>Role selection confirmation screen. | **LogIn Error**<br>Error message during login. | **Loading**<br>Loading screen while the app is initializing. |

---

### Authentication Screens

|  |  |  |  |
| --- | --- | --- | --- |
| ![LogIn](./Mobile-App/screenshots1/screenshots1/general/login_screen.png) | ![QR Code Scan](./Mobile-App/screenshots1/screenshots1/general/scan_qr_code.png) | ![Share QR Code](./Mobile-App/screenshots1/screenshots1/general/share_qr_screen.png) | ![Sign Up](./Mobile-App/screenshots1/screenshots1/general/sign_up_screen.png) |
| **LogIn**<br>User login screen. | **QR Code Scan**<br>Scan a QR code for quick access. | **Share QR Code**<br>Option to share the user’s QR code. | **Sign Up**<br>User registration screen. |
| ![Splash](./Mobile-App/screenshots1/screenshots1/general/splash_screen.png) | | | |
| **Splash**<br>Initial loading screen of the app. | | | |

---

### General User Screens

|  |  |  |  |
| --- | --- | --- | --- |
| ![Home Page](./Mobile-App/screenshots2/general_user/home_page_user.png) | ![Inbox User](./Mobile-App/screenshots2/general_user/inbox_user.png) | ![User Chat](./Mobile-App/screenshots2/general_user/user_chat.png) | ![User Profile](./Mobile-App/screenshots2/general_user/user_profile.png) |
| **Home Page**<br>Main interface for general users. | **Inbox User**<br>User messages and notifications. | **User Chat**<br>Chat interface for users. | **User Profile**<br>User’s personal profile and settings. |

---

### Log Habit Screens

|  |  |  |  |
| --- | --- | --- | --- |
| ![Add medicine](./Mobile-App/screenshots2/log_habit/add_aspirin_1.png) | ![Add food](./Mobile-App/screenshots2/log_habit/add_avocado_screen.png) | ![Add medication calendar](./Mobile-App/screenshots2/log_habit/add_medication_calendar.png) | ![Choose serving](./Mobile-App/screenshots2/log_habit/choose_serving_screen.png) |
| **Add medicine**<br>Screen for adding new medications. | **Add food**<br>Add food items to the log. | **Add medication calendar**<br>Create a medication schedule. | **Choose serving**<br>Select serving sizes for food. |
| ![Daily Stats](./Mobile-App/screenshots2/log_habit/days_stats_screen.png) | ![Log food](./Mobile-App/screenshots2/log_habit/log_food_screen.png) | ![Log medication](./Mobile-App/screenshots2/log_habit/log_medication_screen.png) | ![Log mood](./Mobile-App/screenshots2/log_habit/log_mood_screen.png) |
| **Daily Stats**<br>View daily health statistics. | **Log food**<br>Input food intake for the day. | **Log medication**<br>Record medications taken. | **Log mood**<br>Track mood and emotional state. |
| ![Log running](./Mobile-App/screenshots2/log_habit/log_running_screen.png) | ![Log sleep](./Mobile-App/screenshots2/log_habit/log_sleep_screen.png) | ![Log swimming](./Mobile-App/screenshots2/log_habit/log_swimming_workout.png) | ![Log toilet activity](./Mobile-App/screenshots2/log_habit/log_toilet_screen.png) |
| **Log running**<br>Record running activities. | **Log sleep**<br>Input sleep duration and quality. | **Log swimming**<br>Track swimming workouts. | **Log toilet activity**<br>Log toilet usage. |
| ![Pick weight exercise](./Mobile-App/screenshots2/log_habit/log_weights_pick_exercise_screen.png) | ![Log weights](./Mobile-App/screenshots2/log_habit/log_weights_screen.png) | ![Set hours in weights](./Mobile-App/screenshots2/log_habit/log_weights_set_hour_screen.png) | ![Log workout](./Mobile-App/screenshots2/log_habit/log_workout_screen.png) |
| **Pick weight exercise**<br>Select weightlifting exercises. | **Log weights**<br>Record weightlifting activities. | **Set hours in weights**<br>Input weightlifting hours. | **Log workout**<br>Track overall workouts. |
| ![Log yoga](./Mobile-App/screenshots2/log_habit/log_yoga_exercises_screen.png) | ![Choose yoga activity](./Mobile-App/screenshots2/log_habit/log_yoga_workout_screen.png) | ![User chat](./Mobile-App/screenshots2/general_user/user_chat.png) | ![Logged yoga activities](./Mobile-App/screenshots2/log_habit/logged_yoga_exercises.png) |
| **Log yoga**<br>Record yoga sessions. | **Choose yoga activity**<br>Select yoga activities. | **User chat**<br>Chat interface for users. | **Logged yoga activities**<br>View logged yoga sessions. |
| ![Medication details](./Mobile-App/screenshots2/log_habit/medication_details_screen.png) | ![Medication](./Mobile-App/screenshots2/log_habit/medication_screen.png) | ![Share QR Code](./Mobile-App/screenshots2/log_habit/one_symptom_screen.png) | ![Sign Up](./Mobile-App/screenshots2/log_habit/remove_medication_screen.png) |
| **Medication details**<br>View details of medications. | **Medication**<br>Medication overview screen. | **Share QR Code**<br>Option to share medication details. | **Sign Up**<br>Option to register new users. |
| ![LogIn](./Mobile-App/screenshots2/log_habit/search_food_screen.png) | ![QR Code Scan](./Mobile-App/screenshots2/log_habit/see_avocado_macros_1.png) | ![Share QR Code](./Mobile-App/screenshots2/log_habit/see_avocado_macros_2.png) | ![Sign Up](./Mobile-App/screenshots2/log_habit/symptoms_screen.png) |
| **LogIn**<br>User login screen. | **QR Code Scan**<br>Scan a food QR code. | **Share QR Code**<br>Share food information. | **Sign Up**<br>User registration screen. |
| ![Log weight Exercises](./Mobile-App/screenshots2/log_habit/weights_logged_exercises.png) | | | |
| **Log weight Exercises**<br>Select weight exercises. | | | |

## 🤝Contributors
* [Lydia Christina Wallace](https://github.com/Lydiacwall)
* [Miltos Tsolkas](https://github.com/miltos02)
* [Panagiotis Triantafillidis](https://github.com/Panattack)
