package com.barkoder.samplekotlin

import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.isVisible
import com.barkoder.Barkoder
import com.barkoder.BarkoderConfig
import com.barkoder.BarkoderHelper
import com.barkoder.BarkoderView
import com.barkoder.enums.BarkoderConfigTemplate
import com.barkoder.interfaces.BarkoderResultCallback
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), BarkoderResultCallback {

    private lateinit var txtStartScan: TextView
    private lateinit var imgResult: ImageView
    private lateinit var bkdView: BarkoderView
    private lateinit var cardResult: CardView
    private lateinit var txtResult: TextView
    private lateinit var txtResultType: TextView
    private lateinit var txtResultExtras: TextView
    private lateinit var btnScanning: FloatingActionButton

    private var isScanningActive: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = "${getString(R.string.app_name)} (v${Barkoder.GetVersion()})"

        txtStartScan = findViewById(R.id.txtViewStartScan)
        imgResult = findViewById(R.id.imageViewResult)
        bkdView = findViewById(R.id.barkoderView)
        cardResult = findViewById(R.id.cardViewResult)
        cardResult.setOnClickListener {
            showFullResult()
        }
        txtResult = findViewById(R.id.textViewResult)
        txtResultType = findViewById(R.id.textViewResultType)
        txtResultExtras = findViewById(R.id.textViewResultExtras)
        btnScanning = findViewById(R.id.buttonScanning)
        btnScanning.setOnClickListener { onBtnScanningClick() }

        createBarkoderConfig()
        setActiveBarcodeTypes()
        setBarkoderSettings()
    }

    override fun onStop() {
        isScanningActive = false
        updateUI()

        super.onStop()
    }

    private fun createBarkoderConfig() {
        // In order to perform scanning, config property need to be set before
        // If license key is not valid you will receive results with asterisks inside
        bkdView.config = BarkoderConfig(this, "LICENSE_KEY") {
            Log.i("Licensing SDK: ", it.message)
        }
    }

    private fun setActiveBarcodeTypes() {
        // There is option to set multiple active barcodes at once as array
        bkdView.config.decoderConfig.SetEnabledDecoders(
            arrayOf(
                Barkoder.DecoderType.QR,
                Barkoder.DecoderType.Ean13
            )
        )
        // or configure them one by one
        bkdView.config.decoderConfig.UpcA.enabled = true
    }

    private fun setBarkoderSettings() {
        // These are optional settings, otherwise default values will be used
        bkdView.config.let { config ->
            config.isImageResultEnabled = true
            config.isLocationInImageResultEnabled = true
            config.isRegionOfInterestVisible = true
            config.isPinchToZoomEnabled = true
            config.setRegionOfInterest(5f, 5f, 90f, 90f)
        }
    }

    private fun updateUI(result: Barkoder.Result? = null, resultImage: Bitmap? = null) {
        if (isScanningActive) {
            txtStartScan.isVisible = false
            btnScanning.backgroundTintList =
                ColorStateList.valueOf(resources.getColor(R.color.primary))
        } else {
            txtStartScan.isVisible = resultImage == null
            btnScanning.backgroundTintList =
                ColorStateList.valueOf(resources.getColor(R.color.black))
        }

        txtResultType.text = result?.barcodeTypeName
        txtResult.text = result?.textualData

        showResultExtras(result)

        imgResult.setImageBitmap(resultImage)
        imgResult.isVisible = resultImage != null
    }

    private fun onBtnScanningClick() {
        if (isScanningActive) {
            bkdView.stopScanning()
        } else {
            // Starting the scanning process and set BarkoderResultCallback where read results will be received
            // @throw NPE if config is not set before
            bkdView.startScanning(this)
        }

        isScanningActive = !isScanningActive
        updateUI()
    }

    override fun scanningFinished(results: Array<Barkoder.Result>, thumbnails: Array<Bitmap>, resultImage: Bitmap?) {
        isScanningActive = false

        if (results.isNotEmpty())
            updateUI(results[0], resultImage)
        else
            updateUI()
    }

    private fun showResultExtras(result: Barkoder.Result?) {
        if (result?.extra?.isNotEmpty() == true) {
            val extras = StringBuilder()
            result.extra.forEach {
                extras.append("${it.key}: ").append(it.value).appendLine()
            }
            txtResultExtras.text = extras.toString().trimEnd()
        } else
            txtResultExtras.text = ""
    }

    private fun showFullResult() {
        if (!txtResult.text.isNullOrBlank()) {
            MaterialAlertDialogBuilder(this)
                .setTitle(R.string.result_title)
                .setMessage(txtResult.text)
                .setPositiveButton(android.R.string.ok, null)
                .show()
        }
    }
}
