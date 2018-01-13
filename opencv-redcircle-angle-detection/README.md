# opencv-redcircle-angle-detection
Three red circle detection with angle calc.
"The purpose of this project is for educational purposes only."

This project was written in C++ and Python (to calc the angle (wtf)) in Debian x64

# Instructions
First you have to install some dependencies like g++ and opencv libs
```
sudo apt-get install g++ libopencv-dev python
```

Compile
```
g++ -o main main.cpp -lopencv_core -lopencv_imgproc -lopencv_highgui -lopencv_imgcodecs -std=c++0x
```
OR
```
./run.sh
```

# Usage
```
./main resources/image.jpg
```

# Discipline
- Topic 2: Image Recognition (Will Ricardo dos Santos Machado) PUC Minas - Poços de Caldas

# References
- https://github.com/sol-prog/OpenCV-red-circle-detection
- https://github.com/opencv/opencv/blob/master/samples/cpp/tutorial_code/ImgTrans/HoughCircle_Demo.cpp
