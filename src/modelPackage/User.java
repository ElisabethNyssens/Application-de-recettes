package modelPackage;

public class User {
        private static Author user;

        public static Author getInstance(String pseudo, String firstName, String lastName)  {
            if (user == null) {
               user = new Author(pseudo, firstName, lastName);
            }
            return user;
        }

}
