# 📱 How to Use the CloudStream Google Drive Extension

## 🚀 **Direct Usage (No Publishing Required)**

You can use this extension directly on your Android device without publishing it anywhere!

### **Method 1: As a CloudStream Extension**

1. **Build the Extension**:
   ```bash
   # If you have Android Studio or Gradle installed:
   ./gradlew assembleRelease
   
   # Or use Android Studio:
   # Open project in Android Studio → Build → Generate Signed Bundle/APK
   ```

2. **Install on Your Phone**:
   - Copy the generated APK to your Android device
   - Enable "Install from Unknown Sources" in Android Settings
   - Install the APK

3. **Configure in CloudStream**:
   - Open CloudStream app
   - Go to Settings → Extensions
   - Find "Google Drive" extension
   - Enable it
   - Tap "Authenticate with Google"
   - Sign in with your Google account

4. **Start Streaming**:
   - Your Google Drive videos will appear in CloudStream
   - Tap any video to start streaming
   - Use search to find specific videos

### **Method 2: As a Standalone App**

The extension can also work as a standalone video streaming app:

1. **Install the APK** directly on your device
2. **Open the app** from your app drawer
3. **Authenticate** with Google Drive
4. **Browse and stream** your videos directly

## 🔧 **Installation Methods**

### **Option A: Android Studio (Recommended)**
1. Open the project in Android Studio
2. Build → Generate Signed Bundle/APK
3. Choose APK → Release
4. Install on your device

### **Option B: Command Line**
```bash
# If you have Gradle installed:
./gradlew assembleRelease

# The APK will be in:
# app/build/outputs/apk/release/app-release.apk
```

### **Option C: GitHub Actions (Advanced)**
You can set up automated builds using GitHub Actions to automatically build APKs.

## 📋 **Requirements**

- **Android Device**: 5.0+ (API 21+)
- **CloudStream App**: Latest version
- **Google Account**: With Drive access
- **Internet Connection**: For streaming

## 🎯 **Features Available**

- ✅ **Video Streaming**: Stream videos directly from Google Drive
- ✅ **Search**: Search through your Google Drive videos
- ✅ **Authentication**: Secure OAuth2 integration
- ✅ **Modern UI**: Material Design 3 interface
- ✅ **Offline Support**: Videos cached for offline viewing
- ✅ **Quality Selection**: Choose video quality
- ✅ **Full Screen**: Full-screen playback support

## 🔑 **API Configuration**

The extension is pre-configured with your API key:
```
AIzaSyAbaRKmjHu-A73tdhEXRatmI5WWk-GOwRI
```

## 🛠️ **Troubleshooting**

### **Build Issues**
- Ensure you have Android SDK installed
- Check Java version (Java 8+ required)
- Verify Gradle installation

### **Installation Issues**
- Enable "Install from Unknown Sources"
- Check device storage space
- Verify APK file integrity

### **Usage Issues**
- Check internet connection
- Verify Google account permissions
- Clear app data and retry

## 📞 **Support**

If you encounter issues:
1. Check the troubleshooting section
2. Review the main README.md
3. Check CloudStream documentation
4. Open an issue on GitHub

## 🎉 **Ready to Use!**

Your CloudStream Google Drive extension is ready to use! You don't need to publish it anywhere - just build, install, and enjoy streaming your Google Drive videos! 🎬📱
