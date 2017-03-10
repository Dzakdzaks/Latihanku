package com.netvlops.nv.Detail;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.netvlops.nv.R;

import static com.netvlops.nv.Server.url.url_img;

public class DetailGuru extends AppCompatActivity {
    ImageView img1;
    String gambar, content, title;
    WebView konten;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_guru);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        title = getIntent().getStringExtra("nama_guru");
        getSupportActionBar().setTitle(title);

        konten = (WebView) findViewById(R.id.wbDescGuru);
        konten.getSettings().setJavaScriptEnabled(true);
        content = getIntent().getStringExtra("desc_guru");
        String contentwvContent = "<html><body>" + content + "</body></html>";
        konten.loadData(contentwvContent, "text/html", null);

        gambar = getIntent().getStringExtra("img_guru");
        // gambar2 = getIntent().getStringExtra("gambar2");
        // gambar3 = getIntent().getStringExtra("gambar3");

        img1 = (ImageView) findViewById(R.id.imgGuru);
        //img2 = (ImageView) findViewById(R.id.gambar2);
        //img3 = (ImageView) findViewById(R.id.gambar3);

        Glide.with(this).load(url_img + gambar).placeholder(android.R.drawable.ic_menu_gallery).error(android.R.drawable.ic_menu_report_image).into(img1);
        //Glide.with(this).load("http://192.168.100.10/news/gambar/" + gambar2).placeholder(android.R.drawable.ic_menu_gallery).error(android.R.drawable.ic_menu_report_image).into(img2);
        //Glide.with(this).load("http://192.168.100.10/news/gambar/" + gambar3).placeholder(android.R.drawable.ic_menu_gallery).error(android.R.drawable.ic_menu_report_image).into(img3);

//        viewFlipper = (ViewFlipper) findViewById(R.id.flipper);
//        FadeIn = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
//        FadeOut = AnimationUtils.loadAnimation(this, android.R.anim.fade_out);
//        viewFlipper.setInAnimation(FadeIn);
//        viewFlipper.setOutAnimation(FadeOut);
//
//        viewFlipper.setAutoStart(true);
//        viewFlipper.setFlipInterval(3000);
//        viewFlipper.startFlipping();
    }
}
