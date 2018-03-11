package touristattractionsdatabase;

/**
 * Tourist attraction database record.
 *
 * @author konrad
 */
public class TouristAttraction {

    private String name;
    private String type;
    private int durationTime;
    private int minAge;
    private double cost;
    private String sex;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the durationTime
     */
    public int getDurationTime() {
        return durationTime;
    }

    /**
     * @param durationTime the durationTime to set
     */
    public void setDurationTime(int durationTime) {
        this.durationTime = durationTime;
    }

    /**
     * @return the minAge
     */
    public int getMinAge() {
        return minAge;
    }

    /**
     * @param minAge the minAge to set
     */
    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    /**
     * @return the cost
     */
    public double getCost() {
        return cost;
    }

    /**
     * @param cost the cost to set
     */
    public void setCost(double cost) {
        this.cost = cost;
    }

    /**
     * @return the sex
     */
    public String getSex() {
        return sex;
    }

    /**
     * @param sex the sex to set
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    public TouristAttraction() {
        name = "";
        type = "";
        durationTime = 0;
        minAge = 0;
        cost = 0;
        sex = "";
    }

    public TouristAttraction(String nam, String ty, int dur, int min, double co, String se) {
        name = nam;
        type = ty;
        durationTime = dur;
        minAge = min;
        cost = co;
        sex = se;
    }

    public TouristAttraction(String[] values) {
        name = values[0];
        type = values[1];
        durationTime = Integer.parseInt(values[2]);
        minAge = Integer.parseInt(values[3]);
        cost = Double.parseDouble(values[4]);
        sex = values[5];
    }

    public void print() {
        System.out.println(toString());
    }

    public String toString() {
        return "name: " + getName() + ", type: " + getType() + ", durationTime: " + getDurationTime() + ", minAge: " + getMinAge() + ", cost: " + getCost() + ", sex: " + getSex();
    }

}
