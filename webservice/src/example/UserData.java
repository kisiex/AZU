package example;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class UserData implements Serializable {

    private String name;
    private String lastname;
    private String dateOfBirth;
    private Avatar avatar;
    private UserData additionalData;

    private Map<String, String> someMap = new HashMap<>();

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

    public UserData getAdditionalData() {
        return additionalData;
    }

    public void setAdditionalData(UserData additionalData) {
        this.additionalData = additionalData;
    }

    public UserData() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Map<String, String> getSomeMap() {
        return someMap;
    }

    public void setSomeMap(Map<String, String> someMap) {
        this.someMap = someMap;
    }

    public void appendToMap(String key, String value){
        this.someMap.put(key, value);
    }
}
