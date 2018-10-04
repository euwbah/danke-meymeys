package ai.preferred.crawler.example.entity;

public class Car {
    private int id;
    private String url;
    private String name;
    private String price;
    private String depreciation;
    private String regDate;
    private String manufacturedYear;
    private String mileage;
    private String transmission;
    private String engineCap;
    private String roadTax;
    private String power;
    private String curbWeight;
    private String features;
    private String accessories;
    private String description;
    private String coe;
    private String omv;
    private String arf;
    private String deregValue;
    private String noOfOwners;
    private String typeOfVehicle;
    private String category;
    private String availability;

    private String others;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public String getManufacturedYear() {
        return manufacturedYear;
    }

    public void setManufacturedYear(String manufacturedYear) {
        this.manufacturedYear = manufacturedYear;
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getEngineCap() {
        return engineCap;
    }

    public void setEngineCap(String engineCap) {
        this.engineCap = engineCap;
    }

    public String getRoadTax() {
        return roadTax;
    }

    public void setRoadTax(String roadTax) {
        this.roadTax = roadTax;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getCurbWeight() {
        return curbWeight;
    }

    public void setCurbWeight(String curbWeight) {
        this.curbWeight = curbWeight;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public String getAccessories() {
        return accessories;
    }

    public void setAccessories(String accessories) {
        this.accessories = accessories;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCoe() {
        return coe;
    }

    public void setCoe(String coe) {
        this.coe = coe;
    }

    public String getOmv() {
        return omv;
    }

    public void setOmv(String omv) {
        this.omv = omv;
    }

    public String getArf() {
        return arf;
    }

    public void setArf(String arf) {
        this.arf = arf;
    }

    public String getDeregValue() {
        return deregValue;
    }

    public void setDeregValue(String deregValue) {
        this.deregValue = deregValue;
    }

    public String getNoOfOwners() {
        return noOfOwners;
    }

    public void setNoOfOwners(String noOfOwners) {
        this.noOfOwners = noOfOwners;
    }

    public String getTypeOfVehicle() {
        return typeOfVehicle;
    }

    public void setTypeOfVehicle(String typeOfVehicle) {
        this.typeOfVehicle = typeOfVehicle;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getOthers() {
        return others;
    }

    public void appendOthers(String others) {
        if (this.others == null)
            this.others = "";

        this.others += others + "\n";
    }

    /**
     *
     * @param labelName The exact text value of the car detail attribute name in the table of the website
     * @param value
     */
    public void setValue(String labelName, String value) {
        switch(labelName.trim()) {
            case "Price":
                this.price = value;
                break;
            case "Depreciation":
                this.depreciation = value;
                break;
            case "Reg Date":
                this.regDate = value;
                break;
            case "Manufactured":
                this.manufacturedYear = value;
                break;
            case "Mileage":
                this.mileage = value;
                break;
            case "Transmission":
                this.transmission = value;
                break;
            case "Engine Cap":
                this.engineCap = value;
                break;
            case "Road Tax":
                this.roadTax = value;
                break;
            case "Power":
                this.power = value;
                break;
            case "Curb Weight":
                this.curbWeight = value;
                break;
            case "Features":
                this.features = value;
                break;
            case "Accessories":
                this.accessories = value;
                break;
            case "Description":
                this.description = value;
                break;
            case "COE":
                this.coe = value;
                break;
            case "OMV":
                this.omv = value;
                break;
            case "ARF":
                this.arf = value;
                break;
            case "Dereg Value":
                this.deregValue = value;
                break;
            case "No. of Owners":
                this.noOfOwners = value;
                break;
            case "Type of Veh":
                this.typeOfVehicle = value;
                break;
            case "Category":
                this.category = value;
                break;
            case "Availability":
                this.availability = value;
                break;
            default:
                this.appendOthers(value);
                break;
        }
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDepreciation() {
        return depreciation;
    }

    public void setDepreciation(String depreciation) {
        this.depreciation = depreciation;
    }

    @Override
    public int hashCode() {
        return this.getId();
    }
}
