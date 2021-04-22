package hu.nive.ujratervezes.kepesitovizsga.architect;

import java.util.Set;

public class IndustrialBuildingPlan implements Plan{

    private PlanType planType;
    private String projectName;
    private int areaOfOfficeBlock;
    private int stock;
    private int areaOfManufacturingHall;

    public IndustrialBuildingPlan(String projectName, int areaOfOfficeBlock, int stock, int areaOfManufacturingHall) {
        planType = PlanType.INDUSTRIAL;
        this.projectName = projectName;
        this.areaOfOfficeBlock = areaOfOfficeBlock;
        this.stock = stock;
        this.areaOfManufacturingHall = areaOfManufacturingHall;
    }

    @Override
    public int calculateSquareMeter() {
        return areaOfOfficeBlock * stock + areaOfManufacturingHall;
    }

    @Override
    public PlanType getType() {
        return planType;
    }

    @Override
    public String getProjectName() {
        return projectName;
    }

    public int getAreaOfOfficeBlock() {
        return areaOfOfficeBlock;
    }

    public int getStock() {
        return stock;
    }

    public int getAreaOfManufacturingHall() {
        return areaOfManufacturingHall;
    }
}
