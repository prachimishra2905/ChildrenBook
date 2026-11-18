# 🐣 Baby Nook (ChildrenBook)

[![Android](https://img.shields.io/badge/Platform-Android-3DDC84?logo=android&logoColor=white)](https://developer.android.com)
[![Kotlin](https://img.shields.io/badge/Kotlin-2.2.10-7F52FF?logo=kotlin&logoColor=white)](https://kotlinlang.org)
[![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-Material%203-4285F4?logo=jetpackcompose&logoColor=white)](https://developer.android.com/jetpack/compose)
[![Hilt](https://img.shields.io/badge/Hilt-DI-28509E?logo=dagger&logoColor=white)](https://dagger.dev/hilt)
[![Retrofit](https://img.shields.io/badge/Retrofit-Serialization-000000?logo=square&logoColor=white)](https://square.github.io/retrofit/)

**Baby Nook** is a modern, child-friendly Android application built for parents and caregivers to discover developmentally matched baby and toddler books across different age brackets (0 to 24 months), complete with direct **"Buy on Amazon"** redirect links, daily bonding tips, and an immersive animated launch experience.

---

## ✨ Key Features

- **🎬 Animated Launch Splash Screen:** Features a 3-second animated splash sequence with **fluttering book pages**, ambient background pulse rings, and elastic mascot entrance.
- **📱 Mandatory 3-Screen User Journey:**
  1. **Home Entry:** Welcoming dashboard featuring the vibrant **"Baby Book"** hero card, daily reading bonding tips, and quick activity shortcuts (*Lullabies*, *Sensory Play*).
  2. **Age Selection Modal:** Interactive bottom sheet allowing parents to select developmental age brackets (`0–3m`, `3–6m`, `6–12m`, `1–2y`).
  3. **Curated Book Catalog:** Displays developmentally matched books with cover thumbnails, categories, star ratings, prices, and direct **"🛒 Buy on Amazon"** redirect buttons.
- **🔍 Robust Networking & Curation:** Powered by Retrofit & `kotlinx.serialization` integrating with the Google Books API, backed by intelligent offline curated fallback book lists.
- **💉 Modern Dependency Injection:** Fully decoupled architecture powered by Hilt (KSP) and MVVM (`StateFlow` + `collectAsStateWithLifecycle`).

---

## 🎨 Visual Design System

- **Primary Accent:** Deep Indigo (`#4F46E5`) for branding and primary CTAs.
- **Amazon CTA:** Amber Gradient (`from-amber-400 to-amber-500`) with high-contrast price pills.
- **Age Brackets & Color Coding:**
  - `👀 0 – 3 Months`: High-contrast visual tracking & black/white patterns.
  - `✋ 3 – 6 Months`: Soft cloth & crinkle sensory books.
  - `🧸 6 – 12 Months`: Touch-and-feel & lift-the-flap board books.
  - `🎨 1 – 2 Years`: First words & animal sounds.

---

## 🛠️ Tech Stack & Architecture

- **Language:** Kotlin 2.2.10
- **UI Toolkit:** Jetpack Compose & Material 3
- **Architecture:** MVVM (Model-View-ViewModel) following Clean Architecture principles
- **Dependency Injection:** Dagger Hilt (with KSP)
- **Networking:** Retrofit 2 + OkHttp (Logging Interceptor) + `kotlinx.serialization`
- **Asynchronous Processing:** Kotlin Coroutines & Flow

---

## 🚀 Getting Started

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/prachimishra2905/ChildrenBook.git
   ```
2. **Configure API Key:**
   Create a `local.properties` file in the project root if it doesn't exist, and add your Google Books API key:
   ```properties
   BOOKS_API_KEY=your_google_books_api_key_here
   ```
   *(Note: The app includes robust fallback curated book lists if no API key is provided).*
3. **Build & Run:**
   Open the project in Android Studio (Arctic Fox or newer) and run the app on an Android emulator or physical device.

---

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
