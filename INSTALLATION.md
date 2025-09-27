# CloudStream Google Drive Extension - Installation Guide

## 🚀 Quick Start

### Prerequisites
- Android device with Android 5.0+ (API 21+)
- CloudStream app installed
- Google account with Drive access
- Internet connection

### Installation Steps

1. **Build the Extension**
   ```bash
   ./build.sh
   ```
   Or manually:
   ```bash
   ./gradlew assembleRelease
   ```

2. **Install on Device**
   - Copy `app/build/outputs/apk/release/app-release.apk` to your Android device
   - Enable "Install from Unknown Sources" in Android Settings
   - Install the APK

3. **Configure in CloudStream**
   - Open CloudStream app
   - Go to Settings → Extensions
   - Find "Google Drive" extension
   - Enable it
   - Tap "Authenticate with Google"
   - Sign in with your Google account
   - Grant necessary permissions

4. **Start Streaming**
   - Go to the main CloudStream interface
   - Your Google Drive videos will appear in the "Google Drive Videos" section
   - Tap any video to start streaming

## 🔧 Configuration

### API Key
The extension is pre-configured with the API key:
```
AIzaSyAbaRKmjHu-A73tdhEXRatmI5WWk-GOwRI
```

### Permissions Required
- Internet access
- Network state access
- Wake lock (for video playback)

## 📱 Features

- ✅ **Video Streaming**: Stream videos directly from Google Drive
- ✅ **Search**: Search through your Google Drive videos
- ✅ **Metadata**: View video duration, size, and creation date
- ✅ **Authentication**: Secure OAuth2 integration
- ✅ **Modern UI**: Material Design 3 interface
- ✅ **Offline Support**: Videos cached for offline viewing

## 🛠️ Troubleshooting

### Common Issues

**Extension not appearing in CloudStream:**
- Ensure the APK is properly installed
- Check CloudStream version compatibility
- Restart CloudStream app

**Authentication failed:**
- Check internet connection
- Verify Google account permissions
- Clear app data and retry

**Videos not loading:**
- Check Google Drive API quota
- Verify video file permissions
- Ensure stable internet connection

**Playback issues:**
- Check video format compatibility
- Try different quality settings
- Restart the app

### Debug Mode
Enable debug logging in CloudStream settings for detailed error information.

## 📋 System Requirements

- **Android**: 5.0+ (API 21+)
- **RAM**: 2GB+ recommended
- **Storage**: 100MB+ for app and cache
- **Network**: Stable internet connection for streaming

## 🔒 Security

- OAuth2 authentication
- HTTPS-only communication
- No sensitive data stored locally
- Secure token management

## 📞 Support

For issues and questions:
1. Check this troubleshooting guide
2. Review the main README.md
3. Check CloudStream documentation
4. Open an issue on GitHub

## 🎯 Next Steps

After installation:
1. Authenticate with Google
2. Browse your videos
3. Start streaming!
4. Use search to find specific videos
5. Enjoy your Google Drive videos in CloudStream!

---

**Happy Streaming! 🎬**
