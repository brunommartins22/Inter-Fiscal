/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.interagese.interfiscal.table;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bruno
 */
public abstract class AbstractTableModel<T extends Object> extends javax.swing.table.AbstractTableModel {

    protected List<T> list;

    public AbstractTableModel(List<T> list) {
        if (list == null) {
            this.list = new ArrayList<>();
        } else {
            this.list = list;
        }

    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    public T getItem(int index) {
        return list.get(index);
    }

    public T removeItem(int index) {
        T model = list.remove(index);
        fireTableRowsDeleted(index, index);
        return model;
    }

    public boolean removeObjectItem(T item) {
        boolean b = list.remove(item);
        fireTableDataChanged();
        return b;
    }

    public boolean addItem(T item) {
        boolean b = list.add(item);
        fireTableDataChanged();
        return b;
    }

    public void setItems(List<T> items) {
        list = new ArrayList<>();
        if (items != null) {
            for (T item : items) {
                list.add(item);
            }
        }
        fireTableDataChanged();
    }

    public List<T> getItens() {
        return list;
    }

    public void addItem(int index, T item) {
        list.add(index, item);
    }

    public void addItens(List<T> result) {
        if (result != null) {
            list.addAll(result);
        } else {
            list = new ArrayList<>();
        }
        updateTableListener();
    }

    public T setItem(int index, T item) {
        T t = list.set(index, item);
        fireTableRowsUpdated(index, index);
        return t;
    }

    public Class<?> getColumnClass(int index) {

        return Object.class;

    }

    @Override
    public abstract String getColumnName(int column);

    public List<T> getResultList() {
        return list;
    }

    public void updateTableListener() {
        fireTableDataChanged();
    }

}
