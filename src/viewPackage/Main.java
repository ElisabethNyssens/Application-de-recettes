package viewPackage;

public class Main {

    //commandes docker si modif de la BD

    //docker container rm projetrecettes
    //docker image rm projetrecettes
    //docker build -t projetrecettes .
    //docker run --name projetrecettes -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root -d projetrecettes

    public static void main(String[] args){
        WelcomeWindow welcomeWindow = new WelcomeWindow();
    }
}
