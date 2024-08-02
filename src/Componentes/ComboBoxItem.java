package Componentes;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ComboBoxItem extends JPanel{
    private String text;
    private ImageIcon icon;

    public ComboBoxItem(String text, ImageIcon icon) {
        this.text = text;
        this.icon = icon;
    }

    public String getText() {
        return text;
    }

    public ImageIcon getIcon() {
        return icon;
    }

    @Override
    public String toString() {
        return text; // Esto es necesario para que se muestre el texto en el JComboBox desplegado
    }
    
}
