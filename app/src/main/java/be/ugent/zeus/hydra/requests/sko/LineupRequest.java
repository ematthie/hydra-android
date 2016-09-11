package be.ugent.zeus.hydra.requests.sko;

import android.support.annotation.NonNull;

import be.ugent.zeus.hydra.cache.Cache;
import be.ugent.zeus.hydra.models.sko.Stages;

/**
 * Request SKO lineup data.
 *
 * @author Niko Strijbol
 */
public class LineupRequest extends SkoRequest<Stages> {

    public LineupRequest() {
        super(Stages.class);
    }

    @NonNull
    @Override
    public String getCacheKey() {
        return "lineup.json";
    }

    @Override
    public long getCacheDuration() {
        return Cache.ONE_DAY;
    }
}