package be.ugent.zeus.hydra.repository.requests;

import android.content.Context;

import be.ugent.zeus.hydra.domain.requests.Request;
import be.ugent.zeus.hydra.domain.requests.Result;
import be.ugent.zeus.hydra.repository.data.BaseLiveData;
import be.ugent.zeus.hydra.repository.data.RequestLiveData;

/**
 * @author Niko Strijbol
 */
@Deprecated
public class RequestRepository<M> {

    private final Context context;

    public RequestRepository(Context context) {
        this.context = context.getApplicationContext();
    }

    public BaseLiveData<Result<M>> load(Request<M> request) {
        return new RequestLiveData<>(context, request);
    }
}