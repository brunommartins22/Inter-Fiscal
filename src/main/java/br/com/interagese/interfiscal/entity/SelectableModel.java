package br.com.interagese.interfiscal.entity;



public interface SelectableModel extends Model{

    public boolean isSelected();

    public void setSelected(boolean isSelected);
}
