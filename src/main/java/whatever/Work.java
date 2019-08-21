package whatever;

/**
 * Created by Innokentiy on 21.08.2019.
 */
public class Work {
    private String address;
    private String description;
    private String executor;
    private String startDatePlan;
    private String finishDatePlan;
    private String source;
    private String norm;

    public Work(String address, String description, String executor, String startDatePlan, String finishDatePlan, String source) {
        this.address = address;
        this.norm = norm;
        this.description = description;
        this.executor = executor;
        this.startDatePlan = startDatePlan;
        this.finishDatePlan = finishDatePlan;
        this.source = source;
    }

    public String getNorm() {
        return norm;
    }

    public void setNorm(String norm) {
        this.norm = norm;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExecutor() {
        return executor;
    }

    public void setExecutor(String executor) {
        this.executor = executor;
    }

    public String getStartDatePlan() {
        return startDatePlan;
    }

    public void setStartDatePlan(String startDatePlan) {
        this.startDatePlan = startDatePlan;
    }

    public String getFinishDatePlan() {
        return finishDatePlan;
    }

    public void setFinishDatePlan(String finishDatePlan) {
        this.finishDatePlan = finishDatePlan;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
