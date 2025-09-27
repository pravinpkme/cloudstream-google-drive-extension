# CloudStream Google Drive Extension

> **✅ WORKING VERSION** - Fixed and ready to use!

A CloudStream extension that allows you to stream videos directly from your Google Drive account.

## 🚀 Quick Start

### Installation

1. **Download the extension**:
   ```bash
   # Download the working provider file
   wget https://raw.githubusercontent.com/pravinpkme/cloudstream-google-drive-extension/main/extensions/GoogleDrive/GoogleDriveProvider.kt
   ```

2. **Install in CloudStream**:
   - Open CloudStream app
   - Go to **Settings → Extensions**
   - Tap **"Add Extension"** or **"+"**
   - Select the `GoogleDriveProvider.kt` file
   - **Enable the extension**

### Adding Your Videos

1. **Share your Google Drive videos publicly**:
   - Right-click video → Share → "Anyone with the link can view"

2. **Get the file ID from the URL**:
   ```
   https://drive.google.com/file/d/FILE_ID_HERE/view
   ```

3. **Update the extension** (optional):
   - Edit `GoogleDriveProvider.kt`
   - Replace sample file IDs with your real ones

## ✅ What's Fixed

- **✅ Correct package structure** (`providers` instead of `extractors`)
- **✅ Proper CloudStream API usage** 
- **✅ Multiple streaming URL formats**
- **✅ Working search functionality**
- **✅ Thumbnail support**
- **✅ Error handling**

## 📁 Project Structure

```
├── extensions/GoogleDrive/
│   ├── GoogleDriveProvider.kt    # Main extension file
│   └── extension.json           # Extension configuration
├── dist/                        # Built extension files
├── build.sh                     # Build script
├── repo.json                    # Repository configuration
└── README.md                    # This file
```

## 🛠️ Development

### Building

```bash
# Build the extension
./build.sh

# Output will be in dist/ folder
```

### Testing

1. Copy `dist/GoogleDriveProvider.kt` to your device
2. Install in CloudStream
3. Test with your Google Drive videos

## 🔧 Configuration

### Supported Video Formats
- MP4, MKV, AVI, MOV, WMV
- Any format supported by Android ExoPlayer

### URL Formats Supported
- `https://drive.google.com/file/d/FILE_ID/view`
- `https://drive.google.com/open?id=FILE_ID`
- `https://docs.google.com/uc?id=FILE_ID`
- Direct file IDs

## 📖 Documentation

- [**Installation Guide**](FIXED_EXTENSION_GUIDE.md) - Detailed setup instructions
- [**Setup Guide**](setup_extension.md) - Quick setup reference
- [**CloudStream Extension Info**](CLOUDSTREAM_EXTENSION.md) - Extension format details

## 🐛 Troubleshooting

### Extension Not Loading
- Ensure file is named exactly `GoogleDriveProvider.kt`
- Restart CloudStream after installation
- Check CloudStream version compatibility

### Videos Not Playing
- Verify videos are shared publicly
- Try different streaming options
- Check internet connection

### No Videos Showing
- Extension shows sample videos by default
- Replace sample file IDs with your actual file IDs
- Ensure file IDs are correct format

## 🔮 Roadmap

- [ ] OAuth2 authentication for private videos
- [ ] Google Drive API integration
- [ ] Folder/playlist support
- [ ] Video quality selection
- [ ] Subtitle support
- [ ] Caching for better performance

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Test with CloudStream
5. Submit a pull request

## 📄 License

MIT License - see [LICENSE](LICENSE) file for details.

## 🆘 Support

- **Issues**: [GitHub Issues](https://github.com/pravinpkme/cloudstream-google-drive-extension/issues)
- **Discussions**: [GitHub Discussions](https://github.com/pravinpkme/cloudstream-google-drive-extension/discussions)

---

**⭐ If this extension works for you, please star the repository!**
