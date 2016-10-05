package com.itis.androidlab.contentprovider;

import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.itis.androidlab.contentprovider.adapters.TagsAdapter;
import com.itis.androidlab.contentprovider.models.Tag;
import com.itis.androidlab.contentprovider.provider.TagsContract;
import com.itis.androidlab.contentprovider.utils.ContentValuesHelper;
import com.itis.androidlab.contentprovider.utils.CursorHelper;
import com.orhanobut.logger.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    protected static final int TAGS_LOADER = 0;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private List<Tag> mTags;
    private TagsAdapter mTagsAdapter;

    protected Map<ContentObserver, Uri> contentObservers = new HashMap<>();
    protected AsyncComplaintsParser mAsyncComplaintsParser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_main);
        ButterKnife.bind(this);

        setupRecyclerView(mRecyclerView);

        addContentObserver(new ComplaintObserver(new Handler()), TagsContract.Tags.CONTENT_URI);
        // When user 1st time launches app we trying to load last cached news
        // if they exist
        if (null == savedInstanceState)
            getSupportLoaderManager().initLoader(TAGS_LOADER, null, this);
    }

    private void setupRecyclerView(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setAdapter(mTagsAdapter = new TagsAdapter());
    }

    @Override
    public void onPause() {
        super.onPause();
        getLoaderManager().destroyLoader(TAGS_LOADER);
        if (mAsyncComplaintsParser != null && mAsyncComplaintsParser.getStatus() != AsyncTask.Status.FINISHED) {
            mAsyncComplaintsParser.cancel(true);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mTags == null && getLoaderManager().getLoader(TAGS_LOADER) == null)
            restartLoader(TAGS_LOADER);
    }

    protected void addContentObserver(ContentObserver contentObserver, Uri uri) {
        contentObservers.put(contentObserver, uri);
    }

    protected void restartLoader(int loaderId) {
        getSupportLoaderManager().restartLoader(loaderId, null, this);
    }


    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        // Do nothing
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Uri uri = TagsContract.Tags.CONTENT_URI;

        String selection = null;
        String[] selectionArgs = null;

        // And we need to extract news from the db in the descending order by
        // publication date.
        return new CursorLoader(
                MainActivity.this,
                uri,
                null, // leaving "columns" null just returns all the columns
                selection, // cols for "where" clause
                selectionArgs, // values for "where" clause
                TagsContract.Tags.TAG_ID + " DESC" // values for "order" clause (asc/desc)
        );
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAsyncComplaintsParser = new AsyncComplaintsParser();
        mAsyncComplaintsParser.execute(data);
    }

    @OnClick(R.id.fab)
    public void onAddButtonClick() {
        Tag tag = new Tag();
        getContentResolver().insert(TagsContract.Tags.CONTENT_URI, ContentValuesHelper.mapTagToContentValues(tag));
    }


    public class ComplaintObserver extends ContentObserver {

        public ComplaintObserver(Handler handler) {
            super(handler);
        }

        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            Logger.d("Content in complaint table is changed.");
            restartLoader(TAGS_LOADER);
        }
    }

    private class AsyncComplaintsParser extends AsyncTask<Cursor, Void, List<Tag>> {

        @Override
        protected List<Tag> doInBackground(Cursor... params) {
            return CursorHelper.parseTags(params[0]);
        }

        @Override
        protected void onPostExecute(List<Tag> parsed) {
            mTagsAdapter.setTags(mTags = parsed);
        }
    }
}
