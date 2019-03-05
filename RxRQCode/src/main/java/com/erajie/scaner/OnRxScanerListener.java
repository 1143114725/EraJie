package com.erajie.scaner;


import com.google.zxing.Result;

/**
 * @author EraJieZhang
 */

public interface OnRxScanerListener {
    void onSuccess(String type, Result result);

    void onFail(String type, String message);
}
