# Happy Habits: Upscale the way you manage your Health ⚕️
<div style="text-align: center;">
  <img src="../image-removebg-preview.png" alt="Happy Habits logo" width="200" height="200">
</div>


Are your tired of endless notes on papers, unorganized web and mobile apps for daily tracking. Do you find tiring to log your activities every day ? Are you as a doctor tired of managing your patient daily info ?

`Happy Habits` is a mobile android app, that is designed both for patients and for users. It is a suitable application for daily tracking activity and also bridges the gap between patient and doctor.

### `Happy Habits` is divided into 2 sections:
- 📱 [Mobile-App](../Mobile-App/): Find the frontend and the [UI-UX](#ui-ux) of our app. It has been implemented to be user friendly for the patients and the doctors.
- 🖥️ [API-Server](../API-Server/): This is the core of our app. Here is the implementation of the backend. Every here is connected to server all users. It manages the CRUD operations to the database and functions as a hub for the chat communication.

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

    ![General Logic Architecture](../general-logic.png)
  * ### Frontend Level Architecture
    As the diagram below shows, frontend design follow the [M(odel)-V(iew)-V(iew)M(odel)](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93viewmodel) architecture, and also some characteristics of the [Clean](https://www.freecodecamp.org/news/a-quick-introduction-to-clean-architecture-990c014448d2/) architecture.
    ![Frontend Layer Architecture](../Frontend-Layer-Architecture.png)
  * ### Backend Level Architecture
    On the other hand, backend design is constructed on the simple Controller-Service-Repository pattern, following the principles of the REST Api architecture.
    ![Backend Layer Architecture](../Backend-Layer-Architecture.png)


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
### Doctor Only Screens

<div style="display: flex; justify-content: center; flex-wrap: wrap; gap: 20px; padding: 20px;">
  <figure style="margin: 0; text-align: center; flex: 1 1 200px;">
    <img src="../Mobile-App/screenshots1/screenshots1/doctor_only/doctor_chat.png" width="100%" alt="Chat Page" />
    <figcaption>Chat Page</figcaption>
  </figure>
  <figure style="margin: 0; text-align: center; flex: 1 1 200px;">
    <img src="../Mobile-App/screenshots1/screenshots1/doctor_only/doctor_homepage.png" width="100%" alt="HomePage" />
    <figcaption>HomePage</figcaption>
  </figure>
  <figure style="margin: 0; text-align: center; flex: 1 1 200px;">
    <img src="../Mobile-App/screenshots1/screenshots1/doctor_only/doctor_inbox.png" width="100%" alt="Inbox" />
    <figcaption>Inbox</figcaption>
  </figure>
  <figure style="margin: 0; text-align: center; flex: 1 1 200px;">
    <img src="../Mobile-App/screenshots1/screenshots1/doctor_only/doctor_profile.png" width="100%" alt="Profile" />
    <figcaption>Profile</figcaption>
  </figure>
</div>

### General Screens

<div style="display: flex; justify-content: center; flex-wrap: wrap; gap: 20px; padding: 20px;">
  <figure style="margin: 0; text-align: center; flex: 1 1 200px;">
    <img src="../Mobile-App/screenshots1/screenshots1/general/choose_role_screen.png" width="100%" alt="Choose Role" />
    <figcaption>Choose Role</figcaption>
  </figure>
  <figure style="margin: 0; text-align: center; flex: 1 1 200px;">
    <img src="../Mobile-App/screenshots1/screenshots1/general/choose_role_selected_screen.png" width="100%" alt="Choose Role Selected" />
    <figcaption>Choose Role Selected</figcaption>
  </figure>
  <figure style="margin: 0; text-align: center; flex: 1 1 200px;">
    <img src="../Mobile-App/screenshots1/screenshots1/general/error_login.png" width="100%" alt="LogIn Error" />
    <figcaption>LogIn Error</figcaption>
  </figure>
  <figure style="margin: 0; text-align: center; flex: 1 1 200px;">
    <img src="../Mobile-App/screenshots1/screenshots1/general/get_started_screen.png" width="100%" alt="Loading..." />
    <figcaption>Loading...</figcaption>
  </figure>
</div>

### Authentication Screens

<div style="display: flex; justify-content: center; flex-wrap: wrap; gap: 20px; padding: 20px;">
  <figure style="margin: 0; text-align: center; flex: 1 1 200px;">
    <img src="../Mobile-App/screenshots1/screenshots1/general/login_screen.png" width="100%" alt="LogIn" />
    <figcaption>LogIn</figcaption>
  </figure>
  <figure style="margin: 0; text-align: center; flex: 1 1 200px;">
    <img src="../Mobile-App/screenshots1/screenshots1/general/scan_qr_code.png" width="100%" alt="QR Code Scan" />
    <figcaption>QR Code Scan</figcaption>
  </figure>
  <figure style="margin: 0; text-align: center; flex: 1 1 200px;">
    <img src="../Mobile-App/screenshots1/screenshots1/general/share_qr_screen.png" width="100%" alt="Share QR Code" />
    <figcaption>Share QR Code</figcaption>
  </figure>
  <figure style="margin: 0; text-align: center; flex: 1 1 200px;">
    <img src="../Mobile-App/screenshots1/screenshots1/general/sign_up_screen.png" width="100%" alt="Sign Up" />
    <figcaption>Sign Up</figcaption>
  </figure>
  <figure style="margin: 0; text-align: center; flex: 1 1 200px;">
    <img src="../Mobile-App/screenshots1/screenshots1/general/splash_screen.png" width="100%" alt="Splash" />
    <figcaption>Splash</figcaption>
  </figure>
</div>

### General User Screens

<div style="display: flex; justify-content: center; flex-wrap: wrap; gap: 20px; padding: 20px;">
  <figure style="margin: 0; text-align: center; flex: 1 1 200px;">
    <img src="../Mobile-App/screenshots2/general_user/home_page_user.png" width="100%" alt="LogIn" />
    <figcaption>Home Page</figcaption>
  </figure>
  <figure style="margin: 0; text-align: center; flex: 1 1 200px;">
    <img src="../Mobile-App/screenshots2/general_user/inbox_user.png" width="100%" alt="QR Code Scan" />
    <figcaption>Inbox User</figcaption>
  </figure>
  <figure style="margin: 0; text-align: center; flex: 1 1 200px;">
    <img src="../Mobile-App/screenshots2/general_user/user_chat.png" width="100%" alt="Share QR Code" />
    <figcaption>User Chat</figcaption>
  </figure>
  <figure style="margin: 0; text-align: center; flex: 1 1 200px;">
    <img src="../Mobile-App/screenshots2/general_user/user_profile.png" width="100%" alt="Sign Up" />
    <figcaption>User Profile</figcaption>
  </figure>
</div>

### Log Habit Screens

<div style="display: flex; justify-content: center; flex-wrap: wrap; gap: 20px; padding: 20px;">
  <figure style="margin: 0; text-align: center; flex: 1 1 200px;">
    <img src="../Mobile-App/screenshots2/log_habit/add_aspirin_1.png" width="100%" alt="LogIn" />
    <figcaption>Add medicine</figcaption>
  </figure>
  <figure style="margin: 0; text-align: center; flex: 1 1 200px;">
    <img src="../Mobile-App/screenshots2/log_habit/add_avocado_screen.png" width="100%" alt="QR Code Scan" />
    <figcaption>Add food</figcaption>
  </figure>
  <figure style="margin: 0; text-align: center; flex: 1 1 200px;">
    <img src="../Mobile-App/screenshots2/log_habit/add_medication_calendar.png" width="100%" alt="Share QR Code" />
    <figcaption>Add medication calendar</figcaption>
  </figure>
  <figure style="margin: 0; text-align: center; flex: 1 1 200px;">
    <img src="../Mobile-App/screenshots2/log_habit/choose_serving_screen.png" width="100%" alt="Sign Up" />
    <figcaption>Choose serving</figcaption>
  </figure>
  <figure style="margin: 0; text-align: center; flex: 1 1 200px;">
    <img src="../Mobile-App/screenshots2/log_habit/days_stats_screen.png" width="100%" alt="LogIn" />
    <figcaption>Daily Stats</figcaption>
  </figure>
  <figure style="margin: 0; text-align: center; flex: 1 1 200px;">
    <img src="../Mobile-App/screenshots2/log_habit/log_food_screen.png" width="100%" alt="QR Code Scan" />
    <figcaption>Log food</figcaption>
  </figure>
  <figure style="margin: 0; text-align: center; flex: 1 1 200px;">
    <img src="../Mobile-App/screenshots2/log_habit/log_medication_screen.png" width="100%" alt="Share QR Code" />
    <figcaption>Log medication</figcaption>
  </figure>
  <figure style="margin: 0; text-align: center; flex: 1 1 200px;">
    <img src="../Mobile-App/screenshots2/log_habit/log_mood_screen.png" width="100%" alt="Sign Up" />
    <figcaption>Log mood</figcaption>
  </figure><figure style="margin: 0; text-align: center; flex: 1 1 200px;">
    <img src="../Mobile-App/screenshots2/log_habit/log_running_screen.png" width="100%" alt="LogIn" />
    <figcaption>Log running</figcaption>
  </figure>
  <figure style="margin: 0; text-align: center; flex: 1 1 200px;">
    <img src="../Mobile-App/screenshots2/log_habit/log_sleep_screen.png" width="100%" alt="QR Code Scan" />
    <figcaption>Log sleep</figcaption>
  </figure>
  <figure style="margin: 0; text-align: center; flex: 1 1 200px;">
    <img src="../Mobile-App/screenshots2/log_habit/log_swimming_workout.png" width="100%" alt="Share QR Code" />
    <figcaption>Log swimming</figcaption>
  </figure>
  <figure style="margin: 0; text-align: center; flex: 1 1 200px;">
    <img src="../Mobile-App/screenshots2/log_habit/log_toilet_screen.png" width="100%" alt="Sign Up" />
    <figcaption>Log toilet activity</figcaption>
  </figure><figure style="margin: 0; text-align: center; flex: 1 1 200px;">
    <img src="../Mobile-App/screenshots2/log_habit/log_weights_pick_exercise_screen.png" width="100%" alt="LogIn" />
    <figcaption>Pick weight exercise</figcaption>
  </figure>
  <figure style="margin: 0; text-align: center; flex: 1 1 200px;">
    <img src="../Mobile-App/screenshots2/log_habit/log_weights_screen.png" width="100%" alt="QR Code Scan" />
    <figcaption>Log weights</figcaption>
  </figure>
  <figure style="margin: 0; text-align: center; flex: 1 1 200px;">
    <img src="../Mobile-App/screenshots2/log_habit/log_weights_set_hour_screen.png" width="100%" alt="Share QR Code" />
    <figcaption>Set hours in weights</figcaption>
  </figure>
  <figure style="margin: 0; text-align: center; flex: 1 1 200px;">
    <img src="../Mobile-App/screenshots2/log_habit/log_workout_screen.png" width="100%" alt="Sign Up" />
    <figcaption>Log workout</figcaption>
  </figure><figure style="margin: 0; text-align: center; flex: 1 1 200px;">
    <img src="../Mobile-App/screenshots2/log_habit/log_yoga_exercises_screen.png" width="100%" alt="LogIn" />
    <figcaption>Log yoga</figcaption>
  </figure>
  <figure style="margin: 0; text-align: center; flex: 1 1 200px;">
    <img src="../Mobile-App/screenshots2/log_habit/log_yoga_workout_screen.png" width="100%" alt="QR Code Scan" />
    <figcaption>Choose yoga activity</figcaption>
  </figure>
  <figure style="margin: 0; text-align: center; flex: 1 1 200px;">
    <img src="../Mobile-App/screenshots2/general_user/user_chat.png" width="100%" alt="Share QR Code" />
    <figcaption>User chat</figcaption>
  </figure>
  <figure style="margin: 0; text-align: center; flex: 1 1 200px;">
    <img src="../Mobile-App/screenshots2/log_habit/logged_yoga_exercises.png" width="100%" alt="Sign Up" />
    <figcaption>Logged yoga activities</figcaption>
  </figure><figure style="margin: 0; text-align: center; flex: 1 1 200px;">
    <img src="../Mobile-App/screenshots2/log_habit/medication_details_screen.png" width="100%" alt="LogIn" />
    <figcaption>Medication detaiks</figcaption>
  </figure>
  <figure style="margin: 0; text-align: center; flex: 1 1 200px;">
    <img src="../Mobile-App/screenshots2/log_habit/medication_screen.png" width="100%" alt="QR Code Scan" />
    <figcaption></figcaption>
  </figure>
  <figure style="margin: 0; text-align: center; flex: 1 1 200px;">
    <img src="../Mobile-App/screenshots2/log_habit/one_symptom_screen.png" width="100%" alt="Share QR Code" />
    <figcaption>Share QR Code</figcaption>
  </figure>
  <figure style="margin: 0; text-align: center; flex: 1 1 200px;">
    <img src="../Mobile-App/screenshots2/log_habit/remove_medication_screen.png" width="100%" alt="Sign Up" />
    <figcaption>Sign Up</figcaption>
  </figure><figure style="margin: 0; text-align: center; flex: 1 1 200px;">
    <img src="../Mobile-App/screenshots2/log_habit/search_food_screen.png" width="100%" alt="LogIn" />
    <figcaption>LogIn</figcaption>
  </figure>
  <figure style="margin: 0; text-align: center; flex: 1 1 200px;">
    <img src="../Mobile-App/screenshots2/log_habit/see_avocado_macros_1.png" width="100%" alt="QR Code Scan" />
    <figcaption>QR Code Scan</figcaption>
  </figure>
  <figure style="margin: 0; text-align: center; flex: 1 1 200px;">
    <img src="../Mobile-App/screenshots2/log_habit/see_avocado_macros_2.png" width="100%" alt="Share QR Code" />
    <figcaption>Share QR Code</figcaption>
  </figure>
  <figure style="margin: 0; text-align: center; flex: 1 1 200px;">
    <img src="../Mobile-App/screenshots2/log_habit/symptoms_screen.png" width="100%" alt="Sign Up" />
    <figcaption>Sign Up</figcaption>
  </figure>
    <figure style="margin: 0; text-align: center; flex: 1 1 200px;">
    <img src="../Mobile-App/screenshots2/log_habit/weights_logged_exercises.png" width="100%" alt="Sign Up" />
    <figcaption>Sign Up</figcaption>
  </figure>
</div>