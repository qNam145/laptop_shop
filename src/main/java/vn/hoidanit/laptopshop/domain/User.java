package vn.hoidanit.laptopshop.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import vn.hoidanit.laptopshop.service.validator.StrongPassword;

@Entity
@Table(name = "users")
public class User {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private long id;

     @NotNull
     @Email(message = "Email is not valid", regexp = "^[A-Za-z0-9+_.-]+@(.+)$")
     private String email;

     @NotNull
     @Size(min = 3, message = "Password must be at least 3 characters")
     @StrongPassword
     private String password;

     @NotNull
     @Size(min = 2, message = "Name must be at least 2 characters")
     private String fullName;
     private String address;
     private String phone;
     private String avatar;

     @ManyToOne
     @JoinColumn(name = "role_id")
     private Role role;

     // region getters and setters
     public long getId() {
          return id;
     }

     public void setId(long id) {
          this.id = id;
     }

     public String getEmail() {
          return email;
     }

     public void setEmail(String email) {
          this.email = email;
     }

     public String getPassword() {
          return password;
     }

     public void setPassword(String password) {
          this.password = password;
     }

     public String getFullName() {
          return fullName;
     }

     public void setFullName(String fullName) {
          this.fullName = fullName;
     }

     public String getAddress() {
          return address;
     }

     public void setAddress(String address) {
          this.address = address;
     }

     public String getPhone() {
          return phone;
     }

     public void setPhone(String phone) {
          this.phone = phone;
     }

     public String getAvatar() {
          return avatar;
     }

     public void setAvatar(String avatar) {
          this.avatar = avatar;
     }

     public Role getRole() {
          return role;
     }

     public void setRole(Role role) {
          this.role = role;
     }
     // endregion

     @Override
     public String toString() {
          return "User [id=" + id + ", email=" + email + ", password=" + password + ", fullName=" + fullName
                    + ", address=" + address + ", phone=" + phone + "]";
     }
}
