# 🔧 CloudStream Extension Setup Guide

## ❌ **Why Your Extension Isn't Showing Up**

The current project structure is designed as an **Android Library** but CloudStream extensions need to be in a **specific format** to be recognized.

## ✅ **Solution: Convert to CloudStream Extension Format**

### **Method 1: Use as CloudStream Extension (Recommended)**

1. **Download the Extension File**:
   ```bash
   # Download the GoogleDriveProvider.kt file
   wget https://raw.githubusercontent.com/pravinpkme/cloudstream-google-drive-extension/main/GoogleDriveProvider.kt
   ```

2. **Place in CloudStream Extensions Folder**:
   - Open CloudStream app
   - Go to Settings → Extensions
   - Look for "Add Extension" or "Import Extension"
   - Select the `GoogleDriveProvider.kt` file

### **Method 2: Manual Installation**

1. **Download the Extension**:
   - Go to your GitHub repository
   - Download `GoogleDriveProvider.kt`
   - Save it to your device

2. **Install in CloudStream**:
   - Open CloudStream app
   - Go to Settings → Extensions
   - Tap "Add Extension" or "+"
   - Select the downloaded file
   - Enable the extension

### **Method 3: Direct File Placement**

1. **Find CloudStream Extensions Folder**:
   - Usually located at: `/Android/data/com.lagradost.cloudstream3/files/extensions/`
   - Or: `/sdcard/CloudStream/extensions/`

2. **Copy the Extension File**:
   ```bash
   # Copy the extension file to CloudStream extensions folder
   cp GoogleDriveProvider.kt /path/to/cloudstream/extensions/
   ```

3. **Restart CloudStream**:
   - Close and reopen CloudStream
   - Go to Extensions tab
   - The Google Drive extension should now appear

## 🔍 **Troubleshooting**

### **If Extension Still Doesn't Appear:**

1. **Check File Format**:
   - Ensure the file is named `GoogleDriveProvider.kt`
   - Check that it's in the correct location

2. **Check CloudStream Version**:
   - Ensure you have the latest CloudStream version
   - Some older versions may not support custom extensions

3. **Check File Permissions**:
   - Ensure CloudStream has permission to read the extension file
   - Check that the file is not corrupted

4. **Alternative Method**:
   - Try placing the file in the app's internal storage
   - Use a file manager to copy the file

## 📱 **Expected Behavior**

Once properly installed, you should see:
- ✅ "Google Drive" in the Extensions list
- ✅ Ability to enable/disable the extension
- ✅ Google Drive videos appearing in the main interface
- ✅ Search functionality for your Google Drive videos

## 🚀 **Quick Fix**

The easiest way to get this working:

1. **Download the extension file** from your GitHub repository
2. **Open CloudStream** → Settings → Extensions
3. **Look for "Add Extension" or "Import"** button
4. **Select the downloaded file**
5. **Enable the extension**

## 📞 **Still Having Issues?**

If the extension still doesn't appear:

1. **Check CloudStream documentation** for extension installation
2. **Try different CloudStream versions**
3. **Contact CloudStream support** for extension compatibility
4. **Use the standalone app version** instead of the extension

The extension should work once properly installed in the CloudStream extensions folder! 🎬📱
