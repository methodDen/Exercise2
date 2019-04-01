import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Objects;

@DatabaseTable (tableName = "Persons")
public class Person {
    @DatabaseField (columnName = "id", canBeNull = false, generatedId = true)
    private long id;
    @DatabaseField (columnName = "firstName")
    private String firstName;
    @DatabaseField (columnName = "lastName")
    private String lastName;
    @DatabaseField (columnName = "emailAddress")
    private String emailAddress;
    @DatabaseField (columnName = "age")
    private int age;
    @DatabaseField (columnName = "passwordOfUser" )
    private String password;
    @DatabaseField(columnName = "roleOfUser")
    private Role role;

    public Person(String firstName, String lastName, String emailAddress, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.age = age;
    }

    public Person(String firstName, String lastName, String emailAddress, int age, String password, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.age = age;
        this.password = password;
        this.role = role;
    }


    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Person() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id &&
                age == person.age &&
                Objects.equals(firstName, person.firstName) &&
                Objects.equals(lastName, person.lastName) &&
                Objects.equals(emailAddress, person.emailAddress) &&
                Objects.equals(password, person.password) &&
                role == person.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, emailAddress, age, password, role);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", age=" + age +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }

}
