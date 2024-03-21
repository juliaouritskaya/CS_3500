package view;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import controller.ImageProcessor;

/**
 * represents the Graphical User Interface for the ImageProcessing application.
 */
public class ImageProcessorGUI extends JFrame implements ActionListener {

  ImageProcessor imageProcessor;

  private final int CHARTHEIGHT = 100;
  JPanel filtersPanel;
  JPanel imagePanel;
  JPanel loadSavePanel;

  JPanel chartPanel;

  JComboBox saveFileType;
  JButton loadButton;
  JButton saveButton;
  JButton sepiaButton;
  JButton greyscaleButton;
  JButton horizontalFlipButton;
  JButton verticalFlipButton;
  JButton brightenButton;
  JButton blurButton;
  JButton sharpenButton;
  JButton redComponentButton;
  JButton greenComponentButton;
  JButton blueComponentButton;
  JButton valueComponentButton;
  JButton intensityComponentButton;
  JButton lumaComponentButton;
  JButton mosaicButton;
  JOptionPane mosaicComponentPane;
  JScrollPane scrollPane;
  JLabel imageLabel;

  /**
   * Initializes and creates a GUI controller.
   */
  public ImageProcessorGUI() {
    super();
    setTitle("gui");
    setSize(1000, 500);
    setLayout(new BorderLayout());

    imagePanel = new JPanel();
    imagePanel.setLayout(new BoxLayout(imagePanel, BoxLayout.Y_AXIS));
    add(imagePanel, BorderLayout.CENTER);

    loadSavePanel = new JPanel();
    loadSavePanel.setBackground(Color.pink);
    loadSavePanel.setLayout(new BoxLayout(loadSavePanel, BoxLayout.Y_AXIS));
    add(loadSavePanel, BorderLayout.WEST);

    loadButton = new JButton("load");
    loadButton.setActionCommand("load");
    loadButton.addActionListener(this);
    loadSavePanel.add(loadButton);

    saveButton = new JButton("save");
    saveButton.setActionCommand("save");
    saveButton.addActionListener(this);
    loadSavePanel.add(saveButton);

    saveFileType = new JComboBox();
    saveFileType.setMaximumSize(new Dimension(150, 20));
    saveFileType.addItem("jpg");
    saveFileType.addItem("jpeg");
    saveFileType.addItem("ppm");
    saveFileType.addItem("png");
    saveFileType.addItem("bmp");
    loadSavePanel.add(saveFileType);

    filtersPanel = new JPanel();
    filtersPanel.setBackground(Color.magenta);
    filtersPanel.setLayout(new BoxLayout(filtersPanel, BoxLayout.Y_AXIS));
    add(filtersPanel, BorderLayout.EAST);

    sepiaButton = new JButton("sepia");
    sepiaButton.setActionCommand("sepia");
    sepiaButton.addActionListener(this);
    filtersPanel.add(sepiaButton);

    greyscaleButton = new JButton("greyscale");
    greyscaleButton.setActionCommand("greyscale");
    greyscaleButton.addActionListener(this);
    filtersPanel.add(greyscaleButton);

    horizontalFlipButton = new JButton("horizontalFlip");
    horizontalFlipButton.setActionCommand("horizontalFlip");
    horizontalFlipButton.addActionListener(this);
    filtersPanel.add(horizontalFlipButton);

    verticalFlipButton = new JButton("verticalFlip");
    verticalFlipButton.setActionCommand("verticalFlip");
    verticalFlipButton.addActionListener(this);
    filtersPanel.add(verticalFlipButton);

    brightenButton = new JButton("brighten");
    brightenButton.setActionCommand("brighten");
    brightenButton.addActionListener(this);
    filtersPanel.add(brightenButton);

    blurButton = new JButton("blur");
    blurButton.setActionCommand("blur");
    blurButton.addActionListener(this);
    filtersPanel.add(blurButton);

    sharpenButton = new JButton("sharpen");
    sharpenButton.setActionCommand("sharpen");
    sharpenButton.addActionListener(this);
    filtersPanel.add(sharpenButton);

    redComponentButton = new JButton("redComponent");
    redComponentButton.setActionCommand("redComponent");
    redComponentButton.addActionListener(this);
    filtersPanel.add(redComponentButton);


    greenComponentButton = new JButton("greenComponent");
    greenComponentButton.setActionCommand("greenComponent");
    greenComponentButton.addActionListener(this);
    filtersPanel.add(greenComponentButton);

    blueComponentButton = new JButton("blueComponent");
    blueComponentButton.setActionCommand("blueComponent");
    blueComponentButton.addActionListener(this);
    filtersPanel.add(blueComponentButton);

    valueComponentButton = new JButton("valueComponent");
    valueComponentButton.setActionCommand("valueComponent");
    valueComponentButton.addActionListener(this);
    filtersPanel.add(valueComponentButton);

    intensityComponentButton = new JButton("intensityComponent");
    intensityComponentButton.setActionCommand("intensityComponent");
    intensityComponentButton.addActionListener(this);
    filtersPanel.add(intensityComponentButton);

    lumaComponentButton = new JButton("lumaComponent");
    lumaComponentButton.setActionCommand("lumaComponent");
    lumaComponentButton.addActionListener(this);
    filtersPanel.add(lumaComponentButton);

    mosaicButton = new JButton("mosaic");
    mosaicButton.setActionCommand("mosaic");
    mosaicButton.addActionListener(this);
    filtersPanel.add(mosaicButton);


    imageProcessor = new ImageProcessor();
  }

  /**
   * performs a feature based on the ActionEvent of a user.
   *
   * @param e the event to be processed
   */
  public void actionPerformed(ActionEvent e) {
    String command = e.getActionCommand();
    if (command.equals("load")) {
      final JFileChooser fileChooser = new JFileChooser(".");
      FileNameExtensionFilter filter = new FileNameExtensionFilter("ppm",
              "jpg", "jpeg", "bmp", "png");
      fileChooser.setFileFilter(filter);
      int value = fileChooser.showOpenDialog(this);
      if (value == JFileChooser.APPROVE_OPTION) {
        File file = fileChooser.getSelectedFile();
        String fileName = file.getAbsolutePath();
        // System.out.println(fileName);
        imageProcessor.loadImage("image", fileName);
        updateImageAndChart();
      }
    } else if (command.equals("save")) {
      final JFileChooser fileChooser = new JFileChooser(".");
      FileNameExtensionFilter filter = new FileNameExtensionFilter("ppm",
              "jpg", "jpeg", "bmp", "png");
      fileChooser.setFileFilter(filter);
      int value = fileChooser.showSaveDialog(this);
      if (value == JFileChooser.APPROVE_OPTION) {
        File file = fileChooser.getSelectedFile();
        String fileName = file.getAbsolutePath();
        fileName += "." + saveFileType.getSelectedItem();
        imageProcessor.saveImage("image", fileName);
      }
    } else if (command.equals("sepia")) {
      imageProcessor.sepiaImage("image", "image");
      updateImageAndChart();
    } else if (command.equals("greyscale")) {
      imageProcessor.greyscale("image", "image");
      updateImageAndChart();
    } else if (command.equals("redComponent")) {
      imageProcessor.greyscale("red", "image", "image");
      updateImageAndChart();
    } else if (command.equals("greenComponent")) {
      imageProcessor.greyscale("green", "image", "image");
      updateImageAndChart();
    } else if (command.equals("blueComponent")) {
      imageProcessor.greyscale("blue", "image", "image");
      updateImageAndChart();
    } else if (command.equals("lumaComponent")) {
      imageProcessor.greyscale("luma", "image", "image");
      updateImageAndChart();
    } else if (command.equals("valueComponent")) {
      imageProcessor.greyscale("value", "image", "image");
      updateImageAndChart();
    } else if (command.equals("intensityComponent")) {
      imageProcessor.greyscale("intensity", "image", "image");
      updateImageAndChart();
    } else if (command.equals("horizontalFlip")) {
      imageProcessor.flipHor("image", "image");
      updateImageAndChart();
    } else if (command.equals("verticalFlip")) {
      imageProcessor.flipVert("image", "image");
      updateImageAndChart();
    } else if (command.equals("brighten")) {
      int increment = 0;
      String userInput = JOptionPane.showInputDialog("Increment: ");
      increment = Integer.valueOf(userInput);
      imageProcessor.brightenImage("image", increment, "image");
      updateImageAndChart();
    } else if (command.equals("mosaic")) {
      int seeds = 0;
      String userInput = JOptionPane.showInputDialog("Seeds: ");
      seeds = Integer.valueOf(userInput);
      imageProcessor.mosaicImage("image", seeds, "image");
      updateImageAndChart();
    }
  }

  /**
   * Updates the Image and Chart in real-time.
   */
  private void updateImageAndChart() {
    imagePanel.removeAll();
    displayImage();
    displayChart();
    imagePanel.repaint();
    imagePanel.revalidate();
  }

  /**
   * Displays the image in the GUI interface.
   */
  private void displayImage() {

    BufferedImage bufferedImage = imageProcessor.getBufferedImage("image");
    imageLabel = new JLabel();
    imageLabel.setIcon(new ImageIcon(bufferedImage));

    scrollPane = new JScrollPane(imageLabel);
    scrollPane.setPreferredSize(new Dimension(imagePanel.getWidth(), imagePanel.getHeight()));
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    imagePanel.add(scrollPane);

  }

  /**
   * Displays the chart in the GUI interface.
   */
  private void displayChart() {
    chartPanel = new JPanel() {
      @Override
      protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawLine("red", Color.red, g);
        drawLine("green", Color.green, g);
        drawLine("blue", Color.blue, g);
        drawLine("intensity", Color.DARK_GRAY, g);
      }
    };
    chartPanel.setPreferredSize(new Dimension(scrollPane.getWidth(), CHARTHEIGHT));
    imagePanel.add(chartPanel);
  }

  /**
   * draws the bar lines for the histogram chart.
   *
   * @param component represents a command from the user.
   * @param color     represents the color.
   * @param g         object to draw line.
   */
  private void drawLine(String component, Color color, Graphics g) {
    int[] values = imageProcessor.getComponentCount(component, "image");
    g.setColor(color);
    for (int i = 0; i < values.length - 1; i++) {
      int x1 = i * scrollPane.getWidth() / values.length;
      int y1 = values[i] / CHARTHEIGHT;
      int x2 = (i + 1) * scrollPane.getWidth() / values.length;
      int y2 = values[i + 1] / CHARTHEIGHT;
      g.drawLine(x1, y1, x2, y2);
    }
  }



  /**
   * main method to run the GUI.
   *
   * @param args represents arguments in the main method.
   */
  public static void main(String[] args) {
    ImageProcessorGUI gui = new ImageProcessorGUI();
    gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    gui.setVisible(true);
  }
}
