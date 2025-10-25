# App Icon Setup Guide

## Current Status

The app currently uses placeholder icon configurations. To add proper icons:

## Required Icon Sizes

### For Android (all in `app/src/main/res/`):

1. **mipmap-mdpi/** (48x48 px)
   - ic_launcher.png
   - ic_launcher_round.png

2. **mipmap-hdpi/** (72x72 px)
   - ic_launcher.png
   - ic_launcher_round.png

3. **mipmap-xhdpi/** (96x96 px)
   - ic_launcher.png
   - ic_launcher_round.png

4. **mipmap-xxhdpi/** (144x144 px)
   - ic_launcher.png
   - ic_launcher_round.png

5. **mipmap-xxxhdpi/** (192x192 px)
   - ic_launcher.png
   - ic_launcher_round.png

6. **Adaptive Icon** (Android 8.0+)
   - mipmap-anydpi-v26/ic_launcher.xml (already configured)
   - mipmap-anydpi-v26/ic_launcher_round.xml (already configured)
   - ic_launcher_foreground.png (for each density)

## Design Recommendations

### Brand Colors
- **Primary**: Terracotta (#D4886D)
- **Secondary**: Warm Orange (#E67E50)
- **Background**: Cream (#FFF8F0)

### Design Elements
- Simple, recognizable icon
- Paracord/craft theme
- Readable at small sizes
- Works on light and dark backgrounds

### Icon Concept Ideas
1. **Paracord Knot** - Stylized paracord knot symbol
2. **R Letter** - Letter R with paracord texture
3. **Handicraft Tool** - Simplified crafting tool
4. **Product Silhouette** - Bagcharm or keychain shape

## Tools for Icon Creation

### Online Tools
1. **Android Asset Studio**
   - URL: https://romannurik.github.io/AndroidAssetStudio/
   - Best for: Creating all densities from one image

2. **Icon Kitchen**
   - URL: https://icon.kitchen/
   - Best for: Adaptive icons

3. **App Icon Generator**
   - URL: https://appicon.co/
   - Best for: Multiple platform icons

### Design Software
1. **Figma** (Free) - https://figma.com
2. **Adobe Illustrator** (Paid)
3. **Inkscape** (Free) - https://inkscape.org

## Steps to Add Icons

### Option 1: Using Android Studio

1. Right-click `res` folder
2. New → Image Asset
3. Configure:
   - Icon Type: Launcher Icons (Adaptive and Legacy)
   - Name: ic_launcher
   - Asset Type: Image/Clip Art
   - Path: Browse to your icon file
4. Click Next → Finish

### Option 2: Manual Addition

1. Create icons in required sizes
2. Place files in corresponding mipmap folders:
   ```
   app/src/main/res/
   ├── mipmap-mdpi/
   │   ├── ic_launcher.png
   │   └── ic_launcher_round.png
   ├── mipmap-hdpi/
   │   ├── ic_launcher.png
   │   └── ic_launcher_round.png
   └── ... (other densities)
   ```

3. For adaptive icons, also add foreground:
   ```
   mipmap-xxxhdpi/ic_launcher_foreground.png
   ```

### Option 3: Using Icon Generator

1. Go to Android Asset Studio
2. Upload your icon design (1024x1024 recommended)
3. Adjust settings:
   - Padding: 20%
   - Background color: #D4886D (terracotta)
   - Shape: Square/Circle
4. Download generated zip
5. Extract to `app/src/main/res/`

## Verification

After adding icons:

1. Clean and rebuild project
   ```bash
   ./gradlew clean build
   ```

2. Uninstall old app from device/emulator

3. Install new build

4. Check app launcher:
   - Icon appears correctly
   - No blank/default icons
   - Adaptive icon works (Android 8.0+)

## Testing Checklist

- [ ] Icon visible in app launcher
- [ ] Icon looks good on light background
- [ ] Icon looks good on dark background
- [ ] Adaptive icon animates properly
- [ ] All densities present
- [ ] No pixelation on any device
- [ ] Matches brand guidelines

## Resources

### Icon Design Guidelines
- [Material Design Icons](https://material.io/design/iconography)
- [Android Icon Design](https://developer.android.com/guide/practices/ui_guidelines/icon_design_adaptive)

### Stock Icons (Free)
- [Material Icons](https://fonts.google.com/icons)
- [Icons8](https://icons8.com)
- [Flaticon](https://www.flaticon.com)

### Professional Design Services
- Fiverr
- 99designs
- Upwork

## Current Configuration

The app uses adaptive icon XML configured in:
- `app/src/main/res/mipmap-anydpi-v26/ic_launcher.xml`
- `app/src/main/res/mipmap-anydpi-v26/ic_launcher_round.xml`

These reference:
- Background: `@color/terracotta`
- Foreground: `@mipmap/ic_launcher_foreground` (need to add)

## Need Help?

Contact the design team or refer to Android documentation:
https://developer.android.com/studio/write/image-asset-studio

---

**Note**: Proper icons significantly improve app professionalism and user recognition. Prioritize adding custom icons before production release.
