package com.psychic_pogo;

import android.os.AsyncTask;
import android.os.Looper;
import android.util.Log;


/**
 * Created by Ultramarine on 25/07/2016.
 */
public class BackgroundTask extends AsyncTask<String, Void, String> {

    private Exception exception = null;


    protected String doInBackground(String... urls) {
        try {

            System.out.println("Main Thread: "+(Thread.currentThread() == Looper.getMainLooper().getThread()));
            OkHttpClient httpClient = new OkHttpClient();
            RequestEnvelopeOuterClass.RequestEnvelope.AuthInfo auth = new GoogleLogin(httpClient).login("token");
            PokemonGo go = new PokemonGo(auth, httpClient);
            Log.v("TAG1",go.getPlayerProfile().toString());

            return "Worked!";
        } catch (Exception e) {
            this.exception = e;
            return null;
        }
    }

    protected void onPostExecute(String result) {
        // TODO: check this.exception
        // TODO: do something with the feed
        if (this.exception==null)
        {
            System.out.println("Success!");
        }
        else
        {
            System.out.println("Failure...");
            this.exception.printStackTrace();
        }
    }
}
