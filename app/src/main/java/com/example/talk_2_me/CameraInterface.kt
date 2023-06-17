import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.provider.MediaStore
import android.webkit.JavascriptInterface
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity

class CameraInterface(private val context: Context) {

    @JavascriptInterface
    fun openCamera() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        (context as AppCompatActivity).startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE)
    }

    companion object {
        private const val CAMERA_REQUEST_CODE = 100
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == CAMERA_REQUEST_CODE && resultCode == AppCompatActivity.RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            // Do something with the captured image
        }
    }
}
