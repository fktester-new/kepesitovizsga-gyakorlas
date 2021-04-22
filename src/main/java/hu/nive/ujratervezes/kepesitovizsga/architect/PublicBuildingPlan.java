package hu.nive.ujratervezes.kepesitovizsga.architect;

public class PublicBuildingPlan implements Plan{

    private PlanType planType;
    private String projectName;
    private int stock;
    private int area;

    public PublicBuildingPlan(String projectName, int stock, int area) {
        planType = PlanType.PUBLIC;
        this.projectName = projectName;
        this.stock = stock;
        this.area = area;
    }

    @Override
    public int calculateSquareMeter() {
        return area * stock;
    }

    @Override
    public PlanType getType() {
        return planType;
    }

    @Override
    public String getProjectName() {
        return projectName;
    }

    public int getStock() {
        return stock;
    }

    public int getArea() {
        return area;
    }
}
