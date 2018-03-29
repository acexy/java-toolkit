package fastjson;

public class Entity {
    private String key;
    private String value;

    public String getKey() {
        return key;
    }

    public Entity setKey(String key) {
        this.key = key;
        return this;
    }

    public String getValue() {
        return value;
    }

    public Entity setValue(String value) {
        this.value = value;
        return this;
    }
}
