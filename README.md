# barKoder Android Barcode Scanner SDK

Build fast, reliable barcode scanning directly into native Android applications with the **barKoder Barcode Scanner SDK**. The SDK provides Kotlin and Java developers with an on-device scanning engine for common retail barcodes as well as demanding enterprise use cases such as DPM Data Matrix, damaged and deformed codes, multi-barcode capture, VIN scanning and MRZ/OCR workflows.

barKoder is intended for applications in logistics, manufacturing, retail, automotive, healthcare, inventory, field service and other workflows where a phone or tablet needs to perform like a dedicated barcode scanner.

## Quick links

- **Android SDK:** [https://barkoder.com/barcode-scanner-sdk/platforms/android](https://barkoder.com/barcode-scanner-sdk/platforms/android)
- **Installation guide:** [https://barkoder.com/docs/v1/android/android-sdk-installation](https://barkoder.com/docs/v1/android/android-sdk-installation)
- **Android examples:** [https://barkoder.com/docs/v1/android/android-examples](https://barkoder.com/docs/v1/android/android-examples)
- **API reference:** [https://barkoder.com/docs/v1/android/android-sdk-api-reference](https://barkoder.com/docs/v1/android/android-sdk-api-reference)
- **Free trial:** [https://barkoder.com/trial](https://barkoder.com/trial)

## Key capabilities

barKoder is designed for production barcode capture workflows where speed and decode reliability matter. Depending on the license and configuration, the SDK supports capabilities such as:

- 30+ 1D and 2D barcode symbologies, including QR Code, Data Matrix, PDF417, Code 128, Code 39, EAN/UPC, Aztec, DotCode and GS1 formats
- [Direct Part Marking (DPM) scanning](https://barkoder.com/barcode-scanner-sdk/dpm) for difficult Data Matrix codes on metal, plastic and other industrial surfaces
- [Batch MultiScan](https://barkoder.com/barcode-scanner-sdk/batch-multiscan) for decoding multiple barcodes in a single camera view
- [VIN barcode scanning](https://barkoder.com/barcode-scanner-sdk/vin-scanning) for automotive workflows
- [MRZ scanning](https://barkoder.com/barcode-scanner-sdk/mrz) for passports, ID cards and travel documents
- Continuous scanning, image/gallery scanning and configurable regions of interest
- Advanced decoding for damaged, deformed, low-quality and blurry barcodes
- On-device scanning for normal mobile scanning workflows

For the complete feature set and platform-specific configuration options, use the official documentation linked below.


## Requirements

The current Android product requirements are maintained on the [Android Barcode Scanner SDK page](https://barkoder.com/barcode-scanner-sdk/platforms/android). At the time of this README update, the native Android SDK supports Android 8+ and Kotlin/Java development, with common Android ABIs supported.

## Installation

The repository contains the `barkoder` Android module and a Kotlin sample application.

### 1. Add the barKoder module

Import the `barkoder` module into your Android project. The module directory should contain the SDK AAR and its Gradle configuration.

Add the module dependency to your app:

```gradle
implementation project(path: ':barkoder')
```

For current Gradle/Kotlin DSL instructions, use the [Android installation guide](https://barkoder.com/docs/v1/android/android-sdk-installation).

### 2. Add `BarkoderView` to your layout

```xml
<com.barkoder.BarkoderView
    android:id="@+id/bkdView"
    android:layout_width="match_parent"
    android:layout_height="match_parent" />
```

### 3. Create the SDK configuration

```kotlin
bkdView.config = BarkoderConfig(this, "LICENSE_KEY") { licenseResult ->
    Log.i("barKoder", licenseResult.message)
}
```

You can then enable the decoders and options required for your workflow through `BarkoderConfig`.

### 4. Start scanning

Implement `BarkoderResultCallback` and start the scanner:

```kotlin
bkdView.startScanning(this)
```

A minimal callback receives the decoded results, result thumbnails and optional result image:

```kotlin
override fun scanningFinished(
    results: Array<Barkoder.Result>,
    thumbnails: Array<Bitmap>,
    imageResult: Bitmap?
) {
    if (results.isNotEmpty()) {
        Log.i("barKoder", results[0].textualData)
    }
}
```

See the [`BKDSampleKotlin`](./BKDSampleKotlin) project and the [Android examples](https://barkoder.com/docs/v1/android/android-examples) for complete implementations.

## Demo apps

Test the scanning engine before integration:

- [barKoder Barcode Scanner for Android](https://play.google.com/store/apps/details?id=com.barkoder.demoscanner)
- [barKoder Barcode Scanner for iOS](https://apps.apple.com/us/app/barkoder-scanner/id6443715409)

## Trial license

You can evaluate barKoder in your own application with a free trial license:

**[Get a free barKoder SDK trial](https://barkoder.com/trial)**

The SDK can be initialized without a valid license for integration testing, but decoded results may be partially masked or marked as unlicensed. Use a valid trial or production license for complete results and licensed functionality.

Do not publish a trial license in a production application or public source repository.


## Support

Need help with integration or testing?

- Documentation: [https://barkoder.com/docs/v1/home](https://barkoder.com/docs/v1/home)
- Technical support: [support@barkoder.com](mailto:support@barkoder.com)
- Sales and licensing: [sales@barkoder.com](mailto:sales@barkoder.com)

## License

See the `LICENSE` file in this repository for the terms applicable to the repository contents. Use of the barKoder SDK itself is subject to the applicable barKoder license agreement.
