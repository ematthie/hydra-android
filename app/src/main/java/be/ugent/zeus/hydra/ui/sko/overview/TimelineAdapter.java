package be.ugent.zeus.hydra.ui.sko.overview;

import android.view.ViewGroup;
import be.ugent.zeus.hydra.R;
import be.ugent.zeus.hydra.data.models.sko.TimelinePost;
import be.ugent.zeus.hydra.ui.common.recyclerview.ItemAdapter;
import be.ugent.zeus.hydra.utils.ViewUtils;
import be.ugent.zeus.hydra.utils.customtabs.ActivityHelper;

/**
 * @author Niko Strijbol
 */
public class TimelineAdapter extends ItemAdapter<TimelinePost, TimelineViewHolder> {

    private final ActivityHelper helper;

    public TimelineAdapter(ActivityHelper helper) {
        this.helper = helper;
    }

    public ActivityHelper getHelper() {
        return helper;
    }

    @Override
    public TimelineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TimelineViewHolder(ViewUtils.inflate(parent, R.layout.item_sko_timeline_post), this);
    }
}