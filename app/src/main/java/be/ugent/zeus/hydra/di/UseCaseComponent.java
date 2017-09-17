package be.ugent.zeus.hydra.di;

import be.ugent.zeus.hydra.domain.usecases.homefeed.GetHomeFeed;
import be.ugent.zeus.hydra.domain.usecases.minerva.*;
import dagger.Component;

import javax.inject.Singleton;

/**
 * The component.
 *
 * @author Niko Strijbol
 */
@Singleton
@Component(modules = {AppModule.class, DatabaseModule.class, DatabaseRepoModule.class, ImplementationModule.class, SimpleImplementationModule.class})
public interface UseCaseComponent {

    GetUnreadAnnouncements getUnreadAnnouncement();
    GetCoursesWithUnreadCount getCoursesWithUnreadCount();
    ChangeCourseOrder changeCourseOrder();
    GetSingleCourse getSingleCourse();
    GetAnnouncementsForCourse getUnreadAnnouncementsForCourse();
    GetCalendarForCourse getCalendarForCourse();
    GetSingleCalendarItem getSingleCalendarItem();
    GetSingleAnnouncement getSingleAnnouncement();
    MarkAnnouncementAsRead markAnnouncementAsRead();

    // Home feed use cases
    GetHomeFeed getHomeFeed();
}