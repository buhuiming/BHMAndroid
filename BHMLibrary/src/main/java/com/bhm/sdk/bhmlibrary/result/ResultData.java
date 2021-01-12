package com.bhm.sdk.bhmlibrary.result;

import android.content.Intent;

/**
 * Created by bhm on 2018/9/19.
 */

public class ResultData {

    private int resultCode;
    private Intent data;

    public ResultData(int resultCode, Intent data) {
        this.resultCode = resultCode;
        this.data = data;
    }


    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public Intent getData() {

        return data;
    }

    public void setData(Intent data) {
        this.data = data;
    }
}