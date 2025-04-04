## Project Overview

This project allows users to manage tasks with functionality to **add**, **remove**, and **update** them. It uses **Room DB** for local data storage. The app also integrates **Firebase Analytics** to track events and monitor crashes. An APK build is provided for testing.

### Features:
- **Add**, **remove**, and **update** tasks.
- Local data storage with **Room DB**.
- **Firebase Analytics** for event tracking and crash reporting.

### Setup and Run Instructions:
1. Clone the repository.
2. Open the project in **Android Studio**.
3. Build and run the APK on your device or emulator.

### Third-party Libraries Integrated:
- **Room DB** for local storage.
- **Firebase Analytics** for crash reporting and event tracking.

### Design Decisions:
- **Room DB** is used for persistent, offline storage to ensure fast data access and manage tasks locally.
- Integrated **Firebase Analytics** to monitor app performance and track events, including crashes.

### Firebase Analytics:
- Screenshots of recorded events and logs from the **Firebase Console**.
- A **mobile screen recording** showing the app crashing, along with **crash reports** and details from Firebase.
- **Network performance screenshots** to monitor the app's network behavior during task management.

### APK:
- The **APK** is included in the repository for easy installation and testing on Android devices, such as **Redmi**.

### Crash Recording & Analysis:
- A **screen recording** has been included that demonstrates the app crashing. The recording shows both the **working state** (when the app is functional) and the **non-working state** (during the crash).
- Screenshots of the **crash event** have been added to the `app/src/main/res/drawable` directory for reference.
- **Firebase Analytics logs and screenshots** showing the details of the crash event are available for analysis.

### Additional Resources:
- [**Working App Recording**](https://github.com/kshreeram813/TaskManagerApp/blob/326c0c27acf1d3a23c0d08f62d8394bd0ed099c2/app/src/main/res/drawable/workingapprecording.mp4)
- [**Firebase Crash Screenshot**](https://github.com/kshreeram813/TaskManagerApp/blob/326c0c27acf1d3a23c0d08f62d8394bd0ed099c2/app/src/main/res/drawable/firebasecrash.png)
- [**Crash App Recording**](https://github.com/kshreeram813/TaskManagerApp/blob/326c0c27acf1d3a23c0d08f62d8394bd0ed099c2/app/src/main/res/drawable/crashapprecording.mp4)
