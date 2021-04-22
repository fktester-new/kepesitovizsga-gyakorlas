package hu.nive.ujratervezes.kepesitovizsga.architect;

public interface Plan {

    String getProjectName();
    int calculateSquareMeter();
    PlanType getType();
}
