package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;


public class Controller implements Initializable {
    @FXML
    private Label questionBody;
    @FXML private Button answer1;
    @FXML private Button answer2;
    @FXML private Button answer3;
    @FXML private Label timerLabel;


    @FXML  Label btn1;
    @FXML  Label btn2;
    @FXML  Label btn3;
    @FXML  Label btn4;
    @FXML Label btn5;
    Timer timer;

    private Label[] btns = new Label[5];
    private int period = 60 * 5;

    private int indice = 0;
    private int chCounter = 0;
    private static int level = 1;
    private int sum  = 0;
    private String qs[] = {"JAVA est  un langage", "Toutes les classes héritent de la classe", "Par convention une classe", "Est-ce que on peut avoir plusieurs constructeurs pour la même classe", "Dans la ligne \"public class A implements B\", B est:"};
    private String choises[][] = {{"Compilé", "Interprété", "Compilé et interpreté"}, {"Main", "Object", "AWT"}, {"est en minuscule", "commence par une majuscule", "est en majuscules"}, {"oui", "non"}, {"Interfacce", "Classe parent", "Package"}};
    private String answers[] = {"Compilé et interpreté", "Object", "commence par une majuscule", "oui", "Interfacce"};
    private String userAns[] = new String[5];


    // for level 2
    private String qs2[] = {"Après la compilation, un programme écrit en JAVA, il se transforme en programme en bytecodeQuelle est l’extension du programme en bytecode?",
            "Class Test{Public Test () {\nSystem.out.println(”Bonjour”);\n}\npublic Test (int i) {\nthis(); System.out.println(”Nous sommes en ”+i+”!”);}; }\nqu’affichera l’instruction suivante? Test t1=new Test (2018);\n",
            "Voici un constructeur de la classeEmployee, y-a-t'il un problème ?Public void Employe(String n){Nom=n;}",
            "Pour spécifier que la variable ne pourra plus être modifiée et doit être initialisée dès sa déclaration,\n on la déclare comme une constante avec le mot réservé",
            "Dans une classe, on accède à ses variables grâce au mot clé"};
    private String choises2[][] = {{"a) aucundes choix", "b).JAVA", "c).Class"}, {"a) aucundes choix", "b) BonjourNous sommes en 2018!", "c)Nous sommes en 2018!"},
            {"a)vrai", "b)faux"}, {"a) aucundes choix", "b)final", "c)const"}, {"a) aucundes choix", "b)this", "c)super"}};
    private String answers2[] = {"c).Class", "c)Nous sommes en 2018!", "a)vrai", "b)fina", "b)this"};
    private String userAns2[] = new String[5];


    // for level 3
    private String qs3[] = {"question 1", "question 2", "question 3", "question 4", "Question 5"};
    private String choises3[][] = {{"1", "2", "3"}, {"1", "2"}, {"1", "2", "3"}, {"1", "2"}, {"1", "2", "3"}, {"1", "2", "3"}};
    private String answers3[] = {"1", "1", "1", "1", "1"};
    private String userAns3[] = new String[5];

    // create a red background fill
    BackgroundFill background_fill_red = new BackgroundFill(Color.RED,
            CornerRadii.EMPTY, Insets.EMPTY);
    // create a green background fill
    BackgroundFill background_fill_green = new BackgroundFill(Color.GREEN,
            CornerRadii.EMPTY, Insets.EMPTY);
    // create a white background fill
    BackgroundFill background_fill_white = new BackgroundFill(Color.WHITE,
            CornerRadii.EMPTY, Insets.EMPTY);
    // create Background
    Background red_background = new Background(background_fill_red);
    Background green_background = new Background(background_fill_green);
    Background white_background = new Background(background_fill_white);


    @FXML
    private void printHelloWorld(ActionEvent event) {
        event.consume();
        System.out.println("Hello, World!");
    }


    /** fill the question and the choices */
    private void fillQs() {

        questionBody.setText(qs[indice]);
        answer1.setText(choises[indice][0]);
        answer2.setText(choises[indice][1]);
        if (choises[indice].length <= 2) {
            answer3.setVisible(false);
        }
        else {
            answer3.setVisible(true);
            answer3.setText(choises[indice][2]);
        }
        indice++;
    }

    /** display this method when user lose */
    private void userlose() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("userLose.fxml"));
            Scene scene = new Scene(root, 950, 900);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            Main.window.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** check if user can pass to the next question */
    private void check(int target) {
        timer.cancel();
        if (sum >= target) {

            /** cancel the timer */
            try {
                Parent root = FXMLLoader.load(getClass().getResource("goToSecondQuiz.fxml"));
                Scene scene = new Scene(root, 950, 900);
                scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
                Main.window.setScene(scene);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //setToWhite();
        }
        else {
            userlose();
        }
    }


    /** generate next question */
    private void collectResponse(ActionEvent event) {
        if (event.getSource() == answer1) {
            userAns[chCounter] = answer1.getText();
            System.out.println(Arrays.toString(userAns));
        }
        else if (event.getSource() == answer2) {
            userAns[chCounter] = answer2.getText();
            System.out.println(Arrays.toString(userAns));
        }
        else {
            userAns[chCounter] = answer3.getText();
            System.out.println(Arrays.toString(userAns));
        }
    }

    /** change background of top indicator based on using answers
     * String ans1[], String ans2[], String ans3[]
     * */
    private void changeBackg() {
        /** if user choose the correct answer */
        if (userAns[chCounter].equals(answers[chCounter])) {
            btns[chCounter].setBackground(green_background);
            sum += 20;
        }
        /** user choose wrong answer */
        else {
            btns[chCounter].setBackground(red_background);
        }
    }

    @FXML
    private void nextQs(ActionEvent event) {
        if (indice < 5) {
            collectResponse(event);
            changeBackg();

            /** increment */
            chCounter++;

            /** display next question */
            fillQs();

        }
        /** when reach last question */
        else if (indice == 5) {
            collectResponse(event);
            changeBackg();
            answer1.setDisable(true);
            answer2.setDisable(true);
            answer3.setDisable(true);
            indice = 0;
            level++;
            /** if user pass go to next quiz otherwise end the quiz */
            if (level == 1) {
                check(40);
            }
            else if (level == 2) {
                check(60);
            }
            else {
                check(80);
            }
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (level == 1) {
            answers = answers;
            choises = choises;
            userAns = userAns;
            qs = qs;
        }
        else if (level == 2) {
            answers = answers2;
            choises = choises2;
            userAns = userAns2;
            qs = qs2;
        }
        else {
            answers = answers3;
            choises = choises3;
            userAns = userAns3;
            qs = qs3;
        }

        timerLabel.setText(Integer.toString(period));
        timer = new Timer();
        TimerTask task = new TimerTask()
        {
            public void run()
            {
                Platform.runLater(() -> {
                    int minute = period / 60;
                    int second = period % 60;
                    String res = Integer.toString(minute) + " : " + Integer.toString(second);
                    timerLabel.setText(res);
                    period--;
                    if (period == -1) {
                        try {
                            timer.cancel();
                            Parent root = FXMLLoader.load(getClass().getResource("userLose.fxml"));
                            Scene scene = new Scene(root, 950, 900);
                            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
                            Main.window.setScene(scene);
                        }
                        catch (Exception ex) {
                            System.out.println("error" + ex.getMessage());
                        }

                    }
                });
            }

        };

        timer.schedule(task,0, 1000l);

        btns[0] = btn1;
        btns[1] = btn2;
        btns[2] = btn3;
        btns[3] = btn4;
        btns[4] = btn5;
        /** initialise value of the Quiz.fxml file*/
        /** display next question */
        fillQs();
    }


}






















    /*
    @FXML
    private void nextQuestion() {
        if (indice == 5) {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("submit1.fxml"));
                Scene scene = new Scene(root, 950, 900);
                Main.window.setScene(scene);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            ToggleGroup tg = new ToggleGroup();
            //question.setText(qs[indice]);
            radioQs.getChildren().clear();




            tg.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
                public void changed(ObservableValue<? extends Toggle> ob,
                                    Toggle o, Toggle n) {

                    RadioButton rb = (RadioButton) tg.getSelectedToggle();

                    if (rb != null) {
                        String s = rb.getText();
                        // change the label
                        userAns[indice] = s;
                        System.out.println(userAns[indice]);
                    }
                }
            });
            for (int i = 0; i < choises[indice].length; i++) {
                RadioButton btn = new RadioButton(choises[indice][i]);
                btn.setToggleGroup(tg);
                radioQs.getChildren().add(btn);
            }
            indice++;
        }
    }
    **/



