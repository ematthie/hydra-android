package be.ugent.zeus.hydra.models.minerva;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;
import be.ugent.zeus.hydra.HydraApplication;
import be.ugent.zeus.hydra.loader.cache.Cache;
import be.ugent.zeus.hydra.loader.cache.exceptions.RequestFailureException;
import be.ugent.zeus.hydra.loader.cache.file.SerializeCache;
import be.ugent.zeus.hydra.loader.requests.Request;
import be.ugent.zeus.hydra.loader.requests.RequestExecutor;
import be.ugent.zeus.hydra.requests.minerva.WhatsNewRequest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

/**
 * Wrapper for a course and announcements.
 *
 * @author Niko Strijbol
 */
public class CourseWrapper {

    private static final String TAG = "CourseWrapper";

    private Course course;
    private List<Announcement> announcements = Collections.emptyList();
    private HydraApplication application;
    private Cache cache;

    private AsyncTask task;

    public CourseWrapper(Course c, HydraApplication application) {
        this.course = c;
        this.application = application;
        this.cache = new SerializeCache(application.getApplicationContext());
    }

    /**
     * Cancel the task if it exists.
     */
    public void cancelLoading() {
        if(task != null) {
            Log.d(TAG, "Canceled request for " + course);
            task.cancel(false);
            task = null;
        }
    }

    /**
     * Load the announcements for this course.
     *
     * @param callback The callback.
     */
    public void loadAnnouncements(final RequestExecutor.Callback<List<Announcement>> callback) {

        //It is already loaded.
        if(!announcements.isEmpty()) {
            callback.receiveData(announcements);
            return;
        }

        //Request
        final WhatsNewRequest whatsNewRequest = new WhatsNewRequest(course, application);

        //Wrap in request for cache
        Request<WhatsNew> request = new Request<WhatsNew>() {
            @NonNull
            @Override
            public WhatsNew performRequest() throws RequestFailureException {
                return cache.get(whatsNewRequest);
            }
        };

        //Do request
        task = RequestExecutor.executeAsync(request, new RequestExecutor.Callback<WhatsNew>() {
            @Override
            public void receiveData(@NonNull WhatsNew data) {
                ListIterator<Announcement> li = data.getAnnouncements().listIterator(data.getAnnouncements().size());
                announcements = new ArrayList<>();
                // Iterate in reverse.
                while (li.hasPrevious()) {
                    Announcement a = li.previous();
                    a.setCourse(course);
                    announcements.add(a);
                }
                task = null;
                callback.receiveData(data.getAnnouncements());
            }

            @Override
            public void receiveError(RequestFailureException e) {
                Log.e(TAG, "Error while getting announcements", e);
                task = null;
                callback.receiveError(e);
            }
        });
    }

    /**
     * @return The course.
     */
    public Course getCourse() {
        return course;
    }

    /**
     * @return The announcements.
     */
    public List<Announcement> getAnnouncements() {
        return announcements;
    }
}