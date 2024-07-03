package com.example.demo.student;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.Period;





//These annotations are used to map the class to a database table, They automate the management of the database table and the creation of unique identifiers for each entity, allowing developers to focus more on the application logic rather than the intricacies of database operations.
@Entity //for hibernate : This annotation specifies that the Student class is an entity. In JPA, an entity represents a table stored in a database. Every instance of an entity represents a row in the table. The @Entity annotation marks the class as part of the domain model, managed by the persistence context.
@Table //for table in the database : This annotation specifies the table in the database with which the entity is associated. Without parameters, it defaults to using a table that matches the name of the class (Student). If needed, the @Table annotation can include attributes such as name to specify a different table name, schema to define the schema of the table, or other constraints like unique constraints.




public class Student{


    //These annotations are used to map the class to a database table, They automate the management of the database table and the creation of unique identifiers for each entity, allowing developers to focus more on the application logic rather than the intricacies of database operations.
    // allowing instances of the class to be stored as rows within that table:


    @Id // This annotation is placed above a field to indicate that the field is the primary key of the entity. Every entity must have a primary key which uniquely identifies each row in the database table. In this class, id is the primary key.
    @SequenceGenerator( //his annotation is used to specify the details of a database sequence that will be used to automatically generate values for the primary key.

            name = "student_sequence", // A name given to the sequence generator, used in the @GeneratedValue annotation to reference this generator.
            sequenceName = "student_sequence", // The name of the database sequence. This should match the sequence name as defined in the database.
            allocationSize = 1 // The increment size for the sequence. This is the value by which the sequence in the database increments each time a new value is requested. Setting this to 1 means the sequence will increment by 1 for each new entity.
    )


    @GeneratedValue( // This annotation configures the way the primary key is generated. The parameters included are:
            strategy = GenerationType.SEQUENCE, // Defines the strategy used for generating the primary key values. GenerationType.SEQUENCE indicates that the primary key values are generated using a database sequence.
            generator = "student_sequence" // Refers to the name of the @SequenceGenerator defined earlier. This links the @GeneratedValue strategy to a specific sequence generator definition.
    )

    private Long id;
    private String name;
    private String email;
    private LocalDate dob;
    @Transient
    private Integer age;
    public Student() {
    }

    public Student(Long id,
                   String name,
                   String email,
                   LocalDate dob
                   ) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.dob = dob;
    }


    public Student(String name,
                   String email,
                   LocalDate dob
                   ) {
        this.name = name;
        this.email = email;
        this.dob = dob;
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public LocalDate getDob() {
        return dob;
    }
    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Integer getAge() {

        return Period.between(this.dob,LocalDate.now()).getYears();
    }

    public void setAge(Integer age) {
        this.age = age;
    }
    @Override


    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", dob=" + dob +
                ", age=" + age +
                '}';
    }
}