package Componentes;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class DoubleFilter extends DocumentFilter {
    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        if (isValidDouble(fb.getDocument().getText(0, fb.getDocument().getLength()), string, offset)) {
            super.insertString(fb, offset, string, attr);
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        if (isValidDouble(fb.getDocument().getText(0, fb.getDocument().getLength()), text, offset)) {
            super.replace(fb, offset, length, text, attrs);
        }
    }

    @Override
    public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
        super.remove(fb, offset, length);
    }

    private boolean isValidDouble(String currentText, String newText, int offset) {
        StringBuilder sb = new StringBuilder(currentText);
        sb.insert(offset, newText);
        String fullText = sb.toString();

        if (fullText.isEmpty()) {
            return true;
        }
        if (fullText.equals(".") || fullText.equals("-") || fullText.equals("-.")) {
            return true;
        }
        try {
            Double.parseDouble(fullText);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}