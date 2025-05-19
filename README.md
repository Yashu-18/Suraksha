# 🛡️ Suraksha

> A powerful and minimal Android Women Safety Application built with Kotlin, Room, MVVM, and Coroutines — enabling offline emergency contact storage and instant SMS alerts with your location in distress situations.

---

## 📱 Description

**Suraksha** is a lightweight emergency safety app designed to offer immediate help during dangerous situations. With a clean UI featuring two floating action buttons — one to add a trusted contact and another to notify them via SMS — the app sends your live location directly through text message. It uses Room for persistent offline contact storage and MVVM architecture for maintainable code structure.

---

## ✨ Features

- 📇 **Add Emergency Contact**
  - Easily add a single emergency contact using a clean dialog UI  
  - Contact is saved locally and persistently with Room Database

- 🚨 **One-Tap Emergency Alert**
  - Tap “Notify” to instantly send an SMS with your live location  
  - Works without internet – only requires GPS and SMS permission

- 💾 **Offline Data Storage**
  - Uses Room Database to persist contact data across reboots and crashes

- 📍 **Live GPS Location**
  - Fetches real-time coordinates using `FusedLocationProviderClient`

- ⚙️ **Asynchronous Operations**
  - Powered by Kotlin Coroutines for non-blocking background tasks

- 💬 **SMS Integration**
  - Automatically composes and sends messages to the contact with your latitude & longitude

---

## 🧪 Tech Stack

| Technology                | Role                                  |
|---------------------------|---------------------------------------|
| Kotlin                    | Core application language             |
| XML                       | UI Layouts                            |
| Room Database             | Local storage and ORM                 |
| MVVM                      | Architecture pattern                  |
| ViewModel + LiveData      | UI state management                   |
| Coroutines                | Background operations                 |
| FusedLocationProvider     | Accessing GPS location                |
| SMS Manager               | Sending emergency messages            |

---

## 📸 Screenshots

> *(Replace the links below with your actual screenshots)*

<table>
  <tr>
    <td><img src="https://github.com/user-attachments/assets/example1" alt="Splash" width="220" height="450"/><br/>Splash Screen</td>
    <td><img src="https://github.com/user-attachments/assets/example2" alt="Add Contact" width="220" height="450"/><br/>Add Contact</td>
    <td><img src="https://github.com/user-attachments/assets/example3" alt="Send Location" width="220" height="450"/><br/>Emergency Notification</td>
  </tr>
</table>

---

## 🎥 Demo Video

[Click here to watch the demo](https://drive.google.com/file/d/YOUR_DEMO_LINK/view?usp=drivesdk)

---

## 🔐 Permissions Required

- `ACCESS_FINE_LOCATION` – to fetch real-time GPS coordinates  
- `SEND_SMS` – to send alert messages via SMS  
- `READ_CONTACTS` *(optional)* – if integrating with phone contacts  
- `INTERNET` *(optional)* – if extending to use online location maps  

---

## 🚀 How It Works

1. User adds an emergency contact from the Add FAB  
2. On tapping the Notify FAB:
   - The app fetches the user's current location
   - Composes an SMS with coordinates
   - Sends it to the emergency contact using `SmsManager`
 

---

## 🧩 Future Enhancements
- 🔔 Trigger alerts via shake or voice command  
- 🔐 Panic mode with password-protected cancel  

---



