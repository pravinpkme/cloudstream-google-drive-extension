# CloudStream Google Drive Extension

A CloudStream extension that allows you to stream videos directly from your Google Drive account.

## Features

- 🔐 **Secure Authentication**: OAuth2 integration with Google Drive
- 📱 **Mobile Optimized**: Designed for Android devices
- 🎥 **Video Streaming**: Stream videos directly from Google Drive using ExoPlayer
- 🔍 **Search Functionality**: Search through your Google Drive videos
- 📊 **Video Information**: Display video metadata including duration, size, and creation date
- 🎨 **Modern UI**: Material Design 3 interface

## Installation

1. **Build the Extension**:
   ```bash
   ./gradlew assembleRelease
   ```

2. **Install on Device**:
   - Copy the generated APK to your Android device
   - Install the APK using your preferred method

3. **Configure in CloudStream**:
   - Open CloudStream app
   - Go to Settings > Extensions
   - Enable the Google Drive extension
   - Authenticate with your Google account

## Usage

1. **Authentication**:
   - Open the extension
   - Tap "Authenticate with Google"
   - Sign in with your Google account
   - Grant necessary permissions

2. **Browse Videos**:
   - View all your Google Drive videos
   - Use the search functionality to find specific videos
   - Tap on any video to start streaming

3. **Stream Videos**:
   - Videos will open in the built-in ExoPlayer
   - Full-screen playback supported
   - Quality selection available

## API Configuration

The extension uses the Google Drive API with the provided API key:
```
```

## Technical Details

### Dependencies
- **CloudStream Core**: 4.0.0
- **Google Drive API**: v3
- **ExoPlayer**: 2.19.1
- **Material Design**: 3.0
- **Kotlin Coroutines**: 1.7.3

### Architecture
- **MVVM Pattern**: ViewModel-based architecture
- **Repository Pattern**: GoogleDriveService for API calls
- **Reactive UI**: LiveData for UI updates
- **Modern Android**: ViewBinding, Lifecycle components

### Security
- OAuth2 authentication
- Secure token storage
- HTTPS-only communication
- No sensitive data in logs

## Development

### Prerequisites
- Android Studio Arctic Fox or later
- Kotlin 1.8+
- Android SDK 21+
- Google Drive API access

### Building
```bash
# Debug build
./gradlew assembleDebug

# Release build
./gradlew assembleRelease

# Run tests
./gradlew test
```

### Project Structure
```
src/main/java/com/cloudstream/googledrive/
├── GoogleDriveExtension.kt          # Main CloudStream extension
├── GoogleDriveService.kt            # Google Drive API service
├── ui/
│   ├── MainActivity.kt              # Main video browser
│   ├── VideoPlayerActivity.kt       # Video player
│   ├── AuthActivity.kt              # Authentication
│   ├── adapter/
│   │   └── VideoAdapter.kt         # Video list adapter
│   └── viewmodel/
│       └── MainViewModel.kt         # Main view model
```

## Troubleshooting

### Common Issues

1. **Authentication Failed**:
   - Check internet connection
   - Verify Google account permissions
   - Clear app data and retry

2. **Videos Not Loading**:
   - Ensure Google Drive API is enabled
   - Check API key validity
   - Verify video file permissions

3. **Playback Issues**:
   - Check video format compatibility
   - Ensure stable internet connection
   - Try different video quality

### Debug Information
- Enable debug logging in CloudStream settings
- Check Android logs for error messages
- Verify API quota limits

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests if applicable
5. Submit a pull request

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Support

For issues and questions:
- Check the troubleshooting section
- Review CloudStream documentation
- Open an issue on GitHub

## Changelog

### Version 1.0.0
- Initial release
- Google Drive integration
- Video streaming support
- Authentication system
- Modern Material Design UI
