package classes.algorithms;

public class SearchParameter {

    String searchValue = null;
    char fromRailwayEntityName = 0;
    char toRailwayEntityName = 0;
    int effortValue = 0;

    public String getSearchValue() {
        return searchValue;
    }
    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }
    public char getFromRailwayEntityName() {
        return fromRailwayEntityName;
    }
    public void setFromRailwayEntityName(char fromRailwayEntityName) {
        this.fromRailwayEntityName = fromRailwayEntityName;
    }
    public char getToRailwayEntityName() {
        return toRailwayEntityName;
    }
    public void setToRailwayEntityName(char toRailwayEntityName) {
        this.toRailwayEntityName = toRailwayEntityName;
    }    
    public int getEffortValue() {
        return effortValue;
    }
    public void setEffortValue(int filterMaxSteps) {
        this.effortValue = filterMaxSteps;
    }   
}
