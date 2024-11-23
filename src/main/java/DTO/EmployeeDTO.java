package DTO;


public class EmployeeDTO {
    private String userId;
    private String name;
    private String state;
    private String imgUrl;

    public String getUserId() {
        return this.userId;
    }

    public String getName() {
        return this.name;
    }

    public String getState() {
        return this.state;
    }

    public String getImgUrl() {
        return this.imgUrl;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setState(String state) {
        this.state = state;
    }
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}


