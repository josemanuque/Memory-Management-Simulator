/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package memorymanagementsimulator.frontend;

import java.awt.Color;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author josemanuque
 */
public class TableMMU extends JTable{
    DefaultTableModel model;
    TableCellRenderer tableCellRenderer;
    
    public TableMMU(){
        this.model = new DefaultTableModel();
        this.tableCellRenderer = new TableCellRenderer();
        this.setDefaultRenderer(Object.class, tableCellRenderer);
        setModel(this.model);
        String [] columnNames = {"Page ID", "PID", "Loaded", "L-Addr", "M-Addr", "D-Addr", "Loaded-T", "Mark"};
        model.setColumnIdentifiers(columnNames);
    }
    
    public void addRow(Object[] rowData, Color c){
        model.addRow(rowData);
        
        int rowIndex = model.getRowCount() - 1;
        setRowColor(rowIndex, c);
    }
    
    private void setRowColor(int rowIndex, Color color) {
        // Asignar el color de fondo de la fila
        tableCellRenderer.addRowColor(rowIndex, color);

        // Actualizar la tabla para reflejar los cambios
        this.repaint();
    }

    public void updateRow(int rowIndex, Object[] newData) {
        // Asegurarse de que el índice sea válido
        if (rowIndex < 0 || rowIndex >= model.getRowCount()) {
            throw new IndexOutOfBoundsException("Índice de fila inválido: " + rowIndex);
        }

        // Actualizar los datos en el modelo de la tabla
        for (int i = 0; i < newData.length; i++) {
            model.setValueAt(newData[i], rowIndex, i);
        }

        // Actualizar la tabla para reflejar los cambios
        //this.repaint();
    }

    public void updateRow(int rowIndex, int columnIndex, Object newData) {
        // Asegurarse de que el índice sea válido
        if (rowIndex < 0 || rowIndex >= model.getRowCount()) {
            throw new IndexOutOfBoundsException("Índice de fila inválido: " + rowIndex);
        }

        model.setValueAt(newData, rowIndex, columnIndex);

        // Actualizar la tabla para reflejar los cambios
        //this.repaint();
    }
    public void deleteRow(int rowIndex){
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                if (rowIndex < 0 || rowIndex >= model.getRowCount()) {
                    throw new IndexOutOfBoundsException("Índice de fila inválido: " + rowIndex);
                }

                // Remover el color asociado a la fila
                tableCellRenderer.removeRowColor(rowIndex);

                // Eliminar la fila del modelo
                model.removeRow(rowIndex);

                // Actualizar la tabla para reflejar los cambios
                TableMMU.this.repaint();
            }
        });
    }
}
