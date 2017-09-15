package be.ugent.zeus.hydra.data.database.minerva2.announcement;

import be.ugent.zeus.hydra.data.database.minerva2.TutorMapper;
import be.ugent.zeus.hydra.domain.entities.minerva.AcademicYear;
import be.ugent.zeus.hydra.domain.entities.minerva.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * @author Niko Strijbol
 */
@Mapper(uses = {TutorMapper.class, AcademicYear.class})
public abstract class AnnouncementMapper {

    @Mapping(source = "course.id", target = "courseId")
    public abstract Announcement convert(be.ugent.zeus.hydra.domain.entities.minerva.Announcement item);

    @Mappings({
            @Mapping(source = "courseInstance", target = "course"),
            @Mapping(source = "item.id", target = "id"),
            @Mapping(source = "item.title", target = "title")
    })
    public abstract be.ugent.zeus.hydra.domain.entities.minerva.Announcement convert(Announcement item, Course courseInstance);
}