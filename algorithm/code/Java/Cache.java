public interface Cache {
    public String toString();

    public void put(String key, String value);

    public String get(String key);
}
