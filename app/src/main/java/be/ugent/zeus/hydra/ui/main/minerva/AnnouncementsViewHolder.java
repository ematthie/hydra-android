package be.ugent.zeus.hydra.ui.main.minerva;

import android.graphics.Color;
import android.util.Pair;
import android.view.View;
import android.widget.TextView;

import be.ugent.zeus.hydra.R;
import be.ugent.zeus.hydra.domain.minerva.Announcement;
import be.ugent.zeus.hydra.ui.common.recyclerview.adapters.MultiSelectDiffAdapter;
import be.ugent.zeus.hydra.ui.common.recyclerview.viewholders.DataViewHolder;
import be.ugent.zeus.hydra.utils.DateUtils;

/**
 * @author Niko Strijbol
 */
public class AnnouncementsViewHolder extends DataViewHolder<Pair<Announcement, Boolean>> {

    private final TextView title;
    private final TextView subtitle;
    private final View backgroundHolder;
    private final MultiSelectDiffAdapter<Announcement> adapter;

    public AnnouncementsViewHolder(View itemView, MultiSelectDiffAdapter<Announcement> adapter) {
        super(itemView);
        this.adapter = adapter;
        title = itemView.findViewById(R.id.title);
        subtitle = itemView.findViewById(R.id.subtitle);
        backgroundHolder = itemView.findViewById(R.id.background_container);
    }

    @Override
    public void populate(final Pair<Announcement, Boolean> pair) {

        Announcement announcement = pair.first;

        toggleBackground();

        title.setText(announcement.getTitle());
        String infoText = itemView.getContext().getString(R.string.agenda_subtitle,
                announcement.getCourse().getTitle(),
                DateUtils.relativeDateTimeString(announcement.getLastEditedAt(), itemView.getContext(), false));
        subtitle.setText(infoText);

        itemView.setOnClickListener(v -> {
            // When we are in select mode, we just toggle the items. Otherwise we open them.
            if (adapter.hasSelected()) {
                toggleSelected();
            } else {
                //Intent intent = new Intent(resultStarter.getContext(), AnnouncementActivity.class);
                //intent.putExtra(AnnouncementActivity.ARG_ANNOUNCEMENT, (Parcelable) announcement);
                //resultStarter.startActivityForResult(intent, resultStarter.getRequestCode());
            }
        });

        itemView.setOnLongClickListener(v -> {
            toggleSelected();
            return true;
        });
    }

    private void toggleSelected() {
        adapter.setChecked(getAdapterPosition());
        toggleBackground();
    }

    private void toggleBackground() {
        if (adapter.isChecked(getAdapterPosition())) {
            backgroundHolder.setBackgroundColor(Color.WHITE);
        } else {
            backgroundHolder.setBackgroundColor(Color.TRANSPARENT);
        }
    }
}