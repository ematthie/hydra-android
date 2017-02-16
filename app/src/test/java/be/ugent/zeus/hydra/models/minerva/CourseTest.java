package be.ugent.zeus.hydra.models.minerva;

import be.ugent.zeus.hydra.testing.Utils;
import be.ugent.zeus.hydra.models.ModelTest;
import nl.jqno.equalsverifier.Warning;
import org.junit.Test;

/**
 * @author Niko Strijbol
 */
public class CourseTest extends ModelTest<Course> {

    public CourseTest() {
        super(Course.class);
    }

    @Test
    public void equalsAndHash() {
        Utils.defaultVerifier(Course.class)
                .suppress(Warning.NONFINAL_FIELDS)
                .withOnlyTheseFields("id")
                .verify();
    }
}