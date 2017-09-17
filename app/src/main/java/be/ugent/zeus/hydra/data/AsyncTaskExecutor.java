package be.ugent.zeus.hydra.data;

import android.os.AsyncTask;

import be.ugent.zeus.hydra.domain.usecases.Executor;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Executor that uses a background thread.
 *
 * @author Niko Strijbol
 */
@Singleton
public class AsyncTaskExecutor implements Executor {

    @Inject
    AsyncTaskExecutor() {
    }

    /**
     * This will execute the runnable using {@link AsyncTask#execute(Runnable)}.
     *
     * @param runnable The runnable to execute.
     */
    @Override
    public void execute(Runnable runnable) {
        AsyncTask.execute(runnable);
    }
}