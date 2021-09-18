package com.shaya.poinila.android.presentation.view.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import com.shaya.poinila.android.presentation.R;
import com.shaya.poinila.android.presentation.presenter.RecyclerViewAdapter;
import com.shaya.poinila.android.presentation.view.LoaderList;

import butterknife.Bind;
import data.event.BaseEvent;

/**
 * Created by iran on 11/25/2015.
 */
public abstract class ListBusDialogFragment<T> extends BusDialogFragment implements LoaderList{
    private static final String CLICKED_ITEM_POSITION = "clicked item position";
    protected boolean requestingIsLocked = false;
    public String bookmark;
    protected int clickedItemPosition = -1;
    @Bind(R.id.recycler_view) protected RecyclerView mRecyclerView;
    private RecyclerViewAdapter<T, ?> mAdapter;


    @Override @CallSuper
    protected void loadStateFromBundle(Bundle savedInstanceState) {
        clickedItemPosition = savedInstanceState.getInt(CLICKED_ITEM_POSITION);
    }

    @Override @CallSuper
    protected void saveStateToBundle(Bundle outState) {
        outState.putInt(CLICKED_ITEM_POSITION, clickedItemPosition);
    }

    @Override
    @CallSuper
    protected void initUI(Context context) {
        if(getRecyclerViewListener() != null){
            mRecyclerView.addOnScrollListener(getRecyclerViewListener());
        }
    }

    /**
     * Called in {@link #onStart()} after {@link #initUI(Context)} so it's safe to assume class variables
     * are initialized.
     * @return
     */
    protected abstract RecyclerView.OnScrollListener getRecyclerViewListener();

    public void onLoadMore(){
        if (!requestingIsLocked) {
            requestingIsLocked = true;
            requestForMoreData();
        }
    }

    @CallSuper
    public void onSuccessfulListData(BaseEvent baseEvent, String newBookmark){
        requestingIsLocked = false;
        bookmark = newBookmark;
    }

    public abstract void requestForMoreData();

    @Override
    public ViewGroup getLoadableView() {
        return mRecyclerView;
    }

    public RecyclerViewAdapter<T, ?> getRecyclerViewAdapter(){
        if (mAdapter == null)
            mAdapter = createAndReturnRVAdapter();
        return mAdapter;
    }

    @CallSuper
    protected void onGettingListDataResponse(BaseEvent event, String responseBookmark) {
        if (isListDataResponseValid(event, responseBookmark))
            onSuccessfulListData(event, responseBookmark);
    }

    @CallSuper
    protected boolean isListDataResponseValid(BaseEvent baseEvent, String responseBookmark){
        // bookmark != null &&
        return checkBookMark(this.bookmark, responseBookmark); // this.bookmark may be null
    }


    public boolean checkBookMark(String pageBookmark, String serverBookmark) {
        return serverBookmark == null || !serverBookmark.equals(pageBookmark);
    }

    public abstract RecyclerViewAdapter<T, ?> createAndReturnRVAdapter();
}
