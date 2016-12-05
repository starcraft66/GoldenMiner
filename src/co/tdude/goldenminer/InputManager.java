package co.tdude.goldenminer;

import javafx.scene.input.KeyCode;

import java.util.HashSet;

/**
 * Created by tristan on 2016-12-05.
 */
public class InputManager {
    private static InputManager INSTANCE = new InputManager();
    protected static HashSet currentlyActiveKeys = new HashSet<String>();

    public void addKey(KeyCode key) {
        String code = key.toString();
        System.out.println(code);
        // only add once... prevent duplicates
        if (!currentlyActiveKeys.contains(code))
            currentlyActiveKeys.add(code);
    }

    public void removeKey(KeyCode key) {
        String code = key.toString();
        currentlyActiveKeys.remove(code);
    }

    public static boolean isKeyPressed(String key) {
        return (currentlyActiveKeys.contains(key));
    }

    public static InputManager getInstance() {
        return INSTANCE;
    }
}
