/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package memorymanagementsimulator.frontend;

import java.awt.Color;
import java.awt.Component;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author josemanuque
 */
public class TableCellRenderer  extends DefaultTableCellRenderer {
    private final Map<Integer, Color> rowColors;

    public TableCellRenderer() {
        this.rowColors = new HashMap<>();
    }
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        // Cambiar el color de fondo de la fila
        if (isSelected) {
            c.setBackground(table.getSelectionBackground());
        } else {
            Color rowColor = rowColors.get(row);
            if (rowColor != null) {
                c.setBackground(rowColor);
            } else {
                // Si no se ha establecido un color específico, utilizar el color predeterminado
                c.setBackground(table.getBackground());
            }
        }

        return c;
    }
    
    public void addRowColor(int rowIndex, Color c){
        rowColors.put(rowIndex, c);
    }
    
    public void removeRowColor(int rowIndex){
        rowColors.remove(rowIndex);
        // Ajustar índices después de eliminar una fila
        Map<Integer, Color> updatedRowColors = new HashMap<>();
        rowColors.forEach((index, color) -> {
            if (index > rowIndex) {
                updatedRowColors.put(index - 1, color);
            } else {
                updatedRowColors.put(index, color);
            }
        });
        rowColors.clear();
        rowColors.putAll(updatedRowColors);
    }
}
