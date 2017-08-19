package com.ceemart.ceemart.modules.notification;

import android.content.res.Resources;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ceemart.ceemart.R;
import com.ceemart.ceemart.adapter.SingleMessageAdapter;
import com.ceemart.ceemart.helpers.Message;

import java.util.ArrayList;
import java.util.List;

public class SingleMessageActivity extends AppCompatActivity {

    TextView message;
    private RecyclerView recyclerView;
    private SingleMessageAdapter adapter;
    private List<Message> messageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_message);
        message = (TextView) findViewById(R.id.message);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initCollapsingToolbar();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        messageList = new ArrayList<>();

        adapter = new SingleMessageAdapter(this, messageList);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        prepareMessages();

        try {
            Glide.with(this).load(R.drawable.cover).into((ImageView) findViewById(R.id.backdrop));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Adding few messages for testing
     */
    /**
     * Initializing collapsing toolbar
     * Will show and hide the toolbar title on scroll
     */

    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(getString(R.string.app_name));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }

    private void prepareMessages() {
        int[] covers = new int[]{R.drawable.album1, R.drawable.album2, R.drawable.album3,R.drawable.album1};
        
        Uri[] Videos = new Uri[]{Uri.parse("http://www.html5videoplayer.net/videos/toystory.mp4")};

        Message a;
        a = new Message(1, "True Romance", "Alukkas", covers[0],Videos[0]);
        messageList.add(a);

        a = new Message(1, "Xscpae", "Alukkas", covers[1],Videos[0]);
        messageList.add(a);

        a = new Message(1, "Maroon 5", "Alukkas", covers[2],Videos[0]);
        messageList.add(a);

        a = new Message(1, "Aswanth", "Aswanth", covers[3],Videos[0]);
        messageList.add(a);


        adapter.notifyDataSetChanged();
    }

    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_single_message);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//
//        lytAudio = (LinearLayout) findViewById(R.id.lytAudio);
//        lytVideo = (LinearLayout) findViewById(R.id.lytVideo);
//        lytMessage = (LinearLayout) findViewById(R.id.lytMessage);
//        lytImage = (LinearLayout) findViewById(R.id.lytImage);
//        ImgMessage = (ImageView) findViewById(R.id.ImgMessage);
//        ImgImage = (ImageView) findViewById(R.id.ImgImage);
//        ImgVideo = (ImageView) findViewById(R.id.ImgVideo);
//        video = String.valueOf("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4");
//        VideoUri = Uri.parse(video);
//
//        queryController = new QueryController();
//        Bundle extras = getIntent().getExtras();
//        if (extras != null) {
//            final String rowId = extras.getString("messageId");
//            queryController.selectById(rowId, BeaconDisplayModel.class, new QueryController.RealmModelCallback() {
//                @Override
//                public boolean onSuccessObjectResponse(RealmModel model) {
//                    BeaconDisplayModel obj = (BeaconDisplayModel) model;
//                    tag = obj.getTag();
//                    type = String.valueOf(obj.getType());
//                    pushType = String.valueOf(obj.getPushtype());
//                    showType = String.valueOf(obj.getShow_type());
//                    content = String.valueOf(obj.getContent());
//                    distance = String.valueOf(obj.getDistance());
//                    fromTime = String.valueOf(obj.getFrom_time());
//                    toTime = String.valueOf(obj.getTo_time());
//                    displayTime = String.valueOf(obj.getDisplay_time());
//                    image = String.valueOf(obj.getImage());
//                    message = String.valueOf(obj.getContent());
////                    Video = String.valueOf("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4");
////                    VideoUri = Uri.parse(Video);
//
//                    Log.d("modeljibi", rowId + ", " + String.valueOf(obj));
//                    Log.d("modelImage", image);
//                    Log.d("modelVideo", video);
//
//                    /*MessageText.setText(Message);
//                    Picasso.with(context).load(Image).into(MessageImage);
//                    MessageVideo.setMediaController(new MediaController(context));
//                    MessageVideo.setVideoURI(VideoUri);
//                    MessageVideo.requestFocus();
//                    MessageVideo.start();
//
//                    MessageVideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//                        // Close the progress bar and play the video
//                        public void onPrepared(MediaPlayer mp) {
//                        }
//                    });
//*/
//                    return false;
//                }
//            });
//
//            ImgMessage.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    MessagePopUp(view);
//                }
//            });
//
//            ImgImage.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    ImagePopUp(view);
//                }
//            });
//
//            ImgVideo.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    VideoPopUp(view);
//                }
//            });
//        }
//    }
//
//    private void MessagePopUp(View v) {
//        try {
//            //We need to get the instance of the LayoutInflater, use the context of this activity
//            LayoutInflater inflater = (LayoutInflater) SingleMessageActivity.this
//                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            //Inflate the view from a predefined XML layout
//            View layout = inflater.inflate(R.layout.layout_message, (ViewGroup) findViewById(R.id.MessageView));
//            // create a 300px width and 470px height PopupWindow
//            pw = new PopupWindow(layout, GridLayoutManager.LayoutParams.MATCH_PARENT, GridLayoutManager.LayoutParams.MATCH_PARENT, true);
//            // display the popup in the center
//            pw.showAtLocation(v, Gravity.CENTER, 0, 0);
//
//            TextView mResultText = (TextView) layout.findViewById(R.id.textMessage);
//            mResultText.setText(content);
//            ImageView close = (ImageView) layout.findViewById(R.id.close);
//            close.setOnClickListener(cancel_button_click_listener);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void ImagePopUp(View v) {
//        try {
//            //We need to get the instance of the LayoutInflater, use the context of this activity
//            LayoutInflater inflater = (LayoutInflater) SingleMessageActivity.this
//                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            //Inflate the view from a predefined XML layout
//            View layout = inflater.inflate(R.layout.layout_image, (ViewGroup) findViewById(R.id.MessageView));
//            // create a 300px width and 470px height PopupWindow
//            pw = new PopupWindow(layout, GridLayoutManager.LayoutParams.MATCH_PARENT, GridLayoutManager.LayoutParams.MATCH_PARENT, true);
//            // display the popup in the center
//            pw.showAtLocation(v, Gravity.CENTER, 0, 0);
//
//            ImageView ImageMessage = (ImageView) layout.findViewById(R.id.ImageMessage);
//            Picasso.with(context).load(image).into(ImageMessage);
//            ImageView close = (ImageView) layout.findViewById(R.id.close);
//            close.setOnClickListener(cancel_button_click_listener);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void VideoPopUp(View v) {
//        try {
//            //We need to get the instance of the LayoutInflater, use the context of this activity
//            LayoutInflater inflater = (LayoutInflater) SingleMessageActivity.this
//                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            //Inflate the view from a predefined XML layout
//            View layout = inflater.inflate(R.layout.layout_video, (ViewGroup) findViewById(R.id.MessageView));
//            // create a 300px width and 470px height PopupWindow
//            pw = new PopupWindow(layout, GridLayoutManager.LayoutParams.MATCH_PARENT, GridLayoutManager.LayoutParams.MATCH_PARENT, true);
//            // display the popup in the center
//            pw.showAtLocation(v, Gravity.CENTER, 0, 0);
//
//            VideoView VideoMessage = (VideoView) layout.findViewById(R.id.VideoMessage);
//            VideoMessage.start();
//            ImageView close = (ImageView) layout.findViewById(R.id.close);
//
//            //Creating MediaController
//            MediaController mediaController = new MediaController(inflater.getContext());
//            mediaController.setAnchorView(VideoMessage);
//            VideoMessage.setMediaController(new MediaController(inflater.getContext()));
//
//            VideoMessage.setVideoURI(VideoUri);
//            VideoMessage.requestFocus();
//            VideoMessage.start();
//
//            VideoMessage.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//                // Close the progress bar and play the video
//                public void onPrepared(MediaPlayer mp) {
//                }
//            });
//
//            close.setOnClickListener(cancel_button_click_listener);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private View.OnClickListener cancel_button_click_listener = new View.OnClickListener() {
//        public void onClick(View v) {
//            pw.dismiss();
//        }
//    };

}
