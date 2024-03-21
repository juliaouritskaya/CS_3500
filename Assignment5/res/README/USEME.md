**Script Commands**
--


The user does not have to input anything as a command line argument when running main.
When main is run, a welcome message pops up detailing all the script commands that the program
accepts. The user can then type their commands:

To load an image
* load image-path image-name (load an image)


To save an image
* save image-path image-name


To create a greyscale image with the red component
* red-component image-name dest-image-name


To create a greyscale image with the green component
* green-component image-name dest-image-name


To create a greyscale image with the blue component
* blue-component image-name dest-image-name


To create a greyscale image with the intensity component
* intensity image-name dest-image-name


To create a greyscale image with the luma component
* luma image-name dest-image-name


To create a greyscale image with the value component
* value image-name dest-image-name


To flip the image horizontally
* horizontal-flip image-name dest-image-name


To flip the image vertically
* vertical-flip image-name dest-image-name


To brighten or darken the image:
* brighten increment image-name dest-image-name



These commands each call the appropriate class that implement the ImageOperationCommand interface. All of these commands are case-sensitive and the parameters are order-sensitive. A user cannot try to utilize a command or save and image without loading one first.

For example if a user inputted these:
* load ny.ppm ny
* brighten 50 ny ny-brighten
* vertical-flip ny-brighten ny-flippedbrighten
* save ny-brighten.ppm ny-brighten
This is are acceptable inputs.

However, you can not input this:
* Load ny.ppm ny
* brighten ny ny-brighten
* save ny-brighten.ppm ny-brighten

As the load operation was capitalized, it was not taken as a command and no image was loaded. As there was no image loaded, no operations would work on a null image.
