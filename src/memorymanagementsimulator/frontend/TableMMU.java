/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package memorymanagementsimulator.frontend;

import java.awt.Color;
import javax.swing.JTable;
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
        this.model.setColumnIdentifiers(columnNames);
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
    
    public void deleteRow(int rowIndex){
        tableCellRenderer.removeRowColor(rowIndex);
        model.removeRow(rowIndex);
    }
}
