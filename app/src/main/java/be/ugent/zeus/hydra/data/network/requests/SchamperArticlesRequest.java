package be.ugent.zeus.hydra.data.network.requests;

import android.support.annotation.NonNull;

import be.ugent.zeus.hydra.data.models.schamper.Article;
import be.ugent.zeus.hydra.data.network.Endpoints;
import be.ugent.zeus.hydra.data.network.JsonSpringRequest;
import be.ugent.zeus.hydra.repository.Cache;
import be.ugent.zeus.hydra.repository.requests.CacheableRequest;

/**
 * Request to get Schamper articles.
 *
 * @author feliciaan
 */
public class SchamperArticlesRequest extends JsonSpringRequest<Article[]> implements CacheableRequest<Article[]> {

    public SchamperArticlesRequest() {
        super(Article[].class);
    }

    @NonNull
    @Override
    public String getCacheKey() {
        return "schamper.dailies";
    }

    @NonNull
    @Override
    protected String getAPIUrl() {
        return Endpoints.ZEUS_API_URL_1 + "schamper/daily_android.json";
    }

    @Override
    public long getCacheDuration() {
        return Cache.ONE_DAY;
    }
}