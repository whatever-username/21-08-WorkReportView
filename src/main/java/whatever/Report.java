package whatever;

import java.io.Serializable;

/**
 * Created by Innokentiy on 21.08.2019.
 */
public class Report implements Serializable{
    private Long number;
    private String name;
    private String description;
    private Class reportClass;

    public Report(Long number, String name, String description, Class reportClass) {
        this.number = number;
        this.name = name;
        this.description = description;
        this.reportClass = reportClass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Report report = (Report) o;

        if (name != null ? !name.equals(report.name) : report.name != null) return false;
        return description != null ? description.equals(report.description) : report.description == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
