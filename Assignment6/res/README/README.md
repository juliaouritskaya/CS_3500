Assignment 5: Image Processing (Part 2)
---
---
 Contributors: Evelyn Robert and Julia Ouritskaya


Description: This project is an image processing application that allows users to load, edit
and save images. The file formats that are supported are PPM, JPG, PNG, and, BMP. This assignment follows the MVC(Model-View-Controller) framework to make it easily modifiable for each part. Also as we added new methods onto this Assignment, we used SOLID principles to reduce code duplication and modifying earlier code</p>.

**Main:** Runs the program and reacts to:
- Text arguments via the console

**Model**
* Image: An interface that represents an image.
* ImageImpl: Implements the image class. Contains the implementations of all the operations including flip vertical, flip horizontal, brighten, intensity, all the greyscale variations(red, green, blue, luma, intensity, and value), blur, sharpen, and finally the color transformations (greyscale and sepia). Also contains getters for the pixels, width and height.
* ImageUtil: Contains the load and save methods that take in files and creates an ImageImpl object that contains the necessary information.
* Pixel: A class to represent a singular pixel.
* ProcessingModel: An interface to process all the operations done on an image and place the unedited and edited images in a Map.
* ProcessingModelImpl: The implementation of ProcessingModel that contains all operations and within them, copy the inputed image, edit it and then add the updated image into the map.
* Operations: This package holds every operation as a class and within these, they call Processing Model to call the desired operation.

**View**
* As of now this portion is empty as the program reacts to text recieved from the console.

**Controller**
* Prints a menu of options, takes data from the user and delegates that data to the model and transmits important messages back to the user.

**Source**
* Apple Link: https://www.google.com/url?sa=i&url=https%3A%2F%2Fillustoon.com%2F%3Fid%3D7166&psig=AOvVaw3nUZ2L9jf5shBcn1WTiC2V&ust=1668301739684000&source=images&cd=vfe&ved=0CA8QjRxqFwoTCLCE4Le6p_sCFQAAAAAdAAAAABAS