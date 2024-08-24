# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.
cmake_minimum_required(VERSION 3.30.0)

project(ApiKeysProject)

set(CMAKE_CXX_STANDARD 11)

# Specify the directories where to find the includes and sources
include_directories(${CMAKE_SOURCE_DIR}/src/main/cpp)

# Creates and names a library, sets it as either STATIC
# or SHARED, and provides the relative paths to its source code.
# You can define multiple libraries, and CMake builds them for you.
# Gradle automatically packages shared libraries with your APK.
add_library(
        # Sets the name of the library.
        api-keys

        # Sets the library as a shared library.
        SHARED

        # Provides a relative path to your source file(s).
        src/main/cpp/api-keys.cpp
)

# Searches for a specified prebuilt library and stores the path as a
# variable. Because CMake includes system libraries in the search path by
# default, you only need to specify the name of the public NDK library
# you want to add. CMake verifies that the library exists before
# completing its build.
# For Android, find the log library
if(ANDROID)
    find_library(log-lib log)
else()
    set(log-lib "")
endif()

# Ensure log-lib is defined (skip this for non-Android targets)
if(ANDROID AND NOT log-lib)
    message(FATAL_ERROR "log-lib not found")
endif()

# Specifies libraries CMake should link to your target library. You
# can link multiple libraries, such as libraries you define in this
# build script, prebuilt third-party libraries, or system libraries.
target_link_libraries(
        # Specifies the target library.
        api-keys

        # Links the target library to the log library
        # included in the NDK.
        ${log-lib}
)
