package net.gormelof.hackathontask;

/**
 * Created by Android on 6/3/2015.
 */
public class Person {
    private Integer _id;
    private String _givenName;
    private String _middleName;
    private String _surName;

    public Person(Integer _id, String _givenName, String _middleName, String _surName) {
        this._id = _id;
        this._givenName = _givenName;
        this._middleName = _middleName;
        this._surName = _surName;
    }

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public String get_givenName() {
        return _givenName;
    }

    public void set_givenName(String _givenName) {
        this._givenName = _givenName;
    }

    public String get_middleName() {
        return _middleName;
    }

    public void set_middleName(String _middleName) {
        this._middleName = _middleName;
    }

    public String get_surName() {
        return _surName;
    }

    public void set_surName(String _surName) {
        this._surName = _surName;
    }
}
