package be.ugent.zeus.hydra.domain.entities.minerva;

import java8.util.Objects;
import org.threeten.bp.Year;

/**
 * Represents an academic year, but only in the strict sense: it only supports the year, not the actual start and end
 * date.
 *
 * An example would be year 2016-2017.
 *
 * This class uses {@link Year}, which uses ISO-8601. It might not be suitable for historic years.
 *
 * The natural ordering is ascending (on the start year).
 *
 * @author Niko Strijbol
 */
public final class AcademicYear implements Comparable<AcademicYear> {

    private final Year startYear;

    public AcademicYear(Year startYear) {
        this.startYear = startYear;
    }

    public AcademicYear(int startYear) {
        this.startYear = Year.of(startYear);
    }

    public Year getStartYear() {
        return startYear;
    }

    public Year getEndYear() {
        return startYear.plusYears(1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AcademicYear that = (AcademicYear) o;
        return Objects.equals(startYear, that.startYear);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startYear);
    }

    @Override
    public int compareTo(AcademicYear academicYear) {
        return startYear.compareTo(academicYear.startYear);
    }

    /**
     * A logical string representation of this year. Assume {@code %1} and {@code %2} the start and end year
     * respectively, the output will be {@code "%1 - %2"}. The format of the years depends on the format of {@link
     * Year#toString()}.
     *
     * @return A string representation of the academic year.
     */
    @Override
    public String toString() {
        return getStartYear().toString() + " - " + getEndYear().toString();
    }
}