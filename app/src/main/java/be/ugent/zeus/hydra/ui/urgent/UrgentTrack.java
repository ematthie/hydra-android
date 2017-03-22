package be.ugent.zeus.hydra.ui.urgent;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import be.ugent.zeus.hydra.R;
import be.ugent.zeus.hydra.data.network.requests.UrgentUrlRequest;
import be.ugent.zeus.hydra.data.network.exceptions.RequestFailureException;
import be.ugent.zeus.hydra.service.urgent.track.Track;
import java8.util.function.Consumer;

/**
 * The track for Urgent.
 *
 * @author Niko Strijbol
 */
public class UrgentTrack implements Track {

    private static final String TAG = "UrgentTrack";
    private static final int URGENT_ID = 1;

    private final Context context;

    public UrgentTrack(Context context) {
        this.context = context.getApplicationContext(); //Prevent leaks.
    }

    @Override
    public int getId() {
        return URGENT_ID;
    }

    @Override
    @Nullable
    public String getArtist() {
        return null;
    }

    @Override
    @NonNull
    public String getTitle() {
        return "Urgent.fm";
    }

    @Override
    public void getUrl(final Consumer<String> consumer) {
        new UrlTask(consumer).execute();
    }

    @Override
    @Nullable
    public Bitmap getAlbumArtwork() {
        return BitmapFactory.decodeResource(context.getResources(), R.drawable.logo_urgent);
    }

    /**
     * Task to get the URL. This class is not static, since we need the context.
     */
    private static class UrlTask extends AsyncTask<Void, Void, String> {

        private final Consumer<String> consumer;

        private UrlTask(Consumer<String> consumer) {
            this.consumer = consumer;
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                return new UrgentUrlRequest().performRequest() + ".mp3";
            } catch (RequestFailureException e) {
                Log.w(TAG, "Error while getting url: ", e);
                return null;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            consumer.accept(s);
        }
    }
}