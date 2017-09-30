package be.ugent.zeus.hydra.ui.common;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;

import be.ugent.zeus.hydra.domain.requests.Result;
import be.ugent.zeus.hydra.domain.utils.RefreshLiveData;

/**
 * @author Niko Strijbol
 */
public abstract class BetterRefreshViewModel<OUT> extends AndroidViewModel implements SwipeRefreshLayout.OnRefreshListener{

    private static final String TAG = "RefreshViewModel";

    private RefreshLiveData<Result<OUT>> data;
    private LiveData<Boolean> refreshing;

    public BetterRefreshViewModel(Application application) {
        super(application);
    }

    protected abstract RefreshLiveData<Result<OUT>> executeUseCase();

    public RefreshLiveData<Result<OUT>> getData() {
        if (data == null) {
            data = executeUseCase();
        }
        return data;
    }

    @Override
    public void onRefresh() {
        getData().requestRefresh();
    }

    /**
     * Construct the refresh live data.
     *
     * @return The refresh live data.
     */
    private LiveData<Boolean> buildRefreshLiveData() {
        MediatorLiveData<Boolean> result = new MediatorLiveData<>();
        result.addSource(getData(), data -> result.setValue(data != null && !data.isDone()));
        getData().registerRefreshListener(() -> result.postValue(true));
        return result;
    }

    /**
     * @return The refreshing status.
     */
    public LiveData<Boolean> getRefreshing() {
        if (refreshing == null) {
            refreshing = buildRefreshLiveData();
        }

        return refreshing;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d(TAG, "Destroyed the view model.");
        refreshing = null;
        data = null;
    }
}