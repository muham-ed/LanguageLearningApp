package com.linguamaster.app.data

import com.linguamaster.app.data.model.AppLanguage
import com.linguamaster.app.data.model.CourseUnit
import com.linguamaster.app.data.model.ExerciseItem
import com.linguamaster.app.data.model.ExerciseType
import com.linguamaster.app.data.model.Lesson
import com.linguamaster.app.data.model.VocabularyItem

object CurriculumDataSource {

    fun getUnitsForLanguage(language: AppLanguage): List<CourseUnit> {
        return when (language) {
            AppLanguage.ENGLISH -> englishUnits
            AppLanguage.GERMAN -> germanUnits
        }
    }

    fun getAllVocabularies(language: AppLanguage): List<VocabularyItem> {
        return getUnitsForLanguage(language).flatMap { unit ->
            unit.lessons.flatMap { it.vocabularies }
        }
    }

    // ==========================================
    // ENGLISH CURRICULUM — LEVEL A1 & A2 (SPEAK WITH CONFIDENCE)
    // ==========================================
    private val englishUnits = listOf(
        CourseUnit(
            id = "en_unit_1",
            languageCode = "en",
            level = "A1",
            order = 1,
            titleAr = "الوحدة A1 — مبتدئ (Zero to Hello)",
            titleOriginal = "A1 — Greetings & Daily Life",
            description = "من الصفر إلى التعامل بثقة في المطار، الفندق، المطعم، والمواصلات",
            iconEmoji = "🎯",
            lessons = listOf(
                Lesson(
                    id = "en_l1_1",
                    unitId = "en_unit_1",
                    order = 1,
                    titleAr = "الدرس 1: التحيات والتعريف بالنفس",
                    titleOriginal = "Greetings & Self-Introduction",
                    xpReward = 20,
                    vocabularies = listOf(
                        VocabularyItem(
                            id = "v_en_1_1",
                            lessonId = "en_l1_1",
                            languageCode = "en",
                            wordOriginal = "Hello / Hi",
                            wordArabic = "مرحباً / أهلاً (هيلو / هاي)",
                            phonetic = "/həˈloʊ/",
                            exampleOriginal = "Hello! What's your name?",
                            exampleArabic = "مرحباً! ما اسمك؟"
                        ),
                        VocabularyItem(
                            id = "v_en_1_2",
                            lessonId = "en_l1_1",
                            languageCode = "en",
                            wordOriginal = "My name is...",
                            wordArabic = "اسمي هو... (ماي نيم إز)",
                            phonetic = "/maɪ neɪm ɪz/",
                            exampleOriginal = "Hello! My name is Ahmed.",
                            exampleArabic = "أهلاً! اسمي هو أحمد."
                        ),
                        VocabularyItem(
                            id = "v_en_1_3",
                            lessonId = "en_l1_1",
                            languageCode = "en",
                            wordOriginal = "I am from...",
                            wordArabic = "أنا من... (آي آم فروم)",
                            phonetic = "/aɪ æm frɒm/",
                            exampleOriginal = "I am from Egypt.",
                            exampleArabic = "أنا من مصر."
                        ),
                        VocabularyItem(
                            id = "v_en_1_4",
                            lessonId = "en_l1_1",
                            languageCode = "en",
                            wordOriginal = "How old are you?",
                            wordArabic = "كم عمرك؟ (هاو أولد آر يو)",
                            phonetic = "/haʊ oʊld ɑːr juː/",
                            exampleOriginal = "How old are you? I am 25 years old.",
                            exampleArabic = "كم عمرك؟ عمري 25 سنة."
                        ),
                        VocabularyItem(
                            id = "v_en_1_5",
                            lessonId = "en_l1_1",
                            languageCode = "en",
                            wordOriginal = "Nice to meet you!",
                            wordArabic = "سعيد بلقائك! (نايس تو ميت يو)",
                            phonetic = "/naɪs tuː miːt juː/",
                            exampleOriginal = "Nice to meet you, Ahmed!",
                            exampleArabic = "سعيد بلقائك يا أحمد!"
                        ),
                        VocabularyItem(
                            id = "v_en_1_6",
                            lessonId = "en_l1_1",
                            languageCode = "en",
                            wordOriginal = "Thank you / Goodbye",
                            wordArabic = "شكراً / مع السلامة (ثانك يو / غود باي)",
                            phonetic = "/θæŋk juː / ɡʊdˈbaɪ/",
                            exampleOriginal = "Thank you! Goodbye!",
                            exampleArabic = "شكراً لك! مع السلامة!"
                        )
                    ),
                    exercises = listOf(
                        ExerciseItem(
                            id = "ex_en_1_1",
                            lessonId = "en_l1_1",
                            type = ExerciseType.MULTIPLE_CHOICE,
                            prompt = "اختر الكلمة المناسبة: ___ name is Layla.",
                            targetPhrase = "My name is Layla",
                            correctAnswer = "My",
                            options = listOf("My", "I", "Am", "Is")
                        ),
                        ExerciseItem(
                            id = "ex_en_1_2",
                            lessonId = "en_l1_1",
                            type = ExerciseType.SENTENCE_BUILDER,
                            prompt = "رتّب الكلمات الإنجليزية لتكوين: 'أنا من مصر'",
                            targetPhrase = "I am from Egypt",
                            correctAnswer = "I am from Egypt",
                            options = listOf("Egypt", "am", "from", "I", "is", "my")
                        ),
                        ExerciseItem(
                            id = "ex_en_1_3",
                            lessonId = "en_l1_1",
                            type = ExerciseType.LISTENING,
                            prompt = "استمع للجملة واختر معناها بالعربية:",
                            targetPhrase = "Nice to meet you!",
                            correctAnswer = "سعيد بلقائك!",
                            options = listOf("ما اسمك؟", "سعيد بلقائك!", "كم عمرك؟", "من أين أنت؟")
                        ),
                        ExerciseItem(
                            id = "ex_en_1_4",
                            lessonId = "en_l1_1",
                            type = ExerciseType.MATCHING_PAIRS,
                            prompt = "وصّل العبارة بالترجمة الصحيحة:",
                            targetPhrase = "Greetings Matching",
                            correctAnswer = "Done",
                            matchingPairs = mapOf(
                                "Hello" to "مرحباً",
                                "My name is" to "اسمي هو",
                                "I am from" to "أنا من",
                                "Goodbye" to "مع السلامة"
                            )
                        )
                    )
                ),
                Lesson(
                    id = "en_l1_2",
                    unitId = "en_unit_1",
                    order = 2,
                    titleAr = "الدرس 2: الأرقام والألوان",
                    titleOriginal = "Numbers & Colours",
                    xpReward = 20,
                    vocabularies = listOf(
                        VocabularyItem(
                            id = "v_en_2_1",
                            lessonId = "en_l1_2",
                            languageCode = "en",
                            wordOriginal = "One, Two, Three, Four, Five",
                            wordArabic = "1، 2، 3، 4، 5 (وان، تو، ثري، فور، فايف)",
                            phonetic = "/wʌn, tuː, θriː, fɔːr, faɪv/",
                            exampleOriginal = "I have three books.",
                            exampleArabic = "لدي ثلاثة كتب."
                        ),
                        VocabularyItem(
                            id = "v_en_2_2",
                            lessonId = "en_l1_2",
                            languageCode = "en",
                            wordOriginal = "Ten, Twenty, Thirty, Forty, Fifty, One Hundred",
                            wordArabic = "10، 20، 30، 40، 50، 100",
                            phonetic = "/tɛn, ˈtwɛnti, ˈθɜːrti, ˈfɔːrti, ˈfɪfti, wʌn ˈhʌndrəd/",
                            exampleOriginal = "Gate forty-eight (48).",
                            exampleArabic = "بوابة ثمانية وأربعون."
                        ),
                        VocabularyItem(
                            id = "v_en_2_3",
                            lessonId = "en_l1_2",
                            languageCode = "en",
                            wordOriginal = "Red / Blue / Green / Yellow",
                            wordArabic = "أحمر / أزرق / أخضر / أصفر",
                            phonetic = "/rɛd, bluː, ɡriːn, ˈjɛloʊ/",
                            exampleOriginal = "The sky is blue.",
                            exampleArabic = "السماء زرقاء."
                        ),
                        VocabularyItem(
                            id = "v_en_2_4",
                            lessonId = "en_l1_2",
                            languageCode = "en",
                            wordOriginal = "Black / White / Brown / Pink / Grey",
                            wordArabic = "أسود / أبيض / بني / وردي / رمادي",
                            phonetic = "/blæk, waɪt, braʊn, pɪŋk, ɡreɪ/",
                            exampleOriginal = "I have a black bag.",
                            exampleArabic = "لدي حقيبة سوداء."
                        )
                    ),
                    exercises = listOf(
                        ExerciseItem(
                            id = "ex_en_2_1",
                            lessonId = "en_l1_2",
                            type = ExerciseType.MULTIPLE_CHOICE,
                            prompt = "اختر اللون المناسب: The sky is ___",
                            targetPhrase = "blue",
                            correctAnswer = "blue",
                            options = listOf("blue", "green", "red", "yellow")
                        ),
                        ExerciseItem(
                            id = "ex_en_2_2",
                            lessonId = "en_l1_2",
                            type = ExerciseType.SENTENCE_BUILDER,
                            prompt = "اكتب الرقم 47 بالإنجليزية (forty-seven)",
                            targetPhrase = "forty-seven",
                            correctAnswer = "forty-seven",
                            options = listOf("forty", "seven", "-", "fourty", "eight")
                        ),
                        ExerciseItem(
                            id = "ex_en_2_3",
                            lessonId = "en_l1_2",
                            type = ExerciseType.MATCHING_PAIRS,
                            prompt = "وصّل الألوان بترجمتها العربية:",
                            targetPhrase = "Colours Matching",
                            correctAnswer = "Done",
                            matchingPairs = mapOf(
                                "Red" to "أحمر",
                                "Green" to "أخضر",
                                "Black" to "أسود",
                                "White" to "أبيض"
                            )
                        )
                    )
                ),
                Lesson(
                    id = "en_l1_3",
                    unitId = "en_unit_1",
                    order = 3,
                    titleAr = "الدرس 3: أسرتي",
                    titleOriginal = "My Family",
                    xpReward = 20,
                    vocabularies = listOf(
                        VocabularyItem(
                            id = "v_en_3_1",
                            lessonId = "en_l1_3",
                            languageCode = "en",
                            wordOriginal = "Father / Mother",
                            wordArabic = "الأب / الأم (فاذر / ماذر)",
                            phonetic = "/ˈfɑːðər / ˈmʌðər/",
                            exampleOriginal = "This is my mother.",
                            exampleArabic = "هذه هي أمي."
                        ),
                        VocabularyItem(
                            id = "v_en_3_2",
                            lessonId = "en_l1_3",
                            languageCode = "en",
                            wordOriginal = "Brother / Sister",
                            wordArabic = "الأخ / الأخت (براذر / سيستر)",
                            phonetic = "/ˈbrʌðər / ˈsɪstər/",
                            exampleOriginal = "I have two brothers.",
                            exampleArabic = "لدي أخوان."
                        ),
                        VocabularyItem(
                            id = "v_en_3_3",
                            lessonId = "en_l1_3",
                            languageCode = "en",
                            wordOriginal = "Grandfather / Grandmother",
                            wordArabic = "الجد / الجدة",
                            phonetic = "/ˈɡrændfɑːðər / ˈɡrændmʌðər/",
                            exampleOriginal = "My father's mother is my grandmother.",
                            exampleArabic = "أم أبي هي جدتي."
                        ),
                        VocabularyItem(
                            id = "v_en_3_4",
                            lessonId = "en_l1_3",
                            languageCode = "en",
                            wordOriginal = "We are ___ in my family.",
                            wordArabic = "نحن ___ في عائلتنا.",
                            phonetic = "/wiː ɑːr ... ɪn maɪ ˈfæməli/",
                            exampleOriginal = "We are five in my family.",
                            exampleArabic = "نحن خمسة في عائلتنا."
                        )
                    ),
                    exercises = listOf(
                        ExerciseItem(
                            id = "ex_en_3_1",
                            lessonId = "en_l1_3",
                            type = ExerciseType.MULTIPLE_CHOICE,
                            prompt = "My father's mother is my ___",
                            targetPhrase = "grandmother",
                            correctAnswer = "grandmother",
                            options = listOf("aunt", "grandmother", "sister", "mother")
                        ),
                        ExerciseItem(
                            id = "ex_en_3_2",
                            lessonId = "en_l1_3",
                            type = ExerciseType.SENTENCE_BUILDER,
                            prompt = "رتّب: 'لدي أخوان' (I have two brothers)",
                            targetPhrase = "I have two brothers",
                            correctAnswer = "I have two brothers",
                            options = listOf("two", "I", "brothers", "have", "sister")
                        )
                    )
                ),
                Lesson(
                    id = "en_l1_4",
                    unitId = "en_unit_1",
                    order = 4,
                    titleAr = "الدرس 4: في المطار",
                    titleOriginal = "At the Airport",
                    xpReward = 25,
                    vocabularies = listOf(
                        VocabularyItem(
                            id = "v_en_4_1",
                            lessonId = "en_l1_4",
                            languageCode = "en",
                            wordOriginal = "Passport",
                            wordArabic = "جواز السفر (باسبورت)",
                            phonetic = "/ˈpæspɔːrt/",
                            exampleOriginal = "Here is my passport and boarding pass.",
                            exampleArabic = "تفضل جواز سفري وتذكرة الصعود."
                        ),
                        VocabularyItem(
                            id = "v_en_4_2",
                            lessonId = "en_l1_4",
                            languageCode = "en",
                            wordOriginal = "Boarding pass",
                            wordArabic = "تذكرة الصعود للطائرة (بوردينج باس)",
                            phonetic = "/ˈbɔːrdɪŋ pæs/",
                            exampleOriginal = "Show your boarding pass at the gate.",
                            exampleArabic = "أظهر تذكرة الصعود عند البوابة."
                        ),
                        VocabularyItem(
                            id = "v_en_4_3",
                            lessonId = "en_l1_4",
                            languageCode = "en",
                            wordOriginal = "Gate number ___",
                            wordArabic = "بوابة رقم ___ (جيت نمبر)",
                            phonetic = "/ɡeɪt ˈnʌmbər/",
                            exampleOriginal = "Where is gate number 14?",
                            exampleArabic = "أين بوابة رقم 14؟"
                        ),
                        VocabularyItem(
                            id = "v_en_4_4",
                            lessonId = "en_l1_4",
                            languageCode = "en",
                            wordOriginal = "Departure / Arrival",
                            wordArabic = "المغادرة / الوصول (ديبارتشر / أرايفال)",
                            phonetic = "/dɪˈpɑːrtʃər / əˈraɪvl/",
                            exampleOriginal = "Departure is at 10 AM.",
                            exampleArabic = "المغادرة الساعة 10 صباحاً."
                        )
                    ),
                    exercises = listOf(
                        ExerciseItem(
                            id = "ex_en_4_1",
                            lessonId = "en_l1_4",
                            type = ExerciseType.MULTIPLE_CHOICE,
                            prompt = "ماذا تعني كلمة 'Boarding pass'؟",
                            targetPhrase = "Boarding pass",
                            correctAnswer = "تذكرة الصعود للطائرة",
                            options = listOf("جواز السفر", "تذكرة الصعود للطائرة", "حقيبة السفر", "بوابة المغادرة")
                        ),
                        ExerciseItem(
                            id = "ex_en_4_2",
                            lessonId = "en_l1_4",
                            type = ExerciseType.SENTENCE_BUILDER,
                            prompt = "رتّب: 'Where is gate number 14?'",
                            targetPhrase = "Where is gate number 14?",
                            correctAnswer = "Where is gate number 14?",
                            options = listOf("gate", "Where", "14?", "number", "is")
                        )
                    )
                ),
                Lesson(
                    id = "en_l1_5",
                    unitId = "en_unit_1",
                    order = 5,
                    titleAr = "الدرس 5: في الفندق",
                    titleOriginal = "At the Hotel",
                    xpReward = 25,
                    vocabularies = listOf(
                        VocabularyItem(
                            id = "v_en_5_1",
                            lessonId = "en_l1_5",
                            languageCode = "en",
                            wordOriginal = "Reception / Reservation",
                            wordArabic = "الاستقبال / حجز (ريسيبشن / ريزرفيشن)",
                            phonetic = "/rɪˈsɛpʃn / ˌrɛzərˈveɪʃn/",
                            exampleOriginal = "I have a reservation. My name is Ahmed.",
                            exampleArabic = "عندي حجز باسم أحمد."
                        ),
                        VocabularyItem(
                            id = "v_en_5_2",
                            lessonId = "en_l1_5",
                            languageCode = "en",
                            wordOriginal = "Wi-Fi password",
                            wordArabic = "كلمة سر الواي فاي",
                            phonetic = "/ˈwaɪ faɪ ˈpæswɜːrd/",
                            exampleOriginal = "What is the Wi-Fi password?",
                            exampleArabic = "ما هي كلمة سر الواي فاي؟"
                        )
                    ),
                    exercises = listOf(
                        ExerciseItem(
                            id = "ex_en_5_1",
                            lessonId = "en_l1_5",
                            type = ExerciseType.MULTIPLE_CHOICE,
                            prompt = "كيف تطلب كلمة سر النت؟",
                            targetPhrase = "What is the Wi-Fi password?",
                            correctAnswer = "What is the Wi-Fi password?",
                            options = listOf("What is the Wi-Fi password?", "Where is the room?", "How much is breakfast?", "Check-out please")
                        )
                    )
                ),
                Lesson(
                    id = "en_l1_6",
                    unitId = "en_unit_1",
                    order = 6,
                    titleAr = "الدرس 6: في المطعم",
                    titleOriginal = "At the Restaurant",
                    xpReward = 25,
                    vocabularies = listOf(
                        VocabularyItem(
                            id = "v_en_6_1",
                            lessonId = "en_l1_6",
                            languageCode = "en",
                            wordOriginal = "Menu / Order",
                            wordArabic = "قائمة الطعام / طلب",
                            phonetic = "/ˈmɛnjuː / ˈɔːrdər/",
                            exampleOriginal = "Can I see the menu, please?",
                            exampleArabic = "ممكن قائمة الطعام من فضلك؟"
                        ),
                        VocabularyItem(
                            id = "v_en_6_2",
                            lessonId = "en_l1_6",
                            languageCode = "en",
                            wordOriginal = "The bill / Check",
                            wordArabic = "الحساب / الفاتورة",
                            phonetic = "/ðə bɪl / tʃɛk/",
                            exampleOriginal = "Can I have the bill, please?",
                            exampleArabic = "ممكن الحساب من فضلك؟"
                        )
                    ),
                    exercises = listOf(
                        ExerciseItem(
                            id = "ex_en_6_1",
                            lessonId = "en_l1_6",
                            type = ExerciseType.MULTIPLE_CHOICE,
                            prompt = "كيف تطلب الفاتورة في المطعم؟",
                            targetPhrase = "Can I have the bill, please?",
                            correctAnswer = "Can I have the bill, please?",
                            options = listOf("Can I have the bill, please?", "Where is the menu?", "Table for one", "I am allergic")
                        )
                    )
                ),
                Lesson(
                    id = "en_l1_7",
                    unitId = "en_unit_1",
                    order = 7,
                    titleAr = "الدرس 7: المواصلات والاتجاهات",
                    titleOriginal = "Transport & Directions",
                    xpReward = 25,
                    vocabularies = listOf(
                        VocabularyItem(
                            id = "v_en_7_1",
                            lessonId = "en_l1_7",
                            languageCode = "en",
                            wordOriginal = "Taxi / Cab / Metro",
                            wordArabic = "تاكسي / مترو الأنفاق",
                            phonetic = "/ˈtæksi / kæb / ˈmɛtroʊ/",
                            exampleOriginal = "Take me to the airport, please.",
                            exampleArabic = "خذني إلى المطار من فضلك."
                        ),
                        VocabularyItem(
                            id = "v_en_7_2",
                            lessonId = "en_l1_7",
                            languageCode = "en",
                            wordOriginal = "How much is the fare?",
                            wordArabic = "بكام الأجرة؟",
                            phonetic = "/haʊ mʌtʃ ɪz ðə fɛər/",
                            exampleOriginal = "How much is the fare to the hotel?",
                            exampleArabic = "كم أجرة التوصيل للفندق؟"
                        )
                    ),
                    exercises = listOf(
                        ExerciseItem(
                            id = "ex_en_7_1",
                            lessonId = "en_l1_7",
                            type = ExerciseType.MULTIPLE_CHOICE,
                            prompt = "ماذا يعني سؤال: 'How much is the fare?'",
                            targetPhrase = "How much is the fare?",
                            correctAnswer = "بكام الأجرة؟",
                            options = listOf("بكام الأجرة؟", "أين المحطة؟", "كم الوقت المتبقي؟", "كيف أذهب للمطار؟")
                        )
                    )
                ),
                Lesson(
                    id = "en_l1_8",
                    unitId = "en_unit_1",
                    order = 8,
                    titleAr = "الدرس 8: مراجعة شاملة واختبار مستوى A1",
                    titleOriginal = "Review & Final Test (A1)",
                    xpReward = 50,
                    vocabularies = listOf(
                        VocabularyItem(
                            id = "v_en_8_1",
                            lessonId = "en_l1_8",
                            languageCode = "en",
                            wordOriginal = "Boarding pass",
                            wordArabic = "بطاقة الصعود للطائرة",
                            phonetic = "/ˈbɔːrdɪŋ pæs/",
                            exampleOriginal = "Show your boarding pass at gate 14.",
                            exampleArabic = "أظهر بطاقة الصعود عند البوابة 14."
                        )
                    ),
                    exercises = listOf(
                        ExerciseItem(
                            id = "ex_en_8_1",
                            lessonId = "en_l1_8",
                            type = ExerciseType.MULTIPLE_CHOICE,
                            prompt = "اختر الجملة الصحيحة لحجز غرفة بالفندق:",
                            targetPhrase = "I have a reservation",
                            correctAnswer = "I have a reservation. My name is Sara.",
                            options = listOf(
                                "I have a reservation. My name is Sara.",
                                "Take me to hotel please.",
                                "Where is the menu?",
                                "What is the fare?"
                            )
                        )
                    )
                )
            )
        )
    )

    // ==========================================
    // GERMAN CURRICULUM — LEVEL A1 (DEUTSCH A1 COMPLETE)
    // ==========================================
    private val germanUnits = listOf(
        CourseUnit(
            id = "de_unit_1",
            languageCode = "de",
            level = "A1",
            order = 1,
            titleAr = "كورس اللغة الألمانية المستوى الأول (DEUTSCH A1)",
            titleOriginal = "Deutsch A1 — Komplettkurs",
            description = "من الصفر إلى التحدث بالألمانية بثقة: الحروف، التحيات، الضمائر، الأعداد، الأفعال، وحوارات الحياة اليومية",
            iconEmoji = "🇩🇪",
            lessons = listOf(
                // ----------------------------------------------------
                // FAPITEL 1: الحروف والنطق
                // ----------------------------------------------------
                Lesson(
                    id = "de_l1_1",
                    unitId = "de_unit_1",
                    order = 1,
                    titleAr = "الفصل 1: الحروف والنطق والأصوات الخاصة",
                    titleOriginal = "Kapitel 1: Das Alphabet & Aussprache",
                    xpReward = 20,
                    vocabularies = listOf(
                        VocabularyItem(
                            id = "v_de_1_1",
                            lessonId = "de_l1_1",
                            languageCode = "de",
                            wordOriginal = "der Apfel / das Buch",
                            wordArabic = "تفاحة (آبفل) / كتاب (بوخ)",
                            phonetic = "/ˈapfl̩ / buːx/",
                            exampleOriginal = "A = Apfel, B = Buch",
                            exampleArabic = "حرف A مثل Apfel وحرف B مثل Buch"
                        ),
                        VocabularyItem(
                            id = "v_de_1_2",
                            lessonId = "de_l1_1",
                            languageCode = "de",
                            wordOriginal = "Deutsch / das Wasser",
                            wordArabic = "اللغة الألمانية (دويتش) / ماء (ڤاسر)",
                            phonetic = "/dɔʏtʃ / ˈvasɐ/",
                            exampleOriginal = "Ich lerne Deutsch.",
                            exampleArabic = "أنا أتعلم اللغة الألمانية."
                        ),
                        VocabularyItem(
                            id = "v_de_1_3",
                            lessonId = "de_l1_1",
                            languageCode = "de",
                            wordOriginal = "die Straße / schön",
                            wordArabic = "شارع (شتراسه) / جميل (شين)",
                            phonetic = "/ˈʃtʁaːsə / ʃøːn/",
                            exampleOriginal = "Die Straße ist schön.",
                            exampleArabic = "الشارع جميل."
                        )
                    ),
                    exercises = listOf(
                        ExerciseItem(
                            id = "ex_de_1_1",
                            lessonId = "de_l1_1",
                            type = ExerciseType.MULTIPLE_CHOICE,
                            prompt = "كيف ينطق حرف W في اللغة الألمانية؟ (مثال: Wasser)",
                            targetPhrase = "Wasser",
                            correctAnswer = "ينطق مثل حرف ڤ (V)",
                            options = listOf("ينطق مثل حرف ڤ (V)", "ينطق مثل W الإنجليزية", "ينطق ف خفيفة", "لا ينطق")
                        ),
                        ExerciseItem(
                            id = "ex_de_1_2",
                            lessonId = "de_l1_1",
                            type = ExerciseType.MATCHING_PAIRS,
                            prompt = "وصّل التركيب الصوتي بالرمز الصحيح له:",
                            targetPhrase = "Phonics DE",
                            correctAnswer = "Done",
                            matchingPairs = mapOf(
                                "ei" to "آي (مثل mein)",
                                "ie" to "إي طويلة (مثل Bier)",
                                "eu" to "أوي (مثل heute)",
                                "sch" to "ش (مثل Schule)"
                            )
                        )
                    )
                ),

                // ----------------------------------------------------
                // KAPITEL 2: التحيات والتعارف
                // ----------------------------------------------------
                Lesson(
                    id = "de_l1_2",
                    unitId = "de_unit_1",
                    order = 2,
                    titleAr = "الفصل 2: التحيات والتعارف والمهن",
                    titleOriginal = "Kapitel 2: Begrüßung & Sich Vorstellen",
                    xpReward = 20,
                    vocabularies = listOf(
                        VocabularyItem(
                            id = "v_de_2_1",
                            lessonId = "de_l1_2",
                            languageCode = "de",
                            wordOriginal = "Guten Morgen / Guten Tag / Guten Abend",
                            wordArabic = "صباح الخير / نهارك سعيد / مساء الخير",
                            phonetic = "/ˌɡuːtn̩ ˈmɔʁɡn̩ / ˈtaːk / ˈaːbn̩t/",
                            exampleOriginal = "Guten Tag! Wie heißen Sie?",
                            exampleArabic = "نهارك سعيد! ما اسم حضرتك؟"
                        ),
                        VocabularyItem(
                            id = "v_de_2_2",
                            lessonId = "de_l1_2",
                            languageCode = "de",
                            wordOriginal = "Ich heiße...",
                            wordArabic = "اسمي هو... (إيخ هايسه)",
                            phonetic = "/ɪç ˈhaɪsə/",
                            exampleOriginal = "Ich heiße Mohamed.",
                            exampleArabic = "اسمي هو محمد."
                        ),
                        VocabularyItem(
                            id = "v_de_3_3",
                            lessonId = "de_l1_2",
                            languageCode = "de",
                            wordOriginal = "Ich komme aus Ägypten",
                            wordArabic = "أنا قادم من مصر (إيخ كومه أوس إجيبتن)",
                            phonetic = "/ɪç ˈkɔmə aʊs ɛˈɡʏptn̩/",
                            exampleOriginal = "Woher kommst du? Ich komme aus Ägypten.",
                            exampleArabic = "من أين أنت؟ أنا من مصر."
                        ),
                        VocabularyItem(
                            id = "v_de_2_4",
                            lessonId = "de_l1_2",
                            languageCode = "de",
                            wordOriginal = "Ich wohne in...",
                            wordArabic = "أسكن في... (إيخ فونه إن)",
                            phonetic = "/ɪç ˈvoːnə ɪn/",
                            exampleOriginal = "Ich wohne in Alexandria.",
                            exampleArabic = "أسكن في الإسكندرية."
                        )
                    ),
                    exercises = listOf(
                        ExerciseItem(
                            id = "ex_de_2_1",
                            lessonId = "de_l1_2",
                            type = ExerciseType.MULTIPLE_CHOICE,
                            prompt = "كيف تجيب على سؤال: 'Woher kommst du?'",
                            targetPhrase = "Ich komme aus Ägypten",
                            correctAnswer = "Ich komme aus Ägypten",
                            options = listOf("Ich komme aus Ägypten", "Ich heiße Mohamed", "Ich bin 23 Jahre alt", "Mir geht es gut")
                        ),
                        ExerciseItem(
                            id = "ex_de_2_2",
                            lessonId = "de_l1_2",
                            type = ExerciseType.SENTENCE_BUILDER,
                            prompt = "رتّب لتكوين 'أنا من مصر': (Ich komme aus Ägypten)",
                            targetPhrase = "Ich komme aus Ägypten",
                            correctAnswer = "Ich komme aus Ägypten",
                            options = listOf("Ägypten", "Ich", "aus", "komme", "wohne")
                        )
                    )
                ),

                // ----------------------------------------------------
                // KAPITEL 3: الضمائر وفعلا sein و haben
                // ----------------------------------------------------
                Lesson(
                    id = "de_l1_3",
                    unitId = "de_unit_1",
                    order = 3,
                    titleAr = "الفصل 3: الضمائر الشحصية وفعلا (sein / haben)",
                    titleOriginal = "Kapitel 3: Personalpronomen & Verben sein/haben",
                    xpReward = 20,
                    vocabularies = listOf(
                        VocabularyItem(
                            id = "v_de_3_1",
                            lessonId = "de_l1_3",
                            languageCode = "de",
                            wordOriginal = "ich bin / du bist / er ist / wir sind",
                            wordArabic = "أنا أكون / أنت تكون / هو يكون / نحن نكون",
                            phonetic = "/ɪç bɪn / duː bɪst / eːɐ̯ ɪst/",
                            exampleOriginal = "Ich bin Student. Er ist Arzt.",
                            exampleArabic = "أنا طالب. هو طبيب."
                        ),
                        VocabularyItem(
                            id = "v_de_3_2",
                            lessonId = "de_l1_3",
                            languageCode = "de",
                            wordOriginal = "ich habe / wir haben",
                            wordArabic = "عندي / لدينا",
                            phonetic = "/ɪç ˈhaːbə / viːɐ̯ ˈhaːbn̩/",
                            exampleOriginal = "Ich habe ein Buch. Wir haben Hunger.",
                            exampleArabic = "لدي كتاب. نحن جائعون."
                        )
                    ),
                    exercises = listOf(
                        ExerciseItem(
                            id = "ex_de_3_1",
                            lessonId = "de_l1_3",
                            type = ExerciseType.MULTIPLE_CHOICE,
                            prompt = "اختر التصريف الصحيح: Ich ___ Student.",
                            targetPhrase = "bin",
                            correctAnswer = "bin",
                            options = listOf("bin", "bist", "ist", "sind")
                        ),
                        ExerciseItem(
                            id = "ex_de_3_2",
                            lessonId = "de_l1_3",
                            type = ExerciseType.SENTENCE_BUILDER,
                            prompt = "رتّب: 'Er ist Arzt' (هو دكتور)",
                            targetPhrase = "Er ist Arzt",
                            correctAnswer = "Er ist Arzt",
                            options = listOf("Arzt", "Er", "ist", "bin")
                        )
                    )
                ),

                // ----------------------------------------------------
                // KAPITEL 4: الأعداد
                // ----------------------------------------------------
                Lesson(
                    id = "de_l1_4",
                    unitId = "de_unit_1",
                    order = 4,
                    titleAr = "الفصل 4: الأعداد والأرقام (Zahlen 0 - 1000)",
                    titleOriginal = "Kapitel 4: Die Zahlen 0-1000",
                    xpReward = 20,
                    vocabularies = listOf(
                        VocabularyItem(
                            id = "v_de_4_1",
                            lessonId = "de_l1_4",
                            languageCode = "de",
                            wordOriginal = "eins, zwei, drei, vier, fünf",
                            wordArabic = "1، 2، 3، 4، 5 (أينس، تسفاي، دراي، فير، فينف)",
                            phonetic = "/aɪns, tsvaɪ, dʁaɪ, fiːɐ̯, fyːnf/",
                            exampleOriginal = "Ich habe zwei Brüder.",
                            exampleArabic = "لدي أخوان."
                        ),
                        VocabularyItem(
                            id = "v_de_4_2",
                            lessonId = "de_l1_4",
                            languageCode = "de",
                            wordOriginal = "zehn, zwanzig, einundzwanzig, hundert",
                            wordArabic = "10، 20، 21 (واحد وعشرون)، 100 (مائة)",
                            phonetic = "/tseːn, ˈtsvantsɪç, ˈaɪn.ʊnt.tsvantsɪç, ˈhʊndɐt/",
                            exampleOriginal = "Es kostet zwanzig Euro.",
                            exampleArabic = "سعره عشرون يورو."
                        )
                    ),
                    exercises = listOf(
                        ExerciseItem(
                            id = "ex_de_4_1",
                            lessonId = "de_l1_4",
                            type = ExerciseType.MULTIPLE_CHOICE,
                            prompt = "كيف يكتب الرقم 21 بالألمانية؟ (الآحاد أولاً ثم und ثم العشرات)",
                            targetPhrase = "einundzwanzig",
                            correctAnswer = "einundzwanzig",
                            options = listOf("einundzwanzig", "zwanzigeins", "dreißig", "zehn")
                        )
                    )
                ),

                // ----------------------------------------------------
                // KAPITEL 5: أدوات التعريف والمفردات (العائلة، الأكل، البيت، الألوان)
                // ----------------------------------------------------
                Lesson(
                    id = "de_l1_5",
                    unitId = "de_unit_1",
                    order = 5,
                    titleAr = "الفصل 5: أدوات التعريف (der/die/das)، العائلة، الأكل والألوان",
                    titleOriginal = "Kapitel 5: Artikel, Familie & Wortschatz",
                    xpReward = 25,
                    vocabularies = listOf(
                        VocabularyItem(
                            id = "v_de_5_1",
                            lessonId = "de_l1_5",
                            languageCode = "de",
                            wordOriginal = "der Mann / die Frau / das Kind",
                            wordArabic = "الرجل (مذكر) / المرأة (مؤنث) / الطفل (محايد)",
                            phonetic = "/deːɐ̯ man / diː fʁaʊ / das kɪnt/",
                            exampleOriginal = "Der Mann und die Frau.",
                            exampleArabic = "الرجل والمرأة."
                        ),
                        VocabularyItem(
                            id = "v_de_5_2",
                            lessonId = "de_l1_5",
                            languageCode = "de",
                            wordOriginal = "das Brot / der Kaffee / das Wasser",
                            wordArabic = "الخبز / القهوة / الماء",
                            phonetic = "/das bʁoːt / deːɐ̯ ˈkafe / das ˈvasɐ/",
                            exampleOriginal = "Ich trinke Kaffee und esse Brot.",
                            exampleArabic = "أشرب القهوة وأكل الخبز."
                        ),
                        VocabularyItem(
                            id = "v_de_5_3",
                            lessonId = "de_l1_5",
                            languageCode = "de",
                            wordOriginal = "rot / blau / grün / gelb / weiß / schwarz",
                            wordArabic = "أحمر / أزرق / أخضر / أصفر / أبيض / أسود",
                            phonetic = "/ʁoːt, blaʊ, ɡʁyːn, ɡɛlp, vaɪs, ʃvaʁts/",
                            exampleOriginal = "Das Auto ist rot.",
                            exampleArabic = "السيارة حمراء."
                        )
                    ),
                    exercises = listOf(
                        ExerciseItem(
                            id = "ex_de_5_1",
                            lessonId = "de_l1_5",
                            type = ExerciseType.MULTIPLE_CHOICE,
                            prompt = "ما هي أداة التعريف لكلمة 'Frau' (مرأة)؟",
                            targetPhrase = "die Frau",
                            correctAnswer = "die",
                            options = listOf("die", "der", "das", "ein")
                        ),
                        ExerciseItem(
                            id = "ex_de_5_2",
                            lessonId = "de_l1_5",
                            type = ExerciseType.MATCHING_PAIRS,
                            prompt = "وصّل الكلمة بأداة تعريفها الصحيحة:",
                            targetPhrase = "Articles DE",
                            correctAnswer = "Done",
                            matchingPairs = mapOf(
                                "Mann" to "der",
                                "Frau" to "die",
                                "Kind" to "das",
                                "Kinder (الجمع)" to "die"
                            )
                        )
                    )
                ),

                // ----------------------------------------------------
                // KAPITEL 6: الأفعال والأفعال الناقصة
                // ----------------------------------------------------
                Lesson(
                    id = "de_l1_6",
                    unitId = "de_unit_1",
                    order = 6,
                    titleAr = "الفصل 6: تصريف الأفعال والأفعال الناقصة (Modalverben)",
                    titleOriginal = "Kapitel 6: Verben & Modalverben",
                    xpReward = 25,
                    vocabularies = listOf(
                        VocabularyItem(
                            id = "v_de_6_1",
                            lessonId = "de_l1_6",
                            languageCode = "de",
                            wordOriginal = "lernen / wohnen / kommen / trinken",
                            wordArabic = "يتعلم / يسكن / يأتي / يشرب",
                            phonetic = "/ˈlɛʁnən / ˈvoːnən / ˈkɔmən / ˈtʁɪŋkən/",
                            exampleOriginal = "Ich lerne Deutsch. Du wohnst in Berlin.",
                            exampleArabic = "أنا أتعلم الألمانية. أنت تسكن في برلين."
                        ),
                        VocabularyItem(
                            id = "v_de_6_2",
                            lessonId = "de_l1_6",
                            languageCode = "de",
                            wordOriginal = "können / müssen / wollen",
                            wordArabic = "يستطيع (يقدر) / يجب (لازم) / يريد",
                            phonetic = "/ˈkœnən / ˈmʏsən / ˈvɔlən/",
                            exampleOriginal = "Ich kann Arabisch sprechen. Ich will Deutsch lernen.",
                            exampleArabic = "أستطيع التحدث بالعربية. أريد تعلم الألمانية."
                        )
                    ),
                    exercises = listOf(
                        ExerciseItem(
                            id = "ex_de_6_1",
                            lessonId = "de_l1_6",
                            type = ExerciseType.SENTENCE_BUILDER,
                            prompt = "رتّب: 'أنا أريد تعلم الألمانية' (Ich will Deutsch lernen)",
                            targetPhrase = "Ich will Deutsch lernen",
                            correctAnswer = "Ich will Deutsch lernen",
                            options = listOf("Deutsch", "Ich", "lernen", "will", "kann")
                        )
                    )
                ),

                // ----------------------------------------------------
                // KAPITEL 7: الوقت والأيام والشهور
                // ----------------------------------------------------
                Lesson(
                    id = "de_l1_7",
                    unitId = "de_unit_1",
                    order = 7,
                    titleAr = "الفصل 7: الوقت، الساعة، الأيام، والشهور",
                    titleOriginal = "Kapitel 7: Zeit, Uhrzeit & Kalender",
                    xpReward = 25,
                    vocabularies = listOf(
                        VocabularyItem(
                            id = "v_de_7_1",
                            lessonId = "de_l1_7",
                            languageCode = "de",
                            wordOriginal = "Es ist ein Uhr / Es ist halb drei",
                            wordArabic = "الساعة الواحدة / الساعة الثانية والنص (نص الطريق للـ3)",
                            phonetic = "/ɛs ɪst aɪn uːɐ̯ / ˈhalp dʁaɪ/",
                            exampleOriginal = "Wie spät ist es? Es ist ein Uhr.",
                            exampleArabic = "كم الساعة؟ إنها الواحدة."
                        ),
                        VocabularyItem(
                            id = "v_de_7_2",
                            lessonId = "de_l1_7",
                            languageCode = "de",
                            wordOriginal = "Montag / Freitag / am Montag",
                            wordArabic = "الإثنين / الجمعة / يوم الإثنين",
                            phonetic = "/ˈmoːntaːk / ˈfʁaɪtaːk/",
                            exampleOriginal = "Am Montag habe ich Deutschkurs.",
                            exampleArabic = "يوم الإثنين لدي كورس ألماني."
                        )
                    ),
                    exercises = listOf(
                        ExerciseItem(
                            id = "ex_de_7_1",
                            lessonId = "de_l1_7",
                            type = ExerciseType.MULTIPLE_CHOICE,
                            prompt = "ماذا يعني التعبير 'am Montag'؟",
                            targetPhrase = "am Montag",
                            correctAnswer = "يوم الإثنين",
                            options = listOf("يوم الإثنين", "في شهر يناير", "في الصيف", "الساعة الواحدة")
                        )
                    )
                ),

                // ----------------------------------------------------
                // KAPITEL 8: الجملة والأسئلة والنفي
                // ----------------------------------------------------
                Lesson(
                    id = "de_l1_8",
                    unitId = "de_unit_1",
                    order = 8,
                    titleAr = "الفصل 8: تركيب الجملة، أدوات الاستفهام والنفي (nicht / kein)",
                    titleOriginal = "Kapitel 8: Satzbau, W-Fragen & Negation",
                    xpReward = 25,
                    vocabularies = listOf(
                        VocabularyItem(
                            id = "v_de_8_1",
                            lessonId = "de_l1_8",
                            languageCode = "de",
                            wordOriginal = "Wer? / Was? / Wo? / Woher? / Wann?",
                            wordArabic = "من؟ / ماذا؟ / أين؟ / من أين؟ / متى؟",
                            phonetic = "/veːɐ̯ / vas / voː / voˈheːɐ̯ / van/",
                            exampleOriginal = "Wo wohnst du? Was lernst du?",
                            exampleArabic = "أين تسكن؟ ماذا تتعلم؟"
                        ),
                        VocabularyItem(
                            id = "v_de_8_2",
                            lessonId = "de_l1_8",
                            languageCode = "de",
                            wordOriginal = "nicht / kein",
                            wordArabic = "nicht (نفي الصفة/الفعل) / kein (نفي الاسم)",
                            phonetic = "/nɪçt / kaɪn/",
                            exampleOriginal = "Ich lerne nicht. Ich habe kein Auto.",
                            exampleArabic = "أنا لا أدرس. ليس لدي سيارة."
                        )
                    ),
                    exercises = listOf(
                        ExerciseItem(
                            id = "ex_de_8_1",
                            lessonId = "en_l1_8",
                            type = ExerciseType.SENTENCE_BUILDER,
                            prompt = "رتب السؤال الذهبي: 'Wo wohnst du?' (أين تسكن؟)",
                            targetPhrase = "Wo wohnst du?",
                            correctAnswer = "Wo wohnst du?",
                            options = listOf("wohnst", "Wo", "du?", "bist")
                        )
                    )
                ),

                // ----------------------------------------------------
                // KAPITEL 9: حروف الجر
                // ----------------------------------------------------
                Lesson(
                    id = "de_l1_9",
                    unitId = "de_unit_1",
                    order = 9,
                    titleAr = "الفصل 9: حروف الجر (Präpositionen)",
                    titleOriginal = "Kapitel 9: Präpositionen (in, auf, aus, nach, mit)",
                    xpReward = 25,
                    vocabularies = listOf(
                        VocabularyItem(
                            id = "v_de_9_1",
                            lessonId = "de_l1_9",
                            languageCode = "de",
                            wordOriginal = "in / aus / nach / mit",
                            wordArabic = "in (في) / aus (من مصدر) / nach (إلى دولة/مدينة) / mit (مع/بـ)",
                            phonetic = "/ɪn / aʊs / naːx / mɪt/",
                            exampleOriginal = "Ich wohne in Cairo. Ich fahre nach Berlin mit dem Bus.",
                            exampleArabic = "أسكن في القاهرة. أسافر إلى برلين بالأتوبيس."
                        )
                    ),
                    exercises = listOf(
                        ExerciseItem(
                            id = "ex_de_9_1",
                            lessonId = "de_l1_9",
                            type = ExerciseType.MULTIPLE_CHOICE,
                            prompt = "اختر حرف الجر للسفر إلى دولة أو مدينة: Ich fahre ___ Berlin.",
                            targetPhrase = "nach",
                            correctAnswer = "nach",
                            options = listOf("nach", "aus", "bei", "mit")
                        )
                    )
                ),

                // ----------------------------------------------------
                // KAPITEL 10: عبارات الحياة اليومية والتسوق والمطعم
                // ----------------------------------------------------
                Lesson(
                    id = "de_l1_10",
                    unitId = "de_unit_1",
                    order = 10,
                    titleAr = "الفصل 10: حوارات التسوق، المطعم، والمواصلات",
                    titleOriginal = "Kapitel 10: Alltag, Einkaufen & Restaurant",
                    xpReward = 30,
                    vocabularies = listOf(
                        VocabularyItem(
                            id = "v_de_10_1",
                            lessonId = "de_l1_10",
                            languageCode = "de",
                            wordOriginal = "Entschuldigung! / Sprechen Sie Englisch?",
                            wordArabic = "معذرة! / هل تتحدث الإنجليزية؟",
                            phonetic = "/ɛntˈʃʊldɪɡʊŋ / ˈʃpʁɛçn̩ ziː ˈɛŋlɪʃ/",
                            exampleOriginal = "Entschuldigung, wo ist der Bahnhof?",
                            exampleArabic = "معذرة، أين محطة القطار؟"
                        ),
                        VocabularyItem(
                            id = "v_de_10_2",
                            lessonId = "de_l1_10",
                            languageCode = "de",
                            wordOriginal = "Wie viel kostet das? / Die Rechnung, bitte!",
                            wordArabic = "كم ثمن هذا؟ / الحساب من فضلك!",
                            phonetic = "/viː fiːl ˈkɔstət das / diː ˈʁɛçnʊŋ ˈbɪtə/",
                            exampleOriginal = "Wie viel kostet das? Die Rechnung, bitte!",
                            exampleArabic = "بكام ده؟ الحساب لو سمحت!"
                        )
                    ),
                    exercises = listOf(
                        ExerciseItem(
                            id = "ex_de_10_1",
                            lessonId = "de_l1_10",
                            type = ExerciseType.MULTIPLE_CHOICE,
                            prompt = "كيف تطلب الفاتورة والحساب بالألمانية في المطعم؟",
                            targetPhrase = "Die Rechnung, bitte!",
                            correctAnswer = "Die Rechnung, bitte!",
                            options = listOf("Die Rechnung, bitte!", "Wie viel kostet das?", "Sprechen Sie Englisch?", "Entschuldigung")
                        ),
                        ExerciseItem(
                            id = "ex_de_10_2",
                            lessonId = "de_l1_10",
                            type = ExerciseType.SENTENCE_BUILDER,
                            prompt = "رتّب: 'Die Rechnung, bitte!' (الحساب لو سمحت)",
                            targetPhrase = "Die Rechnung, bitte!",
                            correctAnswer = "Die Rechnung, bitte!",
                            options = listOf("bitte!", "Die", "Rechnung,", "Kaffee")
                        )
                    )
                )
            )
        )
    )
}
