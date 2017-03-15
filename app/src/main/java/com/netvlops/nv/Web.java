package com.netvlops.nv;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Web extends AppCompatActivity {
    WebView wb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        wb = (WebView)findViewById(R.id.webView);
        wb = new WebView(this);
        //Menyalakan javascript
        wb.getSettings().setJavaScriptEnabled(true);
        //Tempat load url
            wb.loadUrl("http://netvlops.com");
       // wb.loadUrl("file:///android_asset/mdr.htm");
        wb.setWebViewClient(new WebViewClient());
        setContentView(wb);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode== KeyEvent.KEYCODE_BACK && wb.canGoBack())){
            wb.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
