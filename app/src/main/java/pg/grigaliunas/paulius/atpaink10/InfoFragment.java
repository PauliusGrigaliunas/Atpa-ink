package pg.grigaliunas.paulius.atpaink10;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;


public class InfoFragment extends Fragment {

    public InfoFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_info, container, false);
        super.onCreate(savedInstanceState);
       // webView = (WebView) view.findViewById(R.id.webView1);
       // webView.getSettings().setJavaScriptEnabled(true);
       // webView.loadUrl("https://github.com/dashboard");

        return view;
    }

}

