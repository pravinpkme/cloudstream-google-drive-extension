# CloudStream Google Drive Extension - Setup Guide

## 🚀 Quick Installation

### Method 1: Using the Built Extension File (Recommended)

1. **Use the provider file directly**:
   ```
   extensions/GoogleDrive/GoogleDriveProvider.kt
   ```

2. **Install in CloudStream**:
   - Open CloudStream app
   - Go to Settings → Extensions → Repositories
   - Add this repository URL: `https://raw.githubusercontent.com/pravinpkme/cloudstream-google-drive-extension/main/`
   - Or manually install the `.kt` file

### Method 2: Manual Installation

1. **Copy the extension file**:
   - Take `GoogleDriveProvider.kt` from the `extensions/GoogleDrive/` folder
   - Copy it to your device

2. **Install in CloudStream**:
   - Open CloudStream app
   - Go to Settings → Extensions
   - Tap "Add Extension" or the "+" button
   - Select the `GoogleDriveProvider.kt` file
   - Enable the extension

## 🔧 Configuration

### For Real Google Drive Videos:

1. **Get your Google Drive file ID**:
   - Open your video file in Google Drive
   - Share the file (make it viewable to anyone with the link)
   - Copy the file ID from the URL: `https://drive.google.com/file/d/FILE_ID_HERE/view`

2. **Update the extension** (optional):
   - Edit the `GoogleDriveProvider.kt` file
   - Replace the sample file IDs with your actual file IDs
   - Or use the search functionality to find your videos

### File ID Examples:
```
Google Drive URL: https://drive.google.com/file/d/1BxiMVs0XRA5nFMdKvBdBZjgmUUqptlbs74OgvE2upms/view
File ID: 1BxiMVs0XRA5nFMdKvBdBZjgmUUqptlbs74OgvE2upms
```

## 📋 Features Fixed

✅ **Correct package structure** - Now uses `providers` instead of `extractors`
✅ **Proper CloudStream API usage** - Fixed response types and methods
✅ **Multiple video URL formats** - Supports different Google Drive URL patterns
✅ **Search functionality** - Working search implementation
✅ **Thumbnail support** - Shows video thumbnails from Google Drive
✅ **Multiple streaming options** - Direct, preview, and download links
✅ **Proper error handling** - No more crashes

## 🎯 How to Add Your Own Videos

1. **Upload videos to Google Drive**
2. **Make them publicly accessible** (share with anyone with the link)
3. **Get the file IDs** from the URLs
4. **Edit the extension** to include your file IDs in the sample data
5. **Or use the search functionality** to find videos dynamically

## 🔗 Supported URL Formats

The extension can handle these Google Drive URL formats:
- `https://drive.google.com/file/d/FILE_ID/view`
- `https://drive.google.com/open?id=FILE_ID`
- `https://docs.google.com/uc?id=FILE_ID`
- Direct file IDs

## 🛠️ Troubleshooting

### Extension Not Showing Up:
1. Check that the file is named exactly `GoogleDriveProvider.kt`
2. Ensure CloudStream has permission to read the file
3. Try restarting CloudStream after installation
4. Check that you're using a compatible CloudStream version

### Videos Not Playing:
1. Ensure your Google Drive videos are set to "Anyone with the link can view"
2. Try different streaming options (Direct/Stream/Download)
3. Check your internet connection
4. Verify the file IDs are correct

### API Limitations:
- Google Drive has rate limits for anonymous access
- For better performance, consider implementing OAuth2 authentication
- Large files may take time to load

## 🔄 Next Steps for Production Use

1. **Implement OAuth2 authentication** for accessing private videos
2. **Add Google Drive API integration** for real-time video listing
3. **Implement caching** for better performance
4. **Add video quality selection**
5. **Support for subtitles and chapters**

The extension is now properly structured and should work with CloudStream! 🎬