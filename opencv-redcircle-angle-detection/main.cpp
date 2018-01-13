#include <opencv2/opencv.hpp>
#include <iostream>
#include <string>
#include <vector>
#include <sstream>


std::string FtoS (float number){
    std::ostringstream buff;
    buff<<number;
    return buff.str();
}

void verifica_imagem(const cv::Mat &img, const std::string &path) {
	if(img.empty()) {
		std::cout << "Caminho da imagem invalido-> " << path << std::endl;
		std::exit(-1);
	}
}

float calculaDistancia( cv::Point p1, cv::Point p2 )
{
 int dx = p2.x - p1.x;
 int dy = p2.y - p1.y;
 return cvSqrt( (float)(dx*dx + dy*dy));
}


int main(int argc, char **argv) {
	if(argc != 2)
	{
		std::cout << "\nQuantidade de argumentos invalidos:" << std::endl;
		std::cout << "Tente o comando: ./main image.jpg" << std::endl;
		std::exit(-1);
	}

	// Carregar Imagem
	std::string path_image{argv[1]};
	cv::Mat bgr_image = cv::imread(path_image);

	// Verifica se a imagem existe em disco
	verifica_imagem(bgr_image, path_image);

	cv::Mat orig_image = bgr_image.clone();

	cv::medianBlur(bgr_image, bgr_image, 3);

	// Converter imagem em HSV
	cv::Mat hsv_image;
	cv::cvtColor(bgr_image, hsv_image, cv::COLOR_BGR2HSV);

	cv::Mat lower_red_hue_range;
	cv::Mat upper_red_hue_range;
	cv::inRange(hsv_image, cv::Scalar(0, 100, 100), cv::Scalar(10, 255, 255), lower_red_hue_range);
	cv::inRange(hsv_image, cv::Scalar(160, 100, 100), cv::Scalar(179, 255, 255), upper_red_hue_range);

	cv::Mat red_hue_image;
	cv::addWeighted(lower_red_hue_range, 1.0, upper_red_hue_range, 1.0, 0.0, red_hue_image);

	cv::GaussianBlur(red_hue_image, red_hue_image, cv::Size(9, 9), 2, 2);

	// HoughCircles para detectar os circulos vermelhos na imagem
	std::vector<cv::Vec3f> circles;
	cv::HoughCircles(red_hue_image, circles, CV_HOUGH_GRADIENT, 1, red_hue_image.rows/8, 100, 20, 0, 0);

	if(circles.size() == 0) std::exit(-1);

	cv::Point circulo1(std::round(circles[0][0]), std::round(circles[0][1]));
	cv::Point circulo2(std::round(circles[1][0]), std::round(circles[1][1]));
	cv::Point circulo3(std::round(circles[2][0]), std::round(circles[2][1]));

	//Desenha Triangulo
	cv::line(orig_image, circulo1, circulo2, cv::Scalar(0, 255, 0));
	cv::line(orig_image, circulo1, circulo3, cv::Scalar(0, 255, 0));
	cv::line(orig_image, circulo2, circulo3, cv::Scalar(0, 255, 0));

	float distanciaTJ = calculaDistancia(circulo3, circulo2);
	float distanciaJC =  calculaDistancia(circulo2, circulo1);
	float distanciaCT = calculaDistancia(circulo1, circulo3);

	std::cout << "Distancia Tornozelo -> Joelho: " << distanciaTJ << std::endl;
	std::cout << "Distancia Joelho -> Cintura: " << distanciaJC << std::endl;
	std::cout << "Distancia Cintura -> Tornozelo: " << distanciaCT << std::endl;

	char buffer[255];
	snprintf(buffer, sizeof(buffer), "python calculaAngulo.py %f %f %f", distanciaTJ, distanciaJC, distanciaCT);
	std::system(buffer);

	cv::namedWindow("OPENCV", cv::WINDOW_AUTOSIZE);
	cv::imshow("OPENCV", orig_image);

	cv::waitKey(0);


	return 0;
}
