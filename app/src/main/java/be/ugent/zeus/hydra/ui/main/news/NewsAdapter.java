package be.ugent.zeus.hydra.ui.main.news;

import android.view.ViewGroup;

import be.ugent.zeus.hydra.R;
import be.ugent.zeus.hydra.data.models.association.UgentNewsItem;
import be.ugent.zeus.hydra.ui.common.ViewUtils;

/**
 * @author Niko Strijbol
 * @author ellen
 */
class NewsAdapter extends be.ugent.zeus.hydra.ui.common.recyclerview.adapters.DiffAdapter<UgentNewsItem, NewsItemViewHolder> {

    protected NewsAdapter() {
        super();
    }

    @Override
    public NewsItemViewHolder onCreateViewHolder(ViewGroup p, int viewType) {
        return new NewsItemViewHolder(ViewUtils.inflate(p, R.layout.item_news));
    }
}