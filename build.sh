#!/bin/bash

# CloudStream Google Drive Extension Build Script

echo "🔧 Building CloudStream Google Drive Extension..."

# Create output directory
mkdir -p dist

# Copy the main provider file
cp extensions/GoogleDrive/GoogleDriveProvider.kt dist/
cp extensions/GoogleDrive/extension.json dist/

# Copy repository configuration
cp repo.json dist/

# Create a zip package for distribution
cd dist
zip -r GoogleDriveExtension.zip *
cd ..

echo "✅ Extension built successfully!"
echo "📦 Files available in dist/ folder:"
ls -la dist/

echo ""
echo "🚀 Installation Instructions:"
echo "1. Copy GoogleDriveProvider.kt to your device"
echo "2. Open CloudStream app"
echo "3. Go to Settings → Extensions"
echo "4. Tap 'Add Extension' and select the .kt file"
echo "5. Enable the Google Drive extension"
echo ""
echo "📖 See setup_extension.md for detailed instructions"
