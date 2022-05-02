package view;

public class Main {
<<<<<<< Updated upstream
    public static void main(String[] args) {
        MainWindow mainWindoww = new MainWindow();
=======
    //commandes docker si Modif
    //docker container rm projetrecettes
    //docker image rm projetrecettes
    //docker build -t projetrecettes .
    //docker run --name projetrecettes -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root -d projetrecettes

    //sinon
    //docker start projetrecettes
    //docker stop projetrecettes

    public static void main(String[] args){
        MainWindow mainWindow = new MainWindow();
>>>>>>> Stashed changes
    }
}
