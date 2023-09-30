package com.example.eazyschool.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
@Table(name = "contact_msg")
@SqlResultSetMappings({
        @SqlResultSetMapping(name = "SqlResultSetMapping.count", columns = @ColumnResult(name = "cnt"))
})
@NamedQueries( {
        @NamedQuery( name = "Contact.findOpenMsgs",
        query="SELECT c FROM Contact c WHERE c.status = :status"),
        @NamedQuery( name="Contact.updateMsgStatus",
        query="UPDATE Contact c SET c.status = ?1 WHERE c.contactId = ?2")
} )
@NamedNativeQueries({
        @NamedNativeQuery( name="Contact.findOpenMsgsNative",
            query="SELECT * FROM contact_msg c WHERE c.status = :status",
            resultClass = Contact.class),
        @NamedNativeQuery( name="Contact.findOpenMsgsNative.count",
                query = "SELECT count(*) as cnt FROM contact_msg c WHERE c.status = :status",
                resultSetMapping = "SqlResultSetMapping.count"),
        @NamedNativeQuery( name="Contact.updateMsgStatusNative",
                query="UPDATE contact_msg c SET c.status = ?1 WHERE c.contact_id = ?2")}
)
public class Contact extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator( name = "native")
    @Column(name = "contact_id")
    private int contactId;

    @NotBlank(message = "Name must not be blank")
    @Size(min = 3, message = "Name must have atleast 3 characters")
    private String name;

    @NotBlank(message = "Mobile number must not be blank")
    @Pattern( regexp = "($|[0-9]{10})", message = "Mobile number must be 10 digits")
    private String mobileNum;

    @NotBlank(message = "Email must not be blank")
    @Email(message = "Please provide a valid email address")
    private String email;

    @NotBlank(message = "Subject number must not be blank")
    @Size(min = 5, message = "Subject must have atleast 5 characters")
    private String subject;

    @NotBlank(message = "Message number must not be blank")
    @Size(min = 10, max = 250, message = "Message must have atleast 10 characters")
    private String message;

    private String status;
}
