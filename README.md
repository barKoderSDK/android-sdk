# barKoder Barcode Scanner SDK for Android

### Add an enterprise-grade barcode scanning engine in your Android Native app 

Integrating the [barKoder Barcode Scanner SDK](https://barkoder.com/barcode-scanner-sdk) into your Enterprise or Consumer-facing mobile apps will instantly transform your user's smarphones and tablets into rugged barcode scanning devices without the need to procure and maintain expensive and sluggish hardware devices that have a very short life span.

The barKoder barcode scanner SDK is a relatively new product in an established market, already developed to be as advanced if not more than other competitor API's. Its robust barcode reading engine can be used to read the content of the most widely used barcodes with lightning fast speed and unprecended recognition rate: 

1D - [Codabar](https://barkoder.com/barcode-types/codaba), [Code 11](https://barkoder.com/barcode-types/code-11), [Code 25](https://barkoder.com/barcode-types/code-25), [Code 39](https://barkoder.com/barcode-types/code-39), [Code 93](https://barkoder.com/barcode-types/code-93), [Code 128](https://barkoder.com/barcode-types/code-128), [EAN-8](https://barkoder.com/barcode-types/ean-upc-code), [EAN-13](https://barkoder.com/barcode-types/ean-upc-code), [Interleaved 2 of 5](https://barkoder.com/barcode-types/code-25), [ITF-14](https://barkoder.com/barcode-types/code-25), [MSI Plessey](https://barkoder.com/barcode-types/msi-plessey), Pharmacode, [Telepen](https://barkoder.com/barcode-types/telepen), [UPC-A](https://barkoder.com/barcode-types/ean-upc-code) & [UPC-E](https://barkoder.com/barcode-types/ean-upc-code)

2D - [Aztec Code](https://barkoder.com/barcode-types/aztec), [Aztec Compact](https://barkoder.com/barcode-types/aztec), [Data Matrix](https://barkoder.com/barcode-types/data-matrix), [PDF417](https://barkoder.com/barcode-types/pdf417), [Micro PDF417](https://barkoder.com/barcode-types/pdf417), [DotCode](https://barkoder.com/barcode-types/dotcode), [QR Code](https://barkoder.com/barcode-types/qr-code) & [Micro QR Code](https://barkoder.com/barcode-types/qr-code)


You can check out our free demo app Barcode Scanner by barKoder available both via [Apple App Store](https://apps.apple.com/us/app/barkoder-scanner/id6443715409?uo=2) & [Google Play Store](https://play.google.com/store/apps/details?id=com.barkoder.demoscanner).

### Full Documentation

You can find full documentation about the barKoder Barcode Reader SDK plugin for React Native here: https://docs.barkoder.com/en/v1/android-installation




##	barKoder Installation Guide for Android Barcode Reader SDK

To use our SDK on [Android](https://developer.android.com/get-started/overview) please follow these simple steps to integrate our SDK into your Android project:

###	Import module

Import the barkoder module like it's shown on the picture
![picture1.1.png](https://docs.barkoder.com/picture1.png)

Keep in mind that path in source directory should reference to the barcoder folder that contains both barkoder.aar and build.gradle, not directly to the barkoder.aar file

### Add dependancy

In your app's build gradle file add the following dependency
```kotlin
implementation project(path: ':barkoder')
```

![picture2.2.png](https://docs.barkoder.com/picture2.2.png)

### Add activity/fragment

Add the following code to the layout.xml of the activity/fragment where you want the scanner to be shown

```java
<com.barkoder.BarkoderView
		android:id="@+id/bkdView"
    android:layout_width="match_parent"
    android:layout_height="match_parent" />
```

![picture3.3.png](https://docs.barkoder.com/picture3.3.png)



### Licensing

The SDK will scan barcodes even without a license, however, certain characters will be marked with asterisks. To avoid this you can create a trial license or even a production license. Follow [barkoder.com/register](https://barkoder.com/register?ref=github) and create a trial license today.

### Create barkoder config

Create barkoder config per your needs or from some of our templates


```java
bkdView.config = BarkoderConfig(this, "LICENSE_KEY") {
   Log.i("LicenseInfo", it.message)
}
BarkoderHelper.applyConfigSettingsFromTemplate(
   this,
   bkdView.config,
   BarkoderConfigTemplate.INDUSTRIAL_1D,
   null
)
```

## Add BarkoderResultCallback

Implement BarkoderResultCallback interface where you will receive scanned results

```java
class MainActivity : AppCompatActivity(), BarkoderResultCallback {
   ....

   override fun scanningFinished(results: Array<Barkoder.Result>, thumbnails: Array<Bitmap>, imageResult: Bitmap?) {
       Log.i("Scanned result", results[0].textualData)
   }
}

```

### Start scanning

```java
bkdView.startScanning(this)
```


## API reference for barKoder's Android Barcode Reader SDK

### BarkoderView


Set camera frames callback if you want to receive the frames/images only without decoding them and do your own work with the frames.
Don't forget to close the image at the end
- @param previewFramesCallback

```kotlin
fun setPreviewFramesCallback(previewFramesCallback: BarkoderPreviewFramesCallback)
```



### getMaxZoomFactor
 
Get maximum zoom factor that can be set for the camera preview
Zoom factor is received in MaxZoomAvailableCallback
- @param callback MaxZoomAvailableCallback

```kotlin
fun getMaxZoomFactor(callback: MaxZoomAvailableCallback)
```

### setZoomFactor

Set zoom factor for the camera preview. If preview session is already active this zoom factor will be set only for this session,
otherwise initial zoom will be set. Every next preview session will be started with this zoom factor.
- @param zoomFactor [1, maxZoomFactor]. Default value is 1

```kotlin
fun setZoomFactor(zoomFactor: Int)
```

### isFlashAvailable

Check if current mobile device has flash available
Result is received in FlashAvailableCallback.
- @param callback

```kotlin
fun isFlashAvailable(callback: FlashAvailableCallback)
```


### setFlashEnabled

Turn flash ON/OFF
If preview session is already active this state be set only for active session,
otherwise the initial flash state is set. Every next preview session will be started with this state.
- @param enabled [true, false]. Default value is false.

```kotlin
fun setFlashEnabled(enabled: Boolean)
```

### stopCamera

Stop the camera preview session and decoding if active

```kotlin
fun stopCamera()
```

### startScanning

Start the camera preview (if is not already running) and the scanning process
- @param resultCallback @throw NullPointerException if BarkoderView config is not set

```kotlin
fun startScanning(resultCallback: BarkoderResultCallback)
```

### stopScanning

Stop the scanning process and the camera preview

```kotlin
fun stopScanning()
```

### pauseScanning

Pause only the scanning process. Camera preview is still running

```kotlin
fun pauseScanning()
```

## BarkoderConfig

 
### getDecoderConfig

Get the decoder config object. With this object you can enable/disable decoders (barcode types) or configure each one of them
@return Barkoder.Config object

```kotlin
fun getDecoderConfig(): Barkoder.Config
```

### getLocationLineColor

Get the location line color as integer
- @return location line color

```kotlin
fun getLocationLineColor(): Int
```

### setLocationLineColor

Set the location line color as integer.
This line is used for marking the scanned barcode in the image result
- @param locationLineColor [valid color representation as integer]. Default value is Color.GREEN


```kotlin
fun setLocationLineColor(locationLineColor: Int)
```
### getLocationLineWidth

Get the location line width as float.
- @return location line width

```kotlin
fun getLocationLineWidth(): Float
```

### setLocationLineWidth

Set the location line width as float.
This line is used for marking the scanned barcode in the image result
- @param locationLineWidth. Default value is 4.0

```kotlin
fun setLocationLineWidth(locationLineWidth: Float)
```

### getRoiLineColor

Get the region of interest line color as integer
- @return roi line color

```kotlin
fun getRoiLineColor(): Int
```

### roiLineColor

Set the region of interest line color as integer
- @param roiLineColor [valid color representation as integer]. Default value is Color.RED

```kotlin
fun setRoiLineColor(roiLineColor: Int)
```

### getRoiLineWidth

Get the region of interest line width as float
- @return roi line width

```kotlin
fun getRoiLineWidth(): Float
```

### setRoiLineWidth

Set the region of interest line width as float
- @param roiLineWidth. Default value is 3.0

```kotlin
fun setRoiLineWidth(roiLineWidth: Float)
```

### getRoiOverlayBackgroundColor

Get the region of interest background color as integer
- @return roi overlay background color

```kotlin
fun getRoiOverlayBackgroundColor(): Int
```

### roiOverlayBackgroundColor

Set the region of interest line color as integer
- @param roiOverlayBackgroundColor [valid color representation as integer]. Default value is 40% transparency

```kotlin
fun setRoiOverlayBackgroundColor(roiOverlayBackgroundColor: Int)
```

### isCloseSessionOnResultEnabled

Check if camera preview session will be closed when barcode is scanned
- @return true if preview will be closed, false otherwise

```kotlin
fun isCloseSessionOnResultEnabled(): Boolean
```

### setCloseSessionOnResultEnabled

Set if camera preview session should be closed when barcode is scanned
- @param closeSessionOnResultEnabled [false, true]. Default is true

```kotlin
fun setCloseSessionOnResultEnabled(closeSessionOnResultEnabled: Boolean)
```

### isImageResultEnabled

Check if the image result is enabled.
Image result is received in BarkoderResultCallback as Bitmap
- @return true if enabled or false if is not enabled

```kotlin
fun isImageResultEnabled(): Boolean
```

### setImageResultEnabled

Set if image result is enabled, otherwise null will be received
- @param imageResultEnabled [false, true]. Default is false

```kotlin
fun setImageResultEnabled(imageResultEnabled: Boolean)
```

### isLocationInImageResultEnabled

Check if barcode location in the image result is enabled.
If enabled, barcode in the result image will be marked
- @return true if enabled or false if is not enabled

```kotlin
fun isLocationInImageResultEnabled()
```

### setLocationInImageResultEnabled

Set if scanned barcode in the image result should be marked
- @param locationInImageResultEnabled [false, true]. Default is false

```kotlin
fun setLocationInImageResultEnabled(locationInImageResultEnabled: Boolean)
```

### getRegionOfInterest

Get active region of interest
@return Barkoder.BKRect object 

```kotlin
fun getRegionOfInterest(): Barkoder.BKRect
```

### setRegionOfInterest
Set region of interest in percentage
@param left. Default 3%
@param top. Default 20%
@param width. Default 94%
@param height. Default 60%
@throw IllegalArgumentException if input params are not valid

```kotlin
fun setRegionOfInterest(left: Float, top: Float, width: Float, height: Float)
```

### GetThreadsLimit

Get maximum threads that are used for the decoding process
@return threads number as integer

```kotlin
fun GetThreadsLimit(): Int
```

### SetThreadsLimit

Set maximum threads that will be used for the decoding process
@param threadsLimit [1, max threads available]
@throw IllegalArgumentException if input param is greater than maximum threads available on that device

```kotlin
fun SetThreadsLimit(threadsLimit: Int)
```


### isLocationInPreviewEnabled

Check if barcode location in preview is enabled.
If enabled, scanned barcode will be marked on the preview screen for short time
@return true if enabled or false if is not enabled

```kotlin
fun isLocationInPreviewEnabled(): Boolean
```


### setLocationInPreviewEnabled

Set if scanned barcode should be marked on the preview screen for short time
@param locationInPreviewEnabled [true, false]. Default is true

```kotlin
fun setLocationInPreviewEnabled(locationInPreviewEnabled: Boolean)
```

### isPinchToZoomEnabled

Check if the camera preview can be zoomed with pinch
@return true if enabled or false if is not enabled

```kotlin
fun isPinchToZoomEnabled(): Boolean
```

### setPinchToZoomEnabled

Enable or disable pinch to zoom on the camera preview
@param pinchToZoomEnabled [true, false]. Default is false

```kotlin
fun setPinchToZoomEnabled(pinchToZoomEnabled: Boolean)
```

### isRegionOfInterestVisible

Check if ROI is visible on the preview screen
@return true if visible or false otherwise

```kotlin
fun isRegionOfInterestVisible(): Boolean
```

### setRegionOfInterestVisible

Set if ROI should be visible on the preview screen
@param regionOfInterestVisible [true, false]. Default is true

```kotlin
fun setRegionOfInterestVisible(regionOfInterestVisible: Boolean)
```

### getBarkoderResolution

Get the active resolution. It can be Normal(HD), or HIGH(Full HD)
@return BarkoderResolution object

```kotlin
fun getBarkoderResolution(): BarkoderResolution
```

### setBarkoderResolution

Set the camera resolution that will be used while scanning
@param barkoderResolution [BarkoderResolution.NORMAL, BarkoderResolution.HIGH]. Default is BarkoderResolution.NORMAL

```kotlin
fun setBarkoderResolution(barkoderResolution: BarkoderResolution)
```

### isBeepOnSuccessEnabled

Check if device will beep on successful scan
@return true if enabled or false if is not enabled

```kotlin
fun isBeepOnSuccessEnabled(): Boolean
```

### setBeepOnSuccessEnabled

Set if device should beep on successful scan
@param beepOnSuccess [true, false]. Default is true

```kotlin
fun setBeepOnSuccessEnabled(beepOnSuccess: Boolean)
```

### isVibrateOnSuccessEnabled

Check if device will vibrate on successful scan
@return true if enabled or false if is not enabled

```kotlin
fun isVibrateOnSuccessEnabled(): Boolean
```
### setVibrateOnSuccessEnabled

Set if device should vibrate on successful scan
@param vibrateOnSuccess [true, false]. Default is true

```kotlin
fun setVibrateOnSuccessEnabled(vibrateOnSuccess: Boolean)
```

Find out more:

[Barcode Scanner SDK](https://barkoder.com/barcode-scanner-sdk) 

