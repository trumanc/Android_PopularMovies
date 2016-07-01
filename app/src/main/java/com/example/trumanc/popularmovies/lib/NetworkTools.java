package com.example.trumanc.popularmovies.lib;

import android.net.Uri;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * This is a utility class to separate commonly used network functionality so that it can be
 * shared effectively by whoever wants to use it.
 *
 * Created by truman on 7/1/16.
 */

/**
 * Given a uri for some network request, send that request and return the response as a raw String.
 * This function does not throw any errors, instead logging any exceptions and returning null if
 * the request wasn't successfully completed.
 */
public class NetworkTools {

    final static String LOG_TAG = NetworkTools.class.getSimpleName();

    public static String downloadUri(Uri requestUri) {


        try {

            URL request = new URL(requestUri.toString());

            HttpURLConnection conn = (HttpURLConnection) request.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.connect();

            Log.d(LOG_TAG, "In downloadUrl(url=" + request.toString() + "), Response code: "
                    + conn.getResponseCode());

            String result = readStreamIntoString(conn.getInputStream());
            Log.d(LOG_TAG, "In downloadUrl(url=\" + request.toString() + \"), Response : "
                    + result);
            // result may be null if readStream experienced an error.
            return result;

        } catch (IOException e) {
            Log.e(LOG_TAG, "Unable to complete url request.", e);
            return null;
        }

    }

    private static String readStreamIntoString(InputStream inStream) {
        // Convert inputStream to string using one of the methods found here:
        // http://stackoverflow.com/questions/309424/read-convert-an-inputstream-to-a-string
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];

        int length;
        try {
            while ((length = inStream.read(buffer)) != -1) {
                result.write(buffer, 0, length);
            }

            inStream.close();

        } catch (IOException e) {
            Log.e(LOG_TAG, "In readStreamIntoString: IOException", e);
            return null;
        }
        // TODO: Should we include the charset here?
        return result.toString();
    }
}
