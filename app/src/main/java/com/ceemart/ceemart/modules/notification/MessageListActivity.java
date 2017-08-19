package com.ceemart.ceemart.modules.notification;

import android.content.Intent;
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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.ceemart.ceemart.R;
import com.ceemart.ceemart.adapter.MessageListAdapter;
import com.ceemart.ceemart.helpers.Message;

import java.util.ArrayList;
import java.util.List;

public class MessageListActivity extends AppCompatActivity {

    TextView message;
    private RecyclerView recyclerView;
    private MessageListAdapter adapter;
    private List<Message> messageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_list);
        message = (TextView) findViewById(R.id.message);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initCollapsingToolbar();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        messageList = new ArrayList<>();
        adapter = new MessageListAdapter(this, messageList, new MessageCallback() {
            @Override
            public boolean onSuccessObjectResponse(Integer id) {
                Toast.makeText(getApplicationContext(), "jibi selected" + id, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), SingleMessageActivity.class);
                intent.setAction(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_LAUNCHER);
                intent.putExtra("messageId", String.valueOf(id));
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
//                finish();
                return false;
            }
        });
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
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
        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
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
        int[] covers = new int[]{
                R.drawable.album1, R.drawable.album2, R.drawable.album3, R.drawable.album4,
                R.drawable.album5, R.drawable.album6, R.drawable.album7, R.drawable.album8,
                R.drawable.album9, R.drawable.album10, R.drawable.album11};



        Message a = new Message(1, "True Romance", "Alukkas", covers[0]);
        messageList.add(a);

        a = new Message(1, "Xscpae", "Alukkas", covers[1]);
        messageList.add(a);

        a = new Message(2, "Maroon 5", "Alukkas", covers[2]);
        messageList.add(a);

        a = new Message(1, "Born to Die", "Alukkas", covers[3]);
        messageList.add(a);

        a = new Message(1, "Honeymoon", "Alukkas", covers[4]);
        messageList.add(a);

        a = new Message(1, "I Need a Doctor", "Alukkas", covers[5]);
        messageList.add(a);

        a = new Message(1, "Loud", "Alukkas", covers[6]);
        messageList.add(a);

        a = new Message(1, "Legend", "Alukkas", covers[7]);
        messageList.add(a);

        a = new Message(1, "Hello", "Alukkas", covers[8]);
        messageList.add(a);

        a = new Message(1, "Greatest Hits", "Alukkas", covers[9]);
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

    /* interface MessageCallback
      *  callback function for message
      *
      *  @param :
      *
      *  @retun
      */
    public interface MessageCallback {
        boolean onSuccessObjectResponse(Integer id);
    }
}
