package com.basel.natour.myapplication.views;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.basel.natour.myapplication.R;
import com.basel.natour.myapplication.Utils.AppUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BookMovieActivity extends AppCompatActivity {

    @BindView(R.id.webview)
    WebView webView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.book_movie_activity );
        ButterKnife.bind( this );
        loadWebViewLoad( getResources().getString( R.string.booking_url ) );
    }

    private void loadWebViewLoad(String urlString) {
        webView.setWebViewClient( new myWebClient() );
        webView.getSettings().setBuiltInZoomControls( true );
        webView.getSettings().setJavaScriptEnabled( true );
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically( true );
        webView.getSettings().setSupportMultipleWindows( true );
        webView.getSettings().setUseWideViewPort( true );
        webView.getSettings().setLoadWithOverviewMode( true );
        webView.setScrollbarFadingEnabled( false );
        webView.setScrollBarStyle( WebView.SCROLLBARS_OUTSIDE_OVERLAY );
        webView.setWebChromeClient( new WebChromeClient() );
        if ( AppUtils.checkNetwork( BookMovieActivity.this ) ) {

            if ( !urlString.startsWith( "http://" ) && !urlString.startsWith( "https://" ) )
                urlString = "http://" + urlString;

            webView.loadUrl( urlString );
        } else {

        }
    }


    public class myWebClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view , String url , Bitmap favicon) {
            super.onPageStarted( view , url , favicon );
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view , String url) {
            //progressBar.setVisibility(View.VISIBLE);
//      mKProgressHUD.show();
            view.loadUrl( url );
            return true;
        }

        @Override
        public void onPageFinished(final WebView view , String url) {
            super.onPageFinished( view , url );
            progressBar.setVisibility( View.GONE );
//      if(!AppUtils.isNull(mKProgressHUD) && mKProgressHUD.isShowing() && !isFinishing())
//      mKProgressHUD.dismiss();

//            webView.setOnScrollChangedCallback( new ObservableWebView.OnScrollChangedCallback() {
//
//                @Override
//                public void onScroll(int l , int t , int oldl , int oldt) {
//                    int tek = (int) Math.floor( webView.getContentHeight() * webView.getScale() );
//                    if ( tek - webView.getScrollY() == webView.getHeight() )
//                        ConnectLog.e( "Aldi" , "maulana: end" );
//
//                }
//
//            } );

            if ( !AppUtils.checkNetwork( BookMovieActivity.this ) ) {
                try {
                    final AlertDialog.Builder dialog = new AlertDialog.Builder(
                            BookMovieActivity.this );
                    dialog
                            .setTitle( "Something went wrong" )
                            .setMessage("No Intenet Connection" )
                            .setPositiveButton(
                                    "OK" ,
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog , int which) {
                                            dialog.dismiss();
                                            finish();
                                        }
                                    } )
                            .show();
                } catch (Exception e) {
                }
            }
        }
    }


}
