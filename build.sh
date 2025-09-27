#!/bin/bash

# CloudStream Google Drive Extension Build Script
echo "Building CloudStream Google Drive Extension..."

# Clean previous builds
echo "Cleaning previous builds..."
./gradlew clean

# Build debug version
echo "Building debug version..."
./gradlew assembleDebug

# Build release version
echo "Building release version..."
./gradlew assembleRelease

# Check if build was successful
if [ $? -eq 0 ]; then
    echo "✅ Build completed successfully!"
    echo "📱 Debug APK: app/build/outputs/apk/debug/app-debug.apk"
    echo "📱 Release APK: app/build/outputs/apk/release/app-release.apk"
    echo ""
    echo "📋 Installation Instructions:"
    echo "1. Copy the APK to your Android device"
    echo "2. Enable 'Install from Unknown Sources' in Android settings"
    echo "3. Install the APK"
    echo "4. Open CloudStream app"
    echo "5. Go to Settings > Extensions"
    echo "6. Enable the Google Drive extension"
    echo "7. Authenticate with your Google account"
    echo ""
    echo "🔑 API Key: AIzaSyAbaRKmjHu-A73tdhEXRatmI5WWk-GOwRI"
    echo "📖 See README.md for detailed usage instructions"
else
    echo "❌ Build failed! Check the error messages above."
    exit 1
fi
