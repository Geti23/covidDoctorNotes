/**
 * In this project, I used the MVC Design Pattern (Model-View-Controller Architecture).
 * The View contains all the JLabels, because the View represents the data of the Model.
 * The Model contains the data to display and also the data to retrieve from the Controller,
 * the model is not directly visible to the UI (in our case the model is the firebase, because
 * it contains all the data needed for the program to function).
 * The Controller contains all the JButtons and JTextFields. Using the Controller components
 * we can change the Model (the data in the firebase), in which case the Model alerts the View
 * to refresh itself, so we can see the new information!
 *
 * This was a short introduction of how this application works in the background. If you
 * want to see it in action, please Execute it, so you can see for yourself what we explained
 * above.
 *
 * Thank you very much for everything,
 * with much respect, Getoari.
 */
public class Main {
    public static void main(String[] args) {
        new Cartboard();
    }
}
