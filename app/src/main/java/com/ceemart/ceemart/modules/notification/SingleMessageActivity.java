package com.ceemart.ceemart.modules.notification;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.VideoView;

import com.ceemart.ceemart.R;
import com.ceemart.ceemart.controllers.QueryController;
import com.ceemart.ceemart.models.BeaconDisplayModel;
import com.squareup.picasso.Picasso;

import io.realm.RealmModel;

public class SingleMessageActivity extends AppCompatActivity {

    QueryController queryController;
    String beaconMap;
    Context context = this;
    LinearLayout lytAudio, lytVideo, lytMessage, lytImage;
    private PopupWindow pw;
    ImageView ImgMessage, ImgImage, ImgVideo;
    String Content, Showtype, Tag, Type, Pushtype, Distance, From_Time, To_Time, Display_Time, Image, Message, Video;
    Uri VideoUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_xml);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        lytAudio = (LinearLayout) findViewById(R.id.lytAudio);
        lytVideo = (LinearLayout) findViewById(R.id.lytVideo);
        lytMessage = (LinearLayout) findViewById(R.id.lytMessage);
        lytImage = (LinearLayout) findViewById(R.id.lytImage);
        ImgMessage = (ImageView) findViewById(R.id.ImgMessage);
        ImgImage = (ImageView) findViewById(R.id.ImgImage);
        ImgVideo = (ImageView) findViewById(R.id.ImgVideo);
        Video = String.valueOf("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4");
        VideoUri = Uri.parse(Video);

        queryController = new QueryController();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            final String rowId = extras.getString("messageId");
            queryController.selectById(rowId, BeaconDisplayModel.class, new QueryController.RealmModelCallback() {
                @Override
                public boolean onSuccessObjectResponse(RealmModel model) {
                    BeaconDisplayModel obj = (BeaconDisplayModel) model;
                    Tag = obj.getTag();
                    Type = String.valueOf(obj.getType());
                    Pushtype = String.valueOf(obj.getPushtype());
                    Showtype = String.valueOf(obj.getShow_type());
                    Content = String.valueOf(obj.getContent());
                    Distance = String.valueOf(obj.getDistance());
                    From_Time = String.valueOf(obj.getFrom_time());
                    To_Time = String.valueOf(obj.getTo_time());
                    Display_Time = String.valueOf(obj.getDisplay_time());
                    Image = String.valueOf(obj.getImage());
                    Message = String.valueOf(obj.getContent());
//                    Video = String.valueOf("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4");
//                    VideoUri = Uri.parse(Video);

                    Log.d("modeljibi", rowId + ", " + String.valueOf(obj));
                    Log.d("modelImage", Image);
                    Log.d("modelVideo", Video);

                    /*MessageText.setText(Message);
                    Picasso.with(context).load(Image).into(MessageImage);
                    MessageVideo.setMediaController(new MediaController(context));
                    MessageVideo.setVideoURI(VideoUri);
                    MessageVideo.requestFocus();
                    MessageVideo.start();

                    MessageVideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        // Close the progress bar and play the video
                        public void onPrepared(MediaPlayer mp) {
                        }
                    });
*/
                    return false;
                }
            });

            ImgMessage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MessagePopUp(view);
                }
            });

            ImgImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ImagePopUp(view);
                }
            });

            ImgVideo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    VideoPopUp(view);
                }
            });
        }
    }

    private void MessagePopUp(View v) {
        try {
            //We need to get the instance of the LayoutInflater, use the context of this activity
            LayoutInflater inflater = (LayoutInflater) SingleMessageActivity.this
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //Inflate the view from a predefined XML layout
            View layout = inflater.inflate(R.layout.layout_message, (ViewGroup) findViewById(R.id.MessageView));
            // create a 300px width and 470px height PopupWindow
            pw = new PopupWindow(layout, GridLayoutManager.LayoutParams.MATCH_PARENT, GridLayoutManager.LayoutParams.MATCH_PARENT, true);
            // display the popup in the center
            pw.showAtLocation(v, Gravity.CENTER, 0, 0);

            TextView mResultText = (TextView) layout.findViewById(R.id.textMessage);
            mResultText.setText(Content);
            ImageView close = (ImageView) layout.findViewById(R.id.close);
            close.setOnClickListener(cancel_button_click_listener);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void ImagePopUp(View v) {
        try {
            //We need to get the instance of the LayoutInflater, use the context of this activity
            LayoutInflater inflater = (LayoutInflater) SingleMessageActivity.this
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //Inflate the view from a predefined XML layout
            View layout = inflater.inflate(R.layout.layout_image, (ViewGroup) findViewById(R.id.MessageView));
            // create a 300px width and 470px height PopupWindow
            pw = new PopupWindow(layout, GridLayoutManager.LayoutParams.MATCH_PARENT, GridLayoutManager.LayoutParams.MATCH_PARENT, true);
            // display the popup in the center
            pw.showAtLocation(v, Gravity.CENTER, 0, 0);

            ImageView ImageMessage = (ImageView) layout.findViewById(R.id.ImageMessage);
            Picasso.with(context).load(Image).into(ImageMessage);
            ImageView close = (ImageView) layout.findViewById(R.id.close);
            close.setOnClickListener(cancel_button_click_listener);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void VideoPopUp(View v) {
        try {
            //We need to get the instance of the LayoutInflater, use the context of this activity
            LayoutInflater inflater = (LayoutInflater) SingleMessageActivity.this
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //Inflate the view from a predefined XML layout
            View layout = inflater.inflate(R.layout.layout_video, (ViewGroup) findViewById(R.id.MessageView));
            // create a 300px width and 470px height PopupWindow
            pw = new PopupWindow(layout, GridLayoutManager.LayoutParams.MATCH_PARENT, GridLayoutManager.LayoutParams.MATCH_PARENT, true);
            // display the popup in the center
            pw.showAtLocation(v, Gravity.CENTER, 0, 0);

            VideoView VideoMessage = (VideoView) layout.findViewById(R.id.VideoMessage);
            VideoMessage.start();
            ImageView close = (ImageView) layout.findViewById(R.id.close);

            //Creating MediaController
            MediaController mediaController = new MediaController(inflater.getContext());
            mediaController.setAnchorView(VideoMessage);
            VideoMessage.setMediaController(new MediaController(inflater.getContext()));

            VideoMessage.setVideoURI(VideoUri);
            VideoMessage.requestFocus();
            VideoMessage.start();

            VideoMessage.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                // Close the progress bar and play the video
                public void onPrepared(MediaPlayer mp) {
                }
            });

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
