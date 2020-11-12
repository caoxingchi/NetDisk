package com.netdisk.test;

import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class JlistTest extends JFrame {

  public JlistTest() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JList<String> list = new JList<>(new String[] { "1", "2", "3" });
    list.setCellRenderer(getRenderer());
    add(list);
    pack();
    setVisible(true);
  }

  private ListCellRenderer<? super String> getRenderer() {
    return new DefaultListCellRenderer() {
      @Override
      public Component getListCellRendererComponent(JList<?> list,
          Object value, int index, boolean isSelected, boolean cellHasFocus) {
        JLabel listCellRendererComponent = (JLabel) super
            .getListCellRendererComponent(list, value, index, isSelected,
                cellHasFocus);
        listCellRendererComponent.setBorder(BorderFactory.createMatteBorder(0,
            0, 1, 0, Color.BLACK));
        return listCellRendererComponent;
      }
    };
  }

  public static void main(String... strings) {
    new JlistTest();
  }
}
