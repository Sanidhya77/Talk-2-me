import android.content.Intent
import android.net.Uri
import android.os.Build
import android.util.Log
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

class CustomWebViewClient : WebViewClient() {

    @Suppress("OverridingDeprecatedMember")
    override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
        val uri = Uri.parse(url)
        return handleUri(uri)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
        val uri = request.url
        return handleUri(uri)
    }

    private fun handleUri(uri: Uri): Boolean {
        Log.i(TAG, "Uri = $uri")
        val host = uri.host
        val scheme = uri.scheme
        // Based on some condition, determine whether to load the URL in the WebView or open it in a browser.
        // You can use `host` or `scheme` or any part of the `uri` to decide.
        if (/* any condition */) {
            // Returning false means that you are going to load this URL in the WebView itself
            return false
        } else {
            // Returning true means that you need to handle what to do with the URL
            // e.g., open a web page in a browser
            val intent = Intent(Intent.ACTION_VIEW, uri)
            view.context.startActivity(intent)
            return true
        }
    }

    companion object {
        private const val TAG = "CustomWebViewClient"
    }
}

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val webView: WebView = findViewById(R.id.webview)
        webView.webViewClient = CustomWebViewClient()
    }
}
