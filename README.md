# Adaptive Shape Explorer

A modern, responsive Android application built using Kotlin and standard Android XML Views. The application demonstrates canonical **List-Detail (Master-Detail)** UX behavior that seamlessly scales across different device types, form factors, and window configurations (such as standard smartphones, foldables, tablets, and desktop modes).

## 🚀 Key Features

- **Adaptive Multi-Pane Layout:** Employs `androidx.slidingpanelayout.widget.SlidingPaneLayout` to handle device screen sizes automatically.
  - **Wide Screens (Tablets / Foldables / Desktops):** Displays both the shape choice pane (`ListView`) and the detailed shape presentation pane (`ImageView` and descriptors) side-by-side simultaneously.
  - **Compact Screens (Smartphones):** Operates as a seamless single-pane drill-down layout. Clicking an item slides open the detail pane over the master list.
- **Dynamic Content Binding:** Tapping items in the `ListView` programmatically swaps graphics and descriptions in the detailed preview view in real-time.
- **Smart Back Navigation:** Installs an intelligent `OnBackPressedCallback` hooked into the system's `OnBackPressedDispatcher`. On smaller devices, pressing the hardware or system back gesture fluidly slips the detail page away to reveal the list again instead of abruptly exiting the app.
- **High-Definition Graphics:** Features pixel-perfect, custom-designed geometric vector drawables (`.xml`) that look sharp at any display scale or density.
- **Modern Edge-to-Edge System Bars:** Integrated with modern window inset dispatching (`ViewCompat.setOnApplyWindowInsetsListener`) ensuring layouts extend edge-to-edge beautifully behind status and navigation bars.

## 🛠️ Architecture & Core Components

- **Activity Layout:** `activity_main.xml` uses a root `SlidingPaneLayout` with a fixed-width left pane (`ListView`) and a weighted, flexible right container (`LinearLayout`).
- **Data Model:** `ShapeItem.kt` encapsulates the core layout properties (Name, Vector Graphic Reference ID, and Educational Text).
- **Controller Logic:** `MainActivity.kt` manages programmatic initialization, list selection tracking, details panel updates, view state restoration, and gesture-aware back stacks.

## 📋 Requirements

- **Minimum SDK:** API 29 (Android 10)
- **Target / Compile SDK:** API 37
- **Language:** Kotlin
- **Build System:** Gradle (Kotlin Script - `.gradle.kts` with Version Catalogs)

## 🔧 Installation & Running

1. Clone or open the project folder `pgm7` inside **Android Studio**.
2. Perform a **Gradle Sync** to download dependencies.
3. Select your target device or emulator (try testing on both a Phone and a Tablet/Foldable to see the adaptive panes in action).
4. Click **Run (`Shift + F10`)** or use the **Deploy** tool.

## Output

The app automatically adjusts its interface to best match the available display size:

### 📱 Compact Screens (Single Pane Mode)
On standard smartphones, the app presents a clean list of options. Selecting an item smoothly slides the full detail view over the list, complete with standard back gesture support to return to the shape selection index.

<p align="center">
  <img src="images/Screenshot 2026-09-17 104915.png" width="30%" alt="Shape Selection List" />
  <img src="images/Screenshot 2026-09-17 104956.png" width="30%" alt="Shape Detail View" />
</p>

### 📐 Wide Screens & Foldables (Dual Pane Mode)
On large tablets, desktop modes, or unfolded screens, both panes remain visible side-by-side automatically, leveraging extra real estate to display list metadata and crisp high-definition geometric vector assets synchronously.

<p align="center">
  <img src="images/Screenshot 2026-09-17 105113.png" width="75%" alt="Dual Pane View 1" />
  <img src="images/Screenshot 2026-09-17 105144.png" width="75%" alt="Dual Pane View 2" />
</p>


