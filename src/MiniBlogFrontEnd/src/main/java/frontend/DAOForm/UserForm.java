package frontend.DAOForm;

import java.util.Date;

public class UserForm {
		private int id;
		private String Username;
		private String Password;
		private String Email;
		private String Lastname;
		private String Firstname;
		private String Gender;
		private String Birthday;
		private Date Create_date;
		private Date Modify_date;
		
		public String getUsername() {
			return Username;
		}
		public void setUsername(String username) {
			Username = username;
		}
		public String getPassword() {
			return Password;
		}
		public void setPassword(String password) {
			Password = password;
		}
		public String getEmail() {
			return Email;
		}
		public void setEmail(String email) {
			Email = email;
		}
		public String getLastname() {
			return Lastname;
		}
		public void setLastname(String lastname) {
			Lastname = lastname;
		}
		public String getFirstname() {
			return Firstname;
		}
		public void setFirstname(String firstname) {
			Firstname = firstname;
		}
		public String getGender() {
			return Gender;
		}
		public void setGender(String gender) {
			Gender = gender;
		}
		public String getBirthday() {
			return Birthday;
		}
		public void setBirthday(String birthday) {
			Birthday = birthday;
		}
		public Date getCreate_date() {
			return Create_date;
		}
		public void setCreate_date(Date create_date) {
			Create_date = create_date;
		}
		public Date getModify_date() {
			return Modify_date;
		}
		public void setModify_date(Date modify_date) {
			Modify_date = modify_date;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			 this.id = id;
		}
}
