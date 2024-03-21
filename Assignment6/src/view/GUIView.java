package view;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

import controller.Features;
import model.Image;
import model.Pixel;

/**
 * This is an implementation of the View interface that uses Java Swing to display
 * the images and the results of applying transformation to the images. It shows any
 * error messages using a pop-up dialog box.
 */
public final class GUIView extends JFrame implements View {
  private JPanel buttonPanel;
  private JPanel listPanel;
  private JPanel histogramPanel;
  private JPanel histogramGraph;
  private JPanel imagePanel;
  private JLabel imageLabel;
  private JScrollPane imagePane;
  private JButton loadButton;
  private JButton saveButton;
  private JButton applyButton;
  private JList<String> images;
  private JList<String> filters;
  private DefaultListModel<String> model;

  public GUIView() {
    super();
    this.histogramGraph = new JPanel();
    Dimension dimension = new Dimension(900, 700);
    this.setBackground(Color.pink);
    this.setTitle("Image Processor");
    this.setPreferredSize(dimension);
    this.setLayout(new BorderLayout());
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.createMainPane(getContentPane());
    this.pack();
    super.setVisible(true);
    this.setResizable(false);
  }

  /**
   * Provides the view with all the callbacks.
   *
   * @param features the callbacks.
   */
  @Override
  public void addFeatures(Features features) {
    loadButton.addActionListener(e -> features.loadImage());
    images.addListSelectionListener(e -> features.displayImage(images.getSelectedValue()));
    applyButton.addActionListener(e -> features.applyFilter(images.getSelectedValue(), filters.getSelectedValue()));
    saveButton.addActionListener(e -> features.saveImage(images.getSelectedValue()));
  }

  /**
   * Adds an image to the GUI view to be displayed.
   *
   * @param imageName      the name of the image to be displayed.
   * @param image          the image to be displayed.
   */
  @Override
  public void addImage(String imageName, Image image) {
    this.updateImage(imageName, image);

    if (!this.model.contains(imageName)) {
      this.model.add(0, imageName);
      this.images.setSelectedIndex(0);
    } else {
      for (int i = 0; i < this.model.getSize(); i++) {
        if (this.model.get(i).equals(imageName)) {
          this.images.setSelectedIndex(i);
        }
      }
    }
  }

  /**
   * Updates the image displayed in the GUI view.
   *
   * @param imageName      the name of the image being displayed.
   * @param image          the image being displayed.
   */
  @Override
  public void updateImage(String imageName, Image image) {
    BufferedImage toDisplay = new BufferedImage(image.getWidth(), image.getHeight(),
            BufferedImage.TYPE_INT_RGB);

    Pixel[][] pixels = image.getPixels();

    for (int row = 0; row < image.getHeight(); row++) {
      for (int col = 0; col < image.getWidth(); col++) {
        int red = pixels[col][row].getRed();
        int green = pixels[col][row].getGreen();
        int blue = pixels[col][row].getBlue();
        int color = (red << 16) + (green << 8) + blue;
        toDisplay.setRGB(col, row, color);
      }
    }

    try {
      this.imageLabel.setIcon(new ImageIcon(toDisplay));
      this.updateHistogram(image);
    } catch (Exception e) {
      this.renderMessage("Failed to display image.");
    }
  }

  /**
   * Returns a list of images that have been loaded into the program.
   *
   * @return a list of images as strings.
   */
  @Override
  public DefaultListModel<String> getImages() {
    DefaultListModel<String> deepCopy = new DefaultListModel<>();

    for (int i = 0; i < this.model.size(); i++) {
      deepCopy.add(i, this.model.getElementAt(i));
    }
    return deepCopy;
  }

  /**
   * Render a specific message to the provided data destination.
   *
   * @param message the message to be transmitted.
   * @throws IllegalStateException if transmission of the board to the provided data
   *                               destination fails.
   */
  @Override
  public void renderMessage(String message) throws IllegalStateException {
    JOptionPane.showMessageDialog(this, message, "", JOptionPane.OK_CANCEL_OPTION);
  }


  private void createMainPane(Container pane) {
    JPanel basePanel = new JPanel();
    JPanel topPanel = new JPanel();
    JPanel bottomPanel = new JPanel();
    Color lightPink = new Color(255, 182, 193);

    basePanel.setLayout(new BorderLayout());

    // TOP PANEL
    topPanel.setLayout(new FlowLayout());
    topPanel.setBackground(lightPink);
    // LEFT BUTTON PANEL
    this.makeButtons();
    // ADD BUTTON PANEL TO TOP PANEL
    topPanel.add(this.buttonPanel);
    // RIGHT LIST PANEL
    this.makeLists();
    // ADD LIST PANEL TO TOP PANEL
    topPanel.add(this.listPanel);
    basePanel.add(topPanel, BorderLayout.PAGE_START);


    // BOTTOM PANEL
    bottomPanel.setLayout(new FlowLayout());
    bottomPanel.setBackground(lightPink);
    // LEFT IMAGE PANEL
    this.makeImage();
    // ADD IMAGE PANEL TO BOTTOM PANEL
    bottomPanel.add(this.imagePanel);
    // RIGHT HISTOGRAM PANEL
    this.makeHistogram();
    // ADD HISTOGRAM PANEL TO BOTTOM PANEL
    bottomPanel.add(this.histogramPanel);
    basePanel.add(bottomPanel);
    basePanel.setBackground(lightPink);

    pane.add(basePanel, BorderLayout.EAST);
  }

  private void makeButtons() {
    buttonPanel = new JPanel();
    buttonPanel.setLayout(new GridLayout(3, 1));
    buttonPanel.setBackground(new Color(255, 182, 193));
    loadButton = new JButton("Load");
    loadButton.setActionCommand("Load");
    loadButton.setPreferredSize(new Dimension(200, 50));
    loadButton.setForeground(Color.pink);
    buttonPanel.add(loadButton);

    saveButton = new JButton("Save");
    saveButton.setActionCommand("Save");
    saveButton.setPreferredSize(new Dimension(200, 50));
    saveButton.setForeground(Color.pink);
    buttonPanel.add(saveButton);

    applyButton = new JButton("Apply");
    applyButton.setActionCommand("Apply");
    applyButton.setPreferredSize(new Dimension(200, 50));
    applyButton.setForeground(Color.pink);
    buttonPanel.add(applyButton);
  }

  private void makeLists() {
    JPanel imagesList = new JPanel();
    imagesList.setPreferredSize(new Dimension(300, 200));
    imagesList.setLayout(new BoxLayout(imagesList, BoxLayout.X_AXIS));
    imagesList.setBorder(BorderFactory.createLineBorder(new Color(255, 0, 127)));

    this.model = new DefaultListModel<>();
    this.images = new JList<>(this.model);
    this.images.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    imagesList.add(new JScrollPane(this.images));

    JPanel filtersList = new JPanel();
    filtersList.setPreferredSize(new Dimension(300, 200));
    filtersList.setLayout(new BoxLayout(filtersList, BoxLayout.X_AXIS));
    filtersList.setBorder(BorderFactory.createLineBorder(new Color(255, 0, 127)));
    String [] transformations = {
            "red-component",
            "green-component",
            "blue-component",
            "intensity",
            "luma",
            "value",
            "horizontal-flip",
            "vertical-flip",
            "brighten",
            "mosaic",
            "sepia",
            "greyscale",
            "sharpen",
            "blur"
    };

    DefaultListModel<String> filterModel = new DefaultListModel<>();
    for (String filter : transformations) {
      filterModel.addElement(filter);
    }
    this.filters = new JList<>(filterModel);
    this.filters.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    filtersList.add(new JScrollPane(this.filters));

    listPanel = new JPanel();
    listPanel.setLayout(new FlowLayout());
    listPanel.setBackground(new Color(255, 182, 193));
    listPanel.add(imagesList);
    listPanel.add(filtersList);
  }

  private void makeImage() {
    this.imagePanel = new JPanel();
    imagePanel.setBackground(new Color(255, 0, 127));
    imageLabel = new JLabel();
    imageLabel.setLayout(new BorderLayout());
    imagePane = new JScrollPane(imageLabel);
    imagePane.setPreferredSize(new Dimension(550, 420));
    this.imagePanel.add(imagePane);
  }

  private void makeHistogram() {
    this.histogramPanel = new JPanel();
    this.histogramPanel.setLayout(new BorderLayout());
    this.histogramPanel.setPreferredSize(new Dimension(360, 430));
    histogramPanel.setBackground(new Color(255, 0, 127));
    histogramPanel.setBorder(BorderFactory.createLineBorder(new Color(255, 0, 127)));
  }
  
  private void updateHistogram(Image image) {
    histogramGraph.removeAll();
    histogramGraph.setLayout(new BoxLayout(histogramGraph, BoxLayout.LINE_AXIS));
    int [][] histogram = image.histogram();
    JPanel redHistogram = new Histogram(histogram[0], Color.red);
    redHistogram.setPreferredSize(new Dimension(300, 400));
    histogramGraph.add(redHistogram);
    JPanel greenHistogram = new Histogram(histogram[1], Color.green);
    histogramGraph.add(greenHistogram);
    JPanel blueHistogram = new Histogram(histogram[2], Color.blue);
    histogramGraph.add(blueHistogram);
    JPanel intensityHistogram = new Histogram(histogram[3], Color.black);
    histogramGraph.add(intensityHistogram);
    histogramGraph.revalidate();
    histogramGraph.repaint();
    histogramPanel.add(histogramGraph, BorderLayout.CENTER);
  }
}
