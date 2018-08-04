package com.github.stokito.IdeaJol;

import org.openjdk.jol.info.FieldLayout;

import javax.swing.table.AbstractTableModel;
import java.util.List;

class FieldLayoutTableModel extends AbstractTableModel {
    private static final String[] COLUMNS = {"Offset", "Size", "Type", "Class", "Field"};
    private final List<FieldLayout> layouts;

    public FieldLayoutTableModel(List<FieldLayout> layouts) {
        this.layouts = layouts;
    }

    public String getColumnName(int column) {
        return COLUMNS[column];
    }

    public int getColumnCount() {
        return COLUMNS.length;
    }

    public int getRowCount() {
        return layouts.size();
    }

    public Object getValueAt(int row, int col) {
        FieldLayout fieldLayout = layouts.get(row);
        switch (col) {
            case 0:
                return fieldLayout.offset();
            case 1:
                return fieldLayout.size();
            case 2:
                return fieldLayout instanceof FieldLayoutPadding ? null : fieldLayout.typeClass();
            case 3:
                return fieldLayout instanceof FieldLayoutPadding ? null : fieldLayout.classShortName();
            case 4:
                return fieldLayout instanceof FieldLayoutPadding ? ((FieldLayoutPadding)fieldLayout).getDescription() : fieldLayout.name();
            default:
                return null;
        }
    }
}
