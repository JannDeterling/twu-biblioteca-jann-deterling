package com.twu.biblioteca.menu;

public class MenuOption {

  private String description;
  private MenuAction menuAction;
  private Integer index;

  public MenuOption(Integer index, String description, MenuAction menuAction){
    this.setIndex(index);
    this.setDescription(description);
    this.setMenuAction(menuAction);
  }

  private void setDescription(String description) {
    assert description != null : "Description can not be null";
    assert !description.equals("") : "Description can not be empty";
    this.description = description;
  }

  private void setMenuAction(MenuAction menuAction) {
    assert menuAction != null : "MenuAction can not be null";
    this.menuAction = menuAction;
  }

  private void setIndex(Integer index) {
    assert index != null : "Index can not be null";
    assert index >= 0 : "Index can not be negative";
    this.index = index;
  }

  public String getDescription() {
    return description;
  }

  public Integer getIndex() {
    return index;
  }

  public void runAction(){
    this.menuAction.runAction();
  }
}
