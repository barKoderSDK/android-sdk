#	barKoder Installation Guide for Android Barcode Reader SDK

To use our SDK on [Android](https://developer.android.com/get-started/overview) please follow these simple steps to integrate our SDK into your Android project:

##	Import module

Import the barkoder module like it's shown on the picture
![picture1.1.png](https://docs.barkoder.com/picture1.png)

Keep in mind that path in source directory should reference to the barcoder folder that contains both barkoder.aar and build.gradle, not directly to the barkoder.aar file

## Add dependancy

In your app's build gradle file add the following dependency
```kotlin
implementation project(path: ':barkoder')
```

![picture2.2.png](https://docs.barkoder.com/picture2.2.png)

## Add activity/fragment

Add the following code to the layout.xml of the activity/fragment where you want the scanner to be shown

```java
<com.barkoder.BarkoderView
		android:id="@+id/bkdView"
    android:layout_width="match_parent"
    android:layout_height="match_parent" />
```

![picture3.3.png](https://docs.barkoder.com/picture3.3.png)



## Licensing

The SDK will scan barcodes even without a license, however, certain characters will be marked with asterisks. To avoid this you can create a trial license or even a production license. Follow [barkoder.com/register](https://barkoder.com/register?ref=github) and create a trial license today.

## Create barkoder config

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

## Start scanning

```java
bkdView.startScanning(this)
```


Find out more:

[~Kotlin~](https://developer.android.com/courses/kotlin-bootcamp/overview?gclid=CjwKCAjwysipBhBXEiwApJOcu_FgLOiD4t2WqUFvcCmPsPiGYXldca0N8zz5VNdsUBgFrsYbhfrJFhoCSRYQAvD_BwE&gclsrc=aw.ds) 
[~Kotlin~ ~Tutorial~](https://www.youtube.com/watch?v=BCSlZIUj18Y&ab_channel=AppDevNotes) 
