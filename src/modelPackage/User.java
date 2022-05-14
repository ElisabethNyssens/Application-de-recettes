package modelPackage;

public class User {
        private static Author user;

        public User(String pseudo, String firstName, String lastName) {
            if (user == null) {
                user = new Author(pseudo, firstName, lastName);
            }
        }

        public static Author getInstance()  {
            return user;
        }

}
