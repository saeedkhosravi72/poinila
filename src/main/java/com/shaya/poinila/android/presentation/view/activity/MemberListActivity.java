package com.shaya.poinila.android.presentation.view.activity;

import android.app.Fragment;
import android.os.Bundle;

import com.shaya.poinila.android.presentation.view.fragments.MemberListFragment;

public class MemberListActivity extends FragmentHostActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        handleIntentExtras();
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initUI() {

    }

    @Override
    protected android.support.v4.app.Fragment getHostedFragment() {
        return MemberListFragment.newInstance(mainEntityID, requestID);
    }

    @Override
    protected boolean withToolbar() {
        return true;
    }


}
