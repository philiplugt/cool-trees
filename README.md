# Cool Trees

A procedural tree generator using OpenGL and LWJGL. The project was to better understand computer graphics and OpenGL. 

This assignment was originally for an 2012 RIT Computer Graphics course.

<br>
<br>
<div align="center">
    &nbsp;
    <img width="360" alt="cool_trees_1" src="https://github.com/philiplugt/cool-trees/assets/22942635/233ccea3-aa35-46fa-8a83-6c8653707922">
    &nbsp;&nbsp;
    <img width="360" alt="cool_trees_2" src="https://github.com/philiplugt/cool-trees/assets/22942635/a65372c5-231b-43b6-ad50-e4cf9f79ea0f">
    &nbsp;
    <br>
    <br>
    &nbsp;
    <img width="360" alt="cool_trees_3" src="https://github.com/philiplugt/cool-trees/assets/22942635/37b8f8ad-734b-4b1a-801b-ae8aceb74c56">
    &nbsp;&nbsp;
    <img width="360" alt="cool_trees_4" src="https://github.com/philiplugt/cool-trees/assets/22942635/2ae14360-e00e-401a-93a0-d215e6579770">
    &nbsp;
    <br>
    <br>
    &nbsp;
    <img width="360" alt="cool_trees_5" src="https://github.com/philiplugt/cool-trees/assets/22942635/c24aaa93-0207-4b2d-b79d-6a0765252f8e">
    &nbsp;&nbsp;
    <img width="360" alt="cool_trees_6" src="https://github.com/philiplugt/cool-trees/assets/22942635/d25a7721-29c2-42ca-bd30-3e313cba4f87">
    &nbsp;
    <p><sup>A sample set of randomly generated trees</sup></p>
    <br>
</div>

### Versioning

Current version has been successfully tested and run with Java 8 and LWJGL 2.9.3 on an Intel MacOSX machine running OpenGL 2.1 (2024-01-22)

| Version | Date | Notes |
| ------- | ---- | ----- |
| v1 | 2012-05-15 | Original assignment submission |
| current | 2021-05-21 | Changes to make the random generator more realistic |

### How to use

This program is dependent on outdated libraries, so getting it to work can be a challenge. The program depends on LWJGL (Lightweight Java Graphics Library) version 2.9.3. This version been replaced with version 3 for several years now. However, updating this app to version 3 would require major changes.

To run the program:
  - Download the source files
  - Download and install Java 8
    - You can check your version with `java -version` or `java --version` depending on your version.
    - If your default `java` command (as well as `javac`) does not link to Java 8, run the binary directly from it's containing directory
  - Download LWJGL 2.9.3 and unzip to easy-to-find location
  - Set your OS's environment variable, so that Java can utilize the LWJGL's native libraries:
    - For Windows, set the `PATH` variable to the folder containing .dll files
    - For Linux, the `LD_LIBRARY_PATH` to the location of the .so files
    - For Mac, the `DYLD_LIBRARY_PATH` to the location of the .dylib files
    - You can test if the variable has been set correctly, by using `java -XshowSettings:properties` and looking for the `java.library.path` attribute
  - Compile and run the files using `javac -cp <Paths_of_source_and_jar_files> *.java` followed by `java -cp <Paths_of_source_and_jar_files> CoolTreesGenerator`

Sample terminal commands to run Cool Trees on MacOSX:
```zsh
# Check for Java 8
> <Path_to_java_8_binary>/java -version
> /Library/Java/JavaVirtualMachines/jdk1.8.0_45.jdk/Contents/Home/bin/java -version

# Adding natives to environment variable
# The path is to where you unzipped LWJGL 2.9.3
> export DYLD_LIBRARY_PATH=<Path_to_lwjgl_natives>/lwjgl-2.9.3/native/macosx  

# Compile and run using Java 8
> javac -cp <Paths_of_source_and_jar_files> *.java
> java -cp <Paths_of_source_and_jar_files> CoolTreesGenerator

# <Paths_of_source_and_jar_files> is the path to where Cool Tree source is located,
# and where you unzipped LWJGL 2.9.3. You will only need lwjgl.jar and lwjgl_util.jar.

# <Paths_of_source_and_jar_files> can be "lib/lwjgl.jar:lwjgl_uitl.jar:source/"
# where `:` is a separator (use ; for Windows), and "lib" and "source" are the location
# of the JAR files and source code within your current working directory
```

The controls of the app:

| Hotkey | Action |
| ------ | ------ |
| Right Arrow | Rotate the camera counterclockwise around the tree |
| Left Arrow | Rotate the camera clockwise around the tree |
| Up Arrow | Move forward along the red line axis |
| Down Arrow | Move backward along the red line axis |
| C | Reset camera to default settings |
| D | Randomly generate a new tree |
| Spacebar | Stop camera movement |
| = | Increase tree branch depth for the next tree (more branches) |
| - | Decrease tree branch depth for the next tree (less branches) |
  
### Details

For my Computer Graphics course we were required to create our own project. I wanted to create procedurally generated trees using L-systems and cell shading. Due to time constraints the project was reduced, cell shading was dropped, and instead of L-systems I used Gaussian random generator to angle and split the branches.

Improvements to this assignment would be to first get the camera working properly. Currently, it only pivots around the 0, 0 node, which is unwieldy. Secondly, implementing L-systems would greatly improve the tree generation, which at this point often still creates unnatural looking trees. Lastly, each tree is built up out of multiple cylinders, which can slow down the system when there are too many. I would need to investigate a better way to render these complex shapes in 3D.
