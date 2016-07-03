package be.ugent.zeus.hydra.models.cards;

import org.joda.time.DateTime;
import org.joda.time.Duration;

import be.ugent.zeus.hydra.adapters.HomeCardAdapter;
import be.ugent.zeus.hydra.models.minerva.Announcement;
import be.ugent.zeus.hydra.models.minerva.Course;

/**
 * Created by feliciaan on 30/06/16.
 */
public class MinervaAnnouncementCard extends HomeCard {

    private final Announcement announcement;

    public MinervaAnnouncementCard(Announcement announcement, Course course) {
        this.announcement = announcement;
        this.announcement.setCourse(course);
    }

    @Override
    public int getPriority() {
        DateTime jodadate = new DateTime(this.getAnnouncement().getDate());
        Duration duration = new Duration(jodadate, new DateTime());
        return (int) (1000 - (duration.getStandardDays()*100));    }

    @Override
    public HomeCardAdapter.HomeType getCardType() {
        return HomeCardAdapter.HomeType.MINERVA_ANNOUNCEMENT;
    }

    public Announcement getAnnouncement() {
        return announcement;
    }
}
