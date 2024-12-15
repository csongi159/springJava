package edu.bbte.idde.vcim2315.presentation;

import edu.bbte.idde.vcim2315.exception.ComponentInsertFailedException;
import edu.bbte.idde.vcim2315.exception.ComponentNotFoundException;
import edu.bbte.idde.vcim2315.exception.ComponentQueryFailedException;
import edu.bbte.idde.vcim2315.service.ComponentService;
import edu.bbte.idde.vcim2315.model.Component;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Map;


public class ComponentUI {
    private final transient ComponentService componentService;
    private final transient DefaultTableModel tableModel;
    private final transient JTextField textId;
    private final transient JTextField textName;
    private final transient JTextField textType;
    private final transient JTextField textPrice;
    private final transient JTextField textQuantity;

    public ComponentUI(ComponentService service) {
        this.componentService = service;
        JFrame frame = new JFrame("Component Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        frame.add(panel);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(6, 2));

        JLabel labelId = new JLabel("Id:");
        formPanel.add(labelId);
        textId = new JTextField(20);
        formPanel.add(textId);

        JLabel labelName = new JLabel("Name:");
        formPanel.add(labelName);
        textName = new JTextField(20);
        formPanel.add(textName);

        JLabel labelType = new JLabel("Type:");
        formPanel.add(labelType);
        textType = new JTextField(20);
        formPanel.add(textType);

        JLabel labelPrice = new JLabel("Price:");
        formPanel.add(labelPrice);
        textPrice = new JTextField(20);
        formPanel.add(textPrice);

        JLabel labelQuantity = new JLabel("Quantity:");
        formPanel.add(labelQuantity);
        textQuantity = new JTextField(20);
        formPanel.add(textQuantity);

        JButton addButton = new JButton("Add Component");
        formPanel.add(addButton);

        addButton.addActionListener(e -> addComponent(frame));

        String[] columnNames = {"ID", "Name", "Type", "Price", "Quantity"};
        tableModel = new DefaultTableModel(columnNames, 0);
        JTable componentTable = new JTable(tableModel);

        componentTable.setEnabled(false);
        updateTable(frame);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton deleteButton = new JButton("Delete");
        JButton updateButton = new JButton("Update");

        buttonPanel.add(deleteButton);
        buttonPanel.add(updateButton);

        deleteButton.addActionListener(e -> deleteComponent(frame));
        JScrollPane tableScrollPane = new JScrollPane(componentTable);
        updateButton.addActionListener(e -> updateComponent(frame));

        panel.add(formPanel, BorderLayout.NORTH);
        panel.add(tableScrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private void addComponent(JFrame frame) {
        try {
            String name = textName.getText();
            String type = textType.getText();
            double price = Double.parseDouble(textPrice.getText());
            long id = Long.parseLong(textId.getText());
            int quantity = Integer.parseInt(textQuantity.getText());
            Component component = new Component(id, name, type, price, quantity);
            componentService.addComponent(component);

            JOptionPane.showMessageDialog(null, "Component added!");
            updateTable(frame);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Invalid input!", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (ComponentInsertFailedException ex) {
            JOptionPane.showMessageDialog(null, "Component already exists", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteComponent(JFrame frame) {
        String idString = JOptionPane.showInputDialog(frame, "Enter ID to delete:");
        try {
            long id = Long.parseLong(idString);
            componentService.deleteComponent(id);
            JOptionPane.showMessageDialog(frame, "Component deleted successfully!");
            updateTable(frame);
        } catch (ComponentNotFoundException | ComponentQueryFailedException ex) {
            JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Invalid ID format!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateComponent(JFrame frame) {
        String idString = JOptionPane.showInputDialog(frame, "Enter ID to update:");
        try {
            long id = Long.parseLong(idString);
            Component component = componentService.getComponent(id);

            String newName = JOptionPane.showInputDialog(frame, "Enter new name:", component.getName());
            String newType = JOptionPane.showInputDialog(frame, "Enter new type:", component.getType());
            String newPriceString = JOptionPane.showInputDialog(frame, "Enter new price:", component.getPrice());
            String newQuantityString = JOptionPane.showInputDialog(frame, "New quantity:", component.getQuantity());

            double newPrice = Double.parseDouble(newPriceString);
            int newQuantity = Integer.parseInt(newQuantityString);

            component.setName(newName);
            component.setType(newType);
            component.setPrice(newPrice);
            component.setQuantity(newQuantity);

            componentService.updateComponent(component);
            JOptionPane.showMessageDialog(frame, "Component updated successfully!");
            updateTable(frame);
        } catch (ComponentNotFoundException | ComponentQueryFailedException ex) {
            JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Invalid price or ID!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateTable(JFrame frame) {
        tableModel.setRowCount(0);
        Map<Long, Component> components;
        try {
            components = componentService.getAllComponents();
            for (Component component : components.values()) {
                Object[] row = {
                        component.getId(),
                        component.getName(),
                        component.getType(),
                        component.getPrice(),
                        component.getQuantity()
                };
                tableModel.addRow(row);
            }
        } catch (ComponentQueryFailedException e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }
}
