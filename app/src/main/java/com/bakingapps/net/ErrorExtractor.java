package com.bakingapps.net;

import android.content.Context;

import com.bakingapps.R;

import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;

/**
 * Created by andiisfh on 09/06/17.
 */

public class ErrorExtractor {

    public static String getError(Context context, Throwable t) {
        String message = "";
        if (t instanceof SocketTimeoutException) {
            message = context.getResources().getString(R.string.rto);
        } else if (t instanceof ConnectException || t instanceof UnknownHostException || t instanceof TimeoutException || t instanceof SocketException) {
            message = context.getResources().getString(R.string.no_internet);
        } else {
            if (t.getMessage() != null)
                message = t.getMessage();
        }

        return message;
    }
}
