# WodWise - Your AI-Powered Workout Assistant
WodWiseApp is designed to enhance your fitness experience by integrating artificial intelligence and personalized data management for your workouts and performance. With a focus on simplicity and effectiveness, the app allows you to create custom workouts, track and manage your training data, and monitor your maximum weights for key exercises.

Key Features:
- AI-Powered Workout Creation: The app uses artificial intelligence to generate personalized workout routines based on your input. You can choose the type of workout, set the duration, and select exercises from a database of over 100 exercises, giving you full control over your training.

- Voice-Based Workout Creation: Simplify your routine-building process even further by using voice commands. Our AI converts your spoken instructions into structured workout routines, making it easier to log your training.

- Workout Management: Save your workouts, whether they’re AI-generated or manually created, in an integrated database. This enables you to keep an organized and accessible record of all your routines.

- Track Your One-Rep Max (1RM): Keep detailed records of your maximum weights in specific exercises and receive automatic recommendations on how many reps you can perform at different percentages of your 1RM. This helps you adjust your training for optimal progress.

Integrated Databases:
- Workout Database: Store all your workouts—created by the AI or manually—allowing you to review, edit, or reuse them at any time.

- 1RM Database: Record your maximum weights for key exercises. The app will provide you with percentages of those weights and indicate how many reps you can perform based on different percentages of your 1RM, helping you track progress safely and effectively.

WodWiseApp is built to be a versatile tool that accelerates your progress in the gym, combining cutting-edge technology and personalized data storage to provide a complete and accessible training experience.

## System requirements

- JDK 17
- Android NDK
- CMake 3.30.0
- Android Studio 4.0 or higher

## Installation

1. **Clone the repository**:
    ```bash
    git clone https://github.com/MiguelCMayo/WodWiseApp.git
    cd your_project
    ```

2. **Configure `local.properties`**:
    - Create a `local.properties` file at the root of the project if it doesn't exist.

## API key configuration

- To securely handle API keys this app uses CMake. To install the app, you need the `api-keys.cpp` file, which is not included in version control.
- Ensure `api-keys.cpp` is correctly located in the `src/main/cpp` directory or the appropriate JNI directory.

### File `api-keys.cpp`

    ```cpp
    #include <jni.h>
    #include <string>

    extern "C" JNIEXPORT jstring JNICALL
    Java_com_migueldev_wodwiseapp_ApiKeyRetriever_getOpenAIApiKey(JNIEnv* env, jobject /* this */) {
    std::string apiKey = "api-key";
    return env->NewStringUTF(apiKey.c_str());
    }

## Dependencies used

- MVVM with coroutines
- Clean Architecture
- Firebase
- Arrow
- Retrofit
- MockK
- Kluent
- AndroidX
- Jetpack Compose
- Dagger Hilt
- OpenAI Client
