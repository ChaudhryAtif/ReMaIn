import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

/**
 * Class to update color of the row based on the status
 */
public class TableRowRender extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        cellComponent.setBackground(Color.white);
        if (table.getColumnCount() > 5 && table.getValueAt(row, 5) != null) {
            String cellVal = table.getValueAt(row, 5).toString();
            if (cellVal.equalsIgnoreCase("NEW")) { cellComponent.setBackground(Color.white);
            } else if (cellVal.equalsIgnoreCase("STARTED") || cellVal.equalsIgnoreCase("PENDING")) {
                cellComponent.setBackground(Color.yellow);
            } else if (cellVal.equalsIgnoreCase("READY") || cellVal.equalsIgnoreCase("ORDERED")) {
                cellComponent.setBackground(Color.green);
            } else if (cellVal.equalsIgnoreCase("HELP") || cellVal.equalsIgnoreCase("CANCELED")) {
                cellComponent.setBackground(Color.red);
            }
        }
        if (isSelected) { cellComponent.setBackground(new Color(184, 207, 229)); }

        // Center Table, Set Row Height, Disable Column ReOrder, Update Table and Header Font
        setHorizontalAlignment(JLabel.CENTER);
        table.setRowHeight(30);
        table.getTableHeader().setReorderingAllowed(false);
        Utilities.updateFont(table, .02);
        Utilities.updateFont(table.getTableHeader(), .023);

        return cellComponent;
    }
}
