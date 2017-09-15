package be.ugent.zeus.hydra.data.database.minerva2.agenda;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;

import be.ugent.zeus.hydra.data.database.minerva2.course.CourseMapper;
import be.ugent.zeus.hydra.domain.entities.minerva.AgendaItem;
import be.ugent.zeus.hydra.domain.usecases.minerva.AgendaItemRepository;

import java.util.Collection;
import java.util.List;

import static be.ugent.zeus.hydra.utils.IterableUtils.transform;

/**
 * @author Niko Strijbol
 */
public class DatabaseAgendaItemRepository implements AgendaItemRepository {

    private final AgendaDao agendaDao;
    private final CourseMapper courseMapper;
    private final AgendaMapper agendaMapper;

    public DatabaseAgendaItemRepository(AgendaDao agendaDao, CourseMapper courseMapper, AgendaMapper agendaMapper) {
        this.agendaDao = agendaDao;
        this.courseMapper = courseMapper;
        this.agendaMapper = agendaMapper;
    }

    @Override
    public LiveData<AgendaItem> getOneLive(Integer integer) {
        return Transformations.map(agendaDao.getOneLive(integer), result -> agendaMapper.convert(result.agendaItem, courseMapper.courseToCourse(result.course)));
    }

    @Override
    public AgendaItem getOne(Integer integer) {
        AgendaDao.Result result = agendaDao.getOne(integer);
        return agendaMapper.convert(result.agendaItem, courseMapper.courseToCourse(result.course));
    }

    @Override
    public LiveData<List<AgendaItem>> getAllLive() {
        return Transformations.map(agendaDao.getAllLive(), results -> transform(results, result -> agendaMapper.convert(result.agendaItem, courseMapper.courseToCourse(result.course))));
    }

    @Override
    public List<AgendaItem> getAll() {
        return transform(agendaDao.getAll(), result -> agendaMapper.convert(result.agendaItem, courseMapper.courseToCourse(result.course)));
    }

    @Override
    public void insert(AgendaItem object) {
        agendaDao.insert(agendaMapper.convert(object));
    }

    @Override
    public void insert(Collection<AgendaItem> objects) {
        agendaDao.insert(transform(objects, agendaMapper::convert));
    }

    @Override
    public void update(AgendaItem object) {
        agendaDao.update(agendaMapper.convert(object));
    }

    @Override
    public void update(Collection<AgendaItem> objects) {
        agendaDao.update(transform(objects, agendaMapper::convert));
    }

    @Override
    public void delete(AgendaItem object) {
        agendaDao.delete(agendaMapper.convert(object));
    }

    @Override
    public void deleteById(Integer integer) {
        agendaDao.delete(integer);
    }

    @Override
    public void delete(Collection<AgendaItem> objects) {
        agendaDao.delete(transform(objects, agendaMapper::convert));
    }
}