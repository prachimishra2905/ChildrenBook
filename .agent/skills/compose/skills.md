---
name: baby-book-ui-designer
description: Designs and generates high-fidelity Figma-style mobile UI screens for baby and toddler book flows, including home screen hero buttons, multi-age bracket dialog modals (0-3m, 3-6m, 6-12m, 1-2y), and curated book list screens with direct Amazon buy links.
---
# Baby Book UI Designer Skill
Use this skill whenever the user asks to design, prototype, or generate mobile app screens for baby and toddler book recommendations, age filtering modals, or e-commerce book catalog interfaces.
---
## 🎨 Visual Design System & Tokens
When generating screens, always adhere to this child-friendly, modern aesthetic:
- **Color Palette:**
    - Primary Accent: Indigo (`#4F46E5`) — used for main branding, active states, and primary CTAs.
    - Amazon CTA: Amber Gradient (`from-amber-400 to-amber-500`) with dark slate price pills (`#0F172A`).
    - Age Colors:
        - 0 – 3 Months: High contrast Slate/Black (`#0F172A`) & White.
        - 3 – 6 Months: Soft Amber (`#F59E0B`).
        - 6 – 12 Months: Gentle Rose/Coral (`#FB7185`).
        - 1 – 2 Years: Sky Blue/Teal (`#0EA5E9`).
- **Typography:** Friendly, rounded sans-serif (Fredoka or Nunito). Minimum readable text floor is `text-xs` (12px).
- **Surface Elevation:** `rounded-[44px]` for phone chassis, `rounded-[32px]` for bottom sheet dialogs, `rounded-2xl` for book cards.
- **Canvas Presentation:** Dark blueprint background (`#0F172A` with dotted grid pattern) displaying smartphone frames with realistic bezels, status bar (`9:41`), and pill notches.
---
## 📱 Mandatory 3-Screen User Journey
Every generated prototype MUST include the following 3 interconnected screens:
### Screen 01: Home Screen (The Entry Point)
1. **Status Bar:** Top bar with time, battery, Wi-Fi, and camera notch.
2. **Branding Header:** App title (e.g. "Baby Nook 🐣") with user avatar.
3. **Hero Primary Button — "Baby Book":**
    - High visual weight with vibrant gradient and subtle ambient glow.
    - Large bold label: **"Baby Book"** + playful floating icon (`📚`).
    - Subtitle describing the catalog (e.g., *"Curated books from 0 to 24 months"*).
    - Clear click affordance indicating that tapping opens the age selector.
4. **Context Elements:** Supporting widgets like "Daily Reading Tip" and secondary activities ("Lullabies", "Sensory Toys").
---
### Screen 02: Age Selection Dialog (Modal / Bottom Sheet)
Triggered immediately when the user taps "Baby Book":
1. **Backdrop:** Frosted dark blur (`bg-slate-900/60 backdrop-blur-sm`).
2. **Sheet Surface:** White bottom sheet (`rounded-t-[36px]`) with top drag handle.
3. **Header:** Title *"Select Baby Age"* with subtitle *"Choose age bracket to reveal developmentally matched books"*.
4. **The 4 Standard Age Checkbox / Radio Options:**
    - `👀 0 – 3 Months`: High contrast, visual stimulation, black & white patterns.
    - `✋ 3 – 6 Months`: Soft cloth books, crinkle textures, baby-safe mirrors.
    - `🧸 6 – 12 Months`: Touch-and-feel, lift-the-flap board books.
    - `🎨 1 – 2 Years`: First words, animal sounds, daily routines & rhymes.
5. **Selection State:** Active item highlights with a 2px indigo border, soft indigo background tint, and a checked indicator (`✓`).
6. **Confirmation CTA:** Large rounded button: *"Show Books ➔"*.
---
### Screen 03: Book List Screen (With Amazon Redirect Links)
Displays the books corresponding to the selected age bracket:
1. **Top Nav Bar:** Back button (`←`), category title (e.g., *"0 – 3 Months Books"*), and current age tag badge.
2. **Book Item Cards:**
    - **Cover Thumbnail:** Visual book cover with topic icon and high-contrast color.
    - **Metadata:** Category tag (e.g., `"High Contrast"`), book title, author, and star rating (e.g., `★ 4.8 (1.2k)`).
    - **Direct Amazon Redirect Button:**
        - High-visibility Amazon amber gradient styling.
        - Label: **"Buy on Amazon"** + bold price pill (e.g., `$7.99`) + cart icon (`🛒`).
        - Clickable link targeting the real Amazon book product URL (`target="_blank"`).
3. **Sticky Footer / Age Switcher:** Allows toggling between age categories without returning to the home screen.
---
## 🛠️ Output Generation Rules
1. **Artifact Creation:** Always write the output as a standalone, responsive HTML file containing Tailwind CSS (`https://cdn.tailwindcss.com`).
2. **Dual-View Support:**
    - **Canvas View:** Displays all 3 mobile frames side-by-side (like viewing screens inside Figma).
    - **Clickable Prototype View:** An interactive single-phone frame that lets the user tap "Baby Book" ➔ opens the dialog ➔ pick an age ➔ renders the book cards with Amazon links.
3. **Inline Embedding:** When completing the user request, embed the generated HTML artifact into the conversation using `<agent-embed src="file:///<path>"></agent-embed>`.