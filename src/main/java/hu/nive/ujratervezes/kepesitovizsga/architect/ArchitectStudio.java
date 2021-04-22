package hu.nive.ujratervezes.kepesitovizsga.architect;

import java.util.*;

public class ArchitectStudio {

    private Map<String, Plan> plans = new HashMap<>();

    public void addPlan(String workingTitle, Plan plan){
        if (isEmpty(workingTitle) || plan == null){
            throw new IllegalArgumentException("Working title and plan must not be empty!");
        }
        plans.put(workingTitle, plan);
    }

    public Plan getPlanWithMaxSquareMeter(){
        Collection<Plan> planlist = plans.values();
        Iterator<Plan> iterator = planlist.iterator();
        int maxArea = 0;
        Plan planWithMaxArea = null;
        while (iterator.hasNext()){
            Plan p = iterator.next();
            if (p.calculateSquareMeter() > maxArea){
                maxArea = p.calculateSquareMeter();
                planWithMaxArea = p;
            }
        }
        return planWithMaxArea;
    }

    public Plan getPlanByWorkingTitle(String workingTitle){
        if (isEmpty(workingTitle)){
            throw new IllegalArgumentException("Working title must not be empty!");
        }
        Set<String> titles = plans.keySet();
        for (String s : titles) {
            if (workingTitle.equals(s)){
                return plans.get(s);
            }
        }
        throw new IllegalArgumentException("No such project.");
    }

    private boolean isEmpty(String s){
        return s == null || "".equals(s);
    }

}
