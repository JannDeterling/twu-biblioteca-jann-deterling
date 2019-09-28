package com.twu.biblioteca.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class MainMenu {

  private final List<MenuOption> menuOptions;
  private final Scanner scanner;

  public MainMenu() {
    this.menuOptions = new ArrayList<>();
    this.scanner = new Scanner(System.in);
  }

  public void registerMenuOption(String menuOptionDescription, MenuAction menuAction){
    final MenuOption menuOption = new MenuOption(menuOptions.size()+1,menuOptionDescription, menuAction);
    this.menuOptions.add(menuOption);
  }

  public void displayMenu() {
    this.printMenu();
    final Integer userChoice = this.collectUserAction();
    this.runMenuOption(userChoice);
  }

  private void printMenu() {
      this.menuOptions.forEach(menuOption -> System.out.println(menuOption.getIndex()+") "+menuOption.getDescription()));
  }

  private Integer collectUserAction() {
    System.out.println("Please select a option (enter the number):");
    return this.scanner.nextInt();
  }

  private void runMenuOption(Integer menuOptionIndex){
    final Optional<MenuOption> optionalMenuOption =
        this.menuOptions.stream().filter(menuOption -> menuOption.getIndex().equals(menuOptionIndex)).findAny();

    if (optionalMenuOption.isPresent()){
      optionalMenuOption.get().runAction();
    }	else {
      System.out.println("Please select a valid option!");
      this.displayMenu();
    }
  }
}
