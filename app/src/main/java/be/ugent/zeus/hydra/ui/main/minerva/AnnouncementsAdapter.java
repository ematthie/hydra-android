package be.ugent.zeus.hydra.ui.main.minerva;

import android.view.ViewGroup;

import be.ugent.zeus.hydra.R;
import be.ugent.zeus.hydra.domain.minerva.Announcement;
import be.ugent.zeus.hydra.ui.common.ViewUtils;
import be.ugent.zeus.hydra.ui.common.recyclerview.adapters.MultiSelectDiffAdapter;

/**
 * Adapteer for announcements.
 *
 * @author Niko Strijbol
 */
class AnnouncementsAdapter extends MultiSelectDiffAdapter<Announcement> {

    @Override
    public AnnouncementsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AnnouncementsViewHolder(ViewUtils.inflate(parent, R.layout.item_minerva_extended_announcement), this);
    }
}