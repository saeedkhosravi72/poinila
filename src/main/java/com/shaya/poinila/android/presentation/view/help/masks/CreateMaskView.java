package com.shaya.poinila.android.presentation.view.help.masks;

import android.content.Context;
import android.view.View;

import com.shaya.poinila.android.presentation.R;

/**
 * Created by iran on 5/24/2016.
 */
public class CreateMaskView extends BaseMaskView{
    protected int level = 1;


    public CreateMaskView(Context context, View itemView) {
        super(context, itemView);
    }

    @Override
    protected void init() {
        descView.setText(R.string.help_create_description);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.mask_create;
    }

    @Override
    protected int getDescViewId() {
        return R.id.mask_create_description;
    }

    @Override
    protected int getNextBtnId() {
        return R.id.mask_create_btn;
    }

    @Override
    public void onClick(View v) {

        level++;

        switch (level){
            default:
                if(btnListener != null)
                    btnListener.onClick(nextBtn);
        }


    }


}
