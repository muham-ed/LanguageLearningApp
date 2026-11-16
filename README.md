# 🌟 LinguaMaster (لينجوا ماستر)
### تطبيق متكامل لتعليم اللغة الإنجليزية والألمانية (Android Client + Web Admin Dashboard)

[![Buy on Mostaql](https://img.shields.io/badge/Mostaql-شراء%20التطبيق-blue?style=for-the-badge&logo=appveyor)](https://mostaql.com/project/1292552-%D8%B4%D8%B1%D8%A7%D8%A1-%D8%AA%D8%B7%D8%A8%D9%8A%D9%82-%D9%84%D8%AA%D8%B9%D9%84%D9%8a%D9%85-%D8%A7%D9%84%D9%84%D8%BA%D8%A9-%D8%A7%D9%84%D8%A5%D9%86%D8%AC%D9%84%D9%8A%D8%B2%D9%8A%D8%A9-%D8%AC%D8%A7%D9%87%D8%B2-%D9%84%D9%84%D8%A8%D9%8A%D8%B9?bid=10262176)

---

## 📌 نبذة عن المنتج
تطبيق تجاري احترافي جاهز للبيع ومبني بأحدث تقنيات أندرويد العالمية (**Jetpack Compose + Material 3 + Kotlin**) لدعم تعلم **اللغة الإنجليزية والألمانية** للناطقين بالعربية مع نظام تفاعلي متقدم يحاكي تطبيقات عالمية مثل Duolingo و Busuu.

---

## 🚀 المكونات الأساسية للنظام

### 1. تطبيق الهاتف (Android App)
* **دعم لغوي مزدوج (Dual Language):** التبديل بنقرة زر واحدة بين **اللغة الإنجليزية 🇬🇧** و **اللغة الألمانية 🇩🇪** مع حفظ تقدم كل لغة بشكل مستقل.
* **محرك صوتي متكامل (Offline TTS Audio Engine):** نطق صوتي أصلي نقي وفوري لجميع الكلمات والجمل بدون الحاجة لأي اشتراكات خارجية أو استهلاك للإنترنت.
* **محرك كويزات تفاعلي شامل (Interactive Learning & Quiz Engine):**
  1. **بطاقات استذكار ذكية (Smart Flashcards):** عرض الكلمة، النطق الصوتي، الرمز الصوتي (Phonetics)، الترجمة العربية، وجملة توضيحية.
  2. **اختيار من متعدد (Multiple Choice Quiz):** أسئلة سريعة لفحص الفهم.
  3. **اختبار الاستماع الصوتي (Listening Comprehension):** الاستماع المباشر واختيار المعنى الصحيح.
  4. **بناء وترتيب الجمل (Interactive Sentence Builder):** ترتيب الكلمات المبعثرة بالضغط التفاعلي.
  5. **توصيل الكلمات (Word Matching Pairs):** الربط بين الكلمة الأجنبية وترجمتها بالعربية.
* **نظام التلعيب والتحفيز (Gamification System):**
  - عداد الأيام المتتالية (Streak Days 🔥).
  - نقاط الخبرة ومكافآت الدروس (XP Points ⭐).
  - نظام القلوب والطاقة (Hearts System ❤️) لمنع التخمين العشوائي مع إمكانية شحن القلوب.
  - أوسمة وإنجازات (Badges & Achievements).
* **قاموس المفردات والمفضلة (Vocabulary & Bookmarks):**
  - بحث لحظي وسريع في جميع الكلمات بالإنجليزية/الألمانية أو بالعربية.
  - حفظ الكلمات الصعبة في المفضلة لمراجعتها لاحقاً.
* **تصميم عصري متكيف بالكامل (Jetpack Compose Material 3):**
  - دعم كامل للـ Dark Mode و Light Mode.
  - حواف ناعمة وألوان جذابة وتجربة Edge-to-Edge سلسة للغاية.

---

### 2. لوحة تحكم الويب (Admin Web Dashboard)
- مسار الملف: `admin-panel/index.html`
- تعمل على أي متصفح مباشرة، أو يمكن رفعها مجاناً على (Firebase Hosting أو Vercel أو Netlify).
- **الميزات:**
  - إحصائيات عامة (عدد اللغات، الوحدات، الكلمات، الكويزات).
  - إدارة المناهج: إضافة وتعديل وحذف الوحدات والمستويات (A1 / A2 / B1).
  - إدارة الدروس والكلمات.
  - تصدير فوري لبيانات المنهج بصيغة **JSON** لتحديث التطبيق بنقرة واحدة.

---

## 🛠️ البنية البرمجية (Clean Architecture)
```
com.linguamaster.app/
├── audio/
│   └── TtsManager.kt               # محرك النطق الصوتي التفاعلي
├── data/
│   ├── model/
│   │   └── CurriculumModels.kt     # نماذج البيانات واللغات والتمارين
│   ├── repository/
│   │   └── CurriculumRepository.kt # مستودع البيانات وحفظ الإحصائيات محلياً
│   └── CurriculumDataSource.kt     # بنك البيانات المنهجي للإنجليزية والألمانية
├── ui/
│   ├── components/
│   │   ├── TopBarHeader.kt         # شريط الحالة العلوي (اللغات، XP، القلوب، Streak)
│   │   ├── BottomNavBar.kt         # شريط التنقل السفلي (تعلم، تمرين، مفردات، حسابي)
│   │   └── LanguagePickerSheet.kt  # نافذة التبديل بين اللغات
│   ├── screens/
│   │   ├── home/HomeScreen.kt      # شاشة الوحدات ومسار الدروس
│   │   ├── lesson/LessonScreen.kt  # شاشة الدرس التفاعلي بجميع أنواع الكويزات
│   │   ├── practice/PracticeScreen.kt # شاشة التمارين والتحديات السريعة
│   │   ├── vocabulary/VocabularyScreen.kt # شاشة القاموس والبحث والمفضلة
│   │   └── profile/ProfileScreen.kt   # شاشة الملف الشخصي والإحصائيات والأوسمة
│   └── theme/                      # ثيمات وألوان وأبعاد Material 3
├── MainActivity.kt
└── LinguaApp.kt
```
