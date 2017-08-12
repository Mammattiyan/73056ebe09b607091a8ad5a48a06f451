package com.ceemart.ceemart;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class For_xml extends AppCompatActivity {

    LinearLayout lytAudio, lytVideo, lytMessage, lytImage;
    private PopupWindow pw;
    ImageView ImgMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_xml);

        lytAudio = (LinearLayout) findViewById(R.id.lytAudio);
        lytVideo = (LinearLayout) findViewById(R.id.lytVideo);
        lytMessage = (LinearLayout) findViewById(R.id.lytMessage);
        lytImage = (LinearLayout) findViewById(R.id.lytImage);
        ImgMessage = (ImageView) findViewById(R.id.ImgMessage);

        lytAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        lytVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        lytMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        lytImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        ImgMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MessagePopUp(view);
                Toast.makeText(getApplicationContext(), "Message clicked", Toast.LENGTH_SHORT).show();
            }
        });

    }


    private void MessagePopUp(View v) {
        try {
            //We need to get the instance of the LayoutInflater, use the context of this activity
            LayoutInflater inflater = (LayoutInflater) For_xml.this
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //Inflate the view from a predefined XML layout
            View layout = inflater.inflate(R.layout.layout_message, (ViewGroup) findViewById(R.id.MessageView));
            // create a 300px width and 470px height PopupWindow
            pw = new PopupWindow(layout, GridLayoutManager.LayoutParams.MATCH_PARENT, GridLayoutManager.LayoutParams.MATCH_PARENT, true);
            // display the popup in the center
            pw.showAtLocation(v, Gravity.CENTER, 0, 0);

            TextView mResultText = (TextView) layout.findViewById(R.id.textMessage);
            mResultText.setText("Aswanth R Chandran");
            ImageView close = (ImageView) layout.findViewById(R.id.close);
            close.setOnClickListener(cancel_button_click_listener);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private View.OnClickListener cancel_button_click_listener = new View.OnClickListener() {
        public void onClick(View v) {
            pw.dismiss();
        }
    };

}
